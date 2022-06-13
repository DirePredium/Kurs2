<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<style>
    <%@include file='/META-INF/css/style.css' %>
</style>
<body>
	<jsp:include page="/META-INF/jsp/header.jsp"/>
<main>
<div class="content">
<div class="table">
    <div id="contentTable">
   			<div class="titleTable">
   					<p>Сообщение</p>
					<p>Email</p>
					<p>Статус</p>
			</div><hr>
		<c:forEach items="${messagesHelp}" var="messageHelp">
				<form name="form${messageHelp.id}" id="form${messageHelp.id}" onchange="return setButtonUpdateDisable('form${messageHelp.id}');" >
		            <input type="hidden" name="id" value="${messageHelp.id}">
		            <input type="text" name="message" value="${messageHelp.message}">
		            <input type="text" name="email"  value="${messageHelp.email}">
		            <select name="status">
			            <c:forEach items="${messageHelpStatus}" var="status">
			            	<option value="${status.toString()}" <c:if test="${status.toString() == messageHelp.status}">selected</c:if>>${status.toString()}</option>
			            </c:forEach>
					</select>
		            <input type="button" value="Обновить" class="buttonUpdate" onclick="return dataUpdate('form${messageHelp.id}', 'controller?command=updateMessageHelp')" disabled>
		            <input type="button" value="Удалить" class="buttonDelete" onclick="return dataDelete('form${messageHelp.id}', 'controller?command=deleteMessageHelp')">
		        </form><br><hr>
		</c:forEach>
	</div>
</div>
</div>
</main>
	<jsp:include page="/META-INF/jsp/footer.jsp"/>
</body>
<script src='script.js'></script>
</html>