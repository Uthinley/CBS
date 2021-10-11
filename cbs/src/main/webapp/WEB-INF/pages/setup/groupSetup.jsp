<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 3/22/2020
  Time: 8:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Group Setup</title>
</head>
<body>
<!-- Begin Page Content -->
<div class="container-fluid">

    <!--Intput table card-->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">User Group Setup</h6>
        </div>
        <div class="card-body">
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <form id="groupSetup" method="post" action=""
                                          class="">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                        <input type="hidden" name="actionType" id="actionType" value="C"/>
                                        <input type="hidden" name="groupID" id="groupId"/>

                                        <div class="form-group row">
                                            <label class="align-right col-md-2">Group Name</label>

                                            <div class="col-md-6">
                                                <input type="text" class="form-control nameOnly" name="groupName"
                                                       id="groupName" required="true">
                                            </div>
                                        </div>

                                        <div class="form-group row">
                                            <label class="align-right col-md-2">Status</label>

                                            <div class="col-md-6">
                                                <form:select id="groupStatus"
                                                             class="form-control editable" name="groupStatus"
                                                             path="statusList">
                                                    <form:options items="${statusList}" itemValue="value"
                                                                  itemLabel="text"/>
                                                </form:select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <input type="reset" id="btnReset" class="btn btn-primary" value="Reset">
                                            <input type="button" id="btnEdit" value="Edit" class="btn btn-primary"
                                                   disabled>
                                            <input type="submit" id="btnSave" value="Save" class="btn btn-primary">
                                            <input type="button" id="btnDelete" value="Delete" class="btn btn-primary"
                                                   disabled>
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
    <!-- DataTales Example -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Group List</h6>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered css-serial" id="groupTableId" width="100%" cellspacing="0">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>ID</th>
                        <th>Group Name</th>
                        <th class="d-none"></th>
                        <th>Status</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

</div>
<!-- /.container-fluid -->
</body>
</html>
