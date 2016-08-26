package cn.future.ssh.dao;

import cn.future.ssh.domain.PNotice;

public interface PNoticeDao {

	

	void updatePNotice(PNotice pNotice);

	void savePNotice(PNotice pNotice);

	PNotice findPNoticeById(Long accreditation);

}
