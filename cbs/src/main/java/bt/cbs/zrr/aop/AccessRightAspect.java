package bt.cbs.zrr.aop;

import org.aspectj.lang.JoinPoint;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AccessRightAspect {

    public void checkAccessRight(JoinPoint joinPoint) throws IOException {
        Object[] parameterList = joinPoint.getArgs();
        HttpServletRequest request = (HttpServletRequest) parameterList[0];
        SecurityContextHolderAwareRequestWrapper wrapper = (SecurityContextHolderAwareRequestWrapper) parameterList[2];

        /*CurrentUser currentUser = (CurrentUser) request.getSession().getAttribute("currentUser");

        if (wrapper.isUserInRole(currentUser.getScreenID() + "-ADD")) {
            HttpServletResponse response = (HttpServletResponse) parameterList[1];
            //response.sendRedirect("/403");
            response.sendRedirect("/dit/403");
        }*/
    }
}
