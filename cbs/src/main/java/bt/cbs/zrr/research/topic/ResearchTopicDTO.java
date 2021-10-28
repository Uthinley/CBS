package bt.cbs.zrr.research.topic;

import org.springframework.web.multipart.MultipartFile;

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
    private Integer research_topic_id;
    private String research_month;
    private String research_topic;
    private String status;
    private String remarks;
    private Date createdDate;
    private String createdBy;

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
}
