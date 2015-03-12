<%@include file="fragments/header.jsp" %>

<center>
    <form:form method="POST" commandName="addCategory">

        <table id="addLocationTable">
            <thead>
                <tr class="theHeader">
                    <th class="name"><fmt:message key="addCategory.enterName" /></th>
                    <th><fmt:message key="addCategory.enterDescription" /></th>
                    <th><fmt:message key="addCategory.commentary" /></th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>Введите название категории</td>
                    <td class="forInput name"><form:input cssClass="theInput" path="name" /></td>
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
