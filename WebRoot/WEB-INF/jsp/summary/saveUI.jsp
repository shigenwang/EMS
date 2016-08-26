<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
 <%@ taglib prefix="s" uri="/struts-tags" %>
 <%@ include file="/WEB-INF/jsp/share/taglib.jsp" %>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>案由修改页面</title>
<script src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>
<link href="${pageContext.request.contextPath }/css/right4.css" rel="stylesheet">
</head>
<body>

     <div class="container bg-white">
       <div class="row">
        <div class="col-md-8 col-md-offset-2  col-xs-8 col-xs-offset-2   col-lg-8 col-lg-offset-2 ">
             <br>
            <s:form action="summaryAction_%{id==null ? 'add' : 'edit'}" cssClass="form-horizontal">
               <s:hidden name="id"></s:hidden>
             	<div style="border-left:#3399FF solid 2px; padding:10px;">
             	  	<core:choose>
             	     <core:when test="${id==null }">
             	         <h4>添加案件</h4>
             	      </core:when>
             	    <core:otherwise>
             	        <h4>修改案件</h4>
             	    </core:otherwise>
             	 </core:choose>   
             	</div>
                <hr>
                 <div class="form-group">
                    <label for="" class="col-sm-3 control-label"> 案由的名称</label>
                    <div class="col-sm-8">
                      <input name="name" value="${name}" type="text" class="form-control" placeholder="名称">
                    </div>
                  </div>	
                  <div class="form-group">
                    <label for="" class="col-sm-3 control-label">处罚种类</label>
                    <div class="col-sm-8">
                      <input name="penaltyType" value="${penaltyType }" type="text" class="form-control" placeholder="种类">
                    </div>
                  </div>
              
                   <div class="form-group">
                    <label for="" class="col-sm-3 control-label">违反条例</label>
                    <div class="col-sm-8">
                       <s:textarea name="vioRegulations" rows="7" cssClass="form-control"/>
                   </div>
                </div>
                
                 <div class="form-group">
                    <label for="" class="col-sm-3 control-label">法律依据</label>
                    <div class="col-sm-8">
                     <s:textarea name="legalBasis" cssClass="form-control" rows="7"></s:textarea>
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="" class="col-sm-3 control-label">裁量阶次</label>
                    <div class="col-sm-8">
                     <s:textarea name="cutOrder" cssClass="form-control" rows="7"></s:textarea>
                    </div>
                  </div>
                  
                  <div class="form-group">
                    <label for="" class="col-sm-3 control-label">办理时限</label>
                    <div class="col-sm-8">
                      <input name="timeLimit" value="${timeLimit }" type="text" class="form-control" placeholder="时限">
                    </div>
                  </div>
                  
                <div class="form-group">    
                 <br>
                    <div class="col-sm-1 col-sm-offset-8 ">  
                      <input  class="btn btn-primary" type="reset" value="重置"/> 
                    </div>
                    <div class="col-sm-1 col-sm-offset-1 ">
                      <s:submit cssClass="btn btn-primary" value="保存"></s:submit>
                    </div>
                </div>
   	    
              </s:form>    
       	</div>
       </div>      
	</div>
    <div style="height:50px;">
    </div>
</body>
</html>
