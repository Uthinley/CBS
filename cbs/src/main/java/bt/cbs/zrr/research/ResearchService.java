package bt.cbs.zrr.research;

import bt.cbs.zrr.global.base.BaseService;
import bt.cbs.zrr.global.common.CommonService;
import bt.cbs.zrr.global.dto.CurrentUser;
import bt.cbs.zrr.global.dto.ResponseMessage;
import bt.cbs.zrr.global.library.CustomFileUtil;
import bt.cbs.zrr.research.comment.ResearchCommentService;
import bt.cbs.zrr.setup.user.UserSetupDTO;
import bt.cbs.zrr.global.enumeration.ApplicationStatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
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

        String date = new SimpleDateFormat("dd-MMM-yyyy").format(new Date());
        String research_number = generateResearchNumber();
        String sub_folder = currentUser.getUserName() + "/" + date + "/" +research_number;



        String filePath = CustomFileUtil.uploadFile(rPaper,sub_folder,rPaper.getOriginalFilename());
        String sDocName;
        StringBuilder supporting_documents_name =  new StringBuilder();
        int i = 0;
        for(MultipartFile sDocument: researchDTO.getSupporting_documents()){
            if (i != 0) supporting_documents_name.append(",");
            i++;
            sDocName = "SD"+i+"_"+Objects.requireNonNull(sDocument.getOriginalFilename()).replace(",","");//remove comma in file name
            supporting_documents_name.append(sDocName);
            CustomFileUtil.uploadFile(sDocument,sub_folder,sDocName);

        }

        Long wordCount =  CustomFileUtil.wordCount(rPaper.getInputStream());
        researchDTO.setWordCount(wordCount.intValue());
        researchDTO.setFilePath(filePath.substring(0,filePath.lastIndexOf("/")));
        researchDTO.setResearch_number(research_number);
        ResearchEntity researchEntity = convertDTOToEntity(researchDTO, currentUser);
        researchEntity.setResearch_paper_name(rPaper.getOriginalFilename());
        researchEntity.setSupporting_documents_name(supporting_documents_name.toString());
        researchDAO.save(researchEntity);

        researchDTO.setPaper_version(researchEntity.getPaper_version());
        researchDTO.setStatus(researchEntity.getStatus().toString());
        commentService.save(researchDTO, currentUser);

        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Your research has been recorded with research paper number <b>"+researchEntity.getResearch_number()+"</b> and forwarded for review.");
        return responseMessage;
    }



    private ResearchEntity convertDTOToEntity(ResearchDTO researchDTO, CurrentUser currentUser){

        ResearchEntity researchEntity = new ResearchEntity();
        researchEntity.setResearchTopic(researchDTO.getResearchTopic());
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
     * Fist four is year, and last four is running sequence
     * @return the generated number
     */
    private String generateResearchNumber(){
        Object nextRNumber = commonService.getNextID("research_dtls","research_number");
        int curYear = Calendar.getInstance().get(Calendar.YEAR);
        if(nextRNumber == null){
            return Integer.valueOf(curYear).toString()+"0001";
        }
        String research_number = Integer.valueOf(((Double)nextRNumber).intValue()).toString();

        //if year is changed, restart sequence from 0001
        if(curYear != Integer.parseInt(research_number.substring(0,4))){
            research_number = Integer.valueOf(curYear).toString()+"0001";
        }

        return research_number;

    }


    public List<ResearchDTO> getResearchList(CurrentUser currentUser) {
        return researchDAO.getResearchList(currentUser);
    }

    public List<ResearchDTO> geAllResearchList() {
        return researchDAO.geAllResearchList();
    }

    public ResponseMessage saveReviewerComments(Integer researchId, String rComment, Integer statusId) {
        researchDAO.saveReviewerComments(researchId, rComment, statusId);
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Saved successfully");
        return responseMessage;
    }

    @Transactional(readOnly = true)
    public GenericDTO getSummaryReport() {
        return researchDAO.getSummaryReport();
    }

    public List<UserSetupDTO> getResearcherList() {
        return researchDAO.getResearcherList(1);
    }

    public List<ResearchDTO> getReviewedResearchList() {
        return researchDAO.getReviewedResearchList(3);
    }
}
