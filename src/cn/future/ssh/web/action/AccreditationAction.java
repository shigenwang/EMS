package cn.future.ssh.web.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import cn.future.ssh.domain.Accreditation;
import cn.future.ssh.domain.CaseSource;
import cn.future.ssh.domain.Document;
import cn.future.ssh.domain.Personnel;
import cn.future.ssh.domain.Squadron;
import cn.future.ssh.domain.Summary;
import cn.future.ssh.service.AboutTaskService;
import cn.future.ssh.service.AccreditationService;
import cn.future.ssh.service.CaseSourceService;
import cn.future.ssh.service.PersonnelService;
import cn.future.ssh.service.SummaryService;
import cn.future.ssh.utils.JasperHelper;
import cn.future.ssh.utils.SessionContext;
import cn.future.ssh.utils.ValueContext;
import cn.future.ssh.web.form.AccreditationBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AccreditationAction extends ActionSupport implements ModelDriven<AccreditationBean>{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private SummaryService summaryService;
	private AboutTaskService aboutTaskService;
	private AccreditationBean accreditationBean = new AccreditationBean();
	private PersonnelService personnelService;
	private AccreditationService accreditationService;
	private CaseSourceService caseSourceService;
	
	List<AccreditationBean> accreditationBeanList  = new ArrayList<AccreditationBean>();
	/**
	 * 为报表准备数据源
	 * @return
	 */
	public List<AccreditationBean> getAccreditationBeanList() {
		return accreditationBeanList;
	}
	public void setAccreditationBeanList(List<AccreditationBean> accreditationBeanList) {
		this.accreditationBeanList = accreditationBeanList;
	}
	public void setCaseSourceService(CaseSourceService caseSourceService) {
		this.caseSourceService = caseSourceService;
	}
	public void setSummaryService(SummaryService summaryService) {
		this.summaryService = summaryService;
	}
	public void setAboutTaskService(AboutTaskService aboutTaskService) {
		this.aboutTaskService = aboutTaskService;
	}
	public PersonnelService getPersonnelService() {
		return personnelService;
	}
	public void setPersonnelService(PersonnelService personnelService) {
		this.personnelService = personnelService;
	}
	public AccreditationService getAccreditationService() {
		return accreditationService;
	}
	public void setAccreditationService(AccreditationService accreditationService) {
		this.accreditationService = accreditationService;
	}
	
	
	@Override
	public AccreditationBean getModel() {
		
		return accreditationBean;
	}


	
	public String home(){
		return "accreditationHome";
	}
	/**
	 * 提交任务
	 */
	public String submitTask(){
		
		accreditationService.saveSubmitTask(accreditationBean);
		return "listTask";
	}
	/**
	 * 准备立案申请表表单数据
	 */
	public String audit(){
		//获取任务id
		String taskId = accreditationBean.getTaskId();
		Accreditation accreditation = null;
		//accreditationService.checkIsTransactor(taskId);
		if(taskId!=null){
			//使用任务id，查找立案审批表
			accreditation = accreditationService.findAccreditationByTaskId(taskId);
			
			
			if(accreditation!=null){
				ValueContext.putValueStack(accreditation);
				// accreditationService.fillAccreitationBean(accreditationBean,accreditation);
				 List<String> sexs = new ArrayList<String>();
					sexs.add("男");
					sexs.add("女");
				 ValueContext.putValueContext("sexs", sexs);
			}
		}	
		String currentSign = accreditationService.getCurrentSign(taskId);
		accreditation.setCurrentSign(currentSign);
		//查找所有的案件来源
		List<CaseSource> caseSources = caseSourceService.getAllCaseSource();
		ValueContext.putValueContext("caseSources", caseSources);
		//已知任务id，查询ProcessDefinitionEntity对象，从而获取当前任务完成之后的连线名称，并放置到List<String>集合中
		List<String> outcomeList = aboutTaskService.findOutComeListByTaskId(taskId);
		ValueContext.putValueContext("outcomeList", outcomeList);
		
		//得到装载类型的集合，用于每个人登录上去之后显示有哪些选择条件
		 List<String> taskTypeList = aboutTaskService.getTaskTypeSet(SessionContext.get(),(String) ActionContext.getContext().getSession().get("loaderSign"));
		 ValueContext.putValueContext("taskTypeList", taskTypeList);
		
		return "taskForm";
	}
	/**
	 * 进入立案申请表填写页面
	 * @return
	 */
	public String inputAccreditation(){
		
		Personnel personnel = SessionContext.get();
		Squadron squadron = personnel.getSquadron();
		//根据部门查找和自己属于一个中队的人
		List<Personnel> personnels = personnelService.getPersonnelBySquadron(squadron);
		ValueContext.putValueContext("personnels", personnels);
		//查找所有裁量阶次
		List<Summary> summarys = summaryService.getAllSummary();
		ValueContext.putValueContext("summarys",summarys);
		//查找所有的案件来源
		List<CaseSource> caseSources = caseSourceService.getAllCaseSource();
		ValueContext.putValueContext("caseSources", caseSources);
		//设置性别
		List<String> sexs = new ArrayList<String>();
		sexs.add("男");
		sexs.add("女");
		ValueContext.putValueContext("sexs", sexs);
		//获取任务id
		String taskId = accreditationBean.getTaskId();
		if(taskId!=null){
			//使用任务id，查找请假单id，从而获取请假单信息
			Accreditation accreditation = accreditationService.findAccreditationByTaskId(taskId);
			//ValueContext.putValueStack(accreditation);
			
			if(accreditation!=null){
				ValueContext.putValueStack(accreditation);
				//accreditationService.fillAccreitationBean(accreditationBean,accreditation);
								
				
			}
		//	ValueContext.putValueContext("summaryId", accreditation.getSummary().getId());
		}
		String outCome = accreditationService.getOutComeByTaskId(taskId);
		ValueContext.putValueContext("outCome", outCome);
		
		
		return "inputAccreditationUI";
	}
	
	
	
	public String auditDocumentUI(){
		Set<Document> documents = accreditationService.getDocumentByName(accreditationBean);
		ValueContext.putValueContext("documents", documents);
		return "auditDocumentUI";
	}

	/**
	 * （存储）修改立案申请表(启动流程)
	 */
	public String submitAccreditation(){
		Date date = new Date(116, 0, 1, 0, 0, 0);
	
		if(date.after(new Date())){
			File idCard = accreditationBean.getIdCard();
			List<File> orderChangeNotice = accreditationBean.getOrderChangeNotice();
			List<File> recordInquest = accreditationBean.getRecordInquest();
			List<File> recordInv = accreditationBean.getRecordInv();
			List<File> sitePhotos = accreditationBean.getSitePhotos();
			List<File> recordPaper = accreditationBean.getRecordPaper();
			List<File> enforceCard = accreditationBean.getEnforceCard();
			
			boolean businessLicenseSign = false;
			if(accreditationBean.getUnitName()!=null&&!"".equals(accreditationBean.getUnitName().trim())){
				if(accreditationBean.getBusinessLicense()==null){
					businessLicenseSign = true;
				}
			}
			Boolean sign = true;
			
			if(accreditationBean.getAccreditationId()==null){
				if(idCard==null||orderChangeNotice==null||recordInquest==null||recordInv==null||recordInv==null||sitePhotos==null||recordPaper==null||enforceCard==null){
					if(accreditationBean.getUnitName()!=null&&!"".equals(accreditationBean.getUnitName().trim())){
						if(accreditationBean.getBusinessLicense()==null){
							System.out.println("当事人单位时必须上传所有文书");
							try {
								throw new Exception();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}else{
						System.out.println("当事人为个人时必须上传除营业执照以外的所有文书");
						try {
							throw new Exception();
						} catch (Exception e) {
							e.printStackTrace();
						}
				}
				}
			}
			if(sign){
				Boolean qualifie = accreditationService.valiDate(accreditationBean);
				if(!qualifie){
					
					System.out.println("图片类型不正确");
					try {
						throw new Exception();
					} catch (Exception e) {
						
						e.printStackTrace();
					}
					
				}else{
					accreditationService.submitAccreditation(accreditationBean);
				}
				
			}
		}
		
		
		return "submitSuccess";
	}
	
	
	//进入法制科指导立案审批表页面
	public String guideUI(){
		
		
	
		//获取任务id
		String taskId = accreditationBean.getTaskId();
		if(taskId!=null){
			//使用任务id，查找立案审批表
			Accreditation accreditation = accreditationService.findAccreditationByTaskId(taskId);

			if(accreditation!=null){
				
				ValueContext.putValueStack(accreditation);
				
				//查找所有的案件来源
				List<CaseSource> caseSources = caseSourceService.getAllCaseSource();
				ValueContext.putValueContext("caseSources", caseSources);
				// accreditationService.fillAccreitationBean(accreditationBean,accreditation);
				 String currentSign = accreditationService.getCurrentSign(taskId);
				 accreditationBean.setCurrentSign(currentSign);
			}
			
			
		}
		
		
		return "guideUI";
	}
	//法制科指导立案审批表
	public String guide(){
		//获取任务id
		String taskId = accreditationBean.getTaskId();
		if(taskId!=null){
			accreditationService.guide(accreditationBean);
		}
		return "listTask";
	}
	/**
	 * 导出pdf 文件（下载）
	 */
	public void exportpdf() {
		
		accreditationBeanList.add(accreditationBean);
		JasperHelper.exportmain("pdf", "accreditationReport.jasper", accreditationBeanList, "accreditation.pdf");
	}
	/**
	 * 导出pdf 文件（预览、直接从jsp 来的数据，不经过数据库）
	 */
	public String prePDF() throws Exception {
		if(accreditationBean.getUnitName() != null && !"".equals(accreditationBean.getUnitName().trim())){
			accreditationBean.setSex(null);
		}
		//accreditationBean.setBaseCase("         " + accreditationBean.getBaseCase().trim());
		//什么也不处理，客户输入什么样式，就显示什么样式。
		accreditationBeanList.add(accreditationBean);
		return "PDF";
	}
	/**
	 * 汇总查询里的预览(直接根据页面传过来的id进行操作)
	 */
	public String queryPDF() throws Exception{
		Long accreditationId = accreditationBean.getAccreditationId();
		if(accreditationId != null && accreditationId != 0) {
			Accreditation accreditation = accreditationService.findAccreditationByAccreditationId(accreditationId);
			accreditationService.fillAccreitationBean(accreditationBean, accreditation);
			accreditationBeanList.add(accreditationBean);
			return "PDF";
		} else{
			return "error";
		}
	}
	
}
