package bt.cbs.zrr.reviewer.assign;

//import com.springapp.mvc.global.base.BaseController;
//import com.springapp.mvc.global.common.CommonService;
//import com.springapp.mvc.global.dto.ResponseMessage;
//import com.springapp.mvc.research.ResearchDTO;
import bt.cbs.zrr.global.base.BaseController;
import bt.cbs.zrr.global.common.CommonService;
import bt.cbs.zrr.global.dto.ResponseMessage;
import bt.cbs.zrr.research.paper.ResearchDTO;
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
import java.util.Date;
import java.util.List;

@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping(value = "/reviewer")
public class ReviewerController extends BaseController {
    @Autowired
    private CommonService commonService;
    @Autowired
    private ReviewerService reviewerService;

    @RequestMapping()
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response, Model model)
            throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("researchMonthList", commonService.getResearchMonthList());
        model.addAttribute("reviewerList", reviewerService.getReviewerList());
        modelAndView.setViewName("assignReviewer");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getResearchListForReview", method= RequestMethod.GET)
    public List<ResearchDTO> getResearchListForReview(HttpServletRequest request, String month){
        currentUser = getCurrentUser(request);
        return reviewerService.getResearchListForReview(month);
    }

    @ResponseBody
    @RequestMapping(value = "/getAssignedResearchList", method= RequestMethod.GET)
    public List<ResearchDTO> getAssignedResearchList(HttpServletRequest request, String month){
        currentUser = getCurrentUser(request);
        return reviewerService.getAssignedResearchList(month);
    }

    @ResponseBody
    @RequestMapping(value = "/assignReviewer", method= RequestMethod.POST)
    public ResponseMessage assignReviewer(HttpServletRequest request, Integer researchNo, Integer reviewerId, Date completionDate){
        currentUser = getCurrentUser(request);
        return reviewerService.assignReviewer(currentUser, researchNo, reviewerId, completionDate);
    }

}
