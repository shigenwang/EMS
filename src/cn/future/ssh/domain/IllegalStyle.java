package cn.future.ssh.domain;

/*
 * 违法行为类型表
 */
public class IllegalStyle {
	private Long id;
	private String name;  //违法类型的名称
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
	
}
