console.log("js added!");
var formSelect;
function sendValues(url, params){
	var getParams = params;
	if (getParams==null) {
		resetForm(formSelect);
		return false;
	}else{
		var http = new XMLHttpRequest();
		http.open("POST", url, true);
		console.log("getParams -- "+ getParams);
		http.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		http.send(getParams);
		http.onerror = function() {
			alert('Ошибка соединения');
		};
		http.onload = function() {
			if( http.responseText.includes("Error update")){
				alert("Неудалось обновить");
				resetForm(formSelect);
			}
			if(http.responseText.includes("Error insert")){
				alert("Error insert");
			}
			if(http.responseText.includes("Error delete")){
				alert("Error delete");
			}
		    return;
	    };
    } 
}
function resetForm(formId){
	element = parent.document.getElementById(formId);
	element.reset();
}
function getFormParametr(){
	Form = document.forms[formSelect];
	formData = new FormData(Form);
	string = "";
	for (var pair of formData.entries()) {
	    if(pair[1]  == null || pair[1] ==undefined || pair[1] ==""){
	        return null;
	    }
	    string += pair[0]+"="+pair[1]+"&";
	}
	return string.slice(0,string.length-1);
}
function setSelectForm(F) {
    formSelect = F;
    return false;
}
function setButtonUpdateDisable(F){
    setSelectForm(F);
    element = document.querySelector('#'+formSelect+' .buttonUpdate');
    element.disabled = false;
}
function setButtonUpdateEneble(formElement){
    element = document.querySelector('#'+formSelect+' .buttonUpdate');
    element.disabled = true;
}
function dataUpdate(F, url){
	if(!confirm("Вы точно хотите обновить запись?")){
		return false;
	}
    setSelectForm(F);
    sendValues(url, getFormParametr());
    setButtonUpdateEneble(formSelect);
    return false;
}
function dataDelete(F, url){
	if(!confirm("Вы точно хотите удалить запись?")){
		return false;
	}
	setSelectForm(F);
    sendValues(url, getFormParametr());
	location.reload();
	return false;
}
function dataAddRecord(F, url){
	if(!confirm("Вы точно хотите добавить запись?")){
		return false;
	}
	setSelectForm(F);
    sendValues(url, getFormParametr());
    location.reload();
	return false;
}
function setButtonAddState(){
	element = document.querySelector('#addForm .buttonInsert');
    element.disabled = false;
	formData = new FormData(document.forms.addForm);
    for (var pair of formData.entries()) {
	    if(pair[1]  == null || pair[1] ==undefined || pair[1] ==""){
	    	element.disabled = true;
	    }
	}
}