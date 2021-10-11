LoginDao.login = SELECT A.USRUSERNAME AS userName, A.USRPASSWORD AS password, A.USRFULLNAME AS userFullName,B.BR_NAME AS branchName, A.USRPASSEXPIRY AS validityDate,  \
                 A.USRUSERSTATUS AS userStatus,A.USRGROUPID AS groupId, A.USRAGENCYCODE AS branchCode, G.GR_STATUS AS groupStatus, A.USRBADCREDENTIAL AS passwordChangeYN \
                 FROM sa_user  A \
                 INNER JOIN sa_branch_info B ON A.USRAGENCYCODE = B.BR_CODE \
                 INNER JOIN sa_user_group G ON A.USRGROUPID = G.GR_ID \
                 WHERE A.USRUSERNAME = :username

LoginDao.getLogID = SELECT a.LOG_ID FROM sa_user_log a WHERE a.USER_NAME=:userName ORDER BY USER_LOGIN DESC LIMIT 1
LoginDao.update = UPDATE sa_user_log SET USER_LOGOUT=:now WHERE USER_NAME =:userName AND LOG_ID=:logID


