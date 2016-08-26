<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
 <%@ taglib prefix="s" uri="/struts-tags" %>
 <%@ include file="/WEB-INF/jsp/share/taglib.jsp" %>
<html>

<head>
<title>中队列表</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${pageContext.request.contextPath }/css/list-zhongdui.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/delFirm.js"></script>
</head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<div class="container">
   <div class="row">
      <div class="col-xs-11 col-sm-9 col-md-9 col-lg-9">
        <p class="bg-primary">中队列表</p>
      </div> 
    </div> 
   
   <div class="row">
       <div class="col-xs-11 col-sm-9 col-md-9 col-lg-9">
     <!--<div class="table-responsive">-->
          <table class="table table-condensed">
              <tr>
                <th class="text-center">中队的名字</th>
                <th class="text-center">中队账号</th>
                <th class="text-center">中队下的人员</th>
                <th class="text-center" colspan ="2">相关操作</th>
              </tr>
             <s:iterator value="#squadronList">
              <tr style="font-size:13px;">
                <td class="text-center">${name}</td>
                <td class="text-center">${account}</td>
                <td class="td1">
                    <s:a action="personnelAction_list">
						  <s:param name="squadronName">${name}</s:param>
						查看本队的人员
					</s:a>
                <td  class="td1"><s:a cssClass="btn btn-primary btn-lg active" action="squadronAction_editUI?id=%{id}">修改</s:a></td>
               <%--  <td  class="td1"><s:a cssClass="btn btn-primary btn-lg active" action="squadronAction_delete?id=%{id}"  onclick="return delConfirm()">删除</s:a></td> --%>
             </tr>
             </s:iterator>
         </table>
     <!-- </div>-->
       </div>
    
   </div>
   <div class="row" style="padding:0px 20px ;margin-top:-20px;">
       <div class="col-xs-11 col-sm-9 col-md-9 col-lg-9">
	 <s:a cssClass="btn btn-primary btn-lg active" action="squadronAction_addUI">添加</s:a>
	   </div>
  </div>
 </div>
</html>
