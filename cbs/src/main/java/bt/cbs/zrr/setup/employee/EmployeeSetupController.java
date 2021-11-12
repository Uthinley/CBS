package bt.cbs.zrr.setup.employee;

import bt.cbs.zrr.global.base.BaseController;
import bt.cbs.zrr.global.common.CommonService;
import bt.cbs.zrr.global.dto.ResponseMessage;
import bt.cbs.zrr.global.enumeration.ScreenNumber;
import bt.cbs.zrr.setup.user.UserSetupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/employeeSetup")
@PreAuthorize("isAuthenticated()")
public class EmployeeSetupController extends BaseController {

    @Autowired
    private EmployeeSetupService employeeSetupService;
    @Autowired
    private CommonService commonService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(HttpServletRequest request, Model model) {
        model.addAttribute("statusList", commonService.getStatusList());
        return "setup/employeeSetup";
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseMessage save(HttpServletRequest request, EmployeeSetupDTO employeeSetupDTO, Character actionType) {
        currentUser = getCurrentUser(request);
        responseMessage = employeeSetupService.save(employeeSetupDTO, currentUser, actionType);
        return responseMessage;
    }


    @ResponseBody
    @RequestMapping(value = "/getEmployeeList", method = RequestMethod.GET)
    public List getEmployeeList() {
        return employeeSetupService.getEmployeeList();
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseMessage delete(HttpServletRequest request, EmployeeSetupDTO employeeSetupDTO) {
        currentUser = getCurrentUser(request);
        responseMessage = employeeSetupService.delete(employeeSetupDTO.getEmployeeId(), currentUser);
        return responseMessage;
    }

}
