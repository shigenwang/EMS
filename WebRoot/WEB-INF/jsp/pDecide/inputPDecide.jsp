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

<title>行政处罚决定书</title>
<link href="css/right4.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript">


function validate(){
	var bankAccount = $.trim($("#bankAccount").attr("value"));
	
	var caseIntroduction = $.trim($("#caseIntroduction").attr("value"));
	
	var payBank = $.trim($("#payBank").attr("value"));
	
	var adreconTimeLimit = $.trim($("#adreconTimeLimit").attr("value"));
	
	var actualFines = $.trim($("#actualFines").attr("value"));

	if(bankAccount==""||caseIntroduction==""||payBank==""||adreconTimeLimit==""||actualFines==""){
		$("#errorInf").html("信息填写不完整");
		return false;
	}
	
	if(isNaN(actualFines)||actualFines < 0){
		$("#errorInf").html("实际罚款必须为大于零的数字");
		return false;
	}
	if(isNaN(bankAccount)||bankAccount < 0||bankAccount.indexOf(".")!=-1){
		$("#errorInf").html("银行账号必须为大于零的数字");
		return false;
	}
	
	if(isNaN(adreconTimeLimit)||adreconTimeLimit < 0||adreconTimeLimit.indexOf(".")!=-1){
		$("#errorInf").html("行政复议时限必须为大于零的数字");
		return false;
	}
	
	
	
	
	
	return true;
}
	// 预览提交
	function  preview(){
		if(validate()){
			$("form").attr("action","pDecideAction_prePDF");
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
			$("form").attr("action","pDecideAction_submitPDecide");
			$("form").attr("method", "POST");
			$("form").attr("target", "_self");
			$("form").submit();
			return;
		}
		return false;
	}
	
	
	
	
</script>

</head>

<body>
<div >
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
        <div class="col-md-10 col-md-offset-1">
             <br>
            
             <br>
            <form  action="">
         	 		 <input name="taskId" value="${taskId }" type="hidden"/>
					<input name="pDecideId" value="${id }" type="hidden">
            		<div class="form-group">
                    <label for="" class="col-sm-3 control-label">案件简介</label>
                     <span style="float:left; line-height:32px"> 本机关于</span>
                    
                  
                    <div class="col-sm-5">
                      <input type="text" class="form-control" name="caseIntroduction"  value="${caseIntroduction }" id="caseIntroduction">
                    </div>
                      <span style="float:left; line-height:32px"> 一案立案调查.</span>
                      <div class="col-sm-8 col-md-offset-3" style="color:#767676; "> 
                      	<textarea class="form-control" rows="2"  style="margin-top: 0;font-size: 14;border: none;resize:none" readonly="readonly">例如：本机关于2015年3月19日对你单位清运垃圾沿途撒漏一案立案调查。</textarea>
                      	
                      </div>
              </div> 
                  
                  
              	
                  
                  <div class="form-group">
                    <label for="" class="col-sm-3 control-label">实际罚款金额</label>
                    <div class="col-sm-8">
                      <input type="text" class="form-control" placeholder="金额" name="actualFines" value="${actualFines }" id="actualFines">
                    </div>
                  </div>
                  
                 <div class="form-group">
                    <label for="" class="col-sm-3 control-label">缴款银行</label>
                    <div class="col-sm-8">
                     <input type="text" class="form-control" placeholder="金额" name="payBank" value="${payBank }" id="payBank">
                   </div>
                </div>  
                
                 <div class="form-group">
                    <label for="" class="col-sm-3 control-label">缴款银行账号</label>
                    <div class="col-sm-8">
                     <input type="text" class="form-control" placeholder="账号" name="bankAccount" value="${bankAccount }" id="bankAccount">
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="" class="col-sm-3 control-label">行政复议地点二</label>
                    <div class="col-sm-8">
                    <select class="form-control" name="adreconAddress1">
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
	               </select>
	               
                   </div>
                </div>  
   			    	<div class="form-group">
                    <label for="" class="col-sm-3 control-label">行政复议地点二</label>
                    <div class="col-sm-8">
                    <select class="form-control" name="adreconAddress2">
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
	               </select>
                   </div>
                </div>  
                <div class="form-group">
                    <label for="" class="col-sm-3 control-label">行政复议时限天数</label>
                    <div class="col-sm-8">
                     <input type="text" class="form-control" placeholder="天数" name="adreconTimeLimit" value="${adreconTimeLimit }" id="adreconTimeLimit">
                   </div>
                </div>  
	               
                <core:if test="${!empty legalSuggest }">
	                <div class="row">
	      	 			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" >
	          				<p class="bg-primary">上次法制科审批意见</p>
	          			<textarea class="form-control" rows="5"  style="margin-top: 0" >${legalSuggest }</textarea>
	         		 	</div>
	         		</div>
	         	
         		</core:if>
         		
         	<div style="margin-top: 20" class="col-md-5 col-md-offset-2"">
             <label id="errorInf" style="font-size: 16px;color: red" for="firstname" class="col-xs-4 col-sm-10 col-md-10 col-lg-7  control-label"></label>
            </div>
   			
						<s:iterator value="outcomeList">	
								<input type="hidden" id="outCome" name="outCome"/>
				                <div class="form-group col-md-1 col-md-offset-1" style="margin-top:20px;">
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
