package cn.future.ssh.dao;

import java.util.List;

import cn.future.ssh.domain.Summary;

public interface SummaryDao {

	void saveSummary(Summary summary);

	List<Summary> findAllSummary();

	void updateSummary(Summary summary);


	Summary findSummaryById(Long id);

	void deleteSummary(Summary summary);

}
