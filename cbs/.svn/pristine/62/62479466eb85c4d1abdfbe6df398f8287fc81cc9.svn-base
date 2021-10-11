package com.springapp.mvc.setup.userLog;

import com.springapp.mvc.auth.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Dechen Wangdi on 2/8/2020.
 */

@Service
public class UserLogService {

    @Autowired
    private UserLogDao userLogDao;
    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d MMM, yyyy - h:mm a");

    public void save(HttpServletRequest request, LoginDTO loginDTO) {

        LocalDateTime now = LocalDateTime.now();
        UserLog userLog = new UserLog();
        userLog.setUserName(loginDTO.getUserName());
        userLog.setUserName(loginDTO.getUserName());
        userLog.setUserLoginTime(dateFormat.format(now));
        userLog.setUserLogoutTime(null);
        userLog.setWorkStation(request.getRemoteAddr());
        userLogDao.save(userLog);
    }

    public void update(HttpServletRequest request) {
        LoginDTO loginDTO = (LoginDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        LocalDateTime now = LocalDateTime.now();
        String userName = loginDTO.getUserName();
        Integer logID = userLogDao.getLogID(userName);
        userLogDao.update(userName, logID, dateFormat.format(now));
    }
}
