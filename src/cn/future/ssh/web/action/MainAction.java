package cn.future.ssh.web.action;

import com.opensymphony.xwork2.ActionSupport;

public class MainAction extends ActionSupport {
   
	public String index() throws Exception{
                
		return "index";		
	}
	
	public String top() throws Exception{
		return "top";
	}
	
	public String left() throws Exception{
		return "left";
	}
	
	public String right() throws Exception{
		return "right";
	}
}
