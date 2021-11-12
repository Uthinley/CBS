package bt.cbs.zrr.setup.employee;

import bt.cbs.zrr.global.base.BaseDao;
import bt.cbs.zrr.setup.user.UserSetup;
import bt.cbs.zrr.setup.user.UserSetupDTO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class EmployeeSetupDAO extends BaseDao {

    @Transactional(readOnly = true)
    public EmployeeSetup getEmployeeId(String employee_id) {
        return em.find(EmployeeSetup.class, employee_id);
    }

    @Transactional
    public void save(EmployeeSetup employeeSetup) {
        saveOrUpdate(employeeSetup);
    }

    @Transactional(readOnly = true)
    public List<EmployeeSetupDTO> getEmployeeList() {
        sqlQuery = properties.getProperty("EmployeeSetupDAO.getEmployeeList");
        org.hibernate.query.Query hQuery = hibernateQuery(sqlQuery, EmployeeSetupDTO.class);
        return hQuery.list();
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(String employeeId) {
        sqlQuery = properties.getProperty("EmployeeSetupDAO.delete");
        hibernateQuery(sqlQuery).setParameter("employeeId",employeeId).executeUpdate();
    }
}
