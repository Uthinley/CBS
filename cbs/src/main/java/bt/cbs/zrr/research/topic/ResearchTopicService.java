package bt.cbs.zrr.research.topic;

import bt.cbs.zrr.global.base.BaseService;
import bt.cbs.zrr.global.common.CommonService;
import bt.cbs.zrr.global.dto.CurrentUser;
import bt.cbs.zrr.global.dto.GenericDTO;
import bt.cbs.zrr.global.dto.ResponseMessage;
import bt.cbs.zrr.global.enumeration.ApplicationStatusCode;
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

        String date = new SimpleDateFormat("dd-MMM-yyyy").format(new Date());

        ResearchTopicEntity topicEntity = convertDTOToEntity(topicDTO,currentUser);
        topicDAO.save(topicEntity);

        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Your research Topic has been recorded for the month of "+topicDTO.getResearch_month()+" and forwarded for review.");
        return responseMessage;
    }

    private ResearchTopicEntity convertDTOToEntity(ResearchTopicDTO topicDTO, CurrentUser currentUser){

        ResearchTopicEntity topicEntity = new ResearchTopicEntity();
        topicEntity.setResearch_topic(topicDTO.getResearch_topic());
        topicEntity.setResearch_topic(topicDTO.getResearch_topic());
        topicEntity.setStatus(ApplicationStatusCode.SUBMITTED.getValue().toString());
        topicEntity.setRemarks(topicDTO.getRemarks());
        topicEntity.setCreatedBy(currentUser.getUserName());
        topicEntity.setCreatedDate(new Date());
        return topicEntity;
    }

}
