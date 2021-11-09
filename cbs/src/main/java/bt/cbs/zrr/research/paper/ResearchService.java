package bt.cbs.zrr.research.paper;

import bt.cbs.zrr.global.base.BaseService;
import bt.cbs.zrr.global.common.CommonService;
import bt.cbs.zrr.global.common.DeleteHistory;
import bt.cbs.zrr.global.dto.CurrentUser;
import bt.cbs.zrr.global.dto.ResponseMessage;
import bt.cbs.zrr.global.enumeration.UserGroup;
import bt.cbs.zrr.global.library.CustomFileUtil;
import bt.cbs.zrr.research.comment.ResearchCommentService;
import bt.cbs.zrr.global.enumeration.ApplicationStatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ResearchService extends BaseService {

    @Autowired
    private ResearchDAO researchDAO;

    @Autowired
    private CommonService commonService;

    @Autowired
    private ResearchCommentService commentService;


    @Transactional
    public ResponseMessage save(CurrentUser currentUser, ResearchDTO researchDTO) throws IOException {

        MultipartFile rPaper = researchDTO.getResearch_paper();
        if(Objects.isNull(rPaper)){
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("Please choose file research paper to upload.");
        }
        String research_number;
        String file_name;
        if(researchDTO.getActionType().equalsIgnoreCase("M")){
            file_name = "MM_"+rPaper.getOriginalFilename();
            research_number = researchDTO.getResearch_number();
            responseMessage.setText("Your research is updated successfully  for research number <b>"+research_number+"</b> and forwarded for review.");

        }else{
            file_name = "NN_"+rPaper.getOriginalFilename();
            research_number = generateResearchNumber();
            responseMessage.setText("Your research has been recorded with research paper number <b>"+research_number+"</b> and forwarded for review.");

        }

        String date = new SimpleDateFormat("dd-MMM-yyyy").format(new Date());
        String sub_folder = currentUser.getUserName() + "/" + date + "/" +research_number;
        String filePath = CustomFileUtil.uploadFile(rPaper,sub_folder,file_name);
        Long wordCount =  CustomFileUtil.wordCount(rPaper);

        researchDTO.setWordCount(wordCount.intValue());
        researchDTO.setFilePath(filePath.substring(0,filePath.lastIndexOf("/")));
        researchDTO.setResearch_number(research_number);
        ResearchEntity researchEntity = convertDTOToEntity(researchDTO, currentUser);
        researchEntity.setResearch_paper_name(file_name);
        researchDAO.save(researchEntity);


        responseMessage.setStatus(SUCCESSFUL_STATUS);
        return responseMessage;
    }


    @Transactional(readOnly = true)
    public List<ResearchDTO> getResearchList(CurrentUser currentUser) {
        if(currentUser.getGroupId() == UserGroup.RESEARCHER.value()) {
            return researchDAO.getResearchList(currentUser.getUserName());
        }
        return researchDAO.getResearchList(null);
    }

    @Transactional(readOnly = true)
    public ResearchDTO findResearch(String research_number) {
        return researchDAO.findResearch(research_number);
    }

    @Transactional
    public ResponseMessage delete(String research_number, String research_id, CurrentUser currentUser) {
        researchDAO.delete(research_number);
        commonService.saveAuditHistory("research_dtls",research_id,currentUser.getUserName());
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Deleted successfully.");
        return responseMessage;
    }



    private ResearchEntity convertDTOToEntity(ResearchDTO researchDTO, CurrentUser currentUser){

        ResearchEntity researchEntity = new ResearchEntity();

        researchEntity.setResearchTopic(researchDTO.getResearchTopic());
        researchEntity.setResearchId(researchDTO.getResearchId());
        researchEntity.setResearch_abstract(researchDTO.getResearch_abstract());
        researchEntity.setKey_words(researchDTO.getKey_words());
        researchEntity.setFilepath(researchDTO.getFilePath());
        researchEntity.setResearch_month(researchDTO.getResearch_month());
        researchEntity.setWordCount(researchDTO.getWordCount().longValue());
        researchEntity.setStatus(ApplicationStatusCode.SUBMITTED.getValue());
        researchEntity.setResearch_number(researchDTO.getResearch_number());
        researchEntity.setPaper_version(1);
        researchEntity.setCreatedBy(currentUser.getUserName());
        researchEntity.setCreatedDate(new Date());
        return researchEntity;
    }

    /**
     * Generate the research number
     * Fist four is year, middle two is month and last four is running sequence
     * Example: 2021090001
     * @return the generated number
     */
    private String generateResearchNumber(){
        Object nextRNumber = commonService.getNextID("research_dtls","research_number");
        String curYear = Integer.valueOf(Calendar.getInstance().get(Calendar.YEAR)).toString();
        String curMonth = Integer.valueOf(Calendar.getInstance().get(Calendar.MONTH)+1).toString();
        if(curMonth.length()==1){
            curMonth='0'+curMonth;
        }
        if(nextRNumber == null){
            return curYear+curMonth+"0001";
        }
        String research_number = Integer.valueOf(((Double)nextRNumber).intValue()).toString();

        //if year is changed, restart sequence from 0001
        if(!curMonth.equalsIgnoreCase(research_number.substring(4,6))){
            research_number = curYear+curMonth+"0001";
        }

        return research_number;

    }
}
