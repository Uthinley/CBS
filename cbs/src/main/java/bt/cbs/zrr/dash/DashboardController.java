package bt.cbs.zrr.dash;

import bt.cbs.zrr.global.base.BaseController;
import bt.cbs.zrr.global.common.CommonService;
import bt.cbs.zrr.global.dto.CurrentUser;
import bt.cbs.zrr.global.dto.DropdownDTO;
import bt.cbs.zrr.global.dto.GenericDTO;
import bt.cbs.zrr.global.library.DateUtil;
import bt.cbs.zrr.research.comment.ResearchCommentService;
import bt.cbs.zrr.research.paper.ResearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping(value = "/dashboard")
public class DashboardController extends BaseController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response, Model model,String month)
            throws IOException {

        ModelAndView modelAndView = new ModelAndView();
        currentUser = getCurrentUser(request);
        if(month == null || month.isEmpty()){
            month = new SimpleDateFormat("YYYY-MM").format(new Date());
        }


        if(currentUser.getGroupId() == 1 || currentUser.getGroupId() == 2){ // admin and approver
            model.addAttribute("users", dashboardService.getUserCount());
            model.addAttribute("title", dashboardService.getTitleCount(currentUser,month));
            model.addAttribute("paper", dashboardService.getPaperCount(currentUser,month));
            modelAndView.setViewName("dash/approver_db");
        }else if(currentUser.getGroupId() == 3){ // reviewer
            model.addAttribute("assignedPaper", dashboardService.reviewerPaper(currentUser,null));
            modelAndView.setViewName("dash/reviewer_db");
        }else{ // researcher
            model.addAttribute("title", dashboardService.getTitleCount(currentUser,null));
            model.addAttribute("paper", dashboardService.getPaperCount(currentUser,null));
            modelAndView.setViewName("dash/researcher_db");
        }
        //modelAndView.setViewName("home");

        return modelAndView;
    }



}


