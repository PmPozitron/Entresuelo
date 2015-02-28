<%@include file="taglib_includes.jsp" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <!-- <script type="text/javascript" src="js/contacts.js"></script> -->
    </head>
    <body style="font-family: Arial; font-size:smaller;">
    <center>

        <table style="border-collapse: collapse;" border="1" bordercolor="#006699" width="500">

            <c:forEach var="categoryDetails" items="${categoryDetails}" >

                <tr bgcolor="lightblue">
                    <th><c:out value="${categoryDetails.item.name}"></c:out></th>
                    <th><c:out value="${categoryDetails.item.description}"></c:out></th>							
                    </tr>

                <c:if test="${not empty categoryDetails.categories}">
                    <c:forEach var="category" items="${categoryDetails.categories}">
                        <tr>
                            <th></th>
                            <th><c:out value="${category.name}"></c:out> / <c:out value="${category.description}"></c:out></th>
                        </tr>
                    </c:forEach>
                </c:if>

            </c:forEach>

        </table>	
    </center>

</body>
</html>