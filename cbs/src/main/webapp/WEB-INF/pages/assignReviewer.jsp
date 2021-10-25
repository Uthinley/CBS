<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 10/25/2021
  Time: 9:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Assign Reviewer</title>
</head>
<body>

<!-- Begin Page Content -->
<div class="container-fluid">
    <!--Intput table card-->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Assign Reviewer</h6>
        </div>
        <div class="card-body">
            <di class="row align-items-center">
                <div class="col-lg-4">
                    <div class="input-group md-form form-sm form-2 pl-0 search">
                            <form:select id="monthId"
                                         class="form-control editable" name="monthId"
                                         path="researchMonthList" required="true">
                                <form:option value="">---Please select---</form:option>
                                <form:options items="${researchMonthList}" itemValue="value"
                                              itemLabel="text"/>
                            </form:select>
                        <div class="input-group-append">
                             <button class="input-group-text bg-info text-white lighten-3" id="searchBtn">
                                 <i class="fas fa-search text-grey" aria-hidden="true"></i></button>
                        </div>
                    </div>
                </div>
            </di>
            <br>
            <div class="row align-items-center">
                <div class="col-lg">
                    <div class="table-responsive">
                        <table class="table table-bordered css-serial" id="researchListTbl" width="100%" cellspacing="0">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th>Research No</th>
<%--                                <th>File</th>--%>
<%--                                <th>Word Count</th>--%>
                                <th>Status</th>
                                <th>Received On</th>
                                <th>Action</th>
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
    </div>
    <!-- DataTales Example -->
<%--    <div class="card shadow mb-4">--%>
<%--        <div class="card-header py-3">--%>
<%--            <h6 class="m-0 font-weight-bold text-primary">Research Paper List</h6>--%>
<%--        </div>--%>
<%--        <div class="card-body">--%>
<%--            <div class="table-responsive">--%>
<%--                <table class="table table-bordered css-serial" id="researchListTbl" width="100%" cellspacing="0">--%>
<%--                    <thead>--%>
<%--                    <tr>--%>
<%--                        <th>#</th>--%>
<%--                        <th>Research Topic</th>--%>
<%--                        <th>File</th>--%>
<%--                        <th>Word Count</th>--%>
<%--                        <th>Status</th>--%>
<%--                        <th>Submitted On</th>--%>
<%--                        <th class="d-none"></th>--%>
<%--                        <th hidden>id</th>--%>
<%--                    </tr>--%>
<%--                    </thead>--%>
<%--                    <tbody>--%>
<%--                    </tbody>--%>
<%--                </table>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>

</div>
<!-- /.container-fluid -->
<script src="<c:url value='/resources/js/cbs/assignReviewer.js'/>"></script>
</body>
</html>
