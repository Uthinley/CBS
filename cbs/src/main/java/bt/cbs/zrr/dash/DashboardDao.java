package bt.cbs.zrr.dash;

import bt.cbs.zrr.global.base.BaseDao;
import bt.cbs.zrr.global.dto.DropdownDTO;
import bt.cbs.zrr.global.dto.GenericDTO;
import bt.cbs.zrr.global.library.GlobalVariable;
import bt.cbs.zrr.setup.user.UserSetupDTO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DashboardDao extends BaseDao {

    @Transactional(readOnly = true)
    public List<DropdownDTO> getUserCount() {
        sqlQuery = properties.getProperty("DashboardDao.getUserCount");
        return hibernateQuery(sqlQuery, DropdownDTO.class).list();
    }
    @Transactional(readOnly = true)
    public GenericDTO getTitleCount(String username, String month) {
        sqlQuery = properties.getProperty("DashboardDao.getTitleCount");
        return (GenericDTO)hibernateQuery(sqlQuery, GenericDTO.class).setParameter("month",month).
                setParameter("username",username).list().get(0);
        //return (GenericDTO)hibernateQuery(sqlQuery, GenericDTO.class).list().get(0);
    }
    @Transactional(readOnly = true)
    public GenericDTO getPaperCount(String username, String month) {
        sqlQuery = properties.getProperty("DashboardDao.getPaperCount");
        return (GenericDTO)hibernateQuery(sqlQuery, GenericDTO.class).setParameter("month",month).
                setParameter("username",username).list().get(0);
        //return (GenericDTO)hibernateQuery(sqlQuery, GenericDTO.class).list().get(0);
    }

    @Transactional(readOnly = true)
    public GenericDTO reviewerPaper(String username, String month) {
        sqlQuery = properties.getProperty("DashboardDao.reviewerPaper");
        return (GenericDTO)hibernateQuery(sqlQuery, GenericDTO.class).setParameter("month",month).
                setParameter("username",username).list().get(0);
        //return (GenericDTO)hibernateQuery(sqlQuery, GenericDTO.class).list().get(0);
    }
}
