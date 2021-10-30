<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 9/27/2021
  Time: 12:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Research Topic</title>
</head>
<body>
<div class="container-fluid dtree">
    <!--Intput table card-->
    <div class="card shadow mb-4">
        <div class="card-header py-3">

            <ul class="nav nav-tabs" id="myTab" role="tablist">
                <li class="nav-item">
                    <a class="nav-link active" id="form-tab" data-toggle="tab" href="#form" role="tab" aria-controls="form" aria-selected="true">Research Topic Selection</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id="approved-tab" data-toggle="tab" href="#approved" role="tab" aria-controls="approved" aria-selected="false">Research Topics List</a>
                </li>
            </ul>

        </div>
        <div class="card-body">
            <div class="tab-content">
                <div class="tab-pane active" id="form" role="tabpanel" aria-labelledby="form-tab">
                    <form id="researchTopicForm" method="post" action="" class="row align-items-center" >
                        <input id="csrf" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <input type="hidden" name="actionType" id="actionType" value="C"/>

                        <div class="col-lg-12">
                            <div class="form-group row">

                                <label class="align-right col-md-2 required">Research Month</label>

                                <div class="col-md-5">
                                    <input type="month" class="form-control " name="research_month"
                                           id="research_month" required="true" placeholder="Month">
                                </div>
                            </div>

                            <div class="form-group row">
                                <label class="align-right col-md-2 required">Research Title</label>

                                <div class="col-md-9">
                            <textarea class="form-control " name="research_topic"id="research_topic" required="true"
                                      placeholder="Propose your research title ..." rows="5"></textarea>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label class="align-right col-md-2 required">Status</label>

                                <div class="col-md-2">
                                    <select class="" id="status" name="status">
                                        <option value="P">Propose</option>
                                    </select>
                                </div>
                                <div class="col-md-8" id="approver_comments">
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
                <div class="tab-pane" id="approved" role="tabpanel" aria-labelledby="approved-tab">
                    <table id="approved_topic_list" class="table table-responsive-sm css-serial">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>Research Month</th>
                            <th>Research Topic</th>
                            <th>Status</th>
                            <th>Approver's Comment</th>
                            <th>Owner</th>
                            <th>Proposed On</th>
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

<script>
    $('.nav-tabs button').on('click', function (e) {
        $(this).tab('show');
    });
</script>
<script src="<c:url value='/resources/js/cbs/researchTopic.js'/>"></script>

</body>
</html>
