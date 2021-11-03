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
            <div class="row align-items-center">
                <div class="col-lg-4">
                    <div class="input-group md-form form-sm form-2 pl-0 search">
                        <input type="month" class="form-control" name="monthId" id="monthId"/>
                        <%--<form:select id="monthId"
                                         class="form-control editable" name="monthId"
                                         path="researchMonthList" required="true">
                                <form:option value="">---Please select---</form:option>
                                <form:options items="${researchMonthList}" itemValue="value"
                                              itemLabel="text"/>
                            </form:select>--%>
                        <div class="input-group-append">
                             <button class="input-group-text bg-info text-white lighten-3" id="searchBtn">
                                 <i class="fas fa-search text-grey" aria-hidden="true"></i></button>
                        </div>
                    </div>
                </div>
            </div>
            <br>
            <div class="row align-items-center">
                <div class="col-lg">
                    <div class="table-responsive">
                        <table class="table table-bordered css-serial" id="researchListTbl" width="100%" cellspacing="0">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th>Research Number</th>
<%--                                <th>File</th>--%>
<%--                                <th>Word Count</th>--%>
                                <th>Research Title</th>
                                <th>Received On</th>
                                <th>Action</th>
<%--                                <th class="d-none"></th>--%>
<%--                                <th hidden>id</th>--%>
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
    <div class="card shadow mb-4">
    <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">List of research paper assigned to reviewer</h6>
    </div>
    <div class="card-body">
        <div class="row align-items-center">
            <div class="col-lg">
                <div class="table-responsive">
                    <table class="table table-bordered css-serial" id="assingedResearchTbl" width="100%" cellspacing="0">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>Research Number</th>
                            <th>Research Title</th>
                            <th>Assigned On</th>
                            <th>Assigned To</th>
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
</div>
    <%--modal for assigning reviewer--%>
    <div class="modal fade bd-example-modal-lg" id="assignModal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
<%--                <h5 class="modal-title" id="exampleModalLabel">New message</h5>--%>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <label for="rNumber" class="col-form-label">Research Number:</label>
                        <input type="text" class="form-control" id="rNumber" readonly>
                    </div>
                    <div class="form-group">
                        <label for="rName" class="col-form-label">Research Topic:</label>
                        <input type="text" class="form-control" id="rName" readonly>
                    </div>
                    <div class="form-group">
                        Reviewer List:
                        <div class="input-group md-form form-sm form-2 pl-0 search">
                            <form:select id="reviewerName"
                                         class="form-control editable" name="monthId"
                                         path="reviewerList" required="true">
                                <form:option value="">---Please select---</form:option>
                                <form:options items="${reviewerList}" itemValue="value"
                                              itemLabel="text"/>
                            </form:select>
                        </div>
                    </div>
                    <div class="form-group">
                        Completion Date:
                        <div class="input-group md-form">
                            <input type="date" id="completionDate" class="form-control" name="completionDate">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="button" id="assignBtn" class="btn btn-primary">Assign</button>
            </div>
        </div>
    </div>
</div>
<!-- /.container-fluid -->
</div>
<script src="<c:url value='/resources/js/cbs/assignReviewer.js'/>"></script>
</body>
</html>
