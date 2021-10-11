package com.springapp.mvc.setup.group;

import com.springapp.mvc.global.base.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by hp on 12/26/2019.
 */
@Entity
@Table(name = "sa_user_group_a")
public class GroupSetup_A extends BaseEntity implements Serializable {
    @Id
    @Column(name = "AUDIT_SERIAL")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer auditSerial;

    @Column(name = "GR_ID")
    private Integer groupID;

    @Column(name = "GR_NAME")
    private String groupName;

    @Column(name = "GR_STATUS")
    private  int groupStatus;

    @Column(name = "CMD_FLAG")
    private Character cmdFlag;

    public Integer getAuditSerial() {
        return auditSerial;
    }

    public void setAuditSerial(Integer auditSerial) {
        this.auditSerial = auditSerial;
    }

    public Integer getGroupID() {
        return groupID;
    }

    public void setGroupID(Integer groupID) {
        this.groupID = groupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getGroupStatus() {
        return groupStatus;
    }

    public void setGroupStatus(int groupStatus) {
        this.groupStatus = groupStatus;
    }

    public Character getCmdFlag() {
        return cmdFlag;
    }

    public void setCmdFlag(Character cmdFlag) {
        this.cmdFlag = cmdFlag;
    }
}
