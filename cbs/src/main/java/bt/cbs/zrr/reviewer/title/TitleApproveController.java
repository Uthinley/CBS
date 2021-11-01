package bt.cbs.zrr.reviewer.title;

import bt.cbs.zrr.global.base.BaseController;
import bt.cbs.zrr.global.dto.ResponseMessage;
import bt.cbs.zrr.research.topic.ResearchTopicDTO;
import bt.cbs.zrr.research.topic.ResearchTopicService;
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
@RequestMapping(value = "/titleApprove")
public class TitleApproveController extends BaseController {

    private final TitleApproveService tApproveService;

    public TitleApproveController(TitleApproveService tApproveService) {
        this.tApproveService = tApproveService;
    }

    @RequestMapping()
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response, Model model){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("researcher/titleApprove");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseMessage save(HttpServletRequest request, String action, String remarks,String[] research_title_ids) throws IOException {
        currentUser = getCurrentUser(request);
        return tApproveService.save(currentUser, action,remarks, research_title_ids);
    }

    @ResponseBody
    @RequestMapping(value = "/gTopicList", method = RequestMethod.GET)
    public List gTopicList(HttpServletRequest request, String status) {
        return tApproveService.gTopicList(status.equalsIgnoreCase("ALL")?null:status,null);
    }

    @ResponseBody
    @RequestMapping(value = "/findTopic", method = RequestMethod.GET)
    public ResearchTopicDTO findTopic(HttpServletRequest request,String research_month) {
        return tApproveService.findTopic(research_month,getCurrentUser(request).getUserName());
    }

}
