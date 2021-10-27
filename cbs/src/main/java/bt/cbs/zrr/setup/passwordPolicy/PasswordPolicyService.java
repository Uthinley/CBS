package bt.cbs.zrr.setup.passwordPolicy;

import bt.cbs.zrr.global.base.BaseService;
import bt.cbs.zrr.global.dto.CurrentUser;
import bt.cbs.zrr.global.dto.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by Dechen Wangdi on 12/20/2019.
 */

@Service
public class PasswordPolicyService extends BaseService {

    private static final int PASS_ID = 1;

    private static final int PASS_LENGTH = 8;
    private static final String PASS_REGEX = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";


    //region private variables.
    @Autowired
    private PasswordPolicyDao passwordPolicyDao;
    //endregion

    //region public methods.
    public ResponseMessage save(PasswordPolicyDTO passwordPolicyDTO, CurrentUser currentUser) throws Exception {
        PasswordPolicy passwordPolicy = passwordPolicyDTO.convertToEntity(passwordPolicyDTO, currentUser);
        passwordPolicy.setPassId(PASS_ID);
        passwordPolicyDao.save(passwordPolicy);
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Password policy saved successfully.");
        return responseMessage;
    }

    public PasswordPolicyDTO getPasswordPolicyInfo() {
        return passwordPolicyDao.getPasswordPolicyInfo();
    }

    public ResponseMessage isPasswordValid(String password) {
        PasswordPolicyDTO passwordPolicyDTO = passwordPolicyDao.getPasswordPolicyInfo();
        if (!StringUtils.isEmpty(passwordPolicyDTO)) {
            if (passwordPolicyDTO.getIsMaxLength() && password.trim().length() < PASS_LENGTH) {
                responseMessage.setStatus(UNSUCCESSFUL_STATUS);
                responseMessage.setText("Password length should be minimum of 8 characters.");
                return responseMessage;
            }

            if (passwordPolicyDTO.getIsSpecialChar() && !password.matches(PASS_REGEX)) {
                responseMessage.setStatus(UNSUCCESSFUL_STATUS);
                responseMessage.setText("Password should contain at least a digit,lower and upper case letter," +
                        "and a special character.");
                return responseMessage;
            }
        }
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        return responseMessage;
    }
    //endregion
}
