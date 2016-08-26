package cn.future.ssh.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import cn.future.ssh.domain.Accreditation;
import cn.future.ssh.domain.IllegalStyle;
import cn.future.ssh.domain.PNotice;
import cn.future.ssh.domain.PTable;
import cn.future.ssh.service.AboutTaskService;
import cn.future.ssh.service.IllegalStyleService;
import cn.future.ssh.service.PNoticeService;
import cn.future.ssh.utils.JasperHelper;
import cn.future.ssh.utils.ValueContext;
import cn.future.ssh.web.form.PNoticeBean;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class PNoticeAction extends ActionSupport implements ModelDriven<PNoticeBean>{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PNoticeBean pNoticeBean = new PNoticeBean();
	private AboutTaskService aboutTaskService;
	private PNoticeService pNoticeService;

	private List<PNoticeBean> pNoticeBeanList = new ArrayList<PNoticeBean>();

	private IllegalStyleService illegalStyleService;

	public void setIllegalStyleService(IllegalStyleService illegalStyleService) {
		this.illegalStyleService = illegalStyleService;
	}	

	public void setAboutTaskService(AboutTaskService aboutTaskService) {
		this.aboutTaskService = aboutTaskService;
	}


	public void setpNoticeService(PNoticeService pNoticeService) {
		this.pNoticeService = pNoticeService;
	}


	@Override
	public PNoticeBean getModel() {
		
		return pNoticeBean;
	}
	/**
	 * 报表预览的数据源
	 * @return
	 */
	public List<PNoticeBean> getPNoticeBeanList() {
		
		return pNoticeBeanList;
	}

	public void setPNoticeBeanList(List<PNoticeBean> pNoticeBeanList) {
		this.pNoticeBeanList = pNoticeBeanList;
	}
	

	/**
	 * 提交任务
	 */
	public String submitTask(){
		
		// 提前准备一个pNotice实体
		//PNotice pNotice = new PNotice();
	
		
			pNoticeService.saveSubmitTask(pNoticeBean);
			if(pNoticeBean.getOutCome().equals("下载审批后的处罚事先告知书")){
				
				return "success";
			}else{
				
				return "listTask";
			}
			
		
		
	}
	
	/*返回要下载文件的的输入流**/
	public InputStream getTargetFile(){
		String pdfDocumentName = pNoticeBean.getPdfDocumentName();
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
		String reallyName = pNoticeBean.getReallyName();
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
	 * 准备处罚事先通知书数据
	 */
	public String audit(){
		//获取任务id
		String taskId = pNoticeBean.getTaskId();
		//使用任务id，查找立案申请表
		PNotice pNotice = pNoticeService.findPNoticeByTaskId(taskId);
		ValueContext.putValueStack(pNotice);
		//已知任务id，查询ProcessDefinitionEntity对象，从而获取当前任务完成之后的连线名称，并放置到List<String>集合中
		List<String> outcomeList = aboutTaskService.findOutComeListByTaskId(taskId);
		ValueContext.putValueContext("outcomeList", outcomeList);
		
		return "taskForm";
	}
	/**
	 * 进入行政处罚事先通知书的录入页面
	 */
	public String input(){
		//获取任务id
		String taskId = pNoticeBean.getTaskId();
		//使用任务id，查找请假单id，从而获取请假单信息
		
		PNotice pNotice = pNoticeService.findPNoticeByTaskId(taskId);
		
		ValueContext.putValueStack(pNotice);
		
		
		List<IllegalStyle> illegalStyles = illegalStyleService.findAllIllegalStyle();
		ServletActionContext.getRequest().setAttribute("illegalStyles", illegalStyles);
		
		//已知任务id，查询ProcessDefinitionEntity对象，从而获取当前任务完成之后的连线名称，并放置到List<String>集合中
		List<String> outcomeList = aboutTaskService.findOutComeListByTaskId(taskId);
		ValueContext.putValueContext("outcomeList", outcomeList);
		
		return "inputPNoticeUI";
	}
	/**
	 * 保存或更改行政处罚事先通知书
	 */
	public String submitPNotice(){
		pNoticeService.submitPNotice(pNoticeBean);
		return "listTask";
	}
	
	
	/**
	 * 导出pdf文件（下载）
	 */
	public void exportpdf() throws Exception {
		// 提前准备一个pNotice实体
		PNotice pNotice = pNoticeService.getPNoticeById("5");
		
		pNoticeService.getFillPNotice(pNoticeBean, pNotice); // 整合出报表需要的 pNoticeBean
		pNoticeBeanList.add(pNoticeBean);// 将要下载的文件实体放到集合里
		JasperHelper.exportmain("pdf", "pNoticeReport.jasper", pNoticeBeanList, "pNotice.pdf");
	}
	/**
	 * pdf文件（预览、打印）
	 */
	public String prePDF() throws Exception {
		// 提前准备一个pNotice实体
		PNotice pNotice = new PNotice();
		if(pNoticeBean.getPNoticeId() != null) { // 查询数据库
			pNotice = pNoticeService.getPNoticeById("" + pNoticeBean.getPNoticeId());
		}
		// 测试：
		//pNotice = pNoticeService.getPNoticeById("28");
		pNoticeService.getFillPNotice(pNoticeBean, pNotice); // 整合出报表需要的 pNoticeBean
		pNoticeBeanList.add(pNoticeBean);// 将要下载的文件实体放到集合里
		return "PDF";
	}
	/**
	 * 汇总查询里的预览(直接根据页面传过来的id进行操作)
	 */
	public String queryPDF() throws Exception{
		Long pNoticeId = pNoticeBean.getPNoticeId();
		if(pNoticeId != null && pNoticeId != 0){
			PNotice pNotice = new PNotice();
			pNotice = pNoticeService.getPNoticeById(pNoticeId + "");
			pNoticeService.getFillPNoforQuery(pNoticeBean, pNotice);// 整合出报表需要的 pNoticeBean
			pNoticeBeanList.add(pNoticeBean);// 将要下载的文件实体放到集合里
			return "PDF";
		} else{
			return "error";
		}
	}
	
	
}
