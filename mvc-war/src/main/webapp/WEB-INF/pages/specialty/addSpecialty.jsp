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
    <script src="<spring:url value="/resources/js/specialty/add.js" />"></script>
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
        </td>
    </tr>
    <tr>
        <td>Department</td>
        <td>
            <label for="selectId">
                <select id="selectId">
                    <c:forEach items="${departmentList}" var="department">
                        <option id="optionDp" value="${department.id}">${department.title}</option>
                    </c:forEach>
                </select>
            </label>
        </td>
    </tr>
    <tr>
        <td>Parent ID</td>
        <td>
            <label for="selectParent">
                <select id="selectParent">
                    <option id="optionParent" value=0>0</option>
                    <c:forEach items="${specialtyList}" var="spe">
                        <option id="optionParent" value="${spe.id}">${spe.id}</option>
                    </c:forEach>
                </select>
            </label>
        </td>
    </tr>
    <tr>
        <td><button type="button" onclick="add()">Submit</button></td>
    </tr>
</table>
</body>
</html>
