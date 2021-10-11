FinancialInfoDao.getLoanInfo =SELECT A.LOAN_ACC_NO AS accountNo, A.LOAN_SECTOR AS sector, A.SANCTIONED_DATE AS sanctionDate, A.PRINCIPAL_OS_30_04_2020 AS sanctionAmt, A.APRIL_INTEREST_WAIVER   AS outstandingAmt, A.INSTITUTIONS AS finInstitution FROM tbl_loan_data A WHERE A.CID_TPN_LIC =:searchKey

FinancialInfoDao.getDepositInfo = SELECT A.CID cid, A.DEPOSIT_TYPE depositType, A.BALANCE amount, A.FINANCIAL_INSTITUTION finInstitution FROM tbl_deposit_data A WHERE A.CID=:searchKey

FinancialInfoDao.getWorkingCapitalInfo = CALL USP_GET_WORKING_CAPITAL_INFO(:searchKey)
FinancialInfoDao.getLandInfo =  CALL USP_GET_LAND_INFO(:searchKey)
FinancialInfoDao.getBuildingInfo = CALL USP_GET_BUILDING_INFO(:searchKey)
FinancialInfoDao.getVehicleInfo = CALL USP_GET_VEHICLE_INFO(:searchKey)
FinancialInfoDao.getBusinessInfo =  CALL USP_GET_BUSINESS_INFO(:searchKey)

FinancialInfoDao.getDepositByBank = SELECT A.finInstitution, (A.amount/A.total)*100 amount FROM temp_amt_tbl A;