<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tariff</title>
</head>
<body>
<jsp:include page="/META-INF/jsp/header.jsp"/>
<main>
	<h1 class="errorMessagePage">${errorTariffUpdate}</h1>
	<div class="center">
	<div class="tariffInfoWrapper">
		<c:forEach items="${tariffs}" var="tariff">
		<div class="tariffBlock">
	        <div class="tariffInfoTitle"><h3><span id="tariffInfoTitle">${tariff.title}</span></h3></div><br>
			<h3>СКОРОСТЬ: до <span id="tarif-info-speed">${tariff.speed}</span> мб/с</h3>
			<h3>ЦЕНА: <span id="tarif-info-price">${tariff.price}</span> грн/міс</h3>
			<img height="200" class="tarif-info-img" id="tarif-info-img" src="resources/Assets/${tariff.img_link}" alt="tarif-img">
	    	<form name="formTariff${tariff.id}" id="formTariff${tariff.id}" action="controller" method="get" onchange="return setButtonAddState('formTariff${tariff.id}');">
				<input type="hidden" name="command" value="updateUserTariff">
				<input type="hidden" name="id_tariff" value="${tariff.id}">
				<input type="email" name="email" placeholder="email">
				<input type="submit" name="submit" class="buttonInsert" value="Изменить" disabled>
			</form>
		</div>
	    </c:forEach>
	</div>
	</div>
</main>
<jsp:include page="/META-INF/jsp/footer.jsp"/>
</body>
<script src='script.js'></script>
</html>