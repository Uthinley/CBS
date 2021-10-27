package bt.cbs.zrr.reviewer;

import bt.cbs.zrr.global.base.BaseController;
import bt.cbs.zrr.global.common.CommonService;
import bt.cbs.zrr.research.ResearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping(value = "/reviewer")
public class ReviewerController extends BaseController {
    @Autowired
    private CommonService commonService;
    private ReviewerService reviewerService;

    @RequestMapping()
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response, Model model)
            throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("researchMonthList", commonService.getResearchMonthList());
        modelAndView.setViewName("assignReviewer");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getReviewerList", method= RequestMethod.GET)
    public List<ResearchDTO> getReviewerList(HttpServletRequest request){
        currentUser = getCurrentUser(request);
        return reviewerService.getReviewerList();
    }
}
