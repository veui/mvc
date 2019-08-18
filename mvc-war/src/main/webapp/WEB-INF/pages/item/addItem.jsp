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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link href="<spring:url value="/resources/css/add.css" />" rel="stylesheet" type="text/css">
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
        <td>Specialty Id</td>
        <td>
            <label for="selectId">
                <select id="selectId">
                    <c:forEach items="${specialtyList}" var="spe">
                        <option id="optionDp" value="${spe.id}">${spe.id}</option>
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

<script type="text/javascript" >
    function validateFloat(float) {
        var valid = false;
        var regex = /^[1-9]\d*(((,\d{3}){1})?(\.\d{0,2})?)$/;
        if (regex.test(float)) {
            valid = true;
        }
        return valid;
    }
    function validateString(username) {
        return /^[0-9a-zA-Z_.-]+$/.test(username);
    }
    function add() {
        var isValid = true;
        var add = {
            item : $('#item').val(),
            price : $('#price').val(),
            specialtyId : $("#selectId :selected").val()
        };
        if (validateString(add.item) === false) {
            isValid = false;
        }
        if (validateFloat(add.price) === false) {
            isValid = false;
        }
        if (isValid === true) {
            $.ajax({
                url: "/item/add",
                type: 'POST',
                processData: false,
                data: JSON.stringify(add),
                contentType: 'application/json; charset=utf-8',
                dataType: 'json',
                success: function(data) {
                    if (data.stat === 1) {
                        window.location.replace("/item");
                    }
                }
            });
        } else {
            window.alert('Your data is invalid. Please enter correct data');
        }
    }
</script>
</html>
