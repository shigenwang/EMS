package cn.future.ssh.service;




import org.activiti.engine.task.Task;

import cn.future.ssh.domain.PNotice;
import cn.future.ssh.web.form.PNoticeBean;



public interface PNoticeService {

	void submitPNotice(PNoticeBean pNoticeBean);

	PNotice findPNoticeByTaskId(String taskId);

	void saveSubmitTask(PNoticeBean pNoticeBean);
	
	// 根据PNotice 转换成报表 PNoticeBean
	public void getFillPNotice(PNoticeBean pNoticeBean, PNotice pNotice);
	// 根据PNotice id进行查找
	public PNotice getPNoticeById(String id);
	
	Task findLastTask();

	PNotice findPNoticeByPNoticeId(Long pNoticeId);

	void updatePNotice(PNotice pNotice);
	
	public void getFillPNoforQuery(PNoticeBean pNoticeBean, PNotice pNotice);
}
