package bt.cbs.zrr.reviewer.assign;

//import com.springapp.mvc.global.base.BaseDao;
//import com.springapp.mvc.global.dto.CurrentUser;
//import com.springapp.mvc.global.dto.DropdownDTO;
//import com.springapp.mvc.global.dto.ResponseMessage;
//import com.springapp.mvc.research.ResearchDTO;
import bt.cbs.zrr.global.base.BaseDao;
import bt.cbs.zrr.global.dto.CurrentUser;
import bt.cbs.zrr.global.dto.DropdownDTO;
import bt.cbs.zrr.research.paper.ResearchDTO;
import bt.cbs.zrr.research.paper.ResearchEntity;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public class ReviewerDAO extends BaseDao {
    private Query hQuery;

    @Transactional
    public List<ResearchDTO> getResearchListForReview(String dateFormat) {
        sqlQuery = properties.getProperty("ReviewerDAO.getResearchListForReview");
        hQuery = (Query) hibernateQuery(sqlQuery, ResearchDTO.class)
                .setParameter("dateFormat", dateFormat);
        return hQuery.list();
    }

    @Transactional
    public List<DropdownDTO> getReviewerList() {
        sqlQuery = properties.getProperty("ReviewerDAO.getReviewerList");
        hQuery = (Query) hibernateQuery(sqlQuery, DropdownDTO.class);
        return hQuery.list();
    }

    @Transactional
    public void save(ResearchReviewerEntity researchReviewerEntity) {
        saveOrUpdate(researchReviewerEntity);
    }

    @Transactional
    public void upDateResearchStatus(Integer researchNo) {
        sqlQuery = properties.getProperty("ReviewerDAO.upDateResearchStatus");
        hQuery = (Query) hibernateQuery(sqlQuery)
                .setParameter("researchNo", researchNo);
        hQuery.executeUpdate();
    }

    @Transactional
    public List<ResearchDTO> getAssignedResearchList(String month) {
        sqlQuery = properties.getProperty("ReviewerDAO.getAssignedResearchList");
        hQuery = (Query) hibernateQuery(sqlQuery, ResearchDTO.class)
                .setParameter("dateFormat", month);
        return hQuery.list();
    }

//    @Transactional
//    public void assignReviewer(CurrentUser currentUser, Integer researchNo, Integer reviewerId, Date completionDate) {
//        sqlQuery = properties.getProperty("ReviewerDAO.assignReviewer");
//        hQuery = (Query) hibernateQuery(sqlQuery)
//                .setParameter("researchNo", researchNo)
//                .setParameter("reviewerId", reviewerId)
//                .setParameter("completionDate",completionDate);
//        hQuery.executeUpdate();
//    }
}
