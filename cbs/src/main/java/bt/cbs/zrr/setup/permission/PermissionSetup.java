package bt.cbs.zrr.setup.permission;

import bt.cbs.zrr.global.base.BaseEntity;
import com.sun.istack.internal.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
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

@Entity
@Table(name = "sa_user_group_wise_permission")
public class PermissionSetup extends BaseEntity implements Serializable{
    //region private column fields
    @Id
    @NotNull
    @Column(name = "PERMISSIONID")
    private BigDecimal permissionId;

    @NotNull
    @Column(name = "GROUP_ID")
    private Integer groupId;

    @NotNull
    @Column(name = "SCREENID")
    private String screenId;

    @Column(name = "ISVIEW",length=1)
    private String isView;

    @Column(name = "ISSAVE",length=1)
    private String isSave;

    @Column(name = "ISEDIT",length=1)
    private String isEdit;

    @Column(name = "ISDELETE",length=1)
    private String isDelete;

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

    public String getIsView() {
        return isView;
    }

    public void setIsView(String isView) {
        this.isView = isView;
    }

    public String getIsSave() {
        return isSave;
    }

    public void setIsSave(String isSave) {
        this.isSave = isSave;
    }

    public String getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(String isEdit) {
        this.isEdit = isEdit;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }
}
