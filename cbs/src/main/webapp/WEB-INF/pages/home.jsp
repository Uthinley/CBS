<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: RMA
  Date: 3/23/2020
  Time: 4:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<sec:authentication var="auth" property="principal"/>
<c:url value="/logout" var="logoutUrl"/>

<form action="${logoutUrl}" method="post" id="logoutForm">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
<html>
<head>
    <title>Dashboard</title>
</head>
<body>
<!-- Begin Page Content -->
<div class="container-fluid">
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">Dashboard</h1>
        <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i class="fas fa-download fa-sm text-white-50"></i> Generate Report</a>
    </div>
<security:authorize access="hasAuthority('00-01-001-VIEW')">
    <div class="row">
        <!-- Earnings (Monthly) Card Example -->
        <div class="col-xl-3 col-md-6 mb-4">
            <div class="card border-left-primary shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                Total Researcher</div>
                            <div class="h5 mb-0 font-weight-bold text-gray-800"><c:out value="${summaryReport.obj1}"></c:out></div>
                        </div>
                        <div class="col-auto">
                            <i class="fa fa-user fa-2x text-gray-300"></i>
                        </div>
                    </div>
                    <div class="row no-gutters align-items-center" id="totalR">
                        <div class="col mr-2">
                        </div>
                        <div class="col-auto">
                            <button id="totalResearcherDtls" class="btn text-primary">See details <i class="fa fa-arrow-right" aria-hidden="true"></i></button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Earnings (Monthly) Card Example -->
        <div class="col-xl-3 col-md-6 mb-4">
            <div class="card border-left-success shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="text-xl font-weight-bold text-success mb-1">
                                Total Research Paper Received as of
                                <p><span id="currentDate"></span> for the month of <span id="currentMonth"></span></p></div>
                            <div class="h5 mb-0 font-weight-bold text-gray-800"><c:out value="${summaryReport.obj2}"></c:out></div>
                        </div>
                        <div class="col-auto">
                            <i class="fa fa-file fa-2x text-gray-300"></i>
                        </div>
                    </div>
                    <div class="row no-gutters align-items-center" id="submittedR">
                        <div class="col mr-2">
                        </div>
                        <div class="col-auto">
                            <button id="sResearch" class="btn text-primary">See details <i class="fa fa-arrow-right" aria-hidden="true"></i></button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Earnings (Monthly) Card Example -->
        <div class="col-xl-3 col-md-6 mb-4">
            <div class="card border-left-info shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="text-xs font-weight-bold text-info text-uppercase mb-1">Total Research Paper Reviewed
                            </div>
                            <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800"><c:out value="${summaryReport.obj3}"></c:out></div>
                            <div class="row no-gutters align-items-center">
                                <div class="col-auto">
                                    <fmt:formatNumber type = "number" var="val1" value="${summaryReport.obj4}" maxFractionDigits="0"/>
                                    <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800"><c:out value="${val1}"></c:out> %</div>
                                </div>
                                <div class="col">
                                    <div class="progress progress-sm mr-2">
                                        <div class="progress-bar bg-info" role="progressbar" style="width: <c:out value="${summaryReport.obj4}"></c:out>%" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-auto">
                            <i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
                        </div>
                    </div>
                    <div class="row no-gutters align-items-center" id="reviewedR">
                        <div class="col mr-2">
                        </div>
                        <div class="col-auto">
                            <button id="reviewedRBtn" class="btn text-info">See details <i class="fa fa-arrow-right" aria-hidden="true"></i></button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Pending Requests Card Example -->
        <div class="col-xl-3 col-md-6 mb-4">
            <div class="card border-left-warning shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">
                                Research Paper Returned</div>
                            <div class="h5 mb-0 font-weight-bold text-gray-800">0</div>
                        </div>
                        <div class="col-auto">
                            <i class="fas fa-comments fa-2x text-gray-300"></i>
                        </div>
                    </div>
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                        </div>
                        <div class="col-auto">
                            <button class="btn text-warning">See details <i class="fa fa-arrow-right" aria-hidden="true"></i></button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal -->
    <div class="modal fade bd-example-modal-lg" id="reviewerModal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header bg-primary text-white">
                    <h5 class="modal-title" id="exampleModalLongTitle">Reviewer Comment:</h5>
                    <button type="button" class="close text-white" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <input type="text" class="form-control" id="researchId" hidden>
                    <div class="form-group">
                        <label for="researchTopic">Research Title</label>
<%--                        <input type="text" class="form-control" id="researchTopic">--%>
                        <textarea class="form-control" id="researchTopic" name="rComment" rows="2" readonly></textarea>
                    </div>
                    <div class="form-group">
                        <label for="researchTopic">Author</label>
                        <input type="text" class="form-control" id="name" readonly>
                    </div>
                    <div class="form-group">
                        <label for="rComment">Reviewer comments</label>
                        <textarea class="form-control" id="rComment" name="rComment" rows="12"></textarea>
                    </div>
                    <%--TODO-- for the different status--%>
<%--                    <div class="form-group">--%>
<%--                        <label class="required" for="statusId">Status</label>--%>
<%--                        <select class="form-control required" id="statusId" name="statusId">--%>
<%--                            <option>--------SELECT----------</option>--%>
<%--                            <option value="2">Reviewed</option>--%>
<%--                            <option value="3">Return</option>--%>
<%--                            <option value="4">Approve</option>--%>
<%--                        </select>--%>
<%--                    </div>--%>
<%--                    <div class="form-group">--%>
<%--                        <label for="aStatus">Status</label>--%>
<%--                        <form:select id="aStatus"--%>
<%--                                     class="form-control editable" name="aStatus"--%>
<%--                                     path="applicationStatusCode">--%>
<%--                            <form:options items="${applicationStatusCode}" itemValue="value"--%>
<%--                                          itemLabel="text"/>--%>
<%--                        </form:select>--%>
<%--                    </div>--%>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="button" id="reviwerSubmitBtn" class="btn btn-primary">Post</button>
                </div>
            </div>
        </div>
    </div>
    <div id="totalResearcherSection" hidden>
        <jsp:include page="researcher/totalResearcher.jsp"></jsp:include>
    </div>
    <div id="submittedResearchSection" hidden>
        <jsp:include page="researcher/submittedResearch.jsp"></jsp:include>
    </div>
    <div id="reviewedResearchSection" hidden>
        <jsp:include page="researcher/reviewedResearch.jsp"></jsp:include>
    </div>
</security:authorize>
</div>
<script src="<c:url value='/resources/js/db/home.js'/>"></script>
</body>
</html>
