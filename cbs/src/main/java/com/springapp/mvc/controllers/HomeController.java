package com.springapp.mvc.controllers;

import com.springapp.mvc.auth.LoginDTO;
import com.springapp.mvc.card.CardDTO;
import com.springapp.mvc.card.CardService;
import com.springapp.mvc.data.FileUploadDTO;
import com.springapp.mvc.data.db.bankDeposit.BankDepositService;
import com.springapp.mvc.global.base.BaseController;
import com.springapp.mvc.global.common.CommonService;
import com.springapp.mvc.global.dto.CurrentUser;
import com.springapp.mvc.global.dto.ResponseMessage;
import com.springapp.mvc.global.library.DateUtil;
import com.springapp.mvc.research.ResearchDTO;
import com.springapp.mvc.research.ResearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
@RequestMapping(value = "")
public class HomeController extends BaseController {
    @Autowired
    private BankDepositService bankDepositService;
    @Autowired
    private ResearchService researchService;
    @Autowired
    private CommonService commonService;

    @RequestMapping(value = {"/", "home"}, method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response, Model model)
            throws IOException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        LoginDTO loginDTO = (LoginDTO) auth.getPrincipal();

        CurrentUser currentUser = new CurrentUser();
        currentUser.setBranchCode(loginDTO.getBranchCode());
        currentUser.setBranchName(loginDTO.getBranchName());
        currentUser.setUserName(loginDTO.getUserName());
        currentUser.setFullName(loginDTO.getUserFullName());
        currentUser.setGroupId(loginDTO.getGroupId());
        currentUser.setBankId(loginDTO.getBankId());
        currentUser.setDzongkhagId(loginDTO.getDzongkhagId());
        currentUser.setServerDate(new Date());

        request.getSession().setAttribute("currentUser", currentUser);

        if (loginDTO.isPasswordChangeYN()) {
            response.sendRedirect("changePassword");
        }


        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("applicationStatusCode", commonService.getApplicationStatusCode());
        model.addAttribute("currentDate", DateUtil.formatDate(currentUser.getServerDate()));
        modelAndView.setViewName("home");
        return modelAndView;
    }


    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseMessage save(HttpServletRequest request, FileUploadDTO fileUploadDTO) throws Exception {
        return bankDepositService.save(fileUploadDTO);
    }

    @ResponseBody
    @RequestMapping(value="/saveReviewerComments")
    public ResponseMessage saveReviewerComments(HttpServletRequest request, Integer researchId, String rComment, Integer statusId){
        return researchService.saveReviewerComments(researchId, rComment, statusId);
    }

    @ResponseBody
    @RequestMapping(value = "/getAllResearchList", method= RequestMethod.GET)
    public List<ResearchDTO> getResearchList(HttpServletRequest request){
        currentUser = getCurrentUser(request);
        return researchService.geAllResearchList();
    }

}


