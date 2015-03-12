<%@include file="fragments/header.jsp" %>

<center>
    <form:form method="post" commandName="editItemDetails">
        <table id="theTable">

            <tr><form:hidden path="item.id"></form:hidden></tr>

                <tr>
                    <td class="theHeader">Введите имя :</td>					
                    <td class="forInput"><form:input cssClass="theInput" path="item.name" /></td>			
                <td><form:errors path="item.name" cssClass="error"></form:errors></td>
                </tr>

                <tr>					
                    <td class="theHeader">Введите описание :</td>					
                    <td class="forInput"><form:input cssClass="theInput" path="item.description" /></td>
                <td><form:errors path="item.description" cssClass="error"></form:errors></td>				
                </tr>

                <tr>	

                    <td class="theHeader">Укажите расположение :</td>
                    <td class="forInput">				
                    <form:select id="locationSelect" cssClass="theSelect" path="item.locationId">
                        <c:forEach var="location" items="${locations}">
                            <form:option value="${location.id}" label="${location.name}" />
                        </c:forEach>
                    </form:select>
                <td><form:errors path="item.locationId" cssClass="error"></form:errors></td>


                </tr>

                <tr>
                    <td class="theHeader">Укажите категории :</td>
                    <td class="forInput">


                    <%--                <form:select path="categories" id="categoriesSelect" class="theSelect" multiple="true">
                                        <c:choose>
                                            <c:when test="${empty editItemDetails.categories}">
                                                <form:options items="${categories}" itemValue="id" itemLabel="name"></form:options>
                                            </c:when>

                        <c:otherwise>
                            <c:forEach var="category" items="${categories}">
                                <c:forEach var="itemCategory" items="${editItemDetails.categories}">                                                                   
                                    <c:choose>
                                        <c:when test="${category.id == itemCategory}">
                                            <option value="${category.id}" selected="selected">${category.name}</option>
                                        </c:when>

                                        <c:otherwise>
                                            <option value="${category.id}">${category.name}</option>\n\
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </c:forEach>
                        </c:otherwise>                            
                    </c:choose>
                </form:select>
                    --%>  

                    <form:select path="categories" items="${categoriesList}" multiple="true" itemValue="id" itemLabel="name" />
                </td>
            </tr>

            <tr>					
                <td class="theHeader">Укажите контейнер :</td>
                <td>
                    <form:select path="containerId" id="containerSelect" class="theSelect">
                        <form:option value="-1" label="не указан"/>
                        <form:options items="${containers}" itemValue="id" itemLabel="name"/>
                    </form:select>
                </td>
            </tr>

            <%--                <tr>					
                                                <td class="theHeader">Укажите контейнер :</td>
                                                <td class="forInput">				
                    <form:select id="containerSelect" class="theSelect" path="container">                                
                        <c:forEach var="container" items="${containers}">
                            <option value="${container.id}">${container.name}</option>
                        </c:forEach>
                    </form:select>
                    </td>
                    </tr>
            --%>
        </table>
        <br>
        <input type="submit" value="Сохранить" id="submit">
    </form:form>
</center>
<%@include file="fragments/footer.jsp" %>