package cn.future.ssh.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import cn.future.ssh.domain.PDecide;
import cn.future.ssh.service.AboutTaskService;
import cn.future.ssh.service.PDecideService;
import cn.future.ssh.utils.JasperHelper;
import cn.future.ssh.utils.ValueContext;
import cn.future.ssh.web.form.PDecideBean;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class PDecideAction extends ActionSupport implements ModelDriven<PDecideBean>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PDecideBean pDecideBean = new PDecideBean();
	
	
	private AboutTaskService aboutTaskService;
	private PDecideService pDecideService;

	private List<PDecideBean> pDecideBeanList = new ArrayList<PDecideBean>();

	/**
	 * 报表预览的数据源
	 * @return
	 */
	public List<PDecideBean> getPDecideBeanList() {
		return pDecideBeanList;
	}

	public void setPDecideBeanList(List<PDecideBean> pDecideBeanList) {
		this.pDecideBeanList = pDecideBeanList;
	}


	public void setpDecideService(PDecideService pDecideService) {
		this.pDecideService = pDecideService;
	}




	public void setAboutTaskService(AboutTaskService aboutTaskService) {
		this.aboutTaskService = aboutTaskService;
	}




	@Override
	public PDecideBean getModel() {
		
		return pDecideBean;
	}
	
	/**
	 * 提交任务
	 */
	public String submitTask(){
		
		pDecideService.saveSubmitTask(pDecideBean);
		if(pDecideBean.getOutCome().equals("下载审批后的处罚决定书")){
			
			return "success";
		}else{
			
			return "listTask";
		}
	
	}
	
	/*返回要下载文件的的输入流**/
	public InputStream getTargetFile(){
		String pdfDocumentName = pDecideBean.getPdfDocumentName();
		String pdfDocumentPath = "e:"+ServletActionContext.getRequest().getContextPath()+"/document/pdf/"+pdfDocumentName;
		FileInputStream fis = null;
		try {
			fis  = new FileInputStream(new File(pdfDocumentPath));
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		return fis;
	}
	
	/*返回要下载文件的文件名**/
	public String getFileName() {
		String reallyName = pDecideBean.getReallyName();
		try {
			reallyName = new String(new String(reallyName).getBytes(),"ISO-8859-1");//对文件名重新编码，否则用户从浏览器下载的文件名会出现问题
			
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
	
	return reallyName;
}
	
	public String downloadPNoticeDocument(){
		return "success";
	}
	/**
	 * 准备处罚决定书数据
	 */
	public String audit(){
		//获取任务id
		String taskId = pDecideBean.getTaskId();
		//使用任务id，查找立案申请表
		PDecide pDecide = pDecideService.findPDecideByTaskId(taskId);
		ValueContext.putValueStack(pDecide);
		//已知任务id，查询ProcessDefinitionEntity对象，从而获取当前任务完成之后的连线名称，并放置到List<String>集合中
		List<String> outcomeList = aboutTaskService.findOutComeListByTaskId(taskId);
		ValueContext.putValueContext("outcomeList", outcomeList);
		
		return "taskForm";
	}
	/**
	 * 进入处罚决定书的录入页面
	 */
	public String input(){
		//获取任务id
		String taskId = pDecideBean.getTaskId();
		//使用任务id，查找请假单id，从而获取请假单信息
		PDecide pDecide = pDecideService.findPDecideByTaskId(taskId);
		ValueContext.putValueStack(pDecide);
		//ServletActionContext.getRequest().setAttribute("pDecide", pDecide);
		//已知任务id，查询ProcessDefinitionEntity对象，从而获取当前任务完成之后的连线名称，并放置到List<String>集合中
		List<String> outcomeList = aboutTaskService.findOutComeListByTaskId(taskId);
		ValueContext.putValueContext("outcomeList", outcomeList);
		
		return "inputPDecideUI";
	}
	/**
	 * 保存或更改处罚决定书
	 */
	public String submitPDecide(){
		pDecideService.submitPDecide(pDecideBean);
		return "listTask";
	}
	
	/**
	 * 导出pdf文件（下载）
	 */
	public void exportpdf() throws Exception {
		// 在pDecideBeanList里放对象
		PDecide pDecide = pDecideService.findPDecideById("1");
		pDecideService.getFillPDecide(pDecideBean, pDecide);
		pDecideBeanList.add(pDecideBean);
		JasperHelper.exportmain("pdf", "pDecideReport.jasper", pDecideBeanList, "pDecide.pdf");
	}
	/**
	 * pdf文件（预览、打印）
	 */
	public String prePDF() throws Exception {
		// 数据源 在pDecideBeanList里放对象
		PDecide pDecide = new PDecide();
		if(pDecideBean.getPDecideId() != null) {
			pDecide = pDecideService.findPDecideById(pDecideBean.getPDecideId() + "");
		}
		pDecideService.getFillPDecide(pDecideBean, pDecide);
		pDecideBeanList.add(pDecideBean);
		
		return "PDF";
	}
	/**
	 * 汇总查询里的预览(直接根据页面传过来的id进行操作)
	 */
	public String queryPDF() throws Exception{
		Long pDecideId = pDecideBean.getPDecideId();
		if(pDecideId != null && pDecideId != 0) {
			// 数据源 在pDecideBeanList里放对象
			PDecide pDecide = new PDecide();
			pDecide = pDecideService.findPDecideById(pDecideId + "");
			pDecideService.getFillPDecideforQuery(pDecideBean, pDecide);
			pDecideBeanList.add(pDecideBean);
			return "PDF";
		} else{
			return "error";
		}
	}
	
}



