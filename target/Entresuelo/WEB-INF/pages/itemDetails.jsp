<%-- 
    Document   : itemDetails
    Created on : Mar 3, 2015, 9:33:20 PM
    Author     : Pozitron
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="taglib_includes.jsp"	%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <script src="<c:url value="/static/scripts/lib/jquery-2.1.0.js" />"></script>
        <script src="<c:url value="/static/scripts/itemDetails.js" />"></script>
        <link type="text/css" href="<c:url value="/static/styles/entresuelo.css" />" rel="stylesheet">
        <link type="text/css" href="<c:url value="/static/styles/tables.css" />" rel="stylesheet">

<!-- 	<link type="text/css" href="<c:url value="/resources/styles/addItem.css" />" rel="stylesheet" />	-->

        <%--    <%@include file="navigationHeader.jsp" %>   --%>

        <title><fmt:message key="application.title"/></title>

    </head>
    <body>
    <center>
        <table id="theTable">

            <tr>

                <td>"${itemWithLocation.name}"</td>			

            </tr>

            <tr>                       					
                <td>"${itemWithLocation.description}"</td>

            </tr>

            <tr>	                        
                <td>"${itemWithLocation.location}"</td>				
            </tr>

            <tr>
                <td>
                    <c:forEach var="category" items="${categoryDetails.categories}">
                        <c:out value="${category.name}"></c:out>&nbsp;
                    </c:forEach>
                </td>

                <td>
                    <c:forEach var="item" items="${inventoryDetails.inventory}">
                        <c:out value="${item.name}"></c:out>&nbsp;
                    </c:forEach>
                </td>

            </tr>    

        </table>
    </center>

</body>
</html>
