package bt.cbs.zrr.setup.permission;

import bt.cbs.zrr.global.base.BaseDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * ====================================================================
 * Created by Dechen Wangdi on 20/12/2019.
 * Description:
 * ====================================================================
 * Modified by:
 * Modified date:
 * Purpose:
 * ====================================================================
 */
@SuppressWarnings("unchecked")
@Repository
public class PermissionSetupDao extends BaseDao {

    @Transactional(readOnly = true)
    public BigDecimal getNextPermissionId() {
        sqlQuery = properties.getProperty("PermissionSetupDao.getNextPermissionId");
        List result_list = hibernateQuery(sqlQuery).list();
        return result_list.isEmpty() ? BigDecimal.ONE : (BigDecimal)result_list.get(0);
    }

    @Transactional
    public void save(PermissionSetup permissionSetup) {
        saveOrUpdate(permissionSetup);
    }

    @Transactional
    public void delete(PermissionSetup permissionSetup) {
        permissionSetup = em.find(PermissionSetup.class, permissionSetup.getPermissionId());
        em.remove(permissionSetup);
    }

    @Transactional(readOnly = true)
    public List<PermissionListDTO> getPermissionList(Integer groupId, Integer status) {
        //TODO need to check.
        if (groupId.equals(BigDecimal.ONE)) {//permit all for admin
            sqlQuery = properties.getProperty("PermissionSetupDao.getAllPermission");
        } else {
            sqlQuery = properties.getProperty("PermissionSetupDao.getPermissionList");
        }
        return hibernateQuery(sqlQuery, PermissionListDTO.class).setParameter("groupId", groupId)
                .setParameter("status", status).list();
    }


    @Transactional(readOnly = true)
    public BigDecimal getPermissionId(Integer groupId, String screenId) {
        sqlQuery = properties.getProperty("PermissionSetupDao.getPermissionId");
        List result_list = hibernateQuery(sqlQuery)
                .setParameter("groupId", groupId)
                .setParameter("screenId", screenId).list();
        return result_list.isEmpty() ? null : (BigDecimal) result_list.get(0);
    }
}
