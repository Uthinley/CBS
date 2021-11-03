package bt.cbs.zrr.research.paper;

import bt.cbs.zrr.global.base.BaseDao;
import bt.cbs.zrr.global.dto.CurrentUser;
import bt.cbs.zrr.global.dto.GenericDTO;
import bt.cbs.zrr.setup.user.UserSetupDTO;
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
    public List<ResearchDTO> getResearchList(String username) {
        sqlQuery = properties.getProperty("ResearchDAO.getResearchList");
        hQuery = (Query) hibernateQuery(sqlQuery, ResearchDTO.class)
                .setParameter("userName",username);
        return hQuery.list();
    }

    @Transactional
    public List<ResearchDTO> geAllResearchList() {
        sqlQuery = properties.getProperty("ResearchDAO.geAllResearchList");
        hQuery = (Query) hibernateQuery(sqlQuery, ResearchDTO.class);
        return hQuery.list();
    }
    @Transactional
    public void saveReviewerComments(Integer researchId, String rComment, String statusId) {
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
        hQuery = hibernateQuery(sqlQuery, UserSetupDTO.class);
//                .setParameter("status", status);
        return hQuery.list();
    }

    @Transactional(readOnly = true)
    public List<ResearchDTO> getReviewedResearchList(Integer statusCode) {
        sqlQuery = properties.getProperty("ResearchDAO.getReviewedResearchList");
        hQuery = hibernateQuery(sqlQuery, ResearchDTO.class)
                .setParameter("statusCode", statusCode);
        return hQuery.list();
    }

    @Transactional(readOnly = true)
    public GenericDTO getSummaryReport() {
        sqlQuery = properties.getProperty("ResearchDAO.getSummaryReport");
        hQuery = hibernateQuery(sqlQuery, GenericDTO.class);
        return (GenericDTO) hQuery.uniqueResult();
    }
}
