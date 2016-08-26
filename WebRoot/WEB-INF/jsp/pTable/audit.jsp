<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/js/commons.jspf" %>
    <%@taglib prefix="s" uri="/struts-tags"%>
    <%@ include file="/WEB-INF/jsp/share/taglib.jsp" %>
    <!DOCTYPE html>
<html >
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>案件处理审批表</title>
    
<link href="css/examine1.css" rel="stylesheet">
<link href="css/kuang.css?version=4" rel="stylesheet" type="text/css" />
<link href="css/alert-handle.css" rel="stylesheet" type="text/css" />
<link href="css/alert-handle2.css?version=1" rel="stylesheet" type="text/css" />
<style type="text/css">
textarea {
	resize:none;
}
</style>

<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script> 
<script type="text/javascript" src="js/tipswindown.js"></script>
<script type="text/javascript" src="js/document.js?version=1"></script> 


<script type="text/javascript">


function validate(outCome){
	 var suggest = $.trim($("#suggest").attr("value"));
	if(suggest==""&&outCome=="驳回"){
		$("#errorInf").html("驳回时必须输入意见");
		return false;
		
	}else if(suggest!=""&&outCome=="批准"){
		$("#errorInf").html("批准时不能输入意见");
		return false;
	} 
	
	if(outCome=="批准"){
		
		var caseLevel = $.trim($("#caseLevel").attr("value"));
		if(caseLevel=="重大"||caseLevel=="特大"){
			var factAndSuggestion = $.trim($("#factAndSuggestion").attr("value"));
			var ICOrCRCDecide = $.trim($("#ICOrCRCDecide").attr("value"));
			
			if(factAndSuggestion==""||ICOrCRCDecide==""){
				$("#errorInf").html("选择案件为重大或特大时必须输入事实和处理意见及业委会或案审委决定");
				return false;
			}
		}	
		
		var proofServicePTFlag = $.trim($("#proofServicePTFlag").attr("value"));
		var finalReportFlag = $.trim($("#finalReportFlag").attr("value"));
		var recordSheetFlag = $.trim($("#recordSheetFlag").attr("value"));
		
		
		if(proofServicePTFlag==""||proofServicePTFlag=="false"||finalReportFlag==""||finalReportFlag=="false"||
				recordSheetFlag==""||recordSheetFlag=="false"){
			$("#errorInf").html("批准时必须通过全部上传的文件");
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

    
  </head>

 <body>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
           
            
              <ul class="nav navbar-nav">
              	<core:forEach items="${taskTypeList }"  var="taskType" >
              		<core:if test="${taskType=='accreditationSign' }">
            			  <li <core:if test="${type=='立案审批表' }">class="active"</core:if>  ><a href="${pageContext.request.contextPath }/aboutTaskAction_findTaskListById?type=立案审批表">立案审批表任务</a></li> 
            	  	</core:if>
            	  	<core:if test="${taskType=='pNoticeSign' }">
            			  <li <core:if test="${type=='事先告知书' }">class="active"</core:if> ><a href="${pageContext.request.contextPath }/aboutTaskAction_findTaskListById?type=事先告知书">处罚告知书任务</a></li>
            	 	</core:if>
            	 	<core:if test="${taskType=='pTableSign' }">
            	 		 <li <core:if test="${type=='处罚审批表' }">class="active"</core:if> ><a href="${pageContext.request.contextPath }/aboutTaskAction_findTaskListById?type=处罚审批表">处罚审批表任务</a></li> 
                    </core:if>
                    <core:if test="${taskType=='pDecideSign' }">
                		  <li <core:if test="${type=='处罚决定书' }">class="active"</core:if> ><a href="${pageContext.request.contextPath }/aboutTaskAction_findTaskListById?type=处罚决定书">处罚决定书任务</a></li>
              		 </core:if>
              		 <core:if test="${taskType=='pClosingReportSign' }">
                  			<li <core:if test="${type=='处罚结案报告' }">class="active"</core:if> ><a href="${pageContext.request.contextPath }/aboutTaskAction_findTaskListById?type=处罚结案报告">处罚结案报告任务</a></li>  
            		</core:if>
            	</core:forEach>
             </ul>
     </div><!-- /.container-fluid -->
    </nav>
   
 <div class="container">
       <div class="col-md-10 col-md-offset-1">
             <table class="table table-bordered text-center" style="font-size: 16px">
          	 <caption  style="font-size: 1.8em">郑州市中原区城市管理行政执法局<br>案&nbsp;件&nbsp;处&nbsp;理&nbsp;审&nbsp;批&nbsp;表</caption>  
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
                      <td height="60">
                    <core:if test="${hostFlag&&joinFlag}">
	                	<p ><%--建议对${client}处以行政罚款${accreditation.pNotice.fines }元整 --%>
	                		建议对当事人罚款${strFines }
	                	<core:if test="${!empty otherpenaltyInf }">并${otherpenaltyInf }</core:if></p>
	                </core:if>
	               
	               
	                 <core:if test="${hostFlag}"><span>${accreditation.sponsor.name }</span></core:if>
	               
	                 <core:if test="${joinFlag}"><span>${accreditation.assistantHandle.name }</span></core:if>
	              
	             	<core:if test="${hostFlag&&joinFlag}"> <div><s:date name="hostDate" format="yyyy.MM.dd"/></div></core:if>
                
               	 
                </td>
               </tr>
                  <tr>
                    <th width="20%">承办部门审核</th>
                    <td>
                   	  <core:if test="${captainFlag}">
	             	 <p>同意承办意见</p>
	                 <div><s:date name="captainDate" format="yyyy.MM.dd"/></div>
	                 <div>${captainName}</div>  
              		  </core:if>  
                	 <core:if test="${!captainFlag}">
	             	 <p>&nbsp;&nbsp;&nbsp;</p>
	                 <div>&nbsp;&nbsp;&nbsp;</div>
	                 <div>&nbsp;&nbsp;&nbsp;</div>  
                 </core:if>           
                    </td>
               </tr>
                  <tr>
                    <th width="20%">法律部门审核</th>
                    <td>
                   	  <core:if test="${legalFlag}">
	             	<p>事实清楚，证据确凿，适用法规条文恰当，罚款金额符合自由裁量阶次制度规定</p>
	                 <div><s:date name="legalDate" format="yyyy.MM.dd"/></div>
	                 <div>${ legalName}</div>  
                 </core:if>     
                   <core:if test="${!legalFlag}">
	             	<p>&nbsp;&nbsp;&nbsp;</p>
	                 <div>&nbsp;&nbsp;&nbsp;</div>
	                 <div>&nbsp;&nbsp;&nbsp;</div>  
                 </core:if>       
                    </td>
               </tr>
                  <tr>
                    <th width="20%">领导审批意见</th>
                    <td>
                   	 <core:if test="${chiefFlag}">
	             	<p>同意承办意见</p>
	                 <div><s:date name="bigCaptainDate" format="yyyy.MM.dd"/></div>
	                 <div>${bigCaptainName}</div>  
                 </core:if>      
                 <core:if test="${!chiefFlag}">
	             	<p>&nbsp;&nbsp;&nbsp;</p>
	                 <div>&nbsp;&nbsp;&nbsp;</div>
	                 <div>&nbsp;&nbsp;&nbsp;</div>  
                 </core:if>    
                    </td>
               </tr>
                
		 </table>

   </div>
       <!------------------------------------------------ 分割线---------------------------------------------------------------- -->
      
            <form class="form-horizontal col-md-12 col-md-offset-1" action="pTableAction_submitTask">
             <core:if test="${!empty captainSuggest }">
			<core:if test="${currentSign=='中队长审批' }">
					<div class="col-xs-10 col-sm-10 col-md-10 col-lg-10" >
	          				<p class="bg-primary">上次我的审批意见</p>
	          				<textarea class="form-control" rows="5"  style="margin-top: 0"  readonly="readonly">${captainSuggest }</textarea>
         			</div>
					
      			   
			</core:if>
			<core:if test="${currentSign!='中队长审批' }">
			
				<div class="col-xs-10 col-sm-10 col-md-10 col-lg-10" >
	          				<p class="bg-primary">上次中队长审批意见</p>
	          				<textarea class="form-control" rows="5"  style="margin-top: 0" readonly="readonly" >${captainSuggest }</textarea>
         		</div>
				
			</core:if>
			<div class="col-md-12" style="height:50px;"></div>
		</core:if>
		<core:if test="${!empty legSuggest }">
			<core:if test="${currentSign=='法制科审批' }">
						
				 <div class="row">
      	 			<div class="col-xs-10 col-sm-10 col-md-10 col-lg-10">
          				<p class="bg-primary">上次我的审批意见</p>
          			<textarea class="form-control" rows="5"  style="margin-top: 0" readonly="readonly">${legSuggest }</textarea>
         		 </div>
      			 </div>
			</core:if>
			<core:if test="${currentSign!='法制科审批' }">
				 <div class="row">
      	 			<div class="col-xs-10 col-sm-10 col-md-10 col-lg-10">
          				<p class="bg-primary">上次法制科审批意见</p>
          			<textarea class="form-control" rows="5"  style="margin-top: 0" readonly="readonly">${legSuggest }</textarea>
         		 </div>
      			 </div>
			</core:if>
			<div class="col-md-12" style="height:50px;"></div>
		</core:if>
		<core:if test="${!empty bigCaptainSuggest }">
			<core:if test="${currentSign=='大队长审批' }">
			
				<div class="col-xs-10 col-sm-10 col-md-10 col-lg-10" >
          				<p class="bg-primary">上次我的审批意见</p>
          				<textarea class="form-control" rows="5"  style="margin-top: 0" readonly="readonly">${bigCaptainSuggest }</textarea>
          				
         		 	</div>
				
			</core:if>
			<core:if test="${currentSign!='大队长审批' }">
				<div class="row">
      	 			<div class="col-xs-10 col-sm-10 col-md-10 col-lg-10">
          				<p class="bg-primary">上次大队长审批意见</p>
          			<textarea class="form-control" rows="5"  style="margin-top: 0" readonly="readonly">${bigCaptainSuggest }</textarea>
         		 </div>
      			 </div>
			</core:if>
			
			
			<div class="col-md-12" style="height:50px;"></div>
		</core:if>
	
			
			<div class="col-md-12" style="height:50px;"></div>
	
		<!----------------------------------------------------  分割线------------------------------------------------------>
		
		
            		
					<core:if test="${currentSign=='法制科指导' }">
						
						<div class="col-xs-10 col-sm-10 col-md-10 col-lg-10" >
	          				<p class="bg-primary">本次我对案件处理审批表意见</p>
	          				<textarea class="form-control" rows="5"  style="margin-top: 0"  name="legGuideSuggest" id="suggest"></textarea>
         		 	     </div>
					</core:if>
					<core:if test="${currentSign=='中队长审批' }">
						
						<div class="col-xs-10 col-sm-10 col-md-10 col-lg-10" >
	          				<p class="bg-primary">本次我对案件处理审批表意见</p>
	          				<textarea class="form-control" rows="5"  style="margin-top: 0"  name="captainSuggest" id="suggest" ></textarea>
         		 	     </div>
         		 	    
					</core:if>
					<core:if test="${currentSign=='法制科审批' }">
					
						<div class="col-xs-10 col-sm-10 col-md-10 col-lg-10" >
	          				<p class="bg-primary">本次我对案件处理审批表的意见</p>
	          				<textarea class="form-control" rows="5"  style="margin-top: 0"  name="legSuggest" id="suggest"></textarea>
         		 	     </div>
						
					</core:if>
					<core:if test="${currentSign=='大队长审批' }">
					
						<div class="col-xs-10 col-sm-10 col-md-10 col-lg-10" >
	          				<p class="bg-primary">本次我对案件处理审批表意见</p>
	          				<textarea class="form-control" rows="5"  style="margin-top: 0"  name="bigCaptainSuggest" id="suggest"></textarea>
         		 	     </div>
					</core:if>
					
						
		<!----------------------------------------------------  分割线------------------------------------------------------>	
			  
                 
               <core:if test="${currentSign=='法制科审批' }">
              <div class="col-md-12" style="height:50px;"></div>
                  
                   <div class="form-group col-md-6">
                    <label for="inputEmail3" class="col-sm-3 control-label">案件级别</label>
                     <div class="col-sm-7">
                     <core:if test="${!empty caseLevel}">
                          <div class="form-group">
	                     
	                      <div class="col-xs-8 col-sm-9 col-md-9 col-lg-9" >
	                         <input type="text" class="form-control"  name="caseLevel" id="caseLevel" value="${caseLevel}" readonly="readonly">
	                      </div>
                 		 </div>
                        
                     </core:if>
                       <core:if test="${empty caseLevel}">
	                         <select class="form-control" name="caseLevel" id="caseLevel">
	                         	 
		                          	<option value="一般">一般</option>
		                          	<option value="重大">重大</option>
		                          	<option value="特大">特大</option>
	                         	
	                         </select>
                       </core:if>
                   <%--   <core:if test="${empty caseLevel}">
                         <select class="form-control" name="caseLevel">
                          <core:if test="${caseLevel=='一般' }">
                          	<option value="一般" selected="selected">一般</option>
                          	<option value="重大" >重大</option>
                          	<option value="特大">特大</option>
                          </core:if>
                          <core:if test="${caseLevel=='重大' }">
                          	<option value="重大" selected="selected">重大</option>
                          	<option value="一般" >一般</option>
                          	<option value="特大">特大</option>
                          	</core:if>
						  <core:if test="${caseLevel=='特大' }">
						  	<option value="特大" selected="selected">特大</option>
						  	<option value="一般" >一般</option>
						  	<option value="特大" >特大</option>
						  </core:if>
						  <core:if test="${caseLevel!='特大'&&caseLevel!='一般'&&caseLevel!='重大' }">
						  	
						  	<option value="一般" >一般</option>
						  	<option value="重大" >重大</option>
						  	<option value="特大" >特大</option>
						  	
						  </core:if>
							
                        </select> --%>
                      </div>
                    </div>
                    
                     <div class="col-xs-10 col-sm-10 col-md-10 col-lg-10">
           				<p class="bg-primary text-left">事实和处理意见</p>
          				 <textarea class="form-control" rows="5" name="factAndSuggestion" id="factAndSuggestion">${factAndSuggestion }</textarea>
       				 </div>
       				 <div class="col-md-12" style="height:50px;"></div>	
       				 <div class="col-xs-10 col-sm-10 col-md-10 col-lg-10">
           				<p class="bg-primary text-left">填写业委会或案审委决定</p>
          				 <textarea class="form-control" rows="2" name="ICOrCRCDecide" id="ICOrCRCDecide">${ICOrCRCDecide }</textarea>
       				 </div>
       				 
        	 </core:if>
			<div class="col-md-12" style="height:50px;"></div>	
             <core:if test="${currentSign=='法制科审批' }">
				<core:if test="${!empty ICSuggest }">
					 <div class="col-xs-10 col-sm-10 col-md-10 col-lg-10" >
	          				<p class="bg-primary">上次业委会对我的驳回意见</p>
	          				<textarea class="form-control" rows="5"  style="margin-top: 0"  >${ICSuggest }</textarea>
         		    </div>
						
				</core:if>
		
		
				<core:if test="${!empty CRCSuggest }">
				
         		    <div class="col-xs-10 col-sm-10 col-md-10 col-lg-10" >
	          				<p class="bg-primary">上次案审委对我的驳回意见</p>
	          				<textarea class="form-control" rows="5"  style="margin-top: 0"  name="legGuideSuggest">${CRCSuggest }</textarea>
         		    </div>
      		   	
				</core:if>
		 
       		</core:if>
             
   				  
            
             
                <input type="hidden" name="taskId" value="${taskId }"/>
            	<input type="hidden" name="currentSign" value="${currentSign }"/>
          	
            <div style="margin-top: 20" class="col-md-7 ">
             <label id="errorInf" style="font-size: 16px;color: red" for="firstname" class="col-xs-4 col-sm-10 col-md-10 col-lg-7  control-label"></label>
            </div> 
                       
         
			
              <s:if test="#outcomeList!=null && #outcomeList.size()>0">
						
			   			 
			   			   	 	 
			                  
			                  
			                    <core:forEach items="${outcomeList}" var="outCome" varStatus="statu">	
			   		   	 		<core:if test="${statu.count==1 }" >
					                   <div class="form-group col-md-1 col-md-offset-1">
					                   		 <br>
					                    
					                  	  <input type="submit" name="outCome" class="btn btn-primary" value="${outCome }" onclick="return validate(this.value)">
					                  
					                    </div>
				                     </core:if>
				                    <core:if test="${statu.count==2 }">
					                    <div class="col-md-1 col-md-offset-2">
					                    	<br>
					                    
					                  	  <input type="submit" name="outCome" class="btn btn-primary" value="${outCome }" onclick="return validate(this.value)">
					                  
					                    </div>
				                 </core:if>
			                  </core:forEach>
			                 
		             
			 </s:if>
			
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