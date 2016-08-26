package cn.future.ssh.dao.impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.future.ssh.dao.DocumentDao;
import cn.future.ssh.domain.Document;

public class DocumentDaoImpl extends HibernateDaoSupport implements DocumentDao {

	@Override
	public void saveIdCard(Document document) {
		this.getHibernateTemplate().save(document);
		
	}

}
