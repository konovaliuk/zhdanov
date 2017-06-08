<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<fmt:setLocale value="${sessionScope.language}" />
<fmt:setBundle basename='messages' />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Header</title>
</head>
<body>
	<div class="form-header">
		<form class="form-to_main" name="header" action="Controller"
			method="post">
			<input type="hidden" name="command" value="GoToMainPage">
			<button type="submit">
				<fmt:message key="toMain"></fmt:message>
			</button>
		</form>
		<div class="language">
			<form class="form-сhange-language" name="header" action="Controller"
				method="post">
				<input type="hidden" name="command" value="ChangeLanguage">
				<input type="hidden" name="language" value="ua_UK">
				<button type="submit">Укр</button>
			</form>
			<form class="form-сhange-language" name="header" action="Controller"
				method="post">
				<input type="hidden" name="command" value="ChangeLanguage">
				<input type="hidden" name="language" value="ru_RU">
				<button type="submit">Рус</button>
			</form>
			<form class="form-сhange-language" name="header" action="Controller"
				method="post">
				<input type="hidden" name="command" value="ChangeLanguage">
				<input type="hidden" name="language" value="en_US">
				<button type="submit">Eng</button>
			</form>
		</div>
		<form class="form-logout" name="header" action="Controller"
			method="post">
			<input type="hidden" name="command" value="Logout">
			<button type="submit">
				<fmt:message key="logout"></fmt:message>
			</button>
		</form>

	</div>

</body>
</html>