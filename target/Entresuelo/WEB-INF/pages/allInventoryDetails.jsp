<%@include file="taglib_includes.jsp" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" href="<c:url value="/static/styles/entresuelo.css" />" rel="stylesheet">
        <link type="text/css" href="<c:url value="/static/styles/tables.css" />" rel="stylesheet">

        <!-- <script type="text/javascript" src="js/contacts.js"></script> -->
    </head>
    <body>
    <center>

        <table>

            <c:forEach var="inventoryDetail" items="${inventoryDetails}" >

                <tr class="theOutput">
                    <th class="name"><c:out value="${inventoryDetail.container.name}"></c:out></th>
                    <th><c:out value="${inventoryDetail.container.description}"></c:out></th>							
                    </tr>
                <c:if test="${not empty inventoryDetail.inventory}">
                    <c:forEach var="item" items="${inventoryDetail.inventory}">
                        <tr>
                            <th class="theHeader">содержимое:</th>

                            <th class="theOutput"><c:out value="${item.name}"></c:out> / <c:out value="${item.description}"></c:out></th>

                            </tr>
                    </c:forEach>
                </c:if>
            </c:forEach>

        </table>	
    </center>

</body>
</html>