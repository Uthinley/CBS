<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: RMA
  Date: 3/23/2020
  Time: 4:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard</title>
</head>
<body>
<!-- Begin Page Content -->
<div class="container-fluid">
    <div class="d-sm-flex align-items-center justify-content-between mb-2">
        <h1 class="h6 mb-0 text-gray-800">Dashboard</h1>
        <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><input type="month" id="month" value="<%=new SimpleDateFormat("YYYY-MM").format(new Date())%>"/> </a>
    </div>
<security:authorize access="hasAuthority('00-01-001-VIEW')">
    <div class="row">
        <!-- Earnings (Monthly) Card Example -->
        <div class="col-xl-4 col-md-6 mb-2">
            <div class="card border-left-primary shadow h-100">
                <div class="card-header">
                    <h6 class="m-0 font-weight-bold text-primary">Users</h6>
                </div>
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2 font-weight-bold small">
                            <c:forEach items="${users}" var="user">
                                <div class="row">
                                    <div class="col-md-8 text-primary mb-1"><c:out value="${user.text}"/>:</div>
                                    <div class="col-md-4"><c:out value="${user.value}"/></div>
                                </div>
                            </c:forEach>

                           <%-- <div class="row">
                                <div class="col-md-8 text-primary mb-1">No. of Reviewer :</div>
                                <div class="col-md-4">6</div>
                            </div>
                            <div class="row">
                                <div class="col-md-8 text-primary mb-1">No. of Approver :</div>
                                <div class="col-md-4">1</div>
                            </div>--%>
                        </div>
                        <div class="col-auto">
                            <a href="<c:url value="/usersetup/"/>" title="Click for detail"> <i class="fa fa-user fa-2x"></i></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Earnings (Monthly) Card Example -->
        <div class="col-xl-4 col-md-6 mb-2">
            <div class="card border-left-success shadow">
                <div class="card-header">
                    <h6 class="m-0 font-weight-bold text-primary">Research Title</h6>
                </div>
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2 font-weight-bold small">
                            <div class="row">
                                <div class="col-md-8 text-success mb-1">Proposed Research Title :</div>
                                <div class="col-md-4 "><c:out value="${title.obj1}"/></div>
                            </div>
                            <div class="row">
                                <div class="col-md-8 text-success mb-1">Research Title Approved :</div>
                                <div class="col-md-4 "><c:out value="${title.obj2}"/></div>
                            </div>
                           <%-- <div class="text-success mb-1">
                                Research Title Returned :&nbsp;<span class="h6 mb-0 font-weight-bold text-gray-800 font-italic">900</span>
                            </div>--%>
                            <div class="row">
                                <div class="col-md-8 text-success mb-1">Research Title Returned :</div>
                                <div class="col-md-4 "><c:out value="${title.obj3}"/></div>
                            </div>
                            <div class="row">
                                <div class="col-md-8 text-success mb-1">Research Title Pending :</div>
                                <div class="col-md-4 "><c:out value="${title.obj4}"/></div>
                            </div>
                        </div>
                        <div class="col-auto">
                            <a href="<c:url value="/titleApprove/"/>" title="Click for detail"><i class="fa fa-file fa-2x"></i></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Earnings (Monthly) Card Example -->
        <div class="col-xl-4 col-md-6 mb-2">
            <div class="card border-left-info shadow h-100">
                <div class="card-header">
                    <h6 class="m-0 font-weight-bold text-primary">Research Paper</h6>
                </div>
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2 font-weight-bold small">
                            <div class="row">
                                <div class="col-md-8 text-info mb-1">Research Paper Uploaded :</div>
                                <div class="col-md-4 "><c:out value="${paper.obj1}"/></div>
                            </div>
                            <div class="row">
                                <div class="col-md-8 text-info mb-1">Research Paper Reviewed :</div>
                                <div class="col-md-4 "><c:out value="${paper.obj2}"/></div>
                            </div>
                            <div class="row">
                                <div class="col-md-8 text-info mb-1">Research Paper Pending :</div>
                                <div class="col-md-4 "><c:out value="${paper.obj3}"/></div>
                            </div>
                        </div>
                        <div class="col-auto">
                            <a href="<c:url value="/research#researchListTbl"/>" title="Click for detail"><i class="fas fa-clipboard-list fa-2x"></i></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="row">

        <div class="col-lg-6 mb-4 h-100">

            <div class="card shadow mb-4">
                <div class="card-header py-3">
                    <h6 class="m-0 font-weight-bold text-primary">Tasks completed in percentage</h6>
                </div>
                <%try{%>
                <div class="card-body">

                    <c:set var="tResearcher" value="${users[3].value eq 0 ? 1: users[3].value}" />
                    <c:set var="titleS" value="${title.obj1 eq 0 ?1:title.obj1}" />
                    <c:set var="titleA" value="${title.obj2}" />
                    <c:set var="paperS" value="${paper.obj1 eq 0 ?1:paper.obj1}" />
                    <c:set var="paperR" value="${paper.obj2}" />
                    <h4 class="small font-weight-bold">Research Title Received
                        <span class="float-right">
                            <c:out value="${titleS}"/> of <c:out value="${tResearcher}"/> =<c:out value="${(titleS*100)/tResearcher}"/>%
                        </span>
                    </h4>
                    <div class="progress mb-4">
                        <div class="progress-bar bg-info" role="progressbar" style="width:<c:out value="${(titleS*100)/tResearcher}"/>%"
                             aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"></div>
                    </div>
                    <h4 class="small font-weight-bold">Research Title Approved <span
                            class="float-right">
                        <c:out value="${titleA}"/> of <c:out value="${titleS}"/> =<c:out value="${(titleA*100)/titleS}"/>%
                    </span></h4>
                    <div class="progress mb-4">
                        <div class="progress-bar bg-danger" role="progressbar" style="width: <c:out value="${(titleA*100)/paperS}"/>%"
                             aria-valuenow="20" aria-valuemin="0" aria-valuemax="100"></div>
                    </div>
                    <h4 class="small font-weight-bold">Research Paper Received <span
                            class="float-right">
                        <c:out value="${paperS}"/> of <c:out value="${tResearcher}"/> =<c:out value="${(paperS*100)/tResearcher}"/>%
                    </span></h4>
                    <div class="progress mb-4">
                        <div class="progress-bar bg-success" role="progressbar" style="width: <c:out value="${(paperS*100)/tResearcher}"/>%"
                             aria-valuenow="90" aria-valuemin="0" aria-valuemax="100"></div>
                    </div>

                    <h4 class="small font-weight-bold">Research Paper Reviewed <span
                            class="float-right">
                        <c:out value="${paperR}"/> of <c:out value="${paperS}"/> =<c:out value="${(paperR*100)/paperS}"/>%
                    </span></h4>
                    <div class="progress mb-4">
                        <div class="progress-bar bg-warning" role="progressbar" style="width: <c:out value="${(paperR*100)/paperS}"/>%"
                             aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"></div>
                    </div>
                    <h4 class="small font-weight-bold">Marks allocation <span
                            class="float-right">60%</span></h4>
                    <div class="progress mb-4">
                        <div class="progress-bar" role="progressbar" style="width: 60%"
                             aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"></div>
                    </div>
                </div>
                <%}catch (Exception e){
                }%>
            </div>
        </div>

        <!-- Area Chart -->
        <div class="col-lg-6 mb-4">
            <div class="card shadow mb-4">
                <div class="card-header py-3">
                    <h6 class="m-0 font-weight-bold text-primary">Marks obtained by researcher: Average trend</h6>
                </div>
                <div class="card-body">
                    <div class="chart" id="marks_line_chart" style="max-height: 300px;font-size: 9px"></div>
                </div>
            </div>
        </div>
    </div>
</security:authorize>
</div>
<script>

    $('#month').on('change', function (e) {
        window.location.href += "?month="+$('#month').val();
        // window.location = 'your_url?data='+id;
    });
    new Morris.Line({
        element: 'marks_line_chart',//Div id.
        data: [
            { y: 'Jan', a: 100},
            { y: 'Feb', a: 75  },
            { y: 'Mar', a: 50,  },
            { y: 'Apr', a: 75 },
            { y: 'May', a: 100 }],
        xkey: 'y', //X - Axis
        parseTime: false,
        ykeys: ['a'],//Y-axis
        labels: ['Average marks obtained'],
        lineColors: ['gray']
    });
    /*var chart = new CanvasJS.Chart("marks_line_chart", {
        animationEnabled: true,
        theme: "light2",
        title:{
            text: "Average marks trend"
        },
        data: [{
            type: "line",
            indexLabelFontSize: 16,
            dataPoints: [
                { y: 80,label: "Jan"},
                { y: 79,label: "Feb"},
                { y: 90,label: "Mar",indexLabel: "\u2191 highest",markerColor: "red", markerType: "triangle" },
                { y: 67,label: "Apr" },
                { y: 70,label: "May" },
                { y: 62,label: "Jun",indexLabel: "\u2193 lowest",markerColor: "DarkSlateGrey", markerType: "cross" },
                { y: 70,label: "Jul" },
                { y: 85,label: "Aug", }
            ]
        }]
    });
    chart.render();*/
</script>
<script src="<c:url value='/resources/js/db/home.js'/>"></script>
</body>
</html>
