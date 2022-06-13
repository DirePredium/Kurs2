<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${sessionScope.get(\"currentUser\")}" var="currentUser" scope="page" />


<html>
<body>
<jsp:include page="/META-INF/jsp/header.jsp"/>
<main>
<div class="center">
	<div class="helpForm">
		<form action="login" method="post" style="text-align: right;">
						<label>Email: </label>
						<input type="email" name="email" placeholder="email"><br>
						<label>Пароль: </label>
						<input type="password" name="password" placeholder="password"><br>
						<input class="buttonInsert" type="submit" name="submit" value="Войти">
		</form>
	</div>
	</div>
</main>
<jsp:include page="/META-INF/jsp/footer.jsp"/>
</body>
</html>
