<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/js/commons.jspf" %>
    <%@taglib prefix="s" uri="/struts-tags"%>
    <%@ include file="/WEB-INF/jsp/share/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<center><br/><br/><br/><br/><br/>
	<label>行政处罚事先告知书审核</label>
	<br/><br/><br/>
	<s:form action="pNoticeAction_submitTask.action">
		<textarea rows="5" cols="30"  style="BORDER-BOTTOM: 0px solid; BORDER-LEFT: 0px solid; BORDER-RIGHT: 0px solid; BORDER-TOP: 0px solid;resize:none; ">${paragraph1 }</textarea>
		
			<p>${paragraph2 }</p>
			<p>${paragraph3 }</p>
			<p>${paragraph4 }</p>
			<p>${paragraph5 }</p>	
		<input name="taskId" type="hidden" value="${taskId }"/>
		<core:if test="${!empty legalSuggest }">
		上次意见：<textarea rows="10" cols="10">${legalSuggest }</textarea>
		</core:if>
		意见：<textarea rows="10" cols="10" name="legalSuggest"></textarea>
		<!-- 使用连线的名称作为按钮 -->
		<s:if test="#outcomeList!=null && #outcomeList.size()>0">
			<s:iterator value="outcomeList">	
				 				
				<input type="submit" name="outCome" value="<s:property/>" class="button_ok"/>
					 			
			</s:iterator>
		</s:if>
	</s:form>
</center>
</body>
</html>