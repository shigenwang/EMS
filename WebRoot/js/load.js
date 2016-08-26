			//1.用于实现验证码的局部刷新---
			function changeImg(){
				var imgSrc=$("#validateImg");
				var src=imgSrc.attr("src");
				imgSrc.attr("src",chgUrl(src));
			}
			
			//2.用于实现验证码的局部刷新----时间戳
			//为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳
			function chgUrl(url){
				var timestamp=(new Date()).valueOf();
				url=url+"?timestamp="+timestamp;
				return url;
			}
			
			//用于解决窗口的嵌套问题
	        if(window.parent!=window){
	        	window.parent.location.reload(true);
	        }
		   
		  //用于解决enter键实现表单的提交
	       $(document).keyup(function(event){
	    	   if(event.keyCode==13){
	    		   $("#Button1").trigger("click");
	    	   }
	       });
	       
	      
	       
	       //用于ajax方法的校验
	       $(function()
	    	       {
	    	           $("#Button1").click(function(){
	    	        	   var contextPath = $.trim($("#contextPath").attr("value"));
	    	        	   //alert(contextPath);
	    	        	  var identity=$("#identity").val();
	    	        	 var checkCode=$("#checkCode").val();
	    	              var account=encodeURI($("#account").val());
	    	              var password=encodeURI($("#password").val());
	    	              $.ajax(
	    	              {
	    	            	  type: "POST",  
	    	                  url:"loginAction2!login",
	    	                  dataType:"json",
	    	                  data:{
	    	                	  identity:identity,
	    	                	  account:account, 
	    	                	  password:password,
	    	                	  checkCode:checkCode},
	    	                  success:function(strValue)
	    	                  {
	    	                	 
	    	                     if(strValue=="1"){
	    	                    	 window.location.href = contextPath+"/index.jsp";
	    	                     }
	    	                     if(strValue=="3"){
	    	                         $("#divTip").html("用户名或密码错误");
	    	                     }
	    	                     if(strValue=="4"){
	    	                         $("#divTip").html("您选择的角色有问题！");
	    	                     }
	    	                     if(strValue=="2"){
	    	                    	 $("#divTip").html("验证码错误，请重新输入！");
	    	                     }
	    	                     if(strValue=="0"){
	    	                    	 $("#divTip").html("未知错误！"); 
	    	                     }
	    	                  }
	    	              })
	    	           })
	    	       })

