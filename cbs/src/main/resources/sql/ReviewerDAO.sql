ReviewerDAO.getResearchListForReview = SELECT \
                                  research_id AS researchId, \
                                  `research_number` AS research_number, \
                                  `word_count` AS wordCount, \
                                  `research_topic` AS researchTopic, \
                                  `paper_version` AS paper_version, \
                                  `research_abstract` AS research_abstract, \
                                  `file_path` AS filePath, \
                                  `status`, \
                                  `CREATEDBY` AS createdBy, \
                                  `CREATEDDATE` AS createdDate \
                              FROM `research_dtls` WHERE research_month = :dateFormat AND STATUS='S'
--                               MONTH(CREATEDDATE)=DATE_FORMAT(:dateFormat, '%m') AND YEAR(CREATEDDATE)= DATE_FORMAT(:dateFormat, '%Y') AND status='S'

ReviewerDAO.getReviewerList = SELECT CONCAT(A.USRFULLNAME, "(",A.USREMAILID, ")" ) AS TEXT, A.USRUSERID AS VALUE FROM `sa_user` A \
                              INNER JOIN sa_user_group B \
                              ON A.USRGROUPID = B.GR_ID \
                              WHERE A.USRUSERSTATUS='1' AND B.GR_NAME="REVIEWER" AND B.GR_STATUS='1' \

ReviewerDAO.upDateResearchStatus = UPDATE `research_dtls` SET STATUS= 'U' WHERE research_number =:researchNo

ReviewerDAO.getAssignedResearchList = SELECT \
                                          A.research_id AS researchId,\
                                          A.`research_number` AS research_number,\
                                          A.`word_count` AS wordCount,\
                                          A.`research_topic` AS researchTopic,\
                                          A.`paper_version` AS paper_version,\
                                          A.`research_abstract` AS research_abstract,\
                                          A.`file_path` AS filePath,\
                                          A.`status`,\
                                          A.`CREATEDBY` AS createdBy,\
                                          A.`CREATEDDATE` AS createdDate,\
                                          B.`assigned_date` AS assignedDate,\
                                          D.status_name AS statusName, \
                                          CONCAT(C.`USRFULLNAME`,"(", C.`USREMAILID`, ")") AS reviewerName \
                                      FROM `research_dtls` A \
                                           INNER JOIN `research_reviewer` B \
                                                      ON A.research_number = B.research_number \
                                           INNER JOIN `sa_user` C \
                                                      ON B.`reviewer_id` = C.`USRUSERID` \
                                           INNER JOIN `common_status` D \
                                                      ON D.`status_id` = A.status \
                                      WHERE A.research_month = :dateFormat
--                                       MONTH(A.CREATEDDATE)=DATE_FORMAT(:dateFormat, '%m') \
--                                       AND YEAR(A.CREATEDDATE)= DATE_FORMAT(:dateFormat, '%Y')