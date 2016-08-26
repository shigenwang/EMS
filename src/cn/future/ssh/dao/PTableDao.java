package cn.future.ssh.dao;

import cn.future.ssh.domain.PTable;

public interface PTableDao {

	PTable findPTableById(long parseLong);

	void updatePTable(PTable pTable);

	void savePTable(PTable pTable);

}
