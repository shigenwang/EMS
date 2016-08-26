package cn.future.ssh.dao;

import java.util.List;

import cn.future.ssh.domain.PageBean;
import cn.future.ssh.domain.Personnel;
import cn.future.ssh.domain.Squadron;
import cn.future.ssh.utils.QueryHelper;

public interface PersonnelDao {

	Personnel findPersonnelByAccount(String account);

	List<Personnel> getPersonnelBySquadron(Squadron squadron);

	Personnel findCaptainByMember(Personnel member);

	Personnel findPersonByRole(String roleName);

	List<Personnel> findLegalDepartmentPersonnels();

	List<Personnel> findIndustryCommitteePersonnels();

	List<Personnel> findCaseReviewComPersonnels();

	void savePersonnel(Personnel personnel);


	Personnel getPersonnelById(Long id);

	void updatePersonnel(Personnel personnel);

	void deletePersonnel(Personnel personnel);


	public Personnel findByAccountAndPassword(String account, String password);


	PageBean getPageBean(int pageNum, int pageSize, QueryHelper queryHelper);

	List<Personnel> findPersonBySquName(String squName);

	List<Personnel> getAllPersonnel();


	Personnel findPersonByRoleAndSquadron(String name, String squadronName);
	
}
