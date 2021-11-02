package bt.cbs.zrr.reviewer.review;

import bt.cbs.zrr.global.base.BaseService;
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
}
