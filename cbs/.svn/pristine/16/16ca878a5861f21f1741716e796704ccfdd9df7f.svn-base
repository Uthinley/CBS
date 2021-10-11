package com.springapp.mvc.setup.group;

import com.springapp.mvc.global.base.BaseDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Sonam Dargay on 12/20/2019.
 */
@Repository
public class GroupSetupDao extends BaseDao {

    public GroupSetup getGroupById(Integer GR_ID) {
        return em.find(GroupSetup.class, GR_ID);
    }

    public GroupSetup_A getGroupAudit(Integer GR_ID) {
        return em.find(GroupSetup_A.class, GR_ID);
    }

    //region public methods.
    @Transactional(rollbackFor = Exception.class)
    public void save(GroupSetup groupSetup, GroupSetup_A groupSetup_a) {
        if (!StringUtils.isEmpty(groupSetup)) {
            saveOrUpdate(groupSetup);
        }
//        em.persist(groupSetup_a);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(int groupId) {
        sqlQuery = properties.getProperty("GroupSetupDao.delete");
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery).setParameter("groupId",groupId);
        hQuery.executeUpdate();
    }

    @Transactional(readOnly = true)
    public List<GroupSetupDTO> getGroupList() {
        sqlQuery = properties.getProperty("GroupSetupDao.getGroupList");
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery, GroupSetupDTO.class);
        return hQuery.list();
    }

    @Transactional(readOnly = true)
    public Boolean isGroupExist(String groupName) {
        sqlQuery = properties.getProperty("GroupSetupDao.isGroupExist");
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery).setParameter("groupName", groupName);
        return !hQuery.list().get(0).equals(BigInteger.ZERO);
    }

    @Transactional(readOnly = true)
    public GroupSetup getGroupInfoById(int groupID) {
        return em.find(GroupSetup.class, groupID);
    }
    //endregion
}
