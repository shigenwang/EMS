package cn.future.ssh.web.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;

import cn.future.ssh.domain.PageBean;
import cn.future.ssh.domain.Personnel;
import cn.future.ssh.domain.Role;
import cn.future.ssh.domain.Squadron;
import cn.future.ssh.service.PersonnelService;
import cn.future.ssh.service.RoleService;
import cn.future.ssh.service.SquadronService;
import cn.future.ssh.utils.QueryHelper;
import cn.future.ssh.utils.ValueContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class PersonnelAction extends ActionSupport implements ModelDriven<Personnel>{
    
	private PersonnelService personnelService;
	private RoleService roleService;
	private SquadronService squadronService;
	private String squadronName;//中队的名称
    private String roleName;//角色的名称
    private Long roleId;
    private int pageNum=1;
    private int pageSize=10;
    
    private String oldPassword;//旧密码
    private String newPassword;//新密码
    private String rePassword;//确认密码
    
	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
    
	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getSquadronName() {
		return squadronName;
	}

	public void setSquadronName(String squadronName) {
		this.squadronName = squadronName;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public SquadronService getSquadronService() {
		return squadronService;
	}

	public void setSquadronService(SquadronService squadronService) {
		this.squadronService = squadronService;
	}

	private Long squadron_id;
	private Long[] role_ids;
	
	public Long getSquadron_id() {
		return squadron_id;
	}

	public void setSquadron_id(Long squadron_id) {
		this.squadron_id = squadron_id;
	}

	public Long[] getRole_ids() {
		return role_ids;
	}

	public void setRole_ids(Long[] role_ids) {
		this.role_ids = role_ids;
	}

	public PersonnelService getPersonnelService() {
		return personnelService;
	}

	public void setPersonnelService(PersonnelService personnelService) {
		this.personnelService = personnelService;
	}
	
	
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

	private Personnel model=new Personnel();
	@Override
	public Personnel getModel() {
		
		return model;
	}
    
	/*
     * 列表
     */
	public String list()throws Exception{
		//准备数据  Role
    	List<Role> roleList=roleService.findAllRole();
    	ValueContext.putValueContext("roleList", roleList);
    	
    	//准备数据 Squadron
    	List<Squadron> squadronList=squadronService.getAllSquadron();
    	ValueContext.putValueContext("squadronList", squadronList);
    	
		//准备用户分页的信息
		QueryHelper queryHelper=new QueryHelper(Personnel.class,"p");
		// 必须是没删除的用户
		queryHelper.addCondition("p.flag=?", "T");
		if((squadronName!=null)&&(!"".equals(squadronName.trim()))){
			queryHelper.addCondition("p.squadron.name=?", squadronName.trim());
		}
		if((roleName!=null)&&(!"".equals(roleName.trim()))){
			queryHelper.addCondition("r.name =?", roleName.trim());
		}
		if((model.getName()!=null)&&(!"".equals(model.getName().trim()))){
			queryHelper.addCondition("p.name=?", (model.getName()).trim());
			
		}
		
		//得到pageBean对象，并将此放置到栈中
		PageBean pageBean=personnelService.getPageBean(pageNum,pageSize,queryHelper);
		ValueContext.putValueStack(pageBean);
		//ActionContext.getContext().getValueStack().push(pageBean);
		return "list";
	}
	/*
	 * 添加
	 */
    public String add()throws Exception{
    	
    	//得到人员列表
    	List<Personnel>personnelList=personnelService.getAllPersonnel();
    		
    	for(Personnel p:personnelList){
    		if(model.getAccount().equals(p.getAccount())){
    			this.addFieldError("errorAccount", "你输入的账号已经注册!");
    			
    			//准备数据  Role
    	    	List<Role> roleList=roleService.findAllRole();
    	    
    	    	ValueContext.putValueContext("roleList", roleList);
    	    	
    	    	//准备数据 Squadron
    	    	List<Squadron> squadronList=squadronService.getAllSquadron();
    	    	ValueContext.putValueContext("squadronList", squadronList);
    			return "saveUI";
    		}
    	}
    	
    	//设置关联的角色
    	List<Role>roleList=roleService.getByIds(role_ids);
    	Squadron squadron=squadronService.getById(squadron_id);
    	String squadronName2="";
    	if(squadron==null){
    		squadronName2="";
    	}else{
    		squadronName2=squadron.getName();
    	}
    	
    	Personnel personnel=null;
		for(Role r:roleList){
			if("中队长".equals(r.getName())){
				   personnel= personnelService.findPersonByRoleAndSquadron(r.getName(),squadronName2);
				   if(personnel!=null){
						this.addFieldError("loader1", "该中队已存在中队长,您可将原中队长的角色进行修改或删除原中队长!");
						//准备数据  Role
				    	List<Role> roleList0=roleService.findAllRole();
				    	
				    	List<Role>roleList2=new ArrayList<>();
				    	for(Role role:roleList0){
				    		if((!"一般管理员".equals(role.getName()))&&(!"超级管理员".equals(role.getName()))){
				    			roleList2.add(role);
				    		}
				    	}
				    	ValueContext.putValueContext("roleList", roleList2);
				    	
				    	//准备数据 Squadron
				    	List<Squadron> squadronList=squadronService.getAllSquadron();
				    	ValueContext.putValueContext("squadronList", squadronList);
						return "saveUI";
					}
			   }
			
			  if("大队长".equals(r.getName())){
				   personnel= personnelService.findPersonByRole(r.getName());
				   if(personnel!=null){
						this.addFieldError("loader2", "大队长只能为一个，您可将原大队长的角色进行修改或删除原大队长!");
						//准备数据  Role
				    	List<Role> roleList0=roleService.findAllRole();
				    	
				    	List<Role>roleList2=new ArrayList<>();
				    	for(Role role:roleList0){
				    		if((!"一般管理员".equals(role.getName()))&&(!"超级管理员".equals(role.getName()))){
				    			roleList2.add(role);
				    		}
				    	}
				    	ValueContext.putValueContext("roleList", roleList2);
				    	
				    	//准备数据 Squadron
				    	List<Squadron> squadronList=squadronService.getAllSquadron();
				    	ValueContext.putValueContext("squadronList", squadronList);
						return "saveUI";
					}
			   }
			
		  
		   if("部门主要领导".equals(r.getName())){
			   personnel= personnelService.findPersonByRole(r.getName());
			   if(personnel!=null){
					this.addFieldError("loader3", "部门主要领导只能为一个,您可将原部门主要领导的角色进行修改或删除原部门主要领导!");
					//准备数据  Role
			    	List<Role> roleList0=roleService.findAllRole();
			    	
			    	List<Role>roleList2=new ArrayList<>();
			    	for(Role role:roleList0){
			    		if((!"一般管理员".equals(role.getName()))&&(!"超级管理员".equals(role.getName()))){
			    			roleList2.add(role);
			    		}
			    	}
			    	ValueContext.putValueContext("roleList", roleList2);
			    	
			    	//准备数据 Squadron
			    	List<Squadron> squadronList=squadronService.getAllSquadron();
			    	ValueContext.putValueContext("squadronList", squadronList);
					return "saveUI";
				}
		   }
		}
		
		model.setRoles(new HashSet<>(roleList));
    	
    	//设置关联的中队
    	model.setSquadron(squadron);
    	//设置人员的标记
    	//设置密码，使用md5加密
    	String md5Password=DigestUtils.md5Hex(model.getPassword());
    	model.setPassword(md5Password);
    	
    	model.setFlag("T");
        
    	personnelService.savePersonnel(model);
    	return "toList";
    }
    
    /*
     * 添加页面
     */
    public String addUI() throws Exception{
    	//准备数据  Role
    	List<Role> roleList=roleService.findAllRole();
    	
    	List<Role>roleList2=new ArrayList<>();
    	for(Role role:roleList){
    		if((!"一般管理员".equals(role.getName()))&&(!"超级管理员".equals(role.getName()))){
    			roleList2.add(role);
    		}
    	}
    	ValueContext.putValueContext("roleList", roleList2);
    	
    	//准备数据 Squadron
    	List<Squadron> squadronList=squadronService.getAllSquadron();
    	ValueContext.putValueContext("squadronList", squadronList);
    	return "saveUI";
    }
    /*
     * 修改
     */
    
    public String edit()throws Exception{
    	
    	//1.从数据库中取出要修改的对象
    	Personnel personnel=personnelService.getPersonnelById(model.getId());
    	Set<Role> rlist= personnel.getRoles();
    	//2.进行修改
    	personnel.setAccount(model.getAccount());
    	personnel.setGender(model.getGender());
    	personnel.setName(model.getName());
    	
    	personnel.setTelephone(model.getTelephone());
    	
    	//设置关联的角色
    	List<Role>roleList=roleService.getByIds(role_ids);
    	Squadron squadron=squadronService.getById(squadron_id);
    	
    	String squadonName3="";
    	if(personnel.getSquadron()==null){
    		squadonName3="";
    	}else{
    		squadonName3=personnel.getSquadron().getName();
    	}
    	
    	String squadronName2="";
    	if(squadron==null){
    		squadronName2="";
    	}else{
    		squadronName2=squadron.getName();
    	}
    	Personnel personnel2=null;
    	Personnel personnel3=null;
    	@SuppressWarnings("unused")
		String roleName2="";
		for(Role r:roleList){
			//主要用于判断要修改的对象是否有唯一的角色，如有，不再进行下面的错误判断
			Iterator<Role> rlist2=rlist.iterator();
			while(rlist2.hasNext()){
				Role role=rlist2.next();
				roleName2=role.getName();
			}
			
			//判断修改的是不是角色是中队长，如果是，则根据这个角色和所选的中队进行查询，看数据库中是否已经存在。
			  if("中队长".equals(r.getName())){
				   personnel2= personnelService.findPersonByRoleAndSquadron(r.getName(),squadronName2);
				  
				   //如果发现没有查到，并且对中队已经进行的了修改，那么再次查询数据库，看看有没有，如果有则进行修改所属中队
				   if(personnel2!=null && !squadonName3.equals(squadronName2)){
					   personnel3= personnelService.findPersonByRoleAndSquadron(r.getName(),squadronName2);
				    	if(personnel3==null){
				    		     personnel.setSquadron(squadron);
						    	//3.更新到数据库
						    	personnelService.updatePersonnel(personnel);
						    	return "toList";
				    	}else{
				    		//否则就是不为null,那么提示已经有了
				    		this.addFieldError("loader1", "该中队的中队长只能为一个,您可将原中队长的角色进行修改或删除!");
							//准备数据  Role
					    	List<Role> roleList0=roleService.findAllRole();
					    	
					    	List<Role>roleList2=new ArrayList<>();
					    	for(Role role:roleList0){
					    		if((!"一般管理员".equals(role.getName()))&&(!"超级管理员".equals(role.getName()))){
					    			roleList2.add(role);
					    		}
					    	}
					    	ValueContext.putValueContext("roleList", roleList2);
					    	
					    	//准备数据 Squadron
					    	List<Squadron> squadronList=squadronService.getAllSquadron();
					    	ValueContext.putValueContext("squadronList", squadronList);
							return "saveUI";
				    	}
					  
					 }
			    }
			  
			  //如果修改的角色是大队长，那么去数据库中进行查找，看看有没有，如果有就会提示已经注册，反之加进去
			  if("大队长".equals(r.getName())){
				   if(!roleName2.equals(r.getName())){
					   personnel3= personnelService.findPersonByRole(r.getName());
				    	if(personnel3==null){
				    		   //修改角色
				    		   personnel.setRoles(new HashSet<>(roleList));
						    	//3.更新到数据库
						    	personnelService.updatePersonnel(personnel);
						    	return "toList";
				    	}else if(personnel3!=null){
				    		this.addFieldError("loader2", "大队长只能为一个,您可将原大队长的角色进行修改或删除!");
							//准备数据  Role
					    	List<Role> roleList0=roleService.findAllRole();
					    	
					    	List<Role>roleList2=new ArrayList<>();
					    	for(Role role:roleList0){
					    		if((!"一般管理员".equals(role.getName()))&&(!"超级管理员".equals(role.getName()))){
					    			roleList2.add(role);
					    		}
					    	}
					    	ValueContext.putValueContext("roleList", roleList2);
					    	
					    	//准备数据 Squadron
					    	List<Squadron> squadronList=squadronService.getAllSquadron();
					    	ValueContext.putValueContext("squadronList", squadronList);
							return "saveUI";
				    	}
				    	
					}
			   }
			  //如果修改的角色是部门主要领导，那么去数据库中进行查找，看看有没有，如果有就会提示已经注册，反之加进去
			  if("部门主要领导".equals(r.getName())){
				   if(!roleName2.equals(r.getName())){
					   personnel3= personnelService.findPersonByRole(r.getName());
				    	if(personnel3==null){
				    		   //修改角色
				    		   personnel.setRoles(new HashSet<>(roleList));
						    	//3.更新到数据库
						    	personnelService.updatePersonnel(personnel);
						    	return "toList";
				    	}else if(personnel3!=null){
				    		this.addFieldError("loader3", "部门主要领导只能为一个,您可将原部门主要领导的角色进行修改或删除!");
							//准备数据  Role
					    	List<Role> roleList0=roleService.findAllRole();
					    	
					    	List<Role>roleList2=new ArrayList<>();
					    	for(Role role:roleList0){
					    		if((!"一般管理员".equals(role.getName()))&&(!"超级管理员".equals(role.getName()))){
					    			roleList2.add(role);
					    		}
					    	}
					    	ValueContext.putValueContext("roleList", roleList2);
					    	
					    	//准备数据 Squadron
					    	List<Squadron> squadronList=squadronService.getAllSquadron();
					    	ValueContext.putValueContext("squadronList", squadronList);
							return "saveUI";
				    	}
				    	
					}
			   }
		   }
		//修改角色
		personnel.setRoles(new HashSet<>(roleList));
		
		if(squadron!=null){
			//设置关联的中队
	    	personnel.setSquadron(squadronService.getById(squadron_id));
		}else{
			personnel.setSquadron(null);
		}
		
		//3.更新到数据库
    	personnelService.updatePersonnel(personnel);
    	return "toList";
    }
    
    /*
     * 修改页面
     */
    public String editUI()throws Exception{
    	//准备Personnel
    	Personnel personnel=personnelService.getPersonnelById(model.getId());
    	ValueContext.putValueStack(personnel);
    	
      	//准备数据  Role
    	List<Role> roleList=roleService.findAllRole();
    	
    	//因为在角色中包含一般管理员和超级管理员，但是在页面中我们不用显示他们，所以对查出来的 角色进行筛选
    	List<Role>roleList2=new ArrayList<>();
    	for(Role role:roleList){
    		if((!"一般管理员".equals(role.getName()))&&(!"超级管理员".equals(role.getName()))){
    			roleList2.add(role);
    		}
    	}
    	ValueContext.putValueContext("roleList", roleList2);
    	
    	//准备数据 Squadron
    	List<Squadron> squadronList=squadronService.getAllSquadron();
    	ValueContext.putValueContext("squadronList", squadronList);
    	
    	
    	//准备回显已经选中的role和Squadron
    	if(personnel.getSquadron()!=null){
    		squadron_id=personnel.getSquadron().getId();
    	}
    	
    	if(personnel.getRoles()!=null){
    		role_ids=new Long[personnel.getRoles().size()];
    		int index=0;
    		for(Role role:personnel.getRoles()){
    			role_ids[index++]=role.getId();
    		}
    	}
    	return "saveUI";
    }
    
    /*
     * 修改页面
     */
    public String personnelEditUI()throws Exception{
    	
      	//准备Personnel
    	Personnel personnel=personnelService.getPersonnelById(model.getId());
    	ValueContext.putValueStack(personnel);
    	return "personnelEditUI";
    }
    /*
     * 修改
     */
    
    public String personnelEdit()throws Exception{
    			
    	//1.从数据库中取出要修改的对象
    	Personnel personnel=personnelService.getPersonnelById(model.getId());
    	//2.进行修改
    	personnel.setAccount(model.getAccount());
    	personnel.setGender(model.getGender());
    	personnel.setName(model.getName());
    	
    	personnel.setTelephone(model.getTelephone());
    	//3.更新到数据库
    	personnelService.updatePersonnel(personnel);
    	return "editSuccess2";
    }
    
    /*
     * 删除
     */
    public String delete()throws Exception{
    	Personnel personnel=personnelService.getPersonnelById(model.getId());
    	personnel.setFlag("F");
    	personnelService.deletePersonnel(personnel);
    	return "toList";
    }
    
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
    	Personnel personnel=personnelService.getPersonnelById(model.getId());
    	String psw=personnel.getPassword();//该密码为加密过后的密码
    	String oldPassword2=DigestUtils.md5Hex(oldPassword);//用户输入的老密码
    	
    	String newPassword2=DigestUtils.md5Hex(newPassword);//用户输入的新密码
    	String rePassword2=DigestUtils.md5Hex(rePassword);//用户输入的确认密码
    	if(!psw.equals(oldPassword2)){
    		this.addFieldError("oldPassword", "您输入的旧密码错误!");
    		return "editPasswordUI";
    	}
    	if(!newPassword2.equals(rePassword2)){
    		this.addFieldError("newPassword", "您输入的两次密码不一致");
    		return "editPasswordUI";
    	}
    	//如果老密码和数据库中存入的一致
    	if((psw.equals(oldPassword2))&&(newPassword2.equals(rePassword2))){
    		personnel.setPassword(newPassword2);
    		personnelService.updatePersonnel(personnel);
    	}
    	return "editSuccess";
    }

	 /*
     * 初始化密码
     * 
     */
    public String  initPassworid()throws Exception{
    	//取出对象
    	Personnel personnel=personnelService.getPersonnelById(model.getId());
    	String psw=DigestUtils.md5Hex("123456");
    	personnel.setPassword(psw);
    	personnelService.updatePersonnel(personnel);
    	return "toList";
    }
}
