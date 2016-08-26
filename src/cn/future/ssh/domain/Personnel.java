package cn.future.ssh.domain;


import java.io.Serializable;
import java.util.Set;

/**人员表*/
public class Personnel implements Serializable {
	/**id*/
	private Long Id;
	/**姓名*/
	private String name;
	private String gender;//性别
	/**角色*/
	private Set<Role> roles;//多对多的关系
	/**所属部门*/
	private Squadron squadron;//一对一的关系
	/**立案审批表    一对多的关系*/
	private Set<Accreditation> accreditation;
	/**个人账号*/
	private String account;
	/**密码*/
	private String password;
	private String telephone;//电话
	private String flag;//标记
	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Squadron getSquadron() {
		return squadron;
	}
	public void setSquadron(Squadron squadron) {
		this.squadron = squadron;
	}
	public Set<Accreditation> getAccreditation() {
		return accreditation;
	}
	public void setAccreditation(Set<Accreditation> accreditation) {
		this.accreditation = accreditation;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Id == null) ? 0 : Id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Personnel other = (Personnel) obj;
		if (Id == null) {
			if (other.Id != null)
				return false;
		} else if (!Id.equals(other.Id))
			return false;
		return true;
	}
}
