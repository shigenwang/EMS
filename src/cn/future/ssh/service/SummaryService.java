package cn.future.ssh.service;

import java.util.List;

import cn.future.ssh.domain.Summary;

public interface SummaryService {

	void saveSummary(Summary summary);

	List<Summary> getAllSummary();

	void updateSummary(Summary summary);

	Summary getSummaryById(Long id);

	void deleteSummary(Summary summary);

}
