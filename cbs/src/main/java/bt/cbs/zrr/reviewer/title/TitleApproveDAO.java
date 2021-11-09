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

}
