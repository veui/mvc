<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@include file="/WEB-INF/pages/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Add specailty</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="<spring:url value="/resources/js/specialty.js" />"></script>
    <link href="<spring:url value="/resources/css/specialty/add.css" />" rel="stylesheet" type="text/css">
</head>
<body>
<h1>Specialty Input Form</h1>
<hr />

<table>
    <tr>
        <td>Title</td>
        <td>
            <label>
                <input name="title" id="title" type="text" />
            </label>
            <div id="title-non-unique-message"></div>
            <div id="title-not-valid"></div>
        </td>
    </tr>
    <tr>
        <td>Department</td>
        <td>
            <label for="selectId">
                <input id="selectId" type="text" placeholder="Department" >
            </label>
        </td>
    </tr>
    <tr>
        <td>Parent</td>
        <td>
            <label for="selectParent">
                <select id="selectParent">
                    <option id="optionParent" value=0>0</option>
                    <c:forEach items="${specialtyList}" var="spe">
                        <option id="optionParent" value="${spe.id}">${spe.title}</option>
                    </c:forEach>
                </select>
            </label>
        </td>
    </tr>
    <tr>
        <td><button class="myButton" type="button" onclick="add()">Submit</button></td>
    </tr>
</table>
</body>
</html>
<script>
    $( function() {
        var availableTags = [];
        <c:forEach items="${departmentList}" var="department">
        var str = '${department.title}';
        availableTags.push(str);
        </c:forEach>

        $("#selectId").autocomplete({
            source: availableTags
        });
    });


    function formIdForSpecDepartmentAdd(str) {
        <c:forEach items="${departmentList}" var="department">
        var strstrVar = '${department.title}';
        if(strVar == str)
            return '${department.id} ';
        </c:forEach>
    }
</script>
