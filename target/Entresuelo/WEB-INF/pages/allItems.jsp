<%@include file="taglib_includes.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- <script type="text/javascript" src="js/contacts.js"></script> -->
</head>
<body style="font-family: Arial; font-size:smaller;">
	<center>
	
<!-- 	
	<form action="searchContacts.do" method="post">		
			<table style="border-collapse: collapse;" border="0" bordercolor="#006699" width="500">
			<tr>
				<td>Enter Contact Name</td> 
				<td><input type="text" name="name"/>
					&nbsp;&nbsp;<input type="submit" value="Search"/>
					&nbsp;&nbsp;<input type="button" value="New Contact" onclick="javascript:go('saveContact.do');"/>
			</td></tr>
		</table>
	</form>
-->
	
	<table style="border-collapse: collapse;" border="1" bordercolor="#006699" width="500">
		<tr bgcolor="lightblue">
			<th>name</th>
			<th>description</th>			
			<th>location</th>	
		</tr>
		
		<c:forEach var="item" items="${items}" >
			<tr>
				<th><c:out value="${item.name}"></c:out></th>
				<th><c:out value="${item.description}"></c:out></th>
				<th><c:out value="${item.location}"></c:out></th>
			</tr>
		</c:forEach>
		
<!-- 		
		<c:if test="${empty SEARCH_CONTACTS_RESULTS_KEY}">
		<tr>
			<td colspan="4">No Results found</td>
		</tr>
		</c:if>
		<c:if test="${! empty SEARCH_CONTACTS_RESULTS_KEY}">
		
			<c:forEach var="item" items="${model.items}" >		
-->
		    <tr>
<!-- 			<td><c:out value="${contact.id}"></c:out></td>	
				<td><c:out value="${item.name}"></c:out></td>
				<td><c:out value="${item.description}"></c:out> </td>
				<td><c:out value="${item.location}"></c:out></td>
-->				
	

<!-- 			<td>
					&nbsp;<a href="updateContact.do?id=${contact.id}">Edit</a>
					&nbsp;&nbsp;<a href="javascript:deleteContact('deleteContact.do?id=${contact.id}');">Delete</a>
				</td>
-->				
			</tr>
<!--
			</c:forEach>
 		</c:if>	-->				
	</table>	
	</center>
		
</body>
</html>