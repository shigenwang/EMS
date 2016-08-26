<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<%@ include file="/WEB-INF/jsp/share/taglib.jsp" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>任务列表</title>
<link href="css/list-renwu.css" rel="stylesheet" type="text/css">
<style type="text/css">
th{
text-align:center; 
}
td{
text-align:center; 
}
</style>
</head>

<body>
<nav class="navbar navbar-default">
	<div class="container-fluid">
           
              <ul class="nav navbar-nav">
              	<core:forEach items="${taskTypeList }"  var="taskType">
              		<core:if test="${taskType=='accreditationSign' }">
            			  <li <core:if test="${type=='立案审批表' }">class="active"</core:if>  ><a href="${pageContext.request.contextPath }/aboutTaskAction_findTaskListById?type=立案审批表">立案审批表任务</a></li> 
            	  	</core:if>
            	  	<core:if test="${taskType=='pNoticeSign' }">
            			  <li <core:if test="${type=='事先告知书' }">class="active"</core:if> ><a href="${pageContext.request.contextPath }/aboutTaskAction_findTaskListById?type=事先告知书">处罚告知书任务</a></li>
            	 	</core:if>
            	 	<core:if test="${taskType=='pTableSign' }">
            	 		 <li <core:if test="${type=='处罚审批表' }">class="active"</core:if> ><a href="${pageContext.request.contextPath }/aboutTaskAction_findTaskListById?type=处罚审批表">处罚审批表任务</a></li> 
                    </core:if>
                    <core:if test="${taskType=='pDecideSign' }">
                		  <li <core:if test="${type=='处罚决定书' }">class="active"</core:if> ><a href="${pageContext.request.contextPath }/aboutTaskAction_findTaskListById?type=处罚决定书">处罚决定书任务</a></li>
              		 </core:if>
              		 <core:if test="${taskType=='pClosingReportSign' }">
                  			<li <core:if test="${type=='处罚结案报告' }">class="active"</core:if> ><a href="${pageContext.request.contextPath }/aboutTaskAction_findTaskListById?type=处罚结案报告">处罚结案报告任务</a></li>  
            		</core:if>
            	</core:forEach>
             </ul>
     </div><!-- /.container-fluid -->
  <!-- /.container-fluid --> 
</nav>

<div class="container">

  <%-- <p>中队:
   <select class="form-control">
      <option>1</option>
      <option>2</option>
      <option>3</option>
      <option>4</option>
      <option>5</option>
   </select> <p>
  
   <p>办理人:<input type="text" class="form-control" placeholder="Text input"></p>
   
   <button type="button" class="btn btn-default">查询</button> --%>
   
   
   
   <div class="row">
      <div class="col-xs-11 col-sm-11 col-md-11 col-lg-9 ">
        <p class="bg-primary">任务页面</p>
      </div> 
    </div> 
   
   <div class="row">
       <div class="col-xs-11 col-sm-11 col-md-11 col-lg-9">
     <!--<div class="table-responsive">-->
          <table class="table table-condensed">
              <tr>
                <th>案件编号</th>
                <th>任务到达时间</th>
                <th>所属中队</th>
                <th>办理人</th>
                <th >任务名称及办理</th>
                <th colspan="2">操作</th>     
              </tr>
              
                <core:if test="${!empty taskMap }">
		     	 <core:forEach items="${taskMap }" var="task">
	              
	              <tr style="font-size:13px;">
	                <td>${task.value.id }</td>   
	                <td><fmt:formatDate value="${task.key.createTime}"  type="both"/></td>
	                <td>${task.value.squadron.name }</td>
	                <td>${task.value.sponsor.name }&nbsp;${task.value.assistantHandle.name }</td>
	                <td>${task.key.name}</td>
	                <td><a href="${pageContext.request.contextPath }/aboutTaskAction_viewTaskForm.action?taskId=${task.key.id}">办理任务</a></td>
	                <td><a href="${pageContext.request.contextPath }/aboutTaskAction_viewCurrentImage.action?taskId=${task.key.id }" target="_blank">查看当前任务进展</a></td>
	             </tr>
           	  </core:forEach>
           	  </core:if>
           
         </table>
  
       </div>
   </div>
</div>
</body>
</html>
