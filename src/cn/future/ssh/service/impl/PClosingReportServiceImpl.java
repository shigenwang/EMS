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

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import cn.future.ssh.dao.PClosingReportDao;
import cn.future.ssh.domain.Accreditation;
import cn.future.ssh.domain.Document;
import cn.future.ssh.domain.PClosingReport;
import cn.future.ssh.domain.PNotice;
import cn.future.ssh.domain.PTable;
import cn.future.ssh.service.AboutTaskService;
import cn.future.ssh.service.AccreditationService;
import cn.future.ssh.service.DocumentService;
import cn.future.ssh.service.PClosingReportService;
import cn.future.ssh.utils.AccountChange;
import cn.future.ssh.utils.SessionContext;
import cn.future.ssh.utils.ValueContext;
import cn.future.ssh.web.form.PClosingReportBean;
import cn.future.ssh.web.form.PTableBean;




public class PClosingReportServiceImpl implements PClosingReportService {

private TaskService taskService;
	
	private RuntimeService runtimeService;
	
	private AccreditationService accreditationService;
	
	private AboutTaskService aboutTaskService;

	private static Properties properties = new Properties();
	private DocumentService documentService;
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



	public void setAboutTaskService(AboutTaskService aboutTaskService) {
		this.aboutTaskService = aboutTaskService;
	}
	private PClosingReportDao pClosingReportDao;


	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}



	public void setRuntimeService(RuntimeService runtimeService) {
		this.runtimeService = runtimeService;
	}



	public void setAccreditationService(AccreditationService accreditationService) {
		this.accreditationService = accreditationService;
	}


	public void setpClosingReportDao(PClosingReportDao pClosingReportDao) {
		this.pClosingReportDao = pClosingReportDao;
	}
	
	public void saveDocument(PClosingReport pClosingReport,PClosingReportBean pClosingReportBean,String methodName,List<String> allowType,File imagesSavedir){
		//通过反射得到立案审批表对象的文书的方法
		String filesMethodStr = "get"+methodName;
		String fileNameMethodStr = "get"+methodName+"FileName";
		String contentTypeMethodStr = "get"+methodName+"ContentType";
		
		Class accreditationBeanClass = pClosingReportBean.getClass();
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
			
			files = (List<File>) filesMethod.invoke(pClosingReportBean);
			fileNames= (List<String>) fileNameMethod.invoke(pClosingReportBean);
			contentTypes = (List<String>) contentTypeMethod.invoke(pClosingReportBean);
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
							document.setAccreditation(pClosingReport.getAccreditation());
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
							
								Class pClosingReportClass = pClosingReport.getClass();
								try {
									Method m = pClosingReportClass.getDeclaredMethod(filesMethodStr);
									Set<Document> documents2 = (Set<Document>) m.invoke(pClosingReport);
									
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
	public void submitPClosingReport(PClosingReportBean pClosingReportBean) {
		PClosingReport pClosingReport = null;
		
		if(pClosingReportBean.getPClosingReportId()!=null){
			pClosingReport = pClosingReportDao.findPClosingReportById(pClosingReportBean.getPClosingReportId());
		}
		
		String imagePath =  "e:"+ServletActionContext.getRequest().getContextPath()+"/document/"+pClosingReport.getAccreditation().getId();
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
		saveDocument(pClosingReport, pClosingReportBean,"ProofServicePC",allowType, imagesSavedir);
		saveDocument(pClosingReport, pClosingReportBean,"FineNote",allowType, imagesSavedir);
		
		
		if(pClosingReport!=null){
			
			/**
			 * 将pDecideBean中的数据保存到pDecide中
			 */
			Date paymentDate = pClosingReportBean.getPaymentDate();
			String basicCase = pClosingReportBean.getBasicCase();
			if(paymentDate!=null&&basicCase!=null&&!"".equals(basicCase.trim())){
			/*	SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
				
				basicCase = pClosingReportBean.getBasicCase()+"\n   当事人于"+sdf.format(paymentDate)+"缴纳了罚款，现已执行到位。";*/
				pClosingReport.setBasicCase(basicCase);
				pClosingReport.setPaymentDate(paymentDate);
			}
			if((pClosingReportBean.getPaymentDateSign()==null||"".equals(pClosingReportBean.getPaymentDateSign().trim()))&&basicCase!=null&&!"".equals(basicCase.trim())){
				pClosingReport.setBasicCase(basicCase);
			}
			if(basicCase==null||"".equals(basicCase.trim())){
				//出错处理
			}
			
			
			//更新对象
			pClosingReportDao.updatePClosingReport(pClosingReport);
		}
		Map<String,Object> variables = new HashMap<String,Object>();
		variables.put("outCome", pClosingReportBean.getOutCome());
		
		taskService.complete(pClosingReportBean.getTaskId(),variables);
		
	}
	/**
	 * 通过任务id查找处罚事先告知书
	 */
	@Override
	public PClosingReport findPClosingReportByTaskId(String taskId) {
		
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
		PClosingReport pClosingReport = accreditation.getpClosingReport();
		
		if(pClosingReport==null){
			pClosingReport = new PClosingReport();
			pClosingReport.setSummary(accreditation.getSummary());
		
			pClosingReportDao.savePClosingReport(pClosingReport);
			
			pClosingReport.setAccreditation(accreditation);
			accreditation.setpClosingReport(pClosingReport);
			pClosingReportDao.updatePClosingReport(pClosingReport);
			
			accreditationService.updateAccreditation(accreditation);
		}
		return pClosingReport;
	}



	



	@Override
	public void saveSubmitTask(PClosingReportBean pClosingReportBean) {
		String taskId = pClosingReportBean.getTaskId();
		
		
		//获取连线的名称
		String outCome = pClosingReportBean.getOutCome();
		/**
		 * 添加批注的时候，由于Activiti底层是使用：
		 *  String userId = Authentication.getAuthenticatedUserId();
           CommentEntity comment = new CommentEntity();
           comment.setUserId(userId);
           
           	所需要从Session中获取当前登陆人，作为该任务的办理人，对应act_hi_comment表中的USER_ID字段，不过不添加审核人，该字段为空
		 * */
		
	/*	Authentication.setAuthenticatedUserId(SessionContext.get().getName());
		taskService.addComment(taskId,processInstanceId,workflowBean.getComment());*/
		Map<String,Object> variables = new HashMap<String,Object>();
		
		
		//完成任务的同时设置流程变量，指定下一个任务节点
		
		variables.put("outCome", outCome);
		
		PClosingReport pClosingReport = this.findPClosingReportByTaskId(taskId);
		

		Boolean proofServicePCFlag = pClosingReportBean.getProofServicePCFlag();
		Boolean fineNoteFlag = pClosingReportBean.getFineNoteFlag();
		
		if(outCome.equals("驳回")&&
				(
					    (pClosingReportBean.getCaptainSuggest()!=null&&!"".equals(pClosingReportBean.getCaptainSuggest().trim()))
					  ||(pClosingReportBean.getLegSuggest()!=null&&!"".equals(pClosingReportBean.getLegSuggest().trim()))
					  ||(pClosingReportBean.getBigCaptainSuggest()!=null&&!"".equals(pClosingReportBean.getBigCaptainSuggest().trim()))
					  ||(pClosingReportBean.getICSuggest()!=null&&!"".equals(pClosingReportBean.getICSuggest().trim()))
					  
					)
				)
		{
			if(pClosingReportBean.getCurrentSign()!=null){
				if(pClosingReportBean.getCurrentSign().equals("中队长审批")){
					pClosingReport.setCaptainSuggest(pClosingReportBean.getCaptainSuggest());
					pClosingReport.setJoinFlag(false);
					pClosingReport.setHostFlag(false);
				}else if(pClosingReportBean.getCurrentSign().equals("法制科审批")){
					pClosingReport.setLegSuggest(pClosingReportBean.getLegSuggest());
					pClosingReport.setCaptainFlag(false);
					pClosingReport.setJoinFlag(false);
					pClosingReport.setHostFlag(false);
				}else if(pClosingReportBean.getCurrentSign().equals("大队长审批")){
					pClosingReport.setBigCaptainSuggest(pClosingReportBean.getBigCaptainSuggest());
					pClosingReport.setLegalFlag(false);
					pClosingReport.setCaptainFlag(false);
					pClosingReport.setJoinFlag(false);
					pClosingReport.setHostFlag(false);
				}
			}
			
		}else if(outCome.equals("批准")
				&&
				!(
						   (pClosingReportBean.getCaptainSuggest()!=null&&!"".equals(pClosingReportBean.getCaptainSuggest().trim()))
							  ||(pClosingReportBean.getLegSuggest()!=null&&!"".equals(pClosingReportBean.getLegSuggest().trim()))
							  ||(pClosingReportBean.getBigCaptainSuggest()!=null&&!"".equals(pClosingReportBean.getBigCaptainSuggest().trim()))
							  ||(pClosingReportBean.getICSuggest()!=null&&!"".equals(pClosingReportBean.getICSuggest().trim()))
							  
				)
				){
			
			
				if(!(proofServicePCFlag&&fineNoteFlag)){
					System.out.println("批准时必须对所有的文书进行通过");
					try {
						throw new Exception();
					} catch (Exception e) {
						
						e.printStackTrace();
					}
					return;
				}
			
			
				if(pClosingReportBean.getCurrentSign()!=null){
					String loaderName = SessionContext.get().getName();
					if(pClosingReportBean.getCurrentSign().equals("中队长审批")){
						pClosingReport.setCaptainSuggest(null);
						pClosingReport.setCaptainFlag(true);
						pClosingReport.setCaptainName(loaderName);
						pClosingReport.setCaptainDate(new Date());
					}else if(pClosingReportBean.getCurrentSign().equals("法制科审批")){
						pClosingReport.setLegSuggest(null);
						pClosingReport.setLegalFlag(true);
						pClosingReport.setLegalName(loaderName);
						pClosingReport.setLegalDate(new Date());
					}else if(pClosingReportBean.getCurrentSign().equals("大队长审批")){
						pClosingReport.setBigCaptainSuggest(null);
						pClosingReport.setChiefFlag(true);
						pClosingReport.setBigCaptainName(loaderName);
						pClosingReport.setBigCaptainDate(new Date());
				}else if(pClosingReportBean.getCurrentSign().equals("主办审批")){
					if(pClosingReport.getJoinFlag()!=null&&pClosingReport.getJoinFlag()){
						pClosingReport.setHostDate(new Date());
					}
					pClosingReport.setHostFlag(true);
				}else if(pClosingReportBean.getCurrentSign().equals("协办审批")){
					if(pClosingReport.getHostFlag()!=null&&pClosingReport.getHostFlag()){
						pClosingReport.setHostDate(new Date());
					}
					pClosingReport.setJoinFlag(true);
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
		pClosingReport.setProofServicePCFlag(proofServicePCFlag);
		pClosingReport.setFineNoteFlag(fineNoteFlag);
		pClosingReportDao.updatePClosingReport(pClosingReport);
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
	public void guide(PClosingReportBean pClosingReportBean) {
		Task task = taskService.createTaskQuery()
				.taskId(pClosingReportBean.getTaskId())
				.singleResult();
			if(task!=null){
				Map<String,Object> variables = new HashMap<String,Object>();
				PClosingReport pClosingReport = findPClosingReportByTaskId(pClosingReportBean.getTaskId());
				
				Boolean proofServicePCFlag = pClosingReportBean.getProofServicePCFlag();
				Boolean fineNoteFlag = pClosingReportBean.getFineNoteFlag();
				
				String outCome = pClosingReportBean.getOutCome();
				String legGuideSuggest = pClosingReportBean.getLegGuideSuggest();

			if(outCome.equals("给予指导通过")&&(legGuideSuggest==null||"".equals(legGuideSuggest))){
				if(!(proofServicePCFlag!=null&&proofServicePCFlag&&fineNoteFlag!=null&&fineNoteFlag)){
					System.out.println("指导通过时必须对所有的文书进行通过");
					try {
						throw new Exception();
					} catch (Exception e) {
						
						e.printStackTrace();
					}
					return;
				}
				variables.put("pClosingReportSubmit", "true");
				pClosingReport.setLegGuideSuggest(null);
			}else if(outCome.equals("给予指导不通过")&&legGuideSuggest!=null&&!"".equals(legGuideSuggest)){
				pClosingReport.setLegGuideSuggest(pClosingReportBean.getLegGuideSuggest());
			}else{
				System.out.println("批准时不能填写意见，驳回时必须填写意见");
				try {
					throw new Exception();
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				return;
			}
			pClosingReport.setProofServicePCFlag(pClosingReportBean.getProofServicePCFlag());
			pClosingReport.setFineNoteFlag(pClosingReportBean.getFineNoteFlag());
			taskService.complete(task.getId(),variables);
			}

		
	}



	@Override
	public String getOutComeByTaskId(String taskId) {
		String pTableSubmit = (String) taskService.getVariable(taskId, "pClosingReportSubmit");
		if(pTableSubmit.equals("false")){
			return "向法制科申请指导";
		}else{
			return "提交立案申请表";
		}
		
	}

	/**
	 * 拼凑行政处罚结案报告 PClosingBean, 预览的时候开始使用
	 */
	public void getFillPClosingReport(PClosingReportBean pClosingReportBean, PClosingReport pClosingReport) {
		// 拼凑基本案情及执行情况
		String basicCase = pClosingReportBean.getBasicCase();
		if(basicCase != null) {
			basicCase = "         " + basicCase.trim() + "\n";
			// 日期格式化
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
			if(pClosingReportBean.getPaymentDate() != null) {
				basicCase = basicCase + "         当事人于" + sdf.format(pClosingReportBean.getPaymentDate()) + "缴纳了罚款，现已执行到位。";
			}
		}
		
		pClosingReportBean.setBasicCase(basicCase);
	}
	
	/**
	 *  为汇总查询拼凑 pclosingReportBean 用的方法
	 */
	public void getFillPClosingforQuery(PClosingReportBean pClosingReportBean, PClosingReport pClosingReport) {
		// 设置基本案情及执行情况
		String basicCase = pClosingReport.getBasicCase();
		if(basicCase != null) {
			basicCase = "         " + basicCase.trim() + "\n";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		
		if(pClosingReport.getPaymentDate() != null) {
			basicCase = basicCase + "         当事人于" + sdf.format(pClosingReport.getPaymentDate()) + "缴纳了罚款，现已执行到位。";
		}
		pClosingReportBean.setBasicCase(basicCase);
		
		if(pClosingReport != null) {
			Accreditation accreditation = pClosingReport.getAccreditation();
			pClosingReportBean.setSponsorName(accreditation.getSponsor().getName()); // 设置主办姓名
			pClosingReportBean.setAssistantHandleName(accreditation.getAssistantHandle().getName()); // 设置协办姓名
			// 设置各种姓名
			pClosingReportBean.setCaptainName(pClosingReport.getCaptainName());//中队长审批人员姓名
			pClosingReportBean.setLegalName(pClosingReport.getLegalName()); //法制科审批人员姓名
			pClosingReportBean.setBigCaptainName(pClosingReport.getBigCaptainName()); //大队长审批人员姓名
			pClosingReportBean.setIndCommName(pClosingReport.getIndCommName()); //业委会审批人员姓名
			
			// 设置各种时间(格式化后的)
			if(pClosingReport.getBigCaptainDate() != null) {
				pClosingReportBean.setHostDateStr(sdf.format(pClosingReport.getHostDate())); //主办和协办审批的时间
				pClosingReportBean.setCaptainDateStr(sdf.format(pClosingReport.getCaptainDate())); //中队的审批时间
				pClosingReportBean.setLegalDateStr(sdf.format(pClosingReport.getLegalDate())); //法制科的审批时间
				pClosingReportBean.setBigCaptainDateStr(sdf.format(pClosingReport.getBigCaptainDate())); //大队长审批时间
			}
			
			// 设置各种审核意见
			pClosingReportBean.setCaptainSuggest(pClosingReport.getCaptainSuggest()); //中队长意见
			pClosingReportBean.setLegSuggest(pClosingReport.getLegSuggest()); //法制科意见
			pClosingReportBean.setBigCaptainSuggest(pClosingReport.getBigCaptainSuggest()); //大队长意见（主管领导）
			pClosingReportBean.setICSuggest(pClosingReport.getICSuggest()); //业委会意见
		}
	}
	@Override
	public void fillPClosingReport(PClosingReportBean pClosingReportBean,
			PClosingReport pClosingReport) {
		if(pClosingReport.getBasicCase()==null||"".equals(pClosingReport.getBasicCase().trim())){
			
			/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
			ValueContext.putValueContext("paymentDate", sdf.format(pClosingReport.getPaymentDate()));*/
			Accreditation accreditation = pClosingReport.getAccreditation();
			PNotice pNotice = accreditation.getpNotice();
			String legalBasic = pClosingReport.getSummary().getLegalBasis();
			legalBasic = legalBasic.substring(0,legalBasic.indexOf("“"));
			
			String vioRegulations = pNotice.getSummary().getVioRegulations();
			vioRegulations = vioRegulations.substring(0,vioRegulations.indexOf("："));
			String basicCase = "   "+accreditation.getBaseCase()+"上述行为违反了"+vioRegulations+"之规定，依据《中原区城市管理执法局行政处罚裁量标准》之规定，"
					+ "该行为属"+pNotice.getIllegalStyles().getName()+"。依据"+legalBasic+"之规定，对当事人处以罚款"+AccountChange.digitUppercase(Double.valueOf(pNotice.getFines()))+"行政处罚。\n";
			pClosingReport.setBasicCase(basicCase);
		}
		
		
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
		}else if(taskName.indexOf("主办审批")!=-1){
			return "主办审批";
		}else if(taskName.indexOf("协办审批")!=-1){
			return "协办审批";
		}
		return "发生未知错误";
	}




	@Override
	public PClosingReport findPClosingReportById(String id) {
		return pClosingReportDao.findPClosingReportById(Long.parseLong(id));
	}



	@Override
	public Boolean valiDate(PClosingReportBean pClosingReportBean) {
		ArrayList<String> allowType = new ArrayList<String>();
		for (Object key : properties.keySet()) {
			String value = (String) properties.get(key);
			String[] values = value.split(",");
			for (String v : values) {
				allowType.add(v);
			}
		}
		
		
		
		if(pClosingReportBean.getProofServicePC()!=null){
			boolean proofServicePTValidate =  aboutTaskService.childValidate(allowType,pClosingReportBean.getProofServicePC(),pClosingReportBean.getProofServicePCFileName(),pClosingReportBean.getProofServicePCContentType());
			if(!proofServicePTValidate){
				return false;
			}
		}
		if(pClosingReportBean.getFineNote()!=null){
			boolean recordSheetValidate =  aboutTaskService.childValidate(allowType,pClosingReportBean.getFineNote(),pClosingReportBean.getFineNoteFileName(),pClosingReportBean.getFineNoteContentType());
			if(!recordSheetValidate){
				return false;
			}
		}
		
		
		
		return true;
	}



	@Override
	public Set<Document> getDocumentByName(PClosingReportBean pClosingReportBean) {
		PClosingReport pClosingReport = findPClosingReportById(String.valueOf(pClosingReportBean.getPClosingReportId()));
		String documentName = pClosingReportBean.getDocumentName();
		String methodName = "get"+documentName;
		Set<Document> documents = new HashSet<Document>();
		try{
			Method m = PClosingReport.class.getDeclaredMethod(methodName);
			
			documents.addAll((Set<Document>)m.invoke(pClosingReport));

		}catch(Exception e){
			e.printStackTrace();
		}
		
		return documents;
		
	}


}
