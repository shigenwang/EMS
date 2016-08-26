package cn.future.ssh.web.action;



import cn.future.ssh.domain.Personnel;
import cn.future.ssh.service.PersonnelService;
import cn.future.ssh.utils.SessionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class LoginAction extends ActionSupport implements ModelDriven<Personnel> {

	/*private Employee employee = new Employee();*/
	
	/*
	@Override
	public Employee getModel() {
		return employee;
	}*/
	
	/*private IEmployeeService employeeService;*/
	
	private Personnel personnel = new Personnel();
	private PersonnelService personnelService;
	private String loaderSign;
	@Override
	public Personnel getModel() {
		
		return personnel;
		
	}


/*	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}
*/

	
	public PersonnelService getPersonnelService() {
		return personnelService;
	}


	public String getLoaderSign() {
		return loaderSign;
	}


	public void setLoaderSign(String loaderSign) {
		this.loaderSign = loaderSign;
	}


	public void setPersonnelService(PersonnelService personnelService) {
		this.personnelService = personnelService;
	}


	/**
	 * 登录
	 * @return
	 */
	public String login(){
		String account = personnel.getAccount();
		
		Personnel personnel = personnelService.findPersonnelByAccount(account);
		if(personnel.getSquadron()==null&&loaderSign.equals("中队")){
			ActionContext.getContext().put("error", "你不是中队人员");
			return "";//错误处理
		}
		SessionContext.setUser(personnel);
		ActionContext.getContext().getSession().put("loaderSign", loaderSign);
		
		return "success";
	}
	
	public String welcome() {
		return "welcome";
	}
	
	public String logout(){
		SessionContext.setUser(null);
		return "login";
	}
}
