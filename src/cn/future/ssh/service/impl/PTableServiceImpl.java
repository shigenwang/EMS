package cn.future.ssh.service.impl;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import cn.future.ssh.dao.PTableDao;
import cn.future.ssh.domain.Accreditation;
import cn.future.ssh.domain.Document;
import cn.future.ssh.domain.PTable;
import cn.future.ssh.domain.Summary;
import cn.future.ssh.service.AboutTaskService;
import cn.future.ssh.service.AccreditationService;
import cn.future.ssh.service.DocumentService;
import cn.future.ssh.service.PTableService;
import cn.future.ssh.utils.AccountChange;
import cn.future.ssh.utils.SessionContext;
import cn.future.ssh.utils.ValueContext;
import cn.future.ssh.web.form.AccreditationBean;
import cn.future.ssh.web.form.PTableBean;



public class PTableServiceImpl implements PTableService {
	
	
	
	private PTableDao pTableDao;

	private AboutTaskService aboutTaskService;
	
	
	public void setAboutTaskService(AboutTaskService aboutTaskService) {
		this.aboutTaskService = aboutTaskService;
	}
	
	private TaskService taskService;
	private DocumentService documentService;
	private RuntimeService runtimeService;
	
	private AccreditationService accreditationService;
	private static Properties properties = new Properties();

	static {
		try {
			properties.load(AccreditationServiceImpl.class.getClassLoader()
					.getResourceAsStream("uploadFileType.properties"));
		} catch (IOException e) {

			e.printStackTrace();
		}
	}


	


	public void setDocumentService(DocumentService documentService) {
		this.documentService = documentService;
	}





	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}


	


	public void setRuntimeService(RuntimeService runtimeService) {
		this.runtimeService = runtimeService;
	}


	


	public void setAccreditationService(AccreditationService accreditationService) {
		this.accreditationService = accreditationService;
	}


	public void setpTableDao(PTableDao pTableDao) {
		this.pTableDao = pTableDao;
	}
	public void saveDocument(PTable pTable,PTableBean pTableBean,String methodName,List<String> allowType,File imagesSavedir){
		//通过反射得到立案审批表对象的文书的方法
		String filesMethodStr = "get"+methodName;
		String fileNameMethodStr = "get"+methodName+"FileName";
		String contentTypeMethodStr = "get"+methodName+"ContentType";
		
		Class accreditationBeanClass = pTableBean.getClass();
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
			
			files = (List<File>) filesMethod.invoke(pTableBean);
			fileNames= (List<String>) fileNameMethod.invoke(pTableBean);
			contentTypes = (List<String>) contentTypeMethod.invoke(pTableBean);
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
							document.setAccreditation(pTable.getAccreditation());
							//保存新建对象，以便产生id
							documentService.saveDocument(document);
							
							File outputDir = new File(imagesSavedir, imageName);
							FileInputStream is;
							try {
								FileOutputStream os = new FileOutputStream(outputDir);
								is = new FileInputStream(files.get(i));
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
							
								Class pTableBeanClass = pTable.getClass();
								try {
									Method m = pTableBeanClass.getDeclaredMethod(filesMethodStr);
									Set<Document> documents2 = (Set<Document>) m.invoke(pTable);
									
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
	 * 保存（更改）处罚事先通知书
	 */
	@Override
	public void submitPTable(PTableBean pTableBean) {
		PTable pTable = null;
		if(pTableBean.getPTableId()!=null){
			pTable = pTableDao.findPTableById(pTableBean.getPTableId());
		}
		String imagePath =  "e:"+ServletActionContext.getRequest().getContextPath()+"/document/"+pTable.getAccreditation().getId();
		File imagesSavedir = new File(imagePath);
		if (!imagesSavedir.exists()) {
			imagesSavedir.mkdirs();
		}
		
	
		
		ArrayList<String> allowType = new ArrayList<String>();
		for (Object key : properties.keySet()) {
			String value = (String) properties.get(key);
			String[] values = value.split(",");
			for (String v : values) {
				allowType.add(v);
			}
		}
		saveDocument(pTable, pTableBean,"ProofServicePT",allowType, imagesSavedir);
		saveDocument(pTable, pTableBean,"FinalReport",allowType, imagesSavedir);
		saveDocument(pTable, pTableBean,"RecordSheet",allowType, imagesSavedir);
		
		if(pTable!=null){
			
			/**
			 * 将pTableBean中的数据保存到pNotice中
			 */
			pTable.setClient(pTableBean.getClient());
			pTable.setBasicCase(pTableBean.getBasicCase());
			pTable.setLegalBasis(pTableBean.getLegalBasis());
			pTable.setIllegalGrade(pTableBean.getIllegalGrade());
			//更新对象
			pTableDao.updatePTable(pTable);
		}
		Map<String,Object> variables = new HashMap<String,Object>();
		variables.put("outCome", pTableBean.getOutCome());
		taskService.complete(pTableBean.getTaskId(),variables);
		
	}
	/**
	 * 通过任务id查找处罚事先告知书
	 */
	@Override
	public PTable findPTableByTaskId(String taskId) {
		
		Task task = taskService.createTaskQuery()
								.taskId(taskId)
								.singleResult();
		
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
														.processInstanceId(task.getProcessInstanceId())
														.singleResult();
		String businessKey = processInstance.getBusinessKey();
		Long accreditationId = null;
		if(StringUtils.isNotBlank(businessKey)){
			 accreditationId = Long.parseLong(businessKey.split("\\.")[1]);
		}
		Accreditation accreditation = accreditationService.findAccreditationByAccreditationId(accreditationId);
		PTable pTable = accreditation.getpTable();
		
		if(pTable==null){
			pTable = new PTable();
			pTable.setSummary(accreditation.getSummary());
			pTableDao.savePTable(pTable);
			
			pTable.setAccreditation(accreditation);
			accreditation.setpTable(pTable);
			pTableDao.updatePTable(pTable);
			accreditationService.updateAccreditation(accreditation);
		}
		String fines = accreditation.getpNotice().getFines();
		if(fines!=null&&!"".equals(fines)){
			String strFines = AccountChange.digitUppercase(Double.valueOf(fines));
			ValueContext.putValueContext("strFines", strFines);
		}
		return pTable;
	}



	



	@Override
	public void saveSubmitTask(PTableBean pTableBean) {
		String taskId = pTableBean.getTaskId();
		
		
		//获取连线的名称
		String outCome = pTableBean.getOutCome();
	
		Map<String,Object> variables = new HashMap<String,Object>();
		
		
		//完成任务的同时设置流程变量，指定下一个任务节点
		
		variables.put("outCome", outCome);
		
		variables.put("caseLevel", pTableBean.getCaseLevel());
		
		PTable pTable = this.findPTableByTaskId(taskId);
		
		Boolean proofServicePTFlag = pTableBean.getProofServicePTFlag();
		Boolean finalReportFlag = pTableBean.getFinalReportFlag();
		Boolean recordSheetFlag = pTableBean.getRecordSheetFlag();
		
		String caseLevel = pTableBean.getCaseLevel();
		if(caseLevel!=null&&!"".equals(caseLevel)){
			pTable.setCaseLevel(pTableBean.getCaseLevel());
		}
		
		
		
		if(outCome.equals("驳回")&&
				(
					    (pTableBean.getCaptainSuggest()!=null&&!"".equals(pTableBean.getCaptainSuggest().trim()))
					  ||(pTableBean.getLegSuggest()!=null&&!"".equals(pTableBean.getLegSuggest().trim()))
					  ||(pTableBean.getBigCaptainSuggest()!=null&&!"".equals(pTableBean.getBigCaptainSuggest().trim()))
					  ||(pTableBean.getICSuggest()!=null&&!"".equals(pTableBean.getICSuggest().trim()))
					  ||(pTableBean.getCRCSuggest()!=null&&!"".equals(pTableBean.getCRCSuggest().trim()))
					)
				)
		{
			if(pTableBean.getCurrentSign()!=null){
				if(pTableBean.getCurrentSign().equals("中队长审批")){
					pTable.setCaptainSuggest(pTableBean.getCaptainSuggest());
					pTable.setCaptainFlag(false);
					pTable.setJoinFlag(false);
					pTable.setHostFlag(false);
					
				}else if(pTableBean.getCurrentSign().equals("法制科审批")){
					
					pTable.setLegSuggest(pTableBean.getLegSuggest());
					
					pTable.setLegalFlag(false);
					pTable.setCaptainFlag(false);
					pTable.setJoinFlag(false);
					pTable.setHostFlag(false);
					pTable.setICOrCRCDecide(pTableBean.getICOrCRCDecide());
				}else if(pTableBean.getCurrentSign().equals("大队长审批")){
					pTable.setBigCaptainSuggest(pTableBean.getBigCaptainSuggest());
					
					pTable.setLegalFlag(false);
					pTable.setCaptainFlag(false);
					pTable.setJoinFlag(false);
					pTable.setHostFlag(false);
					
				}else if(pTableBean.getCurrentSign().equals("业委会集体签字")){
					pTable.setICSuggest(pTableBean.getICSuggest());
					pTable.setIndCommNames(null);
					
					pTable.setLegalFlag(false);
					
				}else if(pTableBean.getCurrentSign().equals("案审委集体签字")){
					pTable.setCRCSuggest(pTableBean.getCRCSuggest());
					pTable.setCaseRevCommNames(null);
					pTable.setLegalFlag(false);
					
				}
			}
			
		}else if(outCome.equals("批准")
				&&
				!(
						   (pTableBean.getCaptainSuggest()!=null&&!"".equals(pTableBean.getCaptainSuggest().trim()))
							  ||(pTableBean.getLegSuggest()!=null&&!"".equals(pTableBean.getLegSuggest().trim()))
							  ||(pTableBean.getBigCaptainSuggest()!=null&&!"".equals(pTableBean.getBigCaptainSuggest().trim()))
							  ||(pTableBean.getICSuggest()!=null&&!"".equals(pTableBean.getICSuggest().trim()))
							  ||(pTableBean.getCRCSuggest()!=null&&!"".equals(pTableBean.getCRCSuggest().trim()))
				)
				){
			
			if(proofServicePTFlag!=null&&finalReportFlag!=null&&recordSheetFlag!=null){
				if(!(proofServicePTFlag&&finalReportFlag&&recordSheetFlag)){
					System.out.println("批准时必须对所有的文书进行通过");
					try {
						throw new Exception();
					} catch (Exception e) {
						
						e.printStackTrace();
					}
					return;
				}
			}
		
			if(pTableBean.getCurrentSign()!=null){
				String loaderName = SessionContext.get().getName();
				if(pTableBean.getCurrentSign().equals("中队长审批")){
					pTable.setCaptainFlag(true);
					pTable.setCaptainSuggest(null);
					pTable.setCaptainName(loaderName);
					pTable.setCaptainDate(new Date());
				}else if(pTableBean.getCurrentSign().equals("法制科审批")){
					
					//法制科选择一般案件时需要将业委会或者案审委的意见变为空
					if(pTableBean.getCaseLevel()!=null&&"一般".equals(pTableBean.getCaseLevel())){
						pTable.setICSuggest(null);
						pTable.setCRCSuggest(null);
						
					}
					
					//法制科选择重大或特大案件时需要将大队长意见变为空
					if(pTableBean.getCaseLevel()!=null&&("重大".equals(pTableBean.getCaseLevel())||"特大".equals(pTableBean.getCaseLevel()))){
						pTable.setBigCaptainSuggest(null);
						String factAndSuggestion = pTableBean.getFactAndSuggestion();
						if(factAndSuggestion!=null&&!"".equals(factAndSuggestion.trim())){
							pTable.setFactAndSuggestion(factAndSuggestion);
						}
					
					}
					pTable.setLegalFlag(true);
					pTable.setLegSuggest(null);
					pTable.setLegalName(loaderName);
					pTable.setLegalDate(new Date());
					pTable.setICOrCRCDecide(pTableBean.getICOrCRCDecide());
				}else if(pTableBean.getCurrentSign().equals("大队长审批")){
					pTable.setChiefFlag(true);
					pTable.setBigCaptainSuggest(null);
					pTable.setBigCaptainName(loaderName);
					pTable.setBigCaptainDate(new Date());
				}else if(pTableBean.getCurrentSign().equals("业委会集体签字")){
					int nrOfCompletedInstances = (int) taskService.getVariable(taskId, "nrOfCompletedInstances");
					int nrOfInstances = (int)taskService.getVariable(taskId, "nrOfInstances");
					if(nrOfInstances-1==nrOfCompletedInstances){
						pTable.setICSuggest(null);
						pTable.setIndCommFlag(true);
						
					}
					String indCommNames = pTable.getIndCommNames();
					if(indCommNames==null){
						indCommNames = "";
					}
					pTable.setIndCommNames(indCommNames + "  "+loaderName);
				}else if(pTableBean.getCurrentSign().equals("案审委集体签字")){
					int nrOfCompletedInstances = (int) taskService.getVariable(taskId, "nrOfCompletedInstances");
					int nrOfInstances = (int)taskService.getVariable(taskId, "nrOfInstances");
					if(nrOfInstances-1==nrOfCompletedInstances){
						pTable.setCRCSuggest(null);
						pTable.setCaseRevCommFlag(true);
					}
					
					String caseRevCommNames = pTable.getCaseRevCommNames();
					if(caseRevCommNames==null){
						caseRevCommNames = "";
					}
					pTable.setCaseRevCommNames(caseRevCommNames + "  "+loaderName);
					
				}else if(pTableBean.getCurrentSign().equals("主办审批")){
					if(pTable.getJoinFlag()!=null&&pTable.getJoinFlag()){
						pTable.setHostDate(new Date());
					}
					pTable.setHostFlag(true);
				}else if(pTableBean.getCurrentSign().equals("协办审批")){
					if(pTable.getHostFlag()!=null&&pTable.getHostFlag()){
						pTable.setHostDate(new Date());
					}
					pTable.setJoinFlag(true);
				}
			}
			
		}else{
			System.out.println("批准时不能填写意见，驳回时必须填写意见");
			try {
				throw new Exception();
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			return;
		}
		if(!pTableBean.getCurrentSign().equals("案审委集体签字")&&!pTableBean.getCurrentSign().equals("业委会集体签字")){
			pTable.setRecordSheetFlag(recordSheetFlag);
			pTable.setFinalReportFlag(finalReportFlag);
			pTable.setProofServicePTFlag(proofServicePTFlag);
		}
		pTableDao.updatePTable(pTable);
		taskService.complete(taskId, variables);
		
		
		
	}


	/**
	 * 查找最新的签章任务
	 */
	@Override
	public Task findLastTask() {
			return taskService.createTaskQuery()
				.taskAssignee(Long.toString(SessionContext.get().getId()))
				.orderByTaskCreateTime()
				.desc()
				.list()
				.get(0);
	}





	




	@Override
	public void guide(PTableBean pTableBean) {
		Task task = taskService.createTaskQuery()
				.taskId(pTableBean.getTaskId())
				.singleResult();
			if(task!=null){
				Map<String,Object> variables = new HashMap<String,Object>();
				PTable pTable = findPTableByTaskId(pTableBean.getTaskId());
				
				Boolean proofServicePTFlag = pTableBean.getProofServicePTFlag();
				Boolean finalReportFlag = pTableBean.getFinalReportFlag();
				Boolean recordSheetFlag = pTableBean.getRecordSheetFlag();
				
				String outCome = pTableBean.getOutCome();
				
				String legGuideSuggest = pTableBean.getLegGuideSuggest();
				if (outCome.equals("给予指导通过")&&(legGuideSuggest==null||"".equals(legGuideSuggest))){
					
					if(!(proofServicePTFlag&&proofServicePTFlag!=null&&finalReportFlag!=null&&finalReportFlag&&recordSheetFlag!=null&&recordSheetFlag)){
						System.out.println("指导通过时必须对所有的文书进行通过");
						try {
							throw new Exception();
						} catch (Exception e) {
							
							e.printStackTrace();
						}
						return;
					}
					variables.put("pTableSubmit", "true");
					pTable.setLegGuideSuggest(null);
				} else if(outCome.equals("给予指导不通过")&&legGuideSuggest!=null&&!"".equals(legGuideSuggest)){
					
					pTable.setLegGuideSuggest(pTableBean.getLegGuideSuggest());
				}else{
					System.out.println("批准时不能填写意见，驳回时必须填写意见");
					try {
						throw new Exception();
					} catch (Exception e) {
						
						e.printStackTrace();
					}
					return;
				}
				pTable.setProofServicePTFlag(pTableBean.getProofServicePTFlag());
				pTable.setFinalReportFlag(pTableBean.getFinalReportFlag());
				pTable.setRecordSheetFlag(pTableBean.getRecordSheetFlag());
				pTableDao.updatePTable(pTable);
				taskService.complete(task.getId(),variables);
			}

}





	@Override
	public String getOutComeByTaskId(String taskId) {
		String pTableSubmit = (String) taskService.getVariable(taskId, "pTableSubmit");
		if(pTableSubmit.equals("false")){
			return "向法制科申请指导";
		}else{
			
				return "提交立案申请表";
	
		}
	}





	@Override
	public void savePTable(PTable pTable) {
		pTableDao.savePTable(pTable);
		
	}




	/**
	 * 从数据库里取出数据，然后进行拼凑pTableBean
	 */
	@Override
	public void fillPTable(PTableBean pTableBean, PTable pTable) {
		
		
		
		Accreditation accreditation =  pTable.getAccreditation();
		String basicCase = accreditation.getpNotice().getIllegalContent();
		
		String client = null;
		String personalName = pTable.getAccreditation().getPersonnelName();
		if(personalName!=null&&!"".equals(personalName)){
			client = personalName;
		}else{
			client = pTable.getAccreditation().getUnitName();
		}
		
		pTable.setClient(client);
		if(pTable.getBasicCase()==null||"".equals(pTable.getBasicCase().trim())){
			
			
			
				
				basicCase = basicCase.substring(basicCase.indexOf("于"));
		
				
			
		
			
		    basicCase = "     "+client+basicCase+"\n";
			
		    pTable.setBasicCase(basicCase);
			
			
			
			
			
		}
		if(pTable.getLegalBasis()==null||"".equals(pTable.getLegalBasis().trim())){
			Summary summary = pTable.getAccreditation().getSummary();
			String legalBasis = "上述行为违反了"+summary.getVioRegulations()+"依据"+summary.getLegalBasis();
			pTable.setLegalBasis(legalBasis);
		}
		

	
		
		
	}
	/**
	 * 根据pTable 拼凑pTableBean, 汇总查询时使用
	 */
	public void getFillPTableforQuery(PTableBean pTableBean, PTable pTable) {
		if(pTable != null) {
			// 设置案由
			pTableBean.setSummaryName(pTable.getSummary().getName());
			// 设置承办中队
			if(pTable.getAccreditation() != null) {
				pTableBean.setSquadronName(pTable.getAccreditation().getSquadron().getName());
				// 设置承办人
				pTableBean.setSponsorName(pTable.getAccreditation().getSponsor().getName());
				pTableBean.setAssistantHandleName(pTable.getAccreditation().getAssistantHandle().getName());
			}
			// 设置事实和处理意见
			if(pTable.getFactAndSuggestion() != null){
				pTableBean.setFactAndSuggestion("         " + pTable.getFactAndSuggestion().trim());
			}
			// 设置业委会或案审委决定
			pTableBean.setICOrCRCDecide(pTable.getICOrCRCDecide());
			// 设置业委会成员姓名
			pTableBean.setIndCommNames(pTable.getIndCommNames());
			// 设置案审委成员姓名
			pTableBean.setCaseRevCommNames(pTable.getCaseRevCommNames());
			
			String client = null; // 当事人
			String personalName = pTable.getAccreditation().getPersonnelName();
			if(personalName!=null&&!"".equals(personalName)){
				client = personalName;
			}else{
				client = pTable.getAccreditation().getUnitName();
			}
			pTableBean.setClient(client);  // 设置当事人
			pTableBean.setBasicCase(pTable.getBasicCase()); //设置基本案情
			pTableBean.setIllegalGrade(pTable.getIllegalGrade()); // 设置违法行为等次
			pTableBean.setLegalBasis(pTable.getLegalBasis()); // 设置法律依据
			
			// 设置各种意见
			if(pTable.getAccreditation() != null) {
				if(pTable.getAccreditation().getpNotice() != null) {
					if(pTable.getAccreditation().getpNotice().getFines() != null) {
						pTableBean.setHostSuggest("建议对当事人罚款" +AccountChange.digitUppercase(Double.parseDouble(pTable.getAccreditation().getpNotice().getFines()))); // 主办和协办建议
					}
				}
			}
			pTableBean.setCaptainSuggest(pTable.getCaptainSuggest()); //中队长意见
			pTableBean.setLegSuggest(pTable.getLegSuggest()); //法制科意见
			pTableBean.setBigCaptainSuggest(pTable.getBigCaptainSuggest()); //大队长意见（主管领导）
			pTableBean.setDepLeaderSuggest(pTable.getDepLeaderSuggest()); //部门领导建议
			pTableBean.setICSuggest(pTable.getICSuggest()); //业委会意见
			pTableBean.setCRCSuggest(pTable.getCRCSuggest()); //案审委意见
			if(pTable.getFactAndSuggestion() == null || "".equals(pTable.getFactAndSuggestion().trim())) { // 事实和处理意见，如果数据源为jsp来的数据，则直接获取即可
				
			} else{
				pTableBean.setFactAndSuggestion(pTable.getFactAndSuggestion()); //事实和处理意见 
			}
			pTableBean.setICOrCRCDecide(pTable.getICOrCRCDecide()); //业委会或案审委集体决定
			pTableBean.setLegGuideSuggest(pTable.getLegGuideSuggest()); //法制科指导意见
			
			// 设置各种名字
			pTableBean.setCaptainName(pTable.getCaptainName()); //中队长姓名
			pTableBean.setLegalName(pTable.getLegalName()); //法制科姓名
			pTableBean.setBigCaptainName(pTable.getBigCaptainName()); //大队长姓名
			pTableBean.setDepLeaderName(pTable.getDepLeaderName()); //部门领导姓名
			pTableBean.setIndCommNames(pTable.getIndCommNames()); //业委会所有审批人员的姓名
			pTableBean.setCaseRevCommNames(pTable.getCaseRevCommNames()); // 案审委所有审批人员的姓名
			
			if("一般".equals(pTable.getCaseLevel())){ // 根据案件类型不一样，流程不一样。如果为一般，则流程进行到最后。
				// 设置各种审核时间(格式化后的时间)
				if(pTable.getBigCaptainDate() != null) { // 首先必须保证数据库里的时间都有
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
					pTableBean.setHostDateStr(sdf.format(pTable.getHostDate()));     // 主办和协办审批时间
					pTableBean.setCaptainDateStr(sdf.format(pTable.getCaptainDate())); // 中队的审批时间
					pTableBean.setLegalDateStr(sdf.format(pTable.getLegalDate()));  // 法制科的审批时间
					pTableBean.setBigCaptainDateStr(sdf.format(pTable.getBigCaptainDate())); // 大队长审批时间（主管领导）
				}
			} else{ // 如果案件到
				// 设置各种审核时间(格式化后的时间)
				if(pTable.getLegalDate() != null) { // 首先必须保证数据库里的时间都有
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
					pTableBean.setHostDateStr(sdf.format(pTable.getHostDate()));     // 主办和协办审批时间
					pTableBean.setCaptainDateStr(sdf.format(pTable.getCaptainDate())); // 中队的审批时间
					pTableBean.setLegalDateStr(sdf.format(pTable.getLegalDate()));  // 法制科的审批时间
				}
			}
		}
	}
	@Override
	public PTable findPTableById(String id) {
		return pTableDao.findPTableById(Long.parseLong(id));
	}





	@Override
	public String getCurrentSign(String taskId) {
		Task task = taskService.createTaskQuery()
				.taskId(taskId)
				.singleResult();
		String taskName = task.getName();
		
	    if(taskName.indexOf("中队长审批")!=-1){
			return "中队长审批";
		}else if(taskName.indexOf("法制科审批")!=-1){
			return "法制科审批";
		}else if(taskName.indexOf("大队长审批")!=-1){
			return "大队长审批";
		}else if(taskName.indexOf("法制科指导")!=-1){
			return "法制科指导";
		}else if(taskName.indexOf("业委会集体签字")!=-1){
			PTable pTable = this.findPTableByTaskId(taskId);
			pTable.setCRCSuggest(null);
			pTableDao.updatePTable(pTable);
			return "业委会集体签字";
		}else if(taskName.indexOf("案审委集体签字")!=-1){
			PTable pTable = this.findPTableByTaskId(taskId);
			pTable.setICSuggest(null);
			pTableDao.updatePTable(pTable);
			return "案审委集体签字";
		}else if(taskName.indexOf("主办审批")!=-1){
			return "主办审批";
		}else if(taskName.indexOf("协办审批")!=-1){
			return "协办审批";
		}
		return "发生未知错误";
	}





	@Override
	public Boolean valiDate(PTableBean pTableBean) {		
		
		ArrayList<String> allowType = new ArrayList<String>();
		for (Object key : properties.keySet()) {
			String value = (String) properties.get(key);
			String[] values = value.split(",");
			for (String v : values) {
				allowType.add(v);
			}
		}
		
		
		
		if(pTableBean.getProofServicePT()!=null){
			boolean proofServicePTValidate =  aboutTaskService.childValidate(allowType,pTableBean.getProofServicePT(),pTableBean.getProofServicePTFileName(),pTableBean.getProofServicePTContentType());
			if(!proofServicePTValidate){
				return false;
			}
		}
		if(pTableBean.getRecordSheet()!=null){
			boolean recordSheetValidate =  aboutTaskService.childValidate(allowType,pTableBean.getRecordSheet(),pTableBean.getRecordSheetFileName(),pTableBean.getRecordSheetContentType());
			if(!recordSheetValidate){
				return false;
			}
		}
		if(pTableBean.getFinalReport()!=null){
			boolean finalReportValidate = aboutTaskService.childValidate(allowType,pTableBean.getFinalReport(),pTableBean.getFinalReportFileName(),pTableBean.getFinalReportContentType());
			if(!finalReportValidate){
				return false;
			}
		}
		
		
		return true;
		
	}





	@Override
	public Set<Document> getDocumentByName(PTableBean pTableBean) {
		PTable pTable = findPTableById(String.valueOf(pTableBean.getPTableId()));
		String documentName = pTableBean.getDocumentName();
		String methodName = "get"+documentName;
		Set<Document> documents = new HashSet<Document>();
		try{
			Method m = PTable.class.getDeclaredMethod(methodName);
			
			documents.addAll((Set<Document>)m.invoke(pTable));

		}catch(Exception e){
			e.printStackTrace();
		}
		
		return documents;
	}
}
