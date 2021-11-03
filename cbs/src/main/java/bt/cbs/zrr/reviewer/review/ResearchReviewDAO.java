package bt.cbs.zrr.reviewer.review;

import bt.cbs.zrr.global.base.BaseDao;
import bt.cbs.zrr.global.dto.CurrentUser;
import bt.cbs.zrr.research.paper.ResearchDTO;
import bt.cbs.zrr.research.topic.ResearchTopicDTO;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Currency;
import java.util.Date;
import java.util.List;

@Repository
public class ResearchReviewDAO extends BaseDao {

    private Query hQuery;

    @Transactional(readOnly = true)
    public List gResearchList(String status, Integer userId, String rMonth) {
        sqlQuery = properties.getProperty("ResearchReviewDAO.gResearchList");
        return hibernateQuery(sqlQuery, ResearchDTO.class)
                .setParameter("userId",userId)
                .setParameter("rMonth",rMonth)
                .setParameter("status",status).list();
    }

    @Transactional()
    public void saveReviewerComments(String rComment, Integer researchId, CurrentUser currentUser) {
        sqlQuery = properties.getProperty("ResearchReviewDAO.saveReviewerComments");
        hQuery = (Query) hibernateQuery(sqlQuery)
                .setParameter("researchId", researchId)
                .setParameter("rComment", rComment);
        hQuery.executeUpdate();
    }

    @Transactional()
    public void saveReviewerMarks(Integer researchNo, String assessmentCriteria, String weightage, String marksAllocated, CurrentUser currentUser) {
       try{
        sqlQuery = properties.getProperty("ResearchReviewDAO.saveReviewerMarks");
        hQuery = (Query) hibernateQuery(sqlQuery)
                .setParameter("researchNo", researchNo)
                .setParameter("assessmentCriteria", assessmentCriteria)
//                .setParameter("weightage", Integer.valueOf(weightage))
                .setParameter("marksAllocated",  Integer.parseInt(marksAllocated))
                .setParameter("weightage",Integer.parseInt( weightage))

                .setParameter("createdBy", currentUser.getUserName())
                .setParameter("createdDate", new Date());
        hQuery.executeUpdate();
       }catch (NumberFormatException ex){

       }
    }
}
