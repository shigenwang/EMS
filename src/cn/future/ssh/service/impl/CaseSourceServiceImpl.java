package cn.future.ssh.service.impl;

import java.util.List;

import cn.future.ssh.dao.CaseSourceDao;
import cn.future.ssh.domain.CaseSource;
import cn.future.ssh.service.CaseSourceService;

public class CaseSourceServiceImpl implements CaseSourceService {
	private CaseSourceDao caseSourceDao;

	
	public void setCaseSourceDao(CaseSourceDao caseSourceDao) {
		this.caseSourceDao = caseSourceDao;
	}

	@Override
	public List<CaseSource> getAllCaseSource() {
		List<CaseSource> caseSources = caseSourceDao.findAllCaseSource();
		return caseSources;
	}
	
	
}
