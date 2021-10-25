package com.springapp.mvc.reviewer;

import com.springapp.mvc.global.base.BaseDao;
import com.springapp.mvc.research.ResearchDTO;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ReviewerDAO extends BaseDao {
    private Query hQuery;

    @Transactional
    public List<ResearchDTO> getReviewerList() {
        sqlQuery = properties.getProperty("ReviewerDAO.getReviewerList");
        hQuery = (Query) hibernateQuery(sqlQuery, ResearchDTO.class);
        return hQuery.list();
    }
}
