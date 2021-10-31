package bt.cbs.zrr.reviewer.title;

import bt.cbs.zrr.global.base.BaseService;
import bt.cbs.zrr.global.dto.CurrentUser;
import bt.cbs.zrr.global.dto.ResponseMessage;
import bt.cbs.zrr.research.topic.ResearchTopicDAO;
import bt.cbs.zrr.research.topic.ResearchTopicDTO;
import bt.cbs.zrr.research.topic.ResearchTopicEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class TitleApproveService extends BaseService {

    private final TitleApproveDAO tApproveDao;

    public TitleApproveService(TitleApproveDAO tApproveDao) {
        this.tApproveDao = tApproveDao;
    }


    @Transactional(readOnly = true)
    public List gTopicList(String status, String userName) {
        return tApproveDao.gTopicList(status,userName);
    }

    @Transactional(readOnly = true)
    public ResearchTopicDTO findTopic(String research_month, String userName) {
        return tApproveDao.findTopic(research_month,userName);
    }


    @Transactional
    public ResponseMessage save(CurrentUser currentUser, String status, String remarks, String[] research_title_ids) {
        if(Objects.isNull(status)){
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("Please enter all the required fields.");
        }

        int i=0;
        for(String id: research_title_ids){
            ResearchTopicEntity entity =  tApproveDao.load(Integer.valueOf(id));
            entity.setRemarks(remarks);
            entity.setStatus(status);
            entity.setApprover(currentUser.getUserName());
            entity.setApprovedDate(currentUser.getServerDate());
            tApproveDao.approve_reject(entity);
            i++;
        }


        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Operation success. You have "+(status.equals("A")?"Approved ":"Returned ") + i + " research titles");
        return responseMessage;
    }
}
