package bt.cbs.zrr.research.paper;

import bt.cbs.zrr.global.base.BaseDao;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SuppressWarnings("unchecked")
@Repository
public class ResearchDAO extends BaseDao {
    @Transactional
    public void save(Object obj) {
        saveOrUpdate(obj);
    }

    @Transactional(readOnly = true)
    public List<ResearchDTO> getResearchList(String username) {
        sqlQuery = properties.getProperty("ResearchDAO.getResearchList");
        return hibernateQuery(sqlQuery, ResearchDTO.class)
                .setParameter("userName",username).list();
    }

    @Transactional(readOnly = true)
    public ResearchDTO findResearch(String research_number) {
        sqlQuery = properties.getProperty("ResearchDAO.findResearch");
        List result_list = hibernateQuery(sqlQuery, ResearchDTO.class)
                .setParameter("research_number",research_number).list();
        return result_list.isEmpty()?null:(ResearchDTO)result_list.get(0);
    }

    public void delete(String research_number) {
        sqlQuery = properties.getProperty("ResearchDAO.delete");
        hibernateQuery(sqlQuery).setParameter("research_number",research_number).executeUpdate();
    }
}
