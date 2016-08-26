<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/share/taglib.jsp" %>
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>案由页面</title>
<link href="css/list-anyou.css?version=1" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/delFirm.js">
</script>
</head>

<body>
<div class="container">
<s:form action="summaryAction_list">
  <div class="an" style="margin-top: 6%;height:40%;margin-left: 12%;">
     案由的名称：
     <s:select name="summary_id" list="#summaryList" cssClass="form-control" listKey="id" listValue="name" headerKey="" headerValue="==无==" >
     </s:select>
      <s:submit cssClass="btn btn-default btn-sm" value="查询" ></s:submit>
  </div>  
</s:form>
<div class="panel-body">     
<dl class="dl-horizontal">
  <dt>处罚种类：</dt>
  <dd>${penaltyType}</dd>
    <br/>
  <dt>违反条例：</dt>
  <dd>${vioRegulations }</dd>
    <br/>
  <dt>法律依据：</dt>
  <dd>${legalBasis }</dd>
    <br/>   
  <dt>载量阶次：</dt>
  <dd>${cutOrder }</dd>
    <br/> 
  <dt>办理时限：</dt>
  <dd>${timeLimit }</dd>
</dl>
</div>
     
     <div class="form-group col-sm-3 col-md-3">
          <s:a cssClass="btn btn-primary btn-lg active" action="summaryAction_addUI">添加</s:a>
          
          <core:if test="${summarySign!=null}">
	          <s:a cssClass="btn btn-primary btn-lg active" action="summaryAction_editUI?id=%{id}">修改</s:a>
		      <s:a cssClass="btn btn-primary btn-lg active" action="summaryAction_delete?id=%{id}" onclick="return delConfirm()">删除</s:a>
	      </core:if>
     </div>    
     
 </div>

</body>
</html>
