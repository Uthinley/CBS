<%--
  Created by IntelliJ IDEA.
  User: sangay tenzin
  Date: 20/12/2019
  Time: 04:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Password Policy Setup</title>
</head>
<body>
<div class="">

<div class="container-fluid">

    <!-- Page Heading -->
    <h1 class="h3 mb-4 text-gray-800">Password Policy Setup</h1>

    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Password Setup Form</h6>
        </div>
        <div class="card-body">
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <form id="passwordPolicyForm" method="post" action="<c:url value='/passwordPolicy'/>"
                                          class="form-horizontal globalForm">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                                        <div class="form-check">
                                            <input type="checkbox" class="form-check-input" name="isMaxLength"
                                                   id="isMaxLength">
                                            <label class="form-check-label">Enable minimum length of password required.</label>
                                        </div>
                                        <br>
                                        <div class="form-check">
                                            <input type="checkbox" class="form-check-input" name="isEnforcePasswordChange"
                                                   id="isEnforcePasswordChange">
                                            <label class="form-check-label">Enforce to change password on first login.</label>
                                        </div>
                                        <br>
                                        <div class="form-check">
                                            <input type="checkbox" class="form-check-input" name="isExpiryDaysEnable" id="isExpiryDaysEnable">
                                            <label class="form-check-label">Enable password expiry policy.</label>
                                        </div>
                                        <br>
                                        <div class="form-check">
                                            <input type="checkbox" class="form-check-input" name="isSpecialChar" id="isSpecialChar">
                                            <label class="form-check-label">Password required to contain special characters.</label>
                                        </div>
                                        <br>


                                        <input type="submit" id="btnReset" value="Reset" class="btn btn-primary">

                                        <security:authorize access="hasAuthority('${SCREEN_ID}-ADD')">
                                            <input type="submit" id="btnSave" value="Save" class="btn btn-primary ">
                                        </security:authorize>
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

</div>
</div>
</body>
</html>
