package com.springapp.mvc.reviewer;

import com.springapp.mvc.global.base.BaseService;
import com.springapp.mvc.research.ResearchDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewerService extends BaseService {
    private ReviewerDAO reviewerDAO;

    public List<ResearchDTO> getReviewerList() {
        return reviewerDAO.getReviewerList();
    }
}
