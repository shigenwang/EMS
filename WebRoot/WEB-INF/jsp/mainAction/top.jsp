<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>top</title>
<link href="${pageContext.request.contextPath}/css/top.css" rel="stylesheet" type="text/css">

</head>
<div class="container">

<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
      <div class="navbar-header">
         <a class="navbar-brand" href="#">郑州市中原区城市管理</a>
      </div>
   
      <ul class="nav navbar-collapse pull-right">
         <li><a onclick="javascript:parent.location.href='${pageContext.request.contextPath}/mainAction_index.action'" style="cursor: pointer;"><span class="glyphicon glyphicon-home"></span></a></li>
         <li></li>
         <li style="margin-top:8px; margin-right:20px;"><font size="-1">您好，${globle_user.name }</font></li>
         <li><a  href="${pageContext.request.contextPath}/loginAction2_logout.action" target="_parent"  style="cursor: hand;"/><font size="-1">退出</font></li>
      </ul>
  <!--    target="_parent"  -->
</nav>      
</div>
<script src="js/jquery-2.1.4.min.js"></script>
<script src="js/bootstrap.js"></script>
</body>
</html>
