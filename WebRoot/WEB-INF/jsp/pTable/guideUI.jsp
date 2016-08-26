<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/js/commons.jspf" %>
    <%@taglib prefix="s" uri="/struts-tags"%>
    <%@ include file="/WEB-INF/jsp/share/taglib.jsp" %>
    <!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>案件处理审批表</title>
    
<link href="css/examine1.css?version=1" rel="stylesheet">
<link href="css/kuang.css?version=5" rel="stylesheet" type="text/css" />
<link href="css/alert-handle.css" rel="stylesheet" type="text/css" />
<link href="css/alert-handle2.css?version=1" rel="stylesheet" type="text/css" />


  <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script> 
<script type="text/javascript" src="js/tipswindown.js?version=1"></script>
<script type="text/javascript" src="js/document.js?version=1"></script> 
  </head>
<script type="text/javascript">

function validate(outCome){
	var suggest = $.trim($("#suggest").attr("value"));
	if(suggest==""&&outCome=="给予指导不通过"){
		$("#errorInf").html("指导不通过时必须输入意见");
		return false;
		
	}else if(suggest!=""&&outCome=="给予指导通过"){
		$("#errorInf").html("指导通过时不能输入意见");
		return false;
	}
	
	if(outCome=="给予指导通过"){
		var proofServicePTFlag = $.trim($("#proofServicePTFlag").attr("value"));
		var finalReportFlag = $.trim($("#finalReportFlag").attr("value"));
		var recordSheetFlag = $.trim($("#recordSheetFlag").attr("value"));
		
		
		if(proofServicePTFlag==""||proofServicePTFlag=="false"||finalReportFlag==""||finalReportFlag=="false"||
				recordSheetFlag==""||recordSheetFlag=="false"){
			$("#errorInf").html("指导通过时必须通过全部上传的文件");
			return false;
		}
		
	}
	
	
}




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
 <div class="container">
       <div class="col-md-10 col-md-offset-1">
             <table class="table table-bordered text-center" style="font-size: 16px">
          	 <caption style="font-size: 1.8em">郑州市中原区城市管理行政执法局<br>案&nbsp;件&nbsp;处&nbsp;理&nbsp;审&nbsp;批&nbsp;表</caption>  
                  <tr>
                    <th width="20%">当事人</th>
                    <td>${client}</td>
               </tr>
                  <tr>
                    <th width="20%">基本案情</th>
                    <td style="text-align: left;"><span>${basicCase }</span></td>
               </tr>
                  <tr>
                    <th width="20%">违法行为等次</th>
                    <td style="text-align: left;"><span>${illegalGrade }</span></td>
               </tr>
                  <tr>
                    <th width="20%">法律依据</th>
                    <td style="text-align: left;"><span>${legalBasis }</span></td>
               </tr>
                   <tr>
                    <th width="20%">承办人建议</th>
                    <td>
                   <div style="height: 50"> </div>  
               	 
               		 </td>
               </tr>
                  <tr>
                    <th width="20%">承办部门审核</th>
                    <td>
                   	  <div style="height: 50"> </div>  
                    </td>
               </tr>
                  <tr>
                    <th width="20%">法律部门审核</th>
                    <td>
                   	 <div style="height: 50"> </div>  
                    </td>
               </tr>
                  <tr>
                    <th width="20%">主管领导审核</th>
                    <td>
                   	 <div style="height: 50"> </div>  
                    </td>
               </tr>
                  <tr>
                    <th width="20%">部门主要领导审核</th>
                    <td>
                   <div style="height: 50"> </div>     
 			    </td>
               </tr>
                  <tr>
                    <th width="20%">备注</th>
                    <td>&nbsp;</td>
               </tr>
		 </table>

   </div>
        <form class="form-horizontal col-md-12 col-md-offset-1" action="pTableAction_guide" >
        	
            
                <div class="col-md-12" style="height:50px;"></div>
               	 <core:if test="${!empty legGuideSuggest}">
              	  	
	                  <div class="col-xs-10 col-sm-10 col-md-10 col-lg-10" >
          				<p class="bg-primary">上次我的意见</p>
          				<textarea class="form-control" rows="5"  style="margin-top: 0" >${legGuideSuggest }</textarea>
          				
         		 	</div>
         		 	 <div class="col-md-12" style="height:50px;">
                
              		  </div>
                  </core:if>
                  
               
      	 			<div class="col-xs-10 col-sm-10 col-md-10 col-lg-10" >
          				<p class="bg-primary">本次指导意见</p>
          				<textarea class="form-control" rows="5"  style="margin-top: 0" id="suggest" name="legGuideSuggest"></textarea>
          				
         		 	</div>
         		
                  <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="height:50px;"></div>
                  
                  
	                <input type="hidden" name="taskId" value="${taskId }"/>
                   	<input type="hidden" name="currentSign" value="${currentSign }"/>
                   	
                   	   <div style="margin-top: 50" class="col-md-7 ">
             <label id="errorInf" style="font-size: 16px;color: red" for="firstname" class="col-xs-4 col-sm-10 col-md-10 col-lg-7  control-label"></label>
            </div> 
          	 <div class="form-group col-md-1 col-md-offset-1" style="margin-top: 50">
                    <input class="btn btn-primary" type="submit" name="outCome" onclick="return validate(this.value)"  value="给予指导通过">
         	 </div>
              <div class="col-md-1 col-md-offset-2" style="margin-top: 50">
                    <input class="btn btn-primary" type="submit" name="outCome"  onclick="return validate(this.value)"  value="给予指导不通过">
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
				<core:if test="${!empty proofServicePT }">
					<div class="anniu">
						<a id="proofServicePT" href="javascript:void(0);">查看</a>
					</div>
				</core:if>
				
			</li>
			<li><img id="finalReportImg" <core:if test="${finalReportFlag }">src="images/alert/20150729042348932_easyicon_net_32.png"</core:if><core:if test="${!finalReportFlag }">src="images/alert/cuohao.png"</core:if>/>
			
				<div class="wenzi">案件调查终结报告</div> 
				<core:if test="${!empty finalReport }">
					<div class="anniu">
						<a id="finalReport" href="javascript:void(0)">查看</a>
					</div>
				</core:if>
			
				
			</li>
			
			<li><img id="recordSheetImg" <core:if test="${recordSheetFlag }">src="images/alert/20150729042348932_easyicon_net_32.png"</core:if><core:if test="${!recordSheetFlag }">src="images/alert/cuohao.png"</core:if>/>
				
				<div class="wenzi">调查终结报告笔录纸</div> 
				<core:if test="${!empty recordSheet }">
					<div class="anniu">
						<a id="recordSheet" href="javascript:void(0)">查看</a>
					</div>
				</core:if>
				
				
			</li>
			
		</ul>
       
	</div>
	
	</div>
		<input type="hidden" id="proofServicePTFlag" name="proofServicePTFlag" value="${proofServicePTFlag }"/>
		<input type="hidden" id="finalReportFlag" name="finalReportFlag" value="${finalReportFlag }"/>
		<input type="hidden" id="recordSheetFlag" name="recordSheetFlag" value="${recordSheetFlag }"/>
		
    	</form>
 </div>
 <div id="ceng" style="display:none;">
	  <div id="china" class="simScrollCont">
	    <div class="mainlist">
	      <ul style="list-style:none;" id="documentUL">
	      </ul>
	    </div>
	   	<div class="btnbox">
	      <input type="button" value="通过" onclick="confirmTerm(1);" class="confirmBtn" >
	      <input type="button" value="不通过" onclick="confirmTerm(0);" class="confirmBtn" >
	      <input type="button" value="关闭" onclick="confirmTerm(2);" class="confirmBtn" >
	    </div>
	  </div>
	  
	</div>
  </body>
</html>