<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">　
<meta http-equiv="X-UA-Compatible" content="IE=edge" /> 
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>登录页面</title>
    
<link href="${pageContext.request.contextPath }/css/login.css" rel="stylesheet">
<link href="${pageContext.request.contextPath }/css/login-2.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.2.min.js"></script>

 <script type="text/javascript" src="${pageContext.request.contextPath }/js/load.js?version=1"> </script>
 <script type="text/javascript" src="${pageContext.request.contextPath }/js/checkCode.js"></script> 
 
  </head>                     

<body class="bg-hex" background="${pageContext.request.contextPath }/images/login.jpg">  

  <div class="container">  
  	 
       <div class="row body-login " > 
           
          <div class="col-md-5 col-md-offset-3 head-con" >
          
         <form class="form-horizontal">
         	<br>
             <div class="col-md-12">
            
          	<h4 class="text-center">郑州市中原区城市管理系统</h4>
            <hr>
            <br>
          </div>
          
            <div style="height: 100px;margin-left: 165px"><font color="red" id="divTip"></font></div> <br /> 
            
              <div class="form-group">
                <label for="inputEmail3" class="col-sm-2 col-sm-offset-1  control-label">角色</label>
                <div class="col-sm-6">
          <s:select cssClass="form-control" name ="identity" id="identity" list="#{'个人' : '个人', '中队' : '中队', '一般管理员' : '一般管理员', '超级管理员' : '超级管理员'}"
            listKey="key" listValue="value" /><br />
                </div>
              </div>
              <div class="form-group">
                <label for="inputPassword3" class="col-sm-2 col-sm-offset-1  control-label">账号</label>
           
                <div class="col-sm-8">
                   <input type="text" name="account" class="form-control" id="account" size="15" placeholder="账号" aria-describedby="basic-addon1" autocomplete="on"
                      onblur="isAccount(this)">
                      
                </div>
                 <div id = "account1" class="col-sm-4">
                  
                </div>
                
              </div>
             <div class="form-group">
                <label for="inputPasswrd3" class="col-sm-2 col-sm-offset-1  control-label">密码</label>
                <div class="col-sm-8">
                 <input type="password" id="password" name="password" size="15" autocomplete="off" class="form-control"
                  placeholder="密码" aria-describedby="basic-addon1" required onblur="isPassword(this)" >
                </div>
                
              </div>
               <div class="form-group">
                <label for="inputPassword3" class="col-sm-2 col-sm-offset-1  control-label">验证码</label>
                <div class="col-sm-3">
                 <input id="checkCode" class="form-control" type="text" nullmsg="" maxlength="100" value=""></input>
                 </div>     
                  <div class="col-sm-2 ui-form-explain">
                     <img id="validateImg" class="yzm-img" src="createImageAction" onclick="changeImg()"/><br/>
                  </div>
                  
                  <div class="col-sm-3" >
                     <s:a href="javascript:void(0)" onclick="changeImg()">看不清,换一张</s:a>
                   </div>
                
              </div>
              <div class="form-group">
                <div class="col-sm-2 col-sm-offset-5">
                   <input id="Button2" type="reset" class="btn btn-de" value="重置"/>
                </div>
                 <div class="col-sm-2 col-sm-offset-1">
              		  <input id="Button1" type="button"  class="btn btn-de" value="登录"/>
              		  <input id="contextPath" type="hidden" value="${pageContext.request.contextPath }">
             	 </div>
              </div>
            </form>
         </div>
      </div>
  </div>
  </body>
</html>