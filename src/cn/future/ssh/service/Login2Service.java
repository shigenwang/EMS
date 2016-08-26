package cn.future.ssh.service;

import java.util.Set;

import cn.future.ssh.domain.Personnel;

public interface Login2Service {
	/**
	 * 根据角色、账号、密码判断数据库里是否存在该记录
	 * @return
	 */
	public Object findByAccountAndPassword(String identity, String account, String password);
    
	/*
	 * 通过登录人和中队标记来判断每个角色所对应的url
	 
	public Set<String> findActionUrl(Personnel personnel, String loaderSign);
*/
}
