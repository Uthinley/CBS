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
    <title>Permission Setup</title>
</head>
<body>
<!-- Begin Page Content -->
<div class="container-fluid">

    <form id="permissionSetupForm" method="post" action="<c:url value='/permissionSetup/'/>"
          class="form-horizontal userSetupForm">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <!-- Page Heading -->
        <h1 class="h3 mb-2 text-gray-800">Permission Setup</h1>

        <!--Intput table card-->
        <div class="card shadow mb-4">
            <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary">Permission Setup Form</h6>
            </div>
            <div class="card-body">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-lg-12">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label>User group</label>
                                                    <form:select id="groupId"
                                                                 class="form-control editable" name="groupId"
                                                                 path="userGroupList" required="true">
                                                        <form:option value="">---Please select---</form:option>
                                                        <form:options items="${userGroupList}" itemValue="value"
                                                                      itemLabel="text"/>
                                                    </form:select>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label>Screen Type</label>
                                                    <form:select id="screenType"
                                                                 class="form-control editable"
                                                                 path="screenTypeList">
                                                        <form:option value="">---Please select---</form:option>
                                                        <form:options items="${screenTypeList}" itemValue="value"
                                                                      itemLabel="text"/>
                                                    </form:select>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label>Screen</label>
                                                    <form:select id="screenList"
                                                                 class="form-control editable"
                                                                 path="screenList" multiple="true">
                                                        <form:option value="">---Please select---</form:option>
                                                        <form:options items="${screenList}" itemValue="value"
                                                                      itemLabel="text"/>
                                                    </form:select>
                                                </div>
                                                <div class="form-group">
                                                    <input type="button" id="addP" value="ADD" class="btn-primary"/>
                                                </div>
                                            </div>
                                        </div>
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
        <!-- DataTales Example -->
        <div class="card shadow mb-4">
            <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary">Permission Detail</h6>
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table id="permissionListTbl" class="col-md-12 table css-serial">
                        <thead>
                        <tr>
                            <th class="align-center">#</th>
                            <th class="align-left">Screen ID</th>
                            <th class="align-left">Screen name</th>
                            <th class="align-center">View</th>
                            <th class="align-center">Edit</th>
                            <th class="align-center">Save</th>
                            <th class="align-center">Delete</th>
                            <th class="align-center"></th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <div class="form-group">
            <%--<div class="col-md-2">--%>
            <input type="reset" id="btnReset" class="btn btn-primary"
                   value="Reset">
            <%--</div>--%>
            <security:authorize access="hasAuthority('${SCREEN_ID}-EDIT')">
                <%--<div class="col-md-2">--%>
                <input type="button" id="btnEdit" value="Edit"
                       class="btn btn-primary ">
                <%--</div>--%>
            </security:authorize>
            <security:authorize access="hasAuthority('${SCREEN_ID}-ADD')">
                <%--<div class="col-md-2">--%>
                <input type="submit" id="btnSave" value="Save"
                       class="btn btn-primary">
                <%--</div>--%>
            </security:authorize>
            <security:authorize access="hasAuthority('${SCREEN_ID}-DELETE')">
                <%--<div class="col-md-2">--%>
                <input type="button" id="btnDelete" value="Delete"
                       class="btn btn-primary"
                       disabled>
                <%--</div>--%>
            </security:authorize>
        </div>
    </form>
</div>
<!-- /.container-fluid -->

</body>
</html>

