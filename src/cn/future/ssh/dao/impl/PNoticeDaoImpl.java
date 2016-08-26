package cn.future.ssh.dao.impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.future.ssh.dao.PNoticeDao;
import cn.future.ssh.domain.PNotice;

public class PNoticeDaoImpl extends HibernateDaoSupport implements PNoticeDao {

	

	@Override
	public void updatePNotice(PNotice pNotice) {
		this.getHibernateTemplate().update(pNotice);
		
	}

	@Override
	public void savePNotice(PNotice pNotice) {
		this.getHibernateTemplate().save(pNotice);
		
	}

	@Override
	public PNotice findPNoticeById(Long id) {
		return this.getHibernateTemplate().get(PNotice.class, id);
		
	}

}
