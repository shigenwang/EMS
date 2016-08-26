<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/js/commons.jspf" %>
<%@ include file="/WEB-INF/jsp/share/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"  content="width=device-width,initial-scale=1">
<title>行政处罚结案报告</title>
<link href="css/right2.css" rel="stylesheet" type="text/css">

<link href="css/kuang.css" rel="stylesheet" type="text/css" />
<link href="css/alert-handle.css" rel="stylesheet" type="text/css" />
<link href="css/alert-handle2.css?version=3" rel="stylesheet" type="text/css" />

<style type="text/css">
textarea {
	resize:none;
}
</style>

<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/tipswindown.js"></script>
<script type="text/javascript" src="js/document.js?version=1"></script>






<script src="js/bootstrap.js"></script> 
<script type="text/javascript">
	// 预览提交
	function  preview(){
		
		var basicCase = $.trim($("#basicCase").attr("value"));
		var paymentDate = $.trim($("#paymentDate").attr("value"));
		if(basicCase==""||paymentDate==""){
			$("#errorInf").html("录入信息不完整");
			return false;
		}
		
		
		$("form").attr("action","pClosingReportAction_prePDF");
		$("form").attr("target", "_blank");
		$("form").attr("method", "POST");
		$("form").submit();
	}
	// 表单提交
	function formSubmit(){
		
		var basicCase = $.trim($("#basicCase").attr("value"));
		var paymentDate = $.trim($("#paymentDate").attr("value"));
		if(basicCase==""||paymentDate==""){
			$("#errorInf").html("录入信息不完整");
			return false;
		}
		
		
		if("${documentUnllSign}"=="true"){
			var proofServicePC = $.trim($("#proofServicePCFile").attr("value"));
			var fineNote = $.trim($("#fineNoteFile").attr("value"));
			
			if(proofServicePC==""||fineNote==""){
				$("#errorInf").html("第一次填写结案报告时必须上传全部文书");
				return false;
			}
		}
		
		$("form").attr("action","pClosingReportAction_submitPClosingReport");
		$("form").attr("method", "POST");
		$("form").attr("target", "_self");
		$("form").submit();
	}
	

</script>
<%-- <script src="js/jQuery-jcDate.js"></script>
<script type="text/javascript">
$(function (){
	$("input.jcDate").jcDate({					       
		IcoClass : "jcDateIco",
		Event : "click",
		Speed : 100,
		Left : 0,
		Top : 28,
		format : "-",
		Timeout : 100
	});
});
</script>  --%>






<script type="text/javascript">
var currentFlag;

$(document).ready(function(){
	$("#fineNote").click(function(){

		auditDocumentUI("${id}","FineNote");
		currentFlag = "fineNoteFlag";
		});
});
$(document).ready(function(){
	$("#proofServicePC").click(function(){
		auditDocumentUI("${id}","ProofServicePC");
		currentFlag = "proofServicePCFlag";
		});
}); 
 


//得到单个文书
function auditDocumentUI(pClosingReportId,documentName){
	
	$.ajax({  
		type: "POST",
        dataType: "html",
        url: "pClosingReportAction_auditDocumentUI",
	    data:
	    {
	    	pClosingReportId:pClosingReportId,
	    	documentName:documentName,  
	    },
	    success:callback
	});
	
}
	
window.onload = load();
function load(){

	 window.setInterval("monitor('fineNoteFile')",1000);
	 window.setInterval("monitor('proofServicePCFile')",1000);
	
	
}
</script>

<style type="text/css">
*{ margin:0px;padding:0px;font-family: "微软雅黑";font-size:12px;color:#555555;list-style:none;} 
.clear{clear:both;height:0;visibility:hidden;}
a{text-decoration:none;cursor:pointer;}
a:hover{color:red;text-decoration:underline;}
a img{border:0;vertical-align:middle;}
</style>

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
<form action="pClosingReportAction_submitPClosingReport" class="form-horizontal" style="margin-top:29px ; margin-left:-25px;"  method="post" enctype="multipart/form-data" >
<div class="container">
  <div class="row">
    <div class="col-xs-10 col-sm-10 col-md-10 col-lg-10">
      <p class="bg-primary">基本案件及执行情况</p>
       
         <textarea class="form-control" rows="20" name="basicCase" id="basicCase" style="font-size: 14px">${basicCase }
      	</textarea>
        <br>
      
         
          		<div class="form-group  col-sm-4">
       		  	 <span style="font-size:12px;font-family: "微软雅黑"">当事人于</span>
       		  		 <input class="form-control laydate-icon" id="paymentDate" style="width:120px;height:25px;line-height:20px;margin-top: 3" value="<s:date name="paymentDate"  format="yyyy-MM-dd"/>" name="paymentDate" id="paymentDate"/>
       		  	  <span style="font-size:12px;font-family:"微软雅黑"">缴纳了罚款，现已执行到位。</span>
       		  	</div> 
        
  		
  
 </div>
</div>


     <core:if test="${!empty captainSuggest }">
		
				 <div class="row">
      	 			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-10"  >
          				<p class="bg-primary">上次中队长驳回时的意见</p>
          			<textarea class="form-control" rows="10"  style="margin-top: 0" readonly="readonly">${captainSuggest }</textarea>
         		 </div>
      			 </div>
			
		</core:if>
		<core:if test="${!empty legSuggest }">
			
			
				<div class="row">
      	 			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-10" >
          				<p class="bg-primary">上次法制科驳回时的意见</p>
          			<textarea class="form-control" rows="10"  style="margin-top: 0" readonly="readonly">${legSuggest }</textarea>
         		 </div>
         		 </div>

		</core:if>
		<core:if test="${!empty bigCaptainSuggest }">
			
				<div class="row">
      	 			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-10"  >
          				<p class="bg-primary">上次大队长驳回时的意见</p>
          			<textarea class="form-control" rows="10"   style="margin-top: 0" readonly="readonly">${bigCaptainSuggest }</textarea>
         		 </div>
         		 </div>
			
		</core:if>
		
		<core:if test="${!empty legGuideSuggest}">
			
			<div class="row">
      	 			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-10"  >
          				<p class="bg-primary">法制科指导意见</p>
          			<textarea class="form-control" rows="10" style="margin-top: 0" readonly="readonly">${legGuideSuggest }</textarea>
         		 </div>
         	</div>
		</core:if>
	
		<core:if test="${!empty ICSuggest}">
			
			<div class="row">
      	 			<div class="col-xs-10 col-sm-10 col-md-10 col-lg-10"  >
          				<p class="bg-primary">上次业委会驳回时的意见</p>
          			<textarea class="form-control" rows="10" style="margin-top: 0" readonly="readonly">${ICSuggest }</textarea>
         		 </div>
         	</div>
		</core:if>
		
		
		<input name="taskId" value="${taskId }" type="hidden">
		<input type="hidden" name="pClosingReportId" value="${id }"/>
		 <div class="col-md-12" style="height:100px;">
            
            </div>
           
                      <label id="errorInf" style="font-size: 16px;color: red" for="firstname" class="col-xs-4 col-sm--5 col-md-0 col-lg-20 control-label" ></label>
                       
       
		<div class="form-group col-md-1 col-md-offset-0" style="margin-top: 20">
       		<input class="btn btn-primary" type="submit"  name="outCome" onclick="return formSubmit()" value="<s:property value="outCome"/>">
       	   </div>
       	   <div class="col-md-1 col-md-offset-2" style="margin-top: 20">		
       		<input class="btn btn-primary"  type="submit" onclick="return preview()"  value="预览"  >
       		
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
            <div class="title">结案报告文书</div>
		</div>
		<ul>   
			
		
			
			
			<li><img id="proofServicePCImg" <core:if test="${proofServicePCFlag }">src="images/alert/20150729042348932_easyicon_net_32.png"</core:if><core:if test="${!proofServicePCFlag }">src="images/alert/cuohao.png"</core:if>/>
				
				<div class="wenzi">送达回证</div> 
				<core:if test="${empty proofServicePCFlag }">
					<div class="anniu2" >
						<input type="file" class="file" name="proofServicePC" id="proofServicePCFile" multiple="multiple"/><label id="proofServicePCFileShow" style="font-size: 10px;margin-top: 10">上传</label>
					</div>
					
				</core:if>
				<core:if test="${!empty proofServicePCFlag&&!proofServicePCFlag }">
				   <div class="anniu3">
						<a id="proofServicePC" href="javascript:void(0)">被驳回查看</a>
					</div>
					<div class="anniu2" >
						<input type="file" class="file" name="proofServicePC" id="proofServicePCFile" multiple="multiple"/><label id="proofServicePCFileShow" style="font-size: 10px;margin-top: 0">上传</label>
					</div>
				</core:if>
				<core:if test="${proofServicePCFlag }">
				  <div class="anniu3">
							<a id="proofServicePC" href="javascript:void(0)">已通过查看</a>
				  </div>
				</core:if>
			</li>
			<li><img id="fineNoteImg" <core:if test="${fineNoteFlag }">src="images/alert/20150729042348932_easyicon_net_32.png"</core:if><core:if test="${!fineNoteFlag }">src="images/alert/cuohao.png"</core:if>/>
			
				<div class="wenzi">罚款专用票据</div> 
				<core:if test="${empty fineNoteFlag }">
					<div class="anniu2" >
						<input type="file" class="file" name="fineNote" id="fineNoteFile" multiple="multiple"/><label id="fineNoteFileShow" style="font-size: 10px;margin-top: 10">上传</label>
					</div>
					
				</core:if>
				<core:if test="${!empty fineNoteFlag&&!fineNoteFlag }">
				  <div class="anniu3">
						<a id="fineNote" href="javascript:void(0)">被驳回查看</a>
					</div>
					<div class="anniu2" >
						<input type="file" class="file" name="fineNote" id="fineNoteFile" multiple="multiple"/><label id="fineNoteFileShow" style="font-size: 10px;margin-top: 0">上传</label>
					</div>
				</core:if>
				<core:if test="${fineNoteFlag }">
				  <div class="anniu3">
					<a id="fineNote" href="javascript:void(0)">已通过查看</a>
				  </div>
				</core:if>
				
			</li>
		</ul>
       
	</div>
	
	</div>
 </form>
<div class="agree">
	  
	
	</div>
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
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath }/js/laydate.js"></script> 
	<script type="text/javascript">
	!function(){
		laydate.skin('molv');//切换皮肤，请查看skins下面皮肤库
		laydate({elem:'#paymentDate'});
		
	}();;
</script>
</body>
</html>
