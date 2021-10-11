package com.springapp.mvc.setup.group;

/**
 * Created by hp on 12/20/2019.
 */
public class GroupSetupDTO {
    private Integer groupID;
    private String groupName;
    private String groupStatusName;
    private  int groupStatus;

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

    public String getGroupStatusName() {
        return groupStatusName;
    }

    public void setGroupStatusName(String groupStatusName) {
        this.groupStatusName = groupStatusName;
    }

    public int getGroupStatus() {
        return groupStatus;
    }

    public void setGroupStatus(int groupStatus) {
        this.groupStatus = groupStatus;
    }
}
