package bt.cbs.zrr.research.topic;

import bt.cbs.zrr.global.base.BaseDao;
import bt.cbs.zrr.global.dto.CurrentUser;
import bt.cbs.zrr.global.dto.GenericDTO;
import bt.cbs.zrr.research.paper.ResearchDTO;
import bt.cbs.zrr.research.paper.ResearchEntity;
import bt.cbs.zrr.setup.user.UserSetupDTO;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class ResearchTopicDAO extends BaseDao {
    private Query hQuery;

    @Transactional
    public void save(Object obj) {
        saveOrUpdate(obj);
    }
}
