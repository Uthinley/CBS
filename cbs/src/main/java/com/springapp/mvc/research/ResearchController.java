package com.springapp.mvc.research;

import com.springapp.mvc.auth.LoginDTO;
import com.springapp.mvc.global.base.BaseController;
import com.springapp.mvc.global.dto.CurrentUser;
import com.springapp.mvc.global.dto.ResponseMessage;
import com.springapp.mvc.global.library.CustomFileUtil;
import com.springapp.mvc.setup.card.CardSetupDTO;
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
@RequestMapping(value = "/research")
public class ResearchController extends BaseController {

    @Autowired
    private ResearchService researchService;

    @RequestMapping()
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response, Model model)
            throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        LoginDTO loginDTO = (LoginDTO) auth.getPrincipal();

        CurrentUser currentUser = new CurrentUser();
        currentUser.setUserName(loginDTO.getUserName());
        currentUser.setFullName(loginDTO.getUserFullName());
        currentUser.setGroupId(loginDTO.getGroupId());
        currentUser.setServerDate(new Date());

        request.getSession().setAttribute("currentUser", currentUser);
        modelAndView.setViewName("researcher/Assignment");
        return modelAndView;

    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseMessage save(@RequestParam("file") MultipartFile file, HttpServletRequest request, ResearchDTO researchDTO) throws IOException {
        currentUser = getCurrentUser(request);
        return researchService.save(file, currentUser, researchDTO);
    }

    @ResponseBody
    @RequestMapping(value = "/getResearchList", method= RequestMethod.GET)
    public List<ResearchDTO> getResearchList(HttpServletRequest request){
        currentUser = getCurrentUser(request);
        return researchService.getResearchList(currentUser);
    }

    @ResponseBody
    @RequestMapping(value = "/getWordCount", method= RequestMethod.POST)
    public Long getWordCount(HttpServletRequest request,@RequestParam("file") MultipartFile file) throws IOException {
        return CustomFileUtil.wordCount(file.getInputStream());
    }
}
