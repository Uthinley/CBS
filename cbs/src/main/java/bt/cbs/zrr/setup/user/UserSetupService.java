package bt.cbs.zrr.setup.user;

import bt.cbs.zrr.global.base.BaseService;
import bt.cbs.zrr.global.dto.CurrentUser;
import bt.cbs.zrr.global.dto.ResponseMessage;
import bt.cbs.zrr.global.enumeration.StatusCode;
import bt.cbs.zrr.global.library.DateUtil;
import bt.cbs.zrr.global.library.GlobalVariable;
import bt.cbs.zrr.setup.passwordPolicy.PasswordPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * =====================================================================================
 * Created by nimayoezer on 25/07/2018.
 * Handles user setup and processes to save or return values to the controller
 * =====================================================================================
 */
@Service
public class UserSetupService extends BaseService {

    //region local variable
    @Autowired
    private UserSetupDao userSetupDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PasswordPolicyService passwordPolicyService;
    //endregion

    //region public methods
    @Transactional(readOnly = false)
    public ResponseMessage save(UserSetupDTO userSetupDTO, CurrentUser currentUser, Character actionType) {
        if (userSetupDTO == null) {
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("Please enter the details properly.");
            return responseMessage;
        }
        switch (actionType) {
            case CMD_FLAG_CREATE:
                responseMessage = save(userSetupDTO, currentUser);
                break;
            case CMD_FLAG_MODIFY:
                responseMessage = update(userSetupDTO, currentUser);
                break;
            case 'R':
                responseMessage = resetPassword(userSetupDTO.getUserName(), currentUser);
                break;
            default:
                responseMessage.setStatus(UNSUCCESSFUL_STATUS);
                responseMessage.setText(MSG_DEFAULT_UNSUCCESSFUL);
        }
        return responseMessage;
    }

    @Transactional(readOnly = false)
    public ResponseMessage save(UserSetupDTO userSetupDTO, CurrentUser currentUser) {

        UserSetup userSetup = userSetupDao.getUser(userSetupDTO.getUserName());
        if (userSetup != null) {
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("Username already exist. Please choose another one.");
            return responseMessage;
        }
        userSetup = convertDTOToEntity(userSetupDTO, currentUser);
        userSetupDao.save(userSetup);

        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("User is created successfully with default password. 'Default@2021'");
        return responseMessage;
    }

    @Transactional(readOnly = false)
    public ResponseMessage update(UserSetupDTO userSetupDTO, CurrentUser currentUser) {
        UserSetup userSetup = userSetupDao.getUser(userSetupDTO.getUserName());
        if (userSetup == null) {
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("There is some problem. Please logout and try again.");
            return responseMessage;
        }
        userSetup = convertDTOToEntity(userSetup, userSetupDTO, currentUser);
        userSetupDao.save(userSetup);

        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("User is updated successfully.");
        return responseMessage;
    }

    @Transactional(readOnly = false)
    public ResponseMessage resetPassword(String username, CurrentUser currentUser) {
        UserSetup userSetup = userSetupDao.getUser(username);
        if (userSetup == null) {
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("There is some problem. Please logout and try again.");
            return responseMessage;
        }
        userSetup = convertDTOToEntity(userSetup, GlobalVariable.DEFAULT_PASSWORD,currentUser);
        userSetupDao.save(userSetup);

        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Password for user , " + userSetup.getUserName().toUpperCase() + " has been reset successfully to default password");
        return responseMessage;
    }

    @Transactional(readOnly = false)
    public ResponseMessage delete(UserSetupDTO userSetupDTO, CurrentUser currentUser) {
        return null;
    }

    @Transactional(readOnly = true)
    public List<UserSetupDTO> getUserList() {
        return userSetupDao.getUserList(StatusCode.ACTIVE.getValue());
    }

    @Transactional(readOnly = true)
    public UserSetupDTO getUser(String username) {
        UserSetupDTO userSetup = userSetupDao.getUserByUsername(username);
        return userSetup;
    }

    public void updateIsBadCredentialStatus(Date date, String userName) {
        userSetupDao.updateIsBadCredentialStatus(date, userName);
    }
    //endregion

    //region private methods

    /**
     * Convert to Entity for save
     *
     * @param userSetupDTO --  UserSetupDTO
     * @param currentUser  --  CurrentUser
     * @return UserSetup
     */
    private UserSetup convertDTOToEntity(UserSetupDTO userSetupDTO, CurrentUser currentUser) {
        UserSetup userSetup = new UserSetup();
        userSetup.setUserId(userSetupDao.getNextUserId());
        userSetup.setUserName(userSetupDTO.getUserName());
        userSetup.setPassword(passwordEncoder.encode(GlobalVariable.DEFAULT_PASSWORD));
        userSetup.setFullName(userSetupDTO.getFullName());
        userSetup.setEmployeeId(userSetupDTO.getEmployeeId());
        userSetup.setEmailId(userSetupDTO.getEmailId());
        userSetup.setUserStatus(userSetupDTO.getUserStatus());
        userSetup.setGroupId(userSetupDTO.getGroupId());
        userSetup.setIsBadCredential(Boolean.TRUE);
        userSetup.setPasswordExpiryDate(DateUtil.plusDay(currentUser.getServerDate(), PASSWORD_EXPIRY_DAYS));
        userSetup.setCreatedBy(currentUser.getUserName());
        userSetup.setCreatedDate(currentUser.getServerDate());
        return userSetup;
    }

    /**
     * Convert to entity for update
     *
     * @param userSetup    -- UserSetup
     * @param userSetupDTO -- UserSetupDTO
     * @param currentUser  -- CurrentUser
     * @return UserSetup
     */
    private UserSetup convertDTOToEntity(UserSetup userSetup, UserSetupDTO userSetupDTO, CurrentUser currentUser) {

        userSetup.setFullName(userSetupDTO.getFullName());
        userSetup.setEmployeeId(userSetupDTO.getEmployeeId());
        userSetup.setEmailId(userSetupDTO.getEmailId());
        userSetup.setUserStatus(userSetupDTO.getUserStatus());
        userSetup.setGroupId(userSetupDTO.getGroupId());
        userSetup.setIsBadCredential(Boolean.TRUE);
        userSetup.setPasswordExpiryDate(DateUtil.plusDay(currentUser.getServerDate(), PASSWORD_EXPIRY_DAYS));
        userSetup.setCreatedBy(currentUser.getUserName());
        userSetup.setCreatedDate(currentUser.getServerDate());
        return userSetup;
    }

    /**
     * For resetting the password
     *
     * @param userSetup   -- UserSetup
     * @param password    -- String
     * @param currentUser -- CurrentUser
     * @return UserSetup
     */
    private UserSetup convertDTOToEntity(UserSetup userSetup, String password, CurrentUser currentUser) {
        userSetup.setPassword(passwordEncoder.encode(password));
        userSetup.setCreatedBy(currentUser.getUserName());
        userSetup.setCreatedDate(currentUser.getServerDate());
        return userSetup;
    }
    //endregion
}
