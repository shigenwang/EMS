package cn.future.ssh.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import cn.future.ssh.dao.AccreditationDao;
import cn.future.ssh.domain.Accreditation;
import cn.future.ssh.domain.CaseSource;
import cn.future.ssh.domain.Document;

import cn.future.ssh.domain.PageBean;
import cn.future.ssh.domain.Personnel;
import cn.future.ssh.domain.Squadron;
import cn.future.ssh.domain.Summary;
import cn.future.ssh.service.AboutTaskService;
import cn.future.ssh.service.AccreditationService;
import cn.future.ssh.service.DocumentService;
import cn.future.ssh.service.PersonnelService;
import cn.future.ssh.service.SquadronService;
import cn.future.ssh.utils.QueryHelper;
import cn.future.ssh.utils.SessionContext;
import cn.future.ssh.utils.ValueContext;
import cn.future.ssh.web.form.AccreditationBean;

public class AccreditationServiceImpl implements AccreditationService {

	private AccreditationDao accreditationDao;

	private PersonnelService personnelService;
	private SquadronService squadronService;
	private DocumentService documentService;
	private RepositoryService repositoryService;

	private RuntimeService runtimeService;

	private TaskService taskService;

	private FormService formService;

	private HistoryService historyService;
	private AboutTaskService aboutTaskService;
	
	
	public void setAboutTaskService(AboutTaskService aboutTaskService) {
		this.aboutTaskService = aboutTaskService;
	}

	private static Properties properties = new Properties();

	static {
		try {
			properties.load(AccreditationServiceImpl.class.getClassLoader()
					.getResourceAsStream("uploadFileType.properties"));
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void setSquadronService(SquadronService squadronService) {
		this.squadronService = squadronService;
	}

	public void setDocumentService(DocumentService documentService) {
		this.documentService = documentService;
	}

	public void setPersonnelService(PersonnelService personnelService) {
		this.personnelService = personnelService;
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

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;

	}

	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}

	public void setAccreditationDao(AccreditationDao accreditationDao) {
		this.accreditationDao = accreditationDao;
	}
	public void saveDocument(Accreditation accreditation,AccreditationBean accreditationBean,String methodName,List<String> allowType,File imagesSavedir){
		//通过反射得到立案审批表对象的文书的方法
		String filesMethodStr = "get"+methodName;
		String fileNameMethodStr = "get"+methodName+"FileName";
		String contentTypeMethodStr = "get"+methodName+"ContentType";
		
		Class accreditationBeanClass = accreditationBean.getClass();
		Method filesMethod = null;
		Method fileNameMethod = null;
		Method contentTypeMethod = null;
		
		List<File> files = null;
		List<String> fileNames= null;
		List<String> contentTypes = null;
		try{
			filesMethod = accreditationBeanClass.getDeclaredMethod(filesMethodStr);
			fileNameMethod = accreditationBeanClass.getDeclaredMethod(fileNameMethodStr);
			contentTypeMethod = accreditationBeanClass.getDeclaredMethod(contentTypeMethodStr);
			
			files = (List<File>) filesMethod.invoke(accreditationBean);
			fileNames= (List<String>) fileNameMethod.invoke(accreditationBean);
			contentTypes = (List<String>) contentTypeMethod.invoke(accreditationBean);
		}catch(Exception e){
			e.printStackTrace();
		}
		
			//HashMap<String,String> map = new HashMap<String,String>();
			List<Document> documents = new ArrayList<Document>();
			List<String> imageNames = new ArrayList<String>();
			if(files!=null){
				for(int i = 0;i < files.size();i++){
					//map.put(fileNames.get(i), contentTypes.get(i));
					imageNames.add(fileNames.get(i));
				}
			
						int i = 0;
						for(String key:fileNames){
							
							String ext = key.substring(key.lastIndexOf("."));
				
							Document document  = new Document();
						
							String imageName = UUID.randomUUID().toString() + ext;
							document.setImageName(imageName);
							document.setAccreditation(accreditation);
							//保存新建对象，以便产生id
							documentService.saveDocument(document);
							
							File outputDir = new File(imagesSavedir, imageName);
							
							try {
								FileOutputStream os = new FileOutputStream(outputDir);
								FileInputStream is = new FileInputStream(files.get(i));
								int length;
								byte[] buffer = new byte[1024 * 1024];
								while ((length = is.read(buffer)) > 0) {
									os.write(buffer, 0, length);
								}
					
								os.close();
								is.close();

							}catch(Exception e){
								e.printStackTrace();
							}
							
							documents.add(document);
							
							i++;// 标识循环到第几个上传文件
								
						}
						//由于上传的文书每次上传都会覆盖前一次上传的同一种类的文书，所以要先将上一次上传的文书清空，然后再添加
						if(documents.size() > 0){
							
								Class accreditationClass = accreditation.getClass();
								try {
									Method m = accreditationClass.getDeclaredMethod(filesMethodStr);
									Set<Document> documents2 = (Set<Document>) m.invoke(accreditation);
									
									documents2.clear();
									
									documents2.addAll(documents);
									/*String flagMethodStr = "set"+methodName+"Flag";
									Method flagMethod = accreditationClass.getDeclaredMethod(flagMethodStr,Boolean.class);
									flagMethod.invoke(accreditation,true);*/
								} catch (Exception e) {
									
									e.printStackTrace();
								} 
								
							documents.clear();
						}
			}
			
					
		}
		
				
	
	/**
	 * 提交（保存）立案申请表的同时启动流程并且办理流程的第一个任务，或者修改立案申请表（流程已经启动，不需要再启动流程）
	 */
	@Override
	public void submitAccreditation(AccreditationBean accreditationBean) {

		Accreditation accreditation = null;
		Personnel loader = SessionContext.get();
		Squadron squadron = loader.getSquadron();
		Long accreditationId = accreditationBean.getAccreditationId();
		if (accreditationId == null) {
			accreditation = new Accreditation();
		} else {
			accreditation = accreditationDao
					.findAccreditationByAccreditationId(accreditationId);
		}
		Map<String, Object> variables = new HashMap<String, Object>();
		accreditation.setId(accreditationBean.getAccreditationId());
		// 给对象属性赋值
		Summary summary = new Summary();
		summary.setId(accreditationBean.getSummaryId());
		accreditation.setSummary(summary);

		// 单位属性设置
		accreditation.setUnitName(accreditationBean.getUnitName());
		accreditation.setLeRepresentative(accreditationBean
				.getLeRepresentative());
		accreditation.setUnitTel(accreditationBean.getUnitTel());
		accreditation.setUnitAddress(accreditationBean.getUnitAddress());

		// 个人属性设置
		accreditation.setPersonnelName(accreditationBean.getPersonnelName());
		
		
		if(accreditationBean.getUnitName()==null||"".equals(accreditationBean.getUnitName().trim())){
			accreditation.setSex(accreditationBean.getSex());
			accreditation.setUserAge(accreditationBean.getUserAge());
			accreditation.setUserAddress(accreditationBean.getUserAddress());
			accreditation.setIdNumber(accreditationBean.getIdNumber());
			accreditation.setUserTel(accreditationBean.getUserTel());
		}
		

		// 基本案情
		accreditation.setBaseCase(accreditationBean.getBaseCase());

		// 案件来源
		CaseSource caseSource = new CaseSource();
		caseSource.setId(accreditationBean.getCaseSourceId());
		accreditation.setCaseSource(caseSource);

		// accreditation.setLegGuideSuggest(accreditationBean.getLegGuideSuggest());
		
		// 设置主办协办
		Personnel sponsor = new Personnel();
		Long sponsorId = accreditationBean.getSponsorId();
		sponsor.setId(sponsorId);

		Long assistantHandleId = accreditationBean.getAssistantHandleId();
		Personnel assistantHandle = new Personnel();
		assistantHandle.setId(assistantHandleId);

		accreditation.setSponsor(sponsor);
		accreditation.setAssistantHandle(assistantHandle);

		
		
		// 上传文书
		

		/*String imagePath = "document";
		String fullPath = ServletActionContext.getServletContext().getRealPath(
				imagePath);
*/
		/*String imagePath = "document";
		String fullPath = ServletActionContext.getServletContext().getRealPath(
				"");
		fullPath = fullPath+"/..";*/
		if(accreditationBean.getTaskId() == null
				|| "".equals(accreditationBean.getTaskId())){
			accreditationDao.saveAccreditation(accreditation);
		}
		
		String imagePath = "e:"+ServletActionContext.getRequest().getContextPath()+"/document/"+accreditation.getId();
		File imagesSavedir = new File(imagePath);
		if (!imagesSavedir.exists()) {
			imagesSavedir.mkdirs();
		}
		
		
		// 将允许上传的图片类型装入集合，以便验证使用
		ArrayList<String> allowType = new ArrayList<String>();
		for (Object key : properties.keySet()) {
			String value = (String) properties.get(key);
			String[] values = value.split(",");
			for (String v : values) {
				allowType.add(v);
			}
		}

		File idCardFile = accreditationBean.getIdCard();
		if (idCardFile != null) {

				
			String idCardFileName = accreditationBean.getIdCardFileName();
			if (!allowType.contains(accreditationBean.getIdCardContentType().toLowerCase())
					|| properties.keySet().contains(
							idCardFileName.substring(idCardFileName
									.lastIndexOf(",") + 1))) {
				try {
					throw new Exception("上传文书类型错误");
				} catch (Exception e) {
					e.printStackTrace();
				}
				return;
			}
	
			String ext = idCardFileName.substring(idCardFileName.lastIndexOf("."));
			Document idCard = new Document();
			
			
			String imageName = UUID.randomUUID().toString() + ext;
			idCard.setImageName(imageName);
			idCard.setAccreditation(accreditation);
			//保存文书，以此产生对象
			documentService.saveDocument(idCard);
			
			File outputDir = new File(imagesSavedir, imageName);
			FileInputStream is;
			try {
				FileOutputStream os = new FileOutputStream(outputDir);
				is = new FileInputStream(idCardFile);
				int length;
				byte[] buffer = new byte[1024 * 1024];
				while ((length = is.read(buffer)) > 0) {
					os.write(buffer, 0, length);
				}
	
				os.close();
				is.close();
			} catch (Exception e) {
	
				e.printStackTrace();
			}
			
			accreditation.setIdCard(idCard);

		}
		
		
		File businessLicese = accreditationBean.getBusinessLicense();
		if (businessLicese != null) {

				
			String businessLicenseFileName = accreditationBean.getBusinessLicenseFileName();
			if (!allowType.contains(accreditationBean.getBusinessLicenseContentType().toLowerCase())
					|| properties.keySet().contains(
							businessLicenseFileName.substring(businessLicenseFileName
									.lastIndexOf(",") + 1))) {
				
			
				try {
					throw new Exception("上传文书类型错误");
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				return;
			}
	
			String ext = businessLicenseFileName.substring(businessLicenseFileName.lastIndexOf("."));
			Document businessLicense = new Document();
			
			
			String imageName = UUID.randomUUID().toString() + ext;
			businessLicense.setImageName(imageName);
			businessLicense.setAccreditation(accreditation);
			//保存文书，以此产生对象
			documentService.saveDocument(businessLicense);
			
			File outputDir = new File(imagesSavedir, imageName);
			FileInputStream is;
			try {
				FileOutputStream os = new FileOutputStream(outputDir);
				is = new FileInputStream(idCardFile);
				int length;
				byte[] buffer = new byte[1024 * 1024];
				while ((length = is.read(buffer)) > 0) {
					os.write(buffer, 0, length);
				}
	
				os.close();
				is.close();
			} catch (Exception e) {
	
				e.printStackTrace();
			}
			
			accreditation.setBusinessLicense(businessLicense);

		}
		
		saveDocument(accreditation, accreditationBean,"EnforceCard",allowType, imagesSavedir);
		saveDocument(accreditation, accreditationBean,"OrderChangeNotice",allowType, imagesSavedir);
		saveDocument(accreditation, accreditationBean,"RecordInquest",allowType, imagesSavedir);
		saveDocument(accreditation, accreditationBean,"SitePhotos",allowType, imagesSavedir);
		saveDocument(accreditation, accreditationBean,"RecordInv",allowType, imagesSavedir);
		saveDocument(accreditation, accreditationBean,"RecordPaper",allowType, imagesSavedir);
		variables.put("sponsorId",
				Long.toString(accreditation.getSponsor().getId()));
		variables.put("assistantHandleId",
				Long.toString(accreditation.getAssistantHandle().getId()));

		if (accreditationBean.getTaskId() == null
				|| "".equals(accreditationBean.getTaskId())) {
			// 查询登录者的中队长，并设置流程变量
			Personnel captain = personnelService
					.findCaptainByMember(loader);
			variables.put("captainId", Long.toString(captain.getId()));

			// 查询大队长，并设置流程变量
			Personnel bigCaptain = personnelService.findPersonByRole("大队长");
			variables.put("bigCaptainId", Long.toString(bigCaptain.getId()));

		
			// 初始化法制科指导标记
			variables.put("accreditationSubmit", "false");
			variables.put("pTableSubmit", "false");
			variables.put("pClosingReportSubmit", "false");
			// 查询所有法制科人员，并将法制科所有人员作为立案审批表审批的候选人
			List<Personnel> legalDepartmentPersonnels = personnelService
					.findLegalDepartmentPersonnels();
			StringBuffer legalDepartmentPersonnelsIds = new StringBuffer();
			for (Personnel personnel : legalDepartmentPersonnels) {
				legalDepartmentPersonnelsIds.append(Long.toString(personnel
						.getId()) + ",");
			}
			variables.put("legalDepartmentPersonnelsIds",
					legalDepartmentPersonnelsIds.substring(0,
							legalDepartmentPersonnelsIds.length() - 1));
			// 防止第一次运行到法制科节点报错
			variables.put("legalDepartmentPersonnelId", null);

			// 指定进行会签时的业委会的所有人员
			List<Personnel> industryCommitteePersonnels = personnelService
					.findIndustryCommitteePersonnels();

			List<String> industryCommitteePersonnelsIds = new ArrayList<String>();
			for (Personnel personnel : industryCommitteePersonnels) {
				industryCommitteePersonnelsIds.add(Long.toString(personnel
						.getId()));
			}
			variables.put("industryCommitteePersonnelsIds",
					industryCommitteePersonnelsIds);

			// 将业委会所有人员作为审批处罚结案报告的候选人
			StringBuffer ownersCommitteePersonnelsIds = new StringBuffer();
			for (Personnel personnel : industryCommitteePersonnels) {
				ownersCommitteePersonnelsIds.append(Long.toString(personnel
						.getId()) + ",");
			}
			variables.put("ownersCommitteePersonnelsIds",
					ownersCommitteePersonnelsIds.substring(0,
							ownersCommitteePersonnelsIds.length() - 1));
			/*
			 * //防止第一次运行到业委会节点报错 variables.put("ownersCommitteePersonnelId",
			 * null);
			 */
			// 指定进行会签时的案审委的所有人员
			List<Personnel> caseReviewComPersonnels = personnelService
					.findCaseReviewComPersonnels();

			List<String> caseReviewComPersonnelsIds = new ArrayList<String>();
			for (Personnel personnel : caseReviewComPersonnels) {
				caseReviewComPersonnelsIds
						.add(Long.toString(personnel.getId()));
			}
			variables.put("caseReviewComPersonnelsIds",
					caseReviewComPersonnelsIds);
			
			
			
			String key = accreditation.getClass().getSimpleName();
			String objId = key + "." + accreditation.getId();

			variables.put(
					"squadronId",
					"squadron"
							+ Long.toString(squadron.getId()));
			// 启动流程
			runtimeService.startProcessInstanceByKey(key, objId, variables);

			Task task = taskService
					.createTaskQuery()
					.taskAssignee(
							"squadron"
									+ Long.toString(squadron.getId()))
					.orderByTaskCreateTime().desc().list().get(0);
			taskService.complete(task.getId(), variables);
		} else {
			accreditation.setId(accreditationBean.getAccreditationId());
			accreditationDao.updateAccreditation(accreditation);
			taskService.complete(accreditationBean.getTaskId(), variables);
			

		}
		
		accreditation.setSquadron(squadron);
		accreditationDao.updateAccreditation(accreditation);
		
		squadron.getAccreditation().add(accreditation);
		squadronService.updateSquadron(squadron);
		
		

	}

	@Override
	public Accreditation findAccreditationByTaskId(String taskId) {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();

		String processInstanceId = task.getProcessInstanceId();
		// 查找流程实例
		ProcessInstance pi = runtimeService.createProcessInstanceQuery()
				.processInstanceId(processInstanceId).singleResult();

		// 查找BUSINESS_KEY字段
		String businessKey = pi.getBusinessKey();
		String id = "";
		if (StringUtils.isNotBlank(businessKey)) {
			id = businessKey.split("\\.")[1];

		}
		Accreditation accreditation = accreditationDao.findAccreditationByAccreditationId(Long.parseLong(id));

		return accreditation;
	}

	@Override
	public void saveSubmitTask(AccreditationBean accreditationBean) {
		String taskId = accreditationBean.getTaskId();

		// 获取连线的名称
		String outCome = accreditationBean.getOutCome();
		/**
		 * 添加批注的时候，由于Activiti底层是使用： String userId =
		 * Authentication.getAuthenticatedUserId(); CommentEntity comment = new
		 * CommentEntity(); comment.setUserId(userId);
		 * 
		 * 所需要从Session中获取当前登陆人，作为该任务的办理人，对应act_hi_comment表中的USER_ID字段，不过不添加审批人，
		 * 该字段为空
		 * */

		/*
		 * Authentication.setAuthenticatedUserId(loader.getName());
		 * taskService
		 * .addComment(taskId,processInstanceId,workflowBean.getComment());
		 */
		Map<String, Object> variables = new HashMap<String, Object>();

		// 完成任务的同时设置流程变量，指定下一个任务节点

		variables.put("outCome", outCome);

		Accreditation accreditation = this.findAccreditationByTaskId(taskId);
		
		Boolean idCardFlag = accreditationBean.getIdCardFlag();
		Boolean enforceCardFlag = accreditationBean.getEnforceCardFlag();
		Boolean orderChangeNoticeFlag = accreditationBean.getOrderChangeNoticeFlag();
		Boolean recordInquestFlag = accreditationBean.getRecordInquestFlag();
		Boolean sitePhotosFlag = accreditationBean.getSitePhotosFlag();
		Boolean recordInvFlag = accreditationBean.getRecordInvFlag();
		Boolean recordPaperFlag = accreditationBean.getRecordPaperFlag();
		Boolean businessLicenseFlag = accreditationBean.getBusinessLicenseFlag();
		
		
		if (outCome.equals("驳回")
				&& ((accreditationBean.getCaptainSuggest() != null && !""
						.equals(accreditationBean.getCaptainSuggest().trim()))
						|| (accreditationBean.getLegSuggest() != null && !""
								.equals(accreditationBean.getLegSuggest()
										.trim()))
						|| (accreditationBean.getBigCaptainSuggest() != null && !""
								.equals(accreditationBean
										.getBigCaptainSuggest().trim())) || (accreditationBean
						.getLegGuideSuggest() != null && !""
						.equals(accreditationBean.getLegGuideSuggest().trim())))) {
			if (accreditationBean.getCurrentSign() != null) {
				if (accreditationBean.getCurrentSign().equals("中队长审批")) {
					accreditation.setCaptainSuggest(accreditationBean
							.getCaptainSuggest());

					accreditation.setJoinFlag(false);
					accreditation.setHostFlag(false);
				} else if (accreditationBean.getCurrentSign().equals("法制科审批")) {

					accreditation.setCaptainFlag(false);
					accreditation.setJoinFlag(false);
					accreditation.setHostFlag(false);
					accreditation.setLegSuggest(accreditationBean
							.getLegSuggest());
				} else if (accreditationBean.getCurrentSign().equals("大队长审批")) {

					accreditation.setLegalFlag(false);
					accreditation.setCaptainFlag(false);
					accreditation.setJoinFlag(false);
					accreditation.setHostFlag(false);
					accreditation.setBigCaptainSuggest(accreditationBean
							.getBigCaptainSuggest());
				}
			}
			
		} else if (outCome.equals("批准")
				&& !((accreditationBean.getCaptainSuggest() != null && !""
						.equals(accreditationBean.getCaptainSuggest().trim()))
						|| (accreditationBean.getLegSuggest() != null && !""
								.equals(accreditationBean.getLegSuggest()
										.trim())) || (accreditationBean
						.getBigCaptainSuggest() != null && !""
						.equals(accreditationBean.getBigCaptainSuggest().trim())))) {
			
			
			
			if(accreditation.getUnitName()==null||"".equals(accreditation.getUnitName())){
				businessLicenseFlag = true;
			}
			if(!(idCardFlag&&enforceCardFlag&&orderChangeNoticeFlag&&recordInquestFlag&&sitePhotosFlag&&recordInvFlag&&recordPaperFlag&&businessLicenseFlag)){
				System.out.println("批准时时必须通过中队上传的所有文书");
				try {
					throw new Exception();
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				return;
			}
			
			
			if (accreditationBean.getCurrentSign() != null) {
				String loaderName = SessionContext.get().getName();
				if (accreditationBean.getCurrentSign().equals("中队长审批")) {
					accreditation.setCaptainSuggest(null);
					accreditation.setCaptainFlag(true);
					accreditation.setCaptainName(loaderName);
					accreditation.setCaptainDate(new Date());
				} else if (accreditationBean.getCurrentSign().equals("法制科审批")) {
					accreditation.setLegSuggest(null);
					accreditation.setLegalFlag(true);
					accreditation.setLegalName(loaderName);
					accreditation.setLegalDate(new Date());
				} else if (accreditationBean.getCurrentSign().equals("大队长审批")) {
					accreditation.setBigCaptainSuggest(null);
					accreditation.setChiefFlag(true);
					accreditation.setBigCaptainName(loaderName);
					accreditation.setBigCaptainDate(new Date());
				} else if (accreditationBean.getCurrentSign().equals("主办审批")) {
					if (accreditation.getJoinFlag() != null
							&& accreditation.getJoinFlag()) {
						accreditation.setHostDate(new Date());
					}
					accreditation.setHostFlag(true);
				} else if (accreditationBean.getCurrentSign().equals("协办审批")) {
					if (accreditation.getHostFlag() != null
							&& accreditation.getHostFlag()) {
						accreditation.setHostDate(new Date());
					}
					accreditation.setJoinFlag(true);
				}
			}
		
		} else {
			System.out.println("批准时不能填写意见，驳回时必须填写意见");
			try {
				throw new Exception();
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			return;
		}
		

		accreditation.setIdCardFlag(idCardFlag);
		accreditation.setEnforceCardFlag(enforceCardFlag);
		accreditation.setOrderChangeNoticeFlag(orderChangeNoticeFlag);
		accreditation.setRecordInquestFlag(recordInquestFlag);
		accreditation.setSitePhotosFlag(sitePhotosFlag);
		accreditation.setRecordInvFlag(recordInvFlag);
		accreditation.setRecordPaperFlag(recordPaperFlag);
		accreditation.setBusinessLicenseFlag(businessLicenseFlag);
		this.updateAccreditation(accreditation);
		taskService.complete(taskId, variables);
	}

	@Override
	public Task findLastTask() {
		return taskService.createTaskQuery()
				.taskAssignee(Long.toString(SessionContext.get().getId()))
				.orderByTaskCreateTime().desc().list().get(0);

	}

	@Override
	public void updateAccreditation(Accreditation accreditation) {
		accreditationDao.updateAccreditation(accreditation);

	}

	@Override
	public Accreditation findAccreditationByAccreditationId(
			Long accreditationId) {
		return accreditationDao.findAccreditationByAccreditationId(accreditationId);

	}

	@Override
	public String getOutComeByTaskId(String taskId) {
		if (taskId == null) {
			return "向法制科申请指导";
		} else {
			String accreditationSubmit = (String) taskService.getVariable(
					taskId, "accreditationSubmit");
			if (accreditationSubmit.equals("false")) {
				return "向法制科申请指导";
			} else {
				return "提交立案申请表";
			}
		}

	}

	@Override
	public void guide(AccreditationBean accreditationBean) {
		Task task = taskService.createTaskQuery()
				.taskId(accreditationBean.getTaskId()).singleResult();
		
		if (task != null) {
			Accreditation accreditation = findAccreditationByTaskId(accreditationBean
					.getTaskId());
			
			Map<String, Object> variables = new HashMap<String, Object>();
			String outCome = accreditationBean.getOutCome();
			
			Boolean idCardFlag = accreditationBean.getIdCardFlag();
			Boolean enforceCardFlag = accreditationBean.getEnforceCardFlag();
			Boolean orderChangeNoticeFlag = accreditationBean.getOrderChangeNoticeFlag();
			Boolean recordInquestFlag = accreditationBean.getRecordInquestFlag();
			Boolean sitePhotosFlag = accreditationBean.getSitePhotosFlag();
			Boolean recordInvFlag = accreditationBean.getRecordInvFlag();
			Boolean recordPaperFlag = accreditationBean.getRecordPaperFlag();
			Boolean businessLicenseFlag = accreditationBean.getBusinessLicenseFlag();
			
			
			String legGuideSuggest = accreditationBean.getLegGuideSuggest();
			
			
			
			
			if (outCome.equals("给予指导通过")&&(legGuideSuggest==null||"".equals(legGuideSuggest))) {
				
				
				if(accreditation.getUnitName()==null||"".equals(accreditation.getUnitName())){
					businessLicenseFlag = true;
				}
				if(!(idCardFlag&&enforceCardFlag&&orderChangeNoticeFlag&&recordInquestFlag&&sitePhotosFlag&&recordInvFlag&&recordPaperFlag&&businessLicenseFlag)){
					System.out.println("指导通过时必须通过中队上传的所有文书");
					try {
						throw new Exception();
					} catch (Exception e) {
						
						e.printStackTrace();
					}
					return;
				}
			
				
				variables.put("accreditationSubmit", "true");
				accreditation.setLegGuideSuggest(null);
			} else if(outCome.equals("给予指导不通过")&&legGuideSuggest!=null&&!"".equals(legGuideSuggest)) {
				accreditation.setLegGuideSuggest(accreditationBean
						.getLegGuideSuggest());
			}else{
				try {
					throw new Exception();
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				return;
			}
			accreditation.setIdCardFlag(idCardFlag);
			accreditation.setEnforceCardFlag(enforceCardFlag);
			accreditation.setOrderChangeNoticeFlag(orderChangeNoticeFlag);
			accreditation.setRecordInquestFlag(recordInquestFlag);
			accreditation.setSitePhotosFlag(sitePhotosFlag);
			accreditation.setRecordInvFlag(recordInvFlag);
			accreditation.setRecordPaperFlag(recordPaperFlag);
			accreditation.setBusinessLicenseFlag(businessLicenseFlag);
			
			
			updateAccreditation(accreditation);
			taskService.complete(task.getId(), variables);
		}

	}

	@Override
	public void fillAccreitationBean(AccreditationBean accreditationBean,
			Accreditation accreditation) {

		// 设置回显使用的裁量阶次id和案件来源id
		CaseSource caseSource = accreditation.getCaseSource();
		accreditationBean.setCaseSourceName(caseSource.getName());
		accreditationBean.setCaseSourceId(caseSource.getId());

		accreditationBean.setSummaryId(accreditation.getSummary().getId());
		// 设置回显使用的主办协办id
		accreditationBean.setSponsorId(accreditation.getSponsor().getId());
		accreditationBean.setAssistantHandleId(accreditation
				.getAssistantHandle().getId());
		// 如果是单位
		if(accreditation.getUnitName() != null && !"".equals(accreditation.getUnitName().trim())) {
			// 单位属性设置
			accreditationBean.setUnitName(accreditation.getUnitName());
			accreditationBean.setLeRepresentative(accreditation
					.getLeRepresentative());
			accreditationBean.setUnitTel(accreditation.getUnitTel());
			accreditationBean.setUnitAddress(accreditation.getUnitAddress());

			// 个人属性设置
			accreditationBean.setPersonnelName(null);
			// 设置性别时，如果当事人是单位，则直接置为 null
			
			accreditationBean.setSex(null);
			accreditationBean.setUserAge(null);
			accreditationBean.setUserAddress(null);
			accreditationBean.setIdNumber(null);
			accreditationBean.setUserTel(null);
		} else{ // 如果是个人
			// 单位属性设置
			accreditationBean.setUnitName(null);
			accreditationBean.setLeRepresentative(null);
			accreditationBean.setUnitTel(null);
			accreditationBean.setUnitAddress(null);

			// 个人属性设置
			accreditationBean.setPersonnelName(accreditation.getPersonnelName());
			// 设置性别时，如果当事人是单位，则直接置为 null
			
			accreditationBean.setSex(accreditation.getSex());
			accreditationBean.setUserAge(accreditation.getUserAge());
			accreditationBean.setUserAddress(accreditation.getUserAddress());
			accreditationBean.setIdNumber(accreditation.getIdNumber());
			accreditationBean.setUserTel(accreditation.getUserTel());
		}
		
		
		accreditationBean.setSponsorName(accreditation.getSponsor().getName());
		accreditationBean.setAssistantHandleName(accreditation
				.getAssistantHandle().getName());
		// 样式不处理，数据库里数据是什么样式，就显示什么样式。
		//accreditationBean.setBaseCase("         " + accreditation.getBaseCase().trim());
		accreditationBean.setBaseCase(accreditation.getBaseCase());
		/*
		 * //设置主办、协办 accreditationBean.setSponsor(accreditation.getSponsor());
		 * accreditationBean
		 * .setAssistantHandle(accreditation.getAssistantHandle());
		 */

		// 设置案件来源
		accreditationBean.setCaseSource(accreditation.getCaseSource());
		// 立案时间、领导(大队长)审批时间
		accreditationBean.setBigCaptainDate(accreditation.getBigCaptainDate());
		// 主办和协办审批的时间
		accreditationBean.setHostDate(accreditation.getHostDate());
		// 中队的审批时间
		accreditationBean.setCaptainDate(accreditation.getCaptainDate());
		// 法制科的审批时间
		accreditationBean.setLegalDate(accreditation.getLegalDate());

		if(accreditation.getBigCaptainDate() != null) {
			// 格式化时间，预览报表、打印报表使用
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
			// 格式化后的立案时间、领导（大队长）审批时间
			accreditationBean.setBigCaptainDateStr(sdf.format(accreditation.getBigCaptainDate()));
			// 格式化后的主办和协办审批时间
			accreditationBean.setHostDateStr(sdf.format(accreditation.getHostDate()));
			// 格式化后的中队的审批时间
			accreditationBean.setCaptainDateStr(sdf.format(accreditation.getCaptainDate()));
			// 格式化后的法制科的审批时间
			accreditationBean.setLegalDateStr(sdf.format(accreditation.getLegalDate()));
			// 格式化时间，为了立案字
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy");
			accreditationBean.setYearNum(sdf2.format(accreditation.getBigCaptainDate()));
		}

		
		accreditationBean
				.setLegGuideSuggest(accreditation.getLegGuideSuggest());
		accreditationBean.setLegSuggest(accreditation.getLegSuggest());
		accreditationBean.setCaptainSuggest(accreditation.getCaptainSuggest());
		accreditationBean.setBigCaptainSuggest(accreditation
				.getBigCaptainSuggest());

		accreditationBean.setAccreditationId(accreditation.getId());

		accreditationBean.setChiefFlag(accreditation.getChiefFlag());
		accreditationBean.setLegalFlag(accreditation.getLegalFlag());
		accreditationBean.setCaptainFlag(accreditation.getCaptainFlag());
		accreditationBean.setJoinFlag(accreditation.getJoinFlag());
		accreditationBean.setHostFlag(accreditation.getHostFlag());
		accreditationBean.setBigCaptainSuggest(accreditation
				.getBigCaptainSuggest());

		accreditationBean.setCaptainName(accreditation.getCaptainName());
		accreditationBean.setLegName(accreditation.getLegalName());
		accreditationBean.setBigCaptainName(accreditation.getBigCaptainName());
		
		// 设置各种审批书
		accreditationBean.setPNotice(accreditation.getpNotice());
		accreditationBean.setPTable(accreditation.getpTable());
		accreditationBean.setPDecide(accreditation.getpDecide());
		accreditationBean.setPClosingReport(accreditation.getpClosingReport());
	}

	@Override
	public void checkIsTransactor(String taskId) {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String taskName = task.getName();
		if (taskName.indexOf("办") != -1) {
			ValueContext.putValueContext("transactorSign", "true");
		}

	}

	@Override
	public String getCurrentSign(String taskId) {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String taskName = task.getName();

		if (taskName.indexOf("中队长审批") != -1) {
			return "中队长审批";
		} else if (taskName.indexOf("法制科审批") != -1) {
			return "法制科审批";
		} else if (taskName.indexOf("大队长审批") != -1) {
			return "大队长审批";
		} else if (taskName.indexOf("法制科指导") != -1) {
			return "法制科指导";
		} else if (taskName.indexOf("主办审批") != -1) {
			return "主办审批";
		} else if (taskName.indexOf("协办审批") != -1) {
			return "协办审批";
		}
		return "发生未知错误";
	}
	//ajax根据文书名称得到文书
	@Override
	public Set<Document> getDocumentByName(AccreditationBean accreditationBean) {
		Accreditation accreditation = findAccreditationByAccreditationId(accreditationBean.getAccreditationId());
		String documentName = accreditationBean.getDocumentName();
		String methodName = "get"+documentName;
		Set<Document> documents = new HashSet<Document>();
		try{
			Method m = Accreditation.class.getDeclaredMethod(methodName);
			
			if(documentName!=null&&("IdCard".equals(documentName)||"BusinessLicense".equals(documentName))){
				Document idCard = (Document) m.invoke(accreditation);
				System.out.println(idCard.getId());
				System.out.println(idCard.getAccreditation().getId());
				documents.add(idCard);
			}else{
				 
				documents.addAll((Set<Document>)m.invoke(accreditation));
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return documents;
	}

	@Override
	public Boolean valiDate(AccreditationBean accreditationBean) {
		
		
		ArrayList<String> allowType = new ArrayList<String>();
		for (Object key : properties.keySet()) {
			String value = (String) properties.get(key);
			String[] values = value.split(",");
			for (String v : values) {
				allowType.add(v);
			}
		}
		
		
		if(accreditationBean.getIdCard()!=null){
			String idCardFileName = accreditationBean.getIdCardFileName();
			if (!allowType.contains(accreditationBean.getIdCardContentType().toLowerCase())
					|| !properties.keySet().contains(idCardFileName.substring(idCardFileName.lastIndexOf(".") + 1).toLowerCase())) {
				
				return false;
			}
		}
		if(accreditationBean.getUnitName()!=null&&!"".equals(accreditationBean.getUnitName().trim())){
			if(accreditationBean.getBusinessLicense()!=null){
				String businessLicenseFileName = accreditationBean.getBusinessLicenseFileName();
				if (!allowType.contains(accreditationBean.getBusinessLicenseContentType().toLowerCase())
						|| !properties.keySet().contains(businessLicenseFileName.substring(businessLicenseFileName.lastIndexOf(".") + 1).toLowerCase())) {
					
					return false;
				}
			}
		}
		if(accreditationBean.getEnforceCard()!=null){
			boolean enforceCardValidate =  aboutTaskService.childValidate(allowType,accreditationBean.getEnforceCard(),accreditationBean.getEnforceCardFileName(),accreditationBean.getEnforceCardContentType());
			if(!enforceCardValidate){
				return false;
			}
		}
		if(accreditationBean.getOrderChangeNotice()!=null){
			boolean orderChangeNoticeValidate =  aboutTaskService.childValidate(allowType,accreditationBean.getOrderChangeNotice(),accreditationBean.getOrderChangeNoticeFileName(),accreditationBean.getOrderChangeNoticeContentType());
			if(!orderChangeNoticeValidate){
				return false;
			}
		}
		if(accreditationBean.getRecordInquest()!=null){
			boolean recordInquestValidate = aboutTaskService.childValidate(allowType,accreditationBean.getRecordInquest(),accreditationBean.getRecordInquestFileName(),accreditationBean.getRecordInquestContentType());
			if(!recordInquestValidate){
				return false;
			}
		}
		if(accreditationBean.getSitePhotos()!=null){
			boolean sitePhotosValidate = aboutTaskService.childValidate(allowType,accreditationBean.getSitePhotos(),accreditationBean.getSitePhotosFileName(),accreditationBean.getSitePhotosContentType());
			if(!sitePhotosValidate){
				return false;
			}
		}
		if(accreditationBean.getRecordInv()!=null){
			boolean recordInvValidate = aboutTaskService.childValidate(allowType,accreditationBean.getRecordInv(),accreditationBean.getRecordInvFileName(),accreditationBean.getRecordInvContentType());
			if(!recordInvValidate){
				return false;
			}
		}
		if(accreditationBean.getRecordPaper()!=null){
			boolean recordPaperValidate = aboutTaskService.childValidate(allowType,accreditationBean.getRecordPaper(),accreditationBean.getRecordPaperFileName(),accreditationBean.getRecordPaperContentType());
			if(!recordPaperValidate){
				return false;
			}
		}
		
		return true;
	}
	// 汇总查询，得到 pageBean
	@Override
	public PageBean getPageBean(int pageNum, int pageSize,
			QueryHelper queryHelper) {
		return accreditationDao.getPageBean(pageNum, pageSize, queryHelper);
	}
	/***
	 * 向汇总查询的所有结果查询出来
	 */
	@Override
	public PageBean getAllResult(QueryHelper queryHelper) {
		return accreditationDao.getAllResult(queryHelper);
	}

	
	
	
}
