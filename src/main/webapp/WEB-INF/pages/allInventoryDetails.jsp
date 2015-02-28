<%@include file="taglib_includes.jsp" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <!-- <script type="text/javascript" src="js/contacts.js"></script> -->
    </head>
    <body style="font-family: Arial; font-size:smaller;">
    <center>

        <table style="border-collapse: collapse;" border="1" bordercolor="#006699" width="500">

            <c:forEach var="inventoryDetail" items="${inventoryDetails}" >

                <tr bgcolor="lightblue">
                    <th><c:out value="${inventoryDetail.container.name}"></c:out></th>
                    <th><c:out value="${inventoryDetail.container.description}"></c:out></th>							
                    </tr>
                <c:if test="${not empty inventoryDetail.inventory}">
                    <c:forEach var="item" items="${inventoryDetail.inventory}">
                        <tr>
                            <th></th>
                            <th><c:out value="${item.name}"></c:out> / <c:out value="${item.description}"></c:out></th>
                        </tr>
                    </c:forEach>
                </c:if>
            </c:forEach>

        </table>	
    </center>

</body>
</html>