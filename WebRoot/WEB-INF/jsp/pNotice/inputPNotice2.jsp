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
	<label>填写行政处罚事先告知书</label>
	<br/><br/><br/>
	<s:form action="pNoticeAction_submitPNotice">
	
		<input type="hidden" name="taskId" value="${taskId }"/>
		
		<input type="hidden" name="pNoticeId" value="${id }"/><br/>
		<core:if test="${!empty accreditation.unitName }">营业执照注册号：<input name="busLicense" value="${busLicense}"><br/></core:if>
		
		违法行为内容：<textarea rows="20" cols="40" name="illegalContent">${illegalContent}</textarea><br/>
		违法行为性质：
		<select name="illegalstyleId">
			<core:forEach items="${illegalStyles }" var="illegalStyle">
				<option value="${illegalStyle.id }">${illegalStyle.name }</option>
			</core:forEach>
		</select><br/>
		罚款金额：<input name="fines" value="${fines }"/><br/>
		其他处罚信息：<input name="otherpenaltyInf" value="${otherpenaltyInf }">
		陈述申辩时限：<input name="sumitDate" value="${sumitDate }">天
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