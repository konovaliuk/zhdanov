<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<%@ include file="Header.jsp"%>
<fmt:setBundle basename='messages' />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/resources/css.css" />" />
<title>Prescription Form</title>
</head>
<body>

	<form class="container-patient_registration" name="PrescriptionForm" action="Controller" method="post">
		<div class="patient-registration">
			<div class="patient-registration-form">
		<input type="hidden" name="command" value="CreatePrescription">
		<input type="hidden" name="prescriptionType" value="${prescriptionType}"> 
		<input type="hidden" name="medicalRecordId" value="${medicalRecordId}"> 
		<label for="name"><b><fmt:message key='name' />:</b></label> 
		<input id="name" type="text" name="name" required><br />
		<label for="description"><b><fmt:message key='description' />:</b></label> 
		<textarea id="description" type="text" name="description" required></textarea>
		<label for="date"><b><fmt:message key='date' />:</b></label>
		<c:if test="${prescriptionType eq 'Surgery'}">
			<input id="date" type="datetime-local" name="date" required>
			<br />
		</c:if>
		<c:if test="${prescriptionType eq 'Medication' || prescriptionType eq 'Procedure'}">
			<input id="date" type="date" name="date" required><br />
			<label for="timesPerDay"><b><fmt:message key='timesPerDay' />:</b></label> 
			<input id="timesPerDay" type="number" name="timesPerDay" required><br /> 
			<label for="days"><b><fmt:message key='days' />:</b></label> 
			<input id="days" type="number" name="days" required><br /> 
		</c:if>
		</div><button type="submit">
				<fmt:message key="create"></fmt:message>
		</button></div>
	</form>
</body>
</html>