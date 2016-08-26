package cn.future.ssh.dao;

import cn.future.ssh.domain.Accreditation;
import cn.future.ssh.domain.PageBean;
import cn.future.ssh.utils.QueryHelper;


public interface AccreditationDao {

	void saveAccreditation(Accreditation accreditation);

	void updateAccreditation(Accreditation accreditation);

	Accreditation findAccreditationByAccreditationId(Long accreditationId);

	PageBean getPageBean(int pageNum, int pageSize, QueryHelper queryHelper);

	PageBean getAllResult(QueryHelper queryHelper);

	
	
}
