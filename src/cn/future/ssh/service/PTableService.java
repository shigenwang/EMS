package cn.future.ssh.service;

import java.util.Set;

import org.activiti.engine.task.Task;

import cn.future.ssh.domain.Document;
import cn.future.ssh.domain.PTable;
import cn.future.ssh.web.form.PTableBean;


public interface PTableService {

	void saveSubmitTask(PTableBean pTableBean);

	Task findLastTask();

	PTable findPTableByTaskId(String taskId);

	void submitPTable(PTableBean pTableBean);



	void guide(PTableBean pTableBean);

	String getOutComeByTaskId(String taskId);

	void savePTable(PTable pTable);

	void fillPTable(PTableBean pTableBean, PTable pTable);
	
	public void getFillPTableforQuery(PTableBean pTableBean, PTable pTable);

	public PTable findPTableById(String id);

	String getCurrentSign(String taskId);

	Boolean valiDate(PTableBean pTableBean);

	Set<Document> getDocumentByName(PTableBean pTableBean);

}
