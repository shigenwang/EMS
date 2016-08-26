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
	<s:form action="pTableAction_submitPTable">
	<label style="font-size:3em">处罚审批表需要填写的内容</label><br/><br/><br/><br/><br/><br/>
		
		<input type="hidden" name="taskId" value="${taskId }">
		<input type="hidden" name="pTableId" value="${pTableId }">
				
		<%-- 当事人：
		<core:if test="${!empty pTable.accreditation.personnelName}" >
			<input   value="${pTable.accreditation.personnelName }" name="client"/>
				
		</core:if>
		
		<core:if test="${empty pTable.accreditation.personnelName}">
			<input  value="${pTable.accreditation.unitName }" name="client"/>
				
		
		 </core:if><br/>
		基本案情：
		<textarea rows="15" cols="40" name="basicCase">${basicCase }
		</textarea><br/>
		违法行为等次：
		<textarea rows="20" cols="40" name="illegalGrade">${illegalGrade }</textarea><br/>
		法律依据：<textarea rows="20" cols="40" name="legalBasis">${legalBasis }</textarea>
		<input type="submit" name="outCome" value="<s:property value="outCome"/>" class="button_ok"/> --%>
		当事人： 
		
			<input value="${client}" name="client"/><br/>
		
		基本案情：
		<textarea rows="15" cols="40" name="basicCase">${basicCase }
		</textarea><br/>
		违法行为等次：
		<textarea rows="20" cols="40" name="illegalGrade">${illegalGrade }</textarea><br/>
		法律依据：<textarea rows="20" cols="40" name="legalBasis">${legalBasis }</textarea>
		
		<core:if test="${!empty captainSuggest }">
		
				上次中队长驳回时的意见：<textarea rows="10" name="captainSuggest" cols="10" >${captainSuggest }</textarea>
		
			
		</core:if>
		<core:if test="${!empty legSuggest }">
			
			
				上次法制科驳回时的意见：<textarea rows="10"  name="legSuggest" cols="10" >${legSuggest }</textarea>
			
			
		</core:if>
		<core:if test="${!empty bigCaptainSuggest }">
			
				上次大队长驳回时的意见：<textarea rows="10"  name="bigCaptainSuggest" cols="10" >${bigCaptainSuggest }</textarea>
			
		</core:if>
		
		<core:if test="${!empty legGuideSuggest}">
			法制科指导意见：
			<textarea name="legGuideSuggest">${legGuideSuggest }</textarea>
		</core:if>
		<input type="submit" name="outCome" value="<s:property value="outCome"/>" class="button_ok"/>
	</s:form>
</center>
</body>
</html>