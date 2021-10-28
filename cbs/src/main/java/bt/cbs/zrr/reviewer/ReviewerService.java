package bt.cbs.zrr.reviewer;

<<<<<<< HEAD:cbs/src/main/java/com/springapp/mvc/reviewer/ReviewerService.java
import com.springapp.mvc.global.base.BaseService;
import com.springapp.mvc.global.dto.CurrentUser;
import com.springapp.mvc.global.dto.DropdownDTO;
import com.springapp.mvc.global.dto.ResponseMessage;
import com.springapp.mvc.research.ResearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
=======
import bt.cbs.zrr.global.base.BaseService;
import bt.cbs.zrr.research.paper.ResearchDTO;
>>>>>>> d1f0d338627f6b9484424f65ccf3c7c0b4345860:cbs/src/main/java/bt/cbs/zrr/reviewer/ReviewerService.java
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReviewerService extends BaseService {
    @Autowired
    private ReviewerDAO reviewerDAO;

    public List<ResearchDTO> getResearchListForReview(Date dateFormat) {
        return reviewerDAO.getResearchListForReview(dateFormat);
    }

    public List<DropdownDTO> getReviewerList() {
        return reviewerDAO.getReviewerList();
    }

    public ResponseMessage assignReviewer(CurrentUser currentUser, Integer researchNo, Integer reviewerId, Date completionDate) {
        reviewerDAO.assignReviewer(currentUser, researchNo, reviewerId, completionDate);
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Assigned Successfully");
        return responseMessage;
    }
}
