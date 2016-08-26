package cn.future.ssh.service;

import java.util.List;

import cn.future.ssh.domain.Squadron;

public interface SquadronService {

	void saveSquadron(Squadron model);


	Squadron findSquadronById(Long id);


	List<Squadron> getAllSquadron();


	/*void deleteSquadronById(Squadron squadron);*/


	Squadron getById(Long squadron_id);


	void updateSquadron(Squadron squadron);



}
