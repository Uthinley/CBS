package bt.cbs.zrr.global.common;


import bt.cbs.zrr.global.base.BaseDao;
import bt.cbs.zrr.global.dto.DropdownDTO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
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
        return hibernateQuery(sqlQuery, DropdownDTO.class).list();
    }

    @Transactional(readOnly = true)
    public List<DropdownDTO> getScreenByTypeList(Character screenType) {
        sqlQuery = properties.getProperty("CommonDao.getScreenByTypeList");
        return (List<DropdownDTO>)hibernateQuery(sqlQuery, DropdownDTO.class)
                .setParameter("screenType",screenType).list();
    }

    @Transactional(readOnly = true)
    public List<DropdownDTO> getUserGroupList() {
        sqlQuery = properties.getProperty("CommonDao.getUserGroupList");
        return (List<DropdownDTO>)hibernateQuery(sqlQuery, DropdownDTO.class).list();
    }

    @Transactional(readOnly = true)
    public List<DropdownDTO> getResearchMonthList() {
        sqlQuery = properties.getProperty("CommonDao.getResearchMonthList");
        return (List<DropdownDTO>)hibernateQuery(sqlQuery, DropdownDTO.class).list();
    }

    /**
     * To get list of branch
     *
     * @param status Integer
     * @return List<DropdownDTO>
     */
    @Transactional(readOnly = true)
    public List<DropdownDTO> getBranchCodeList(Integer status) {
        sqlQuery = properties.getProperty("CommonDao.getBranchCodeList");
        return  (List<DropdownDTO>)hibernateQuery(sqlQuery, DropdownDTO.class)
                .setParameter("status", status).list();
    }


    @Transactional(readOnly = true)
    public List<DropdownDTO> getReportList(Integer status) {
        sqlQuery = properties.getProperty("CommonDao.getReportList");
        return (List<DropdownDTO>)hibernateQuery(sqlQuery, DropdownDTO.class).list();
    }


    @Transactional(readOnly = true)
    public List<DropdownDTO> getBankList(Integer status) {
        sqlQuery = properties.getProperty("CommonDao.getBankList");
        return (List<DropdownDTO>) hibernateQuery(sqlQuery, DropdownDTO.class)
                .setParameter("status", status).list();
    }

    @Transactional(readOnly = true)
    public Object getNextID(String tblName, String colName) {
        sqlQuery = "select ("+colName+")+1 from "+tblName+" order by "+colName + " desc";
        List list = hibernateQuery(sqlQuery).list();
        return list.isEmpty()?null:list.get(0);
    }

    @Transactional(readOnly = true)
    public Object getValue(String tblName, String colName, String filterCol, Object filterVal) {
        sqlQuery = "select "+colName+" from "+tblName+" where "+filterCol+ "=:filterVal";
        List list = hibernateQuery(sqlQuery).setParameter("filterVal",filterVal).list();
        return list.isEmpty()?null:list.get(0);
    }

    @Transactional
    public void saveUpdate(Object obj) {
        saveOrUpdate(obj);
    }

}
