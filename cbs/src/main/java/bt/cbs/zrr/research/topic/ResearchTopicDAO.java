package bt.cbs.zrr.research.topic;

import bt.cbs.zrr.global.base.BaseDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class ResearchTopicDAO extends BaseDao {

    @Transactional
    public void save(Object obj) {
        saveOrUpdate(obj);
    }

    @Transactional(readOnly = true)
    public List gTopicList(String status, String userName, String research_month) {
        sqlQuery = properties.getProperty("ResearchTopicDAO.gTopicList");
        return hibernateQuery(sqlQuery, ResearchTopicDTO.class)
                .setParameter("userName",userName)
                .setParameter("status",status)
                .setParameter("research_month",research_month).list();
    }

    @Transactional(readOnly = true)
    public ResearchTopicDTO findTopic(String research_month, String userName) {
        sqlQuery = properties.getProperty("ResearchTopicDAO.findTopic");
        List list = hibernateQuery(sqlQuery, ResearchTopicDTO.class)
                .setParameter("userName",userName)
                .setParameter("research_month",research_month).list();
        return list.isEmpty()?null:(ResearchTopicDTO)list.get(0);
    }
}
