package cn.future.ssh.dao;

import cn.future.ssh.domain.PDecide;

public interface PDecideDao {

	

	void updatePDecide(PDecide pDecide);

	void savePDecide(PDecide pDecide);

	PDecide findPDecideById(Long id);

}
