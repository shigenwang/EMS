package cn.future.ssh.dao;

import cn.future.ssh.domain.PClosingReport;

public interface PClosingReportDao {

	PClosingReport findPClosingReportById(Long id);

	void updatePClosingReport(PClosingReport pClosingReport);

	void savePClosingReport(PClosingReport pClosingReport);

}
