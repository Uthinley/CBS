package com.springapp.mvc.setup.permission;

import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * ====================================================================
 * Created by Dechen Wangdi on 20/12/2019.
 * Description:
 * ====================================================================
 * Modified by:
 * Modified date:
 * Purpose:
 * ====================================================================
 */
public class PermissionListDTO {
    private BigDecimal permissionId;
    private Integer groupId;
    private String screenId;
    @Size(max=1)
    private Character isView;
    @Size(max=1)
    private Character isEdit;
    @Size(max=1)
    private Character isSave;
    @Size(max=1)
    private Character isDelete;

    private String screenName;

    public BigDecimal getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(BigDecimal permissionId) {
        this.permissionId = permissionId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getScreenId() {
        return screenId;
    }

    public void setScreenId(String screenId) {
        this.screenId = screenId;
    }

    public Character getIsView() {
        return isView;
    }

    public void setIsView(Character isView) {
        this.isView = isView;
    }

    public Character getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Character isEdit) {
        this.isEdit = isEdit;
    }

    public Character getIsSave() {
        return isSave;
    }

    public void setIsSave(Character isSave) {
        this.isSave = isSave;
    }

    public Character getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Character isDelete) {
        this.isDelete = isDelete;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PermissionListDTO that = (PermissionListDTO) o;

        if (!groupId.equals(that.groupId)) return false;
        if (!screenId.equals(that.screenId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = groupId.hashCode();
        result = 31 * result + screenId.hashCode();
        return result;
    }
}
