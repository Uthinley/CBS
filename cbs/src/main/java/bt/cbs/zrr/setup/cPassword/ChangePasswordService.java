package bt.cbs.zrr.setup.cPassword;

import bt.cbs.zrr.setup.user.UserSetupService;
import bt.cbs.zrr.global.base.BaseService;
import bt.cbs.zrr.global.dto.CurrentUser;
import bt.cbs.zrr.global.dto.ResponseMessage;
import bt.cbs.zrr.global.library.DateUtil;
import bt.cbs.zrr.setup.passwordPolicy.PasswordPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ====================================================================
 * Created by Dechen Wangdi 13-02-2020
 * Description:
 * ====================================================================
 * Modified by:
 * Modified date:
 * Purpose:
 * ====================================================================
 */
@Service
public class ChangePasswordService extends BaseService {
    @Autowired
    private ChangePasswordDao changePasswordDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserSetupService userSetupService;

    @Autowired
    private PasswordPolicyService passwordPolicyService;

    //region public methods
    @Transactional(readOnly = false)
    public ResponseMessage changePassword(ChangePasswordDTO changePasswordDTO, CurrentUser currentUser) {
        if (changePasswordDTO == null) {
            responseMessage.setText(MSG_DEFAULT_UNSUCCESSFUL);
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            return responseMessage;
        }
        String userName = changePasswordDTO.getUserName();
        String newPassword = changePasswordDTO.getNewPassword();

        if (!userName.equals(currentUser.getUserName())) {
            responseMessage.setText("You are authorised to change your password only.");
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            return responseMessage;
        }

        responseMessage = isPasswordMatching(changePasswordDTO.getPassword(), userName);
        if (responseMessage.getStatus() == UNSUCCESSFUL_STATUS) {
            return responseMessage;
        }

        if (!newPassword.equals(changePasswordDTO.getConfirmPassword())) {
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("Confirm password does not match password.");
            return responseMessage;
        }

        responseMessage = passwordPolicyService.isPasswordValid(newPassword);
        if (responseMessage.getStatus() != SUCCESSFUL_STATUS) {
            return responseMessage;
        }

        changePasswordDao.changePassword(userName, passwordEncoder.encode(newPassword));
        userSetupService.updateIsBadCredentialStatus(DateUtil.plusDay(currentUser.getServerDate(), PASSWORD_EXPIRY_DAYS)
                , userName);
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Password changed successfully.");
        return responseMessage;
    }

    @Transactional(readOnly = true)
    public ResponseMessage isPasswordMatching(String password, String username) {
        String passwordFDB = changePasswordDao.getPassword(username);
        Boolean isPasswordMatching = passwordEncoder.matches(password, passwordFDB);
        if (isPasswordMatching) {
            responseMessage.setStatus(SUCCESSFUL_STATUS);
        } else {
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("Please enter the correct password.");
        }
        return responseMessage;
    }
    //endregion
}
