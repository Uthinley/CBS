package com.springapp.mvc.setup.cPassword;

import com.springapp.mvc.global.base.BaseController;
import com.springapp.mvc.global.dto.ResponseMessage;
import com.springapp.mvc.global.enumeration.ScreenNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ====================================================================
 * Modified by: Dechen Wagndi
 * Modified date:
 * Purpose:
 * ====================================================================
 */
@Controller
@RequestMapping(value = "/changePassword")
@PreAuthorize("isAuthenticated()")
public class ChangePasswordController extends BaseController {

    @Autowired
    private ChangePasswordService changePasswordService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
        currentUser = getCurrentUser(request);
        model.addAttribute("username", currentUser.getUserName());
        model.addAttribute("SCREEN_ID", ScreenNumber.CHANGE_PASSWORD.getValue());
        return "setup/changePassword";
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseMessage save(HttpServletRequest request, ChangePasswordDTO changePasswordDTO) {
        currentUser = getCurrentUser(request);
        responseMessage = changePasswordService.changePassword(changePasswordDTO, currentUser);
        return responseMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/matchPassword", method = RequestMethod.GET)
    public ResponseMessage isPasswordMatching(HttpServletRequest request, String password) {
        currentUser = getCurrentUser(request);
        return changePasswordService.isPasswordMatching(password, currentUser.getUserName());
    }
}
