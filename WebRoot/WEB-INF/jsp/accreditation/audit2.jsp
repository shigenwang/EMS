<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/js/commons.jspf" %>
    <%@ include file="/WEB-INF/jsp/share/taglib.jsp" %>
    <%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<center><br/><br/><br/><br/><br/>
	<label>填写立案申请表</label>
	<br/><br/><br/>
	<s:form action="accreditationAction_submitTask.action">
	<label style="font-size:3em">立案申请表其他内容</label><br/><br/><br/><br/><br/><br/>
		<s:hidden name="taskId"/>

		单位名称：<s:textfield name="unitName"></s:textfield><br/>
		单位法定代表人（主要负责人）：<s:textfield name="leRepresentative" ></s:textfield><br/>
		电话：<s:textfield name="unitTel" ></s:textfield><br/>
		单位地址：<s:textfield name="unitAddress"  ></s:textfield><br/>
		
		<br/>
		<br/>
		
		<br/>
		个人名称：<s:textfield name="personnelName" ></s:textfield><br/>
		性别：<input value="${sex }">
			<br/>
			
		年龄：<s:if test="userAge==0"><s:textfield name="userAge" value=""></s:textfield></s:if>
		<s:else><s:textfield name="userAge" ></s:textfield></s:else> <br/>
		住址：<s:textfield name="userAddress"></s:textfield><br/>
		身份证号：<s:textfield name="idNumber"></s:textfield><br/>
		电话：<s:textfield name="userTel"></s:textfield><br/>
		
		来源：<input value="${caseSourceName }" disabled="true"><br/><br/>
		
		基本案情：<s:textarea name="baseCase" rows="20" cols="40"></s:textarea><br/>
		
		
		
	
	
		
		主办:<input  disabled value="${sponsorName }" /><br/>
		协办:<input  disabled value="${assistantHandleName }"/>
		
		<core:if test="${!empty captainSuggest }">
			<core:if test="${currentSign=='中队长审批' }">
				上次我的审批意见：<textarea rows="10" cols="10" >${captainSuggest }</textarea>
			</core:if>
			<core:if test="${currentSign!='中队长审批' }">
				上次中队长审批意见：<textarea rows="10" cols="10" >${captainSuggest }</textarea>
			</core:if>
			
		</core:if>
		<core:if test="${!empty legSuggest }">
			<core:if test="${currentSign=='法制科领导审批' }">
				上次我的审批意见：<textarea rows="10" cols="10" >${legSuggest }</textarea>
			</core:if>
			<core:if test="${currentSign!='法制科领导审批' }">
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
		
		<input type="hidden" name="currentSign" value="${currentSign }"/>
		
		<core:if test="${currentSign=='法制科指导' }">
			意见：<textarea name="legGuideSuggest" rows="10" cols="10" ></textarea>
		</core:if>
		<core:if test="${currentSign=='中队长审批' }">
			意见：<textarea name="captainSuggest" rows="10" cols="10" ></textarea>
		</core:if>
		<core:if test="${currentSign=='法制科领导审批' }">
			意见：<textarea name="legSuggest" rows="10" cols="10" ></textarea>
		</core:if>
		<core:if test="${currentSign=='大队长审批' }">
			意见：<textarea name="bigCaptainSuggest" rows="10" cols="10" ></textarea>
		</core:if>
		
		
		<br/>
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