package cn.future.ssh.dao.impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.future.ssh.dao.PClosingReportDao;
import cn.future.ssh.domain.PClosingReport;

public class PClosingReportDaoImpl extends HibernateDaoSupport implements PClosingReportDao{
	@Override
	public void updatePClosingReport(PClosingReport pClosingReport) {
		this.getHibernateTemplate().update(pClosingReport);
		
	}

	@Override
	public void savePClosingReport(PClosingReport pClosingReport) {
		this.getHibernateTemplate().save(pClosingReport);
		
	}



	@Override
	public PClosingReport findPClosingReportById(Long id) {
		return this.getHibernateTemplate().get(PClosingReport.class, id);
	}

}
