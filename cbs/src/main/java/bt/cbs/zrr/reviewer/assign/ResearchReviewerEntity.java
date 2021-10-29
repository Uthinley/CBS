package bt.cbs.zrr.reviewer.assign;

import bt.cbs.zrr.global.base.BaseEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "research_reviewer")
public class ResearchReviewerEntity extends BaseEntity {

    @Id
    @Column(name = "research_reviewer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer researchReviewerId;

    @Column(name = "research_number")
    private Integer researchNumber;

    @Column(name = "reviewer_id")
    private Integer reviewerId;

    @Column(name = "assigned_date")
    private Date assignedDate;

    @Column(name = "completion_date")
    private Date completionDate;

    public Integer getResearchReviewerId() {
        return researchReviewerId;
    }

    public void setResearchReviewerId(Integer researchReviewerId) {
        this.researchReviewerId = researchReviewerId;
    }

    public Integer getResearchNumber() {
        return researchNumber;
    }

    public void setResearchNumber(Integer researchNumber) {
        this.researchNumber = researchNumber;
    }

    public Integer getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(Integer reviewerId) {
        this.reviewerId = reviewerId;
    }

    public Date getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(Date assignedDate) {
        this.assignedDate = assignedDate;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }
}
