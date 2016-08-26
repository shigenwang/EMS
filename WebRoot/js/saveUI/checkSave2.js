//验证账号(正确格式：数字或者字母或者数字字母组合)
var accountd = true;
function checkAccount(account){
	var myreg=/^[A-Za-z0-9]\w{2,14}$/; 
	if(myreg.test(account.value)){
		$("#account").html("  ");
		accountd=true;
	}else{
		$("#account").html("<font color='red'>*请输入数字或者字母或者数字字母组合,并且长度在3-15之间!</font>");
		accountd=false;
	}
}
//验证人员名称(正确格式：姓名为2到10个汉字,英文或汉字和英文组合)
var named = true;
function checkName(name){
	var myreg=/^[\u4e00-\u9fa5A-Za-z]{2,10}$/;
	if(myreg.test(name.value)){
		$("#name").html("  ");
		named=true;
	}else{
		$("#name").html("<font color='red'>*姓名为2到10个汉字,英文或汉字和英文组合!</font>");
		named=false;
		
	}
}
//验证电话(正确格式：输入11位的数字!)
var teld = true;
function checkTel(tel){
	var myreg=/^[0-9]\d{1,10}$/;
	if(myreg.test(tel.value)){
		$("#tel").html("  ");
		teld=true;
	}else{
		$("#tel").html("<font color='red'>*电话必须是数字,长度不超过11位!</font>");
	    teld = false;
	}	
}

//验证旧密码(正确格式为：必须以字母,数字开头，长度在6-18之间，只能包含字符、数字和下划线。)
var pswd = true;
function checkPsw(psw){
	
	var myreg=/^[A-Za-z0-9]\w{5,17}$/; 
	if(myreg.test(psw.value)){
		$("#psw").html("  ");
		pswd = true;
	}else{
		$("#psw").html("<font color='red'>*必须以字母,数字开头，长度在6-18之间，只能包含英文字符、数字和下划线!</font>");
		pswd = false;
	}		
}

//验证新密码(正确格式为：必须以字母,数字开头，长度在6-18之间，只能包含字符、数字和下划线。)
var psw2 = true;
function checkNewPsw(psw){
	
	var myreg=/^[A-Za-z0-9]\w{5,17}$/; 
	if(myreg.test(psw.value)){
		$("#psw2").html("  ");
		psw2=true;
	}else{
		$("#psw2").html("<font color='red'>*必须以字母,数字开头，长度在6-18之间，只能包含字符、数字和下划线!</font>");
		 psw2 = false
	}		
}

//验证重复密码(正确格式为：必须以字母,数字开头，长度在6-18之间，只能包含字符、数字和下划线。)
var psw3 = true;
function checkRePsw(psw){
	
	var myreg=/^[A-Za-z0-9]\w{5,17}$/; 
	if(myreg.test(psw.value)){
		$("#psw3").html("  ");
		psw3=true;
	}else{
		$("#psw3").html("<font color='red'>*必须以字母,数字开头，长度在6-18之间，只能包含字符、数字和下划线!</font>");
		 psw3 = false;
	}		
}

//验证中队名称(正确格式：姓名为3到10个汉字,英文或汉字和英文组合)
var sqadronnamed = true;
function checkSqadronName(sqadronname){
	var myreg=/^[\u4e00-\u9fa5A-Za-z]{2,10}$/;
	if(myreg.test(sqadronname.value)){
		$("#sqaname").html("  ");
		sqadronnamed=true;
	}else{
		$("#sqaname").html("<font color='red'>*姓名为2到10个汉字,英文或汉字和英文组合!</font>");
		sqadronnamed = false;
	}
}


//判断输入正确添加
function personnelAdd2(){
	var userAccount=document.getElementById("userAccount").value;
	var username=document.getElementById("username").value;

	
	var telll = document.getElementById("tel2").value;
	if(telll.trim() == "") {
		teld = true;
	}
	if(username.trim()==""){
		alert("人员名称不能为空!");
		return false;
	}
	if(userAccount.trim()==""){
		alert("账号不能为空!");
		return false;
	}
	
	
	
	if(named == true && accountd == true  &&  teld == true ) {
		return true;
	} else {
		return false;
	}
}

function sumbitCheckPassword(){
	 var oldPassword=document.getElementById("oldPassword").value;
	   	var newPassword=document.getElementById("newPassword").value;
	   	var rePassword=document.getElementById("rePassword").value;
	   	if(oldPassword.trim()==""){
	   		alert("旧密码不能为空!");
	   		return false;
	   	}
	  
	   	if(newPassword.trim()==""){
	   		alert("新密码不能为空!");
	   		return false;
	   	}
	   	
		if(rePassword.trim()==""){
	   		alert("重复新密码不能为空!");
	   		return false;
	   	}
		if(pswd == true && psw2 == true  &&  psw3 == true) {
			return true;
		} else {
			return false;
		}
}

function submitCheckSquadron(){
	var sqadronAccount=document.getElementById("sqadronAccount").value;
	var sqadronname=document.getElementById("sqadronname").value;
	if(sqadronname.trim()==""){
		alert("中队名称不能为空!");
		return false;
	}
	if(sqadronAccount.trim()==""){
		alert("账号不能为空!");
		return false;
	}
	if(sqadronnamed == true && accountd == true ) {
		return true;
	} else {
		return false;
	}
}