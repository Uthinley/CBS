<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 11/9/2021
  Time: 3:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employee Setup</title>
</head>
<body>


<div class="container-fluid">
    <security:authorize access="hasAuthority('01-01-002-VIEW')">
        <div class="card shadow mb-4">
            <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary">Employee Setup</h6>
            </div>
            <div class="card-body">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <form id="userSetupForm" method="post" action="<c:url value='/employeeSetup'/>">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <input type="hidden" name="actionType" value="C" id="actionType"/>

                            <div class="form-group row">
                                <label class="col-md-2 align-right required" for="employeeId">Employee ID
                                    : </label>

                                <div class="col-md-4">
                                    <input type="text" name="employeeId" id="employeeId"
                                           class="form-control input-sm editable" required>
                                </div>
                                <label class="col-md-2 align-right" for="cid">CID
                                    : </label>

                                <div class="col-md-4">
                                    <input type="text" name="cid" id="cid"
                                           class="form-control input-sm editable">
                                </div>

                            </div>

                            <div class="form-group row">
                                <label class="col-md-2 align-right required" for="firstName">First name
                                    : </label>

                                <div class="col-md-2">
                                    <input type="text" name="firstName" id="firstName"
                                           class="form-control input-sm editable nameOnly"
                                           required>
                                </div>
                                <label class="col-md-2 align-right " for="secondName">Middle Name
                                    : </label>

                                <div class="col-md-2">
                                    <input type="text" name="secondName" id="secondName"
                                           class="form-control input-sm editable"
                                           >
                                </div>
                                <label class="col-md-2 align-right" for="lastName">Last Name
                                    : </label>

                                <div class="col-md-2">
                                    <input type="text" name="lastName" id="lastName"
                                           class="form-control input-sm editable"
                                           >
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-md-2 align-right required" for="positionTitle">Position title
                                    : </label>

                                <div class="col-md-4">
                                    <input type="text" name="positionTitle" id="positionTitle"
                                           class="form-control input-sm editable" required>
                                </div>
                                <label class="col-md-2 align-right required" for="positionLevel">Position level
                                    : </label>

                                <div class="col-md-4">
                                    <input type="text" name="positionLevel" id="positionLevel"
                                           class="form-control input-sm editable" required>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label class="col-md-2 align-right required" for="agency">Agency
                                    : </label>

                                <div class="col-md-4">
                                    <input type="text" name="agency" id="agency"
                                           class="form-control input-sm editable" required>
                                </div>
                                <label class="col-md-2 align-right required" for="positionLevel">Division
                                    : </label>

                                <div class="col-md-4">
                                    <input type="text" name="division" id="division"
                                           class="form-control input-sm editable" required>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label class="col-md-2 align-right required" for="phoneNumber">Phone Number
                                    : </label>

                                <div class="col-md-4">
                                    <input type="text" name="phoneNumber" id="phoneNumber"
                                           class="form-control input-sm editable" required>
                                </div>
                                <label class="col-md-2 align-right required" for="email">Email
                                    : </label>

                                <div class="col-md-4">
                                    <input type="text" name="email" id="email"
                                           class="form-control input-sm editable" required>
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
                <h6 class="m-0 font-weight-bold text-primary">Employee List</h6>
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-bordered css-serial table-responsive-sm table-hover table-success" id="userListTbl" width="100%" cellspacing="0">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>Employee ID</th>
                            <th>CID</th>
                            <th>Full name</th>
                            <th>Position title</th>
                            <th>Position level</th>
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
