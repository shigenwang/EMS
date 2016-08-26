package cn.future.ssh.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import cn.future.ssh.dao.PDecideDao;
import cn.future.ssh.domain.Accreditation;
import cn.future.ssh.domain.PDFDocument;
import cn.future.ssh.domain.PDecide;
import cn.future.ssh.domain.PNotice;
import cn.future.ssh.domain.Summary;
import cn.future.ssh.service.AccreditationService;
import cn.future.ssh.service.PDFDocumentService;
import cn.future.ssh.service.PDecideService;
import cn.future.ssh.utils.AccountChange;
import cn.future.ssh.utils.SessionContext;
import cn.future.ssh.web.form.PDecideBean;




public class PDecideServiceImpl implements PDecideService{
	private TaskService taskService;
	
	private RuntimeService runtimeService;
	
	private AccreditationService accreditationService;
	
	
	private PDecideDao pDecideDao;
	
	private PDFDocumentService pdfDocumentService;

	public void setPdfDocumentService(PDFDocumentService pdfDocumentService) {
		this.pdfDocumentService = pdfDocumentService;
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


	public void setpDecideDao(PDecideDao pDecideDao) {
		this.pDecideDao = pDecideDao;
	}
	
	/**
	 * 保存（更改）处罚事先通知书
	 */
	@Override
	public void submitPDecide(PDecideBean pDecideBean) {
		PDecide pDecide = null;
		if(pDecideBean.getPDecideId()!=null){
			pDecide = pDecideDao.findPDecideById(pDecideBean.getPDecideId());
		}
	
		if(pDecide!=null){
			
			/**
			 * 将pDecideBean中的数据保存到pDecide中
			 */
			pDecide.setCaseIntroduction(pDecideBean.getCaseIntroduction());
			pDecide.setBankAccount(pDecideBean.getBankAccount());
			pDecide.setPayBank(pDecideBean.getPayBank());
			pDecide.setAdreconAddress1(pDecideBean.getAdreconAddress1());
			pDecide.setAdreconAddress2(pDecideBean.getAdreconAddress2());
			pDecide.setAdreconTimeLimit(pDecideBean.getAdreconTimeLimit());
			pDecide.setActualFines(pDecideBean.getActualFines());
			pDecide.setBankAccount(pDecideBean.getBankAccount());
			pDecide.setAdreconTimeLimit(pDecideBean.getAdreconTimeLimit());
			PNotice pNotice = pDecide.getAccreditation().getpNotice();
			Summary summary = pDecide.getSummary();
			
			String paragraph2 = "   本机关于"+pDecide.getCaseIntroduction()+"一案立案调查。经查，你于"
					+pNotice.getIllegalContent()+"。该行为违反了"
					+summary.getVioRegulations()+"之规定";
			pDecide.setParagraph2(paragraph2);
			
			String paragraph3 = "   根据你的违法事实、性质、情节、社会危害程度和相关证据，你的违法行为为"
								+pNotice.getIllegalStyles().getName()+"。";
			pDecide.setParagraph3(paragraph3);
			Accreditation accreditation = pDecide.getAccreditation();
			String paragraph4 ="  违法行为的证据：1、当事人身份证件1份；2、责令停止（改正）违法行为通知书"+accreditation.getOrderChangeNotice().size()+"份；"
					+ "3、现场检查（勘验）笔录"+accreditation.getRecordInquest().size()+"份；4、询问笔录"+accreditation.getRecordPaper().size()+"份；5、现场照片拍摄说明"+accreditation.getRecordInv().size()+"份；6、行政处罚事先告知书及送达回证"+accreditation.getpTable().getProofServicePT().size()+"份等。";
			pDecide.setParagraph4(paragraph4);
			
			String paragraph5 = "   上述违法行为事实清楚、证据确凿充分，依据"+summary.getLegalBasis()+"之规定，决定对你作出如下处罚："+ "罚款"+AccountChange.digitUppercase(Double.valueOf(pDecide.getActualFines()))+"。";
			pDecide.setParagraph5(paragraph5);
			
			
		
			String paragraph7 = "   请你自接到本决定书之日起的15日内，将罚款缴至"+pDecide.getPayBank()
					+"(账号："+pDecide.getBankAccount()+")。逾期不缴纳罚款的，每日按照罚款数额的3%加处罚款。但加处罚款的数额不超出罚款的数额";
			pDecide.setParagraph7(paragraph7);
			
			String paragraph8 = "   如不服本决定，可以自收到本决定书之日起"+pDecide.getAdreconTimeLimit()+"内向"
								+pDecide.getAdreconAddress1()+"或"+pDecide.getAdreconAddress2()
								+"申请行政复议，或者在六个月内直接向人民法院起诉。逾期不申请行政复议、"
								+ "不向人民法院起诉又不履行本处罚决定的，我局将申请人民法院强制执行。";
			pDecide.setParagraph8(paragraph8);
			
		
			pDecide.setDate(new Date());
			//更新对象
			pDecideDao.updatePDecide(pDecide);
		}
		Map<String,Object> variables = new HashMap<String,Object>();
		variables.put("outCome", pDecideBean.getOutCome());
		taskService.complete(pDecideBean.getTaskId(),variables);
		
	}
	/**
	 * 通过任务id查找处罚事先告知书
	 */
	@Override
	public PDecide findPDecideByTaskId(String taskId) {
		
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
		PDecide pDecide = accreditation.getpDecide();
		if(pDecide==null){
			pDecide = new PDecide();
			pDecide.setSummary(accreditation.getSummary());
			pDecide.setPayBank("郑州银行");
			pDecide.setBankAccount("123456789");
			pDecide.setAdreconTimeLimit(60);
			pDecideDao.savePDecide(pDecide);
			
			pDecide.setAccreditation(accreditation);
			accreditation.setpDecide(pDecide);
			pDecideDao.updatePDecide(pDecide);
			
			accreditationService.updateAccreditation(accreditation);
		}
		
		
		return pDecide;
	}


	@Override
	public void saveSubmitTask(PDecideBean pDecideBean) {
		String taskId = pDecideBean.getTaskId();
		
		PDecide pDecide = this.findPDecideByTaskId(taskId);
		//获取连线的名称
		String outCome = pDecideBean.getOutCome();
		Map<String,Object> variables = new HashMap<String,Object>();
		
		if(!outCome.equals("下载审批后的处罚决定书")){

			//完成任务的同时设置流程变量，指定下一个任务节点
			
			if(outCome.equals("驳回")&&pDecideBean.getLegalSuggest()!=null&&!"".equals(pDecideBean.getLegalSuggest().trim())){
				pDecide.setLegalSuggest(pDecideBean.getLegalSuggest());
				pDecide.setLegalFlag(false);

				//完成任务的同时设置流程变量，指定下一个任务节点
				
				
			
			}else if(outCome.equals("批准")&&(pDecideBean.getLegalSuggest()==null||"".equals(pDecideBean.getLegalSuggest().trim()))){
				pDecide.setLegalSuggest(null);
				pDecide.setLegalFlag(true);
				
				if(pDecideBean.getPdfDocument()==null||(!pDecideBean.getPdfDocumentContentType().equals("application/pdf")&&!pDecideBean.getPdfDocumentContentType().equals("application/kswps"))){
					System.out.println("请上传pdf文件");
					try {
						throw new Exception();
					} catch (Exception e) {
						
						e.printStackTrace();
					}
					return;
				}
				PDFDocument pdfDocument = new PDFDocument();
				
				
				String reallyName = pDecideBean.getPdfDocumentFileName();
				pdfDocument.setReallyName(reallyName);
				
				String name = UUID.randomUUID().toString()+".pdf";
				pdfDocument.setName(name);
				String pdfDocumentDirStr =  "e:"+ServletActionContext.getRequest().getContextPath()+"/document/pdf";
				
				File pdfDocumentDir = new File(pdfDocumentDirStr);
				if(!pdfDocumentDir.exists()){
					pdfDocumentDir.mkdirs();
				}
				
				File pdfDocumentFile = new File(pdfDocumentDirStr,name);
				try {
					FileInputStream is = new FileInputStream(pDecideBean.getPdfDocument());
					FileOutputStream os = new FileOutputStream(pdfDocumentFile);
					byte[] buffer = new byte[1024*1024];
					int length;
					while((length = is.read(buffer))!=-1){
						os.write(buffer, 0, length);
					}
					os.close();
					is.close();
				}catch(Exception e){
					e.printStackTrace();
				}
				pdfDocumentService.save(pdfDocument);
				pDecide.setPdfDocument(pdfDocument);
				
			
			}else if(!outCome.equals("行政处罚决定书报表生成")){
				//出错处理
				System.out.println("批准时不能填写意见，驳回时必须填写意见");
				try {
					throw new Exception();
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				return;
				
			
				
		}
			
			pDecideDao.updatePDecide(pDecide);
		}
		
		

		variables.put("outCome", outCome);
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
	public void fillPDecide(PDecideBean pDecideBean, PDecide pDecide) {
		
	}


	/**
	 * 报表预览的时候，拼凑pDecideBean
	 */
	public void getFillPDecide(PDecideBean pDecideBean, PDecide pDecide) {
		
		if(pDecide.getDate() != null) {
			// 设置报表审批时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
			pDecideBean.setDateStr(sdf.format(pDecide.getDate()));
		}
		if(pDecide.getAccreditation() != null) {
			// 字号
			pDecideBean.setAccreditationId(pDecide.getAccreditation().getId());
			// 设置罚决字(跟立案审批表的年号一样)
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy");
			if(pDecide.getAccreditation().getBigCaptainDate() != null) {
				pDecideBean.setYearNum(sdf1.format(pDecide.getAccreditation().getBigCaptainDate()));
			}
		}
		if(pDecide != null) {
			// 拼凑第一段
			String paragraph1 = "";
			
			Accreditation accreditation = pDecide.getAccreditation();
			String UnitName = accreditation.getUnitName();
			if(UnitName!=null&&!"".equals(UnitName)){ // 判断当事人是 “单位” 的时候
				paragraph1 = UnitName+":\n经营者姓名:   "+accreditation.getLeRepresentative()+"     营业执照注册号：  "
							+accreditation.getpNotice().getBusLicense()+"\n地址:  "+accreditation.getUnitAddress();
			}else{ // 判断当事人是 “个人”
				paragraph1 = "当事人："+accreditation.getPersonnelName()+"     性别："+accreditation.getSex()+"     年龄："+accreditation.getUserAge()+"\n身份证号:  "+accreditation.getIdNumber()
						+"\n地址："+accreditation.getUserAddress();
			}
			pDecideBean.setParagraph1(paragraph1 + "\n");
			
			// 拼凑第二段
			String paragraph2 = "         ";
			paragraph2 = paragraph2 + "本机关于" + pDecideBean.getCaseIntroduction() + "一案立案调查。";
			paragraph2 = paragraph2 + pDecide.getAccreditation().getpNotice().getIllegalContent();
			paragraph2 = paragraph2 + "该行为违反了" + pDecide.getSummary().getVioRegulations() + "之规定。";
			pDecideBean.setParagraph2(paragraph2 + "\n");
			
			// 拼凑第三段
			String paragraph3 = "         ";
			String illegalStylesName = pDecide.getAccreditation().getpNotice().getIllegalStyles().getName();
			if(illegalStylesName != null) {
				paragraph3 = paragraph3 + "根据你的违法事实、性质、情节、社会危害程度和相关证据，你的违法行为为" + illegalStylesName;
			}
			pDecideBean.setParagraph3(paragraph3 + "。\n");
			
			// 拼凑第四段
			String paragraph4 = "         ";
			// 如果当事人是单位
			String unitName = pDecide.getAccreditation().getUnitName();
			if(unitName != null && !"".equals(unitName)){ // 如果当事人是 个人
				paragraph4 = paragraph4 +"违法行为的证据：1、企业法人营业执照副本复印件1份；2、法人身份证复印件1份；3、责令停止（改正）违法行为通知书1份；"
						+ "4、现场检查（勘验）笔录1份；5、询问笔录1份；6、现场照片拍摄说明1份；7、行政处罚事先告知书及送达回证1份等。";
			} else {// 如果当事人是单位
				paragraph4 = paragraph4 + "违法行为的证据：1、当事人身份证复印件1份；2、责令停止（改正）违法行为通知书1份；"
						+ "3、现场检查（勘验）笔录一份；4、询问笔录1份；5、现场照片拍摄说明1份；6、行政处罚事先告知书及送达回证1份等。";
			}
			pDecideBean.setParagraph4(paragraph4 + "\n");
			
			// 拼凑第五段
			String paragraph5 = "         ";
			paragraph5 = paragraph5 + "上述违法行为事实清楚、证据确凿，根据" + pDecide.getSummary().getLegalBasis() + "之规定，决定对你做出如下行政处罚：";
			Integer actualFines = pDecideBean.getActualFines();
			paragraph5 = paragraph5 + AccountChange.digitUppercase(actualFines) + " 。";
			pDecideBean.setParagraph5(paragraph5 + "\n");
			
			// 拼凑第六段
			/*String paragraph6 = "         ";
			String otherpenaltyInf = pDecide.getAccreditation().getpNotice().getOtherpenaltyInf();
			Integer actualFines = pDecideBean.getActualFines();
			// 如果有其他处罚时
			if(otherpenaltyInf != null && !"".equals(otherpenaltyInf)) {
				if(actualFines != null) {
					paragraph6 = paragraph6 + "1、" + AccountChange.digitUppercase(actualFines);
					paragraph6 = paragraph6 + "  2、" + otherpenaltyInf;
				}
			} else { // 只有金额罚款时
				if(actualFines != null) {
					paragraph6 = paragraph6 + AccountChange.digitUppercase(actualFines);
				}
			}
			pDecideBean.setParagraph6(paragraph6 + "。\n");*/
			
			// 拼凑第七段
			String paragraph7 = "         ";
			paragraph7 = paragraph7 + "请你自接到本决定书之日起15日内，将罚款缴至" + pDecideBean.getPayBank() + "(" + pDecideBean.getBankAccount() + ")。";
			paragraph7 = paragraph7 + "逾期不缴纳的，每日按罚款数额的3%加处罚款。";
			pDecideBean.setParagraph7(paragraph7 + "\n");
			
			// 拼凑第八段
			String paragraph8 = "         ";
			paragraph8 = paragraph8 + "如不服本决定，可以自收到本决定书之日起" + pDecideBean.getAdreconTimeLimit() + "日内向";
			paragraph8 = paragraph8 + pDecideBean.getAdreconAddress1() + "或" + pDecideBean.getAdreconAddress2() + "申请行政复议， "
					+ "或者在三个月内直接想人民法院起诉。逾期不申请行政复议、不向人民法院起诉又不履行本处罚决定的，我局将申请人民法院强制执行。";
			pDecideBean.setParagraph8(paragraph8 + "\n");
		}
	}

	/**
	 * 报表预览的时候，拼凑pDecideBean
	 */
	public void getFillPDecideforQuery(PDecideBean pDecideBean, PDecide pDecide) {
		if(pDecide.getDate() != null) {
			// 设置报表审批时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
			pDecideBean.setDateStr(sdf.format(pDecide.getDate()));
		}
		if(pDecide.getAccreditation() != null) {
			// 字号
			pDecideBean.setAccreditationId(pDecide.getAccreditation().getId());
			// 设置罚决字(跟立案审批表的年号一样)
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy");
			if(pDecide.getAccreditation().getBigCaptainDate() != null) {
				pDecideBean.setYearNum(sdf1.format(pDecide.getAccreditation().getBigCaptainDate()));
			}
		}
		// 各个段落内容
		String paragraph2 =  pDecide.getParagraph2();
		String paragraph3 =  pDecide.getParagraph3();
		String paragraph4 =  pDecide.getParagraph4();
		String paragraph5 =  pDecide.getParagraph5();
		String paragraph6 =  pDecide.getParagraph6();
		String paragraph7 =  pDecide.getParagraph7();
		String paragraph8 =  pDecide.getParagraph8();
		if(paragraph2 != null) {
			pDecideBean.setParagraph2("         " + paragraph2.trim() + "\n");
		}
		if(paragraph3 != null) {
			pDecideBean.setParagraph3("         " + paragraph3.trim() + "\n");
		}
		if(paragraph4 != null) {
			pDecideBean.setParagraph4("         " + paragraph4.trim() + "\n");
		}
		if(paragraph5 != null) {
			pDecideBean.setParagraph5("         " + paragraph5.trim() + "\n");
		}
		if(paragraph6 != null) {
			pDecideBean.setParagraph6("         " + paragraph6.trim() + "\n");
		}
		if(paragraph7 != null) {
			pDecideBean.setParagraph7("         " + paragraph7.trim() + "\n");
		}
		if(paragraph8 != null) {
			pDecideBean.setParagraph8("         " + paragraph8.trim());
		}
		// 拼凑各段内容
		if(pDecide.getAccreditation() != null) {
			if(pDecide.getAccreditation().getpNotice() != null) {
				String paragraph1 = pDecide.getAccreditation().getpNotice().getParagraph1();
				if(paragraph1 != null) {
					pDecideBean.setParagraph1(paragraph1.trim() + "\n");
				}
			}
		}
	}

	@Override
	public PDecide findPDecideById(String id) {
		return pDecideDao.findPDecideById(Long.parseLong(id));
	}



	@Override
	public PDecide findPNoticeByPNoticeId(Long pDecideId) {
		PDecide pDecide = pDecideDao.findPDecideById(pDecideId);
		return pDecide;
	}


	public void updatePDecide(PDecide pDecide) {
		pDecideDao.updatePDecide(pDecide);
	}

	
}
