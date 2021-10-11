package com.springapp.mvc.setup.card;

import com.springapp.mvc.auth.LoginDTO;
import com.springapp.mvc.global.base.BaseController;
import com.springapp.mvc.global.common.CommonService;
import com.springapp.mvc.global.dto.CurrentUser;
import com.springapp.mvc.global.dto.ResponseMessage;
import com.springapp.mvc.setup.branch.BranchSetupDTO;
import com.springapp.mvc.setup.user.UserSetupDTO;
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
@RequestMapping(value = "/cardSetup")
public class CardSetupController extends BaseController {

    @Autowired
    private CommonService commonService;
    @Autowired
    private CardSetupService cardSetupService;

    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public ModelAndView cardType(HttpServletRequest request, HttpServletResponse response, Model model)
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

        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("statusList", commonService.getStatusList());
        modelAndView.setViewName("card/cardTypeSetup");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseMessage save(HttpServletRequest request, CardSetupDTO cardSetupDTO, Character actionType) throws Exception {
        currentUser = getCurrentUser(request);
        return cardSetupService.save(cardSetupDTO, currentUser, actionType);
    }

    @ResponseBody
    @RequestMapping(value = "/getCardList", method= RequestMethod.GET)
    public List<CardSetupDTO> getCardList(){
        return cardSetupService.getCardList();
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseMessage delete(HttpServletRequest request, CardSetupDTO cardSetupDTO) {
        currentUser = getCurrentUser(request);
        responseMessage = cardSetupService.delete(cardSetupDTO, currentUser);
        return responseMessage;
    }
}
