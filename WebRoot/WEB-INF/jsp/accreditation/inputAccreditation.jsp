	<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/js/commons.jspf" %>
<%@ include file="/WEB-INF/jsp/share/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>立案审批表</title>
<link href="css/kuang.css?version=1" rel="stylesheet" type="text/css" />
<link href="css/right3.css?version=3" rel="stylesheet" type="text/css">
<link href="css/alert-handle.css?version=1" rel="stylesheet" type="text/css" />
<link href="css/alert-handle2.css?version=4" rel="stylesheet" type="text/css" />
<style type="text/css">
textarea {
	resize:none
}
</style>
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/tipswindown.js?version=1"></script>
<script type="text/javascript" src="js/document.js?version=5"></script> 
<!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>s
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<script type="text/javascript">

	
	// 预览提交
	function  preview(){
		
		var unitName = $.trim($("#unitName").attr("value"));
		var leRepresentative = $.trim($("#leRepresentative").attr("value"));
		var unitTel = $.trim($("#unitTel").attr("value"));
		
		var userTel = $.trim($("#userTel").attr("value"));
		
	
		var unitAddress = $.trim($("#unitAddress").attr("value"));
		
		
		var personnelName =$.trim($("#personnelName").attr("value"));
		var userAge = $.trim($("#userAge").attr("value"));
		
		
		
		var userAddress = $.trim($("#userAddress").attr("value"));
		var idNumber = $.trim($("#idNumber").attr("value"));
		
		var baseCase = $.trim($("#baseCase").attr("value"));
		
		var personnelSign = unitName!=""||leRepresentative!=""||unitTel!=""||unitAddress!=""
		var unitNameSign = personnelName!=""||userAge!=""||userAddress!=""||idNumber!=""||userTel!="";
		
		
		
		if(((unitName!=""&&leRepresentative!=""&&unitTel!=""&&unitAddress!="")||(personnelName!=""&&userAge!=""&&userAddress!=""&&idNumber!=""&&userTel!=""))&&baseCase!=""){
			
			if(userTel==""){
				if(isNaN(unitTel)){
					$("#errorInf").html("电话号码不合法");
					return false;
				}
			}
			if(unitTel==""){
				if(isNaN(userTel)){
					$("#errorInf").html("电话号码不合法");
					return false;
				}
				if(isNaN(userAge)||userAge<=0||userAge>=150){
					$("#errorInf").html("年龄不合法");
					return false;
				
				}
			}
			if(personnelSign&&unitNameSign){
				
				$("#errorInf").html("单位和个人信息不能同时输入");
				return false;
			}
			/* var sponsorId = $.trim($("#sponsorId").attr("value"));
			var assistantHandleId = $.trim($("#assistantHandleId").attr("value"));
			if(sponsorId==assistantHandleId){
				$("#errorInf").html("主办人和协办人不能相同");
				return false;
			} */
			
			$("form").attr("action","accreditationAction_prePDF");
			$("form").attr("target", "_blank");
			$("form").attr("method", "POST");
			$("form").submit();
		
		}else{
			$("#errorInf").html("录入信息不完整");
			return false;
		}
		
	}
	// 表单提交
	function formSubmit(){

		var unitName = $.trim($("#unitName").attr("value"));
		
		var leRepresentative = $.trim($("#leRepresentative").attr("value"));
		var unitTel = $.trim($("#unitTel").attr("value"));
		
		var userTel = $.trim($("#userTel").attr("value"));
		
	
		var unitAddress = $.trim($("#unitAddress").attr("value"));
		
		
		var personnelName =$.trim($("#personnelName").attr("value"));
		var userAge = $.trim($("#userAge").attr("value"));
			
			
		
		var userAddress = $.trim($("#userAddress").attr("value"));
		var idNumber = $.trim($("#idNumber").attr("value"));
		
		
		var baseCase = $.trim($("#baseCase").attr("value"));
		
		var personnelSign = unitName!=""||leRepresentative!=""||unitTel!=""||unitAddress!=""
		var unitNameSign = personnelName!=""||userAge!=""||userAddress!=""||idNumber!=""||userTel!="";
		
		
		if(((unitName!=""&&leRepresentative!=""&&unitTel!=""&&unitAddress!="")||(personnelName!=""&&userAge!=""&&userAddress!=""&&idNumber!=""&&userTel!=""))&&baseCase!=""){
			
			if(userTel==""){
				if(isNaN(unitTel)){
					$("#errorInf").html("电话号码不合法");
					return false;
				}
			}
			if(unitTel==""){
				if(isNaN(userTel)){
					$("#errorInf").html("电话号码不合法");
					return false;
				}
				if(isNaN(userAge)||userAge<=0||userAge>=150){
					$("#errorInf").html("年龄不合法");
					return false;
				
				}
			}
			
			if(personnelSign&&unitNameSign){
				
				$("#errorInf").html("单位和个人信息不能同时输入");
				return false;
			}
			/* var sponsorId = $.trim($("#sponsorId").attr("value"));
			var assistantHandleId = $.trim($("#assistantHandleId").attr("value"));
			if(sponsorId==assistantHandleId){
				$("#errorInf").html("主办人和协办人不能相同");
				return false;
			} */
			var idCardFile = $.trim($("#idCardFile").attr("value"));
			var enforceCard = $.trim($("#enforceCardFile").attr("value"));
			var orderChangeNotice = $.trim($("#orderChangeNoticeFile").attr("value"));
			var recordInquest = $.trim($("#recordInquestFile").attr("value"));
			var sitePhotos = $.trim($("#sitePhotosFile").attr("value"));
			var recordInv = $.trim($("#recordInvFile").attr("value"));
			var recordPaper = $.trim($("#recordPaperFile").attr("value"));
			
			var businessLicense = $.trim($("#businessLicenseFile").attr("value"));
			
			if("${id}"==""){
				if(idCardFile==""||enforceCard==""||orderChangeNotice==""||recordInquest==""||sitePhotos==""||recordInv==""||recordPaper==""){
					
					$("#errorInf").html("当事人为个人时必须上传除营业执照以外的所有文书");
					return false;

				}else{
					if(unitName!=""){
						if(businessLicense==""){
							$("#errorInf").html("当事人为单位时必须上传所有文书");
							return false;
						}
						
					}
					
				
				}
				
			}
			
			
			$("form").attr("action","accreditationAction_submitAccreditation");
			$("form").attr("method", "POST");
			$("form").attr("target", "_self");
			$("form").submit();
		}else{
			$("#errorInf").html("录入信息不完整");
			return false;
		}
		
		
	}
	
	function load(){
		
		 window.setInterval("monitor('idCardFile')",1200);
		 window.setInterval("monitor('enforceCardFile')",1200);
		 window.setInterval("monitor('orderChangeNoticeFile')",1200);
		 window.setInterval("monitor('recordInquestFile')",1200);
		 window.setInterval("monitor('sitePhotosFile')",1200);
		 window.setInterval("monitor('recordInvFile')",1200);
		 window.setInterval("monitor('recordPaperFile')",1200);
		 window.setInterval("monitor('businessLicenseFile')",1200); 
	}
	
	window.onload = load();
	
	
	function auditDocumentUI(accreditationId,documentName){
		
		$.ajax({  
			type: "POST",
	        dataType: "html",
	        url: "accreditationAction_auditDocumentUI",
		    data:
		    {
		    	accreditationId:accreditationId,
		    	documentName:documentName,  
		    },
		    success:callback
		});
		
	}
</script>


<script type="text/javascript">

var currentFlag;

$(document).ready(function(){
	$("#idCard").click(function(){

		auditDocumentUI("${id}","IdCard");
		currentFlag = "idCardFlag";
		});
});
$(document).ready(function(){
	$("#enforceCard").click(function(){
		auditDocumentUI("${id}","EnforceCard");
		currentFlag = "enforceCardFlag";
		});
}); 
$(document).ready(function(){
	$("#orderChangeNotice").click(function(){
		auditDocumentUI("${id}","OrderChangeNotice");
		currentFlag = "orderChangeNoticeFlag";
		});
}); 
$(document).ready(function(){
	$("#recordInquest").click(function(){
		auditDocumentUI("${id}","RecordInquest");
		currentFlag = "recordInquestFlag";
		});
}); 
$(document).ready(function(){
	$("#sitePhotos").click(function(){
		auditDocumentUI("${id}","SitePhotos");
		currentFlag = "sitePhotosFlag";
		});
}); 
$(document).ready(function(){
	$("#recordInv").click(function(){
		auditDocumentUI("${id}","RecordInv");
		currentFlag = "recordInvFlag";
		});
}); 
$(document).ready(function(){
	$("#recordPaper").click(function(){
		auditDocumentUI("${id}","RecordPaper");
		currentFlag = "recordPaperFlag";
		});
}); 
$(document).ready(function(){
	$("#businessLicense").click(function(){
		auditDocumentUI("${id}","BusinessLicense");
		currentFlag = "businessLicenseFlag";
		});
}); 

</script>


</head>

<body>
 <nav class="navbar navbar-default">
      <div class="container-fluid">
           
              <ul class="nav navbar-nav">
            	  <li <core:if test="${type=='立案审批表' }">class="active"</core:if>  ><a href="${pageContext.request.contextPath }/aboutTaskAction_findTaskListById?type=立案审批表">立案审批表任务</a></li> 
            	  <li <core:if test="${type=='事先告知书' }">class="active"</core:if> ><a href="${pageContext.request.contextPath }/aboutTaskAction_findTaskListById?type=事先告知书">处罚告知书任务</a></li>
            	  <li <core:if test="${type=='处罚审批表' }">class="active"</core:if> ><a href="${pageContext.request.contextPath }/aboutTaskAction_findTaskListById?type=处罚审批表">处罚审批表任务</a></li> 
                  <li <core:if test="${type=='处罚决定书' }">class="active"</core:if> ><a href="${pageContext.request.contextPath }/aboutTaskAction_findTaskListById?type=处罚决定书">处罚决定书任务</a></li>
                  <li <core:if test="${type=='处罚结案报告' }">class="active"</core:if> ><a href="${pageContext.request.contextPath }/aboutTaskAction_findTaskListById?type=处罚结案报告">处罚结案报告任务</a></li>  
            
             </ul>
     </div><!-- /.container-fluid -->
 </nav>
<form class="form-horizontal" role="form" style="margin-top:29px ; margin-left:-25px;" action="" enctype="multipart/form-data">
<div class="container">
  <div class="row">
    <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
      <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
         <div class="panel-heading">
       	  当事人
         </div>
         
           <div class="panel-body">
       
               <p style="float:left;">单位</p>
                 
                   <div class="form-group" style="margin-top:29px;">
                      <label for="firstname" class="col-xs-4 col-sm-3 col-md-3 col-lg-3 control-label">单位名称</label>
                       <div class="col-xs-8 col-sm-9 col-md-9 col-lg-9">
                         <input name="unitName" type="text" class="form-control" id="unitName" value="${unitName}">
                      </div>
                   </div>
                     
                   <div class="form-group">
                      <label for="firstname" class="col-xs-4 col-sm-3 col-md-3 col-lg-3  control-label">法定代表人</label>
                      <div class="col-xs-6 col-sm-4 col-md-4 col-lg-4">
                         <input name="leRepresentative" type="text" class="form-control" id="leRepresentative" value="${leRepresentative}">
                      </div>
                   </div>
                   
                   <div class="form-group">
                      <label for="firstname" class="col-xs-4 col-sm-3 col-md-3 col-lg-3  control-label">电话</label>
                      <div class="col-xs-6 col-sm-4 col-md-4 col-lg-4">
                         <input type="text" class="form-control" id="unitTel" name="unitTel" value="${unitTel}">
                      </div>
                   </div>
                   
                   <div class="form-group">
                      <label for="firstname" class="col-xs-4 col-sm-3 col-md-3 col-lg-3  control-label">地址</label>
                      <div class="col-xs-8 col-sm-9 col-md-9 col-lg-9">
                         <input type="text" class="form-control" id="unitAddress" name="unitAddress" value="${unitAddress}">
                      </div>
                   </div>
                   
                
               
                <p style="float:left;">个人</p>
                
                   <div class="form-group" style="margin-top:44px ;">
                      <label for="firstname" class="col-xs-4 col-sm-3 col-md-3 col-lg-3  control-label">姓名</label>
                       <div class="col-xs-6 col-sm-4 col-md-4 col-lg-4">
                         <input type="text" class="form-control" id="personnelName" name="personnelName" value="${personnelName}">
                      </div>
                   </div>
                   
                   <div class="form-group">
                      <label for="firstname" class="col-xs-4 col-sm-3 col-md-3 col-lg-3  control-label">性别</label>
                      <div class="col-xs-6 col-sm-4 col-md-4 col-lg-4">
                    	<select class="form-control" name="sex" style="height:30px">
		                    <core:forEach items="${sexs }" var="sex">
								<core:if test='${sex==selectSex}' >
									<option value="${sex }" selected="selected">${sex }</option>
								</core:if>
								<core:if test='${sex!=selectSex}' >
									<option value="${sex }">${sex }</option>
								</core:if>
							</core:forEach>
                      
                   		 </select>
                     </div>
                   </div>
                   
                   <div class="form-group">
                      <label for="firstname" class="col-xs-4 col-sm-3 col-md-3 col-lg-3  control-label">年龄</label>
                      <div class="col-xs-6 col-sm-4 col-md-4 col-lg-4">
                      	
                         	<input type="text" class="form-control" id="userAge" name="userAge" <core:if test="${userAge!=0}">value="${userAge }"</core:if>>
                         
                      </div>
                   </div>
                   
                   <div class="form-group">
                      <label for="firstname" class="col-xs-4 col-sm-3 col-md-3 col-lg-3  control-label">住址</label>
                      <div class="col-xs-8 col-sm-9 col-md-9 col-lg-9">
                         <input type="text" class="form-control" id="userAddress" name="userAddress" value="${userAddress}">
                      </div>
                   </div>
                   
                   <div class="form-group">
                      <label for="firstname" class="col-xs-4 col-sm-3 col-md-3  col-lg-3  control-label">身份证号</label>
                      <div class="col-xs-8 col-sm-9 col-md-9 col-lg-9">
                         <input type="text" class="form-control" id="idNumber"  name="idNumber" value="${idNumber}">
                      </div>
                   </div>
                   
                   <div class="form-group">
                      <label for="firstname" class="col-xs-4 col-sm-3 col-md-3 col-lg-3  control-label">电话</label>
                      <div class="col-xs-6 col-sm-4 col-md-4 col-lg-4">
                         <input type="text" class="form-control" id="userTel" name="userTel" value="${userTel}">
                      </div>
                   </div>
               
           </div>
          </div>   
       </div> 
      <div class="row">
       <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
        <p class="bg-primary">案由</p>
           <div class="panel-footer" style="margin-top: 0" >
           
           <select class="form-control" style="height: 30px;margin-top: 10px;" name="summaryId">
             <core:forEach items="${summarys }" var="sm">
					<core:if test='${sm.id==summary.id}' >
						<option value="${sm.id }" selected="selected">${sm.name }</option>
					</core:if>
					<core:if test='${sm.id!=summary.id}' >
						<option value="${sm.id }">${sm.name }</option>
					</core:if>
				</core:forEach>
           </select>
           </div>
          </div>
          
          
      </div>
      
           <div class="row">
       <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
          <p class="bg-primary">来源</p>
           <div class="panel-footer" style="margin-top: 0" >
           
           <select class="form-control" style="height: 30px;margin-top: 10px;" name="caseSourceId">
             <core:forEach items="${caseSources }" var="cs">
					<core:if test='${cs.id==caseSource.id}' >
						<option value="${cs.id }" selected="selected">${cs.name }</option>
					</core:if>
					<core:if test='${cs.id!=caseSource.id}' >
						<option value="${cs.id  }">${cs.name }</option>
					</core:if>
				</core:forEach>
           </select>
           </div>
       </div>
       </div>
    </div>
    
    
    
    
     
    <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
  
       <div class="row">
       <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
          <p class="bg-primary">基本案情</p>
          <textarea  class="form-control" style="margin-top: 0" rows="13"  name="baseCase" id="baseCase">${baseCase }</textarea>
          </div>
       </div> 
     
       
		
        <div class="row">
       <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
          <p class="bg-primary">主办</p>
           <div class="panel-footer" style="margin-top: 0" >
           
           <select  style="height: 35px;margin-top: 10px;"  class="form-control" name="sponsorId" id="sponsorId">
             <core:forEach items="${personnels }" var="personnel" >
					<core:if test='${personnel.id==sponsor.id}' >
						<option value="${personnel.id }" selected="selected">${personnel.name }</option>
					</core:if>
					<core:if test='${personnel.id!=sponsor.id}' >
						<option value="${personnel.id }">${personnel.name }</option>
					</core:if>
			 </core:forEach>
           </select>
           </div>
       </div>
       </div>
       
        <div class="row">
       <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
          <p class="bg-primary">协办</p>
           <div class="panel-footer" style="margin-top: 0" >
           
           <select class="form-control" style="height: 35px;margin-top: 10px;" name="assistantHandleId" id="assistantHandleId">
             <core:forEach items="${personnels }" var="personnel">
					<core:if test='${personnel.id==assistantHandle.id}' >
						<option value="${personnel.id }" selected="selected">${personnel.name }</option>
					</core:if>
					<core:if test='${personnel.id!=assistantHandle.id}' >
						<option value="${personnel.id }">${personnel.name }</option>
					</core:if>
				</core:forEach>
           </select>
           </div>
       </div>
       </div>
       
        <core:if test="${!empty captainSuggest }">
		
				 <div class="row">
      	 			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12"  >
          				<p class="bg-primary">上次中队长驳回时的意见</p>
          			<textarea class="form-control" rows="10"  style="margin-top: 0;background-color: white" readonly="readonly">${captainSuggest }</textarea>
         		 </div>
      			 </div>
			
		</core:if>
		<core:if test="${!empty legSuggest }">
			
			
				<div class="row">
      	 			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" >
          				<p class="bg-primary">上次法制科驳回时的意见</p>
          			<textarea class="form-control" rows="10"  style="margin-top: 0;background-color: white" readonly="readonly">${legSuggest }</textarea>
         		 </div>
         		 </div>

		</core:if>
		<core:if test="${!empty bigCaptainSuggest }">
			
				<div class="row">
      	 			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12"  >
          				<p class="bg-primary">上次大队长驳回时的意见</p>
          			<textarea class="form-control" rows="10"   style="margin-top: 0;background-color: white" readonly="readonly">${bigCaptainSuggest }</textarea>
         		 </div>
         		 </div>
			
		</core:if>
		
		<core:if test="${!empty legGuideSuggest}">
			
			<div class="row">
      	 			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12"  >
          				<p class="bg-primary">法制科指导意见</p>
          			<textarea class="form-control" rows="10" style="margin-top: 0;background-color: white" readonly="readonly">${legGuideSuggest }</textarea>
         		 </div>
         	</div>
		</core:if>
		
		<input name="taskId" value="${taskId }" type="hidden">
		<input type="hidden" name="accreditationId" name="captainSuggest" value="${id }"/>
		
		<div class="form-group col-md-1 col-md-offset-0" style="margin-top:20px;">
       		<input class="btn btn-primary" type="submit" onclick="return formSubmit()" name="outCome" value="<s:property value="outCome"/>">
        </div>
        
		<div class="form-group col-md-10 col-md-offset-2" style="margin-top:20px;">
       		<input class="btn btn-primary"  type="button" onclick="return preview()"  value="预览">
        </div>
       
        
           <div class="form-group" style="margin-top:44px ;">
                      <label id="errorInf" style="font-size: 12px;color: red" for="firstname" class="col-xs-4 col-sm-10 col-md-10 col-lg-7  control-label">${errorInf}</label>
                       
           </div>
     </div>
  </div>
</div>

   
	
	<div class="qqserver">

	<div class="qqserver_fold">
		<div></div>
	</div>
	
	<div class="qqserver-body" style="display: block;">
		<div class="qqserver-header">
			<div></div>
			<span class="qqserver_arrow"></span>
            <div class="title">立案审批表文书</div>
		</div>
		<ul>   
		
		
			<li><img id="idCardImg" <core:if test="${idCardFlag }">src="images/alert/20150729042348932_easyicon_net_32.png"</core:if><core:if test="${!idCardFlag }">src="images/alert/cuohao.png"</core:if>/>
			
				<div class="wenzi">当事人身份证</div> 
				<core:if test="${empty idCardFlag }">
					<div class="anniu2" >
						<input type="file" class="file" name="idCard" id="idCardFile" /><label id="idCardFileShow" style="font-size: 10px;margin-top: 10">上传</label>
					</div>
					
				</core:if>
				<core:if test="${!empty idCardFlag&&!idCardFlag }">
				  <div class="anniu3">
							<a id="idCard" href="javascript:void(0)" >被驳回查看</a>
				  </div>
					<div class="anniu2">
						<input type="file" class="file" name="idCard" id="idCardFile"/><label id="idCardFileShow" style="font-size: 10px;margin-top: 6">上传</label>
					</div>
				</core:if>
				<core:if test="${idCardFlag }">
				  <div class="anniu3" >
							<a id="idCard" href="javascript:void(0)" >已通过查看</a>
				  </div>
				</core:if>
			</li>
			
			
			<li><img id="enforceCardImg" <core:if test="${enforceCardFlag }">src="images/alert/20150729042348932_easyicon_net_32.png"</core:if><core:if test="${!enforceCardFlag }">src="images/alert/cuohao.png"</core:if>/>
			
				<div class="wenzi">执法证</div> 
				
				<core:if test="${empty enforceCardFlag }">
					<div class="anniu2" >
						<input type="file" class="file" name="enforceCard" id="enforceCardFile" multiple="multiple"/><label id="enforceCardFileShow" style="font-size: 10px;margin-top: 10">上传</label>
					</div>
					
				</core:if>
				<core:if test="${!empty enforceCardFlag&&!enforceCardFlag }">
				   	<div class="anniu3">
						<a id="enforceCard" href="javascript:void(0);">被驳回查看</a>
					</div>
					<div class="anniu2" >
						<input type="file" class="file" name="enforceCard" id="enforceCardFile" multiple="multiple"/><label id="enforceCardFileShow" style="font-size: 10px;margin-top: 6">上传</label>
					</div>
				</core:if>
				
				<core:if test="${enforceCardFlag }">
				  <div class="anniu3">
						<a id="enforceCard" href="javascript:void(0);">已通过查看</a>
				  </div>
				</core:if>
				
			</li>
			<li><img id="orderChangeNoticeImg" <core:if test="${orderChangeNoticeFlag }">src="images/alert/20150729042348932_easyicon_net_32.png"</core:if><core:if test="${!orderChangeNoticeFlag }">src="images/alert/cuohao.png"</core:if>/>
			
				<div class="wenzi">责令改正通知书</div> 
				<core:if test="${empty orderChangeNoticeFlag }">
					<div class="anniu2" >
						<input type="file" class="file" name="orderChangeNotice" id="orderChangeNoticeFile" multiple="multiple"/><label id="orderChangeNoticeFileShow" style="font-size: 10px;margin-top: 10">上传</label>
					</div>
					
				</core:if>
				<core:if test="${!empty orderChangeNoticeFlag&&!orderChangeNoticeFlag }">
				  <div class="anniu3">
						<a id="orderChangeNotice" href="javascript:void(0)">被驳回查看</a>
					</div>
					<div class="anniu2" >
						<input type="file" class="file" name="orderChangeNotice" id="orderChangeNoticeFile" multiple="multiple"/><label id="orderChangeNoticeFileShow" style="font-size: 10px;margin-top: 6">上传</label>
					</div>
				</core:if>
				<core:if test="${orderChangeNoticeFlag }">
				  <div class="anniu3">
						<a id="orderChangeNotice" href="javascript:void(0)">已通过查看</a>
				  </div>
				</core:if>
				
			</li>
			
			<li><img id="recordInquestImg" <core:if test="${recordInquestFlag }">src="images/alert/20150729042348932_easyicon_net_32.png"</core:if><core:if test="${!recordInquestFlag }">src="images/alert/cuohao.png"</core:if>/>
				
				<div class="wenzi">现场勘验笔录</div> 
				<core:if test="${empty recordInquestFlag }">
					<div class="anniu2" >
						<input type="file" class="file" name="recordInquest" id="recordInquestFile" multiple="multiple"/><label id="recordInquestFileShow" style="font-size: 10px;margin-top: 10">上传</label>
					</div>
					
				</core:if>
				<core:if test="${!empty recordInquestFlag&&!recordInquestFlag }">
				   <div class="anniu3">
						<a id="recordInquest" href="javascript:void(0)">被驳回查看</a>
					</div>
					<div class="anniu2" >
						<input type="file" class="file" name="recordInquest" id="recordInquestFile" multiple="multiple"/><label id="recordInquestFileShow" style="font-size: 10px;margin-top: 6">上传</label>
					</div>
				</core:if>
				<core:if test="${recordInquestFlag }">
				  <div class="anniu3">
						<a id="recordInquest" href="javascript:void(0)">已通过查看</a>
				  </div>
				</core:if>
			</li>
			<li><img  id="sitePhotosImg"  <core:if test="${sitePhotosFlag }">src="images/alert/20150729042348932_easyicon_net_32.png"</core:if><core:if test="${!sitePhotosFlag }">src="images/alert/cuohao.png"</core:if>/>
			
				<div class="wenzi">现场照片</div> 
				<core:if test="${empty sitePhotosFlag }">
					<div class="anniu2" >
						<input type="file" class="file" name="sitePhotos" id="sitePhotosFile" multiple="multiple"/><label id="sitePhotosFileShow" style="font-size: 10px;margin-top: 10">上传</label>
					</div>
					
				</core:if>
				<core:if test="${!empty sitePhotosFlag&&!sitePhotosFlag }">
				   	<div class="anniu3">
						<a id="sitePhotos" href="javascript:void(0)">被驳回查看</a>
					</div>
					<div class="anniu2" >
						<input type="file" class="file" name="sitePhotos" id="sitePhotosFile" multiple="multiple"/><label id="sitePhotosFileShow" style="font-size: 10px;margin-top: 6">上传</label>
					</div>
				</core:if>
				
				<core:if test="${sitePhotosFlag }">
				  <div class="anniu3">
						<a id="sitePhotos" href="javascript:void(0)">已通过查看</a>
				  </div>
				</core:if>
				
			</li>
			<li><img  id="recordInvImg" <core:if test="${recordInvFlag }">src="images/alert/20150729042348932_easyicon_net_32.png"</core:if><core:if test="${!recordInvFlag }">src="images/alert/cuohao.png"</core:if>/>
			
				<div class="wenzi">调查询问笔录</div> 
				<core:if test="${empty recordInvFlag }">
					<div class="anniu2" >
						<input type="file" class="file" name="recordInv" id="recordInvFile" multiple="multiple"/><label id="recordInvFileShow" style="font-size: 10px;margin-top: 10">上传</label>
					</div>
					
				</core:if>
				<core:if test="${!empty recordInvFlag&&!recordInvFlag }">
				   	<div class="anniu3">
						<a id="recordInv" href="javascript:void(0)">被驳回查看</a>
					</div>
					<div class="anniu2" >
						<input type="file" class="file" name="recordInv" id="recordInvFile" multiple="multiple"/><label id="recordInvFileShow" style="font-size: 10px;margin-top: 6">上传</label>
					</div>
				</core:if>
				
				<core:if test="${recordInvFlag }">
				  <div class="anniu3">
						<a id="recordInv" href="javascript:void(0)">已通过查看</a>
				  </div>
				</core:if>
				
			</li>
			<li><img id="recordPaperImg" <core:if  test="${recordPaperFlag }">src="images/alert/20150729042348932_easyicon_net_32.png"</core:if><core:if test="${!recordPaperFlag }">src="images/alert/cuohao.png"</core:if>/>
			
				<div class="wenzi">笔录纸</div> 
				<core:if test="${empty recordPaperFlag }">
					<div class="anniu2" >
						<input type="file" class="file" name="recordPaper" id="recordPaperFile" multiple="multiple"/><label id="recordPaperFileShow" style="font-size: 10px;margin-top:10">上传</label>
					</div>
					
				</core:if>
				<core:if test="${!empty recordPaperFlag&&!recordPaperFlag }">
				   <div class="anniu3">
						<a id="recordPaper" href="javascript:void(0)">被驳回查看</a>
					</div>
					<div class="anniu2" >
						<input type="file" class="file" name="recordPaper" id="recordPaperFile" multiple="multiple"/><label id="recordPaperFileShow" style="font-size: 10px;margin-top: 6">上传</label>
					</div>
				</core:if>
				<core:if test="${recordPaperFlag }">
				  <div class="anniu3">
						<a id="recordPaper" href="javascript:void(0)">已通过查看</a>
				  </div>
				</core:if>
			</li>
			<br/>
           <li><img id="businessLicenseImg" <core:if test="${businessLicenseFlag }">src="images/alert/20150729042348932_easyicon_net_32.png"</core:if><core:if test="${!businessLicenseFlag }">src="images/alert/cuohao.png"</core:if>/>
			
				<div class="wenzi">营业执照</div> 
				<core:if test="${empty businessLicenseFlag }">
					<div class="anniu2" >
						<input type="file" class="file" name="businessLicense" id="businessLicenseFile" /><label id="businessLicenseFileShow" style="font-size: 10px;margin-top: 10">上传</label>
					</div>
					
				</core:if>
				<core:if test="${!empty businessLicenseFlag&&!businessLicenseFlag }">
				  <div class="anniu3">
							<a id="businessLicense" href="javascript:void(0)" >被驳回查看</a>
				  </div>
					<div class="anniu2">
						<input type="file" class="file" name="businessLicense" id="businessLicenseFile" /><label id="businessLicenseFileShow" style="font-size: 10px;margin-top: 6">上传</label>
					</div>
				</core:if>
				<core:if test="${businessLicenseFlag }">
				  <div class="anniu3" >
							<a id="businessLicense" href="javascript:void(0)" >已通过查看</a>
				  </div>
				</core:if>
			</li>
		</ul>
       
	</div>
	
	</div>
	

  </form>  

  <div class="agree">
	  <!--<label>
	    <input type="checkbox" name="isread" id="isread" />
	  </label>
	  <label id="isread-text">点击弹出 7个jQuery弹出层特效</label>-->
	
	</div>
	<!--agree end-->
	<div id="ceng" style="display:none;">
	  <div id="china" class="simScrollCont">
	    <div class="mainlist">
	      <ul style="list-style:none;" id="documentUL">
	
	      </ul>
	    </div>
	   	<div class="btnbox">
	      <input type="button" value="关闭" onclick="confirmTerm(2);" class="confirmBtn" >
	     
	    </div>
	  </div>
	  
	</div>
	
	
</body>
</html>
