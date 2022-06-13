<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${sessionScope.get(\"errorMessage\")}" var="errorMessageSession" scope="page" />

<html>
<body>
	<h4><a href="/Kurs2/">Main page</a></h4>
	
	<hr>

	<h2>Error!</h2>
	
	<hr>
	
	<c:if test="${errorMessage}">
	 	${errorMessage}
	 	${errorMessageSession}
	</c:if>
	<c:if test="!${errorMessage}">
	 	${errorMessageSession}
	</c:if>
	<%-- page request session application --%>
	
</body>
</html>
