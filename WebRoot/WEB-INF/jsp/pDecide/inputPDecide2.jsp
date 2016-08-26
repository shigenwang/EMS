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
	<label>填写处罚决定书</label>
	<br/><br/><br/>
	<s:form action="pDecideAction_submitPDecide">
	<label style="font-size:3em">处罚决定书需要填写的内容</label><br/><br/><br/><br/><br/><br/>
		
		<input name="taskId" value="${taskId }" type="hidden"/>
		<input name="pDecideId" value="${id }" type="hidden"><br/>
	<%-- 	案件简介：本机关于<input name="caseIntroduction"  value="${pDecide.caseIntroduction }">一案立案调查<br/>
		实际罚款：<input name="actualFines" value="${pDecide.actualFines }"><br/>
		被罚者缴款银行：<input name="payBank" value="${pDecide.payBank }"/><br/>
		缴款银行账号：<input name="bankAccount" value="${pDecide.bankAccount }"><br/>
		行政复议地点一：
		<select name="adreconAddress1" >
			<core:if test="${pDecide.adreconAddress1=='中原区人民政府' }">
				<option value="中原区人民政府" select>中原区人民政府</option>
				<option value="郑州市城市管理局">郑州市城市管理局</option>
			</core:if>
			<core:if test="${pDecide.adreconAddress1=='郑州市城市管理局' }">
				<option value="郑州市城市管理局" select>郑州市城市管理局</option>
				<option value="中原区人民政府" >中原区人民政府</option>
			</core:if>
			<core:if test="${pDecide.adreconAddress1!='中原区人民政府'&&pDecide.adreconAddress1!='郑州市城市管理局' }">
				<option value="中原区人民政府" >中原区人民政府</option>
				<option value="郑州市城市管理局">郑州市城市管理局</option>
			</core:if>
		</select><br/>
		行政复议地点二：
		<select name="adreconAddress2" >
			<core:if test="${pDecide.adreconAddress2=='中原区人民政府' }">
				<option value="中原区人民政府" select>中原区人民政府</option>
				<option value="郑州市城市管理局">郑州市城市管理局</option>
			</core:if>
			<core:if test="${pDecide.adreconAddress2=='郑州市城市管理局' }">
				<option value="郑州市城市管理局">郑州市城市管理局</option>
				<option value="中原区人民政府" >中原区人民政府</option>
			</core:if>
			<core:if test="${pDecide.adreconAddress2!='中原区人民政府'&&pDecide.adreconAddress2!='郑州市城市管理局' }">
				<option value="郑州市城市管理局">郑州市城市管理局</option>
				<option value="中原区人民政府">中原区人民政府</option>
				
			</core:if>
		</select><br/> --%>
		
		案件简介：本机关于<input name="caseIntroduction"  value="${caseIntroduction }">一案立案调查<br/>
		实际罚款：<input name="actualFines" value="${actualFines }"><br/>
		被罚者缴款银行：<input name="payBank" value="${payBank }"/><br/>
		缴款银行账号：<input name="bankAccount" value="${bankAccount }"><br/>
		行政复议地点一：
		<select name="adreconAddress1" >
			<core:if test="${adreconAddress1=='中原区人民政府' }">
				<option value="中原区人民政府" select>中原区人民政府</option>
				<option value="郑州市城市管理局">郑州市城市管理局</option>
			</core:if>
			<core:if test="${adreconAddress1=='郑州市城市管理局' }">
				<option value="郑州市城市管理局" select>郑州市城市管理局</option>
				<option value="中原区人民政府" >中原区人民政府</option>
			</core:if>
			<core:if test="${adreconAddress1!='中原区人民政府'&&adreconAddress1!='郑州市城市管理局' }">
				<option value="中原区人民政府" >中原区人民政府</option>
				<option value="郑州市城市管理局">郑州市城市管理局</option>
			</core:if>
		</select><br/>
		行政复议地点二：
		<select name="adreconAddress2" >
			<core:if test="${adreconAddress2=='中原区人民政府' }">
				<option value="中原区人民政府" select>中原区人民政府</option>
				<option value="郑州市城市管理局">郑州市城市管理局</option>
			</core:if>
			<core:if test="${adreconAddress2=='郑州市城市管理局' }">
				<option value="郑州市城市管理局" select>郑州市城市管理局</option>
				<option value="中原区人民政府" >中原区人民政府</option>
			</core:if>
			<core:if test="${adreconAddress2!='中原区人民政府'&&adreconAddress2!='郑州市城市管理局' }">
				<option value="郑州市城市管理局">郑州市城市管理局</option>
				<option value="中原区人民政府">中原区人民政府</option>
				
			</core:if>
		</select><br/>
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