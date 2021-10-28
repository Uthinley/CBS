package bt.cbs.zrr.setup.passwordPolicy;

import bt.cbs.zrr.global.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by sangay Tenzin on 12/20/2019.
 */

@Entity
@Table(name = "sa_password_policy")
public class PasswordPolicy extends BaseEntity implements Serializable {

    //region private variables.
    @Id
    @Column(name = "PASS_ID")
    private int passId;

    @Column(name = "PASS_LENGTH")
    private Boolean isMaxLength;

    @Column(name = "PASS_VALID")
    private Boolean isExpiryDays;

    @Column(name = "PASS_SPECIAL")
    private Boolean isSpecialChar;

    @Column(name = "PASS_ENFORCE")
    private Boolean isEnforcePasswordChange;
    //endregion

    //region getter and setter.
    public int getPassId() {
        return passId;
    }

    public void setPassId(int passId) {
        this.passId = passId;
    }

    public Boolean getIsMaxLength() {
        return isMaxLength;
    }

    public void setIsMaxLength(Boolean isMaxLength) {
        this.isMaxLength = isMaxLength;
    }

    public Boolean getIsExpiryDays() {
        return isExpiryDays;
    }

    public void setIsExpiryDays(Boolean isExpiryDays) {
        this.isExpiryDays = isExpiryDays;
    }

    public Boolean getIsSpecialChar() {
        return isSpecialChar;
    }

    public void setIsSpecialChar(Boolean isSpecialChar) {
        this.isSpecialChar = isSpecialChar;
    }

    public Boolean getIsEnforcePasswordChange() {
        return isEnforcePasswordChange;
    }

    public void setIsEnforcePasswordChange(Boolean isEnforcePasswordChange) {
        this.isEnforcePasswordChange = isEnforcePasswordChange;
    }

    //endregion
}

