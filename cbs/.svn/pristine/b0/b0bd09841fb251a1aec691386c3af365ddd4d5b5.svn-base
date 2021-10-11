package com.springapp.mvc.global.base;

import com.springapp.mvc.global.dto.CurrentUser;
import com.springapp.mvc.global.dto.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ====================================================================
 * Created by Dechen Wangdi on 05/12/2019.
 * Description:
 * ====================================================================
 */
public class BaseController {
    protected CurrentUser currentUser;
    @Autowired
    protected ResponseMessage responseMessage;


    public void setCurrentUser(CurrentUser currentUser) {
        this.currentUser = currentUser;
    }

    public void setResponseMessage(ResponseMessage responseMessage) {
        this.responseMessage = responseMessage;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);

        // true passed to CustomDateEditor constructor means convert empty String to null
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    protected CurrentUser getCurrentUser(HttpServletRequest request) {
        //if (currentUser == null) {
        currentUser = (CurrentUser) request.getSession().getAttribute("currentUser");
        currentUser.setServerDate(new Date());
        //}
        return currentUser;
    }

    protected String getReportSourcePath(HttpServletRequest request) {
        String path = request.getSession().getServletContext().getRealPath("/WEB-INF/classes/jreport/");
        return path.replace("\\", "//");
    }

    protected String getReportOutputPath(HttpServletRequest request) {
        return request.getSession().getServletContext().getRealPath("/resources/reports");
    }

    public String unmask(String maskedValue) {
        return String.join("", maskedValue.split("\\."));
    }

    //TODO for System Date
    public String getDateFormat(Date date) {
        return new SimpleDateFormat("dd-MMM-yyyy").format(date);
    }

}
