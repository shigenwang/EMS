package cn.future.ssh.service.impl;

import cn.future.ssh.dao.PDFDocumentDao;
import cn.future.ssh.domain.PDFDocument;
import cn.future.ssh.service.PDFDocumentService;

public class PDFDocumentServiceImpl implements PDFDocumentService{
	private PDFDocumentDao pdfDocumentDao;
	
	
	public void setPdfDocumentDao(PDFDocumentDao pdfDocumentDao) {
		this.pdfDocumentDao = pdfDocumentDao;
	}


	@Override
	public void save(PDFDocument pdfDocument) {
		pdfDocumentDao.save(pdfDocument);
		
	}

}
