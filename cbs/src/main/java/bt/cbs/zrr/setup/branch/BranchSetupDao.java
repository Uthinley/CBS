package bt.cbs.zrr.setup.branch;

import bt.cbs.zrr.global.base.BaseDao;
import bt.cbs.zrr.setup.group.GroupSetup_A;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Sonam Dargay on 1/3/2020.
 */
@Repository
public class BranchSetupDao extends BaseDao {
    @Transactional(rollbackFor = Exception.class)
    public void save(BranchSetup branchSetup) {
//        saveOrUpdate(branchSetup);
        if (!StringUtils.isEmpty(branchSetup)) {
            saveOrUpdate(branchSetup);
        }
    }

    public void save(BranchSetup branchSetup, GroupSetup_A groupSetup_a) {
        if (!StringUtils.isEmpty(branchSetup)) {
            saveOrUpdate(branchSetup);
        }
    }

    @Transactional(readOnly = true)
    public BranchSetup getBranchInfoById(int branchId) {
        return em.find(BranchSetup.class, branchId);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(int branchId) {
        sqlQuery = properties.getProperty("BranchSetupDao.delete");
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery).setParameter("branchId", branchId);
        hQuery.executeUpdate();
    }

    @Transactional(readOnly = true)
    public List<BranchSetupDTO> getBranchList() {
        sqlQuery = properties.getProperty("BranchSetupDao.getBranchList");
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery, BranchSetupDTO.class);
        return hQuery.list();
    }

    @Transactional(readOnly = true)
    public Boolean isBranchExist(String branchName) {
        sqlQuery = properties.getProperty("BranchSetupDao.isBranchExist");
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery).setParameter("branchName", branchName);
        return !hQuery.list().get(0).equals(BigInteger.ZERO);
    }
}
