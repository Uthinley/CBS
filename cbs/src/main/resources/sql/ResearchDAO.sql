ResearchDAO.getResearchList = SELECT research_id AS researchId,research_topic AS researchTopic, word_count AS wordCount, file_path AS filePath, CREATEDDATE AS createdDate, a.status,b.status_name AS statusName,a.research_month,a.research_number, a.reviewer_comment, a.research_paper_name FROM research_dtls a LEFT JOIN common_status b ON a.status = b.status_id where (:userName is null or CREATEDBY =:userName)

ResearchDAO.geAllResearchList = SELECT research_id AS researchId,research_topic AS researchTopic, word_count AS wordCount, file_path AS filePath, CREATEDDATE AS createdDate, CREATEDBY AS createdBy, C.status_name AS statusName FROM research_dtls A INNER JOIN `common_status` C ON C.status_id = A.status
ResearchDAO.saveReviewerComments = UPDATE research_dtls SET status = :statusId, reviewer_comment =:rComment WHERE research_id = :researchId

ResearchDAO.getResearcherList = SELECT A.USRUSERID userId,A.USRUSERNAME userName,A.USRFULLNAME fullName,A.USRUSERSTATUS userStatus,\
                                A.USREMPLOYEEID employeeId,A.USREMAILID emailId,A.USRGROUPID groupId,B.GR_NAME groupName \
                                FROM cbs_db.sa_user A INNER JOIN cbs_db.sa_user_group B ON A.USRGROUPID = B.GR_ID WHERE A.USRUSERSTATUS ='1' AND B.GR_NAME='Reviewer'

ResearchDAO.getReviewedResearchList = SELECT research_id as researchId,research_topic as researchTopic, \
                                word_count as wordCount, file_path as filePath, CREATEDBY as createdBy, CREATEDDATE as createdDate, status  from research_dtls where status = :statusCode

ResearchDAO.getSummaryReport =SELECT COUNT(*) as obj1 FROM cbs_db.sa_user U INNER JOIN `sa_user_group` G ON G.GR_ID=U.USRGROUPID WHERE U.`USRUSERSTATUS`='1' AND G.GR_NAME='Reviewer'
-- SELECT \
--                               (select count(*) from cbs_db.research_dtls) as obj2, \
--                               (select count(*) from cbs_db.research_dtls where status="2") as obj3, \
--                               (((select count(*) from cbs_db.research_dtls where status="2")/ \
--                                 ((select count(*) from cbs_db.research_dtls where status="1") + \
--                                  (select count(*) from cbs_db.research_dtls where status="2")))*100) as obj4