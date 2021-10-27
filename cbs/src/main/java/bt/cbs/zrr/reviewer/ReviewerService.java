package bt.cbs.zrr.reviewer;

import bt.cbs.zrr.global.base.BaseService;
import bt.cbs.zrr.research.ResearchDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewerService extends BaseService {
    private ReviewerDAO reviewerDAO;

    public List<ResearchDTO> getReviewerList() {
        return reviewerDAO.getReviewerList();
    }
}
