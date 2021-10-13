package com.springapp.mvc.research;

import com.springapp.mvc.global.base.BaseEntity;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "research_dtls")
public class ResearchEntity extends BaseEntity {

    @Id
    @Column(name = "research_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer researchId;

    @Column(name = "word_count")
    private Long wordCount;

    @Column(name = "research_topic")
    private String researchTopic;

    @Column(name = "file_path")
    private String filepath;

    @Column(name = "status")
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

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

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getResearchTopic() {
        return researchTopic;
    }

    public void setResearchTopic(String researchTopic) {
        this.researchTopic = researchTopic;
    }
}
