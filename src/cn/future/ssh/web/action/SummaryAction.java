package cn.future.ssh.web.action;

import java.util.List;

import cn.future.ssh.domain.Summary;
import cn.future.ssh.service.SummaryService;
import cn.future.ssh.utils.ValueContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class SummaryAction extends ActionSupport implements ModelDriven<Summary> {
    
	private Summary model=new Summary();
	private SummaryService summaryService;
	private Long summary_id;
	
	public Long getSummary_id() {
		return summary_id;
	}
	public void setSummary_id(Long summary_id) {
		this.summary_id = summary_id;
	}
	public SummaryService getSummaryService() {
		return summaryService;
	}
	public void setSummaryService(SummaryService summaryService) {
		this.summaryService = summaryService;
	}
	@Override
	public Summary getModel() {
		// TODO Auto-generated method stub
		return model;
	}
	
	/*
	 * 查询列表
	 */
	public String list()throws Exception{
		//准备数据summary
		Summary summary=summaryService.getSummaryById(summary_id);
		ValueContext.putValueStack(summary);
		ActionContext.getContext().getSession().put("summarySign", summary);
		
		List<Summary> summaryList=summaryService.getAllSummary();
		ValueContext.putValueContext("summaryList", summaryList);
		
		return "list";
	}
	
	/*
	 * 保存
	 * 
	 */
	public String add()throws Exception{
		
		summaryService.saveSummary(model);
		return "toList";
	}
	
	/*
	 * 保存的页面
	 */
	public String addUI()throws Exception{
	
		
		return "saveUI";
	}
	
	/*
	 * 修改
	 * 
	 */
	public String edit()throws Exception{
		//1.从数据库中取出要修改的数据
		Summary summary=summaryService.getSummaryById(model.getId());
		
		//2.进行更改
		summary.setCutOrder(model.getCutOrder());
		summary.setLegalBasis(model.getLegalBasis());
		summary.setName(model.getName());
		summary.setPenaltyType(model.getPenaltyType());
		summary.setTimeLimit(model.getTimeLimit());
		summary.setVioRegulations(model.getVioRegulations());
		
		//保存到数据库
		summaryService.updateSummary(summary);
		
		return "toList";
	}
	
	/*
	 * 修改的页面
	 */
	public String editUI()throws Exception{
	 //准备回显的数据
		Summary summary=summaryService.getSummaryById(model.getId());
		ValueContext.putValueStack(summary);
		
		return "saveUI";
	}
	
	/*
	 * 删除
	 */
	public String delete()throws Exception{
		Summary summary=summaryService.getSummaryById(model.getId());
		summaryService.deleteSummary(summary);
		return "toList";
	}
     
}
