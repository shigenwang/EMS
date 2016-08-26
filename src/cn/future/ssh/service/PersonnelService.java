package cn.future.ssh.service;

import java.util.List;

import cn.future.ssh.domain.PageBean;
import cn.future.ssh.domain.Personnel;
import cn.future.ssh.domain.Squadron;
import cn.future.ssh.utils.QueryHelper;



public interface PersonnelService {

	Personnel findPersonnelByAccount(String account);

	List<Personnel>  getPersonnelBySquadron(Squadron squadron);

	Personnel findCaptainByMember(Personnel member);

	Personnel findPersonByRole(String roleName);

	List<Personnel> findLegalDepartmentPersonnels();

	List<Personnel> findIndustryCommitteePersonnels();

	List<Personnel> findCaseReviewComPersonnels();

	void savePersonnel(Personnel model);

	Personnel getPersonnelById(Long id);

	void updatePersonnel(Personnel personnel);

	void deletePersonnel(Personnel personnel);

    public Boolean isContainsRoles(String roleName, Personnel personnel);
    
	PageBean getPageBean(int pageNum, int pageSize, QueryHelper queryHelper);

	List<Personnel> findPersonBySquName(String squName);

	List<Personnel> getAllPersonnel();

	Personnel findPersonByRoleAndSquadron(String name, String squadronName);


}
