<%@ page import="bt.cbs.zrr.global.enumeration.ApplicationStatusCode" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 9/27/2021
  Time: 12:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Research Topic</title>
</head>
<body>
<div class="container-fluid dtree">
    <div class="card shadow mb-3">
        <div class="card-header py-3">
            <h6 class="mb-0 text-gray-800">Research Title Approval</h6>
        </div>
        <div class="card-body">
            <div class="tab-content">
                <div class="tab-pane active" id="form" role="tabpanel" aria-labelledby="form-tab">
                    <form id="searchForm" method="GET" action="" class="row align-items-center" >
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                        <div class="col-lg-12">
                            <div class="form-group row">

                            </div>
                            <div class="form-group row">

                                <label class="align-right col-md-2 required">Research Month</label>

                                <div class="col-md-3">
                                    <input type="month" class="form-control " name="research_month"
                                           id="research_month" required="true" placeholder="Month" value="<%=new SimpleDateFormat("YYYY-MM").format(new Date())%>">
                                </div>

                            </div>

                            <div class="form-group row">
                                <label class="align-right col-md-2 required">Status</label>

                                <div class="col-md-2">
                                    <select  class="form-control-sm" name="status">
                                        <option value="ALL">ALL</option>
                                        <option value="<%=ApplicationStatusCode.SUBMITTED.getValue()%>">Submitted</option>
                                        <option value="<%=ApplicationStatusCode.APPROVED.getValue()%>">Approved</option>
                                        <option value="<%=ApplicationStatusCode.RETURNED.getValue()%>">Returned</option>
                                        <option value="<%=ApplicationStatusCode.RE_SUBMITTED.getValue()%>">Re-Submitted</option>
                                    </select>
                                </div>
                                <div class="col-md-2">
                                    <input type="submit" id="btnSearch" value="Search" class="btn btn-primary">
                                </div>
                            </div>
                        </div>
                    </form>
                    <form id="titleApprovalForm" method="post" action="" class="row align-items-center" >
                        <input id="csrf" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <input type="hidden" name="actionType" id="actionType" value="C"/>

                            <div class="col-lg-12" >
                                <table id="research_title_list" class="table table-responsive-sm css-serial">
                                    <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Month</th>
                                        <th>Research Topic</th>
                                        <th>Status</th>
                                        <th>Approver's Comment</th>
                                        <th>Owner</th>
                                        <th>Proposed On</th>
                                        <th>Action</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                </table>
                                <div class="form-group row">
                                    <label class="align-right col-md-2 required">Approver Remarks</label>

                                    <div class="col-md-8">
                                        <textarea class="form-control" name="remarks" id="remarks" required="true"
                                                  rows="5" placeholder="Enter the approvers comments/remarks if any.."></textarea>
                                    </div>
                                </div>
                            </div>


                            <div class="col-lg-12 form-group row">
                                <input type="reset" id="btnReset" class="btn btn-primary offset-3" value="Reset">
                                <input type="button" id="btnEdit" value="Edit" class="btn btn-primary" disabled>
                                <input type="button" id="btnApprove" value="Approve" class="btn btn-primary">
                                <input type="button" id="btnReturn" value="Returned" class="btn btn-primary">
                            </div>
                    </form>
                </div>

            </div>

        </div>
    </div>

</div>

<script src="<c:url value='/resources/js/cbs/titleApprove.js'/>"></script>

</body>
</html>
