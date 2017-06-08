<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="Header.jsp"%>
<fmt:setLocale value="${sessionScope.language}" />

<fmt:setBundle basename='messages' />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link type="text/css" rel="stylesheet"
	href="<c:url value="/resources/css.css" />" />
<title><fmt:message key='mainPage' /></title>
</head>


<body>
	<div class="container">
		<div>
			<h1>
				<fmt:message key='mainPage' />
			</h1>
			<c:if test="${isInHospital}">
				<p><fmt:message key='patientsInHospital' /></p>
			</c:if>
		</div>
		<div>
			<table>
				<thead>
					<tr class="table-header">
						<td><fmt:message key='lastName' /></td>
						<td><fmt:message key='firstName' /></td>
						<td><fmt:message key='dateOfBirth' /></td>
						<td><fmt:message key='phoneNumber' /></td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${patients}" var="patient">

						<tr>
							<td><a
								href="/hospital/Controller?command=GoToPatientInfoPage&patientId=${patient.id}">
									<c:out value="${patient.lastName}" />
							</a></td>
							<td><c:out value="${patient.firstName}" /></td>
							<td><c:out value="${patient.dateOfBirth}" /></td>
							<td><c:out value="${patient.phoneNumber}" /></td>
						</tr>


					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="form-container">
			<form class="form-search" name="FindPatientsByLastName"
				action="Controller" method="post">
				<input type="hidden" name="command" value="GoToMainPage"> 
				<label><b><fmt:message key='lastName' />:</b></label> 
				<input type="text" placeholder="<fmt:message key='lastName' />" name="lastName" required> 
				<button type="submit">
					<fmt:message key="search"></fmt:message>
				</button>
			</form>
			<form class="form-registration" name="AllPatients" action="Controller" method="post">
				<input type="hidden" name="command" value="GoToMainPage"> <input
					type="hidden" name="allPatients" value="ShowAllPatients">
				<button type="submit">
					<fmt:message key="showAllPatients"></fmt:message>
				</button>
			</form>
			<form class="form-registration" name="GoToPaientRegistration"
				action="Controller" method="post">
				<input type="hidden" name="command" value="GoToPatientRegistration">
				<button type="submit">
					<fmt:message key="patientRegistration"></fmt:message>
				</button>
			</form>
		</div>
	</div>
</body>
</html>