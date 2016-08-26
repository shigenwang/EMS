package cn.future.ssh.service.impl;

import java.util.List;

import cn.future.ssh.dao.IllegalStyleDao;
import cn.future.ssh.domain.IllegalStyle;
import cn.future.ssh.service.IllegalStyleService;

public class IllegalStyleServiceImpl implements IllegalStyleService {
	private IllegalStyleDao illegalStyleDao;

	
	

	public IllegalStyleDao getIllegalStyleDao() {
		return illegalStyleDao;
	}




	public void setIllegalStyleDao(IllegalStyleDao illegalStyleDao) {
		this.illegalStyleDao = illegalStyleDao;
	}




	@Override
	public List<IllegalStyle> findAllIllegalStyle() {
		List<IllegalStyle> illegalStyles = illegalStyleDao.findAllIllegalStyle();
		return illegalStyles;
	}




	@Override
	public IllegalStyle findIllegaStyleById(long illegalstyleId) {
		IllegalStyle illegalStyle = illegalStyleDao.findIllegaStyleById(illegalstyleId);
		return illegalStyle;
	}



	
}
