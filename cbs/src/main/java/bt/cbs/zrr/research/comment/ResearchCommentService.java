package bt.cbs.zrr.research.comment;

import bt.cbs.zrr.global.base.BaseService;
import bt.cbs.zrr.global.common.CommonService;
import bt.cbs.zrr.global.dto.CurrentUser;
import bt.cbs.zrr.global.dto.ResponseMessage;
import bt.cbs.zrr.research.paper.ResearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ResearchCommentService extends BaseService {

    @Autowired
    private ResearchCommentDAO researchDAO;

    @Autowired
    private CommonService commonService;

    public ResponseMessage save(ResearchDTO researchDTO, CurrentUser currentUser){
        ResearchComment researchComment = new ResearchComment();
        researchComment.setPaper_version(researchDTO.getPaper_version());
        researchComment.setResearch_number(researchDTO.getResearch_number());
        researchComment.setResearch_comment(researchDTO.getResearch_abstract());
        researchComment.setResearch_document_path(researchDTO.getFilePath());
        researchComment.setResearch_status(researchDTO.getStatus());
        researchComment.setCreatedBy(currentUser.getUserName());
        researchComment.setCreatedDate(currentUser.getServerDate());
        researchDAO.save(researchComment);
        responseMessage = new ResponseMessage();
        responseMessage.setStatus(1);
        responseMessage.setText("Data recorded.");
        return responseMessage;
    }

    @Transactional
    public ResponseMessage reviewerComment(Integer researchId, String rComment, Integer statusId, CurrentUser currentUser){
        String research_number = commonService.getValue("research_dtls","research_number","research_id",researchId).toString();
        ResearchComment researchComment = new ResearchComment();
        researchComment.setResearch_number(research_number);
        researchComment.setResearch_comment(rComment);
        researchComment.setResearch_status(statusId.toString());
        researchComment.setCreatedBy(currentUser.getUserName());
        researchComment.setCreatedDate(currentUser.getServerDate());
        researchDAO.save(researchComment);
        responseMessage = new ResponseMessage();
        responseMessage.setStatus(1);
        responseMessage.setText("Data recorded.");
        return responseMessage;
    }



    /*public ResponseMessage save(MultipartFile file, CurrentUser currentUser, ResearchDTO researchDTO) throws IOException {
        if(Objects.isNull(file)){
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("Please choose file to upload.");
        }

        String date = new SimpleDateFormat("dd-MMM-yyyy").format(new Date());
        String sub_folder = currentUser.getUserName() + "/" + date + "/" +file.getOriginalFilename();

        String filePath = CustomFileUtil.uploadFile(file,sub_folder,file.getOriginalFilename());
        Long wordCount =  CustomFileUtil.wordCount(file.getInputStream());
        researchDTO.setWordCount(wordCount.intValue());
        researchDTO.setFilePath(filePath);
        ResearchEntity researchEntity = convertDTOToEntity(researchDTO, currentUser);
        researchDAO.save(researchEntity);
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Your research has been recorded with research paper number <b>"+researchEntity.getResearch_number()+"</b> and forwarded for review.");
        return responseMessage;
    }

    private ResearchEntity convertDTOToEntity(ResearchDTO researchDTO, CurrentUser currentUser){

        ResearchEntity researchEntity = new ResearchEntity();
        researchEntity.setResearchTopic(researchDTO.getResearchTopic());
        researchEntity.setResearch_description(researchDTO.getResearch_description());
        researchEntity.setFilepath(researchDTO.getFilePath());
        researchEntity.setCreatedBy(currentUser.getUserName());
        researchEntity.setCreatedDate(new Date());
        researchEntity.setWordCount(researchDTO.getWordCount().longValue());
        researchEntity.setStatus(ApplicationStatusCode.SUBMITTED.getValue());
        researchEntity.setResearch_number(generateResearchNumber());
        researchEntity.setPaper_version(1);
        return researchEntity;
    }


    *//**
     * Generate the research number
     * Fist four is year, and last four is running sequence
     * @return the generated number
     *//*
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
    */
}
