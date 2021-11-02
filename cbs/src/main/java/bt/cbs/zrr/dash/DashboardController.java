package bt.cbs.zrr.dash;

import bt.cbs.zrr.global.base.BaseController;
import bt.cbs.zrr.global.common.CommonService;
import bt.cbs.zrr.global.dto.CurrentUser;
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

@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping(value = "/dashboard")
public class DashboardController extends BaseController {

    @Autowired
    private CommonService commonService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response, Model model)
            throws IOException {

        ModelAndView modelAndView = new ModelAndView();
        currentUser = getCurrentUser(request);
        model.addAttribute("applicationStatusCode", commonService.getApplicationStatusCode());
        model.addAttribute("currentDate", DateUtil.formatDate(currentUser.getServerDate()));
        if(currentUser.getGroupId() == 1 || currentUser.getGroupId() == 4){
            modelAndView.setViewName("dash/approver_db");
        }else if(currentUser.getGroupId() == 3){
            modelAndView.setViewName("dash/reviewer_db");
        }else{
            modelAndView.setViewName("dash/researcher_db");
        }

        return modelAndView;
    }



}


