<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${sessionScope.get(\"currentUser\")}" var="currentUser" scope="page" />

<style>
    <%@include file='/META-INF/css/style.css' %>
</style>
	<script>
	    var role = ${currentUser.role};
	    alert(role+"asd");
	    console.log("asd");
	</script>
	<div class="header">
		<header>
			<div class="logo">
				<h1><a href="/Kurs2/">CALLPLACER</a></h1>
			</div>
			<nav>
				<ul>
					<li><a href="controller?command=messageHelp">Сообщения</a></li>
					<li><a href="controller?command=openHelp">Помощь</a></li>
					<li><a href="controller?command=openUsers">Пользователи</a></li>
					<li><a href="controller?command=openTariff">Тарифы</a></li>
					<li><a href="controller?command=openEquipment">Устройства</a></li>
					<li><a href="" style="color: #315768;">${currentUser.role}</a></li>
					<li><a href="login.jsp"><c:if test="${currentUser.role == null}">Войти</c:if></a></li>
					<li><a href="controller?command=logout"><c:if test="${currentUser.role != null}">Выйти</c:if></a></li>
					
				</ul>
			</nav>
		</header>
	</div>