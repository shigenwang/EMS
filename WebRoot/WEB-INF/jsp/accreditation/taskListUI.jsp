<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ include file="/js/commons.jspf" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/css/right4.css" type="text/css" rel="stylesheet">
</head>
<body>

 <nav class="navbar navbar-default">
          <div class="container-fluid">
            <!-- Collect the nav links, forms, and other content for toggling -->
              <ul class="nav navbar-nav">
                <li class="active"><a href="right5.jsp">行政处罚告知书</a></li>
                <li ><a href="right4.jsp">行政处罚决定书</a></li>     
             </ul>
          </div><!-- /.container-fluid -->
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
		                <td width="94%" valign="bottom"><span class="STYLE1">个*人任务管理列表</span></td>
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
		      <s:iterator value="#list">。
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