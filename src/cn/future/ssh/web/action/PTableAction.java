package cn.future.ssh.web.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.activiti.engine.task.Task;
import org.apache.struts2.ServletActionContext;

import cn.future.ssh.domain.Document;
import cn.future.ssh.domain.PTable;
import cn.future.ssh.service.AboutTaskService;
import cn.future.ssh.service.PTableService;
import cn.future.ssh.utils.JasperHelper;
import cn.future.ssh.utils.SessionContext;
import cn.future.ssh.utils.ValueContext;
import cn.future.ssh.web.form.PTableBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class PTableAction extends ActionSupport implements ModelDriven<PTableBean> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PTableBean pTableBean = new PTableBean();
	private AboutTaskService aboutTaskService;
	private PTableService pTableService;
	
	private List<PTableBean> pTableBeanList = new ArrayList<PTableBean>();

	/**
	 * 报表预览的数据源
	 * @return
	 */
	public List<PTableBean> getPTableBeanList() {
		return pTableBeanList;
	}
	
	public void setPTableBeanList(List<PTableBean> pTableBeanList) {
		this.pTableBeanList = pTableBeanList;
	}



	public void setAboutTaskService(AboutTaskService aboutTaskService) {
		this.aboutTaskService = aboutTaskService;
	}



	public void setpTableService(PTableService pTableService) {
		this.pTableService = pTableService;
	}



	@Override
	public PTableBean getModel() {
	
		return pTableBean;
	}
	/**
	 * 提交任务
	 */
	public String submitTask(){
		
		pTableService.saveSubmitTask(pTableBean);
		
		return "listTask";
	}
	
	/**
	 * 准备立案申请表表单数据
	 */
	public String audit(){
		//获取任务id
		String taskId = pTableBean.getTaskId();
		//使用任务id，处罚审批表
		PTable pTable = pTableService.findPTableByTaskId(taskId);
		ValueContext.putValueStack(pTable);
		
		//已知任务id，查询ProcessDefinitionEntity对象，从而获取当前任务完成之后的连线名称，并放置到List<String>集合中
		List<String> outcomeList = aboutTaskService.findOutComeListByTaskId(taskId);
		ValueContext.putValueContext("outcomeList", outcomeList);
		String currentSign = pTableService.getCurrentSign(taskId);
		pTable.setCurrentSign(currentSign);
		if(pTableBean.getDepartment()!=null){
			if(pTableBean.getDepartment().equals("IC")){
				return "ICAuditUI";
			}else if(pTableBean.getDepartment().equals("CRC")){
				return "CRAuditCUI";
			}
		}
		//得到装载类型的集合，用于每个人登录上去之后显示有哪些选择条件
		 List<String> taskTypeList = aboutTaskService.getTaskTypeSet(SessionContext.get(),(String) ActionContext.getContext().getSession().get("loaderSign"));
		 ValueContext.putValueContext("taskTypeList", taskTypeList);
		return "taskForm";
	}
	
	//进入法制科指导行政处罚审批表页面
		public String guideUI(){
			//获取任务id
			String taskId = pTableBean.getTaskId();
			if(taskId!=null){
				//使用任务id，查找行政处罚审批表
				PTable pTable = pTableService.findPTableByTaskId(taskId);
				ValueContext.putValueStack(pTable);
			}
			//得到装载类型的集合，用于每个人登录上去之后显示有哪些选择条件
			 List<String> taskTypeList = aboutTaskService.getTaskTypeSet(SessionContext.get(),(String) ActionContext.getContext().getSession().get("loaderSign"));
			 ValueContext.putValueContext("taskTypeList", taskTypeList);
			return "guideUI";
		}
		
		//法制科指导行政处罚审批表
		public String guide(){
			//获取任务id
			String taskId = pTableBean.getTaskId();
			if(taskId!=null){
				pTableService.guide(pTableBean);
			}
			return "listTask";
		}
	/**
	 * 进入行政处罚事先通知书的录入页面
	 */
	public String input(){
		//获取任务id
		String taskId = pTableBean.getTaskId();
		//使用任务id，查找请假单id，从而获取请假单信息
		PTable pTable = pTableService.findPTableByTaskId(taskId);
		
		pTableService.fillPTable(pTableBean,pTable);
		
		ValueContext.putValueStack(pTable);
		
		Set<Document> proofServicePT =  pTable.getProofServicePT();
		Set<Document> finalReport = pTable.getFinalReport();
		Set<Document> recordSheet = pTable.getRecordSheet();
		
		
		if(proofServicePT==null||proofServicePT.size()==0||finalReport==null||finalReport.size()==0||recordSheet==null||recordSheet.size()==0){
			
				
				ValueContext.putValueContext("documentUnllSign", "true");
					 
				
				
			}
		
		String outCome = pTableService.getOutComeByTaskId(taskId);
		ValueContext.putValueContext("outCome", outCome);
		
		
		return "inputPTableUI";
	}
	
	public String auditDocumentUI(){
		Set<Document> documents = pTableService.getDocumentByName(pTableBean);
		ValueContext.putValueContext("documents", documents);
		return "auditDocumentUI";
	}
	/**
	 * 保存或更改行政处罚事先通知书
	 */
	public String submitPTable(){
		
		PTable pTable = pTableService.findPTableByTaskId(pTableBean.getTaskId());
		
		Set<Document> proofServicePT =  pTable.getProofServicePT();
		Set<Document> finalReport = pTable.getFinalReport();
		Set<Document> recordSheet = pTable.getRecordSheet();
		
		
		Boolean sign = true;
		
		
			if(proofServicePT==null||proofServicePT.size()==0||finalReport==null||finalReport.size()==0||recordSheet==null||recordSheet.size()==0){
				List<File> profServicePTFile = pTableBean.getProofServicePT();
				List<File> finalReportFile = pTableBean.getFinalReport();
				List<File> recordSheetFile = pTableBean.getRecordSheet();
				if(profServicePTFile==null||profServicePTFile.size()==0||finalReportFile==null||finalReportFile.size()==0||recordSheetFile==null||recordSheetFile.size()==0){
					sign = false;
					System.out.println("第一次上传时必须上传所有的文书");
					try {
						throw new Exception();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			}
		
		if(sign){
			Boolean qualifie = pTableService.valiDate(pTableBean);
			if(!qualifie){
				System.out.println("图片类型不正确");
				try {
					throw new Exception();
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				
			}else{
				pTableService.submitPTable(pTableBean);
			}
			
		}
		
	
		return "listTask";
	}
	
	/**
	 * 导出pdf文件（下载）
	 */
	public void exportpdf() throws Exception {
		// 在pTableBeanList里放对象
		PTable pTable = pTableService.findPTableById("1");
		pTableService.getFillPTableforQuery(pTableBean, pTable);
		pTableBeanList.add(pTableBean);
		JasperHelper.exportmain("pdf", "pTableReport.jasper", pTableBeanList, "pTable.pdf");
	}
	/**
	 * pdf文件（预览、打印）
	 */
	public String prePDF() throws Exception {
		pTableBeanList.add(pTableBean);
		return "PDF";
	}

	/**
	 * pdf 文件预览 （案审委）
	 */
	public String prePDF2() throws Exception {
		pTableBeanList.add(pTableBean);
		return "PDF2";
	}
	/**
	 * pdf 文件预览（业委会）
	 */
	public String prePDF3() throws Exception {
		pTableBeanList.add(pTableBean);
		return "PDF3";
	}
	/**
	 * 汇总查询里的预览(直接根据页面传过来的id进行操作)
	 */
	public String queryPDF() throws Exception{
		Long pTableId = pTableBean.getPTableId();
		if(pTableId != null && pTableId != 0){
			PTable pTable = new PTable();
			pTable = pTableService.findPTableById(pTableId + "");
			pTableService.getFillPTableforQuery(pTableBean, pTable);
			pTableBeanList.add(pTableBean);
			return "PDF";
		} else{
			return "error";
		}
	}
	/**
	 * 汇总查询里的预览(直接根据页面传过来的id进行操作)（案审委）
	 */
	public String queryPDF2() throws Exception{
		Long pTableId = pTableBean.getPTableId();
		if(pTableId != null && pTableId != 0){
			PTable pTable = new PTable();
			pTable = pTableService.findPTableById(pTableId + "");
			pTableService.getFillPTableforQuery(pTableBean, pTable);
			pTableBeanList.add(pTableBean);
			return "PDF2";
		} else{
			return "error";
		}
	}
	/**
	 * 汇总查询里的预览(直接根据页面传过来的id进行操作)（业委会）
	 */
	public String queryPDF3() throws Exception{
		Long pTableId = pTableBean.getPTableId();
		if(pTableId != null && pTableId != 0){
			PTable pTable = new PTable();
			pTable = pTableService.findPTableById(pTableId + "");
			pTableService.getFillPTableforQuery(pTableBean, pTable);
			pTableBeanList.add(pTableBean);
			return "PDF3";
		} else{
			return "error";
		}
	}

}
