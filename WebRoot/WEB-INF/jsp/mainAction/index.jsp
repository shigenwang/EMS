<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/js/commons.jspf" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

<title>郑州市中原区城市管理</title>
<link href="css/top.css" rel="stylesheet">

<!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>s
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>


<frameset rows="64,*" frameborder="no" border="0" framespacing="0" >
    <frame src="${pageContext.request.contextPath}/mainAction_top.action" scrolling="No" noresize="noresize" marginwidth="0" marginheight="0" >
       <frameset cols="226,*">
           <frame src="${pageContext.request.contextPath}/mainAction_left.action" frameborder="no" scrolling="No"  noresize="noresize";/>
           <%--  <frame name="rightFrame" src="${pageContext.request.contextPath}/mainAction_right.action" frameborder="no" scrolling="auto" noresize marginwidth="0" marginheight="0" />  --%>
            <s:if test="#session.loaderSign!='一般管理员' ">
                <frame name="rightFrame" src="${pageContext.request.contextPath}/aboutTaskAction_findTaskListById.action" frameborder="no" scrolling="auto" noresize marginwidth="0" marginheight="0" />  
           </s:if> 
           
            <s:if  test="#session.loaderSign=='一般管理员' ">
                 <frame name="rightFrame" src="${pageContext.request.contextPath}/aboutTaskAction_deployHome.action" frameborder="no" scrolling="auto" noresize marginwidth="0" marginheight="0" />  
            </s:if>
      
       </frameset>
</frameset>
<noframes></noframes>

<body>

</body>
</html>
