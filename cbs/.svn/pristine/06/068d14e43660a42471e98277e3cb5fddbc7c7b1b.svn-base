package com.springapp.mvc.setup.userLog;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;

/**
 * Created by Dechen  Wangdi on 2/8/2020.
 */

@Entity
@Table(name = "sa_user_log")
public class UserLog {

    //region private variables.
    @Id
    @NotNull
    @Column(name = "LOG_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userLog;

    @NotNull
    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "USER_LOGIN")
    private String userLoginTime;

    @Column(name = "USER_LOGOUT")
    private String userLogoutTime;

    @Column(name = "USER_WORK_STATION")
    private String workStation;
    //endregion

    //region getter and setter.
    public String getUserLog() {
        return userLog;
    }

    public void setUserLog(String userLog) {
        this.userLog = userLog;
    }

    public String getUserLoginTime() {
        return userLoginTime;
    }

    public void setUserLoginTime(String userLoginTime) {
        this.userLoginTime = userLoginTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLogoutTime() {
        return userLogoutTime;
    }

    public void setUserLogoutTime(String userLogoutTime) {
        this.userLogoutTime = userLogoutTime;
    }

    public String getWorkStation() {
        return workStation;
    }

    public void setWorkStation(String workStation) {
        this.workStation = workStation;
    }

    //endregion
}
