<%@include file="fragments/header.jsp" %>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags" %>

<center>

    <table>
        <tr class="theHeader">
            <th class="name">name</th>
            <th>description</th>			
            <th class="name">location</th>	
        </tr>

        <c:forEach var="item" items="${pagedListHolder.pageList}">
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
    <br>
    <%-- // create link for pages, "~" will be replaced later on with the proper page number --%>
    <c:url value="/allItemsPaged" var="pagedLink">	
        <c:param name="page" value="~"/>
    </c:url>

    <%-- // load our paging tag, pass pagedListHolder and the link --%>
    <tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/>
</center>
<%@include file="fragments/footer.jsp" %>
