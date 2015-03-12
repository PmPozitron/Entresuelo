<%@include file="fragments/header.jsp" %>

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
<%@include file="fragments/footer.jsp" %>