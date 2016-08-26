<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/login.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-2.1.4.js"></script>
<title>登录界面</title>
<script type="text/javascript">
      $(function()
	       {
	           $("#Button1").click(function(){
	        	  var identity=$("#identity").val();
	              var account=encodeURI($("#account").val());
	              var password=encodeURI($("#password").val());
	              $.ajax(
	              {
	                  url:"loginAction2.action",
	                  dataType:"text",
	                  data:{
	                	  identity:identity,
	                	  account:account, 
	                	  password:password},
	                  success:function(strValue)
	                  {
	                     if(strValue=="true"){
	                    	 window.location.href = "${pageContext.request.contextPath}/loginTest.jsp";
	                     }
	                     if(strValue=="false"){
	                         $("#divTip").html("用户名或密码错误");
	                     }
	                  }
	              })
	           })
	       })
   
    </script>
</head>
<body>
	<div style="width: 400px; height: 200px; text-align:left; border:blank 1px solid;">

		 <div id="divTip"></div> <br />
	      	${message } <br />
		<s:form action="loginAction2_login.action" method="post">
			角色：<s:select name = "identity" list="#{1 : '个人', 2 : '中队', 3 : '一般管理员', 4 : '超级管理员'}" listKey="key" listValue="value" /><br />
			账号：<s:textfield name="account"  size="15" onblur = "isAccount(this) " /><span id = "account1" style="margin-left:5px; font-size:0.60em; font-weight:normal;"></span><br />
			密码：<s:password  name="password" size="15" onblur = "isPassword(this)" /><span id = "password1" style="margin-left:5px; font-size:0.60em; font-weight:normal;"></span><br />
			<s:submit value = "登录" onclick="return isLegal();"/>
		</s:form>
	</div>
</body>
</html>
