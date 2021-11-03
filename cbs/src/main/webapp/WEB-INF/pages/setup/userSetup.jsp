<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 3/19/2020
  Time: 12:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>User Setup</title>
</head>
<body>
<!-- Begin Page Content -->
<div class="container-fluid">
    <security:authorize access="hasAuthority('01-01-002-VIEW')">
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">User Setup</h6>
        </div>
        <div class="card-body">
            <div class="panel panel-default">
                <div class="panel-body">
                    <form id="userSetupForm" method="post" action="<c:url value='/usersetup'/>">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <input type="hidden" name="actionType" value="C" id="actionType"/>

                        <div class="form-group row">
                            <label class="col-md-2 align-right required" for="userName">Username
                                : </label>

                            <div class="col-md-4">
                                <input type="text" name="userName" id="userName"
                                       class="form-control input-sm editable" required>
                            </div>
                            <label class="col-md-2 align-right required" for="employeeId">Employee ID
                                : </label>

                            <div class="col-md-4">
                                <input type="text" name="employeeId" id="employeeId"
                                       class="form-control input-sm editable" required>
                            </div>

                        </div>

                        <div class="form-group row">
                            <label class="col-md-2 align-right required" for="fullName">Full name
                                : </label>

                            <div class="col-md-4">
                                <input type="text" name="fullName" id="fullName"
                                       class="form-control input-sm editable nameOnly"
                                       required>
                            </div>
                            <label class="col-md-2 align-right required" for="emailId">Email ID
                                : </label>

                            <div class="col-md-4">
                                <input type="text" name="emailId" id="emailId"
                                       class="form-control input-sm editable"
                                       required>
                            </div>

                        </div>

                        <div class="form-group row">
                            <label class="col-md-2 align-right required" for="groupId">User
                                group : </label>

                            <div class="col-md-4">
                                <form:select id="groupId"
                                             class="form-control editable" name="groupId"
                                             path="userGroupList" required="true">
                                    <form:option value="">---Please select---</form:option>
                                    <form:options items="${userGroupList}" itemValue="value"
                                                  itemLabel="text"/>
                                </form:select>
                            </div>

                            <label class="col-md-2 align-right required"
                                   for="userStatus">Status</label>

                            <div class="col-md-2">
                                <form:select id="userStatus"
                                             class="form-control input-sm editable"
                                             name="userStatus"
                                             path="statusList">
                                    <form:options items="${statusList}" itemValue="value"
                                                  itemLabel="text"/>
                                </form:select>
                            </div>

                        </div>

                        <div class="form-group ">
                            <input type="reset" id="btnReset" class="btn btn-primary"
                                   value="Reset">
                            <input type="button" id="btnEdit" value="Edit"
                                   class="btn btn-primary"
                                   disabled>
                            <input type="submit" id="btnSave" value="Save"
                                   class="btn btn-primary">
                            <input type="button" id="resetPassword" value="Reset Password"
                                   class="btn btn-primary" disabled>
                            <input type="button" id="btnDelete" value="Delete"
                                   class="btn btn-primary" disabled>
                        </div>
                </form>
            </div>
            <!-- /.col-lg-6 (nested) -->
        </div>
        <!-- /.row (nested) -->
    </div>
    <!-- /.panel-body -->
</div>
    </security:authorize>
    <security:authorize access="hasAuthority('01-01-006-VIEW')">
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">User List</h6>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered css-serial table-responsive-sm table-hover table-success" id="userListTbl" width="100%" cellspacing="0">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Username</th>
                        <th>Full name</th>
                        <th>Employee ID</th>
                        <th>User group</th>
                        <th>Email ID</th>
                        <th>Status</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    </security:authorize>
</div>

</body>
</html>
