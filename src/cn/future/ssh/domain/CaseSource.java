package cn.future.ssh.domain;

import java.util.Set;

/*
 * 案情来源表
 */
public class CaseSource {
	private Long id;
	private String name;//名称
	private String description;//描述
	private Set<Accreditation>accreditations;//立案审批表（一对多的对关系）
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<Accreditation> getAccreditations() {
		return accreditations;
	}
	public void setAccreditations(Set<Accreditation> accreditations) {
		this.accreditations = accreditations;
	}
	
}
