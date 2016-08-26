// login2.jsp 的js
//密码验证(6 - 10位字符：可以是数字、大小写字母，不能为空)
var password = false;
function isPassword(s) {
	var regu = /^[A-Za-z0-9_-]{1,40}$/;      //只能是 6到10个 数字或字母
	if (regu.test(s.value)) {
		$("#password1").html("<font color:'rgba(255,0,0,0.49)'>*</font>");
		password = true;
	} else {
		$("#password1").html("<font color='rgba(255,0,0,0.49)'>*密码不合法,请输入6到10个 数字或字母!</font>");
	}
}

// 账号验证(6 - 10位字符：可以是数字、大小写字母，不能为空)
var account = false;
function isAccount(s) {
	var regu = /^[A-Za-z0-9_-]{1,30}$/;      //只能是 6到10个 数字或字母
	if (regu.test(s.value)) {
		$("#account1").html("<font color:'green'>*</font>");
		account = true;
	} else {
		$("#account1").html("<font color='rgba(255,0,0,0.49)'>*密码不合法,请输入6到10个 数字或字母!</font>");
	}
}

// 判断是否能登录
function isLegal(){
	if(account == true && password == true) {
		return true;
	} else {
		return false;
	}
}

if(window.parent!=window){
	window.parent.location.reload(true);
}

$(document).keyup(function(event){
	   if(event.keyCode==13){
		   $("#Button1").trigger("click");
	   }
});


$(function()
	       {
	           $("#Button1").click(function(){
	        	
	        	   var contextPath = $.trim($("#contextPath").attr("value"));
	        	  var identity=$("#identity").val();
	        	 // alert(identity+"****");
	        	 var checkCode=$("#checkCode").val();
	              var account=encodeURI($("#account").val());
	              var password=encodeURI($("#password").val());
	            
	              $.ajax(
	              {
	            	  type: "POST",  
	                  url:"loginAction2!login",
	                  dataType:"json",
	                  data:{
	                	  identity:identity,
	                	  account:account, 
	                	  password:password,
	                	  checkCode:checkCode},
	                  success:function(strValue)
	                  {
	                	 
	                   
	                     if(strValue=="1"){
	                    
	                    	 window.location.href = contextPath+"/index.jsp";
	                     }
	                     if(strValue=="3"){
	                    	
	                         $("#divTip").html("用户名或密码错误");
	                     }
	                     if(strValue=="2"){
	                    	 
	                    	 $("#divTip").html("请重新输入验证码");
	                     }
	                     if(strValue=="0"){
	                    	 
	                    	 $("#divTip").html("请认真核实您的登录!"); 
	                     }
	                  }
	              })
	           })
	       })


