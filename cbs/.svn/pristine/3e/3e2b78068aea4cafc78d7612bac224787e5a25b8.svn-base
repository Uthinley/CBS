package com.springapp.mvc.setup.userLog;

import com.springapp.mvc.global.base.BaseDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by Dechen Wangdi on 2/8/2020.
 */

@Repository
public class UserLogDao extends BaseDao {

    @Transactional(rollbackFor = Exception.class)
    public void save(UserLog userLog) {
        em.persist(userLog);
    }

    @Transactional(readOnly = true)
    public Integer getLogID(String userName) {
        sqlQuery = properties.getProperty("LoginDao.getLogID");
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery)
                .setParameter("userName", userName);
        return (Integer)hQuery.uniqueResult();
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(String userName, Integer logID, String now) {
        sqlQuery = properties.getProperty("LoginDao.update");
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery)
                .setParameter("userName", userName)
                .setParameter("logID", logID)
                .setParameter("now", now);
        hQuery.executeUpdate();
    }
}
