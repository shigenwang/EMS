package cn.future.ssh.service;

import org.activiti.engine.task.Task;

import cn.future.ssh.domain.PDecide;
import cn.future.ssh.domain.PNotice;
import cn.future.ssh.web.form.PDecideBean;


public interface PDecideService {

	void submitPDecide(PDecideBean pDecideBean);

	PDecide findPDecideByTaskId(String taskId);

	Task findLastTask();

	void saveSubmitTask(PDecideBean pDecideBean);

	void fillPDecide(PDecideBean pDecideBean, PDecide pDecide);

	public void getFillPDecide(PDecideBean pDecideBean, PDecide pDecide);

	public PDecide findPDecideById(String id);

	PDecide findPNoticeByPNoticeId(Long pDecideId);

	void updatePDecide(PDecide pDecide);
	
	public void getFillPDecideforQuery(PDecideBean pDecideBean, PDecide pDecide);
}
