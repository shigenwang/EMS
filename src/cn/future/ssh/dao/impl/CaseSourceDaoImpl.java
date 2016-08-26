package cn.future.ssh.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.future.ssh.dao.CaseSourceDao;
import cn.future.ssh.domain.CaseSource;

public class CaseSourceDaoImpl extends HibernateDaoSupport implements
		CaseSourceDao {

	@Override
	public List<CaseSource> findAllCaseSource() {
		String hql = "from CaseSource o";
		List<CaseSource> caseSources = this.getHibernateTemplate().find(hql);
		return caseSources;
	}
	
}
