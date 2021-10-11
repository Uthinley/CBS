<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 9/20/2021
  Time: 3:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Card Type</title>
</head>
<body>

<!-- Begin Page Content -->
<div class="container-fluid">
    <!--Intput table card-->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Card Type Setup</h6>
        </div>
        <div class="card-body">
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <form id="cardSetup" method="post" action=""
                                          class="">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                        <input type="hidden" name="actionType" id="actionType" value="C"/>
                                        <input type="text" name="id" id="id" hidden/>

                                        <div class="form-group row">
                                            <label class="align-right col-md-2">Card Name</label>

                                            <div class="col-md-6">
                                                <input type="text" class="form-control nameOnly" name="card_name"
                                                       id="card_name" required="true">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="align-right col-md-2">Type of Card</label>

                                            <div class="col-md-6">
                                                <select class="form-control" id="card_type" name="card_type">
                                                    <option>--------SELECT----------</option>
                                                    <option value="International Credit Card">International Credit Card</option>
                                                    <option value="International Debit Card">International Debit Card</option>
                                                    <option value="International Student Card">International Student Card</option>
                                                    <option value="Debit Card">Debit Card</option>
                                                    <option value="Prepaid Card">Prepaid Card</option>
                                                    <option value="Credit Card">Credit Card</option>
                                                    <option value="Corporate Credit Card">Corporate Credit Card</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="align-right col-md-2">Bank</label>

                                            <div class="col-md-6">
                                                <select class="form-control" id="bank" name="bank">
                                                    <option>--------SELECT----------</option>
                                                    <option value="BOBL">BOBL</option>
                                                    <option value="BNBL">BNBL</option>
                                                    <option value="DPNBL">DPNBL</option>
                                                    <option value="TBank">TBank</option>
                                                    <option value="BDBL">BDBL</option>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="form-group row">
                                            <label class="align-right col-md-2">Status</label>

                                            <div class="col-md-6">
                                                <form:select id="status"
                                                             class="form-control editable" name="status"
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
            <h6 class="m-0 font-weight-bold text-primary">Card List</h6>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered css-serial" id="groupTableId" width="100%" cellspacing="0">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Card Name</th>
                        <th>Type of card</th>
                        <th>Bank</th>
                        <th>Status</th>
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
<script src="<c:url value='/resources/js/card/cardTypeSetup.js'/>"></script>
</body>
</html>
