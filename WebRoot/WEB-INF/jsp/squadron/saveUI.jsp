<%@page import="java.util.*" pageEncoding="utf-8" %>
<%@taglib prefix="s" uri="/struts-tags"  %>
<%@include file="/WEB-INF/jsp/share/taglib.jsp" %>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>中队添加/修改页面</title>
<link href="${pageContext.request.contextPath}/css/right4.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
<script src="${pageContext.request.contextPath}/js/saveUI/checkSave.js"></script>

</head>
<body>
     <div class="container ">
       <div class="row bg-white" style="margin-top:2%;">
        <div class="col-md-10 col-md-offset-1  ">
             <br>
            <s:form action="squadronAction_%{id==null ? 'add' : 'edit'}" cssClass="form-horizontal">
              <s:hidden name="id"></s:hidden>
            
             <br>
             	<div style="border-left:#3399FF solid 2px; padding:10px;">
             	  <core:choose>
             	      <core:when test="${id==null}">
             	          <h4>添加中队信息</h4>
             	      </core:when>
             	      <core:otherwise>
             	          <h4>修改中队信息</h4>
             	      </core:otherwise>
             	  </core:choose>
             	</div>
                <div class="col-sm-10">
                <hr></div>
                 <div class="form-group">
                    <label for="" class="col-sm-3 control-label"> 中队名称</label>
                    <div class="col-sm-4">
                      <input name="name" id="sqadronname" value="${name}" type="text" class="form-control" 
                      placeholder="姓名" onblur="checkSqadronName(this)">
                    </div>
                       <div id="sqaname" class="col-sm-4"></div>
                  </div>	   
                       	
                  <div class="form-group">
                    <label for="" class="col-sm-3 control-label">中队账号</label>
                    <div class="col-sm-4">
                      <input name="account" id="sqadronAccount" value="${account}" type="text" class="form-control" 
                      placeholder="个人账号" onblur="checkAccount(this)">
                    </div>
                    <div id="account" class="col-sm-4"></div>
                    <div class="col-sm-4"><font color="red"><s:fielderror fieldName="errorAccount"/></font></div>
                  </div>
                  
                  <core:if test="${id==null }">
                  <div class="form-group">
                    <label for="" class="col-sm-3 control-label">密码 </label>
                    <div class="col-sm-4">
                      <input type="password" value="${password}"  id="sqadronPassword" name="password" class="form-control"
                       placeholder="密码" onblur="checkPsw(this)">
                    </div>
                     <div id="psw" class="col-sm-4"></div>
                  </div>
                  </core:if>
                  
                <div class="form-group">    
                 <br>
                 <core:if test="${id==null}">
                    <div class="col-sm-1 col-sm-offset-6 ">  
                       <input  class="btn btn-primary" type="reset" value="重置"/> 
                    </div>
                 </core:if>  
                  <core:if test="${id==null}"> 
                    <div class="col-sm-1 col-sm-offset-1 ">
                       <s:submit cssClass="btn btn-primary" value="保存" onclick="return submitCheck();"></s:submit>
                    </div>
                  </core:if>
                   <core:if test="${id!=null}">  
                     <div class="col-sm-1 col-sm-offset-1 ">
                       <s:submit cssClass="btn btn-primary" value="保存" onclick="return submitCheck2();"></s:submit>
                      </div>
                    </core:if>
                </div>
              </s:form>    
       	</div>
       </div>      
	</div>
    <div style="height:50px;">
    </div>
</body>
</html>
