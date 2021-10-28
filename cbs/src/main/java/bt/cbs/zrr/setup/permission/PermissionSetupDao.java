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
@Repository
public class PermissionSetupDao extends BaseDao {

    @Transactional(readOnly = true)
    public BigDecimal getNextPermissionId() {
        sqlQuery = properties.getProperty("PermissionSetupDao.getNextPermissionId");
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery);
        return hQuery.list().isEmpty() ? BigDecimal.ONE : (BigDecimal) hQuery.list().get(0);
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
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery, PermissionListDTO.class);
        hQuery.setParameter("groupId", groupId).setParameter("status", status);
        return hQuery.list();
    }

    @Transactional(readOnly = true)
    public BigDecimal getPermissionId(Integer groupId, String screenId) {
        sqlQuery = properties.getProperty("PermissionSetupDao.getPermissionId");
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery);
        hQuery.setParameter("groupId", groupId);
        hQuery.setParameter("screenId", screenId);
        return hQuery.list().isEmpty() ? null : (BigDecimal) hQuery.list().get(0);
    }
}
