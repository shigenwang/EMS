$(function(){
	var $qqServer = $('.qqserver');
	var $qqserverFold = $('.qqserver_fold');
	var $qqserverUnfold = $('.qqserver_arrow');
	$qqserverFold.mouseover(function(){
		$qqserverFold.hide();
		$qqServer.addClass('unfold');
		
	});
	$qqserverUnfold.click(function(){
		$qqServer.removeClass('unfold');
		$qqserverFold.show();
	});
	//窗体宽度小鱼1024像素时不显示客服QQ
	function resizeQQserver(){
		$qqServer[document.documentElement.clientWidth < 1024 ? 'hide':'show']();
	}
	$(window).bind("load resize",function(){
		resizeQQserver();
	});
});

/*//得到单个文书

*/

function callback(data){
				
																																																																																																																																																																												
              var documentUL = $("#documentUL");
          
              documentUL.html(data)
    
              tipsWindown("","id:"+"china",750,550,"true","","true","china");		
           
		 
}


function monitor(str){
	var image = document.getElementById(str).value;
	
	 if(image!="")
	    {
		 var idCardShow = $("#"+str+"Show"); 
	
		 idCardShow.html("已上传");
		
	     return false;
	    }
	   
}

function confirmTerm(s) {
	
	closeWindown();
	var length = currentFlag.length;
	var str = currentFlag.substring(0, length-4);
	// 通过，传递一种数据标志
	if(s == 1) {
		$("#"+currentFlag).attr("value", "true");
		$("#"+str+"Img").attr("src","images/alert/20150729042348932_easyicon_net_32.png")
	
	}
	// 不通过，传递另一种数据标志
	else if(s == 0){
		$("#"+currentFlag).attr("value", "false");
		$("#"+str+"Img").attr("src","images/alert/cuohao.png")
		}
	
}
