<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
 <%@ taglib prefix="s" uri="/struts-tags" %>
   <%@ include file="/WEB-INF/jsp/share/taglib.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<html>
<head>
<meta charset="utf-8">

<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>人员管理页面</title>
<link href="${pageContext.request.contextPath}/css/right4.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
<script src="${pageContext.request.contextPath}/js/saveUI/checkSave2.js"></script>


</head>
<body>
     <div class="container bg-white">
       <div class="row">
        <div class="col-sm-11 ">
             <br>
            <s:form  action="personnelAction_personnelEdit"
           onsubmit="return sele();"  cssClass="form-horizontal">
				<!-- 隐藏字段id -->
			   <s:hidden name="id"></s:hidden>
			   
             <br>
             	<div style="border-left:#3399FF solid 2px; padding:10px">
             	      <h4>修改人员信息</h4>
             	</div>
             	<div class="col-sm-10">
                <hr></div>
                 <div class="form-group">
                    <label for="" class="col-sm-3 control-label"> 人员的名称</label>
                    <div class="col-sm-4">
                      <input id="username" type="text" value="${name}" name="name" class="form-control" 
                      placeholder="姓名" onblur="checkName(this)"/>
                    </div>
                    <div id="name" class="col-sm-4"></div>
                    <div class="col-sm-4"><s:fielderror fieldName="name"/></div>
                  </div>	
                  
                  <div class="form-group">
                    <label for="" class="col-sm-3 control-label">性别 </label>
                    <div class="col-sm-8">
                     <core:choose>
                          <core:when test="${gender =='男'}">
                             <label class="checkbox-inline">
                                 <input  type="radio" id="inlineCheckbox1" name="gender" checked="checked" value="男"> 男
                                 <input  type="radio" id="inlineCheckbox2" name="gender"  value="女"> 女
                              </label>
                           </core:when>
                            <core:when test="${gender=='女'}">
                               <label class="checkbox-inline">
	                             <input  type="radio" id="inlineCheckbox1" name="gender"  value="男"> 男
	                             <input  type="radio" id="inlineCheckbox2" name="gender" checked="checked" value="女"> 女
	                          </label> 
                            </core:when>
                           <core:otherwise>
	                            <input  type="radio" id="inlineCheckbox1" name="gender"  value="男"> 男
                                <input  type="radio" id="inlineCheckbox2" name="gender"  value="女"> 女  
                           </core:otherwise>
                     </core:choose>
                    </div>
                  </div>
              
                  <div class="form-group">
                    <label for="" class="col-sm-3 control-label">个人账号</label>
                    <div class="col-sm-4">
                      <input type="text" id="userAccount" value="${account }"  name="account" class="form-control" 
                      placeholder="个人账号" onblur="checkAccount(this)">
                    </div>
                    <div id="account" class="col-sm-4"></div>
                  </div>
                  
                  	<div class="form-group">
                    <label for="" class="col-sm-3 control-label">电话 </label>
                    <div class="col-sm-4">
                      <input value="${telephone}" id="tel2" type="text" name="telephone" class="form-control" 
                      placeholder="电话"  onblur="checkTel(this)">
                    </div>
                    <div id="tel" class="col-sm-4"></div>
                  </div>
                  
                  
                <div class="form-group">    
                 <br>
                 <core:if test="${id==null}">
                    <div class="col-sm-1 col-sm-offset-6 ">  
                    <input  class="btn btn-primary" type="reset" value="重置"/> 
                    </div>
                  </core:if>
                    <div class="col-sm-1 col-sm-offset-1 ">
                     <s:submit cssClass="btn btn-primary"  value="保存" onclick="return personnelAdd2();"></s:submit>
                    </div>
                </div>
              </s:form>    
       	</div>
       </div>      
	</div>
    <div style="height:50px;">
    </div>
</div>

</body>
</html>
