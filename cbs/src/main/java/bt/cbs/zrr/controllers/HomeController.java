package bt.cbs.zrr.controllers;

import bt.cbs.zrr.auth.LoginDTO;
import bt.cbs.zrr.global.base.BaseController;
import bt.cbs.zrr.global.dto.CurrentUser;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping(value = "")
public class HomeController extends BaseController {

    @RequestMapping(value = {"/", "home"}, method = RequestMethod.GET)
    public void index(HttpServletRequest request, HttpServletResponse response, Model model)
            throws IOException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        LoginDTO loginDTO = (LoginDTO) auth.getPrincipal();
        CurrentUser currentUser = new CurrentUser();
        currentUser.setBranchCode(loginDTO.getBranchCode());
        currentUser.setBranchName(loginDTO.getBranchName());
        currentUser.setUserID(loginDTO.getUserId().intValue());
        currentUser.setUserName(loginDTO.getUserName());
        currentUser.setFullName(loginDTO.getUserFullName());
        currentUser.setGroupId(loginDTO.getGroupId());
        currentUser.setBankId(loginDTO.getBankId());
        currentUser.setDzongkhagId(loginDTO.getDzongkhagId());
        currentUser.setServerDate(new Date());
        request.getSession().setAttribute("currentUser", currentUser);

        if (loginDTO.isPasswordChangeYN()) {
            response.sendRedirect("changePassword");
        }else {
            response.sendRedirect("dashboard");
        }
    }

}


