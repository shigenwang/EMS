package cn.future.ssh.service.impl;

import java.util.List;
import java.util.Set;

import cn.future.ssh.dao.PersonnelDao;
import cn.future.ssh.domain.PageBean;
import cn.future.ssh.domain.Personnel;
import cn.future.ssh.domain.Role;
import cn.future.ssh.domain.Squadron;
import cn.future.ssh.service.PersonnelService;
import cn.future.ssh.utils.QueryHelper;



public class PersonnelServiceImpl implements PersonnelService {
	private PersonnelDao personnelDao;

	public PersonnelDao getPersonnelDao() {
		return personnelDao;
	}

	public void setPersonnelDao(PersonnelDao personnelDao) {
		this.personnelDao = personnelDao;
	}

	@Override
	public Personnel findPersonnelByAccount(String account) {
		Personnel personnel = personnelDao.findPersonnelByAccount(account);
		return personnel;
	}

	@Override
	public List<Personnel> getPersonnelBySquadron(Squadron squadron) {
		List<Personnel> personnels = personnelDao.getPersonnelBySquadron(squadron);
		return personnels;
	}

	@Override
	public Personnel findCaptainByMember(Personnel member) {
		
		return personnelDao.findCaptainByMember(member);
	}

	@Override
	public Personnel findPersonByRole(String roleName) {
		
		return personnelDao.findPersonByRole(roleName);
	}

	@Override
	public List<Personnel> findLegalDepartmentPersonnels() {
		return	personnelDao.findLegalDepartmentPersonnels();
		
	}

	@Override
	public List<Personnel> findIndustryCommitteePersonnels() {
		
		return personnelDao.findIndustryCommitteePersonnels();
	}

	@Override
	public List<Personnel> findCaseReviewComPersonnels() {
		
		return personnelDao.findCaseReviewComPersonnels();
	}
    /*
     * 保存人员
     */
	@Override
	public void savePersonnel(Personnel personnel) {
		
		personnelDao.savePersonnel(personnel);
	}


    @Override
  	public Personnel getPersonnelById(Long id) {
  		// TODO Auto-generated method stub
  		return personnelDao.getPersonnelById(id);
  	}

	@Override
	public void updatePersonnel(Personnel personnel) {
		// TODO Auto-generated method stub
		personnelDao.updatePersonnel(personnel);
	}

	@Override
	public void deletePersonnel(Personnel personnel) {
		personnelDao.deletePersonnel(personnel);
	}

	

	@Override
	public PageBean getPageBean(int pageNum, int pageSize,
			QueryHelper queryHelper) {
		// TODO Auto-generated method stub
		return personnelDao.getPageBean(pageNum,pageSize,queryHelper);
	}

	@Override
	public List<Personnel> findPersonBySquName(String squName) {
		return personnelDao.findPersonBySquName(squName);
	}
	// 判断一个人是否是指定角色
	@Override
	public Boolean isContainsRoles(String roleName, Personnel personnel) {
		Set<Role> roles = personnel.getRoles();
		for (Role role : roles) {
			if(roleName.equals(role.getName())){
				return true;
			}
		}  
		return false;
	}
	
	@Override
	public List<Personnel> getAllPersonnel() {
		// TODO Auto-generated method stub
		return personnelDao.getAllPersonnel();
	}
	//某个中队中只能有个中队长
	@Override
	public Personnel findPersonByRoleAndSquadron(String name, String squadronName) {
		// TODO Auto-generated method stub
		return personnelDao.findPersonByRoleAndSquadron(name,squadronName);
	}
}
