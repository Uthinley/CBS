ResearchReviewDAO.gResearchList= SELECT a.research_id, a.research_number, a.research_month, a.research_topic, a.word_count, a.file_path, \
                                        a.research_paper_name, a.status, a.CREATEDBY, a.CREATEDDATE, c.status_name , b.assigned_date, b.completion_date \
                                 FROM research_dtls a \
                                          INNER JOIN research_reviewer b ON b.research_number = a.research_number \
                                          LEFT JOIN common_status c ON c.status_id = a.status \
                                 WHERE b.reviewer_id = :userId AND \
                                         a.research_month=:rMonth AND \
                                         a.status= (:status IS NULL OR a.status = :status) \