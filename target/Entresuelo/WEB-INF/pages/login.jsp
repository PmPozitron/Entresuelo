<%@include file="taglib_includes.jsp" %>
<%@page session="true"%>

<html>
<head>
    <link type="text/css" href="<c:url value="/static/styles/login.css" />" rel="stylesheet" />
    <script src="<c:url value="/static/scripts/lib/jquery-2.1.0.js" />"></script>
    <script src="<c:url value="/static/scripts/login.js" />"></script>
    
    <title>Login Page</title>

</head>
<body>

    <h1>Spring Security Login Form (Database Authentication)</h1>

    <div id="login-box">

        <h3>Login with Username and Password</h3>

	<c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>
        
        <c:if test="${not empty msg}">
            <div class="msg">${msg}</div>
        </c:if>

	<form name='loginForm' action="<c:url value='/j_spring_security_check' />" method='POST'>
            <table>
                <tr>
                    <td>User:</td>
                    <td><input class='theInput' type='text' name='username'></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type='password' name='password' /></td>
                </tr>
                <tr>
                    <td colspan='2'><input name="submit" type="submit" value="submit" /></td>
                </tr>
            </table>
            <input class='theInput' type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

        </form>
    </div>

</body>
</html>