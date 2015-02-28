<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="taglib_includes.jsp"	%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <script src="<c:url value="/static/scripts/lib/jquery-2.1.0.js" />"></script>
        <script src="<c:url value="/static/scripts/addItem.js" />"></script>

<!-- 	<link type="text/css" href="<c:url value="/resources/styles/addItem.css" />" rel="stylesheet" />	-->

        <%--    <%@include file="navigationHeader.jsp" %>   --%>

        <title><fmt:message key="application.title"/></title>

    </head>	

    <body>

        <form:form method="post" commandName="addItem">
            <table id="theTable">

                <tr>
                    <td class="theHeader">Введите имя :</td>					
                    <td class="forInput"><form:input cssClass="theInput" path="name" /></td>			
                    <td><form:errors path="name" cssClass="error"></form:errors></td>
                    </tr>

                    <tr>					
                        <td class="theHeader">Введите описание :</td>					
                        <td class="forInput"><form:input cssClass="theInput" path="description" /></td>
                    <td><form:errors path="description" cssClass="error"></form:errors></td>				
                    </tr>

                    <tr>	

                        <td class="theHeader">Укажите расположение :</td>
                        <td class="forInput">				
                        <form:select id="locationSelect" cssClass="theSelect" path="locationId">
                            <c:forEach var="location" items="${locations}">
                                <form:option value="${location.id}" label="${location.name}" />
                            </c:forEach>
                        </form:select>
                    <td><form:errors path="locationId" cssClass="error"></form:errors></td>


                    </tr>

                    <tr>
                        <td class="theHeader">Укажите категории :</td>
                        <td class="forInput">
                        <form:select id="categorySelect" class="theSelect" multiple="multiple" path="categories">                            
                            <c:forEach var="category" items="${categories}">
                        <option value="${category.id}">${category.name}</option>
                    </c:forEach>
                </form:select>
            </td>
        </tr>

        <tr>					
            <td class="theHeader">Укажите контейнер :</td>
            <td class="forInput">				
                <form:select id="containerSelect" class="theSelect" path="container">                                
                    <c:forEach var="container" items="${containers}">
                <option value="${container.id}">${container.name}</option>
            </c:forEach>
        </form:select>
    </td>
</tr>
</table>
<br>
<input type="submit" value="Сохранить" id="submit">
</form:form>

</body>
</html>