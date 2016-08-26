<%@page language="java" import="java.util.*" pageEncoding="utf-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/WEB-INF/jsp/share/taglib.jsp" %>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>人员页面</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.2.min.js"></script>
 <link href="${pageContext.request.contextPath }/css/list-renyuan.css?version=1" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/delFirm.js"></script>

</head>
<body>

<div class="container">

<s:form  action="personnelAction_list"  >
<%-- <s:form action="personnelAction_list" > --%>
<div class="form-inline" style="margin-top:30px;">
  <div class="form-group  col-md-4 ">
    <label for="exampleInputName2">人员的名称</label>
    <s:textfield name="name" class="form-control" id="exampleInputName2" placeholder="如：张三"></s:textfield>
  </div>
  <div class="form-group col-md-3">
    <label for="exampleInputEmail2">角色</label>
     <s:select name="roleName" cssClass="form-control"  list="#roleList"
						listKey="name" listValue="name" headerKey="" headerValue="==无=="></s:select>
  </div>
  
   <div class="form-group col-md-3">
    <label for="exampleInputEmail2">所属中队</label>
      <s:select name="squadronName" cssClass="form-control"  list="#squadronList"
						listKey="name" listValue="name" headerKey="" headerValue="==无=="></s:select>
  </div>
  <div class="form-group col-md-2 col-md-offset-0 col-sm-2 col-sm-offset-9">
     <s:submit cssClass="btn btn-default" value="查询"></s:submit>
  </div>


 <div><br><br><br><br></div>

 <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 bg-primary" style="margin:10px auto 0px 17px;">人员列表</div>
          <table class="table table-condensed col-md-12" style="margin:0px auto 0px 17px;">
         
              <tr >
                <th class="text-center">人员的名称</th>
                <th class="text-center">账号</th>
                <th class="text-center">性别</th>
                <th class="text-center">电话</th>
                <th class="text-center">角色</th>
                <th class="text-center">所属中队</th>
                <th class="text-center" colspan ="3">相关操作</th>
              </tr>
              
               <s:iterator value="recordList">
              <tr style="font-size:13px;">
                <td class="text-center">${name }</td>
                <td class="text-center">${account}</td>
                <td class="text-center">${gender}</td>
                <td class="text-center">${telephone}</td>
                <td>
                    <s:iterator value="roles">
						 ${name}&nbsp;&nbsp;&nbsp;&nbsp;
					</s:iterator>
				</td>
				
                <td class="text-center">${squadron.name}</td>
                 <td> <s:a cssClass="btn btn-primary btn-lg active" action="personnelAction_editUI?id=%{id}">修改</s:a></td>
                <td> <s:a cssClass="btn btn-primary btn-lg active" action="personnelAction_delete?id=%{id}" onclick="return delConfirm()">删除</s:a></td>
                <td> <s:a cssClass="btn btn-primary btn-lg active" action="personnelAction_initPassworid?id=%{id}" onclick="return window.confirm('你确定要初始化密码为123456吗?')">重置密码</s:a></td>
             </tr>
             </s:iterator>
         </table>
   </div></div>
 </s:form>
<div class="row" style="padding:0px 3px ;">
       <div class="col-xs-11 col-sm-9 col-md-9 col-lg-9">
	 <s:a cssClass="btn btn-primary btn-lg active" action="personnelAction_addUI">添加</s:a>
	   </div>
  </div> 
   <!-- 分页信息 -->
   <%@ include file="/WEB-INF/jsp/public/pageView.jspf" %>

 </div>
  </body>
</html>
