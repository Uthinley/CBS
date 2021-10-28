<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
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
<div class="container-fluid dtree">
    <!--Intput table card-->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Research Paper</h6>
        </div>
        <div class="card-body">
            <form id="addResearch" method="post" action=""
                  class="row align-items-center" enctype="multipart/form-data">
                <input id="csrf" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="hidden" name="actionType" id="actionType" value="C"/>
                <input type="text" name="id" id="id" hidden/>

                <div class="col-lg-7">

                    <%--<fieldset><legend>Research details</legend>--%>

                    <div class="form-group row">

                        <label class="align-right col-md-3">Research Month</label>

                        <div class="col-md-5">
                           <input type="month" class="form-control " name="research_month"
                                   id="research_month" required="true" placeholder="Month">
                        </div>

                        <div class="col-md-4">
                            <input type="text" class="form-control" name="research_number"
                                   id="research_number" readonly placeholder="Research Number">
                        </div>
                    </div>


                    <div class="form-group row">
                        <label class="align-right col-md-3">Research Topic</label>

                        <div class="col-md-9">
                            <input type="text" class="form-control " name="researchTopic"
                                   id="researchTopic" required="true" placeholder="Research Topic ...">
                            <input type="text" class="form-control " name="key_words"
                                   id="key_words" required="true" placeholder="Key words ...">
                            <textarea  class="form-control" name="research_abstract"
                                       id="research_abstract"  rows="6"  placeholder="Enter the research abstract ..."></textarea>
                        </div>
                    </div>
                    <%--</fieldset>--%>
                </div>

                <div class="col-lg-5 align-center">
                    <div class="form-group row">
                        <label class="align-right col-md-3" style="font-size: 9px">Research Paper</label>

                        <div class="col-md-9">
                            <input type="file" class="form-control-file nameOnly" name="research_paper"
                                   id="research_paper" required="true" accept=".doc,.docx">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="align-right col-md-3" style="font-size: 9px">Supporting Documents</label>

                        <div class="col-md-9">
                            <input type="file" class="form-control-file" name="supporting_documents"
                                   multiple="multiple">
                        </div>
                    </div>

                    <div class="h5 font-weight-bold text-info text-uppercase mb-1">
                        TOTAL WORD COUNT</div>
                    <div class="h1 mb-0 font-weight-bold text-secondary word_count">0</div>
                </div>
                <div class="col-lg-12 form-group row">
                    <input type="reset" id="btnReset" class="btn btn-primary offset-3" value="Reset">
                    <input type="button" id="btnEdit" value="Edit" class="btn btn-primary"
                           disabled>
                    <input type="submit" id="btnSave" value="Save" class="btn btn-primary">
                    <input type="button" id="btnDelete" value="Delete" class="btn btn-primary"
                           disabled>
                </div>
    </form>
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
                        <th>Research Number</th>
                        <th>Research Topic</th>
                        <th>Document</th>
                        <th>Current Paper Version</th>
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
