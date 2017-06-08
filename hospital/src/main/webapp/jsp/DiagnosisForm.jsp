<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="Header.jsp"%>
<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename='messages' />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/resources/css.css" />" />
<title>Insert title here</title>
</head>
<body>
	<form class="container-registration" name="DiagnosisForm"
		action="Controller" method="post">
		<div class="patient-registration">
			<div class="patient-registration-form">
				<input type="hidden" name="command" value="MakeDiagnosis"> 
				<input type="hidden" name="medicalRecordId" value="${medicalRecordId}">
				<label for="name"><b><fmt:message key='name' />:</b></label> 
				<input id=name type="text" name="name" required><br /> 
				<label for="description"><b><fmt:message key='description' />:</b></label>
				<textarea id="description" name="description" required></textarea>
				<label for="checkbox"><b><fmt:message key='isFinal' />:</b></label>
				<input id="checkbox" type="checkBox" name="isFinal" value="true"><br />
			</div>
			<button  type="submit">
				<fmt:message key="confirm"></fmt:message>
			</button>
		</div>
	</form>
</body>
</html>