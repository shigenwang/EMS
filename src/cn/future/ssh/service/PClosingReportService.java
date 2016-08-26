package cn.future.ssh.service;

import java.util.Set;

import org.activiti.engine.task.Task;

import cn.future.ssh.domain.Document;
import cn.future.ssh.domain.PClosingReport;
import cn.future.ssh.domain.PDecide;
import cn.future.ssh.web.form.PClosingReportBean;
import cn.future.ssh.web.form.PDecideBean;


public interface PClosingReportService {

	void saveSubmitTask(PClosingReportBean pClosingReportBean);

	Task findLastTask();

	PClosingReport findPClosingReportByTaskId(String taskId);

	void submitPClosingReport(PClosingReportBean pClosingReportBean);

	void guide(PClosingReportBean pClosingReportBean);

	String getOutComeByTaskId(String taskId);
	
	public void getFillPClosingReport(PClosingReportBean pClosingReportBean, PClosingReport pClosingReport);

	void fillPClosingReport(PClosingReportBean pClosingReportBean,
			PClosingReport pClosingReport);


	String getCurrentSign(String taskId);


	public PClosingReport findPClosingReportById(String id);

	Boolean valiDate(PClosingReportBean pClosingReportBean);

	Set<Document> getDocumentByName(PClosingReportBean pClosingReportBean);

	public void getFillPClosingforQuery(PClosingReportBean pClosingReportBean, PClosingReport pClosingReport);
}
