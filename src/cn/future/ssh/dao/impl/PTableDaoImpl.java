package cn.future.ssh.dao.impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.future.ssh.dao.PTableDao;
import cn.future.ssh.domain.PNotice;
import cn.future.ssh.domain.PTable;

public class PTableDaoImpl extends HibernateDaoSupport implements PTableDao {

	@Override
	public PTable findPTableById(long id) {
		return this.getHibernateTemplate().get(PTable.class, id);
	}

	@Override
	public void updatePTable(PTable pTable) {
		this.getHibernateTemplate().update(pTable);
		
	}

	@Override
	public void savePTable(PTable pTable) {
		this.getHibernateTemplate().save(pTable);
		
	}
	
}
