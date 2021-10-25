package com.springapp.mvc.research.comment;

import com.springapp.mvc.global.base.BaseDao;
import com.springapp.mvc.global.dto.CurrentUser;
import com.springapp.mvc.research.GenericDTO;
import com.springapp.mvc.research.ResearchDTO;
import com.springapp.mvc.research.ResearchEntity;
import com.springapp.mvc.setup.user.UserSetupDTO;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class ResearchCommentDAO extends BaseDao {
    private Query hQuery;
    @Transactional
    public void save(Object obj) {
        saveOrUpdate(obj);
    }

    /*@Transactional
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

    @Transactional(readOnly = true)
    public List<UserSetupDTO> getResearcherList(Integer status) {
        sqlQuery = properties.getProperty("ResearchDAO.getResearcherList");
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery, UserSetupDTO.class);
//                .setParameter("status", status);
        return hQuery.list();
    }

    @Transactional(readOnly = true)
    public List<ResearchDTO> getReviewedResearchList(Integer statusCode) {
        sqlQuery = properties.getProperty("ResearchDAO.getReviewedResearchList");
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery, ResearchDTO.class)
                .setParameter("statusCode", statusCode);
        return hQuery.list();
    }

    @Transactional(readOnly = true)
    public GenericDTO getSummaryReport() {
        sqlQuery = properties.getProperty("ResearchDAO.getSummaryReport");
        hQuery = (Query) hibernateQuery(sqlQuery, GenericDTO.class);
        return (GenericDTO) hQuery.uniqueResult();
    }*/
}
