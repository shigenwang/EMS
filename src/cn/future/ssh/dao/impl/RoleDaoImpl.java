package cn.future.ssh.dao.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.future.ssh.dao.RoleDao;
import cn.future.ssh.domain.Role;

public class RoleDaoImpl extends HibernateDaoSupport implements RoleDao{
	
     @Override
    public List<Role> getAllRole() {

	  String hql="from Role r order by id desc";
	  return this.getHibernateTemplate().find(hql);
    }

	@Override
	public List<Role> getByIds(Long[] role_ids) {
		
		if(role_ids==null||role_ids.length==0){
			return Collections.EMPTY_LIST;
		}else
		return this.getSessionFactory().getCurrentSession().createQuery("from Role r"+" where id in (:role_ids) order by id asc")//
				.setParameterList("role_ids", role_ids)//
				.list();
   }
}
