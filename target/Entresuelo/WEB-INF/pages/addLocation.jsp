<%-- 
    Document   : addLocation
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
        <script src="<c:url value="/static/scripts/addLocation.js" />"></script>
        <link type="text/css" href="<c:url value="/static/styles/entresuelo.css" />" rel="stylesheet">
        <link type="text/css" href="<c:url value="/static/styles/tables.css" />" rel="stylesheet">
        <title><fmt:message key="addlocation.title" /></title>
    </head>
    <body>
        <form:form method="POST" commandName="addLocation">

            <table border="1" id="addLocationTable">
                <thead>
                    <tr>
                        <th><fmt:message key="addLocation.enterName" /></th>
                        <th><fmt:message key="addLocation.enterDescription" /></th>
                        <th><fmt:message key="addLocation.commentary" /></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Введите название локации</td>
                        <td class="forInput"><form:input cssClass="theInput" path="name" /></td>
                        <td><form:errors path="name" cssClass="error"></form:errors></td>
                        </tr>
                        <tr>
                            <td>Введите описание локации</td>
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
