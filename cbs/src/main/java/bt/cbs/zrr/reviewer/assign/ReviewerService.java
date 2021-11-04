package bt.cbs.zrr.reviewer.assign;

import bt.cbs.zrr.global.dto.CurrentUser;
import bt.cbs.zrr.global.dto.DropdownDTO;
//import com.springapp.mvc.global.base.BaseService;
//import com.springapp.mvc.global.dto.CurrentUser;
//import com.springapp.mvc.global.dto.DropdownDTO;
//import com.springapp.mvc.global.dto.ResponseMessage;
//import com.springapp.mvc.research.ResearchDTO;
import bt.cbs.zrr.global.dto.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import bt.cbs.zrr.global.base.BaseService;
import bt.cbs.zrr.research.paper.ResearchDTO;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReviewerService extends BaseService {
    @Autowired
    private ReviewerDAO reviewerDAO;

    public List<ResearchDTO> getResearchListForReview(String dateFormat) {
        return reviewerDAO.getResearchListForReview(dateFormat);
    }
//    public List<ResearchDTO> getAssingedResearchList(Date dateFormat) {
//        return reviewerDAO.getAssingedResearchList(dateFormat);
//    }

    public List<DropdownDTO> getReviewerList() {
        return reviewerDAO.getReviewerList();
    }

    public ResponseMessage assignReviewer(CurrentUser currentUser, Integer researchNo, Integer reviewerId, Date completionDate) {
        ResearchReviewerEntity researchReviewerEntity= new ResearchReviewerEntity();
        researchReviewerEntity.setReviewerId(reviewerId);
        researchReviewerEntity.setResearchNumber(researchNo);
        researchReviewerEntity.setAssignedDate(new Date());
        researchReviewerEntity.setCompletionDate(completionDate);
        researchReviewerEntity.setCreatedBy(currentUser.getUserName());
        researchReviewerEntity.setCreatedDate(new Date());

        reviewerDAO.save(researchReviewerEntity);
        reviewerDAO.upDateResearchStatus(researchNo);
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Assigned Successfully");
        return responseMessage;
    }

    public List<ResearchDTO> getAssignedResearchList(String month) {
        return reviewerDAO.getAssignedResearchList(month);
    }
}
