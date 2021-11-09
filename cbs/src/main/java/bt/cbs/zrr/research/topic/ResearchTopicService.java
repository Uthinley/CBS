package bt.cbs.zrr.research.topic;

import bt.cbs.zrr.global.base.BaseService;
import bt.cbs.zrr.global.common.CommonService;
import bt.cbs.zrr.global.dto.CurrentUser;
import bt.cbs.zrr.global.dto.GenericDTO;
import bt.cbs.zrr.global.dto.ResponseMessage;
import bt.cbs.zrr.global.enumeration.ApplicationStatusCode;
import bt.cbs.zrr.global.enumeration.UserGroup;
import bt.cbs.zrr.global.library.CustomFileUtil;
import bt.cbs.zrr.research.comment.ResearchCommentService;
import bt.cbs.zrr.research.paper.ResearchDAO;
import bt.cbs.zrr.research.paper.ResearchDTO;
import bt.cbs.zrr.research.paper.ResearchEntity;
import bt.cbs.zrr.setup.user.UserSetupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class ResearchTopicService extends BaseService {

    @Autowired
    private ResearchTopicDAO topicDAO;


    @Transactional
    public ResponseMessage save(CurrentUser currentUser, ResearchTopicDTO topicDTO) throws IOException {

        if(Objects.isNull(topicDTO)){
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("Please enter all the required fields.");
        }


        ResearchTopicEntity topicEntity = convertDTOToEntity(topicDTO,currentUser);
        responseMessage.setText("Your research Title for the month of "+topicDTO.getResearch_month()+" is submitted successfully for approval.");

        if(topicDTO.getActionType().equalsIgnoreCase("M")){
            topicEntity.setResearch_topic_id(topicDTO.getResearch_topic_id());
            responseMessage.setText("Your research Title for the month of "+topicDTO.getResearch_month()+" is re-submitted successfully.");

        }
        topicDAO.save(topicEntity);
        responseMessage.setStatus(SUCCESSFUL_STATUS);
         return responseMessage;
    }

    @Transactional(readOnly = true)
    public List gTopicList(String status, String research_month,CurrentUser currentUser) {
        if(currentUser != null && currentUser.getGroupId() == UserGroup.RESEARCHER.value()){
            return topicDAO.gTopicList(status, currentUser.getUserName(),research_month);
        }
        return topicDAO.gTopicList(status, null,research_month);

    }

    @Transactional(readOnly = true)
    public ResearchTopicDTO findTopic(String research_month, String userName) {
        return topicDAO.findTopic(research_month,userName);
    }

    private ResearchTopicEntity convertDTOToEntity(ResearchTopicDTO topicDTO, CurrentUser currentUser){

        ResearchTopicEntity topicEntity = new ResearchTopicEntity();
        topicEntity.setResearch_month(topicDTO.getResearch_month());
        topicEntity.setResearch_topic(topicDTO.getResearch_topic());
        topicEntity.setStatus(topicDTO.getStatus());
        topicEntity.setRemarks(topicDTO.getRemarks());
        topicEntity.setCreatedBy(currentUser.getUserName());
        topicEntity.setCreatedDate(new Date());
        return topicEntity;
    }


}
