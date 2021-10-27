package bt.cbs.zrr.setup.passwordPolicy;

import bt.cbs.zrr.global.base.BaseController;
import bt.cbs.zrr.global.dto.ResponseMessage;
import bt.cbs.zrr.global.enumeration.ScreenNumber;
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
 * Created by sangay tenzin on 12/20/2019.
 * Modified by Dechen Wangdi on 14/02/2020.
 */

@Controller
@RequestMapping(value = "passwordPolicy")
@PreAuthorize("isAuthenticated()")
public class PasswordPolicyController extends BaseController {

    //region private variable.
    @Autowired
    private PasswordPolicyService passwordPolicyService;
    //endregion

    //region public methods.
    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("SCREEN_ID", ScreenNumber.PASSWORD_POLICY.getValue());
        return "setup/passwordPolicy";
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseMessage save(HttpServletRequest request, PasswordPolicyDTO passwordPolicyDTO) throws Exception {
        currentUser = getCurrentUser(request);
        return passwordPolicyService.save(passwordPolicyDTO, currentUser);
    }

    @ResponseBody
    @RequestMapping(value = "/getPasswordPolicyInfo", method = RequestMethod.GET)
    public PasswordPolicyDTO getPasswordPolicyInfo() throws Exception {
        return passwordPolicyService.getPasswordPolicyInfo();
    }
    //endregion
}
