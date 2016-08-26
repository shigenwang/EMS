package cn.future.ssh.utils;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import cn.future.ssh.domain.Personnel;
import cn.future.ssh.domain.Squadron;

public class SessionContext {

	public static final String GLOBLE_USER_SESSION = "globle_user";
	
/*	public static void setUser(Employee user){
		if(user!=null){
			ServletActionContext.getRequest().getSession().setAttribute(GLOBLE_USER_SESSION, user);
		}else{
			ServletActionContext.getRequest().getSession().removeAttribute(GLOBLE_USER_SESSION);
		}
	}*/
	public static void setUser(Personnel personnel){
		String loaderSign=(String) ActionContext.getContext().getSession().get("loaderSign");
		if(personnel!=null){
			if("一般管理员".equals(loaderSign)){
				personnel.setName("一般管理员");
				ServletActionContext.getRequest().getSession().setAttribute(GLOBLE_USER_SESSION, personnel);
			}else{
				ServletActionContext.getRequest().getSession().setAttribute(GLOBLE_USER_SESSION, personnel);
			}
			
		}else{
			ActionContext.getContext().getSession().remove("loaderSign");
			ServletActionContext.getRequest().getSession().removeAttribute(GLOBLE_USER_SESSION);
		}
	}
	public static Personnel get(){
		return (Personnel) ServletActionContext.getRequest().getSession().getAttribute(GLOBLE_USER_SESSION);
	}
	public static void setSquadron(Squadron squadron) {
		if(squadron!=null){
			ServletActionContext.getRequest().getSession().setAttribute(GLOBLE_USER_SESSION, squadron);
		}else{
			ActionContext.getContext().getSession().remove("loaderSign");
			ServletActionContext.getRequest().getSession().removeAttribute(GLOBLE_USER_SESSION);
		}
		
	}
	
	public static Squadron getSqadron(){
		return (Squadron) ServletActionContext.getRequest().getSession().getAttribute(GLOBLE_USER_SESSION);
	}
	
}
