<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 25/07/2018
  Time: 04:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<meta name="decorator" content="/layout/layout-login.jsp"/>
<html>
<head>
    <title></title>
</head>
<body>
<div class="container">
    <div id="login-box" class="col-md-6 row d-flex justify-content-center align-items-center">
        <div class="header col-md-12 align-center text-primary">
            <h2>བཞི་ཆེན་ཞིབ་མཇུག་མ་ཡིག།</h2>
            <h5>Zhichen Research Registry</h5>
<%--            <hr>--%>
<%--          <p style="text-align: center;font-size: small">Royal Monetary Authority of Bhutan</p>--%>
        </div>
        <div class="col-md-4 img">
            <img width="30%" height="30%" src="<c:url value="/resources/startbootstrap/img/logoZRR.jpg"/>"/>
        </div>
        <div id="login-column" class="col-md-8">
            <form id="login-form" class="form-horizontal" action="<c:url value='/auth'/>" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <br>
                <h6 class="text-center loginHeader">Login</h6>

                <div class="form-group">
                    <label for="password" class="text-info">Username:</label><br>
                    <input type="text" name="username" autofocus="true" class="form-control"
                           tabindex="1" required="true" autocomplete="off" placeholder="Enter Your Username"/>
                </div>
                <div class="form-group">
                    <label for="password" class="text-info">Password:</label><br>
                    <input type="password" id="password" name="password" class="form-control tabable" tabindex="2"
                           required="true" autocomplete="off" placeholder="Enter Your Password"/>
                </div>
                <div class="form-group">
                    <label for="remember-me" class="text-info"><span>Remember me</span> <span><input
                            id="remember-me"
                            name="remember-me"
                            type="checkbox"></span></label><br>
                </div>

                <div class="form-group">
                    <div class="col-md-12 animate5 bounceIn">
                        <input type="submit" class="btn btn-primary btn-block tabable" tabindex="4"
                               value="LOGIN" style="color: #fff;"/>
                    </div>
                </div>

                <div class="form-group" style="color:red">
                    <c:if test="${not empty error}">
                        <label class="form-control error"><spring:message code="${error}"/></label>
                    </c:if>
                </div>
            </form>

        </div>
        <%--<div class="col-md-12 disclaimer">
            <h6>Confidentiality</h6>
            <p>
                "Information in this system is strictly confidential and should not be reproduced or distributed to any
                third party without prior consent of the management"
            </p>
        </div>--%>
    </div>
</div>
</body>
</html>
