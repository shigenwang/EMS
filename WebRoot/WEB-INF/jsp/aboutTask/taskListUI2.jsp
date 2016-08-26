<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ include file="/js/commons.jspf" %>
  <%@ include file="/WEB-INF/jsp/share/taglib.jsp" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="css/right4.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <nav class="navbar navbar-default">
          <div class="container-fluid">
            <!-- Collect the nav links, forms, and other content for toggling -->
              <ul class="nav navbar-nav">
                <li> <core:if test="${type=='立案审批表'}">class="active" </core:if><s:a action="aboutTaskAction_findTaskList?type='立案审批表'">立案审批表任务</s:a></li>
                <li ><core:if test="${type=='行政处罚事先告知书'}">class="active" </core:if><s:a action="aboutTaskAction_findTaskList?type='行政处罚事先告知书'">行政处罚事先告知书</s:a></li>     
                <li ><core:if test="${type=='行政处罚审批表'}">class="active" </core:if><s:a action="aboutTaskAction_findTaskList?type='行政处罚审批表'">行政处罚审批表</s:a></li>    
                <li ><core:if test="${type=='行政处罚决定书'}">class="active" </core:if><s:a action="aboutTaskAction_findTaskList?type='行政处罚决定书'">行政处罚决定书</s:a></li>    
                <li ><core:if test="${type=='行政处罚结案报表'}">class="active" </core:if><s:a action="aboutTaskAction_findTaskList?type='行政处罚结案报表'">行政处罚结案报表</s:a></li>    
             </ul>
            
          </div>
    </nav>

<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
		      <tr>
		        <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
		          <tr>
		            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
		              <tr>
		                <td width="6%" height="19" valign="bottom"><div align="center"><img src="${pageContext.request.contextPath }/images/tb.gif" width="14" height="14" /></div></td>
		                <td width="94%" valign="bottom"><span class="STYLE1">个人任务管理列表</span></td>
		              </tr>
		            </table></td>
		            <td><div align="right"><span class="STYLE1">
		              </span></div></td>
		          </tr>
		        </table></td>
		      </tr>
		    </table></td>
		  </tr>
		  <tr>
		    <td><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" onmouseover="changeto()"  onmouseout="changeback()">
		      <tr>
		        <td width="15%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">任务ID</span></div></td>
		        <td width="25%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">任务名称</span></div></td>
		        <td width="20%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">创建时间</span></div></td>
		        <td width="20%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">办理人</span></div></td>
		        <td width="20%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">操作</span></div></td>
		      </tr>
		      <s:if test="#list!=null&&#list.size()>0">
		      <s:iterator value="#list">
		        <tr>
			        <td height="20" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><s:property value="id"/></div></td>
			        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><s:property value="name"/></div></td>
			        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"/></div></td>
			        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><s:property value="assignee"/></div></td>
			        <td height="20" bgcolor="#FFFFFF"><div align="center" class="STYLE21">
			        	<a href="${pageContext.request.contextPath }/aboutTaskAction_viewTaskForm.action?taskId=<s:property value="id"/>">办理任务</a>
						<a target="_blank" href="aboutTaskAction_viewCurrentImage.action?taskId=<s:property value="id"/>">查看当前流程图</a>
			        </div></td>
			    </tr> 
			    </s:iterator>
		      </s:if>
		    </table></td>
		  </tr>
	</table>
</body>
</html>