<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 9/15/2021
  Time: 12:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Card Information</title>
</head>
<body>

<!-- Begin Page Content -->
<div class="container-fluid">
    <!--Intput table card-->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">International Card Issuance</h6>
        </div>
        <div class="card-body">
            <div class="row">
                <div class="col-lg-10">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <form id="searchCard" method="GET" action="">
                                        <div class="form-group row">
                                            <label class="align-right required col-lg-2"
                                                   for="cid">Search By CID</label>

                                            <div class="col-lg-6">
                                                <input type="number" name="cid" id="cid"
                                                       class="form-control" required>
                                            </div>
                                            <div class="col-lg-2">
                                                <button id="searchBtn" class="btn btn-primary">Search</button>
                                            </div>
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

                <div class="col-lg-2" id="db_excel_format"></div>
            </div>
        </div>
    </div>

    <div class="card shadow mb-4 result" hidden>
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Card Information</h6>
        </div>
        <div class="card-body">
            <h5>International Card denominated in USD</h5>
            <table id="cardInfo" class="table table-bordered">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Card Type</th>
                    <th scope="col">Amount Sanctioned (USD)</th>
<%--                    <th scope="col">Amount Sanctioned (INR)</th>--%>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
            <br>
            <h5>International Card denominated in INR</h5>
            <table id="cardInfoINR" class="table table-bordered">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Card Type</th>
                    <th scope="col">Amount Sanctioned (INR)</th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </div>

    <div class="row result" hidden>
            <div class="col">
                <div class="card shadow ">
                    <div class="card-header ">
                        <h6 class="m-0 font-weight-bold text-primary">Add Card Information</h6>
                    </div>
                    <div class="card-body">
                        <form id="addCardInfo" method="POST" >

                            <label class="required "
                                   for="cid">CID</label>
                            <div class="form-group">
                                <input type="number" name="cid" id="cid1"
                                       class="form-control" required>
                            </div>
                            <label class="required"
                                   for="cid">Name</label>
                            <div class="form-group ">
                                <input type="text" name="name" id="fullname"
                                       class="form-control" required>
                            </div>
                            <label class="required"
                                   for="cid">Card Type</label>
                            <div class="form-group">
<%--                                <select class="form-control" id="exampleFormControlSelect1">--%>
<%--                                    <option>--------SELECT----------</option>--%>
<%--                                    <option>International Credit Card</option>--%>
<%--                                    <option>International Debit Card</option>--%>
<%--                                    <option>International Student Card</option>--%>
<%--                                    <option>Debit Card</option>--%>
<%--                                    <option>Prepaid Card</option>--%>
<%--                                    <option>Credit Card</option>--%>
<%--                                    <option>Corporate Credit Card</option>--%>
<%--                                </select>--%>
                                    <form:select id="card_type"
                                                 class="form-control editable required" name="cardType"
                                                 path="cardList">
                                        <form:options items="${cardList}" itemValue="text"
                                                      itemLabel="text"/>
                                    </form:select>
                            </div>
                            <div class="form-group">
                                <label class="required" for="currency">Currency</label>
                                <select class="form-control required" id="currency" name="currency">
                                    <option>--------SELECT----------</option>
                                    <option value="USD">USD</option>
                                    <option value="INR">INR</option>
                                </select>
                            </div>
                            <label class=" required"
                                   for="cid">Sanction Amount</label>
                            <div class="form-group">
                                <input type="number" name="sanctionAmt" id="sanctionAmt"
                                       class="form-control" required>
                            </div>

                            <div class="form-group">
                                <%--<security:authorize access="hasRole('${SCREEN_ID}-ADD')">--%>
                                <input type="submit" id="btnSave" value="Save"
                                       class="btn btn-primary  editable">
                                <%--</security:authorize>--%>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card shadow ">
                    <div class="card-header ">
                        <h6 class="m-0 font-weight-bold text-primary">The limits for cards as follows:</h6>
                    </div>
                    <div class="card-body">
                        <div class="card-body">
                            <h5 class="card-title">International Card denominated in INR</h5>
                            <table class="table table-sm table-bordered">
                                <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Type of Cards</th>
                                    <th scope="col">Limits per card</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td scope="row">1</td>
                                    <td>Debit Card</td>
                                    <td>INR <strong>65,000</strong> per month</td>
                                </tr>
                                <tr>
                                    <td scope="row">2</td>
                                    <td>Prepaid Card</td>
                                    <td>INR <strong>50,000</strong> per month</td>
                                </tr>
                                <tr>
                                    <td scope="row">3</td>
                                    <td>Credit card</td>
                                    <td>INR <strong>150,000</strong> per month</td>
                                </tr>
                                </tbody>
                            </table>
                            <h5 class="card-title">International Card denominated in USD</h5>
                            <table class="table table-sm table-bordered">
                                <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Type of Cards</th>
                                    <th scope="col">Limits per card</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td scope="row">1</td>
                                    <td>International Debit Card</td>
                                    <td>USD <strong>1000</strong> per annum</td>
                                </tr>
                                <tr>
                                    <td scope="row">2</td>
                                    <td>International Credit Card</td>
                                    <td>USD <strong>1000</strong> per annum</td>

                                </tr>
                                <tr>
                                    <td scope="row">3</td>
                                    <td>International Student Card</td>
                                    <td>USD <strong>10,000</strong> per annum</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>

        </div>

</div>
<script src="<c:url value='/resources/js/card/addCard.js'/>"></script>
</body>
</html>