<%@ page import="bt.cbs.zrr.global.enumeration.ApplicationStatusCode" %><%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 9/27/2021
  Time: 12:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Review Research</title>
</head>
<body>
<div class="container-fluid dtree">
    <div class="card shadow mb-3">
        <div class="card-header py-3">
            <h6 class="mb-0 text-gray-800">Research paper assigned for your review</h6>
        </div>
        <div class="card-body">
            <div class="tab-content">
                <div class="tab-pane active" id="form" role="tabpanel" aria-labelledby="form-tab">
                    <form id="searchForm" method="GET" action="" class="row align-items-center" >
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                        <div class="col-lg-12">
                            <div class="form-group row">

                                <label class="align-right col-md-2 required">Month</label>

                                <div class="col-md-5">
                                    <input type="month" class="form-control " name="research_month"
                                           id="research_month" required="true" placeholder="Month">
                                </div>
                            </div>

                            <div class="form-group row">
                                <label class="align-right col-md-2 required">Status</label>

                                <div class="col-md-2">
                                    <select  class="form-control-sm" name="status">
                                        <option value="ALL">ALL</option>
                                        <option value="<%=ApplicationStatusCode.SUBMITTED.getValue()%>">Submitted</option>
                                        <option value="<%=ApplicationStatusCode.REVIEWED.getValue()%>">Reviewed</option>
                                    </select>
                                </div>
                                <div class="col-md-2">
                                    <input type="submit" id="btnSearch" value="Search" class="btn btn-primary">
<%--                                    <i class="fa fa-search" aria-hidden="true"></i>--%>
                                </div>
                            </div>
                        </div>
                    </form>
                    <form id="researchReviewForm" method="post" action="" class="row align-items-center" >
                        <input id="csrf" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <input type="hidden" name="actionType" id="actionType" value="C"/>

                        <div class="col-lg-12" >
                            <table id="assigned_research_tbl" class="table table-responsive-sm css-serial">
                                <thead>
                                <tr>
                                    <th>#</th>
                                    <th hidden>id</th>
                                    <th>Month</th>
                                    <th>Research Title</th>
                                    <th>Status</th>
                                    <th>Research Paper</th>
                                    <th>Word Count</th>
                                    <th>Author</th>
                                    <th>Assigned On</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
<%--                            <div class="form-group row">--%>
<%--                                <label class="align-right col-md-2 required">Approver Remarks</label>--%>

<%--                                <div class="col-md-8">--%>
<%--                                        <textarea class="form-control" name="remarks" id="remarks" required="true"--%>
<%--                                                  rows="5" placeholder="Enter the approvers comments/remarks if any.."></textarea>--%>
<%--                                </div>--%>
<%--                            </div>--%>
                        </div>
<%--                        <div class="col-lg-12 form-group row">--%>
<%--                            <input type="reset" id="btnReset" class="btn btn-primary offset-3" value="Reset">--%>
<%--                            <input type="button" id="btnEdit" value="Edit" class="btn btn-primary" disabled>--%>
<%--                            <input type="button" id="btnApprove" value="Approve" class="btn btn-primary">--%>
<%--&lt;%&ndash;                            <input type="button" id="btnReturn" value="Returned" class="btn btn-primary">&ndash;%&gt;--%>
<%--                        </div>--%>
                    </form>
                </div>

            </div>

        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade bd-example-modal-lg" id="reviewerModal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header bg-info text-white">
                <h5 class="modal-title" id="exampleModalLongTitle">Reviewer Comment:</h5>
                <button type="button" class="close text-white" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <input type="text" class="form-control" id="researchId" hidden>
                <div class="form-group col-md-12">
                    <label for="researchTitle">Research Title</label>
                    <input class="form-control" id="researchTitle" name="researchTopic" readonly>
                </div>
                <div class="form-group col-md-5">
                    <label for="author">Author</label>
                    <input type="text" class="form-control" id="author" readonly>
                </div>
                <div class="form-group">
                    <label for="rComment">Reviewer comments</label>
                    <textarea class="form-control" id="rComment" name="rComment" rows="5"></textarea>
                </div>
                <div class="form-group">
                    <label for="rComment">Marking (Quality and Quantity) -</label>
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Particulars</th>
                            <th scope="col">Assessment</th>
                            <th scope="col">IWP score</th>
                            <th scope="col">Performance Level</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td scope="row">1</td>
                            <td>Word Count</td>
                            <td>4000</td>
                            <td>3.875</td>
                            <td>Outstanding</td>
                        </tr>
                        <tr>
                            <td scope="row">2</td>
                            <td>Quality assessment by reviewer</td>
                            <td><input type="number" class="form-control" id="exampleInputPassword1"></td>
                            <td><input type="number" class="form-control" readonly></td>
                            <td><input type="text" class="form-control" readonly></td>
                        </tr>
                        </tbody>
                    </table>

                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Assessment criteria</th>
                            <th scope="col">Remarks</th>
                            <th scope="col">Weightage</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td scope="row">1</td>
                            <td>Policy relevance/Research significance</td>
                            <td>The research has immediate practical utility or adds new knowledge to the existing knowledge</td>
                            <td><input type="number" class="form-control" id="weightageOne"></td>
                        </tr>
                        <tr>
                            <td scope="row">2</td>
                            <td>Research methodology</td>
                            <td>The research has immediate practical utility or adds new knowledge to the existing knowledge</td>
                            <td><input type="number" class="form-control" id="weightageTwo"></td>
                        </tr>
                        <tr>
                            <td scope="row">3</td>
                            <td>Data analysis and findings</td>
                            <td>The research has immediate practical utility or adds new knowledge to the existing knowledge</td>
                            <td><input type="number" class="form-control" id="weightageThree"></td>
                        </tr>
                        <tr>
                            <td scope="row">4</td>
                            <td>Recommendations</td>
                            <td>The research has immediate practical utility or adds new knowledge to the existing knowledge</td>
                            <td><input type="number" class="form-control" id="weightageFour"></td>
                        </tr>
                        <tr>
                            <td scope="row">5</td>
                            <td>Overall standard of the report</td>
                            <td>The research has immediate practical utility or adds new knowledge to the existing knowledge</td>
                            <td><input type="number" class="form-control" id="weightage"></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="alert alert-danger" role="alert">
                    This is a danger alert—check it out!
                </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="button" id="submitBtn" class="btn btn-primary">Post</button>
            </div>
        </div>
    </div>
</div>


<script src="<c:url value='/resources/js/cbs/researchReview.js'/>"></script>

</body>
</html>
