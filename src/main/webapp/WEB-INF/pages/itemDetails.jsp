<%@include file="fragments/header.jsp" %>

<center>
    <table id="theTable">

        <tr><form:hidden path="itemWithLocation.id"></form:hidden></tr>

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

    <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
        
        <a href="<c:url value="/editItemDetails?itemId=${itemWithLocation.id}" />">
            <c:out value="редактировать"></c:out>
            </a>

            <span>&nbsp;&nbsp;&nbsp;</span>

            <a href="<c:url value="/deleteItem?itemId=${itemWithLocation.id}" />">
            <c:out value="удалить"></c:out>
            </a>
    </c:if>
</center>
<%@include file="fragments/footer.jsp" %>