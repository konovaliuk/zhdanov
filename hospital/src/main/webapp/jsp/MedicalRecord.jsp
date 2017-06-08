
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<title>Medical Record</title>
</head>
<body>

	<div class="med">
		<div class="container-medical_records">
			<div class="medical_records-table">
				<table>
					<tr>
						<fmt:message key='patientInfo' />

						<td><fmt:message key='firstName' /></td>
						<td><fmt:message key='lastName' /></td>
						<td><fmt:message key='dateOfBirth' /></td>
					</tr>
					<tr>
						<td>${patient.firstName}</td>
						<td>${patient.lastName}</td>
						<td><fmt:formatDate type="date"
								value="${patient.dateOfBirth}" /></td>
					</tr>
				</table>
			</div>
			<div class="medical_records-table">
				<table>
					<thead>
						<tr>
							<td><fmt:message key='name' /></td>
							<td><fmt:message key='date' /></td>
							<td><fmt:message key='type' /></td>
							<td><fmt:message key='status' /></td>
						</tr>
					</thead>
					<tbody>
						<fmt:message key='prescriptions' />

						<c:forEach items="${medicalRecord.prescriptions}"
							var="prescription">

							<tr>
								<td><a
									href="/hospital/Controller?command=PrescriptionInfo&patientId=${patient.id}&prescriptionId=${prescription.id}">
										${prescription.name}</a></td>
								<td><fmt:formatDate type="date"
										value="${prescription.performanceDate}" /></td>
								<td>${prescription.type.type}</td>
								<td><c:if test="${medicalRecord.closed eq null }">
										<c:if test="${prescription.isDone}">
											<fmt:message key='performed' />
										</c:if>
										<c:if
											test="${!(prescription.isDone) && (sessionScope.user.userType.type eq 'Doctor')}">
											<a
												href="/hospital/Controller?command=PerformPrescription&prescriptionId=${prescription.id}">
												<fmt:message key='perform' />
											</a>
										</c:if>
										<c:if
											test="${!(prescription.isDone) && (sessionScope.user.userType.type eq 'Nurse') &&(prescription.type.type eq 'Surgery')}">
											<fmt:message key='unperformed' />
										</c:if>
										<c:if
											test="${!(prescription.isDone) && (sessionScope.user.userType.type eq 'Nurse') &&(prescription.type.type eq 'Medication' || prescription.type.type eq 'Procedure')}">
											<a
												href="/hospital/Controller?command=PerformPrescription&prescriptionId=${prescription.id}">
												<fmt:message key='perform' />
											</a>
										</c:if>
									</c:if> <c:if test="${medicalRecord.closed  ne null }">
										<c:if test="${prescription.isDone}">
											<fmt:message key='performed' />
										</c:if>
										<c:if test="${!prescription.isDone}">
											<fmt:message key='unperformed' />
										</c:if>
									</c:if></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<c:if
			test="${sessionScope.user.userType.type eq 'Doctor' && medicalRecord.closed eq null}">
			<div class="mr-button">
				<form name="NewPrescription" action="Controller" method="post">
					<input type="hidden" name="command" value="GoToNewPrescription">
					<input type="hidden" name="medicalRecordId"
						value="${medicalRecord.id}"> <select
						name="prescriptionType">
						<option value="Medication"> <fmt:message key='medication' /> </option>
						<option value="Procedure"> <fmt:message key='procedure' /> </option>
						<option value="Surgery"> <fmt:message key='surgery' /> </option>
					</select>
					<button type="submit">
						<fmt:message key="newPrescription"></fmt:message>
					</button>
				</form>
			</div>
		</c:if>

		<div class="container-medical_records">
			<div class="table-mr medical_records-table">
				<table>
					<thead>
						<tr>
							<td><fmt:message key='name' /></td>
							<td><fmt:message key='date' /></td>
							<td><fmt:message key='status' /></td>
						</tr>
					</thead>
					<tbody>
						<fmt:message key='diagnoses' />
						<c:forEach items="${medicalRecord.diagnoses}" var="diagnosis">

							<tr>
								<td>${diagnosis.name}</td>
								<td><fmt:formatDate type="date" value="${diagnosis.date}" />
								</td>
								<td><c:if test="${!diagnosis.isFinal}">
										<fmt:message key='interim' />
									</c:if> <c:if test="${diagnosis.isFinal}">
										<fmt:message key='final' />
									</c:if></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<c:if
				test="${sessionScope.user.userType.type eq 'Doctor' && medicalRecord.closed eq null}">
				<div class="table-mr">
					<form name="NewDiagnosis" action="Controller" method="post">
						<input type="hidden" name="command" value="GoToNewDiagnosis">
						<input type="hidden" name="medicalRecordId"
							value="${medicalRecord.id}">
						<button type="submit">
							<fmt:message key="newDiagnosis"></fmt:message>
						</button>
					</form>
				</div>
			</c:if>
		</div>
	</div>
</body>
</html>