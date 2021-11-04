package bt.cbs.zrr.setup.userLog;

import bt.cbs.zrr.global.base.BaseDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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
        List result_list = hibernateQuery(sqlQuery)
                .setParameter("userName", userName).list();
        return result_list.isEmpty()?null:(Integer)result_list.get(0);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(String userName, Integer logID, String now) {
        sqlQuery = properties.getProperty("LoginDao.update");
        hibernateQuery(sqlQuery)
                .setParameter("userName", userName)
                .setParameter("logID", logID)
                .setParameter("now", now).executeUpdate();
    }
}
