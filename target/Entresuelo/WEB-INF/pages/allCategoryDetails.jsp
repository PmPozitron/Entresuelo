<%@include file="fragments/header.jsp" %>

<center>

    <table>

        <c:forEach var="categoryDetails" items="${categoryDetails}" >

            <tr class="theOutput">
                <th class="name"><c:out value="${categoryDetails.item.name}"></c:out></th>
                <th><c:out value="${categoryDetails.item.description}"></c:out></th>							
                </tr>

            <c:if test="${not empty categoryDetails.categories}">
                <c:forEach var="category" items="${categoryDetails.categories}">
                    <tr>
                        <th class="theHeader">Категория</th>
                        <th class="theOutput"><c:out value="${category.name}"></c:out> / <c:out value="${category.description}"></c:out></th>
                        </tr>
                </c:forEach>
            </c:if>

        </c:forEach>

    </table>	
</center>
<%@include file="fragments/footer.jsp" %>