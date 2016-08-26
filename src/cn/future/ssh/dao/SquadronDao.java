package cn.future.ssh.dao;

import java.util.List;

import cn.future.ssh.domain.Squadron;

public interface SquadronDao {

	void saveSquadron(Squadron squadron);


	Squadron getSquadronById(Long id);


	List<Squadron> findAllSquadron();


/*	void deleteSquadronById(Squadron squadron);*/


	Squadron getById(Long id);


	public Squadron findByAccountAndPassword(String account, String password);


	void updateSquadron(Squadron squadron);

}
