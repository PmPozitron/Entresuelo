<%@include file="fragments/header.jsp" %>

<center>

    <table>
        <tr class="theHeader">
            <th class="name">name</th>
            <th>description</th>			
        </tr>

        <c:forEach var="category" items="${categories}" >
            <tr class="theOutput">
                <th class="name"><c:out value="${category.name}"></c:out></th>
                <th><c:out value="${category.description}"></c:out></th>				
                </tr>
        </c:forEach>

    </table>	
</center>
<%@include file="fragments/footer.jsp" %>