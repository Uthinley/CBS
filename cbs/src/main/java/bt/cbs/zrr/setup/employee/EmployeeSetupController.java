package bt.cbs.zrr.setup.employee;

import bt.cbs.zrr.global.base.BaseController;
import bt.cbs.zrr.global.enumeration.ScreenNumber;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/employeeSetup")
@PreAuthorize("isAuthenticated()")
public class EmployeeSetupController extends BaseController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(HttpServletRequest request, Model model) {
        return "setup/employeeSetup";
    }
}
