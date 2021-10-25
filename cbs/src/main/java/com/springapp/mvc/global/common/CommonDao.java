package com.springapp.mvc.global.common;


import com.springapp.mvc.global.base.BaseDao;
import com.springapp.mvc.global.dto.DropdownDTO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * ====================================================================
 * Created by Dechen Wangdi on 05/12/2019.
 * Description:
 * ====================================================================
 * Modified by:
 * Modified date:
 * Purpose:
 * ====================================================================
 */
@SuppressWarnings("unchecked")
@Repository
public class CommonDao extends BaseDao {

    @Transactional(readOnly = true)
    public List<DropdownDTO> getScreenList() {
        sqlQuery = properties.getProperty("CommonDao.getScreenList");
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery, DropdownDTO.class);
        return hQuery.list();
    }

    @Transactional(readOnly = true)
    public List<DropdownDTO> getScreenByTypeList(Character screenType) {
        sqlQuery = properties.getProperty("CommonDao.getScreenByTypeList");
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery, DropdownDTO.class).setParameter("screenType",screenType);
        return hQuery.list();
    }

    @Transactional(readOnly = true)
    public List<DropdownDTO> getUserGroupList() {
        sqlQuery = properties.getProperty("CommonDao.getUserGroupList");
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery, DropdownDTO.class);
        return hQuery.list();
    }
    @Transactional(readOnly = true)
    public List<DropdownDTO> getResearchMonthList() {
        sqlQuery = properties.getProperty("CommonDao.getResearchMonthList");
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery, DropdownDTO.class);
        return hQuery.list();
    }

    /**
     * To get list of purpose for ngultrum exchange.
     *
     * @param status Integer
     * @return List<DropdownDTO>
     */
    @Transactional(readOnly = true)
    public List<DropdownDTO> getBranchCodeList(Integer status) {
        sqlQuery = properties.getProperty("CommonDao.getBranchCodeList");
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery, DropdownDTO.class)
                .setParameter("status", status);
        return (List<DropdownDTO>) hQuery.list();
    }


    @Transactional(readOnly = true)
    public List<DropdownDTO> getReportList(Integer status) {
        sqlQuery = properties.getProperty("CommonDao.getReportList");
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery, DropdownDTO.class);
        return (List<DropdownDTO>) hQuery.list();
    }


    @Transactional(readOnly = true)
    public List<DropdownDTO> getBankList(Integer status) {
        sqlQuery = properties.getProperty("CommonDao.getBankList");
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery, DropdownDTO.class)
                .setParameter("status", status);
        return (List<DropdownDTO>) hQuery.list();
    }

    @Transactional(readOnly = true)
    public Object getNextID(String tblName, String colName) {
        sqlQuery = "select ("+colName+")+1 from "+tblName+" order by "+colName + " desc";
        List list = hibernateQuery(sqlQuery).list();
        return list.isEmpty()?BigInteger.ONE:list.get(0);
    }

    @Transactional(readOnly = true)
    public Object getValue(String tblName, String colName, String filterCol, Object filterVal) {
        sqlQuery = "select "+colName+" from "+tblName+" where "+filterCol+ "=:filterVal";
        List list = hibernateQuery(sqlQuery).setParameter("filterVal",filterVal).list();
        return list.isEmpty()?null:list.get(0);
    }

    public void saveUpdate(FileDetailEntity fileDetail) {
        saveOrUpdate(fileDetail);
    }

    public List<DropdownDTO> getTSWCFLoans(Integer value) {
        sqlQuery = properties.getProperty("CommonDao.getTSWCFLoans");
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery, DropdownDTO.class)
                .setParameter("status", value);
        return (List<DropdownDTO>) hQuery.list();
    }
}
