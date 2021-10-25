package com.springapp.mvc.research;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public class ResearchDTO {

    private Integer researchId;
    private String researchTopic;
    private String research_abstract;
    private Integer wordCount;
    private String filePath;
    private String statusName;
    private String status;
    private String research_number;
    private Integer paper_version;
    private String research_paper_name;
    private String supporting_documents_name;

    private MultipartFile research_paper;
    private MultipartFile[] supporting_documents;
    private Date createdDate;
    private String createdBy;

    public Integer getResearchId() {
        return researchId;
    }

    public void setResearchId(Integer researchId) {
        this.researchId = researchId;
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

    public Integer getWordCount() {
        return wordCount;
    }

    public void setWordCount(Integer wordCount) {
        this.wordCount = wordCount;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public MultipartFile getResearch_paper() {
        return research_paper;
    }

    public void setResearch_paper(MultipartFile research_paper) {
        this.research_paper = research_paper;
    }

    public MultipartFile[] getSupporting_documents() {
        return supporting_documents;
    }

    public void setSupporting_documents(MultipartFile[] supporting_documents) {
        this.supporting_documents = supporting_documents;
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
