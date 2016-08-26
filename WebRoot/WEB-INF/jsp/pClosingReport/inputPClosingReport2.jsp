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
	<label>填写行政处罚结案报告</label>
	<br/><br/><br/>
	<s:form action="pClosingReportAction_submitPClosingReport">
	<label style="font-size:3em">行政处罚结案报告需要填写的内容</label><br/><br/><br/><br/><br/><br/>
		
		<input type="hidden" name="taskId" value="${taskId }" />
		<input type="hidden" name="pClosingReportId" value="${pClosingReportId }"/>
		基本案情及执行情况：
		<textarea rows="20" cols="40" name="basicCase">${basicCase }</textarea>
		<core:if test="${empty paymentDate }">
			缴款日期：当事人于<input name="paymentDate" value="<s:date name="paymentDate"  format="yyyy-MM-dd"/>"/>缴纳了罚款，现已执行到位。
		</core:if>
		<core:if test="${!empty paymentDate }">
			<input type="hidden" name="paymentDateSign" value="true"/>
		</core:if>
		
		<core:if test="${!empty captainSuggest }">
		
				上次中队长驳回时的意见：<textarea rows="10"  cols="10" >${captainSuggest }</textarea>
		
			
		</core:if>
		<core:if test="${!empty legSuggest }">
			
			
				上次法制科驳回时的意见：<textarea rows="10"   cols="10" >${legSuggest }</textarea>
			
			
		</core:if>
		<core:if test="${!empty bigCaptainSuggest }">
			
				上次大队长驳回时的意见：<textarea rows="10"   cols="10" >${bigCaptainSuggest }</textarea>
			
		</core:if>
		<core:if test="${!empty ICSuggest }">
			
				上次大队长驳回时的意见：<textarea rows="10"   cols="10" >${ICSuggest }</textarea>
			
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