package bt.cbs.zrr.research.topic;

import bt.cbs.zrr.auth.LoginDTO;
import bt.cbs.zrr.global.base.BaseController;
import bt.cbs.zrr.global.dto.CurrentUser;
import bt.cbs.zrr.global.dto.ResponseMessage;
import bt.cbs.zrr.global.library.CustomFileUtil;
import bt.cbs.zrr.research.paper.ResearchDTO;
import bt.cbs.zrr.research.paper.ResearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        LoginDTO loginDTO = (LoginDTO) auth.getPrincipal();

        CurrentUser currentUser = new CurrentUser();
        currentUser.setUserName(loginDTO.getUserName());
        currentUser.setFullName(loginDTO.getUserFullName());
        currentUser.setGroupId(loginDTO.getGroupId());
        currentUser.setServerDate(new Date());

        request.getSession().setAttribute("currentUser", currentUser);
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
    @RequestMapping(value = "/getResearchList", method= RequestMethod.GET)
    public List<ResearchDTO> getResearchList(HttpServletRequest request){
        currentUser = getCurrentUser(request);
        return topicService.getResearchList(currentUser);
    }

    @ResponseBody
    @RequestMapping(value = "/getWordCount", method= RequestMethod.POST)
    public Long getWordCount(HttpServletRequest request,@RequestParam("file") MultipartFile file) throws IOException {
        return CustomFileUtil.wordCount(file.getInputStream());
    }
}
