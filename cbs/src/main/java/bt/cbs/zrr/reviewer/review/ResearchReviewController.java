package bt.cbs.zrr.reviewer.review;

import bt.cbs.zrr.global.base.BaseController;
import bt.cbs.zrr.global.dto.ResponseMessage;
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
@RequestMapping(value = "/researchReview")
public class ResearchReviewController extends BaseController {

    @Autowired
    private ResearchReviewService researchReviewService;

    @RequestMapping()
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response, Model model){
        ModelAndView modelAndView = new ModelAndView();
        currentUser = getCurrentUser(request);
        modelAndView.setViewName("reviewer/researchReview");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/gResearchList", method = RequestMethod.GET)
    public List gResearchList(HttpServletRequest request, String status, String research_month) {
        currentUser = getCurrentUser(request);
        return researchReviewService.gResearchList(status.equalsIgnoreCase("ALL")?null:status,currentUser.getUserID(),research_month);
    }

    @ResponseBody
    @RequestMapping(value = "/saveReviewerMarks", method = RequestMethod.POST)
    public ResponseMessage saveReviewerMarks(HttpServletRequest request, String rComment, Integer researchNo, Integer researchId, String[] assessmentCriteria, String[] weightage, String marksAllocated ) throws IOException {
        currentUser = getCurrentUser(request);
        return researchReviewService.saveReviewerMarks(currentUser, rComment, researchNo, researchId, assessmentCriteria, weightage, marksAllocated);
    }

}
