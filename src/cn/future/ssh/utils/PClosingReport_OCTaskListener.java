package cn.future.ssh.utils;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
/**
 * 流程中的任务节点执行完毕后执行此类
 * */
public class PClosingReport_OCTaskListener implements TaskListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void notify(DelegateTask delegateTask) {
		
		WebApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
		TaskService taskService = (TaskService)ac.getBean("taskService");
		
		
		Map<String,Object> variables = new HashMap<String,Object>();
			
		variables.put("ownersCommitteePersonnelsIds", "-1,-1");
		variables.put("ownersCommitteePersonnelId", Long.toString(SessionContext.get().getId()));
		taskService.setVariables(delegateTask.getId(), variables);
		
	

	}

}
