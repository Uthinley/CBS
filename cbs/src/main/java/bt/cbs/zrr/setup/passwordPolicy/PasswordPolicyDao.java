package bt.cbs.zrr.setup.passwordPolicy;

import bt.cbs.zrr.global.base.BaseDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by sangay Tenzin on 12/20/2019.
 */

@Repository
public class PasswordPolicyDao extends BaseDao {

    @Transactional(rollbackFor = Exception.class)
    public void save(PasswordPolicy passwordPolicy) {
        saveOrUpdate(passwordPolicy);
    }

    @Transactional(readOnly = true)
    public PasswordPolicyDTO getPasswordPolicyInfo() {
        String sqlQuery = properties.getProperty("PasswordPolicyDao.getPasswordPolicyInfo");
        List result_list = hibernateQuery(sqlQuery, PasswordPolicyDTO.class).list();
        return result_list.isEmpty() ? null : (PasswordPolicyDTO) result_list.get(0);
    }
}

