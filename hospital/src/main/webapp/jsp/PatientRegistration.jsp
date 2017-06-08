<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="Header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename='messages' />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<title>Insert title here</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/resources/css.css" />" />
</head>
<body>

	<form class="container-patient_registration" name="PatientRegistrationForm" action="Controller" method="post">
		<div class="patient-registration">
			<div class="patient-registration-form">
			<input type="hidden" name="command" value="RegisterPatient">
			<label><b><fmt:message key='firstName' />:</b></label> 
			<input type="text" placeholder="<fmt:message key='firstName' />" name="firstName" required><br />
			<label><b><fmt:message key='lastName' />:</b></label> 
			<input type="text" placeholder="<fmt:message key='lastName' />" name="lastName" required><br /> 
			<label><b><fmt:message key='dateOfBirth' />:</b></label><br />
			<input type="date" placeholder="<fmt:message key='dateOfBirth' />" name="dateOfBirth" required>
			<label> <b><fmt:message key='phoneNumber' />:</b></label>
			<input type="text" placeholder="<fmt:message key='phoneNumber' />" name="phoneNumber" required> <br />
			</div><button type="submit">
				<fmt:message key='register' />
			</button>
		</div>
	</form>
</body>
</html>