package bt.cbs.zrr.research.paper;

import bt.cbs.zrr.global.base.BaseEntity;

import javax.persistence.*;

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

    @Column(name = "research_month")
    private String research_month;

    @Column(name = "research_number")
    private String research_number;

    @Column(name = "research_topic")
    private String researchTopic;

    @Column(name = "word_count")
    private Long wordCount;

    @Column(name = "key_words")
    private String key_words;

    @Column(name = "research_abstract")
    private String research_abstract;


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
    private String status;

    public Integer getResearchId() {
        return researchId;
    }

    public void setResearchId(Integer researchId) {
        this.researchId = researchId;
    }

    public String getResearch_month() {
        return research_month;
    }

    public void setResearch_month(String research_month) {
        this.research_month = research_month;
    }

    public String getResearch_number() {
        return research_number;
    }

    public void setResearch_number(String research_number) {
        this.research_number = research_number;
    }

    public String getResearchTopic() {
        return researchTopic;
    }

    public void setResearchTopic(String researchTopic) {
        this.researchTopic = researchTopic;
    }

    public Long getWordCount() {
        return wordCount;
    }

    public void setWordCount(Long wordCount) {
        this.wordCount = wordCount;
    }

    public String getKey_words() {
        return key_words;
    }

    public void setKey_words(String key_words) {
        this.key_words = key_words;
    }

    public String getResearch_abstract() {
        return research_abstract;
    }

    public void setResearch_abstract(String research_abstract) {
        this.research_abstract = research_abstract;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
