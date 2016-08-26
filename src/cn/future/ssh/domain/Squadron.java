package cn.future.ssh.domain;


import java.io.Serializable;
import java.util.Set;

/**中队*/
public class Squadron implements Serializable {
	/**id*/
	private Long id;
	/**中队名称*/
	private String name;
	/**中队人员*/
	private Set<Personnel> personnels;
	/**中队账号*/
	private String account;
	/**中队密码*/
	private String password;
	/**立案审批表  一对多的关系*/
	private Set<Accreditation>accreditation;
	
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
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Personnel> getPersonnels() {
		return personnels;
	}
	public void setPersonnels(Set<Personnel> personnels) {
		this.personnels = personnels;
	}
	public Set<Accreditation> getAccreditation() {
		return accreditation;
	}
	public void setAccreditation(Set<Accreditation> accreditation) {
		this.accreditation = accreditation;
	}
}
