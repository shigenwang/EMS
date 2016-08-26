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
	<label>结案报告书审核</label>
	<br/><br/><br/>
	<s:form action="pClosingReportAction_submitTask.action">
		结案报告id:<s:textfield name="id"></s:textfield><br/><br/>
	<label style="font-size:3em">结案报告书审核</label><br/><br/><br/><br/><br/><br/>
		基本案情及执行情况：
		<textarea rows="20" cols="40" name="basicCase">${basicCase }</textarea>
		<s:hidden name="taskId"></s:hidden>

		<input type="hidden" name="currentSign" value="${currentSign }"/>
		<core:if test="${!empty captainSuggest }">
			<core:if test="${currentSign=='中队长审批' }">
				上次我的审批意见：<textarea rows="10" cols="10" >${captainSuggest }</textarea>
			</core:if>
			<core:if test="${currentSign!='中队长审批' }">
				上次中队长审批意见：<textarea rows="10" cols="10" >${captainSuggest }</textarea>
			</core:if>
			
		</core:if>
		<core:if test="${!empty legSuggest }">
			<core:if test="${currentSign=='法制科审批' }">
				上次我的审批意见：<textarea rows="10" cols="10" >${legSuggest }</textarea>
			</core:if>
			<core:if test="${currentSign!='法制科审批' }">
				上次法制科长审批意见：<textarea rows="10" cols="10" >${legSuggest }</textarea>
			</core:if>
			
		</core:if>
		<core:if test="${!empty bigCaptainSuggest }">
			<core:if test="${currentSign=='大队长审批' }">
				上次我的审批意见：<textarea rows="10" cols="10" >${bigCaptainSuggest }</textarea>
			</core:if>
			<core:if test="${currentSign!='大队长审批' }">
				上次大队长审批意见：<textarea rows="10" cols="10" >${bigCaptainSuggest }</textarea>
			</core:if>
		</core:if>
		<core:if test="${!empty ICSuggest }">
			<core:if test="${currentSign=='部门领导（业委会）审批' }">
				上次我的审批意见：<textarea rows="10" cols="10" >${ICSuggest }</textarea>
			</core:if>
			<core:if test="${currentSign!='部门领导（业委会）审批' }">
				上次业委会审批意见：<textarea rows="10" cols="10" >${ICSuggest }</textarea>
			</core:if>
		</core:if>
		<core:if test="${currentSign=='法制科指导' }">
			本次意见：<textarea name="legGuideSuggest" rows="10" cols="10" ></textarea>
		</core:if>
		<core:if test="${currentSign=='中队长审批' }">
			本次意见：<textarea name="captainSuggest" rows="10" cols="10" ></textarea>
		</core:if>
		<core:if test="${currentSign=='法制科审批' }">
			本次意见：<textarea name="legSuggest" rows="10" cols="10" ></textarea>
		</core:if>
		<core:if test="${currentSign=='大队长审批' }">
			本次意见：<textarea name="bigCaptainSuggest" rows="10" cols="10" ></textarea>
		</core:if>
		<core:if test="${currentSign=='部门领导（业委会）审批' }">
			本次意见：<textarea name="ICSuggest" rows="10" cols="10" ></textarea>
		</core:if>
	
	
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