<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@include file="/WEB-INF/pages/header.jsp" %>
<html>
<head>
    <title>Edit Specialty</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="<spring:url value="/resources/js/specialty.js" />" rel="script" type="text/javascript"></script>
    <link href="<spring:url value="/resources/css/specialty/edit.css" />" rel="stylesheet" type="text/css" />
</head>
<body>
<form name="specialtyList" action="${pageContext.request.contextPath}/specialty">
    <table>
        <tr>
            <td>ID</td>
            <td>
                <label>
                    <input name="id" id="id" value="${specialtyList.id}" readonly />
                </label>
            </td>
        </tr>
        <tr>
            <td>Title</td>
            <td>
                <label>
                    <input name="title" id="title" type="text" value="${specialtyList.title}" />
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
                        <c:forEach items="${specialties}" var="spe">
                            <option id="optionParent" value="${spe.id}">${spe.title}</option>
                        </c:forEach>
                    </select>
                </label>
                <div id="parentIdNotEqualToId"></div>
            </td>
        </tr>
        <tr>
            <td></td>
            <td><button class="myButton" type="button" onclick="edit()">Submit</button></td>
        </tr>
    </table>
</form>
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


    function formIdForSpecDepartmentEdit(str) {
        <c:forEach items="${departmentList}" var="department">
        var strstrVar = '${department.title}';
        if(strVar == str)
            return '${department.id} ';
        </c:forEach>
    }
</script>
