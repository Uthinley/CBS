GroupSetupDao.getGroupList = SELECT g.GR_ID as groupID, g.GR_NAME as groupName,g.GR_STATUS AS groupStatus,CASE WHEN (g.GR_STATUS=1) THEN 'Active' ELSE 'Inactive' END AS groupStatusName FROM sa_user_group g

GroupSetupDao.isGroupExist = SELECT COUNT(*) FROM sa_user_group A WHERE A.GR_NAME=:groupName
GroupSetupDao.delete = DELETE FROM sa_user_group WHERE GR_ID=:groupId