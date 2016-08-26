package cn.future.ssh.service.impl;

import java.util.List;

import cn.future.ssh.dao.RoleDao;
import cn.future.ssh.domain.Role;
import cn.future.ssh.service.RoleService;

public class RoleServiceImpl implements RoleService{
     private RoleDao roleDao;
     
	public RoleDao getRoleDao() {
		return roleDao;
	}
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	@Override
	public List<Role> findAllRole() {
		// TODO Auto-generated method stub
		return roleDao.getAllRole();
	}

	@Override
	public List<Role> getByIds(Long[] role_ids) {
		// TODO Auto-generated method stub
		return roleDao.getByIds(role_ids);
	}  
}
