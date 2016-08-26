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
<title>行政处罚审批表</title>


<link href="css/right1.css" rel="stylesheet"/>

<link href="css/kuang.css" rel="stylesheet" type="text/css" />
<link href="css/alert-handle.css" rel="stylesheet" type="text/css" />
<link href="css/alert-handle2.css?version=4" rel="stylesheet" type="text/css" />
<style type="text/css">
textarea {
	resize:none;
}
</style>
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/tipswindown.js"></script>
<script type="text/javascript" src="js/document.js?version=1"></script>
<script type="text/javascript">

	function validate(){
		var client = $.trim($("#client").attr("value"));
		var basicCase = $.trim($("#basicCase").attr("value"));
		var illegalGrade = $.trim($("#illegalGrade").attr("value"));
		var legalBasis = $.trim($("#legalBasis").attr("value"));
		if(client==""||basicCase==""||illegalGrade==""||legalBasis==""){
			$("#errorInf").html("录入信息不完整");
			return false;
		}
		return true;
	}
	
	// 预览提交
	function  preview(){
		if(!validate()){
			return false;
		}
		
		$("form").attr("action","pTableAction_prePDF");
		$("form").attr("target", "_blank");
		$("form").attr("method", "POST");
		$("form").submit();
	}
	// 表单提交
	function formSubmit(){
		if(!validate()){
			return false;
		}
		if("${documentUnllSign}"=="true"){
			var proofServicePT = $.trim($("#proofServicePTFile").attr("value"));
			var finalReport = $.trim($("#finalReportFile").attr("value"));
			var recordSheet = $.trim($("#recordSheetFile").attr("value"));
			
			
				if(proofServicePT==""||finalReport==""||recordSheet==""){
					$("#errorInf").html("第一次填写处罚审批表时必须上传全部文书");
					return false;
				}
			
		}
		
		
		
		
		$("form").attr("action","pTableAction_submitPTable");
		$("form").attr("method", "POST");
		$("form").attr("target", "_self");
		$("form").submit();
		
	}
</script>
<script type="text/javascript">

window.onload = load();
function load(){

	 window.setInterval("monitor('proofServicePTFile')",1000);
	 window.setInterval("monitor('finalReportFile')",1000);
	 window.setInterval("monitor('recordSheetFile')",1000);
	
}




</script>
<script type="text/javascript">
var currentFlag;

$(document).ready(function(){
	$("#finalReport").click(function(){
	
		auditDocumentUI("${id}","FinalReport");
		currentFlag = "finalReportFlag";
		});
});
$(document).ready(function(){
	$("#proofServicePT").click(function(){
		auditDocumentUI("${id}","ProofServicePT");
		currentFlag = "proofServicePTFlag";
		});
}); 
$(document).ready(function(){
	
	$("#recordSheet").click(function(){
		
		auditDocumentUI("${id}","RecordSheet");
		currentFlag = "recordSheetFlag";
		});
}); 


//得到单个文书
function auditDocumentUI(pTableId,documentName){
	
	$.ajax({  
		type: "POST",
        dataType: "html",
        url: "pTableAction_auditDocumentUI",
	    data:
	    {
	    	pTableId:pTableId,
	    	documentName:documentName,  
	    },
	    success:callback
	});
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
	<div class="bg-pri">

		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<!-- Collect the nav links, forms, and other content for toggling -->
				<ul class="nav navbar-nav">
					<li <core:if test="${type=='立案审批表' }">class="active"</core:if>><a
						href="${pageContext.request.contextPath }/aboutTaskAction_findTaskListById?type=立案审批表">立案审批表任务</a></li>
					<li <core:if test="${type=='事先告知书' }">class="active"</core:if>><a
						href="${pageContext.request.contextPath }/aboutTaskAction_findTaskListById?type=事先告知书">处罚告知书任务</a></li>
					<li <core:if test="${type=='处罚审批表' }">class="active"</core:if>><a
						href="${pageContext.request.contextPath }/aboutTaskAction_findTaskListById?type=处罚审批表">处罚审批表任务</a></li>
					<li <core:if test="${type=='处罚决定书' }">class="active"</core:if>><a
						href="${pageContext.request.contextPath }/aboutTaskAction_findTaskListById?type=处罚决定书">处罚决定书任务</a></li>
					<li <core:if test="${type=='处罚结案报告' }">class="active"</core:if>><a
						href="${pageContext.request.contextPath }/aboutTaskAction_findTaskListById?type=处罚结案报告">处罚结案报告任务</a></li>

				</ul>
			</div>
			<!-- /.container-fluid -->
		</nav>

		<div class="container bg-white">
			<br />
			<form action="pTableAction_submitPTable"  method="post"  enctype="multipart/form-data">
				<div class="row">
					<div
						class="col-md-8 col-md-offset-2 col-xs-8 col-xs-offset-1 col-lg-8">
						<p class="bg-primary text-left">当事人</p>
						<input type="text" class="form-control" value="${client}"  style="font-size: 14"
							name="client" id="client">
					</div>

					<div
						class="col-md-8 col-md-offset-2 col-xs-10 col-xs-offset-1 col-lg-8">
						<p class="bg-primary text-left">基本案情</p>
						<textarea class="form-control" rows="5" name="basicCase" id="basicCase" style="font-size: 14">${basicCase }</textarea>
					</div>

					<div
						class="col-md-8 col-md-offset-2 col-xs-10 col-xs-offset-1 col-lg-8">
						<p class="bg-primary text-left">违法行为等次</p>
						<textarea id="illegalGrade" class="form-control" rows="5" name="illegalGrade"  style="font-size: 14">${illegalGrade }</textarea>
					</div>
					
					
					<div class="row">
							<div
								class="col-md-8 col-md-offset-2 col-xs-8 col-xs-offset-1 col-lg-8">
								<p class="bg-primary">违法行为等次填写示例</p>
								<textarea class="form-control" rows="2"  style="margin-top: 0;font-size: 14;border: none;" readonly="readonly"  style="font-size: 14">根据《郑州市中原区城市管理执法局行政处罚裁量标准》之规定，擅自占用城市道路，面积在5平方米以上，30平方米以下的，经责令改正后，能够采取一定补救措施的，属一般违法行为。</textarea>
							</div>
					</div>
		                    
					<div class="row">
							<div
								class="col-md-8 col-md-offset-2 col-xs-8 col-xs-offset-1 col-lg-8">
								<p class="bg-primary">裁量阶次（供填写违法行为等次使用）</p>
								<textarea class="form-control" rows="14"  style="margin-top: 0;font-size: 14;border: none;" readonly="readonly" >${summary.cutOrder }</textarea>
							</div>
					</div>
		                    
		                    	
                     
                  
					<div class="col-md-8 col-md-offset-2  col-xs-10 col-xs-offset-1">
						<p class="bg-primary text-left">法律依据</p>
						<textarea id="legalBasis" class="form-control" rows="11" name="legalBasis"  style="font-size: 14">${legalBasis }</textarea>
					</div>


					<div class="col-md-12" style="height: 50px;"></div>
					<core:if test="${!empty captainSuggest }">
						<div
							class="col-md-8 col-md-offset-2 col-xs-8 col-xs-offset-1 col-lg-8">
							<p class="bg-primary">上次中队长驳回时的意见</p>
							<textarea class="form-control" rows="5" style="margin-top: 0"  style="font-size: 14"
								name="captainSuggest">${captainSuggest }</textarea>
						</div>


					</core:if>
					<core:if test="${!empty legSuggest }">

						<div class="row">
							<div
								class="col-md-8 col-md-offset-2 col-xs-8 col-xs-offset-1 col-lg-8">
								<p class="bg-primary">上次法制科驳回时的意见</p>
								<textarea class="form-control" rows="10" style="margin-top: 0"  style="font-size: 14"
									name="legSuggest">${legSuggest }</textarea>
							</div>
						</div>



					</core:if>
					<core:if test="${!empty bigCaptainSuggest }">

						<div class="row">
							<div
								class="col-md-8 col-md-offset-2 col-xs-8 col-xs-offset-1 col-lg-8">
								<p class="bg-primary">上次大队长驳回时的意见</p>
								<textarea class="form-control" rows="10" style="margin-top: 0"  style="font-size: 14"
									name="bigCaptainSuggest">${bigCaptainSuggest }</textarea>
							</div>
						</div>



					</core:if>

					<core:if test="${!empty legGuideSuggest}">

						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12"
							style="height: 50px;"></div>
						<div
							class="col-md-8 col-md-offset-2 col-xs-8 col-xs-offset-1 col-lg-8">
							<p class="bg-primary">法制科指导意见</p>
							<textarea class="form-control" rows="5" style="margin-top: 0"  style="font-size: 14"
								disabled="disabled">${legGuideSuggest }</textarea>

						</div>

					</core:if>
					<br> <input type="hidden" name="taskId" value="${taskId }">
					<input type="hidden" name="pTableId" value="${id }">
					<div class="form-group col-md-1 col-md-offset-2"
						style="margin-top: 20px;">
						<input class="btn btn-primary" type="submit" name="outCome" onclick="return formSubmit()"
							value="<s:property value="outCome"/>">
						
					</div>
					
					<div class="form-group col-md-1 col-md-offset-2"
						style="margin-top: 20px;">
						
						<input class="btn btn-primary"  type="button" onclick="return preview()"  value="预览">
					</div>
					  <div class="form-group"  class="form-group col-md-5 col-md-offset-0">
                      	<label id="errorInf" style="font-size: 16px;color: red" for="firstname" class="col-xs-4 col-sm-8 col-md-5 col-lg-6  control-label"></label>
                       
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
            <div class="title">处罚审批表文书</div>
		</div>
		<ul>   
		
		
			
			<li><img id="proofServicePTImg" <core:if test="${proofServicePTFlag }">src="images/alert/20150729042348932_easyicon_net_32.png"</core:if><core:if test="${!proofServicePTFlag }">src="images/alert/cuohao.png"</core:if>/>
			
				<div class="wenzi">送达回证</div> 
				
				<core:if test="${empty proofServicePTFlag }">
					<div class="anniu2" >
						<input type="file" class="file" name="proofServicePT" id="proofServicePTFile" multiple="multiple"/><label id="proofServicePTFileShow" style="font-size: 10px;margin-top: 10">上传</label>
					</div>
					
				</core:if>
				<core:if test="${!empty proofServicePTFlag&&!proofServicePTFlag }">
				   	<div class="anniu3">
						<a id="proofServicePT" href="javascript:void(0);">被驳回查看</a>
					</div>
					<div class="anniu2" >
						<input type="file" class="file" name="proofServicePT" id="proofServicePTFile" multiple="multiple"/><label id="proofServicePTFileShow" style="font-size: 10px" margin-top: 0">上传</label>
					</div>
				</core:if>
				
				<core:if test="${proofServicePTFlag }">
				  <div class="anniu3">
						<a id="proofServicePT" href="javascript:void(0);">已通过查看</a>
				  </div>
				</core:if>
				
			</li>
			<li><img id="finalReportImg" <core:if test="${finalReportFlag }">src="images/alert/20150729042348932_easyicon_net_32.png"</core:if><core:if test="${!finalReportFlag }">src="images/alert/cuohao.png"</core:if>/>
			
				<div class="wenzi">案件调查终结报告</div> 
				<core:if test="${empty finalReportFlag }">
					<div class="anniu2" >
						<input type="file" class="file" name="finalReport" id="finalReportFile" multiple="multiple"/><label id="finalReportFileShow" style="font-size: 10px;margin-top: 10">上传</label>
					</div>
					
				</core:if>
				<core:if test="${!empty finalReportFlag&&!finalReportFlag }">
				  <div class="anniu3">
						<a id="finalReport" href="javascript:void(0)">被驳回查看</a>
					</div>
					<div class="anniu2" >
						<input type="file" class="file" name="finalReport" id="finalReportFile" multiple="multiple"/><label id="finalReportFileShow" style="font-size: 10px;margin-top: 0">上传</label>
					</div>
				</core:if>
				<core:if test="${finalReportFlag }">
				  <div class="anniu3">
						<a id="finalReport" href="javascript:void(0)">已通过查看</a>
				  </div>
				</core:if>
				
			</li>
			
			<li><img id="recordSheetImg" <core:if test="${recordSheetFlag }">src="images/alert/20150729042348932_easyicon_net_32.png"</core:if><core:if test="${!recordSheetFlag }">src="images/alert/cuohao.png"</core:if>/>
				
				<div class="wenzi">调查终结报告笔录纸</div> 
				<core:if test="${empty recordSheetFlag }">
					<div class="anniu2" >
						<input type="file" class="file" name="recordSheet" id="recordSheetFile" multiple="multiple"/><label id="recordSheetFileShow" style="font-size: 10px;margin-top: 10">上传</label>
					</div>
					
				</core:if>
				<core:if test="${!empty recordSheetFlag&&!recordSheetFlag }">
				   <div class="anniu3">
						<a id="recordSheet" href="javascript:void(0)">被驳回查看</a>
					</div>
					<div class="anniu2" >
						<input type="file" class="file" name="recordSheet" id="recordSheetFile" multiple="multiple"/><label id="recordSheetFileShow" style="font-size: 10px;margin-top: 0">上传</label>
					</div>
				</core:if>
				<core:if test="${recordSheetFlag }">
				  <div class="anniu3">
							<a id="recordSheet" href="javascript:void(0)">已通过查看</a>
				  </div>
				</core:if>
			</li>
			
			
           
		</ul>
       
	</div>
	
	</div>

	</form>
			
			
	
	<!--agree end-->
	
	
		</div>
		<br> <br>
	</div>
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
</body>
</html>