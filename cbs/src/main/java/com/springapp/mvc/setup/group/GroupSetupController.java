package com.springapp.mvc.setup.group;

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
 * Created by hp on 12/20/2019.
 */

@Controller
@RequestMapping(value = "groupSetup")
public class GroupSetupController extends BaseController {

    //region private variable
    @Autowired
    private GroupSetupService groupSetupService;

    @Autowired
    private CommonService commonService;

    //endregion
    //region public method.
    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("SCREEN_ID", ScreenNumber.GROUP_SETUP.getValue());
        model.addAttribute("statusList", commonService.getStatusList());
        return "setup/groupSetup";
    }

    @ResponseBody
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ResponseMessage save(HttpServletRequest request, GroupSetupDTO groupSetupDTO, Character actionType) throws Exception {
        currentUser = getCurrentUser(request);
        return groupSetupService.save(groupSetupDTO, currentUser, actionType);
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseMessage delete(HttpServletRequest request, int groupId) {
        currentUser = getCurrentUser(request);
        responseMessage = groupSetupService.delete(groupId, currentUser);
        return responseMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/isGroupExist", method = RequestMethod.GET)
    public Boolean isGroupExist(String groupName) {
        return groupSetupService.isGroupExist(groupName);
    }


    @ResponseBody
    @RequestMapping(value = "/getGroupList", method = RequestMethod.GET)
    public List getGroupList() {
        return groupSetupService.getGroupList();
    }
}
