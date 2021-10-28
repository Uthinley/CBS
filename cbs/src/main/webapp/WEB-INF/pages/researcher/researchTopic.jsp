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
    <title>Research Topic</title>
</head>
<body>
<div class="container-fluid dtree">
    <!--Intput table card-->
    <div class="card shadow mb-4">
<div class="card-body">
    <form id="researchTopicForm" method="post" action=""
          class="row align-items-center" enctype="multipart/form-data">
        <input id="csrf" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="hidden" name="actionType" id="actionType" value="C"/>

        <div class="col-lg-12">
        <div class="form-group row">

            <label class="align-right col-md-3">Research Month</label>

            <div class="col-md-5">
                <input type="month" class="form-control " name="research_month"
                       id="research_month" required="true" placeholder="Month">
            </div>
        </div>

        <div class="form-group row">
            <label class="align-right col-md-3">Research Topic</label>

            <div class="col-md-9">
                <textarea class="form-control " name="research_topic"
                          id="research_topic" required="true" placeholder="Choose your research topic ..."></textarea>
            </div>
        </div>

        <div class="col-lg-12 form-group row">
            <input type="reset" id="btnReset" class="btn btn-primary offset-3" value="Reset">
            <input type="button" id="btnEdit" value="Edit" class="btn btn-primary"
                   disabled>
            <input type="submit" id="btnSave" value="Save" class="btn btn-primary">
            <input type="button" id="btnDelete" value="Delete" class="btn btn-primary"
                   disabled>
        </div>
        </div>
    </form>
</div>
    </div>
</div>

</body>
</html>
