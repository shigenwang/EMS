package cn.future.ssh.service;

import java.util.List;

import cn.future.ssh.domain.Role;

public interface RoleService {

	List<Role> findAllRole();

	List<Role> getByIds(Long[] role_ids);


}
