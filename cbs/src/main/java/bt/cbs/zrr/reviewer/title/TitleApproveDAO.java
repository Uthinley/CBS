package bt.cbs.zrr.reviewer.title;

import bt.cbs.zrr.global.base.BaseDao;
import bt.cbs.zrr.research.topic.ResearchTopicDTO;
import bt.cbs.zrr.research.topic.ResearchTopicEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class TitleApproveDAO extends BaseDao {

    @Transactional
    public void approve_reject(Object obj) {
        getCurrentSession().update(obj);
    }

    @Transactional(readOnly = true)
    public ResearchTopicEntity load(int id) {
        return getCurrentSession().load(ResearchTopicEntity.class, id);
    }

    @Transactional(readOnly = true)
    public List gTopicList(String status, String userName) {
        sqlQuery = properties.getProperty("ResearchTopicDAO.gTopicList");
        return hibernateQuery(sqlQuery, ResearchTopicDTO.class)
                .setParameter("userName",userName)
                .setParameter("status",status).list();
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
