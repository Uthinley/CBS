ReviewerDAO.getResearchListForReview = SELECT \
                                  research_id AS researchId, \
                                  `research_number` AS research_number, \
                                  `word_count` AS wordCount, \
                                  `research_topic` AS researchTopic, \
                                  `paper_version` AS paper_version, \
                                  `research_abstract` AS research_description, \
                                  `file_path` AS filePath, \
                                  `status`, \
                                  `CREATEDBY` AS createdBy, \
                                  `CREATEDDATE` AS createdDate \
                              FROM `research_dtls` WHERE MONTH(CREATEDDATE)=DATE_FORMAT(:dateFormat, '%m') AND YEAR(CREATEDDATE)= DATE_FORMAT(:dateFormat, '%Y') AND status='1'

ReviewerDAO.getReviewerList = SELECT CONCAT(A.USRFULLNAME, "(",A.USREMAILID, ")" ) AS TEXT, A.USRUSERID AS VALUE FROM `sa_user` A \
                              INNER JOIN sa_user_group B \
                              ON A.USRGROUPID = B.GR_ID \
                              WHERE A.USRUSERSTATUS='1' AND B.GR_NAME="REVIEWER" AND B.GR_STATUS='1' \