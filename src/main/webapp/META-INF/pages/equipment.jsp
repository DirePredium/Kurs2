<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Equipment</title>
</head>
<body>
<jsp:include page="/META-INF/jsp/header.jsp"/>
<%-- <h1 style="color: red;">${errorTariffUpdate}</h1> --%>
<main>
<div class="content">
	<div class="table">
		<div id="contentTable">
						<div class="titleTable">
							<p>Email</p>
							<p>Устройство</p>
						</div>
						<hr>
			<c:forEach items="${userEquipment}" var="userEquipment">
	   			<form name="form${userEquipment.key}" id="form${userEquipment.key}" onchange="return setButtonUpdateDisable('form${userEquipment.key}');" >
			            <input type="hidden" name="id" value="${userEquipment.key}">
						<select name="userId">
				            <c:forEach items="${users}" var="user">
	<option value="${user.id}"  <c:if test="${user.email == userEquipment.value.key.email}">selected</c:if>>${user.email}</option>
				            </c:forEach>
						</select>
			            <select name="equipmentId">
				            <c:forEach items="${equipments}" var="equipment">
	<option value="${equipment.id}"  <c:if test="${equipment.name == userEquipment.value.value.name}">selected</c:if>>${equipment.name}</option>
				            </c:forEach>
						</select>
			            <input type="button" value="Обновить" class="buttonUpdate" onclick="return dataUpdate('form${userEquipment.key}', 'controller?command=updateUserEquipment')" disabled>
			            <input type="button" value="Удалить" class="buttonDelete" onclick="return dataDelete('form${userEquipment.key}', 'controller?command=deleteUserEquipment')">
			     </form><br><hr>
		    </c:forEach>
		</div>
	</div>
	<div class="addTable">
	<div class="add">
    	<form name="addForm" id="addForm">
	    				<select name="userId">
				            <c:forEach items="${users}" var="user">
								<option value="${user.id}">${user.email}</option>
				            </c:forEach>
						</select><br>
			            <select name="equipmentId">
				            <c:forEach items="${equipments}" var="equipment">
								<option value="${equipment.id}" >${equipment.name}</option>
				            </c:forEach>
						</select><br>
	        <input type="button" value="Добавить" class="buttonInsert" onclick="return dataAddRecord('addForm', 'controller?command=insertUserEquipment')">
	    </form>
	</div>
	</div>
</div>
</main>
<jsp:include page="/META-INF/jsp/footer.jsp"/>
</body>
<script src='script.js'></script>
</html>