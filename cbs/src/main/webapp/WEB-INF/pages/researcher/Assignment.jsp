<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 9/27/2021
  Time: 12:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Researcher</title>
</head>
<body>

<!-- Begin Page Content -->
<div class="container-fluid">
    <!--Intput table card-->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Research Paper Submission</h6>
        </div>
        <div class="card-body">
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <form id="addResearch" method="post" action=""
                                          class="" enctype="multipart/form-data">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                        <input type="hidden" name="actionType" id="actionType" value="C"/>
                                        <input type="text" name="id" id="id" hidden/>

                                        <div class="form-group row">
                                            <label class="align-right col-md-2">Research Topic</label>

                                            <div class="col-md-6">
                                                <input type="text" class="form-control nameOnly" name="researchTopic"
                                                       id="researchTopic" required="true">
                                            </div>
                                        </div>

                                        <div class="form-group row">
                                            <label class="align-right col-md-2">File Upload</label>

                                            <div class="col-md-6">
                                                <input type="file" class="form-control nameOnly" name="file"
                                                       id="file" required="true">
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
            <h6 class="m-0 font-weight-bold text-primary">Research Paper List</h6>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered css-serial" id="researchListTbl" width="100%" cellspacing="0">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Research Topic</th>
                        <th>File</th>
                        <th>Word Count</th>
                        <th>Status</th>
                        <th>Submitted On</th>
                        <th class="d-none"></th>
                        <th hidden>id</th>
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
