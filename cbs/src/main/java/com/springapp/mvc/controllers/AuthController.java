package com.springapp.mvc.controllers;

import com.springapp.mvc.auth.LoginDTO;
import com.springapp.mvc.global.common.CommonService;
import com.springapp.mvc.global.enumeration.LoginErrorCode;
import com.springapp.mvc.global.library.CustomFileUtil;
import com.springapp.mvc.setup.userLog.UserLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Nima Yoezer
 * Description: It handles and processes authentication success request, keeps history.
 * Date: 25/07-2018
 */
@Controller
@RequestMapping(value = "")
public class AuthController {

    @Autowired
    private UserLogService userLogService;

    /**
     * login loader
     *
     * @param error   error
     * @param request request
     * @return ModelAndView
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam(value = "error", required = false) String error,
                        HttpServletRequest request, Model model) {

        if (error != null) {
            model.addAttribute("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof AnonymousAuthenticationToken) {
            model.addAttribute("bankName", "Royal Monetary Authority of Bhutan");
            return "login";
        } else {
            return "home";
        }
    }

    /**
     * logout loader
     *
     * @return ModelAndView
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        userLogService.update(request);
        new SecurityContextLogoutHandler().logout(request, response, auth);
        return "redirect:/login";
    }

    /**
     * authentication processing path
     *
     * @return ModelAndView
     */
    @PreAuthorize("isAnonymous()")
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ModelAndView auth() {

        ModelAndView model = new ModelAndView();
        model.setViewName("home");
        return model;
    }

    /**
     * access denied path
     *
     * @param request request
     * @return ModelAndView
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accessDenied(HttpServletRequest request) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ModelAndView model = new ModelAndView();

        if (!(auth instanceof AnonymousAuthenticationToken)) {
            LoginDTO userLogin = (LoginDTO) auth.getPrincipal();
            model.addObject("username", userLogin.getUserName());
        }
        model.setViewName("403");
        return model;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/viewOrDownloadFile", method = RequestMethod.POST)
    public void viewDownloadFile(HttpServletResponse response, @RequestParam String filePath) throws Exception {
        CustomFileUtil.viewDownloadFile(filePath,response);
    }

    /**
     * to generate authentication error message
     *
     * @param request request
     * @param key     key
     * @return String
     */
    private String getErrorMessage(HttpServletRequest request, String key) {
        Exception exception = (Exception) request.getSession().getAttribute(key);
        if (exception != null) {
            String message = exception.getMessage();

            if (message.equals(LoginErrorCode.FAILED.getCode()) || message.equals(LoginErrorCode.LOCKED.getCode())) {
                return message;
            } else if (message.equals(LoginErrorCode.EXPIRED.getCode())) {
                return message;
            } else {
                return LoginErrorCode.MAX_SESSION.getCode();
            }
        } else {
            return null;
        }
    }
}
