<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${sessionScope.language}" />
<fmt:setBundle basename='messages' />
<%@ include file="Header.jsp"%>
<fmt:setBundle basename='messages' />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/resources/css.css" />" />
</head>
<body>
	<div class="prescription-info">
		<div>
			<ul>
				<li><fmt:message key='prescription' />: ${prescription.name}</li>
				<li><fmt:message key='description' />:
					${prescription.description}</li>
				<li><fmt:message key='performanceDate' />:
					<fmt:formatDate type="date"
								value="${prescription.performanceDate}"/></li>
				<li><fmt:message key='prescribed' />: ${prescriber.lastName}
					${prescriber.firstName}</li>
				<c:if test="${prescription.isDone}">
					<li><fmt:message key='performeD' />: ${performer.lastName}
						${performer.firstName}</li>
				</c:if>
				<c:if test="${!prescription.isDone}">
					<li><fmt:message key='status' />: <fmt:message
							key='unperformed' /></li>
				</c:if>
			</ul>
		</div>
	</div>
</body>
</html>