package com.springapp.mvc.setup.cPassword;

import com.springapp.mvc.global.base.BaseDao;
import com.springapp.mvc.setup.user.UserSetup;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * ====================================================================
 * Created by Dechen Wangdi
 * Description:
 * SQL Query in common.ora.sql
 * ====================================================================
 * Modified by:
 * Modified date:
 * Purpose:
 * ====================================================================
 */
@Repository
public class ChangePasswordDao extends BaseDao {

    @Transactional(readOnly = false)
    public void changePassword(String userName, String password) {
        sqlQuery = properties.getProperty("ChangePasswordDao.changePassword");
        Query hQuery = hibernateQuery(sqlQuery)
                .setParameter("userName", userName)
                .setParameter("password", password);
        hQuery.executeUpdate();
    }

    @Transactional(readOnly = false)
    public String getPassword(String userName) {
        UserSetup userSetup = em.find(UserSetup.class, userName);
        return ((userSetup == null) ? null : userSetup.getPassword());
    }
}
