package com.springapp.mvc.setup.branch;

import com.springapp.mvc.global.base.BaseController;
import com.springapp.mvc.global.common.CommonService;
import com.springapp.mvc.global.dto.ResponseMessage;
import com.springapp.mvc.global.enumeration.ScreenNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Sonam Dargay on 1/3/2020.
 */
@Controller
@RequestMapping(value = "branchSetup")
public class BranchSetupController extends BaseController {
    @Autowired
    private BranchSetupService branchSetupService;

    @Autowired
    private CommonService commonService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("SCREEN_ID", ScreenNumber.BRANCH_SETUP.getValue());
        model.addAttribute("statusList", commonService.getStatusList());
        return "setup/branchSetup";
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseMessage save(HttpServletRequest request, BranchSetupDTO branchSetupDTO, Character actionType) throws Exception {
        currentUser = getCurrentUser(request);
        return branchSetupService.save(branchSetupDTO, currentUser, actionType);
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseMessage delete(HttpServletRequest request, int branchId) {
        currentUser = getCurrentUser(request);
        responseMessage = branchSetupService.delete(branchId, currentUser);
        return responseMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/getBranchList", method = RequestMethod.GET)
    public List getBranchList() {
        return branchSetupService.getBranchList();
    }

    @ResponseBody
    @RequestMapping(value = "/isBranchExist", method = RequestMethod.GET)
    public Boolean isBranchExist(String branchName) {
        return branchSetupService.isBranchExist(branchName);
    }
}
