package bt.cbs.zrr.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description: <Replace description>
 * Date: 04-May-2016.
 *
 * @author: yonten.choden
 * @version: 1.0.0
 * ======================
 * Change History:
 * Version:
 * Author:
 * Date:
 * Change Description:
 * Search Tag:
 */
@Service("loginService")
public class LoginService {
    //region private dao
    @Autowired
    LoginDao loginDao;
    //endregion

    //region public method

    /**
     * to get user information while logging in.
     *
     * @param username username
     * @return LoginDTO
     */
    public LoginDTO login(String username) {
        return loginDao.login(username);
    }

}
