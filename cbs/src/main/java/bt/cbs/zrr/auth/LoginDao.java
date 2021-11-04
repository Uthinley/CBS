package bt.cbs.zrr.auth;


import bt.cbs.zrr.global.base.BaseDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository("loginDao")
public class LoginDao extends BaseDao {

    //region public method

    /**
     * to get the user information while logging in.
     *
     * @param username username
     * @return UserSetupDTO
     */
    @Transactional(readOnly = true)
    public LoginDTO login(String username) {
        sqlQuery = properties.getProperty("LoginDao.login");
        List result_list = hibernateQuery(sqlQuery, LoginDTO.class)
                .setParameter("username", username)
                .list();
        return (LoginDTO) (result_list.isEmpty() ? null : result_list.get(0));
    }
    //endregion
}
