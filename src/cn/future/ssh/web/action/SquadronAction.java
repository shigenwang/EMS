
package cn.future.ssh.web.action;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import cn.future.ssh.domain.Squadron;
import cn.future.ssh.service.SquadronService;
import cn.future.ssh.utils.ValueContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class SquadronAction extends ActionSupport implements ModelDriven<Squadron>{
    
	private SquadronService squadronService;
    private String oldPassword;//旧密码
    private String newPassword;//新密码
    private String rePassword;//确认密码
    
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getRePassword() {
		return rePassword;
	}
	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}
	public SquadronService getSquadronService() {
		return squadronService;
	}
	public void setSquadronService(SquadronService squadronService) {
		this.squadronService = squadronService;
	}

	private Squadron model=new Squadron();
	@Override
	public Squadron getModel() {
		return model;
	}
    /*
     * 列表
     */
	public String list()throws Exception{
		List<Squadron> squadronList=squadronService.getAllSquadron();
		ValueContext.putValueContext("squadronList", squadronList);
		return "list";
	}
	/*
	 * 添加
	 */
    public String add()throws Exception{
    	
    	//得到中队的列表
    	List<Squadron>squadronList=squadronService.getAllSquadron();
    	for(Squadron squadron:squadronList){
    		if(squadron.getAccount().equals(model.getAccount())){
    			this.addFieldError("errorAccount", "你输入的账号已经注册!");
    			//ActionContext.getContext().getSession().put("errorAccount", "你输入的账号已经注册!");
    			return "saveUI";
    		}
    	}
    	
    	//使用md5加密
    	String md5Password=DigestUtils.md5Hex(model.getPassword());
    	
    	model.setPassword(md5Password);
    	squadronService.saveSquadron(model);
    	
    	return "toList";
    }
    
    /*
     * 添加页面
     */
    public String addUI() throws Exception{
    	
    	return "saveUI";
    }
    /*
     * 修改
     */
    
    public String edit()throws Exception{

    	//1.从数据库中取出要修改的对象
    	Squadron squadron=squadronService.findSquadronById(model.getId());
    	
    	//2.进行修改
    	squadron.setName(model.getName());
    	
    	squadron.setAccount(model.getAccount());
    	
    	//对保存的数据库
    	/*String md5Password=DigestUtils.md5Hex(model.getPassword());
    	squadron.setPassword(md5Password);*/
    	
    	//3.更新到数据库
    	squadronService.saveSquadron(squadron);
    	return "toList";
    }
    
    /*
     * 修改页面
     */
    public String editUI()throws Exception{
    	//准备数据
    	Squadron squadron=squadronService.findSquadronById(model.getId());
    	ValueContext.putValueStack(squadron);
    	
    	return "saveUI";
    }
    
    /*
     * 修改
     */
    
    public String squadronEdit()throws Exception{
    	//1.从数据库中取出要修改的对象
    	Squadron squadron=squadronService.findSquadronById(model.getId());
    	
    	//2.进行修改
    	squadron.setName(model.getName());
    
    	squadron.setAccount(model.getAccount());
    	
    	//对保存的数据库
    	/*String md5Password=DigestUtils.md5Hex(model.getPassword());
    	squadron.setPassword(md5Password);*/
    	
    	//3.更新到数据库
    	squadronService.saveSquadron(squadron);
    	return "editSuccess2";
    }
    
    /*
     * 修改页面
     */
    public String squadronEditUI()throws Exception{
    	//准备数据
    	Squadron squadron=squadronService.findSquadronById(model.getId());
    	ValueContext.putValueStack(squadron);
    	
    	return "squadronEditUI";
    }
    
   /* 
     * 删除
     
    public String delete()throws Exception{
    	Squadron squadron=squadronService.findSquadronById(model.getId());
    	squadronService.deleteSquadronById(squadron);
    	return "toList";
    }*/
    
    /*
     * 修改密码页面
     */
    public String editPasswordUI(){
    	return "editPasswordUI";
    }
    
    /*
     * 修改密码
     */
    
    public String editPassword(){
    	//先得到要修改的人员对象
    	Squadron squadron=squadronService.findSquadronById(model.getId());
    	String psw=squadron.getPassword();//该密码为加密过后的密码
    	String oldPassword2=DigestUtils.md5Hex(oldPassword);//用户输入的老密码
    	
    	String newPassword2=DigestUtils.md5Hex(newPassword);//用户输入的新密码
    	String rePassword2=DigestUtils.md5Hex(rePassword);//用户输入的确认密码
    	
    	if(!psw.equals(oldPassword2)){
    		this.addFieldError("oldPassword", "您输入的旧密码错误!");
    		return "editPasswordUI";
    	}
    	if(!newPassword2.equals(rePassword2)){
    		this.addFieldError("newPassword", "您输入的两次密码不一致!");
    		return "editPasswordUI";
    	}
    	
    	//如果老密码和数据库中存入的一致
    	if((psw.equals(oldPassword2))&&(newPassword2.equals(rePassword2))){
    		squadron.setPassword(newPassword2);
    		squadronService.updateSquadron(squadron);
    	}
    	return "editSuccess";
    }
}
