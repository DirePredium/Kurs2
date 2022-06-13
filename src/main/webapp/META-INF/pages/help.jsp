<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Help</title>
</head>
<body>
<jsp:include page="/META-INF/jsp/header.jsp"/>
<main>
<div class="center">
	<div class="helpForm">
		<form name="formHelp" id="formHelp">
			<input type="email" name="email" placeholder="email">
			<!-- <input type="submit" name="submit" class="buttonInsert" value="Отправить" disabled><br> -->
			<input type="button" value="Отправить" class="buttonInsert" onclick="return sendMessageHelp('formHelp', 'controller?command=sendMessageHelp')"><br>
			<textarea  name = "message" placeholder="Задайте свой вопрос и мы ответим на ваш email" ></textarea>
		</form>
	</div>
	</div>
</main>
<jsp:include page="/META-INF/jsp/footer.jsp"/>
</body>
<script src='script.js'></script>
</html>