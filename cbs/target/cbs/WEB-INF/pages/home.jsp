<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                            <div class="h5 mb-0 font-weight-bold text-gray-800">288</div>
                        </div>
                        <div class="col-auto">
                            <i class="fas fa-calendar fa-2x text-gray-300"></i>
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
                            <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                Total Research Paper Submitted</div>
                            <div class="h5 mb-0 font-weight-bold text-gray-800">215,000</div>
                        </div>
                        <div class="col-auto">
                            <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
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
                            <div class="row no-gutters align-items-center">
                                <div class="col-auto">
                                    <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">50%</div>
                                </div>
                                <div class="col">
                                    <div class="progress progress-sm mr-2">
                                        <div class="progress-bar bg-info" role="progressbar" style="width: 50%" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-auto">
                            <i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
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
                                Pending Requests</div>
                            <div class="h5 mb-0 font-weight-bold text-gray-800">18</div>
                        </div>
                        <div class="col-auto">
                            <i class="fas fa-comments fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="card">
            <div class="card-header">
                Research Papers
            </div>
            <div class="card-body">
                <p class="card-text">
                <table class="table" id="researchTbl">
                    <thead class="thead-dark">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col" hidden>Research Id</th>
                        <th scope="col">Research Topic</th>
                        <th scope="col">File</th>
                        <th scope="col">Word count</th>
                        <th scope="col">Status</th>
                        <th scope="col">Submitted By</th>
                        <th scope="col">Submitted On</th>
                        <th scope="col">Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
                </p>
                <%--            <a href="#" class="btn btn-primary">Go somewhere</a>--%>
            </div>
        </div>

    <!-- Modal -->
    <div class="modal fade" id="reviewerModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
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
                        <label for="researchTopic">Research Topic</label>
<%--                        <input type="text" class="form-control" id="researchTopic">--%>
                        <textarea class="form-control" id="researchTopic" name="rComment" rows="2" readonly></textarea>
                    </div>
                    <div class="form-group">
                        <label for="researchTopic">Submitted By</label>
                        <input type="text" class="form-control" id="name" readonly>
                    </div>
                    <div class="form-group">
                        <label for="rComment">Remarks</label>
                        <textarea class="form-control" id="rComment" name="rComment" rows="15"></textarea>
                    </div>
                    <div class="form-group">
                        <label class="required" for="statusId">Currency</label>
                        <select class="form-control required" id="statusId" name="statusId">
                            <option>--------SELECT----------</option>
                            <option value="2">Reviewed</option>
                            <option value="3">Return</option>
                            <option value="4">Approve</option>
                        </select>
                    </div>
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
                    <button type="button" id="reviwerSubmitBtn" class="btn btn-primary">Submit</button>
                </div>
            </div>
        </div>
    </div>
</security:authorize>
</div>
<script src="<c:url value='/resources/js/db/home.js'/>"></script>
</body>
</html>
