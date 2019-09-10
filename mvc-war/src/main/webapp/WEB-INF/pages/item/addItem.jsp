<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@include file="/WEB-INF/pages/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Add item</title>
    <script src="<spring:url value="/resources/js/item/add.js" />"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link href="<spring:url value="/resources/css/item/add.css" />" rel="stylesheet" type="text/css">
</head>
<body>
<h1>Item Input Form</h1>
<hr />

<table>
    <tr>
        <td>Item</td>
        <td>
            <label>
                <input name="item" id="item" type="text" />
            </label>
            <div id="item-non-unique-message"></div>
        </td>
    </tr>
    <tr>
        <td>Price</td>
        <td>
            <label>
                <input name="price" id="price" type="number" />
            </label>
        </td>
    </tr>
    <tr>
        <td>Specialty</td>
        <td>
            <label for="selectId">
                <select id="selectId">
                    <c:forEach items="${specialtyList}" var="spe">
                        <option id="optionDp" value="${spe.id}">${spe.title}</option>
                    </c:forEach>
                </select>
            </label>
        </td>
    </tr>
    <tr>
        <td></td>
        <td><button type="button" onclick="add()">Submit</button></td>
    </tr>
</table>
</body>
</html>
