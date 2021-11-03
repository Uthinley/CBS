<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 9/27/2021
  Time: 12:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
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
            <ul class="nav nav-tabs" id="myTab" role="tablist">
                <security:authorize access="hasAuthority('02-01-003-VIEW')">
                <li class="nav-item">
                    <a class="nav-link active" id="form-tab" data-toggle="tab" href="#form" role="tab"
                       aria-controls="form" aria-selected="true">
                        <p class="m-0 font-weight-bold text-primary">Research Paper</p>
                    </a>
                </li>
                </security:authorize>
                <security:authorize access="hasAuthority('02-01-004-VIEW')">
                <li class="nav-item">
                    <a class="nav-link" id="research-list-tab" data-toggle="tab" href="#research-list" role="tab"
                       aria-controls="research-list" aria-selected="false">
                        <p class="m-0 font-weight-bold text-primary">Research Paper List</p>
                    </a>
                </li>
                </security:authorize>
            </ul>
        </div>

        <div class="card-body">
            <div class="tab-content">

                <security:authorize access="hasAuthority('02-01-003-VIEW')">
                <div class="tab-pane active" id="form" role="tabpanel" aria-labelledby="form-tab">
                    <form id="addResearch" method="post" action=""
                          class="row align-items-center" enctype="multipart/form-data">
                        <input id="csrf" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <input type="hidden" name="actionType" id="actionType" value="C"/>
                        <input type="text" name="id" id="id" hidden/>

                        <div class="col-lg-7">

                            <%--<fieldset><legend>Research details</legend>--%>

                            <div class="form-group row">

                                <label class="align-right col-md-3 required">Research Month</label>

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
                                <label class="align-right col-md-3 required" for="researchTopic">Research Title</label>

                                <div class="col-md-9">
                                    <textarea class="form-control " name="researchTopic"
                                              id="researchTopic" required="true" placeholder="Research Title ..." readonly></textarea>
                                    <textarea class="form-control " name="key_words"
                                              id="key_words" required="true" placeholder="Key words ..."></textarea>
                                    <textarea class="form-control" name="research_abstract"
                                              id="research_abstract" rows="6"
                                              placeholder="Enter the research abstract ..."></textarea>
                                </div>
                            </div>
                            <%--</fieldset>--%>
                        </div>

                        <div class="col-lg-5 align-center">
                            <div class="form-group row">
                                <p style="text-align: center;width: 100%;color: #78261f">***Max file size 5MB only</p>
                            </div>
                            <div class="form-group row">
                                <label class="align-right col-md-3 required" style="font-size: 9px">Research Paper</label>

                                <div class="col-md-9">
                                    <input type="file" class="form-control-file nameOnly" name="research_paper"
                                           id="research_paper" required="true" accept=".doc,.docx">
                                </div>
                            </div>
                            <%--<div class="form-group row">
                                <label class="align-right col-md-3" style="font-size: 9px">Supporting Documents</label>

                                <div class="col-md-9">
                                    <input type="file" class="form-control-file" name="supporting_documents"
                                           multiple="multiple">
                                </div>
                            </div>--%>

                            <div class="h5 font-weight-bold text-info text-uppercase mb-1">
                                TOTAL WORD COUNT
                            </div>
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
                </security:authorize>
                <security:authorize access="hasAuthority('02-01-004-VIEW')">
                <div class="tab-pane" id="research-list" role="tabpanel" aria-labelledby="research-list-tab">
                    <div class="table-responsive">
                        <table class="table table-bordered css-serial table-responsive-sm" id="researchListTbl"
                               width="100%" cellspacing="0">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th>Research Number</th>
                                <th>Research Month</th>
                                <th>Research Title</th>
                                <th>Word Count</th>
                                <th>Research Paper</th>
                                <th>Status</th>
                                <th>Reviewer's Comment</th>
                                <th>Uploaded On</th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>
                </div>
                </security:authorize>
            </div>
        </div>

    </div>
</div>
<script src="<c:url value='/resources/js/cbs/addResearch.js'/>"></script>
<!-- /.container-fluid -->
</body>
</html>
