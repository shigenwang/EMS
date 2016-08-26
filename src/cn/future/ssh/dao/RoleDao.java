package cn.future.ssh.dao;

import java.util.List;

import cn.future.ssh.domain.Role;

public interface RoleDao {

	List<Role> getAllRole();

	List<Role> getByIds(Long[] role_ids);

}
