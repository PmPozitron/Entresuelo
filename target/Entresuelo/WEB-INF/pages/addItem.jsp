<%@include file="fragments/header.jsp" %>

<center
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
</center>
<%@include file="fragments/footer.jsp" %>