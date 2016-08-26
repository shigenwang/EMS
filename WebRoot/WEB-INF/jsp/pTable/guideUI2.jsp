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
	<label>填写处罚审批表</label>
	<br/><br/><br/>
	<s:form action="pTableAction_guide">
	<label style="font-size:3em">处罚审批表需要填写的内容</label><br/><br/><br/><br/><br/><br/>
		
		<s:hidden name="taskId"></s:hidden>
		<s:hidden name="id"></s:hidden>
	
		
		<input type="submit" name="outCome" value="给予指导通过" class="button_ok"/>
				<input type="submit" name="outCome" value="给予指导不通过" class="button_ok"/>
				<br/>
				
				<core:if test="${!empty legGuideSuggest }">
		
					上次我的指导意见：<textarea rows="10" cols="10" >${legGuideSuggest }</textarea>
		
			
				</core:if>
		
				指导意见：<textarea name="legGuideSuggest" cols="10">
				
		</textarea>	 	
	</s:form>
</center>
</body>
</html>