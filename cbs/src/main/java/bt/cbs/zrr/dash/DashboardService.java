package bt.cbs.zrr.dash;

import bt.cbs.zrr.global.base.BaseService;
import bt.cbs.zrr.global.dto.CurrentUser;
import bt.cbs.zrr.global.dto.DropdownDTO;
import bt.cbs.zrr.global.dto.GenericDTO;
import bt.cbs.zrr.global.enumeration.UserGroup;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DashboardService extends BaseService {
    private DashboardDao dashboardDao;

    public DashboardService(DashboardDao dashboardDao) {
        this.dashboardDao = dashboardDao;
    }

    @Transactional(readOnly = true)
    public List<DropdownDTO> getUserCount() {
        return dashboardDao.getUserCount();
    }

    @Transactional(readOnly = true)
    public GenericDTO getTitleCount(CurrentUser currentUser,String month) {
        if(currentUser.getGroupId() == UserGroup.RESEARCHER.value()){
            return dashboardDao.getTitleCount(currentUser.getUserName(),month);
        }
        return dashboardDao.getTitleCount(null,month);

    }

    @Transactional(readOnly = true)
    public GenericDTO getPaperCount(CurrentUser currentUser, String month) {
        if(currentUser.getGroupId() == UserGroup.RESEARCHER.value()){
            return dashboardDao.getPaperCount(currentUser.getUserName(),month);
        }
        return dashboardDao.getPaperCount(null,month);
    }

    @Transactional(readOnly = true)
    public GenericDTO reviewerPaper(CurrentUser currentUser, String month) {
        return dashboardDao.reviewerPaper(currentUser.getUserName(),month);
    }

}
