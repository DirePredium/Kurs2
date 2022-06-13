<%@page import="com.my.db.entity.UserRole"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Users</title>
</head>
<body>
<jsp:include page="/META-INF/jsp/header.jsp"/>
<main>
<div class="content">
	<div class="table">
	    <div id="contentTable">
	    			<div class="titleTable">
						<p>Email</p>
						<p>Роль</p>
						<p>Пароль</p>
					</div>
					<hr>
			<c:forEach items="${users}" var="user">
					<form name="form${user.id}" id="form${user.id}" onchange="return setButtonUpdateDisable('form${user.id}');" >
			            <input type="hidden" name="id" value="${user.id}">
			            <input type="text" name="email"  value="${user.email}">
			            <select name="role">
				            <c:forEach items="${userRoles}" var="role">
				            	<option value="${role.toString()}" <c:if test="${role.toString() == user.role}">selected</c:if>>${role.toString()}</option>
				            </c:forEach>
						</select>
			            <input type="text" name="password"  value="${user.password}">
			            <input type="button" value="Обновить" class="buttonUpdate" onclick="return dataUpdate('form${user.id}', 'controller?command=updateUser')" disabled>
			            <input type="button" value="Удалить" class="buttonDelete" onclick="return dataDelete('form${user.id}', 'controller?command=deleteUser')">
			            </form><br><hr>
			</c:forEach>
		</div>
	</div> 
	<div class="addTable">
	<div class="add">
	    <form name="addForm" id="addForm" onchange="return setButtonAddState('addForm');">
	    	<label>Email: </label>
	        <input type="text" name="email" placeholder="email"><br>
	        <label>Роль: </label>
	        <select name="role">
		    	<c:forEach items="${userRoles}" var="role">
		            <option value="${role.toString()}">${role.toString()}</option>
		    	</c:forEach>
			</select><br>
			<label>Пароль: </label>
	        <input type="text" name="password"  placeholder="password"><br>
	        <input type="button" value="Добавить" class="buttonInsert" onclick="return dataAddRecord('addForm', 'controller?command=insertUser')" disabled>
	    </form><br>
	</div>
	</div>
</div>
</main>
<jsp:include page="/META-INF/jsp/footer.jsp"/>
</body>
<script src='script.js'></script>
</html>