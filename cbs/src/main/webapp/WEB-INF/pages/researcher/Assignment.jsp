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

                <div class="col-lg-8">

                    <div class="form-group row">
                        <label class="align-right col-md-3">Research Number</label>

                        <div class="col-md-8">
                            <input type="text" class="form-control" name="research_number"
                                   id="research_number" readonly placeholder="Research number will be auto generated">
                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="align-right col-md-3">Research Topic</label>

                        <div class="col-md-8">
                            <input type="text" class="form-control " name="researchTopic"
                                   id="researchTopic" required="true">
                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="align-right col-md-3">Key word</label>

                        <div class="col-md-8">
                            <input type="text" class="form-control " name="key_word"
                                   id="key_word" required="true">
                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="align-right col-md-3">Abstract</label>

                        <div class="col-md-8">
                            <textarea  class="form-control" name="research_abstract"
                                      id="research_abstract"  rows="6" ></textarea>
                        </div>
                    </div>

                </div>
                <div class="col-lg-4 align-center">
                    <div class="form-group row">
                        <label class="align-right col-md-3" style="font-size: 9px">Research Paper</label>

                        <div class="col-md-8">
                            <input type="file" class="form-control-file nameOnly" name="research_paper"
                                   id="research_paper" required="true" accept=".doc,.docx">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="align-right col-md-3" style="font-size: 9px">Supporting Documents</label>

                        <div class="col-md-8">
                            <input type="file" class="form-control-file" name="supporting_document"
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
