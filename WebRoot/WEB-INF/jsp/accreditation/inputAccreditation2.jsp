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
	<label>立案申请表填写</label>
	<br/><br/><br/>
	
	<s:form action="accreditationAction_submitAccreditation">
	<div align="right" style="width: 400">
		案由：
		<select name="summaryId" >
			<core:forEach items="${summarys }" var="summary">
					<core:if test='${summary.id==summaryId}' >
						<option value="${summary.id }" selected="selected">${summary.name }</option>
					</core:if>
					<core:if test='${summary.id!=summaryId}' >
						<option value="${summary.id }">${summary.name }</option>
					</core:if>
				</core:forEach>
		<select><br/><br/>
		
		单位名称：<s:textfield name="unitName"></s:textfield><br/>
		单位法定代表人（主要负责人）：<s:textfield name="leRepresentative" ></s:textfield><br/>
		电话：<s:textfield name="unitTel" ></s:textfield><br/>
		单位地址：<s:textfield name="unitAddress"  ></s:textfield><br/>
		
		<br/>
		<br/>
		
		<br/>
		个人名称：<s:textfield name="personnelName" ></s:textfield><br/>
		性别：<select name="sex">
				<core:forEach items="${sexs }" var="sex">
					<core:if test='${sex==selectSex}' >
						<option value="${sex }" selected="selected">${sex }</option>
					</core:if>
					<core:if test='${sex!=selectSex}' >
						<option value="${sex }">${sex }</option>
					</core:if>
				</core:forEach>
			</select>
			<br/>
			
		年龄：<s:if test="userAge==0"><s:textfield name="userAge" value=""></s:textfield></s:if>
		<s:else><s:textfield name="userAge" ></s:textfield></s:else> <br/>
		住址：<s:textfield name="userAddress"></s:textfield><br/>
		身份证号：<s:textfield name="idNumber"></s:textfield><br/>
		电话：<s:textfield name="userTel"></s:textfield><br/>
		
		来源：
		<select name="caseSourceId"  >
			<core:forEach items="${caseSources }" var="caseSource">
					<core:if test='${caseSource.id==caseSourceId}' >
						<option value="${caseSource.id }" selected="selected">${caseSource.name }</option>
					</core:if>
					<core:if test='${caseSource.id!=caseSourceId}' >
						<option value="${caseSource.id  }">${caseSource.name }</option>
					</core:if>
				</core:forEach>
		<select><br/><br/>
		
		基本案情：<textarea name="baseCase" rows="20" cols="40" >${ baseCase}</textarea><br/>
		
		
		
	
	
		
		 主办：<s:select name="sponsorId" list="personnels" listKey="id" listValue="name"></s:select><br/>
		协办：<s:select name="assistantHandleId" list="personnels" listKey="id" listValue="name"></s:select><br/> 
		</div>
		<br/>
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
		<s:hidden name="taskId"></s:hidden>
		<input type="hidden" name="accreditationId" name="captainSuggest" value="${accreditationId }"/>
		
		<input type="submit" name="outCome" value="<s:property value="outCome"/>" class="button_ok"/>
	</s:form>
</center>
</body>
</html>