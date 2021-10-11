package com.springapp.mvc.setup.passwordPolicy;

import com.springapp.mvc.global.dto.CurrentUser;
import org.springframework.util.StringUtils;

/**
 * Created by sangay Tenzin on 12/20/2019.
 */
public class PasswordPolicyDTO {

    //region private variables.
    private Boolean isMaxLength;
    private Boolean isEnforcePasswordChange;
    private Boolean isExpiryDaysEnable;
    private Boolean isSpecialChar;
    //endregion

    //region getter and setter.

    public Boolean getIsMaxLength() {
        return isMaxLength;
    }

    public void setIsMaxLength(Boolean isMaxLength) {
        this.isMaxLength = isMaxLength;
    }

    public Boolean getIsEnforcePasswordChange() {
        return isEnforcePasswordChange;
    }

    public void setIsEnforcePasswordChange(Boolean isEnforcePasswordChange) {
        this.isEnforcePasswordChange = isEnforcePasswordChange;
    }

    public Boolean getIsExpiryDaysEnable() {
        return isExpiryDaysEnable;
    }

    public void setIsExpiryDaysEnable(Boolean isExpiryDaysEnable) {
        this.isExpiryDaysEnable = isExpiryDaysEnable;
    }

    public Boolean getIsSpecialChar() {
        return isSpecialChar;
    }

    public void setIsSpecialChar(Boolean isSpecialChar) {
        this.isSpecialChar = isSpecialChar;
    }

    //endregion

    public PasswordPolicy convertToEntity(PasswordPolicyDTO passwordPolicyDTO, CurrentUser currentUser) {
        PasswordPolicy passwordPolicy = new PasswordPolicy();
        passwordPolicy.setIsMaxLength(StringUtils.isEmpty(passwordPolicyDTO.getIsMaxLength()) ? Boolean.FALSE : Boolean.TRUE);
        passwordPolicy.setIsEnforcePasswordChange(StringUtils.isEmpty(passwordPolicyDTO.getIsEnforcePasswordChange()) ? Boolean.FALSE : Boolean.TRUE);
        passwordPolicy.setIsExpiryDays(StringUtils.isEmpty(passwordPolicyDTO.getIsExpiryDaysEnable()) ? Boolean.FALSE : Boolean.TRUE);
        passwordPolicy.setIsSpecialChar(StringUtils.isEmpty(passwordPolicyDTO.getIsSpecialChar()) ? Boolean.FALSE : Boolean.TRUE);
        passwordPolicy.setCreatedBy(currentUser.getUserName());
        passwordPolicy.setCreatedDate(currentUser.getServerDate());
        return passwordPolicy;
    }
}

