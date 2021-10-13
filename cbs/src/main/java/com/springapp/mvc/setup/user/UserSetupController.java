package com.springapp.mvc.setup.user;

import com.springapp.mvc.global.base.BaseController;
import com.springapp.mvc.global.common.CommonService;
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
import java.util.List;

/**
 * ====================================================================================
 * Created by Dechen Wangdi on 20/12/2019.
 * ====================================================================================
 */
@Controller
@RequestMapping(value = "/usersetup")
@PreAuthorize("isAuthenticated()")
public class UserSetupController extends BaseController {

    @Autowired
    private CommonService commonService;
    @Autowired
    private UserSetupService userSetupService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(HttpServletRequest request, Model model) {
        model.addAttribute("userGroupList", commonService.getUserGroupList());
        model.addAttribute("statusList", commonService.getStatusList());
        //model.addAttribute("branchCodeList", commonService.getBranchCodeList());
        model.addAttribute("SCREEN_ID", ScreenNumber.USER_SETUP.getValue());
        return "setup/userSetup";
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseMessage save(HttpServletRequest request, UserSetupDTO userSetupDTO, Character actionType) {
        currentUser = getCurrentUser(request);
        responseMessage = userSetupService.save(userSetupDTO, currentUser, actionType);
        return responseMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    public ResponseMessage resetPassword(HttpServletRequest request, String username) {
        currentUser = getCurrentUser(request);
        responseMessage = userSetupService.resetPassword(username, currentUser);
        return responseMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseMessage delete(HttpServletRequest request, UserSetupDTO userSetupDTO) {
        currentUser = getCurrentUser(request);
        responseMessage = userSetupService.delete(userSetupDTO, currentUser);
        return responseMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/getUserList", method = RequestMethod.GET)
    public List getUserList() {
        return userSetupService.getUserList();
    }

}
