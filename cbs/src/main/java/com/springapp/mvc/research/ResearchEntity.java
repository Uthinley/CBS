package com.springapp.mvc.research;

import com.springapp.mvc.global.base.BaseEntity;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "research_dtls")
public class ResearchEntity extends BaseEntity {

    /*
    * INSERT INTO `cbs_db`.`research_dtls`
	(`research_id`,
	`research_number`,
	`word_count`,
	`research_topic`,
	`paper_version`,
	`key_words`,
	`research_abstract`,
	`file_path`,
	`research_paper_name`,
	`supporting_documents_name`,
	`reviewer_comment`,
	`status`,
	`CREATEDBY`,
	`CREATEDDATE`
	)*/

    @Id
    @Column(name = "research_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer researchId;

    @Column(name = "word_count")
    private Long wordCount;

    @Column(name = "research_topic")
    private String researchTopic;

    @Column(name = "research_abstract")
    private String research_abstract;

    @Column(name = "research_number")
    private String research_number;

    @Column(name = "paper_version")
    private Integer paper_version;

    @Column(name = "file_path")
    private String filepath;

    @Column(name = "research_paper_name")
    private String research_paper_name;

    //documents name in comma separated
    @Column(name = "supporting_documents_name")
    private String supporting_documents_name;

    @Column(name = "status")
    private Integer status;

    public Integer getResearchId() {
        return researchId;
    }

    public void setResearchId(Integer researchId) {
        this.researchId = researchId;
    }

    public Long getWordCount() {
        return wordCount;
    }

    public void setWordCount(Long wordCount) {
        this.wordCount = wordCount;
    }

    public String getResearchTopic() {
        return researchTopic;
    }

    public void setResearchTopic(String researchTopic) {
        this.researchTopic = researchTopic;
    }

    public String getResearch_abstract() {
        return research_abstract;
    }

    public void setResearch_abstract(String research_abstract) {
        this.research_abstract = research_abstract;
    }

    public String getResearch_number() {
        return research_number;
    }

    public void setResearch_number(String research_number) {
        this.research_number = research_number;
    }

    public Integer getPaper_version() {
        return paper_version;
    }

    public void setPaper_version(Integer paper_version) {
        this.paper_version = paper_version;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getResearch_paper_name() {
        return research_paper_name;
    }

    public void setResearch_paper_name(String research_paper_name) {
        this.research_paper_name = research_paper_name;
    }

    public String getSupporting_documents_name() {
        return supporting_documents_name;
    }

    public void setSupporting_documents_name(String supporting_documents_name) {
        this.supporting_documents_name = supporting_documents_name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
