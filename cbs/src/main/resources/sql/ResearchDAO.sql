ResearchDAO.getResearchList = SELECT a.research_id AS researchId,a.research_topic AS researchTopic, a.word_count AS wordCount, a.file_path AS filePath, a.CREATEDDATE AS createdDate, a.status,b.status_name AS statusName,a.research_month,a.research_number, a.reviewer_comment, a.research_paper_name,U.USRFULLNAME AS createdBy FROM research_dtls a INNER JOIN sa_user U ON U.USRUSERNAME = A.CREATEDBY LEFT JOIN common_status b ON a.status = b.status_id where (:userName is null or A.CREATEDBY =:userName) order by A.research_number desc
ResearchDAO.findResearch = SELECT a.research_id AS researchId,a.research_topic AS researchTopic, a.word_count AS wordCount, a.file_path AS filePath, a.CREATEDDATE AS createdDate, a.status,b.status_name AS statusName,a.research_month,a.research_number, a.reviewer_comment, a.research_paper_name,U.USRFULLNAME AS createdBy,a.key_words,a.research_abstract FROM research_dtls a INNER JOIN sa_user U ON U.USRUSERNAME = A.CREATEDBY LEFT JOIN common_status b ON a.status = b.status_id where  A.research_number =:research_number
ResearchDAO.delete = DELETE FROM research_dtls a WHERE a.research_number = :research_number