ResearchDAO.getResearchList = SELECT research_id AS researchId,research_topic AS researchTopic, word_count AS wordCount, file_path AS filePath, CREATEDDATE AS createdDate, a.status,b.status_name AS statusName,a.research_month,a.research_number, a.reviewer_comment FROM research_dtls a LEFT JOIN common_status b ON a.status = b.status_id where CREATEDBY =:userName

ResearchDAO.geAllResearchList = SELECT research_id AS researchId,research_topic AS researchTopic, word_count AS wordCount, file_path AS filePath, \
                                       CREATEDDATE AS createdDate, CREATEDBY AS createdBy, C.status_name AS statusName \
                                FROM research_dtls A \
                                         INNER JOIN `common_status` C \
                                                    ON C.status_id = A.status
--                             SELECT research_id as researchId,research_topic as researchTopic, word_count as wordCount, file_path as filePath, CREATEDDATE as createdDate, CREATEDBY as createdBy, CASE WHEN (status=1) THEN 'Submitted' WHEN (status=2) THEN 'Reviewed' WHEN (status=3) THEN 'Returned' WHEN (status=4) THEN 'Approved' ELSE 'NA' END as status  from research_dtls
ResearchDAO.saveReviewerComments = UPDATE research_dtls SET status = :statusId, reviewer_comment =:rComment WHERE research_id = :researchId

ResearchDAO.getResearcherList = SELECT A.USRUSERID userId,A.USRUSERNAME userName,A.USRFULLNAME fullName,A.USRUSERSTATUS userStatus,\
                                A.USREMPLOYEEID employeeId,A.USREMAILID emailId,A.USRGROUPID groupId,B.GR_NAME groupName \
                                FROM cbs_db.sa_user A INNER JOIN cbs_db.sa_user_group B ON A.USRGROUPID = B.GR_ID

ResearchDAO.getReviewedResearchList = SELECT research_id as researchId,research_topic as researchTopic, \
                                word_count as wordCount, file_path as filePath, CREATEDBY as createdBy, CREATEDDATE as createdDate, status  from research_dtls where status = :statusCode

ResearchDAO.getSummaryReport = SELECT \
                              (Select count(*) from cbs_db.sa_user) as obj1, \
                              (select count(*) from cbs_db.research_dtls) as obj2, \
                              (select count(*) from cbs_db.research_dtls where status="2") as obj3, \
                              (((select count(*) from cbs_db.research_dtls where status="2")/ \
                                ((select count(*) from cbs_db.research_dtls where status="1") + \
                                 (select count(*) from cbs_db.research_dtls where status="2")))*100) as obj4