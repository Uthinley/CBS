package bt.cbs.zrr.reviewer.review;

import bt.cbs.zrr.global.base.BaseDao;
import bt.cbs.zrr.research.topic.ResearchTopicDTO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public class ResearchReviewDAO extends BaseDao {

    @Transactional(readOnly = true)
    public List gResearchList(String status, Integer userId, String rMonth) {
        sqlQuery = properties.getProperty("ResearchReviewDAO.gResearchList");
        return hibernateQuery(sqlQuery, ResearchTopicDTO.class)
                .setParameter("userId",userId)
                .setParameter("rMonth",rMonth)
                .setParameter("status",status).list();
    }
}
