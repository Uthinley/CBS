
PermissionSetupDao.getNextPermissionId = SELECT (PERMISSIONID+1) AS nPERMISSIONID FROM sa_user_group_wise_permission ORDER BY PERMISSIONID DESC
PermissionSetupDao.getPermissionList = SELECT PP.PERMISSIONID,PP.GROUP_ID AS groupId,PP.SCREENID,PP.ISVIEW,PP.ISSAVE,PP.ISEDIT,PP.ISDELETE,S.SCR_NAME AS screenName FROM sa_user_group_wise_permission PP INNER JOIN sa_screen S ON PP.SCREENID = S.SCR_ID WHERE GROUP_ID=:groupId AND S.SCR_STATUS=:status
PermissionSetupDao.getAllPermission=select CAST(:groupId AS DECIMAL)groupId,A.SCR_ID,CAST('Y' as char) ISVIEW,CAST('Y' as char)ISSAVE,CAST('Y' as char)ISEDIT ,CAST('Y' as char) ISDELETE ,A.SCR_NAME FROM sa_screen a WHERE A.SCR_STATUS =:status
PermissionSetupDao.getPermissionId = SELECT A.PERMISSIONID FROM sa_user_group_wise_permission A WHERE A.GROUP_ID =:groupId AND A.SCREENID =:screenId


