package com.springapp.mvc.reviewer;

import com.springapp.mvc.global.base.BaseService;
import com.springapp.mvc.global.dto.CurrentUser;
import com.springapp.mvc.global.dto.DropdownDTO;
import com.springapp.mvc.global.dto.ResponseMessage;
import com.springapp.mvc.research.ResearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
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
