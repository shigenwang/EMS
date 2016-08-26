 //删除提示的操作
	       function delConfirm(message){
	    	   if(message==null){
	    		   message="您确定要删除本记录吗？";
	    	   }
	    	   return window.confirm(message);
	       }