<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 3/19/2020
  Time: 10:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!-- Sidebar -->
<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
    id="accordionSidebar">

    <!-- Sidebar - Brand -->
    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="<c:url value="/home"/>">
        <div class="sidebar-brand-icon">
            <i class="glyphicon glyphicon-dashboard"></i>
        </div>

        <div class="sidebar-brand-icon">
<%--            <img src="<c:url value="/resources/startbootstrap/img/logoZRR.jpg"/>">--%>
        </div>

        <div class="sidebar-brand-text mx-3">
<%--            <i class="fa fa-home"></i>&nbsp;--%>
            HOME</div>
    </a>

    <!-- Divider -->
    <hr class="sidebar-divider my-0">

    <!-- Divider -->
    <hr class="sidebar-divider">

    <!-- Nav Item - Pages Collapse Menu -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse"
           data-target="#collapseTwo" aria-expanded="true"
           aria-controls="collapseTwo">
            <i class="fas fa-fw fa-cog"></i>

            <span class="sidebar-brand-text">Admin Panel</span>
        </a>

        <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header text-white">Admin Panel</h6>
                <security:authorize access="hasAuthority('01-01-001-VIEW')">
                    <a class="collapse-item" href="<c:url value="/groupSetup"/>">Group setup</a>
                </security:authorize>
                <security:authorize access="hasAuthority('01-01-002-VIEW')">
                    <a class="collapse-item" href="<c:url value="/usersetup"/>">User setup</a>
                </security:authorize>
                <security:authorize access="hasAuthority('01-01-003-VIEW')">
                    <a class="collapse-item" href="<c:url value="/permissionSetup"/>">Permission setup</a>
                </security:authorize>
                <security:authorize access="hasAuthority('01-01-004-VIEW')">
                    <a class="collapse-item" href="<c:url value="/passwordPolicy"/>">Password policy</a>
                </security:authorize>
                <security:authorize access="hasAuthority('01-01-005-VIEW')">
                    <a class="collapse-item" href="<c:url value="/employeeSetup"/>">Employee setup</a>
                </security:authorize>
            </div>
        </div>
    </li>

    <!-- Nav Item - Utilities Collapse Menu for card -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#card" aria-expanded="true"
           aria-controls="collapseUtilities">
            <i class="fas fa-fw fa-desktop"></i>
            <span class="heading">Researcher Paper</span>
        </a>
        <div id="card" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <div class="sub-item">
<%--                    <h6 class="collapse-header text-white">DFRS Operation</h6>--%>
                    <security:authorize access="hasAuthority('02-01-001-VIEW')">
                        <a class="collapse-item" href="<c:url value="/researchTopic"/>">Research Title</a>
                    </security:authorize>
                    <security:authorize access="hasAuthority('02-01-003-VIEW')">
                        <a class="collapse-item" href="<c:url value="/research"/>">Research Paper</a>
                    </security:authorize>

                </div>
            </div>
        </div>
    </li>


    <!-- Nav Item - Utilities Collapse Menu for card -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#review" aria-expanded="true"
           aria-controls="collapseUtilities">
            <i class="fas fa-fw fa-desktop"></i>
            <span class="heading">Research Review</span>
        </a>
        <div id="review" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <div class="sub-item">
                    <%--                    <h6 class="collapse-header text-white">DFRS Operation</h6>--%>
                    <security:authorize access="hasAuthority('02-01-006-VIEW')">
                        <a class="collapse-item" href="<c:url value="/reviewer"/>">Assign Reviewer</a>
                    </security:authorize>
                    <security:authorize access="hasAuthority('02-01-007-VIEW')">
                        <a class="collapse-item" href="<c:url value="/researchReview"/>">Research Review</a>
                    </security:authorize>
                </div>
            </div>
        </div>
    </li>

    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#approval" aria-expanded="true"
           aria-controls="collapseUtilities">
            <i class="fas fa-fw fa-desktop"></i>
            <span class="heading">Research Approvals</span>
        </a>
        <div id="approval" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <div class="sub-item">
                    <security:authorize access="hasAuthority('02-01-005-VIEW')">
                        <a class="collapse-item" href="<c:url value="/titleApprove"/>">Title Approval</a>
                    </security:authorize>
                </div>
            </div>
        </div>
    </li>


    <!-- Divider -->
    <hr class="sidebar-divider">

    <!-- Sidebar Toggler (Sidebar) -->
    <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
    </div>
</ul>
<!-- End of Sidebar -->