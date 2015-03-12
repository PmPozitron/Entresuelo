<%@include file="fragments/header.jsp" %>

<center>

    <table>
        <tr class="theHeader">
            <th class="name">name</th>
            <th>description</th>			
        </tr>

        <c:forEach var="location" items="${locations}" >
            <tr class="theOutput">
                <th class="name"><c:out value="${location.name}"></c:out></th>
                <th><c:out value="${location.description}"></c:out></th>				
                </tr>
        </c:forEach>

    </table>	
</center>
<%@include file="fragments/footer.jsp" %>