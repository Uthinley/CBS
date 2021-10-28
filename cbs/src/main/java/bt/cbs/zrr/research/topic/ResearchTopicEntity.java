package bt.cbs.zrr.research.topic;

import bt.cbs.zrr.global.base.BaseEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "research_dtls")
public class ResearchTopicEntity extends BaseEntity {

    /*
    `research_topic_id`,
	`research_month`,
	`research_topic`,
	`status`,
	`remarks`,
	`CREATEDBY`,
	`CREATEDDATE`
    * */
    @Id
    @Column(name = "research_topic_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer research_topic_id;
    @Column(name = "research_month")
    private String research_month;
    @Column(name = "research_topic")
    private String research_topic;

    @Column(name = "status")
    private String status;

    @Column(name = "remarks")
    private String remarks;

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

}
