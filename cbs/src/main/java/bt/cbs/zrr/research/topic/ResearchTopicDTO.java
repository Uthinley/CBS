package bt.cbs.zrr.research.topic;

import java.util.Date;

public class ResearchTopicDTO {

    /*
    `research_topic_id`,
	`research_month`,
	`research_topic`,
	`status`,
	`remarks`,
	`CREATEDBY`,
	`CREATEDDATE`
    * */

    private String actionType;
    private Integer research_topic_id;
    private String research_month;
    private String objectives;
    private String research_topic;
    private String status;
    private String remarks;
    private Date createdDate;
    private String createdBy;
    private Date approvedDate;
    private String approver;

    private String status_name;
    private String position_title;
    private String employee_id;

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public Integer getResearch_topic_id() {
        return research_topic_id;
    }

    public void setResearch_topic_id(Integer research_topic_id) {
        this.research_topic_id = research_topic_id;
    }

    public String getResearch_month() {
        return research_month;
    }

    public void setResearch_month(String research_month) {
        this.research_month = research_month;
    }

    public String getResearch_topic() {
        return research_topic;
    }

    public void setResearch_topic(String research_topic) {
        this.research_topic = research_topic;
    }

    public String getObjectives() {
        return objectives;
    }

    public void setObjectives(String objectives) {
        this.objectives = objectives;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(Date approvedDate) {
        this.approvedDate = approvedDate;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }

    public String getPosition_title() {
        return position_title;
    }

    public void setPosition_title(String position_title) {
        this.position_title = position_title;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }
}
