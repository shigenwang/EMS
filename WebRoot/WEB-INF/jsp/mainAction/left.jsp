<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/js/commons.jspf"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/WEB-INF/jsp/share/taglib.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>郑州市中原区城市管理</title>

<link href="${pageContext.request.contextPath }/css/menu.css?version=2"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/css/menu2.css?version=1"
	rel="stylesheet" type="text/css" />

<style type="text/css">
</style>
<script language="JavaScript" type="text/javascript"
	src="${pageContext.request.contextPath }/js/menu-jquery.js"></script>

<script type="text/javascript">
	$(function() {
		//导航切换
		$(".menuson li").click(function() {
			$(".menuson li.active").removeClass("active")
			$(this).addClass("active");
		});

		$('.title')
				.click(
						function() {
							var $ul = $(this).next('ul');
							var $downImg = $(this).find("span");
							//var downImg = document.getElementsByClassName("title")[0];
							$('dd').find('ul').slideUp();
							// 在改变被点击图标选项时，先将所有的选项图标回正
							$(".title")
									.find("span")
									.css(
											"background-image",
											"url(${pageContext.request.contextPath }/images/%E6%A8%AA%E7%AE%AD%E5%A4%B4.png)");
							$(".title")
									.css(
											{
												background : "url(${pageContext.request.contextPath }/images/one-bg.png) #F2F2F2 no-repeat",
												height : "42px"
											});
							if ($ul.is(':visible')) {
								$(this).next('ul').toggle();
								$downImg
										.css(
												"background-image",
												"url(${pageContext.request.contextPath }/images/%E6%A8%AA%E7%AE%AD%E5%A4%B4.png)");
								$(this)
										.css(
												{
													background : "url(${pageContext.request.contextPath }/images/one-bg.png) #F2F2F2 no-repeat",
													height : "42px"
												});
							} else {
								$(this).next('ul').toggle();
								$downImg
										.css(
												"background-image",
												"url(${pageContext.request.contextPath }/images/%E7%AB%96%E7%AE%AD%E5%A4%B4.png)");
								$(this)
										.css(
												{
													background : "url(${pageContext.request.contextPath }/images/one-menu-bg.png) #F2F2F2 no-repeat",
													height : "52px"
												});
							}
						});
	})
</script>


</head>


<body style="background:#f0f9fd"; >
    
    <dl class="leftmenu">
	    <dd>
	    <div class="title" style="height:52px;background:url(${pageContext.request.contextPath }/images/one-menu-bg.png) #F2F2F2 no-repeat">
	    <span class="title_span" style="background:url(${pageContext.request.contextPath }/images/%E7%AB%96%E7%AE%AD%E5%A4%B4.png) no-repeat"><!--<img src="images/横箭头.png" />--></span>审批流转
	    </div>
            
   	 <ul class="menuson">
   	 <core:forEach items="${actionUrlSet }" var="actionUrl">
   	 
       <core:if test="${actionUrl=='aboutTaskAction_findTaskListById'}">
        <li class="active"><cite></cite>
        <a href="aboutTaskAction_findTaskListById" target="rightFrame" style="color:black">
        <img src="${pageContext.request.contextPath }/images/linkman.png"  style="margin-right:10px;">我的任务
        </a><i></i></li>
          </core:if>
      
      <core:if test="${actionUrl=='accreditationAction_inputAccreditation'}">
	         <li> <!-- class="active" --><cite></cite>
	        <a href="accreditationAction_inputAccreditation" target="rightFrame" style="color:black">
	        		<img src="${pageContext.request.contextPath }/images/linkman.png" style="margin-right:10px;"/>填写案例审批表
	        </a><i></i></li>
      </core:if>
       
       <core:if test="${actionUrl=='aboutTaskAction_deployHome'}">
	        <li> <!-- class="active" --><cite></cite>   <a href="aboutTaskAction_deployHome.action?ass=1" target="rightFrame" style="color:black">
	        		<img src="${pageContext.request.contextPath }/images/linkman.png" style="margin-right:10px;"/>流程部署
	        </a><i></i></li>
        </core:if>
        
         <core:if test="${actionUrl=='aboutTaskAction_findCandidateTaskListById'}">
         <li><!-- class="active" --><cite></cite>   <a href="aboutTaskAction_findCandidateTaskListById" target="rightFrame" style="color:black">
        		<img src="${pageContext.request.contextPath }/images/linkman.png" style="margin-right:10px;"/>领取任务
        </a><i></i></li>
        </core:if>
       </core:forEach> 
        </ul> 
    </dd>
    
     
    <dd>
     <s:if test="#session.loaderSign=='一般管理员' ">
	  <div class="title" >
	    <span class="title_span"></span>人事管理
	    </div>
     </s:if>
     
    	<ul class="menuson">
    	<core:forEach items="${actionUrlSet }" var="actionUrl">
    	
    	<core:if test="${actionUrl=='squadronAction_list'}">
	        <li class="active"><cite></cite>
	        <a href="squadronAction_list" target="rightFrame" style="color:black">
	        		<img src="${pageContext.request.contextPath }/images/linkman.png" style="margin-right:10px;">中队管理
	        </a><i></i></li>
	     
        </core:if>
        	<core:if test="${actionUrl=='personnelAction_list'}">
         <li class="active"><cite></cite>
	        <a href="personnelAction_list" target="rightFrame" style="color:black">
	        		<img src="${pageContext.request.contextPath }/images/linkman.png" style="margin-right:10px;">人员管理
	        </a><i></i></li>
           </core:if>
       </core:forEach> 
        </ul>    
    </dd>
    
    <s:if test="#session.loaderSign!='一般管理员' ">
    <dd>
    <div class="title" >
    <span class="title_span"></span>查询相关
    </div>
    	<ul class="menuson">
        <li class="active"><cite></cite>
        <a href="queryAction_list" target="rightFrame" style="color:black">
        		<img src="${pageContext.request.contextPath }/images/linkman.png" style="margin-right:10px;"/>汇总查询
        </a><i></i></li>
        </ul>
    </dd>
     </s:if>
   <dd>
	    <s:if test="#session.loaderSign=='一般管理员' ">
		    <div class="title" >
		    <span class="title_span" ></span>裁量阶次
		    </div>
	    </s:if>
    
    	<ul class="menuson">
    	<core:forEach items="${actionUrlSet }" var="actionUrl">
    	
    	<core:if test="${actionUrl=='summaryAction_list'}">
        <li class="active"><cite></cite>
        <a href="summaryAction_list" target="rightFrame" style="color:black">
        	<img src="${pageContext.request.contextPath }/images/linkman.png" style="margin-right:10px;"/>案由管理
        </a><i></i></li>
        </core:if>
        </core:forEach>
        </ul>    
    </dd>
    
    <dd>
    <div class="title" >
    <span class="title_span" ></span>个人设置
    </div>
  
    <s:if test="#session.loaderSign=='中队' ">
    	<ul class="menuson">
        <li class="active"><cite></cite>
        <a href="squadronAction_squadronEditUI?id=${squadornSign.id}" target="rightFrame" style="color:black">
        	<img src="${pageContext.request.contextPath }/images/linkman.png" style="margin-right:10px;"/>个人信息修改
        </a><i></i></li>
        <li class="active"><cite></cite>
     
        <a href="squadronAction_editPasswordUI?id=${squadornSign.id}" target="rightFrame" style="color:black">
        	<img src="${pageContext.request.contextPath }/images/linkman.png" style="margin-right:10px;"/>修改密码
        </a><i></i></li>
        </ul> 
     </s:if>
     <s:else>
        <ul class="menuson">
        <li class="active"><cite></cite>
        <a href="personnelAction_personnelEditUI?id=${globle_user.id}" target="rightFrame" style="color:black">
        	<img src="${pageContext.request.contextPath }/images/linkman.png" style="margin-right:10px;"/>个人信息修改
        </a><i></i></li>
        <li class="active"><cite></cite>
       
     
        <a href="personnelAction_editPasswordUI?id=${globle_user.id}" target="rightFrame" style="color:black">
        	<img src="${pageContext.request.contextPath }/images/linkman.png" style="margin-right:10px;"/>修改密码
        </a><i></i></li>
        </ul> 
      </s:else>
    </dd>

		<%-- <dd>
    <div class="title">
    <span class="title_span"></span>其他设置
    </div>
    <ul class="menuson">
        <li><cite></cite><a href="#">编辑内容</a><i></i></li>
        <li><cite></cite><a href="#">发布信息</a><i></i></li>
        <li><cite></cite><a href="#">档案列表显示</a><i></i></li>
        </ul>     
    </dd> 
    
    
    <dd><div class="title"><span class="title_span"></span>编辑器</div>
    <ul class="menuson">
        <li><cite></cite><a href="#">自定义</a><i></i></li>
        <li><cite></cite><a href="#">常用资料</a><i></i></li>
        <li><cite></cite><a href="#">信息列表</a><i></i></li>
        <li><cite></cite><a href="#">其他</a><i></i></li>
    </ul>    
    </dd>  
    
    
    <dd><div class="title"><span class="title_span"></span>日期管理</div>
    <ul class="menuson">
        <li><cite></cite><a href="#">自定义</a><i></i></li>
        <li><cite></cite><a href="#">常用资料</a><i></i></li>
        <li><cite></cite><a href="#">信息列表</a><i></i></li>
        <li><cite></cite><a href="#">其他</a><i></i></li>
    </ul>
    
    </dd>    --%>


	</dl>

</body>
</html>
