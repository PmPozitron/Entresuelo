<%@include file="fragments/header.jsp" %>

<center>

    <table>
        <tr class="theHeader">
            <th class="name">name</th>
            <th>description</th>			
            <th class="name">location</th>	
        </tr>

        <c:forEach var="item" items="${items}" >
            <tr class="theOutput">
                <%--                <th class="name"><c:out value="${item.name}"></c:out></th>  --%>
                <th class="name">
                    <a href="<c:url value="/getItemDetails?itemId=${item.id}" />">
                            <c:out value="${item.name}"></c:out>
                        
                    </a>
                    
                    
                </th>
                <th><c:out value="${item.description}"></c:out></th>
                <th class="name"><c:out value="${item.location}"></c:out></th>
                </tr>
        </c:forEach>            		
    </table>	
</center>
<%@include file="fragments/footer.jsp" %>