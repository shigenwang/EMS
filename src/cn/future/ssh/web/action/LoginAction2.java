package cn.future.ssh.web.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.future.ssh.domain.Personnel;
import cn.future.ssh.domain.Role;
import cn.future.ssh.domain.Squadron;
import cn.future.ssh.service.Login2Service;
import cn.future.ssh.service.PersonnelService;
import cn.future.ssh.utils.SessionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 登录Action 分角色登录：个人、中队、一般管理员、超级管理员
 * 
 * @author HZC
 *
 */
@SuppressWarnings("serial")
public class LoginAction2 extends ActionSupport{

	/** 身份选择信息 */
	private String identity;//登录人的类型
	private PersonnelService personnelService;
	
	private String account;
	private String password;
	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	/*
	 * 验证码
	 */
	private String checkCode;
	private Login2Service login2Service;
	
	public Login2Service getLogin2Service() {
		return login2Service;
	}

	public void setLogin2Service(Login2Service login2Service) {
		this.login2Service = login2Service;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
	

	public PersonnelService getPersonnelService() {
		return personnelService;
	}

	public void setPersonnelService(PersonnelService personnelService) {
		this.personnelService = personnelService;
	}


	/**
	 * 登录方法
	 * @return
	 * @throws Exception
	 */
	public String login() throws Exception {
		//从session 中获取验证码
		 HttpSession session1 = ServletActionContext.getRequest().getSession();
	     String checkCode2 = (String)session1.getAttribute("checkCode");
	     
		String result="0";
	
		Object object =login2Service.findByAccountAndPassword(identity, account, password);
		
		//Map<String, Object> session = ActionContext.getContext().getSession();
		if (object != null && (checkCode!=""&&checkCode.equalsIgnoreCase(checkCode2))) {
			if (identity .equals("个人") ) { // 个人
				Personnel personnel = (Personnel) object;
				  for(Role role:personnel.getRoles()){
				     if(role.getName().equals("一般管理员")){
						result="4";
					  }	else{
						    ActionContext.getContext().getSession().put("loaderSign", identity);
							SessionContext.setUser(personnel);
							result="1";
						}
				}
				
			} else if (identity .equals("中队")) { // 中队
				
				Squadron squadron = (Squadron) object;
				//因为以中队身份登录后，需要修改个人的信息，因此必须获得该中队的id
				//我们将中队的id存放在session中
				ServletActionContext.getRequest().getSession().setAttribute("squadornSign", squadron);
				//ActionContext.getContext().getSession().put("squadornSign", squadron);
				Personnel personnel =new Personnel();
				personnel.setName(squadron.getName());
				personnel.setSquadron(squadron);
				ActionContext.getContext().getSession().put("loaderSign", identity);
				SessionContext.setUser(personnel);
				result="1";
				
			} else if (identity .equals("一般管理员") ) { // 一般管理员
				Personnel personnel = (Personnel) object;
				for(Role role:personnel.getRoles()){
					if(!role.getName().equals("一般管理员")){
						result="4";
					}else{
						ActionContext.getContext().getSession().put("loaderSign", identity);
						SessionContext.setUser(personnel);
						result="1";	
					}
				}
				
			} else if(identity .equals("超级管理员") ) { // 超级管理员
				SessionContext.setUser(null);
				ActionContext.getContext().getSession().put("loaderSign", identity);
				result="1";
			} 
			
		} else if((checkCode==""||!checkCode.equalsIgnoreCase(checkCode2))){
			result="2";
	   	} else if((object == null)&&(checkCode!=""||checkCode.equalsIgnoreCase(checkCode2))){
			result="3";
		}
	/*	
		//用于在页面中显示不同的标签
		String loaderSign=(String) ActionContext.getContext().getSession().get("loaderSign");
		Set<String>actionUrlSet=login2Service.findActionUrl(SessionContext.get(),loaderSign);*/
		//向客户端传递数据
	       ServletActionContext.getResponse().getWriter().print(result);
	       return Action.NONE;
 }
		
	

	public String logout() throws Exception {
		SessionContext.setUser(null);
		return "loginUI";
	}
}
