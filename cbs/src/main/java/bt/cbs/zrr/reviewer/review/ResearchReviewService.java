package bt.cbs.zrr.reviewer.review;

import bt.cbs.zrr.global.base.BaseService;
import bt.cbs.zrr.global.dto.CurrentUser;
import bt.cbs.zrr.global.dto.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ResearchReviewService extends BaseService {
    @Autowired
    private ResearchReviewDAO researchReviewDAO;

    @Transactional(readOnly = true)
    public List gResearchList(String status, Integer userId, String rMonth) {
        return researchReviewDAO.gResearchList(status,userId, rMonth);
    }

    public ResponseMessage saveReviewerMarks(CurrentUser currentUser, String rComment,Integer researchNo, Integer researchId,
                                             String[] assessmentCriteria, String[] weightage, String[] marksAllocated) {
        researchReviewDAO.saveReviewerComments(rComment, researchId, currentUser);
        for (int i=0; i< assessmentCriteria.length; i++){
            researchReviewDAO.saveReviewerMarks(researchNo,assessmentCriteria[i], weightage[i],marksAllocated[i], currentUser );
        }
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Saved Successfully");
        return responseMessage;
    }
}
