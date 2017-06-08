<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${sessionScope.language}" />
<fmt:setBundle basename='messages'/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<title>Login Page</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/resources/css.css" />" />
</head>

<body>

	<form class="container-registration"  name="loginForm" action="Controller" method="post">
		<div class="autorizationForm">
			<input type="hidden" name="command" value="Login"> 
			<label><b><fmt:message key='email'/>:</b></label>
			<input type="text" placeholder="email" name="email" required> <br /> 
			<label><b><fmt:message key='password'/>:</b></label>
			<input type="password" placeholder="password" name="password" value=""> <br />
			<button type="submit"><fmt:message key='login'/></button>
		</div>
	</form>
</body>


</html>