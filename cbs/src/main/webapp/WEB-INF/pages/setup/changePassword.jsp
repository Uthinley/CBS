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
    <title>Change Password</title>
</head>
<body>
<!-- Begin Page Content -->
<div class="container-fluid">
    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">Change Password</h1>

    <!--Intput table card-->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Change Password</h6>
        </div>
        <div class="card-body">
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <form id="changePasswordForm" method="post"
                                          action="<c:url value='/changePassword'/>">

                                        <div class="form-group row">
                                            <label class="align-right required col-lg-2" for="userName">User Name
                                                : </label>

                                            <div class="col-md-6">
                                                <input type="text" name="userName" id="userName"
                                                       class="form-control"
                                                       value="${username}" readonly required>
                                            </div>
                                        </div>


                                        <div class="form-group row">
                                            <label class="align-right required col-lg-2" for="password">Password:</label>

                                            <div class="col-md-6">
                                                <input type="password" name="password" id="password"
                                                       class="form-control"
                                                       autocomplete="off" required>
                                            </div>
                                        </div>

                                        <div class="form-group row">
                                            <label class="align-right required col-lg-2" for="newPassword">New
                                                Password</label>

                                            <div class="col-md-6">
                                                <input type="password" name="newPassword" id="newPassword"
                                                       class="form-control editable"
                                                       autocomplete="off" required>
                                            </div>
                                        </div>

                                        <div class="form-group row">
                                            <label class="align-right required col-lg-2" for="confirmPassword">Confirm
                                                Password
                                                : </label>

                                            <div class="col-md-6">
                                                <input type="password" name="confirmPassword"
                                                       id="confirmPassword"
                                                       class="form-control editable"
                                                       autocomplete="off" required>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <input type="reset" id="btnReset" class="btn btn-primary"
                                                   value="Reset">

                                            <security:authorize access="hasAuthority('${SCREEN_ID}-ADD')">
                                                <input type="submit" id="btnSave" value="Save"
                                                       class="btn btn-primary  editable">
                                            </security:authorize>
                                        </div>
                                    </form>
                                </div>
                                <!-- /.col-lg-6 (nested) -->
                            </div>
                            <!-- /.row (nested) -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
        </div>
    </div>

</div>
<!-- /.container-fluid -->

</body>
</html>



