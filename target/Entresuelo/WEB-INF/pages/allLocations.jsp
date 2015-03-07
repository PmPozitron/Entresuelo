<%@include file="taglib_includes.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- <script type="text/javascript" src="js/contacts.js"></script> -->
</head>
<body style="font-family: Arial; font-size:smaller;">
	<center>
	
		<table style="border-collapse: collapse;" border="1" bordercolor="#006699" width="500">
		<tr bgcolor="lightblue">
			<th>name</th>
			<th>description</th>			
		</tr>
		
		<c:forEach var="location" items="${locations}" >
			<tr>
				<th><c:out value="${location.name}"></c:out></th>
				<th><c:out value="${location.description}"></c:out></th>				
			</tr>
		</c:forEach>
		
		</table>	
	</center>
		
</body>
</html>