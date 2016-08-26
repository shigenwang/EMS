package cn.future.ssh.dao.impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.future.ssh.dao.PDFDocumentDao;
import cn.future.ssh.domain.PDFDocument;

public class PDFDocumentDaoImpl extends HibernateDaoSupport implements PDFDocumentDao {

	@Override
	public void save(PDFDocument pdfDocument) {
		this.getHibernateTemplate().save(pdfDocument);
		
	}

}
