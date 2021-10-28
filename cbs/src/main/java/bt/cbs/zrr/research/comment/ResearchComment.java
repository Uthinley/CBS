package bt.cbs.zrr.research.comment;

import bt.cbs.zrr.global.base.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "research_comment")
public class ResearchComment extends BaseEntity {
    /*
    * research_comment_id',
	'research_number',
	'research_comment',
	'paper_version',
	'research_document_path',
	'research_status',
	'CREATEDBY',
	'CREATEDDATE'*/
    @Id
    @Column(name = "research_comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer research_comment_id;

    @Column(name = "research_number")
    private String research_number;

    @Column(name = "research_comment")
    private String research_comment;

    @Column(name = "paper_version")
    private Integer paper_version;

    @Column(name = "research_document_path")
    private String  research_document_path;

    @Column(name = "research_status")
    private String  research_status;

    public Integer getResearch_comment_id() {
        return research_comment_id;
    }

    public void setResearch_comment_id(Integer research_comment_id) {
        this.research_comment_id = research_comment_id;
    }

    public String getResearch_number() {
        return research_number;
    }

    public void setResearch_number(String research_number) {
        this.research_number = research_number;
    }

    public String getResearch_comment() {
        return research_comment;
    }

    public void setResearch_comment(String research_comment) {
        this.research_comment = research_comment;
    }

    public Integer getPaper_version() {
        return paper_version;
    }

    public void setPaper_version(Integer paper_version) {
        this.paper_version = paper_version;
    }

    public String getResearch_document_path() {
        return research_document_path;
    }

    public void setResearch_document_path(String research_document_path) {
        this.research_document_path = research_document_path;
    }

    public String getResearch_status() {
        return research_status;
    }

    public void setResearch_status(String research_status) {
        this.research_status = research_status;
    }
}
