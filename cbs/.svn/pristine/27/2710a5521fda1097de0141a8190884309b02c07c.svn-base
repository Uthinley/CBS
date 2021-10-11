package com.springapp.mvc.card;

import com.springapp.mvc.auth.LoginDTO;
import com.springapp.mvc.global.base.BaseController;
import com.springapp.mvc.global.common.CommonService;
import com.springapp.mvc.global.dto.CurrentUser;
import com.springapp.mvc.global.dto.ResponseMessage;
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
@RequestMapping(value = "/card")
public class CardController extends BaseController {

    @Autowired
    private CardService cardService;
    @Autowired
    private CommonService commonService;

    @RequestMapping(value = {""}, method = RequestMethod.GET)
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

        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("cardList", cardService.getCardType());
        modelAndView.setViewName("card/cardIssuance");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<CardDTO> search( String cid) throws Exception {
        return cardService.search(cid);
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseMessage search(HttpServletRequest request, CardDTO cardDTO) throws Exception {
        currentUser = getCurrentUser(request);
        return cardService.save(currentUser, cardDTO);
    }

}
