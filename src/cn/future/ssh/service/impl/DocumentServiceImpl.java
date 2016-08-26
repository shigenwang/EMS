package cn.future.ssh.service.impl;

import cn.future.ssh.dao.DocumentDao;
import cn.future.ssh.domain.Document;
import cn.future.ssh.service.DocumentService;

public class DocumentServiceImpl implements DocumentService {
	private DocumentDao documentDao;

	
	public void setDocumentDao(DocumentDao documentDao) {
		this.documentDao = documentDao;
	}


	@Override
	public void saveDocument(Document document) {
		documentDao.saveIdCard(document);
		
	}
	
	
}
