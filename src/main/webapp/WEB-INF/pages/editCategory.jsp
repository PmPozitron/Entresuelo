<%@include file="fragments/header.jsp" %>

<center>
    <form:form method="POST" commandName="editCategory">

        <table id="addCategoryTable">
            <thead>
                <tr>
                    <th><fmt:message key="editCategory.enterName" /></th>
                    <th><fmt:message key="editCategory.enterDescription" /></th>
                    <th><fmt:message key="editCategory.commentary" /></th>
                </tr>
            </thead>
            <tbody>
                <tr><form:hidden path="id"></form:hidden></tr>
                    <tr>
                        <td>Введите название категории</td>
                        <td class="forInput"><form:input cssClass="theInput" path="name" /></td>
                    <td><form:errors path="name" cssClass="error"></form:errors></td>
                    </tr>
                    <tr>
                        <td>Введите описание категории</td>
                        <td class="forInput"><form:input cssClass="theInput" path="description" /></td>
                    <td><form:errors path="description" cssClass="error"></form:errors></td>
                    </tr>
                </tbody>
            </table>
            <br>
            <input type="submit" value="Сохранить" id="submit" />

    </form:form>
</center>
<%@include file="fragments/footer.jsp" %>
