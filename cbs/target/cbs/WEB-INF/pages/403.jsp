<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 3/19/2020
  Time: 12:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Access Denied</title>
</head>
<body>
<!-- Begin Page Content -->
<div class="container-fluid">
    <!--Intput table card-->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <span>Access Denied</span><span>Access Permission Denied to show the screen</span>
        </div>
        <div class="card-body">
            <div class="panel panel-default">
                <div class="panel-body">
                    <h4>
                        <span class="label text-danger">You have no permission to show requested screen</span>
                        <p>
                        <ul>
                            <li>
                                Please ask your administrator to provide access permission on requested screen.
                            </li>
                            <li>
                                After confirm permission has given, please logout and login again to get access to requested screen.
                            </li>
                        </ul>
                        </p>
                    </h4>
                </div>
                <!-- /.col-lg-6 (nested) -->
            </div>
            <!-- /.row (nested) -->
        </div>
        <!-- /.panel-body -->
    </div>
    <!-- /.panel -->
</div>
<!-- /.container-fluid -->

</body>
</html>

