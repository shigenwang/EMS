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
	<label>处罚决定书审核</label>
	<br/><br/><br/>
	<s:form action="pDecideAction_submitTask.action">
		处罚决定书id:<s:textfield name="id"></s:textfield><br/><br/>
	<label style="font-size:3em">处罚决定书审核</label><br/><br/><br/><br/><br/><br/>
		
		<s:hidden name="taskId"></s:hidden>
	
		<textarea rows="5" cols="30"  style="BORDER-BOTTOM: 0px solid; BORDER-LEFT: 0px solid; BORDER-RIGHT: 0px solid; BORDER-TOP: 0px solid;resize:none; ">${paragraph1 }</textarea>
			<p>${paragraph2 }</p>
			<p>${paragraph3 }</p>
			<p>${paragraph4 }</p>
			<p>${paragraph5 }</p>	
			<p>${paragraph6 }</p>
			<p>${paragraph7 }</p>
			<p>${paragraph8 }</p>
		<!-- 使用连线的名称作为按钮 -->
		<core:if test="${!empty legalSuggest }">
			上次我的意见：
			<textarea rows="20" cols="40">
				${legalSuggest }
			</textarea>
		</core:if>
		
		本次意见：
			<textarea rows="20" cols="40" name="legalSuggest">
			
			</textarea>
		<s:if test="#outcomeList!=null && #outcomeList.size()>0">
			<s:iterator value="outcomeList">	
				 				
				<input type="submit" name="outCome" value="<s:property/>" class="button_ok"/>
					 			
			</s:iterator>
		</s:if>
	</s:form>
</center>
</body>
</html>