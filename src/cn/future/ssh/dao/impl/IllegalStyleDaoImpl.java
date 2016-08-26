package cn.future.ssh.dao.impl;



import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.future.ssh.dao.IllegalStyleDao;
import cn.future.ssh.domain.IllegalStyle;

public class IllegalStyleDaoImpl extends HibernateDaoSupport implements IllegalStyleDao {

	@Override
	public List<IllegalStyle> findAllIllegalStyle() {
		String hql = "from IllegalStyle o";
		List<IllegalStyle> illegalStyles = this.getHibernateTemplate().find(hql);
		return illegalStyles;
		
	}

	@Override
	public IllegalStyle findIllegaStyleById(long illegalstyleId) {
		String hql = "from IllegalStyle o where o.id = ?";
		IllegalStyle illegalStyle = (IllegalStyle) (this.getHibernateTemplate().find(hql, illegalstyleId).get(0));
		return illegalStyle;
	}

	

}
