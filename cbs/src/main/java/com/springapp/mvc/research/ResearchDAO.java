package com.springapp.mvc.research;

import com.springapp.mvc.global.base.BaseDao;
import com.springapp.mvc.global.dto.CurrentUser;
import com.springapp.mvc.global.dto.ResponseMessage;
import com.springapp.mvc.setup.card.CardSetupDTO;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class ResearchDAO extends BaseDao {
    private Query hQuery;
    @Transactional
    public void save(ResearchEntity researchEntity) {
        saveOrUpdate(researchEntity);
    }

    @Transactional
    public List<ResearchDTO> getResearchList(CurrentUser currentUser) {
        sqlQuery = properties.getProperty("ResearchDAO.getResearchList");
        hQuery = (Query) hibernateQuery(sqlQuery, ResearchDTO.class)
                .setParameter("userName",currentUser.getUserName());
        return hQuery.list();
    }

    @Transactional
    public List<ResearchDTO> geAllResearchList() {
        sqlQuery = properties.getProperty("ResearchDAO.geAllResearchList");
        hQuery = (Query) hibernateQuery(sqlQuery, ResearchDTO.class);
        return hQuery.list();
    }
    @Transactional
    public void saveReviewerComments(Integer researchId, String rComment, Integer statusId) {
        sqlQuery = properties.getProperty("ResearchDAO.saveReviewerComments");
        hQuery = (Query) hibernateQuery(sqlQuery)
                .setParameter("researchId", researchId)
                .setParameter("rComment", rComment)
                .setParameter("statusId",statusId);
        hQuery.executeUpdate();
    }
}