package cn.future.ssh.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;
import java.util.zip.ZipInputStream;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;

import com.opensymphony.xwork2.ActionContext;

import cn.future.ssh.domain.Accreditation;
import cn.future.ssh.domain.Document;
import cn.future.ssh.domain.PTable;
import cn.future.ssh.domain.Personnel;
import cn.future.ssh.domain.Role;
import cn.future.ssh.service.AboutTaskService;
import cn.future.ssh.service.AccreditationService;
import cn.future.ssh.utils.SessionContext;
import cn.future.ssh.web.form.AccreditationBean;
import cn.future.ssh.web.form.PTableBean;



public class AboutTaskServiceImpl implements AboutTaskService {
	
	
	private TaskService taskService;
	
	private RepositoryService repositoryService;
	
	private RuntimeService runtimeService;
	
	private FormService formService;
	
	private AccreditationService accreditationService;
	
	private HistoryService historyService;
	

	private static Properties properties = new Properties();

	static {
		try {
			properties.load(AccreditationServiceImpl.class.getClassLoader()
					.getResourceAsStream("uploadFileType.properties"));
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	


	public void setAccreditationService(AccreditationService accreditationService) {
		this.accreditationService = accreditationService;
	}

	public void setHistoryService(HistoryService historyService) {
		this.historyService = historyService;
	}

	public void setFormService(FormService formService) {
		this.formService = formService;
	}

	public void setRuntimeService(RuntimeService runtimeService) {
		this.runtimeService = runtimeService;
	}

	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}
	/**
	 * 得到任务节点的formkey
	 */
	@Override
	public String findTaskFormKeyByTaskId(String taskId) {
		TaskFormData formData = formService.getTaskFormData(taskId);
		String formKey = formData.getFormKey();
		return formKey;
	}
	

		
	/**
	 * 查找候选任务
	 */
	@Override
	public Map<Task,Accreditation> findCandidateTaskById(String id) {
		
		
		List<Task> list =  taskService.createTaskQuery()
					.taskCandidateUser(id)
					.orderByTaskCreateTime().desc()
					.list();
		
		 Map<Task,Accreditation> taskMap = new HashMap<Task,Accreditation>();
		 
		 for(int i = 0;i < list.size();i++){
			 Task task =  list.get(i);
			 String taskId = task.getId();
			 Accreditation accreditation = accreditationService.findAccreditationByTaskId(taskId);
			 taskMap.put(task, accreditation);
			 
		 }
	
		return taskMap;
	}
	
	
	/**
	 *通过任务登录者的id将id作为任务的办理人查找待办任务
	 */
	@Override
	public Map<Task,Accreditation> findTaskMapById(String id,String type) {
		
	 List<Task> list1 = taskService.createTaskQuery()
				.taskAssignee(id)
				.orderByTaskCreateTime().desc()
				.list();
	 List<Task> list2 = null;
	 if(type!=null&&!"".equals(type.trim())){
		list2 = new ArrayList<Task>();
		for(Task task:list1){
			if(task.getName().indexOf(type)!=-1){
				list2.add(task);
			}
		}
	 }else{
		 list2 = list1;
	 }
			
	 Map<Task,Accreditation> taskMap = new HashMap<Task,Accreditation>();
	 
	 for(int i = 0;i < list2.size();i++){
		 Task task =  list2.get(i);
		 String taskId = task.getId();
		 Accreditation accreditation = accreditationService.findAccreditationByTaskId(taskId);
		 taskMap.put(task, accreditation);
		 
	 }
/*	 //主要用于将中队人员待打印报表的重复的任务删除掉
	 List<Task> list2 = new ArrayList<Task>();	
	 for(Task task1:list1){
		 for(Task task2:list1){
			 if(task1.getTaskDefinitionKey().equals(task2.getTaskDefinitionKey())&&task1.getProcessInstanceId().equals(task2.getProcessInstanceId())){
				 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				 long task1Time=sdf.parse(task1.getCreateTime().toLocaleString()).getTime();
				 long task2Time =sdf.parse(task2.getCreateTime().toLocaleString()).getTime();
				 if(task1Time > task2Time){
					 list2.add(task2);
				 }
				 if(task1.getCreateTime().after(task2.getCreateTime())){
					taskService.complete(task2.getId());
					 list2.add(task2);
				 }
				
			 }
		 }
	 }
	 list1.removeAll(list2);*/
	 
	 return taskMap;
	}
	/**
	 * 通过任务id查找流程定义对象
	 */
	@Override
	public ProcessDefinition findProcessDefinitionByTaskId(String taskId) {
		Task task = taskService.createTaskQuery()
								.taskId(taskId)
								.singleResult();
		
	
		String processDefinitionId = task.getProcessDefinitionId();
		
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
					.processDefinitionId(processDefinitionId)
							.singleResult();
		return processDefinition;
	}
	
	/**
	 * 通过部署对象id和图片名称得到流程图片的输入流
	 */
	@Override
	public InputStream findImageInputStream(String deploymentId,
			String imageName) {
		
		return repositoryService.getResourceAsStream(deploymentId, imageName);
	}
	

	@Override
	public void claimTask(String taskId) {
		Personnel personnel = SessionContext.get();
		
		Task task = taskService.createTaskQuery()
								.taskId(taskId)
								.singleResult();
		ProcessInstance pt = runtimeService.createProcessInstanceQuery()
											.processInstanceId(task.getProcessInstanceId())
											.singleResult();
		String businessKey = pt.getBusinessKey();
		String key = businessKey.substring(businessKey.indexOf(".")+1);
		
		Accreditation accreditation = accreditationService.findAccreditationByAccreditationId(Long.valueOf(key));
		
		accreditation.setLegalName(personnel.getName());
		accreditationService.updateAccreditation(accreditation);
		
		taskService.claim(taskId, Long.toString(personnel.getId()));
		
	}
	/**
	 * 查找任务节点的出线
	 */
	@Override
	public List<String> findOutComeListByTaskId(String taskId) {
		Task task = taskService.createTaskQuery()
				.taskId(taskId)
				.singleResult();
		//获取流程定义id
		String processDefinitionId = task.getProcessDefinitionId();
		//查询ProcessDefinitionEntity对象
		ProcessDefinitionEntity pde = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processDefinitionId);
													
														
		//使用流程实例id，查询正在执行的执行对象表，返回流程实例对象
		ProcessInstance pi = runtimeService.createProcessInstanceQuery()
											.processInstanceId(task.getProcessInstanceId())
											.singleResult();

		//String activityId = pi.getActivityId();
		
	//	ActivityImpl activityImpl = pde.findActivity(activityId);
		ActivityImpl activityImpl = null;
		List<ActivityImpl> activitiList = pde.getActivities();   
	
		for(ActivityImpl pvm:activitiList){
			
			if(task.getTaskDefinitionKey().equals(pvm.getId())){
				activityImpl = pvm;
			}
		}
		List<PvmTransition> pts = activityImpl.getOutgoingTransitions();
		List<String> list = new ArrayList<String>();
		if(pts!=null&&pts.size()>0){
			for(PvmTransition pvm:pts){
				
				if(StringUtils.isNotBlank((String)pvm.getProperty("name"))){
					String name = (String)(pvm.getProperty("name"));
					if(!list.contains(name)){
						list.add(name);
					}
					
				}
			}
		

		}
		return list;
	}

	@Override
	public List<Deployment> findDeploymentList() {
		List<Deployment> depList = repositoryService.createDeploymentQuery()
							.orderByDeploymenTime().asc()
							.list();
		return depList;
	}
	
	@Override
	public void saveNewDeploye(File file, String fileName) {
		ZipInputStream zipInputStream = null;
		try {
			zipInputStream = new ZipInputStream(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			// 
			e.printStackTrace();
		}
		repositoryService.createDeployment()
						.name(fileName)
						.addZipInputStream(zipInputStream)
						.deploy();
		
	}

	@Override
	public void deleteProcessDefinitionByDeploymentId(String deploymentId) {
		repositoryService.deleteDeployment(deploymentId,true);
		
	}

	@Override
	public Task findTaskByTaskId(String taskId) {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		return task;
	}

	@Override
	public List<String> getTaskTypeSet(Personnel personnel,String loaderSign) {
		List<String> taskTypeList = new ArrayList<String>();
			if(loaderSign!=null&&"中队".equals(loaderSign)){
				taskTypeList.add("accreditationSign");
				taskTypeList.add("pNoticeSign");
				taskTypeList.add("pTableSign");
				taskTypeList.add("pDecideSign");
				taskTypeList.add("pClosingReportSign");
				
			}else{
			
				for(Role role:personnel.getRoles()){
					
						String roleName = role.getName();
						if(roleName!=null){
							if(roleName.equals("中队长")||roleName.equals("大队长")||roleName.equals("中队队员")){
								taskTypeList.add("accreditationSign");
								taskTypeList.add("pTableSign");
								taskTypeList.add("pClosingReportSign");
							}else if(roleName.equals("法制科领导")){
								taskTypeList.add("accreditationSign");
								taskTypeList.add("pNoticeSign");
								taskTypeList.add("pTableSign");
								taskTypeList.add("pDecideSign");
								taskTypeList.add("pClosingReportSign");
							}
						}
				   	
				}
			}
		return taskTypeList;
	}

	@Override
	public Boolean childValidate(ArrayList<String> allowType,
			List<File> images,
			List<String> FileNames,
			List<String> ContentTypes) {
			if(images!=null&&images.size() > 0){
			HashMap<String,String> map = new HashMap<String,String>();
			
			
			for(int i = 0;i < images.size();i++){
				map.put(FileNames.get(i),ContentTypes.get(i));
			}
		
			for(String key:map.keySet()){
					String ext = key.substring(key.lastIndexOf(".")+1);
					
					if(!allowType.contains(map.get(key).toLowerCase())||!properties.keySet().contains(ext.toLowerCase())){
						
						return false;
					}
							
				}
		
		}
		return true;
	}

	@Override
	public ProcessDefinition findProcessDefinitionByAccId(String accreditationId) {
		ProcessInstance pt = runtimeService.createProcessInstanceQuery()
											.processInstanceBusinessKey("Accreditation."+accreditationId)
											.singleResult();
		ProcessDefinition pd = repositoryService.createProcessDefinitionQuery()
												.processDefinitionId(pt.getProcessDefinitionId())
												.singleResult();
		
				
											
		return pd;
	}

	@Override
	public Map<String, Object> findCoordingByAccId(String accreditationId) {
		ProcessInstance pt = runtimeService.createProcessInstanceQuery()
				.processInstanceBusinessKey("Accreditation."+accreditationId)
				.singleResult();
		ProcessDefinitionEntity pde = (ProcessDefinitionEntity)repositoryService.getProcessDefinition(pt.getProcessDefinitionId());
					
	
		String activityId = runtimeService.createProcessInstanceQuery()
											.processInstanceId(pt.getId())
											.singleResult()
											.getActivityId();
		if(activityId==null){
			String caseLevel = accreditationService.findAccreditationByAccreditationId(Long.parseLong(accreditationId)).getpTable().getCaseLevel();
			
			if(caseLevel.equals("重大")){
				activityId = "usertask21";
				
			}else{
				activityId = "usertask22";
			}
		}
		ActivityImpl activityImpl = pde.findActivity(activityId);
		Map<String,Object> acs = new HashMap<String,Object>();
		acs.put("x", activityImpl.getX());
		acs.put("y", activityImpl.getY());
		acs.put("width", activityImpl.getWidth());
		acs.put("height", activityImpl.getHeight());
		return acs;
	}
	
	/**
	 * 通过任务id查找当前任务节点，返回当前任务节点的坐标级宽高
	 */
	@Override
	public Map<String, Object> findCoordingByTask(String taskId) {
		Task task = taskService.createTaskQuery()
				.taskId(taskId)
				.singleResult();
		String processDefinitionId = task.getProcessDefinitionId();
		ProcessDefinitionEntity pde = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processDefinitionId);
		String activityId = runtimeService.createProcessInstanceQuery()
											.processInstanceId(task.getProcessInstanceId())
											.singleResult()
											.getActivityId();
		
		if(activityId==null){
			Accreditation accreditation = accreditationService.findAccreditationByTaskId(taskId);
			String caseLevel = accreditationService.findAccreditationByAccreditationId(accreditation.getId()).getpTable().getCaseLevel();
			
			if(caseLevel.equals("重大")){
				activityId = "usertask21";
				
			}else{
				activityId = "usertask22";
			}
		}									
		ActivityImpl activityImpl = pde.findActivity(activityId);
		Map<String,Object> acs = new HashMap<String,Object>();
		acs.put("x", activityImpl.getX());
		acs.put("y", activityImpl.getY());
		acs.put("width", activityImpl.getWidth());
		acs.put("height", activityImpl.getHeight());
		
		return acs;
	}
			


	
}
