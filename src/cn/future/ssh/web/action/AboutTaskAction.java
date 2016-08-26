package cn.future.ssh.web.action;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.struts2.ServletActionContext;

import cn.future.ssh.domain.Accreditation;
import cn.future.ssh.domain.Personnel;
import cn.future.ssh.service.AboutTaskService;
import cn.future.ssh.utils.SessionContext;
import cn.future.ssh.utils.ValueContext;
import cn.future.ssh.utils.Compression;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AboutTaskAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private AboutTaskService aboutTaskService;
	
	private String taskId;

	private String deploymentId;
	
	private String imageName;
	
    private String type;
	
    private String accreditationId;
    
    
	public String getAccreditationId() {
		return accreditationId;
	}

	public void setAccreditationId(String accreditationId) {
		this.accreditationId = accreditationId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	private File file;		//流程定义部署文件
	private String filename;//流程定义名称
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}


	public String getDeploymentId() {
		return deploymentId;
	}


	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}

	public String getImageName() {
		return imageName;
	}


	public void setImageName(String imageName) {
		this.imageName = imageName;
	}


	public String getTaskId() {
		return taskId;
	}


	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}


	public void setAboutTaskService(AboutTaskService aboutTaskService) {
		this.aboutTaskService = aboutTaskService;
	}
	
	
	/**
	 * 查找候选任务
	 */
	public String findCandidateTaskListById(){
		String loaderSign=(String) ActionContext.getContext().getSession().get("loaderSign");
		if(loaderSign!=null&&!"".equals(loaderSign)){
			if(!loaderSign.equals("中队")){
				Map<Task,Accreditation>  candidateTaskList = aboutTaskService.findCandidateTaskById(Long.toString(SessionContext.get().getId()));

				ValueContext.putValueContext("candidateTasklist", candidateTaskList);
			}
		}
		
		return "candidateTaskListUI";
	}

	/**
	 * 打开任务表单
	 */
	public String viewTaskForm(){
		String taskId = this.getTaskId();
		//获取任务表单中任务节点的url连接
		String url = aboutTaskService.findTaskFormKeyByTaskId(taskId);
		Task task = aboutTaskService.findTaskByTaskId(taskId);
		if(task.getName().equals("业委会集体签字")||task.getName().equals("案审委集体签字")){
			url+="taskId="+getTaskId();
		}else{
			url+="?taskId="+taskId;
		}
		
		ValueContext.putValueContext("url", url);
		return "viewTaskForm";
	}
	public String claimTask(){
		aboutTaskService.claimTask(taskId);
		return "claimTaskSuccess";
	}
	public String viewCurrentImageOrderAccId(){
		String accreditationId = this.getAccreditationId();
		ProcessDefinition pd = aboutTaskService.findProcessDefinitionByAccId(accreditationId);
		
		
		ValueContext.putValueContext("deploymentId", pd.getDeploymentId());
		ValueContext.putValueContext("imageName", pd.getDiagramResourceName());
		
		Map<String,Object> acs = aboutTaskService.findCoordingByAccId(accreditationId);
		ValueContext.putValueContext("acs", acs);
		
		return "image";
	}
	
	/**
	 * 查看当前流程运行到了哪个节点（查看当前活动节点，并使用红色的框标注）
	 */
	public String viewCurrentImage(){
		String taskId = this.getTaskId();
		ProcessDefinition pd = aboutTaskService.findProcessDefinitionByTaskId(taskId);
		
		ValueContext.putValueContext("deploymentId", pd.getDeploymentId());
		ValueContext.putValueContext("imageName", pd.getDiagramResourceName());
		
		Map<String,Object> acs = aboutTaskService.findCoordingByTask(taskId);
		ValueContext.putValueContext("acs", acs);
		return "image";
	}
	public String compressionDocument(){
		String documentsPath = "e:"+ServletActionContext.getRequest().getContextPath()+"/document/"+accreditationId;
		Compression compression = new Compression();
        try {
        	compression.zip(documentsPath);//你要压缩的文件夹
        }catch (Exception ex) {
           ex.printStackTrace();
       }
        return downLoadDocuments();
	}
	/*返回要下载文件的的输入流**/
	public InputStream getTargetFile(){
		
		String documentsPath = "e:"+ServletActionContext.getRequest().getContextPath()+"/document/"+accreditationId;
		
		FileInputStream fis = null;
		
		
		try {
			fis  = new FileInputStream(new File(documentsPath+".zip"));
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		return fis;
	}
	
	/*返回要下载文件的文件名**/
	public String getFileName() {
		String fileName = "案件"+accreditationId+"文书.zip";
		try {
			
			fileName = new String(new String(fileName).getBytes(),"ISO-8859-1");//对文件名重新编码，否则用户从浏览器下载的文件名会出现问题
			
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
	
	return fileName;
}
	
	public String downLoadDocuments(){
		return "success";
	}
	/**
	 * 查看流程图
	 */
	public void viewImage(){
		//获取部署对象id
		String deploymentId = this.getDeploymentId();
		//获取图片名称
		String imageName = this.getImageName();
		
		//获取资源文件表中的资源图片输入流
		InputStream in  = aboutTaskService.findImageInputStream(deploymentId,imageName);
		
		//将图片写到页面上，用输出流
		try {
			OutputStream opt = ServletActionContext.getResponse().getOutputStream();
			
			for(int b = -1;(b=in.read())!=-1;){
				opt.write(b);
			}
			opt.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据登录者的id查找登录者未办理的任务
	 */
	public String findTaskListById(){
		String id = null;
		Personnel personnel = SessionContext.get();
		String loaderSign = (String) ActionContext.getContext().getSession().get("loaderSign");
		
		if(loaderSign!=null&&!"".equals(loaderSign)){
			if(loaderSign.equals("中队")){
				id = "squadron"+Long.toString(personnel.getSquadron().getId());
			}else if(loaderSign.equals("一般管理员")){
				id = Long.toString(SessionContext.get().getId());
			}
			else{
				id = Long.toString(SessionContext.get().getId());
			}
		}
		
		//得到装载任务列表的集合
		Map<Task,Accreditation> taskMap = aboutTaskService.findTaskMapById(id,type);
	
		ValueContext.putValueContext("taskMap", taskMap);
		
		if(loaderSign!=null&&!"".equals(loaderSign)){
		
			//得到装载类型的集合，用于每个人登录上去之后显示有哪些选择条件
			 List<String> taskTypeList = aboutTaskService.getTaskTypeSet(SessionContext.get(),loaderSign);
			 ValueContext.putValueContext("taskTypeList", taskTypeList);
		
	  }
		return "taskListUI";
	}
	
	/**
	 * 部署管理首页显示
	 * @return
	 */
	public String deployHome(){
		//查询所有的部署对象
		List<Deployment> depList = aboutTaskService.findDeploymentList();
			
		ValueContext.putValueContext("depList", depList);
				
		return "deployHome";
	}
	
	/**
	 * 发布流程
	 * @return
	 */
	public String newdeploy(){
		File file = this.getFile();
		String fileName = this.getFilename();
		
		aboutTaskService.saveNewDeploye(file,fileName);
		
		return "list";
	}
	
	
	/**
	 * 删除部署信息
	 */
	public String delDeployment(){
		String deploymentId = this.getDeploymentId();
	
		aboutTaskService.deleteProcessDefinitionByDeploymentId(deploymentId);
		
		return "list";
	}
	
}

