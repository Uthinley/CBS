<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 3/19/2020
  Time: 11:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<sec:authentication var="auth" property="principal"/>
<c:url value="/logout" var="logoutUrl"/>

<!-- Topbar -->
<nav class="navbar navbar-expand navbar-light  topbar mb-4 static-top shadow">
    <form action="${logoutUrl}" method="post" id="logoutForm">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>

    <!-- Sidebar Toggle (Topbar) -->
    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
        <i class="fa fa-bars"></i>
    </button>


    <div style="width: 100%;padding-top: 4px;"><a href="<c:url value="/home"/>" style="text-decoration: none" class="text-primary align-center"><h5>བཞི་ཆེན་ཞིབ་མཇུག་མ་ཡིག།</h5>
        <h5>Zhichen Research Registry (ZRR)</h5></a> &nbsp;&nbsp;</div>

    <%--<security:authorize access="hasRole('00-01-001-VIEW')">
        <a href="<c:url value="/home"/>">
            <button>Tabular view</button>
        </a>&nbsp;

        <a href="<c:url value="/dashboard"/>">
            <button id="toggleDashboard">Graphical view</button>
        </a>
    </security:authorize>--%>
    <!-- Topbar Navbar -->
    <ul class="navbar-nav ml-auto">

        <!-- Nav Item - User Information -->
        <li class="nav-item dropdown no-arrow">
            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown"
               aria-haspopup="true" aria-expanded="false">
                <span class="mr-2 d-none d-lg-inline text-gray-900 small heading">${auth.userFullName}</span>
                <i class="fas fa-user fa-3x"></i>
            </a>
            <!-- Dropdown - User Information -->
            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
                <security:authorize access="hasAuthority('01-01-005-VIEW')">
                    <a class="dropdown-item" href="<c:url value="/changePassword"/>">
                        <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                        Change Password
                    </a>
                </security:authorize>


                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="<c:url value="/logout/"/>"><i
                        class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i> Logout</a>
            </div>
        </li>
    </ul>
</nav>
<!-- End of Topbar -->
