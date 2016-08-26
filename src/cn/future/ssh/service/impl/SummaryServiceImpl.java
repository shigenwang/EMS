package cn.future.ssh.service.impl;

import java.util.List;

import cn.future.ssh.dao.SummaryDao;
import cn.future.ssh.domain.Summary;
import cn.future.ssh.service.SummaryService;

public class SummaryServiceImpl  implements SummaryService {
 
	private SummaryDao summaryDao;
	
	public SummaryDao getSummaryDao() {
		return summaryDao;
	}

	public void setSummaryDao(SummaryDao summaryDao) {
		this.summaryDao = summaryDao;
	}

	@Override
	public void saveSummary(Summary summary) {
		summaryDao.saveSummary(summary);
	}

	@Override
	public List<Summary> getAllSummary() {
		List<Summary> summarys = summaryDao.findAllSummary();
		return summarys;
		
	}

	@Override
	public void updateSummary(Summary summary) {
		summaryDao.updateSummary(summary);
		
	}

	@Override
	public Summary getSummaryById(Long id) {
		// TODO Auto-generated method stub
		return summaryDao.findSummaryById(id);
	}
	@Override
	public void deleteSummary(Summary summary) {
		// TODO Auto-generated method stub
		summaryDao.deleteSummary(summary);
	}
}
