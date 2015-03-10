<%@include file="taglib_includes.jsp" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" href="<c:url value="/static/styles/entresuelo.css" />" rel="stylesheet">
        <link type="text/css" href="<c:url value="/static/styles/tables.css" />" rel="stylesheet">

        <!-- <script type="text/javascript" src="js/contacts.js"></script> -->
    </head>
    <body>
    <center>

        <table>
            <tr class="theHeader">
                <th class="name">name</th>
                <th>description</th>			
                <th class="name">location</th>	
            </tr>

            <c:forEach var="item" items="${items}" >
                <tr class="theOutput">
                    <th class="name"><c:out value="${item.name}"></c:out></th>
                    <th><c:out value="${item.description}"></c:out></th>
                    <th class="name"><c:out value="${item.location}"></c:out></th>
                </tr>
            </c:forEach>            		
        </table>	
    </center>
    </body>
</html>