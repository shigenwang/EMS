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

<title>行政处罚告知书</title>
<link href="css/right4.css" rel="stylesheet">
<style type="text/css">
textarea {
    resize: none;
}
</style>
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript">

function validate(){
	if("${accreditation.unitName}"!=""){
		var busLicense = $.trim($("#busLicense").attr("value"));
		//alert(busLicense);
		if(busLicense==""){
		
			$("#errorInf").html("营业执照注册号不能为空");
			return false;
		}
	}
	var illegalContent = $.trim($("#illegalContent").attr("value"));
	
	var fines = $.trim($("#fines").attr("value"));
	
	var sumitDate = $.trim($("#sumitDate").attr("value"));
	
	if(illegalContent==""||fines==""||sumitDate==""){
		$("#errorInf").html("信息填写不完整");
		return false;
	}
	if(isNaN(fines)){
		//有问题，如果输入的是以0开头的数字，不会出错
		$("#errorInf").html("罚款金额必须为数字");
		
		return false;

	}
	if(fines <= 0){
		$("#errorInf").html("罚款金额必须大于零");
		return false;
	}
	
	
	if(isNaN(sumitDate)){
		$("#errorInf").html("时限天数必须为数字");
		
		return false;

	}
	
	return true;
}
	// 预览提交
	function  preview(){
		
		if(validate()){
			
			$("form").attr("action","pNoticeAction_prePDF");
			$("form").attr("target", "_blank");
			$("form").attr("method", "POST");
			$("form").submit();
			return;
		}
		return false;
		
	}
	// 表单提交
	function formSubmit(outCome){
	
		if(validate()){
			$("#outCome").attr("value",outCome);
			
			$("form").attr("action","pNoticeAction_submitPNotice");
			$("form").attr("method", "POST");
			$("form").attr("target", "_self");
			$("form").submit();
			return;
		}
		return false;
		
		
	}
	
	
</script>


<!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<div>
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
    
     <div class="container bg-white">
       <div class="row">
        <div class="col-md-12 col-md-offset-0 col-xs-10 col-xs-offset-1   col-lg-10 col-lg-offset-1 ">
             <br>
             
                 
            <form class="form-horizontal" action="">
            	<br>
            	<input type="hidden" name="taskId" value="${taskId }"/>
		
				<input type="hidden" name="pNoticeId" value="${id }"/><br/>
                <core:if test="${!empty accreditation.unitName }"> 
                  <div class="form-group">
                    <label for="" class="col-sm-3 control-label"> 营业执照注册号</label>
                    <div class="col-sm-8">
                      <input id="busLicense" name="busLicense" type="text" class="form-control" placeholder="注册号" value="${busLicense}" >
                    </div>
                  </div>
                  </core:if>	
                 
                  <div class="form-group">
                    <label for="" class="col-sm-3 control-label">违法行为性质</label>
                    <div class="col-sm-8">
                     <select class="form-control" name="illegalstyleId">
                      	<core:forEach items="${illegalStyles }" var="illegalStyle">
							<core:if test="${illegalStyle.id==pNotice.illegalStyles.id}">                   	
								<option  value="${illegalStyle.id }" selected="selected">${illegalStyle.name }</option>
							</core:if>   
							<core:if test="${illegalStyle.id!=pNotice.illegalStyles.id}">                   	
								<option  value="${illegalStyle.id }" >${illegalStyle.name }</option>
							</core:if> 
						</core:forEach>
                      
                    </select>
                   	</div>
               	  </div>
                
                 <div class="form-group">
                    <label for="" class="col-sm-3 control-label">违法行为</label>
                    <div class="col-sm-8">
                     <textarea id="illegalContent" name="illegalContent" class="form-control" rows="7">${illegalContent}</textarea>
                    </div>
                  </div>
                   <div class="form-group">
                  		  <label for="" class="col-sm-3 control-label">违法行为填写示例</label>
                   			<div  class="col-sm-8"  >
          					
          					<textarea class="form-control" rows="2"  style="margin-top: 0;font-size: 14;border: none;" readonly="readonly" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;你（你单位）于2015年4月9日在文化公路与市场街西南角处，实施了占用城市道路，长5.6米，宽3米，面积16.8平方米，经责令改正后，采取一定补救措施的行为。</textarea>
          			
         		 		</div>
               
                    
                  </div>
                  <div class="form-group">
                    <label for="" class="col-sm-3 control-label">罚款金额</label>
                     <div class="col-sm-8">
                      <input id="fines" name="fines" type="text" class="form-control" placeholder="金额" value="${fines }">
                     </div>
                  </div>
                  <div class="form-group ">
                    <label for="" class="col-sm-3 control-label">裁量阶次（供罚款参考使用）</label>
                    
                    	<div  class="col-sm-8"  >
          					
          					<textarea class="form-control" rows="14"  style="margin-top: 0;font-size: 14;border: none;" readonly="readonly">${summary.cutOrder }</textarea>
          			
         		 		</div>
                     
                  </div>
                   <div class="form-group">
                    <label for="" class="col-sm-3 control-label">其他处罚信息</label>
                    <div class="col-sm-8">
                      <input type="text" class="form-control" name="otherpenaltyInf" placeholder="其他处罚信息" value="${otherpenaltyInf }">
                    </div>
                  </div>
                  
                	<div class="form-group">
	                    <label for="" class="col-sm-3 control-label">陈述申辩时限天数</label>
	                    <div class="col-sm-8">
	                      <input id="sumitDate" name="sumitDate" type="text" class="form-control" value="${sumitDate }" placeholder="陈述申辩时限" >
	                      	
	                    </div>
                  </div>
                  <div class="form-group">
                  		  <label for="" class="col-sm-3 control-label"></label>
                   			<div  class="col-sm-8 "  >
          					
          					<textarea class="form-control" rows="1"  style="margin-top: 0;font-size: 14;border: none;" readonly="readonly" >例如：3</textarea>
          			
         		 		</div>
               
                    
                  </div>
       		      <core:if test="${!empty legalSuggest }">
	                  <div class="form-group ">
	      	 			<div class="col-sm-8  col-md-offset-3" >
	          				<p class="bg-primary">上次法制科审核意见</p>
	          			<textarea class="form-control" rows="10"  style="margin-top: 0" readonly="readonly">${legalSuggest }</textarea>
	         		 	</div>
	         			 </div>
         		 </core:if>
         		 
         		 
         		 		   <div style="margin-top: 25" class="col-md-7 ">
             <label id="errorInf" style="font-size: 16px;color: red" for="firstname" class="col-xs-4 col-sm-10 col-md-10 col-lg-7  control-label"></label>
            </div>
		      
						<s:iterator value="outcomeList">	
			                 	<input type="hidden" id="outCome" name="outCome"/>
			                 	
				                <div class="col-md-1 col-md-offset-1" style="margin-top:20px;">
		       						 <input type="submit" class="btn btn-primary" value="<s:property/>" onclick="return formSubmit(this.value)">
		      			  		</div>
		        
								<div class="col-md-1 col-md-offset-2" style="margin-top:20px;">
						       		 <input class="btn btn-primary"  type="button" onclick="return preview()"  value="预览">
						        </div>
       
		                  </s:iterator>
				
					
					 
              </form>    
       	</div>
       </div>      
	</div>
    <div style="height:50px;">
    </div>
</div>



</body>
</html>
