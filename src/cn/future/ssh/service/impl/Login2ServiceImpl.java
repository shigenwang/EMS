package cn.future.ssh.service.impl;

import cn.future.ssh.dao.PersonnelDao;
import cn.future.ssh.dao.SquadronDao;
import cn.future.ssh.service.Login2Service;

public class Login2ServiceImpl implements Login2Service {
	
	private PersonnelDao personnelDao;
	private SquadronDao squadronDao;

	public PersonnelDao getPersonnelDao() {
		return personnelDao;
	}

	public void setPersonnelDao(PersonnelDao personnelDao) {
		this.personnelDao = personnelDao;
	}

	public SquadronDao getSquadronDao() {
		return squadronDao;
	}

	public void setSquadronDao(SquadronDao squadronDao) {
		this.squadronDao = squadronDao;
	}

	/**
	 * @param identity 角色类型
	 * @param account 账号
	 * @param password 密码
	 */
	public Object findByAccountAndPassword(String identity, String account, String password) {
		// 个人
		if("个人" .equals(identity) ) {
			return personnelDao.findByAccountAndPassword(account, password);
		}
		// 中队
		if("中队" .equals(identity)) {
			return squadronDao.findByAccountAndPassword(account, password);
		}
		// 一般管理员
		if("一般管理员" .equals(identity)) {
			return personnelDao.findByAccountAndPassword(account, password);
		}
		//超级管理员
		if("超级管理员" .equals(identity)) {
			
		}
		return false;
	}
	
	/*
	 * 通过登录人和中队标记来判断每个角色所对应的url
	 
	@Override
	public Set<String> findActionUrl(Personnel personnel, String loaderSign) {
     
		return null;
	}*/

}
