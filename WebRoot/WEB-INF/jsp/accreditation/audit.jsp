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
    <title>立案审批表</title>
    
<link href="css/examine1.css?version=0" rel="stylesheet">
<link href="css/kuang.css?version=5" rel="stylesheet" type="text/css" />
<link href="css/alert-handle.css?version=1" rel="stylesheet" type="text/css" />
<link href="css/alert-handle2.css?version=3" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script> 
<script type="text/javascript" src="js/tipswindown.js?version=2"></script>
<script type="text/javascript" src="js/document.js?version=2"></script> 

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
		var idCardFlag = $.trim($("#idCardFlag").attr("value"));
		var enforceCardFlag = $.trim($("#enforceCardFlag").attr("value"));
		var orderChangeNoticeFlag = $.trim($("#orderChangeNoticeFlag").attr("value"));
		var recordInquestFlag = $.trim($("#recordInquestFlag").attr("value"));
		var sitePhotosFlag = $.trim($("#sitePhotosFlag").attr("value"));
		var recordInvFlag = $.trim($("#recordInvFlag").attr("value"));
		var recordPaperFlag = $.trim($("#recordPaperFlag").attr("value"));
		
		if(idCardFlag==""||idCardFlag=="false"||enforceCardFlag==""||enforceCardFlag=="false"||
			orderChangeNoticeFlag==""||orderChangeNoticeFlag=="false"||recordInquestFlag==""||recordInquestFlag=="false"||
			sitePhotosFlag==""||sitePhotosFlag=="false"||recordInvFlag==""||recordInvFlag=="false"||
			recordPaperFlag==""||recordPaperFlag=="false"
		){
			$("#errorInf").html("批准时必须通过全部上传的文件");
			return false;
		}else if("${unitName}"!=""){
			var businessLicenseFlag = $.trim($("#businessLicenseFlag").attr("value"));
			if(businessLicenseFlag==""||businessLicenseFlag=="false"){
				$("#errorInf").html("批准时必须通过全部上传的文件");
				return false;
			}
			
			
		}
		
	}
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
   
  </head>

 <body>
   <nav class="navbar navbar-default">
       <div class="container-fluid">
         
              <ul class="nav navbar-nav">
              	<core:forEach items="${taskTypeList }"  var="taskType">
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
           <caption>郑州市中原区城市管理行政执法局<br>案&nbsp;件&nbsp;立&nbsp;案&nbsp;审&nbsp;批&nbsp;表<p class="text-center" style="margin-top:10px; font-weight:normal;font-size:16px;">(中原行执）立案字【&nbsp;&nbsp;】  第${id } 号 </p></caption>
          
          
           <tr>
             <th width="10%" rowspan="6">当事人</th>
             <th width="10%" rowspan="3">单位</th>
             <th>名称</th>
             <td colspan="5">
             	 <core:if test="${empty unitName }">/</core:if>
             
             	<core:if test="${!empty unitName }">${unitName }</core:if>
             </td>
           </tr>
           <tr>
             <th width="15%">法定代表人<br>（负责人）
             </th>
             <td>
             	 <core:if test="${empty leRepresentative }">/</core:if>
             
             	<core:if test="${!empty leRepresentative }">${leRepresentative }</core:if>
             
            </td>
             <th width="10%">电话</th>
             <td colspan="3">
             	 <core:if test="${empty unitTel }">/</core:if>
             
             	<core:if test="${!empty unitTel }">${unitTel }</core:if>
             </td>
           </tr>
           <tr>
             <th>地址</th>
             <td colspan="5">
             	 <core:if test="${empty unitAddress }">/</core:if>
             
             	<core:if test="${!empty unitAddress }">${unitAddress }</core:if>
             </td>
           </tr>
           
           <tr>
             <th rowspan="3">个人</th>
             <th>姓名</th>
             <td width="20%">
             	<core:if test="${empty personnelName }">/</core:if>
             
              	<core:if test="${!empty personnelName }">${personnelName }</core:if>
             </td>
             <th>性别</th>
             <td width="10%">
             <core:if test="${empty sex }">/</core:if>
             
             <core:if test="${!empty sex }">${sex }</core:if>
             
             
             </td>
             <th width="10%">年龄</th>
             <td width="20%">
             	  <core:if test="${empty userAge }">/</core:if>
             
             	  <core:if test="${!empty sex }"> 
		             	  <s:if test="userAge==0">""</s:if>
		            		<s:else>${userAge }</s:else>
            	  </core:if>
            		</td>
            
             
             
           
           </tr>
           <tr>
             <th>住址</th>
             <td colspan="5">
             	 <core:if test="${empty userAddress }">/</core:if>
             
             	<core:if test="${!empty userAddress }">${userAddress }</core:if>
             
             
          </td>
           </tr>
           <tr>
             <th>身份证号码</th>
             <td colspan="2">
             	 <core:if test="${empty idNumber }">/</core:if>
             
             	<core:if test="${!empty idNumber }">${idNumber }</core:if>
             </td>
             <th>电话</th>
             <td colspan="2">
             		 <core:if test="${empty userTel }">/</core:if>
             
             	<core:if test="${!empty userTel }">${userTel }</core:if>
             
             </td>
           </tr>
           <tr>
             <th colspan="2">案件来源</th>
             <td colspan="6">
   				<core:forEach items="${caseSources }" var="cs">
					<core:if test='${cs.id==caseSource.id}' >
						
						 <label class="radio-inline">
      				 	   <input type="radio"  id="inlineRadio1" value="option1" checked="checked" > ${cs.name }
       					 </label>	
					
					</core:if>
					<core:if test='${cs.id!=caseSource.id}' >
						 <label class="radio-inline">
          					<input type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1" disabled="disabled"> ${cs.name }
      					 </label>
					</core:if>
				</core:forEach>
       
             </td>
           </tr>
           <tr>
             <th colspan="2">立案时间</th>
             <td colspan="6"></td>
           </tr>
           <tr>
             <th colspan="2">基本案情</th>
             <td colspan="6" height="70" style="text-align: left;"><span>${baseCase } </span></td>
           </tr>
            <tr>
             <th colspan="2">承办人意见</th>
             <td colspan="6" height="60">
             	
                <!--<div>案件主办人： 徐闯</div>
                <div>协办人：张艺</div>
                <div>2015.4.9</div>-->
              
	                <core:if test="${hostFlag&&joinFlag}">
	                	<p>建议立案</p>
	                </core:if>
	                
	                
	                 <core:if test="${hostFlag}"><span>${sponsorName }</span></core:if>
	              
	                 <core:if test="${joinFlag}"><span>${assistantHandleName }</span></core:if>
	                 
	             	<core:if test="${hostFlag&&joinFlag}"> <div><s:date name="hostDate" format="yyyy.MM.dd"/></div></core:if>
                		
               	 
             </td>
           </tr>
           <tr>
             <th colspan="2">承办部门意见</th>
             <td colspan="6" height="60">
             	<core:if test="${captainFlag}">
	             	 <p>同意</p>
	                 <div><s:date name="captainDate" format="yyyy.MM.dd"/></div>
	                 <div>${captainName}</div>  
                 </core:if>  
                       
             </td>
           </tr>
           <tr>
             <th colspan="2">法制机构审核意见</th>
             <td colspan="6" height="60">
             	<core:if test="${legalFlag}">
	             	<p>同意</p>
	                 <div><s:date name="legalDate" format="yyyy.MM.dd"/></div>
	                 <div>${ legName}</div>  
                 </core:if>     
                        
             </td>
           </tr>
           <tr>
             <th colspan="2">领导审批意见</th>
             <td colspan="6" height="60">
            	<core:if test="${chiefFlag}">
	             	<p>同意</p>
	                 <div><s:date name="bigCaptainDate" format="yyyy.MM.dd"/></div>
	                 <div>${chiefName}</div>  
                 </core:if>      
                     
             </td>
           </tr>
         </table>
   </div>
   <form class="form-horizontal col-md-10 col-md-offset-1" action="accreditationAction_submitTask">
   <core:if test="${!empty captainSuggest }">
			<core:if test="${currentSign=='中队长审批' }">
				 <div class="row">
      	 			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
          				<p class="bg-primary">上次我的审批意见</p>
          			<textarea class="form-control" rows="5"   style="margin-top: 0">${captainSuggest }</textarea>
         		 </div>
      			 </div>
			</core:if>
			<core:if test="${currentSign!='中队长审批' }">
				<div class="row">
      	 			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
          				<p class="bg-primary">上次中队长审批意见</p>
          			<textarea class="form-control" rows="5"  style="margin-top: 0" >${captainSuggest }</textarea>
         		 </div>
      			 </div>
			</core:if>
			
		</core:if>
		<core:if test="${!empty legSuggest }">
			<core:if test="${currentSign=='法制科审批' }">
				 <div class="row">
      	 			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
          				<p class="bg-primary">上次我的审批意见</p>
          			<textarea class="form-control" rows="5"  style="margin-top: 0">${legSuggest }</textarea>
         		 </div>
      			 </div>
			</core:if>
			<core:if test="${currentSign!='法制科审批' }">
				 <div class="row">
      	 			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
          				<p class="bg-primary">上次法制科审批意见</p>
          			<textarea class="form-control" rows="5"  style="margin-top: 0" >${legSuggest }</textarea>
         		 </div>
      			 </div>
			</core:if>
			
		</core:if>
		<core:if test="${!empty bigCaptainSuggest }">
			<core:if test="${currentSign=='大队长审批' }">
				<div class="row">
      	 			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
          				<p class="bg-primary">上次我的审批意见</p>
          			<textarea class="form-control" rows="5"   style="margin-top: 0">${bigCaptainSuggest }</textarea>
         		 </div>
      			 </div>
			</core:if>
			<core:if test="${currentSign!='大队长审批' }">
				<div class="row">
      	 			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
          				<p class="bg-primary">上次大队长审批意见</p>
          			<textarea class="form-control" rows="5"  style="margin-top: 0" >${bigCaptainSuggest }</textarea>
         		 </div>
      			 </div>
			</core:if>
		</core:if>
		
		
   	<div class="row" style="margin-bottom:50px;">  
     
        <core:if test="${currentSign!='主办审批'&&currentSign!='协办审批' }">
	         <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
	          				<p class="bg-primary">本次我的意见</p>
	          				
	          			<textarea class="form-control" rows="5" id="suggest" 
	          				<core:if test="${currentSign=='法制科指导' }">name="legGuideSuggest"</core:if>
	          				<core:if test="${currentSign=='中队长审批' }">name="captainSuggest"</core:if>
	          				<core:if test="${currentSign=='法制科审批' }">name="legSuggest"</core:if>
	          				<core:if test="${currentSign=='大队长审批' }">name="bigCaptainSuggest"</core:if>
	          			></textarea>
	        </div>
        </core:if> 
        	<input name="taskId" type="hidden" value="${taskId }">
			<input name="accreditationId" type="hidden" value="${accreditationId }">
            <div class="col-md-12" style="height:50px;">
            
            </div>
            
            <input type="hidden" name="currentSign" value="${currentSign }"/>
          <div style="margin-top: 25" class="col-md-7 ">
             <label id="errorInf" style="font-size: 16px;color: red" for="firstname" class="col-xs-4 col-sm-10 col-md-10 col-lg-7  control-label"></label>
            </div>        
        	
            <s:if test="#outcomeList!=null && #outcomeList.size()>0">
						
			   		   	 	<core:forEach items="${outcomeList}" var="outCome" varStatus="statu">	
			   		   	 		<core:if test="${statu.count==1 }" >
					                   <div class="col-md-1 col-md-offset-1">
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
					<core:if test="${!empty idCard }">
						<div class="anniu">
							<a id="idCard" href="javascript:void(0)" >查看</a>
						</div>
					</core:if>
					
			</li>
			
			
			<li><img id="enforceCardImg" <core:if test="${enforceCardFlag }">src="images/alert/20150729042348932_easyicon_net_32.png"</core:if><core:if test="${!enforceCardFlag }">src="images/alert/cuohao.png"</core:if>/>
			
				<div class="wenzi">执法证</div> 
				<core:if test="${!empty enforceCard }">
					<div class="anniu">
						<a id="enforceCard" href="javascript:void(0);">查看</a>
					</div>
				</core:if>
				
			</li>
			<li><img id="orderChangeNoticeImg" <core:if test="${orderChangeNoticeFlag }">src="images/alert/20150729042348932_easyicon_net_32.png"</core:if><core:if test="${!orderChangeNoticeFlag }">src="images/alert/cuohao.png"</core:if>/>
			
				<div class="wenzi">责令改正通知书</div> 
				<core:if test="${!empty orderChangeNotice }">
					<div class="anniu">
						<a id="orderChangeNotice" href="javascript:void(0)">查看</a>
					</div>
				</core:if>
			
				
			</li>
			
			<li><img id="recordInquestImg" <core:if test="${recordInquestFlag }">src="images/alert/20150729042348932_easyicon_net_32.png"</core:if><core:if test="${!recordInquestFlag }">src="images/alert/cuohao.png"</core:if>/>
				
				<div class="wenzi">现场勘验笔录</div> 
				<core:if test="${!empty recordInquest }">
					<div class="anniu">
						<a id="recordInquest" href="javascript:void(0)">查看</a>
					</div>
				</core:if>
				
				
			</li>
			<li><img  id="sitePhotosImg"  <core:if test="${sitePhotosFlag }">src="images/alert/20150729042348932_easyicon_net_32.png"</core:if><core:if test="${!sitePhotosFlag }">src="images/alert/cuohao.png"</core:if>/>
			
				<div class="wenzi">现场照片</div> 
				<core:if test="${!empty sitePhotos }">
					<div class="anniu">
						<a id="sitePhotos" href="javascript:void(0)">查看</a>
					</div>
				</core:if>
				
				
			</li>
			<li><img  id="recordInvImg" <core:if test="${recordInvFlag }">src="images/alert/20150729042348932_easyicon_net_32.png"</core:if><core:if test="${!recordInvFlag }">src="images/alert/cuohao.png"</core:if>/>
			
				<div class="wenzi">调查询问笔录</div> 
				<core:if test="${!empty recordInv }">
					<div class="anniu">
						<a id="recordInv" href="javascript:void(0)">查看</a>
					</div>
				</core:if>
			
				
			</li>
			<li><img id="recordPaperImg" <core:if  test="${recordPaperFlag }">src="images/alert/20150729042348932_easyicon_net_32.png"</core:if><core:if test="${!recordPaperFlag }">src="images/alert/cuohao.png"</core:if>/>
			
				<div class="wenzi">笔录纸</div> 
				<core:if test="${!empty recordPaper }">
					<div class="anniu">
						<a id="recordPaper" href="javascript:void(0)">查看</a>
					</div>
				</core:if>

			</li>
           
           <core:if test="${!empty unitName }">
				<li><img id="businessLicenseImg" <core:if  test="${businessLicenseFlag }">src="images/alert/20150729042348932_easyicon_net_32.png"</core:if><core:if test="${!businessLicenseFlag }">src="images/alert/cuohao.png"</core:if>/>
				
					<div class="wenzi">营业执照</div> 
					<core:if test="${!empty businessLicense }">
						<div class="anniu">
							<a id="businessLicense" href="javascript:void(0)">查看</a>
						</div>
					</core:if>
				</li>
           </core:if>
		</ul>
	</div>
	
	</div>
          <input type="hidden" id="idCardFlag" name="idCardFlag" value="${idCardFlag }"/>
		<input type="hidden" id="enforceCardFlag" name="enforceCardFlag" value="${enforceCardFlag }"/>
		<input type="hidden" id="orderChangeNoticeFlag" name="orderChangeNoticeFlag" value="${orderChangeNoticeFlag }"/>
		<input type="hidden" id="recordInquestFlag" name="recordInquestFlag" value="${recordInquestFlag }"/>
		<input type="hidden" id="sitePhotosFlag" name="sitePhotosFlag" value="${sitePhotosFlag }"/>
		<input type="hidden" id="recordInvFlag" name="recordInvFlag" value="${recordInvFlag }"/>
		<input type="hidden" id="recordPaperFlag" name="recordPaperFlag" value="${recordPaperFlag }"/>
		<input type="hidden" id="businessLicenseFlag" name="businessLicenseFlag" value="${businessLicenseFlag }"/>
     </form>
          
	
     </div>

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