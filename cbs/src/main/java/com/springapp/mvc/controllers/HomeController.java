package com.springapp.mvc.controllers;

import com.springapp.mvc.auth.LoginDTO;
import com.springapp.mvc.card.CardDTO;
import com.springapp.mvc.card.CardService;
import com.springapp.mvc.data.FileUploadDTO;
import com.springapp.mvc.data.db.bankDeposit.BankDepositService;
import com.springapp.mvc.global.base.BaseController;
import com.springapp.mvc.global.dto.CurrentUser;
import com.springapp.mvc.global.dto.ResponseMessage;
import com.springapp.mvc.global.library.DateUtil;
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
    private CardService cardService;


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
        model.addAttribute("currentDate", DateUtil.formatDate(currentUser.getServerDate()));
        modelAndView.setViewName("home");
        return modelAndView;
    }

    /**
     * //endregion
     * home controller
     *
     * @param request  request
     * @param response response
     * @return ModelAndView
     */

    public String indexd(HttpServletRequest request, HttpServletResponse response, Model model)
            throws IOException {
        return "home";
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseMessage save(HttpServletRequest request, FileUploadDTO fileUploadDTO) throws Exception {
        return bankDepositService.save(fileUploadDTO);
    }

    /**
     * Get bank-wise total cards
     * @return CardDTO list
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/getTotalCardsBankWise", method = RequestMethod.GET)
    public List<CardDTO> getTotalCardsBankWise() throws Exception {
        return cardService.totalCardsBankWise();
    }

    /**
     * Method to get all the cards info
     * @return CardDTO List
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/getAllCardInfoList", method = RequestMethod.GET)
    public List<CardDTO> getAllCardInfoList() throws Exception {
        return cardService.getAllCardInfoList();
    }

}


