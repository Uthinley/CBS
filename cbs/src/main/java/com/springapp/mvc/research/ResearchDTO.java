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
    private String research_paper;
    private String supporting_documents;

    private MultipartFile research_paper_file;
    private MultipartFile[] supporting_documents_file;
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

    public String getResearch_paper() {
        return research_paper;
    }

    public void setResearch_paper(String research_paper) {
        this.research_paper = research_paper;
    }

    public String getSupporting_documents() {
        return supporting_documents;
    }

    public void setSupporting_documents(String supporting_documents) {
        this.supporting_documents = supporting_documents;
    }

    public MultipartFile getResearch_paper_file() {
        return research_paper_file;
    }

    public void setResearch_paper_file(MultipartFile research_paper_file) {
        this.research_paper_file = research_paper_file;
    }

    public MultipartFile[] getSupporting_documents_file() {
        return supporting_documents_file;
    }

    public void setSupporting_documents_file(MultipartFile[] supporting_documents_file) {
        this.supporting_documents_file = supporting_documents_file;
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
