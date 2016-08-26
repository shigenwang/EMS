<%@page language="java" import="java.util.*" pageEncoding="utf-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/WEB-INF/jsp/share/taglib.jsp" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>汇总查询列表</title>
<link href="${pageContext.request.contextPath }/css/inquiry.css?id=1" rel="stylesheet" type="text/css">
<style type="text/css">
tr>td:nth-child(3){
	text-overflow:ellipsis; dispaly:block;
	white-space:nowrap; 
	*white-space:nowrap; 
	overflow:hidden; width:50px;
}
* {
	margin: 0;
	padding: 0;
	font-family: "宋体", Arial, "新宋体";
	font-size: 12px;
	color: #555555;
	list-style: none;
}
.clear {
	clear: both;
	height: 0;
	visibility: hidden;
}
a {
	text-decoration: none;
	cursor: pointer;
}
a:hover {
	color: red;
	text-decoration: underline;
}
a img {
	border: 0;
	vertical-align: middle;
}
</style>
<script type="text/javascript">
var fromDateFlag = false;
var toDateFlag = false;
function isSubmit(){
	fromDateFlag = validateDate("fromDate");
	toDateFlag = validateDate("toDate");
	if(fromDateFlag == true && toDateFlag == true) {
		return true;
	} else{
		return false;
	}
}

// 校验时间
function validateDate(name) {
	var dateStr = $("input[name='" + name + "']")[0].value;
	alert("dateStr : " + dateStr);
	var objRegExp = /^(\d{4})\-(\d{1,2})\-(\d{1,2})$/;
	if(!objRegExp.test(strValue)){
		return false;
	} else{
		var arrayDate = strValue.split(RegExp.$1);
		var intDay = parseInt(arrayDate[2],10);
		var intYear = parseInt(arrayDate[0],10);
		var intMonth = parseInt(arrayDate[1],10);
		alert("intDay : " + intDay);
		alert("intyear : " + intYear);
		alert("intMonth : " + intMonth);
		if(intMonth > 12 || intMonth < 1) {
			return false;
		}
		var arrayLookup = { '1' : 31,'3' : 31, '4' : 30,'5' : 31,'6' : 30,'7' : 31, 
		'8' : 31,'9' : 30,'10' : 31,'11' : 30,'12' : 31} 
	
		if(arrayLookup[parseInt(arrayDate[1])] != null) {
			if(intDay <= arrayLookup[parseInt(arrayDate[1])] && intDay != 0){
				return true;  
			}
		} 
	
		if (intMonth-2 ==0) {
			var booLeapYear = (intYear % 4 == 0 && (intYear % 100 != 0 || intYear % 400 == 0)); 
			if( ((booLeapYear && intDay <= 29) || (!booLeapYear && intDay <=28)) && intDay !=0) 
				return true;
		}
	}
	return false;  
} 

</script>
</head>

<body>
<%-- <s:debug></s:debug> --%>
<nav class="navbar navbar-default">
  <div class="container-fluid"> 
    <!-- Collect the nav links, forms, and other content for toggling -->
    <ul class="nav navbar-nav">
      <li class="active"><a href="javascript:void(0);">查找页面</a></li>
    </ul>
  </div>
  <!-- /.container-fluid --> 
</nav>
<div class="container">
<s:form action="queryAction_list">
 <div class="row">
  	<div class="col-sm-9" style="padding-top:10px;padding-left:80px;">
      <div class="col-sm-10"  >
   
          <div class="form-group  col-sm-4 ">
            <label for="exampleInputEmail2">结案时间段（开始）</label>
            <input class=" form-control laydate-icon" name="fromDate" id="start" style="min-width:16%;height: 27px;;line-height:27px;padding:4px;" value="<fmt:formatDate value="${fromDate }" type="date"/>" />
          </div>
          <div class="form-group  col-sm-4">
            <label for="exampleInputEmail2">结案时间段（截止）</label>
            <input class="form-control laydate-icon" name="toDate" id="end" style="min-width: 16%;height: 27px;line-height:27px;padding:4px;" value="<fmt:formatDate value="${toDate }" type="date"/>" />
          </div>
          <!-- squadronNameFlag; //中队的名称标
		squaPersonFlag; //承办人姓名标记 
		caseSourceIdFlag; //案情来源标记
		legNameFlag; // 法制科领导姓名标记  -->
          <s:iterator value="conditionsSet" var="condition">
          	<core:if test="${condition == 'squadronNameFlag' }">
	          <div class="form-group  col-sm-4">
	            <label for="exampleInputName2">中队:</label>
	            <s:select cssClass="form-control" list="#squadronList" listKey="name" listValue="name" name = "squadronName" headerKey="" headerValue="--请选择--" />
	          </div>
	        </core:if>
	        <core:if test="${condition == 'squaPersonFlag' }">
	          <div class="form-group  col-sm-4">
	            <label for="exampleInputEmail2">承办人</label>
	            <s:select cssClass="form-control" list="#squPerList" listKey="name" listValue="name" name="squaPerson"  headerKey="" headerValue="--请选择--" />
	          </div>
	        </core:if>
	        <core:if test="${condition == 'caseSourceIdFlag' }">
	          <div class="form-group  col-sm-4">
	            <label for="exampleInputEmail2">案件来源</label>
	            <s:select cssClass="form-control" list="#caseSourceList" listKey="id" listValue="name" name="caseSourceId" headerKey="0" headerValue="--请选择--" />
	          </div>
	        </core:if>
	        <core:if test="${condition == 'legNameFlag' }">
	          <div class="form-group  col-sm-4">
	            <label for="exampleInputEmail2">法制科领导</label>
	            <s:select cssClass="form-control" list="#legalList" listKey="name" listValue="name" name="legName" headerKey = "" headerValue="--请选择--" />
	          </div>
	        </core:if>
          </s:iterator>
      </div>
    </div>
    <div class="col-sm-1"  style="margin-top:50px;">
       	<s:submit cssClass="btn btn-default" value="查询" />
    </div>
</div>
  <div class="row" style="height:20px;" >
    <div class="col-xs-11 col-sm-9 col-md-9 col-lg-9">
      <p class="bg-primary">查询列表</p>
    </div>
  </div>
  <div class="row">
    <div class="col-xs-11 col-sm-9 col-md-9 col-lg-9"> 
      <!--<div class="table-responsive">-->
      <table width="700" class="table table-condensed table-hover  " style=" table-layout:fixed;">
      <script type="text/javascript">
				var tr = document.createElement('tr');
				var td = document.createElement('td');
				var a = document.createElement('a');
				a.href="";
				a.onclick=function(){
				};
				td.appendChild(a);
				tr.appendChild(td);
      </script>
        <tr>
          <th>案件编号</th>
          <th>当事人</th>
          <th>基本案件</th>
          <th>承办人</th>
          <th>状态</th>
          <th>罚款金额</th>
          <th>相关操作</th>
        </tr>
        <core:forEach items="${recordList }" var="bean">
          <tr>
          <td>${bean.id }</td>
          <td>
          <core:if test="${bean.unitName != null }">${bean.unitName }</core:if>
          <core:if test="${bean.personnelName != null }">${bean.personnelName }</core:if>
          </td> 
          <!-- 当内容过多时，进行省略 -->
          
          <td>${bean.baseCase }</td>
          <td>${bean.sponsor.name }、${bean.assistantHandle.name }</td>
          <td>
            <core:if test="${!empty bean.pClosingReport }">
	          	<core:if test="${empty bean.pClosingReport.bigCaptainDate}">正在处理</core:if>
	          	<core:if test="${!empty bean.pClosingReport.bigCaptainDate}">已结案</core:if>
	        </core:if>
	        <core:if test="${empty bean.pClosingReport }">正在处理</core:if>
          </td>
          <td>
            <core:if test="${!empty bean.pDecide }">
	          	<core:if test="${!empty bean.pDecide.actualFines}">${bean.pDecide.actualFines }元</core:if>
	          	<core:if test="${empty bean.pDecide.actualFines}"></core:if>
	          	
	        </core:if>
          <td>
          <a href="queryAction_queryDetail?id=${bean.id }">查看详情</a>
          </td>
       </tr>
        
        </core:forEach>
        <s:iterator value="recordList" var="bean">
      
       </s:iterator>
      </table>
      <!-- </div>--> 
    </div>
  </div>
  <!-- 分页信息 -->
<%@ include file="/WEB-INF/jsp/public/pageView.jspf" %>
 </s:form>
 </div>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath }/js/laydate.js"></script> 
<script type="text/javascript">
!function(){
	laydate.skin('molv');//切换皮肤，请查看skins下面皮肤库
	laydate({elem:'#start'});
	laydate({elem:'#end'});
}();
</script>
</body>
</html>
