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
    <title>审批决定书</title>
    

    <style> 
        table { table-layout:fixed; word-break: break-all; word-wrap: break-word; }  
           textarea {
				resize:none;
			} 
    </style> 
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script> 
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
    }
    
    </script>
<link href="css/examine1.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
   <nav class="navbar navbar-default">
     
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
   </nav>
  <form class="form-horizontal col-md-12 col-md-offset-1" action="pTableAction_submitTask">
  <div class="container">
  	
  
  	<div class="col-md-10 col-md-offset-0">
        <table class="table table-bordered text-center"  style="font-size: 16px">
        <caption>郑州市中原区城市管理行政执法局<br>案审委办公案件审批表</caption>  
          <tr>
            <th width="20%">案由</th>
            <td width="30%">擅自占用城市道路</td>
            <th width="20%">当事人</th>
            <td width="30%">马骝</td>
          </tr>
          <tr>
            <th width="20%">承办中队</th>
            <td width="30%">建办中队</td>
            <th width="20%">承办人</th>
            <td width="30%">${accreditation.sponsor.name }、${accreditation.assistantHandle.name }</td>
          </tr>
          <tr>
            <th width="20%">事实和处理意见</th>
            
            <td colspan="3">
            	<core:if test="${!empty factAndSuggestion }">
            		<p>${factAndSuggestion }</p>
            		<div style="height: 100"></div>
            	</core:if>
            	<core:if test="${empty factAndSuggestion }">
            		 		<div style="height: 150"></div>
		                	
            	</core:if>
            </td>
          </tr>
          <tr>
            <th width="20%">案审委决定</th>
            	<td colspan="3" height="100">  
		            	${ICOrCRCDecide } 
           		 </td>	
          </tr>
          <tr>
            <th width="20%">案审委成员签名</th>
            <td colspan="3" height="100">
	            		${caseRevCommNames }

            </td>
          </tr>
        </table>
     
	</div>     
	
	<div class="row" style="margin-top:40px;">  
        				<core:if test="${!empty CRCSuggest }">
        				 <div class="col-xs-10 col-sm-10 col-md-10 col-lg-10  col-md-offset-0" >
	          				<p class="bg-primary">上次案审委驳回时意见</p>
	          				<textarea class="form-control" rows="5"  style="margin-top: 0">${CRCSuggest }</textarea>
          				
         		 			</div>
						<div class="col-md-12" style="height:50px;"></div>
						</core:if>
              		
         			 <div class="col-xs-10 col-sm-10 col-md-10 col-lg-10  col-md-offset-0" >
          				<p class="bg-primary">本次意见</p>
          				<textarea class="form-control" rows="5"  style="margin-top: 0" name="CRCSuggest" id="suggest"></textarea>
          				
         		 	</div>
          <input type="hidden" name="taskId" value="${taskId }"/>
		<input type="hidden" name="currentSign" value="${currentSign }"/>
		<div class="col-md-12" style="height:50px;"></div>	
        <div style="margin-top: 20" class="col-md-7">
					<label id="errorInf" style="font-size: 16px; color: red"
						for="firstname"
						class="col-xs-4 col-sm-10 col-md-10 col-lg-7  control-label"></label>
				</div>
				
				<div class="col-md-1 col-md-offset-1">
						<br>
					 <input class="btn btn-primary" type="submit" name="outCome" value="批准" onclick="return validate(this.value)">
				</div>
				
				<div class="col-md-1 col-md-offset-1">
						<br>
					 <input class="btn btn-primary" type="submit" name="outCome" value="驳回" onclick="return validate(this.value)" >
				</div>
         	
               
              
       </div>

    
</div>	
</form>
   
  </body>
</html>