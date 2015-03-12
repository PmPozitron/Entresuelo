<%@include file="fragments/header.jsp" %>

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
<%@include file="fragments/footer.jsp" %>