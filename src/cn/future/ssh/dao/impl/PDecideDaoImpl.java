package cn.future.ssh.dao.impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.future.ssh.dao.PDecideDao;
import cn.future.ssh.domain.PDecide;
import cn.future.ssh.domain.PNotice;

public class PDecideDaoImpl extends HibernateDaoSupport implements PDecideDao{

	@Override
	public void updatePDecide(PDecide pDecide) {
		this.getHibernateTemplate().update(pDecide);
		
	}

	@Override
	public void savePDecide(PDecide pDecide) {
		this.getHibernateTemplate().save(pDecide);
		
	}



	@Override
	public PDecide findPDecideById(Long id) {
		return this.getHibernateTemplate().get(PDecide.class, id);
	}

}	
