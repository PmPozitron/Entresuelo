<%-- 
    Document   : addCategory
    Created on : Feb 23, 2015, 10:43:39 PM
    Author     : Pozitron
--%>

<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>

<%@include file="taglib_includes.jsp"	%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="<c:url value="/static/scripts/lib/jquery-2.1.0.js" />"></script>
        <script src="<c:url value="/static/scripts/addCategory.js" />"></script>
        <link type="text/css" href="<c:url value="/static/styles/entresuelo.css" />" rel="stylesheet">
        <link type="text/css" href="<c:url value="/static/styles/tables.css" />" rel="stylesheet">
        <title><fmt:message key="addCategory.title" /></title>
    </head>
    <body>
        <form:form method="POST" commandName="addCategory">

            <table id="addLocationTable">
                <thead>
                    <tr class="theHeader">
                        <th class="name"><fmt:message key="addCategory.enterName" /></th>
                        <th><fmt:message key="addCategory.enterDescription" /></th>
                        <th><fmt:message key="addCategory.commentary" /></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Введите название категории</td>
                        <td class="forInput name"><form:input cssClass="theInput" path="name" /></td>
                        <td><form:errors path="name" cssClass="error"></form:errors></td>
                        </tr>
                        <tr>
                            <td>Введите описание категории</td>
                            <td class="forInput"><form:input cssClass="theInput" path="description" /></td>
                            <td><form:errors path="description" cssClass="error"></form:errors></td>
                        </tr>
                    </tbody>
                </table>
                <br>
                <input type="submit" value="Сохранить" id="submit" />

        </form:form>

    </body>
</html>
