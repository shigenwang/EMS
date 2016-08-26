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
	<label>案审委办公案件审批决定书</label>
	<br/><br/><br/>
	<s:form action="pTableAction_submitTask.action">
		处罚通知书id:<s:textfield name="id"></s:textfield><br/><br/>
	<label style="font-size:3em">处罚审批表内容</label><br/><br/><br/><br/><br/><br/>
		
		<input type="hidden" name="taskId" value="${taskId }"/>
<input type="hidden" name="currentSign" value="${currentSign }"/>
		
			事实和处理意见：
			<textarea rows="20" cols="40" name="factAndSuggestion">${factAndSuggestion }</textarea>
			
			
			<core:if test="${!empty CRCSuggest }">
					上次案审委驳回时意见：<textarea rows="10" cols="10" >${CRCSuggest }</textarea>
			</core:if>
		
		本次意见：	<textarea rows="20" cols="40" name="CRCSuggest"></textarea>
		<!-- 使用连线的名称作为按钮 -->
		
				 				
				<input type="submit" name="outCome" value="批准" class="button_ok"/>
				<input type="submit" name="outCome" value="驳回" class="button_ok"/>	 			
		
	</s:form>
</center>
</body>
</html>