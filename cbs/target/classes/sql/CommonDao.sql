CommonDao.getScreenList = SELECT SCR_ID value, SCR_NAME text FROM sa_screen ORDER BY SCR_ID ASC
CommonDao.getScreenByTypeList = SELECT SCR_ID value, SCR_NAME text FROM sa_screen WHERE (:screenType IS NULL OR SCR_TYPE=:screenType) ORDER BY SCR_ID ASC
CommonDao.getUserGroupList = SELECT A.GR_ID AS value, A.GR_NAME AS text FROM sa_user_group A
CommonDao.getReportDisplayOption = SELECT A.RP_ID AS reportNo,A.RP_NAME AS reportName FROM sa_report_display_option A WHERE A.RP_ID=:reportNo
CommonDao.getBranchCodeList = SELECT A.BR_CODE value, A.BR_NAME text FROM sa_branch_info A WHERE A.BR_STATUS=:status ORDER BY A.BR_CODE ASC;

CommonDao.getReportList = SELECT A.RP_ID AS VALUE ,A.RP_NAME AS TEXT FROM  sa_report_display_option A ORDER BY A.RP_ID  ASC

CommonDao.getBankList = SELECT A.bank_id value, A.bank_name text,bank_code text2 FROM common_bank A WHERE A.isactive =:status
CommonDao.getTSWCFLoans = SELECT a.tswcf_id value, a.name text FROM dfrs_tswcf_type a WHERE a.isactive =:status




