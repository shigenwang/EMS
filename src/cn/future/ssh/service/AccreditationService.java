package cn.future.ssh.service;




import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.activiti.engine.task.Task;

import cn.future.ssh.domain.Accreditation;
import cn.future.ssh.domain.Document;
import cn.future.ssh.domain.PageBean;
import cn.future.ssh.utils.QueryHelper;
import cn.future.ssh.web.form.AccreditationBean;




public interface AccreditationService {

	void submitAccreditation(AccreditationBean accreditationBean);

	Accreditation findAccreditationByTaskId(String taskId);


	
	void saveSubmitTask(AccreditationBean accreditationBean);

	Task findLastTask();

	void updateAccreditation(Accreditation accreditation);

	Accreditation findAccreditationByAccreditationId(Long accreditationId);

	String getOutComeByTaskId(String taskId);

	void guide(AccreditationBean accreditationBean);

	void fillAccreitationBean(AccreditationBean accreditationBean,
			Accreditation accreditation);

	void checkIsTransactor(String taskId);

	String getCurrentSign(String taskId);

	Set<Document> getDocumentByName(AccreditationBean accreditationBean);

	Boolean valiDate(AccreditationBean accreditationBean);

	PageBean getPageBean(int pageNum, int pageSize, QueryHelper queryHelper);

	PageBean getAllResult(QueryHelper queryHelper);
	
}
