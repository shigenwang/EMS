package cn.future.ssh.interceptor;

import java.util.HashSet;
import java.util.Set;

import cn.future.ssh.domain.Personnel;
import cn.future.ssh.domain.Role;
import cn.future.ssh.utils.SessionContext;
import cn.future.ssh.utils.ValueContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class CheckPrivilegeInterceptor implements Interceptor {
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		
	}
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		String actionName=invocation.getProxy().getActionName();
		
		//获得登录人
		Personnel personnel=SessionContext.get();
		//获得中队登录的标记
		String loaderSign=(String) ActionContext.getContext().getSession().get("loaderSign");
		Set<String>actionUrlSet=new HashSet<String>();
		String roleName="";
		if(personnel!=null){
			actionUrlSet.add("mainAction_top");
			actionUrlSet.add("mainAction_left");
			actionUrlSet.add("mainAction_right");
			actionUrlSet.add("mainAction_index");
			actionUrlSet.add("loginAction2_logout");
			actionUrlSet.add("aboutTaskAction_viewCurrentImageOrderAccId");
			actionUrlSet.add("aboutTaskAction_viewCurrentImage");
			actionUrlSet.add("aboutTaskAction_viewImage");
			actionUrlSet.add("aboutTaskAction_compressionDocument");
			actionUrlSet.add("aboutTaskAction_downLoadDocuments	");
		if(loaderSign!=null&&"中队".equals(loaderSign)){
			actionUrlSet.add("aboutTaskAction_findTaskListById");//我的任务
			actionUrlSet.add("accreditationAction_inputAccreditation");//填写立案审批表
			
			actionUrlSet.add("squadronAction_list");
			actionUrlSet.add("squadronAction_squadronEdit");
			actionUrlSet.add("squadronAction_squadronEditUI");

			actionUrlSet.add("accreditationAction_inputAccreditation");
			actionUrlSet.add("accreditationAction_submitAccreditation");
			actionUrlSet.add("accreditationAction_exportpdf");
			actionUrlSet.add("accreditationAction_prePDF");
			actionUrlSet.add("accreditationAction_queryPDF");
			
			actionUrlSet.add("pClosingReportAction_input");
			actionUrlSet.add("pClosingReportAction_submitPClosingReport");
			actionUrlSet.add("pClosingReportAction_exportpdf");
			actionUrlSet.add("pClosingReportAction_prePDF");
			actionUrlSet.add("pClosingReportAction_queryPDF");
			
			actionUrlSet.add("pDecideAction_submitPDecide");
			actionUrlSet.add("pDecideAction_exportpdf");
			actionUrlSet.add("pDecideAction_prePDF");
			actionUrlSet.add("pDecideAction_queryPDF");
			
			actionUrlSet.add("pDecideAction_downloadPNoticeDocumentF");
			
			actionUrlSet.add("pNoticeAction_submitPNotice");
			actionUrlSet.add("pNoticeAction_exportpdf");
			actionUrlSet.add("pNoticeAction_prePDF");
			actionUrlSet.add("pNoticeAction_queryPDF");
			actionUrlSet.add("pNoticeAction_downloadPNoticeDocumentF");
			
			actionUrlSet.add("pTableAction_prePDF");
			actionUrlSet.add("pTableAction_prePDF2");
			actionUrlSet.add("pTableAction_prePDF3");
			actionUrlSet.add("pTableAction_queryPDF");
			actionUrlSet.add("pTableAction_queryPDF2");
			actionUrlSet.add("pTableAction_queryPDF3");
			actionUrlSet.add("pTableAction_submitPTable");
			
			actionUrlSet.add("queryAction_list");
			actionUrlSet.add("queryAction_exportpdf");
			actionUrlSet.add("queryAction_queryDetail");
			
			
		}else if(loaderSign!=null&&"一般管理员".equals(loaderSign)){
			actionUrlSet.add("personnelAction_list");
			actionUrlSet.add("personnelAction_add");
			actionUrlSet.add("personnelAction_addUI");
			actionUrlSet.add("personnelAction_delete");
			actionUrlSet.add("personnelAction_initPassworid");

			actionUrlSet.add("squadronAction_list");
			actionUrlSet.add("squadronAction_add");
			actionUrlSet.add("squadronAction_addUI");
			actionUrlSet.add("squadronAction_edit");
			actionUrlSet.add("squadronAction_editUI");
			actionUrlSet.add("squadronAction_delete");
			
			actionUrlSet.add("summaryAction_list");
			actionUrlSet.add("summaryAction_add");
			actionUrlSet.add("summaryAction_addUI");
			actionUrlSet.add("summaryAction_edit");
			actionUrlSet.add("summaryAction_editUI");
			actionUrlSet.add("summaryAction_delete");
			
			actionUrlSet.add("aboutTaskAction_newdeploy");//部署流程
			actionUrlSet.add("aboutTaskAction_delDeployment");//删除流程
			actionUrlSet.add("aboutTaskAction_deployHome");//流程部署
		}
		else{
			for(Role roles:personnel.getRoles()){
				 roleName=roles.getName();
				if(roleName!=null){
					if(roleName.equals("中队队员")||roleName.equals("大队长")||roleName.equals("中队长")
							||roleName.equals("业委会领导")||roleName.equals("案审委领导")){
						actionUrlSet.add("aboutTaskAction_findTaskListById");//我的任务
						actionUrlSet.add("personnelAction_edit");//信息修改
						actionUrlSet.add("queryAction_list");
						actionUrlSet.add("queryAction_exportpdf");
						actionUrlSet.add("queryAction_queryDetail");
						
						actionUrlSet.add("accreditationAction_queryPDF");
						actionUrlSet.add("pNoticeAction_downloadPNoticeDocument");
						actionUrlSet.add("pTableAction_queryPDF");
						actionUrlSet.add("pDecideAction_downloadPNoticeDocument");
						actionUrlSet.add("pClosingReportAction_queryPDF");
						actionUrlSet.add("pTableAction_queryPDF2");
						actionUrlSet.add("pTableAction_queryPDF3");
					}
					if(roleName.equals("法制科领导")){
						actionUrlSet.add("aboutTaskAction_findTaskListById");//我的任务
						actionUrlSet.add("aboutTaskAction_findCandidateTaskListById");//领取任务
						actionUrlSet.add("aboutTaskAction_claimTask");//
						
						actionUrlSet.add("accreditationAction_guideUI");
						actionUrlSet.add("accreditationAction_guide");
						
						actionUrlSet.add("pClosingReportAction_guideUI");
						actionUrlSet.add("pClosingReportAction_guide");
						
						actionUrlSet.add("pTableAction_prePDF2");
						actionUrlSet.add("pTableAction_prePDF3");
						actionUrlSet.add("pTableAction_guideUI");
						actionUrlSet.add("pTableAction_guide");
						
						actionUrlSet.add("queryAction_list");
						actionUrlSet.add("queryAction_exportpdf");
						actionUrlSet.add("queryAction_queryDetail");
						
						actionUrlSet.add("accreditationAction_queryPDF");
						actionUrlSet.add("pNoticeAction_downloadPNoticeDocument");
						actionUrlSet.add("pTableAction_queryPDF");
						actionUrlSet.add("pDecideAction_downloadPNoticeDocument");
						actionUrlSet.add("pClosingReportAction_queryPDF");
						actionUrlSet.add("pTableAction_queryPDF2");
						actionUrlSet.add("pTableAction_queryPDF3");
						
					}
				}
			}
		}
		
		ValueContext.putValueContext("actionUrlSet",actionUrlSet );
	    ActionContext.getContext().getSession().put("loaderSign", loaderSign);
		}

		//如果未登陆
		if(personnel==null){
			if(actionName.startsWith("loginAction2")){
				return invocation.invoke();
			}else{
				//如果不是，就转到登录页面
				return "loginUI";
			}
		}
		//如果为登录，就判断权限
		else{
			for(String url:actionUrlSet ){
				if(url.contains(actionName)){
					System.out.println(url.contains(actionName));
					return invocation.invoke();	
				}
			}
			
			//所有人公共的权限
			if(actionName.contains("submitTask")||actionName.contains("auditDocumentUI")
					||actionName.contains("audit")||actionName.contains("input")||actionName.contains("findCandidateTaskListById")
					||actionName.contains("viewTaskForm")||actionName.contains("personnelAction_edit")||actionName.contains("personnelAction_editUI")
					||actionName.contains("personnelAction_personnelEdit")||actionName.contains("personnelAction_personnelEditUI")
					||actionName.contains("editPassword")||actionName.contains("editPasswordUI")){
				return invocation.invoke();	
			}
			//如果没有权限，就转到提示页面
			
			return "error";
		}
	}
}
