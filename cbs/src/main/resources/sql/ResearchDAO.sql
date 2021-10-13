ResearchDAO.getResearchList = SELECT research_id as researchId,research_topic as researchTopic, word_count as wordCount, file_path as filePath, CREATEDDATE as createdDate, status  from research_dtls where CREATEDBY =:userName

ResearchDAO.geAllResearchList = SELECT research_id as researchId,research_topic as researchTopic, word_count as wordCount, file_path as filePath, CREATEDDATE as createdDate, CREATEDBY as createdBy, status  from research_dtls
ResearchDAO.saveReviewerComments = UPDATE research_dtls SET status = :statusId, reviewer_comment =:rComment WHERE research_id = :researchId