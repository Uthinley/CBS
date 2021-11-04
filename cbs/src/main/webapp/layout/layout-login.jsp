<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 3/18/2020
  Time: 4:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<%@ page session="false" %>
<%--<%
    ResourceBundle resource = ResourceBundle.getBundle("env");
	String environment = resource.getString("ENVIRONMENT");
	pageContext.setAttribute("env", environment);
%>--%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Login :: ZRR</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <link href="<c:url value='/resources/startbootstrap/vendor/fontawesome-free/css/all.min.css'/>" rel="stylesheet" type="text/css">
<%--    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">--%>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/sweetalert/sweetalert.css'/>"/>
    <!-- Custom styles for this template-->
    <link href="<c:url value='/resources/startbootstrap/css/sb-admin-2.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resources/css/style.css'/>" rel="stylesheet">
    <style>
        #login{
            background-image: url(<c:url value="/resources/startbootstrap/img/background.png" />);
            background-repeat: no-repeat, repeat;
            background-size: cover;
        }
    </style>
</head>
<body id="login">
<sitemesh:write property="body"/>


<!-- Bootstrap core JavaScript-->
<script src="<c:url value='/resources/startbootstrap/vendor/jquery/jquery.min.js'/>"></script>

<script src="<c:url value='/resources/startbootstrap/vendor/bootstrap/js/bootstrap.bundle.min.js'/>"></script>

<!-- Core plugin JavaScript-->
<script src="<c:url value='/resources/startbootstrap/vendor/jquery-easing/jquery.easing.min.js'/>"></script>
<script src="<c:url value='/resources/startbootstrap/js/sb-admin-2.min.js'/>"></script>


</body>
</html>
