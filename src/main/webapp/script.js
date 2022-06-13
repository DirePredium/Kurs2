var formSelect;
function sendValues(url, params){
	var getParams = encodeURI(params);
	if (getParams==null) {
		resetForm(formSelect);
		return false;
	}else{
		var http = new XMLHttpRequest();
		http.open("POST", url, true);
		console.log("getParams -- "+ getParams);
		http.setRequestHeader("Content-type","application/x-www-form-urlencoded; charset=UTF-8");
		http.send(getParams);
		http.onerror = function() {
			alert('Ошибка соединения');
		};
		http.onload = function() {
			if( http.responseText.includes("Error update")){
				alert("Неудалось обновить");
				resetForm(formSelect);
				return;
			}
			if(http.responseText.includes("Error insert")){
				alert("Не удалось добавить.");
				return;
			}
			if(http.responseText.includes("Error delete")){
				alert("Неудалось удалить");
				return;
			}
		    return;
	    };
    } 
}
async function sendValuesFetch(url, params, isReload){
	let response = await fetch(url, {
		  method: 'POST',
		  headers: {
		    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
		  },
		  body: params
	});
	let resultText = await response.text();
			if( resultText.includes("Error update")){
				alert("Неудалось обновить");
				resetForm(formSelect);
				return;
			}
			if(resultText.includes("Error insert")){
				alert("Не удалось добавить.");
				return;
			}
			if(resultText.includes("Error delete")){
				alert("Неудалось удалить");
				return;
			}
	if(isReload){
		location.reload();
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
    sendValuesFetch(url, getFormParametr(), false);
    setButtonUpdateEneble(formSelect);
    return false;
}
function dataDelete(F, url){
	if(!confirm("Вы точно хотите удалить запись?")){
		return false;
	}
	setSelectForm(F);
    sendValuesFetch(url, getFormParametr(), true);
	//location.reload();
	return false;
}
function dataAddRecord(F, url){
	if(!confirm("Вы точно хотите добавить запись?")){
		return false;
	}
	setSelectForm(F);
    sendValuesFetch(url, getFormParametr(), true);
    //location.reload();
	return false;
}
function sendMessageHelp(F, url){
	setSelectForm(F);
    sendValuesFetch(url, getFormParametr(), true);
    //location.reload();
	return false;
}

function setButtonAddState(formId){
	element = document.querySelector('#'+formId+' .buttonInsert');
    element.disabled = false;
	formData = new FormData(document.forms[formId]);
    for (var pair of formData.entries()) {
	    if(pair[1]  == null || pair[1] ==undefined || pair[1] ==""){
	    	element.disabled = true;
	    }
	}
}