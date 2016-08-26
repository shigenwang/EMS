package cn.future.ssh.web.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.activiti.engine.task.Task;

























import cn.future.ssh.domain.Document;
import cn.future.ssh.domain.PClosingReport;
import cn.future.ssh.domain.PDecide;
import cn.future.ssh.domain.PTable;
import cn.future.ssh.service.AboutTaskService;
import cn.future.ssh.service.PClosingReportService;
import cn.future.ssh.utils.JasperHelper;
import cn.future.ssh.utils.SessionContext;
import cn.future.ssh.utils.ValueContext;
import cn.future.ssh.web.form.PClosingReportBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class PClosingReportAction extends ActionSupport implements ModelDriven<PClosingReportBean>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PClosingReportBean pClosingReportBean = new PClosingReportBean();
	
	
	private AboutTaskService aboutTaskService;
	private PClosingReportService pClosingReportService;
	
	private List<PClosingReportBean> pClosingReportBeanList = new ArrayList<PClosingReportBean>();
	
	/**
	 * 报表预览的数据源
	 * @return
	 */
	public void setpClosingReportService(PClosingReportService pClosingReportService) {
		this.pClosingReportService = pClosingReportService;
	}

	public List<PClosingReportBean> getPClosingReportBeanList() {
		return pClosingReportBeanList;
	}




	public void setPClosingReportBeanList(List<PClosingReportBean> pClosingReportBeanList) {
		this.pClosingReportBeanList = pClosingReportBeanList;
	}




	public void setAboutTaskService(AboutTaskService aboutTaskService) {
		this.aboutTaskService = aboutTaskService;
	}




	@Override
	public PClosingReportBean getModel() {
		
		return pClosingReportBean;
	}
	
	/**
	 * 提交任务
	 */
	public String submitTask(){
		
		pClosingReportService.saveSubmitTask(pClosingReportBean);
		
		return "listTask";
	}
	/**
	 * 准备处罚决定书数据
	 */
	public String audit(){
		//获取任务id
		String taskId = pClosingReportBean.getTaskId();
		//使用任务id，查找立案申请表
		PClosingReport pClosingReport = pClosingReportService.findPClosingReportByTaskId(taskId);
		ValueContext.putValueStack(pClosingReport);
		//已知任务id，查询ProcessDefinitionEntity对象，从而获取当前任务完成之后的连线名称，并放置到List<String>集合中
		List<String> outcomeList = aboutTaskService.findOutComeListByTaskId(taskId);
		ValueContext.putValueContext("outcomeList", outcomeList);
		String currentSign = pClosingReportService.getCurrentSign(taskId);
		pClosingReport.setCurrentSign(currentSign);
		//得到装载类型的集合，用于每个人登录上去之后显示有哪些选择条件
		 List<String> taskTypeList = aboutTaskService.getTaskTypeSet(SessionContext.get(),(String) ActionContext.getContext().getSession().get("loaderSign"));
		 ValueContext.putValueContext("taskTypeList", taskTypeList);		
		return "taskForm";
	}
	/**
	 * 进入处罚决定书的录入页面
	 */
	public String input(){
		//获取任务id
		String taskId = pClosingReportBean.getTaskId();
		//使用任务id，查找请假单id，从而获取请假单信息
		PClosingReport pClosingReport = pClosingReportService.findPClosingReportByTaskId(taskId);
		pClosingReportService.fillPClosingReport(pClosingReportBean,pClosingReport);
		ValueContext.putValueStack(pClosingReport);
		
		
		Set<Document> proofServicePC =  pClosingReport.getProofServicePC();
		Set<Document> fineNote = pClosingReport.getFineNote();
		
		
		
		if(proofServicePC==null||proofServicePC.size()==0||fineNote==null||fineNote.size()==0){
			
				
				ValueContext.putValueContext("documentUnllSign", "true");
					 
				
				
			}
		String outCome = pClosingReportService.getOutComeByTaskId(taskId);
		ValueContext.putValueContext("outCome", outCome);
		
		return "inputPClosingReportUI";
	}
	/**
	 * 保存或更改处罚决定书
	 */
	public String submitPClosingReport(){
		
		
		PClosingReport pClosingReport = pClosingReportService.findPClosingReportByTaskId(pClosingReportBean.getTaskId());
		
		Set<Document> proofServicePC =  pClosingReport.getProofServicePC();
		Set<Document> fineNote = pClosingReport.getFineNote();
	
		Boolean sign = true;
		
			if(proofServicePC==null||proofServicePC.size()==0||fineNote==null||fineNote.size()==0){
				List<File> profServicePTFile = pClosingReportBean.getProofServicePC();
				List<File> finalReportFile = pClosingReportBean.getFineNote();
			
				if(profServicePTFile==null||profServicePTFile.size()==0||finalReportFile==null||finalReportFile.size()==0){
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
			Boolean qualifie = pClosingReportService.valiDate(pClosingReportBean);
			if(!qualifie){
				System.out.println("图片类型不正确");
				try {
					throw new Exception();
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				
			}else{
				pClosingReportService.submitPClosingReport(pClosingReportBean);
			}
			
		}
		
	
		return "listTask";
	}
	
	//进入法制科指导结案报告页面

			public String guideUI(){
				//获取任务id
				String taskId = pClosingReportBean.getTaskId();
				if(taskId!=null){
					//使用任务id，查找结案报告
					PClosingReport pClosingReport = pClosingReportService.findPClosingReportByTaskId(taskId);
					ValueContext.putValueStack(pClosingReport);
				
				}
				//得到装载类型的集合，用于每个人登录上去之后显示有哪些选择条件
				 List<String> taskTypeList = aboutTaskService.getTaskTypeSet(SessionContext.get(),(String) ActionContext.getContext().getSession().get("loaderSign"));
				 ValueContext.putValueContext("taskTypeList", taskTypeList);
				return "guideUI";
			}
			
			//法制科指导结案报告
			public String guide(){
				//获取任务id
				String taskId = pClosingReportBean.getTaskId();
				if(taskId!=null){
					pClosingReportService.guide(pClosingReportBean);
				}
				return "listTask";
			}

			public String auditDocumentUI(){
				Set<Document> documents = pClosingReportService.getDocumentByName(pClosingReportBean);
				ValueContext.putValueContext("documents", documents);
				return "auditDocumentUI";
}

	
	/**
	 * 导出pdf文件（下载）
	 */
	public void exportpdf() throws Exception {
		// 在pClosingReportBeanList里放对象
		PClosingReport pClosingReport = pClosingReportService.findPClosingReportById("1");
		pClosingReportService.getFillPClosingReport(pClosingReportBean, pClosingReport);
		pClosingReportBeanList.add(pClosingReportBean);
		JasperHelper.exportmain("pdf", "pDecideReport.jasper", pClosingReportBeanList, "pDecide.pdf");
	}
	/**
	 * pdf文件（预览、打印）
	 */
	public String prePDF() throws Exception {
		// 数据源 在pClosingReportBeanList里放对象
		PClosingReport pClosingReport = new PClosingReport();
		pClosingReportService.getFillPClosingReport(pClosingReportBean, pClosingReport);
		pClosingReportBeanList.add(pClosingReportBean);
		return "PDF";
	}
	/**
	 * 汇总查询里的预览(直接根据页面传过来的id进行操作)
	 */
	public String queryPDF() throws Exception{
		Long pClosingReportId = pClosingReportBean.getPClosingReportId();
		if(pClosingReportId != null && pClosingReportId != 0) {
			// 数据源 在pClosingReportBeanList里放对象
			PClosingReport pClosingReport = new PClosingReport();
			pClosingReport = pClosingReportService.findPClosingReportById(pClosingReportId + "");
			pClosingReportService.getFillPClosingforQuery(pClosingReportBean, pClosingReport);
			pClosingReportBeanList.add(pClosingReportBean);
			return "PDF";
		} else{
			return "error";
		}
	}

}
