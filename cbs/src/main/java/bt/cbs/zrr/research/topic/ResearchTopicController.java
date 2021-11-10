package bt.cbs.zrr.research.topic;

import bt.cbs.zrr.global.base.BaseController;
import bt.cbs.zrr.global.dto.ResponseMessage;
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
@RequestMapping(value = "/researchTopic")
public class ResearchTopicController extends BaseController {

    private final ResearchTopicService topicService;

    public ResearchTopicController(ResearchTopicService topicService) {
        this.topicService = topicService;
    }

    @RequestMapping()
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response, Model model){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("researcher/researchTopic");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseMessage save(HttpServletRequest request, ResearchTopicDTO topicDTO) throws IOException {
        currentUser = getCurrentUser(request);
        return topicService.save(currentUser, topicDTO);
    }

    @ResponseBody
    @RequestMapping(value = "/gTopicList", method = RequestMethod.GET)
    public List gTopicList(HttpServletRequest request, String status) throws Exception {
        return topicService.gTopicList(status,null,getCurrentUser(request));
    }

    @ResponseBody
    @RequestMapping(value = "/findTopic", method = RequestMethod.GET)
    public ResearchTopicDTO findTopic(HttpServletRequest request,String research_month) {
        return topicService.findTopic(research_month,getCurrentUser(request).getUserName());
    }

}
