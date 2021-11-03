ResearchReviewDAO.gResearchList= SELECT a.research_id as researchId, a.research_number, a.research_month, a.research_topic as researchTopic, a.word_count as wordCount, a.file_path as filePath, \
                                        a.research_paper_name, a.status, a.CREATEDBY as createdBy, a.CREATEDDATE as createdDate, c.status_name as statusName, b.assigned_date as assignedDate \
                                 FROM research_dtls a \
                                          INNER JOIN research_reviewer b ON b.research_number = a.research_number \
                                          LEFT JOIN common_status c ON c.status_id = a.status \
                                 WHERE b.reviewer_id = :userId AND \
                                         a.research_month=:rMonth AND \
                                         (:status IS NULL OR a.status = :status)
--                                          a.status= (:status IS NULL OR a.status = :status) \

ResearchReviewDAO.saveReviewerComments = UPDATE `research_dtls` SET reviewer_comment =:rComment, status='R' WHERE research_id = :researchId
ResearchReviewDAO.saveReviewerMarks = INSERT INTO research_reviewer_marks(research_number, assessment_criteria, weightage, marks_allocated, CREATEDBY, CREATEDDATE) \
                                         VALUES(:researchNo,:assessmentCriteria,:weightage,:marksAllocated,:createdBy, :createdDate)