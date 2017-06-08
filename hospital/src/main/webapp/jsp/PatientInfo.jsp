
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="Header.jsp"%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename='messages' />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link type="text/css" rel="stylesheet"
	href="<c:url value="/resources/css.css" />" />
<title>Patient info</title>
</head>
<body>
	<div class="container-patient_info">
		<div class="patient-info">
			<div>
				<p>
					<fmt:message key='firstName' />
					: ${patient.firstName}
				</p>
				<p>
					<fmt:message key='lastName' />
					: ${patient.lastName}
				</p>
				<p>
					<fmt:message key='dateOfBirth' />
					: <fmt:formatDate type="date"
								value="${patient.dateOfBirth}"/>
				</p>
			</div>
			<div>
				<table>
					<thead>
						<tr>
							<td><fmt:message key='id' /></td>
							<td><fmt:message key='opened' /></td>
							<td><fmt:message key='closed' /></td>
						</tr>
					</thead>
					<tbody>
						<fmt:message key='medicalRecords' />
						<c:forEach items="${patient.medicalRecords}" var="medicalRecord">
							<tr>
								<td>
									<form class="form-medical_records" action="Controller"
										method="post">
										<input type="hidden" name="command" value="GoToMedicalRecord">
										<input type="hidden" name="medicalRecordId"
											value="${medicalRecord.id}"> <input type="hidden"
											name="patientId" value="${medicalRecord.patientId}">
										<button type="submit">
											<c:out value="${medicalRecord.id}" />
										</button>
									</form>
								</td>
								<td><fmt:formatDate type="date"
										value="${medicalRecord.opened}" /></td>
								<td><fmt:formatDate type="date"
										value="${medicalRecord.closed}" /></td>

							</tr>

						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div>
			<form class="form-create_mr" name="CreateMedicalRecord"
				action="Controller" method="post">
				<input type="hidden" name="command" value="CreateMedicalRecord">
				<input type="hidden" name="patientId" value="${patient.id}">
				<button type="submit">
					<fmt:message key="createMedicalRecord"></fmt:message>
				</button>
			</form>
		</div>
	</div>
</body>
</html>