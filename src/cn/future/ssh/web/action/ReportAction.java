package cn.future.ssh.web.action;

import cn.future.ssh.base.BaseAction;
import cn.future.ssh.domain.Accreditation;
import cn.future.ssh.service.AccreditationService;
import cn.future.ssh.utils.JasperHelper;
import net.sf.jasperreports.engine.JasperCompileManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
/**
 * 报表相关的Action
 * 其中包括：
 * 1、根据页面已经填写的数据进行预览、打印（数据来源：页面输入的数据）
 * 思路：通过提交，获取页面已经填写的数据，在Action里封装成 bean，然后进行操作。
 * 2、将数据库里已经存储的数据进行报表的预览、打印（数据来源：数据库）
 * 思路：通过id从数据库里查询出数据，封装成bean，然后进行操作。
 * @author hzc
 *
 */
public class ReportAction extends BaseAction<Accreditation>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 立案审批表的 service 方法
	 */
	private AccreditationService accreditationService;
	/**
	 * 立案审批表id
	 */
	private String accreditationId;
	
	public String getAccreditationId() {
		return accreditationId;
	}
	public void setAccreditationId(String accreditationId) {
		this.accreditationId = accreditationId;
	}
	public AccreditationService getAccreditationService() {
		return accreditationService;
	}
	public void setAccreditationService(AccreditationService accreditationService) {
		this.accreditationService = accreditationService;
	}

	List<Accreditation> accreditationList  = new ArrayList<Accreditation>();
	
	public List<Accreditation> getAccreditationList() {
		Accreditation accreditation =  accreditationService.findAccreditationByAccreditationId(model.getId());
		accreditationList.add(accreditation);
		Accreditation accreditation2 = accreditationService.findAccreditationByAccreditationId(26L);
		accreditationList.add(accreditation2);
		return accreditationList;
	}
	public void setAccreditationList(List<Accreditation> accreditationList) {
		this.accreditationList = accreditationList;
	}
	/**
	 * 导出pdf 文件（下载）
	 */
	public String exportpdf() {
	    long id = model.getId();
		Accreditation accreditation =  accreditationService.findAccreditationByAccreditationId(id);
		accreditationList.add(accreditation);
		Accreditation accreditation2 = accreditationService.findAccreditationByAccreditationId(26L);
		accreditationList.add(accreditation2);
		JasperHelper.exportmain("pdf", "accreditationReport.jasper", accreditationList, "accreditation.pdf");
		return null;
	}
	/**
	 * 导出pdf 文件（预览）
	 */
	public String prePDF() throws Exception {
		return super.execute();
	}
}
