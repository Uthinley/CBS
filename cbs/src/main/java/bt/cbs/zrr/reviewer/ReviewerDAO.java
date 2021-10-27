package bt.cbs.zrr.reviewer;

import bt.cbs.zrr.global.base.BaseDao;
import bt.cbs.zrr.research.ResearchDTO;
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
