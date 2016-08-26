package cn.future.ssh.service;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;

import cn.future.ssh.domain.Accreditation;
import cn.future.ssh.domain.Personnel;

public interface AboutTaskService {

	Map<Task,Accreditation>  findTaskMapById(String id,String type);

	ProcessDefinition findProcessDefinitionByTaskId(String taskId);

	Map<String, Object> findCoordingByTask(String taskId);

	InputStream findImageInputStream(String deploymentId, String imageName);

	String findTaskFormKeyByTaskId(String taskId);

	Map<Task,Accreditation> findCandidateTaskById(String id);

	void claimTask(String taskId);

	List<String> findOutComeListByTaskId(String taskId);

	List<Deployment> findDeploymentList();

	void saveNewDeploye(File file, String fileName);

	void deleteProcessDefinitionByDeploymentId(String deploymentId);

	Task findTaskByTaskId(String taskId);

	List<String> getTaskTypeSet(Personnel personnel, String loaderSign);
	//校验上传的文书的格式是否正确
	Boolean childValidate(ArrayList<String> allowType,
			List<File> images,
			List<String> FileNames,
			List<String> ContentTypes);

	ProcessDefinition findProcessDefinitionByAccId(String accreditationId);

	Map<String, Object> findCoordingByAccId(String accreditationId);
	

}
