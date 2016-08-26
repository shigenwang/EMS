package cn.future.ssh.service.impl;

import java.util.List;

import cn.future.ssh.dao.SquadronDao;
import cn.future.ssh.domain.Squadron;
import cn.future.ssh.service.SquadronService;

public class SquadronServiceImpl  implements SquadronService {
    private SquadronDao squadronDao;
    
	public SquadronDao getSquadronDao() {
		return squadronDao;
	}

	public void setSquadronDao(SquadronDao squadronDao) {
		this.squadronDao = squadronDao;
	}

	@Override
	public void saveSquadron(Squadron squadron) {

		squadronDao.saveSquadron(squadron);
		
	}

	@Override
	public Squadron findSquadronById(Long id) {
		return squadronDao.getSquadronById(id);
	}

	@Override
	public List<Squadron> getAllSquadron() {
		return squadronDao.findAllSquadron();
	}


	/*@Override
	public void deleteSquadronById(Squadron squadron) {
		// TODO Auto-generated method stub
		squadronDao.deleteSquadronById(squadron);
	}*/

	@Override
	public Squadron getById(Long id) {
		// TODO Auto-generated method stub
		return squadronDao.getById(id);
	}

	@Override
	public void updateSquadron(Squadron squadron) {
		squadronDao.updateSquadron(squadron);
		
	}

}
