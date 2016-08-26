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

import cn.future.ssh.dao.PNoticeDao;
import cn.future.ssh.domain.Accreditation;
import cn.future.ssh.domain.IllegalStyle;
import cn.future.ssh.domain.PDFDocument;
import cn.future.ssh.domain.PNotice;
import cn.future.ssh.domain.Summary;
import cn.future.ssh.service.AccreditationService;
import cn.future.ssh.service.IllegalStyleService;
import cn.future.ssh.service.PDFDocumentService;
import cn.future.ssh.service.PNoticeService;
import cn.future.ssh.utils.AccountChange;
import cn.future.ssh.utils.SessionContext;
import cn.future.ssh.web.form.PNoticeBean;


public class PNoticeServiceImpl implements  PNoticeService{
		
		private PNoticeDao pNoticeDao;
		
		
		
		private TaskService taskService;
		
		private RuntimeService runtimeService;
		
		private AccreditationService accreditationService;
		
		private IllegalStyleService illegalStyleService;
		
		private PDFDocumentService pdfDocumentService;

		


		public void setPdfDocumentService(PDFDocumentService pdfDocumentService) {
			this.pdfDocumentService = pdfDocumentService;
		}



		public void setIllegalStyleService(IllegalStyleService illegalStyleService) {
			this.illegalStyleService = illegalStyleService;
		}



		public void setAccreditationService(AccreditationService accreditationService) {
			this.accreditationService = accreditationService;
		}



		public void setRuntimeService(RuntimeService runtimeService) {
			this.runtimeService = runtimeService;
		}

	

		

		public void setTaskService(TaskService taskService) {
			this.taskService = taskService;
		}

		public PNoticeDao getpNoticeDao() {
			return pNoticeDao;
		}

		public void setpNoticeDao(PNoticeDao pNoticeDao) {
			this.pNoticeDao = pNoticeDao;
		}
		/**
		 * 保存（更改）处罚事先通知书
		 */
		@Override
		public void submitPNotice(PNoticeBean pNoticeBean) {
			
			PNotice pNotice = null;
			if(pNoticeBean.getPNoticeId()!=null){
				pNotice = pNoticeDao.findPNoticeById(pNoticeBean.getPNoticeId());
			}
			
			if(pNotice!=null){
				pNotice.setId(pNoticeBean.getPNoticeId());
				
				pNotice.setBusLicense(pNoticeBean.getBusLicense());
				pNotice.setFines(pNoticeBean.getFines());
				pNotice.setSumitDate(pNoticeBean.getSumitDate());
			
				pNotice.setOtherpenaltyInf(pNoticeBean.getOtherpenaltyInf());
				IllegalStyle illegalStyle = illegalStyleService.findIllegaStyleById(pNoticeBean.getIllegalstyleId());
				illegalStyle.setId(pNoticeBean.getIllegalstyleId());
				pNotice.setIllegalStyles(illegalStyle);
				
				
				pNotice.setIllegalContent(pNoticeBean.getIllegalContent());
				
				Accreditation accreditation = pNotice.getAccreditation();
				Summary summary = pNotice.getSummary();
				
				String paragraph1 = null;
				String UnitName = accreditation.getUnitName();
				
				
				if(UnitName!=null&&!"".equals(UnitName)){
					paragraph1 = UnitName+":\n经营者姓名:   "+accreditation.getLeRepresentative()+"     营业执照注册号：  "
								+pNotice.getBusLicense()+"\n地址:  "+accreditation.getUnitAddress();
					
				}else{
					paragraph1 = "当事人："+accreditation.getPersonnelName()+"     性别："+accreditation.getSex()+"     年龄："+accreditation.getUserAge()+"\n身份证号:  "+accreditation.getIdNumber()
							+"\n地址：	"+accreditation.getUserAddress();
				}
				pNotice.setParagraph1(paragraph1);
				
				
				String paragraph2 = pNotice.getIllegalContent()+"该行为违反了"
									+summary.getVioRegulations()+"之规定。";
				pNotice.setParagraph2(paragraph2);
				
				String paragraph3 = "    从你的违法事实、性质、情节、社会危害程度和证据来看，你的违法行为属于"+pNotice.getIllegalStyles().getName()+"。";
				pNotice.setParagraph3(paragraph3);
				
				StringBuffer penaltyInf = new StringBuffer();
				penaltyInf.append("罚款").append(AccountChange.digitUppercase(Double.valueOf(pNotice.getFines())));	
				String paragraph4 = "  依据"+summary.getLegalBasis()+"之规定，本机关拟对你做出"+penaltyInf+"的行政处罚";
				pNotice.setParagraph4(paragraph4);
				
				String paragraph5 = "  根据《中华人民共和国行政处罚法》第三十一条、第三十二条的规定，你可以自收到本告知书之日起"
									+pNotice.getSumitDate()+"日内到中原区城市管理行政执法局进行陈述、申辩或提交书面陈述、申"
									+ "辩材料，逾期不陈述、申辩的视为放弃陈述申辩权利。";
				pNotice.setParagraph5(paragraph5);
				pNotice.setDate(new Date());
				pNoticeDao.updatePNotice(pNotice);
			}
			
			
			Map<String,Object> variables = new HashMap<String,Object>();
			variables.put("outCome", pNoticeBean.getOutCome());
			taskService.complete(pNoticeBean.getTaskId(),variables);
			
		}
		/**
		 * 通过任务id查找处罚事先告知书
		 */
		@Override
		public PNotice findPNoticeByTaskId(String taskId) {
			
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
			PNotice pNotice = accreditation.getpNotice();
			if(pNotice==null){
				pNotice = new PNotice();
				pNotice.setSummary(accreditation.getSummary());
				pNotice.setSumitDate(3);
				
				pNoticeDao.savePNotice(pNotice);
				
				pNotice.setAccreditation(accreditation);
				accreditation.setpNotice(pNotice);
				pNoticeDao.updatePNotice(pNotice);
				accreditationService.updateAccreditation(accreditation);
			}
			if(pNotice.getIllegalContent()==null||"".equals(pNotice.getIllegalContent().trim())){
				if(accreditation.getUnitName()!=null&&!"".equals(accreditation.getUnitName().trim())){
					pNotice.setIllegalContent("     你单位于");
				}else{ 
					pNotice.setIllegalContent("     你于");
				}
			}
			
			return pNotice;
		}


		@Override
		public void saveSubmitTask(PNoticeBean pNoticeBean) {
			String taskId = pNoticeBean.getTaskId();
			PNotice pNotice = this.findPNoticeByTaskId(taskId);
			Map<String,Object> variables = new HashMap<String,Object>();
			//获取连线的名称
			String outCome = pNoticeBean.getOutCome();
			if(!outCome.equals("下载审批后的处罚事先告知书")){

				if(outCome.equals("驳回")&&pNoticeBean.getLegalSuggest()!=null&&!"".equals(pNoticeBean.getLegalSuggest().trim())){
					pNotice.setLegalSuggest(pNoticeBean.getLegalSuggest());
					pNotice.setLegalFlag(false);
					
					//完成任务的同时设置流程变量，指定下一个任务节点
					
					
				}else if(outCome.equals("批准")&&(pNoticeBean.getLegalSuggest()==null||"".equals(pNoticeBean.getLegalSuggest().trim()))){
					pNotice.setLegalSuggest(null);
					pNotice.setLegalFlag(true);	
					
					if(pNoticeBean.getPdfDocument()==null||(!pNoticeBean.getPdfDocumentContentType().equals("application/pdf")&&!pNoticeBean.getPdfDocumentContentType().equals("application/kswps"))){
						System.out.println("请上传pdf文件");
						try {
							throw new Exception();
						} catch (Exception e) {
							
							e.printStackTrace();
						}
						return;
					}
					PDFDocument pdfDocument = new PDFDocument();
					
					
					String reallyName = pNoticeBean.getPdfDocumentFileName();
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
						FileInputStream is = new FileInputStream(pNoticeBean.getPdfDocument());
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
					pNotice.setPdfDocument(pdfDocument);
					
					
				}else if(!outCome.equals("行政处罚事先告知书报表生成")){
					//出错处理
					System.out.println("批准时不能填写意见，驳回时必须填写意见");
					try {
						throw new Exception();
					} catch (Exception e) {
						
						e.printStackTrace();
					}
					return;
				}
				//完成任务的同时设置流程变量，指定下一个任务节点
				this.updatePNotice(pNotice);
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


        /**
         * 为预览功能进行准备的方法
         * 完善(拼凑) PNoticeBean 里的数据
         */
		public void getFillPNotice(PNoticeBean pNoticeBean, PNotice pNotice) {
			// 当所传入的对象都不为null时再进行赋值操作
			if(pNoticeBean != null && pNotice != null) {
				// 设置第几号
				pNoticeBean.setCaseNum(pNotice.getAccreditation().getId());
				// 设置审批时间（将最后一个人的审批时间格式化）
				// 格式化时间，预览报表、打印报表使用
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
				if(pNotice.getDate() != null) {
					pNoticeBean.setDateStr(sdf.format(pNotice.getDate()));
					//设置罚告字
					SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy");
					pNoticeBean.setYearNum(sdf2.format(pNotice.getDate()));
				}
				
				//拼凑报表第一段内容
				String paragraph1 = "";
				Accreditation accreditation = pNotice.getAccreditation();
				String UnitName = accreditation.getUnitName();
				if(UnitName!=null&&!"".equals(UnitName)){ // 判断当事人是 “单位” 的时候
					paragraph1 = UnitName+":\n经营者姓名:   "+accreditation.getLeRepresentative()+"     营业执照注册号：  "
								+pNotice.getBusLicense()+"\n地址:  "+accreditation.getUnitAddress();
				}else{ // 判断当事人是 “个人”
					paragraph1 = "当事人："+accreditation.getPersonnelName()+"     性别："+accreditation.getSex()+"     年龄："+accreditation.getUserAge()+"\n身份证号:  "+accreditation.getIdNumber()
							+"\n地址："+accreditation.getUserAddress();
				}
				pNoticeBean.setParagraph1(paragraph1.trim() + "\n");
				
				//拼凑报表第二段内容
				String paragraph2 = "";
				paragraph2 = paragraph2 + "         " + pNoticeBean.getIllegalContent().trim();
				//System.out.println("打印summary：" + pNotice.getSummary().getName());
				paragraph2 = paragraph2 + "该行为违反了" + pNotice.getSummary().getVioRegulations() + "之规定。";
				pNoticeBean.setParagraph2(paragraph2 + "\n");
				//拼凑报表第三段内容
				String paragraph3 = "";
				if(pNoticeBean.getIllegalstyleId() != 0) { // 有违法类型的时候
					IllegalStyle illegalStyle = illegalStyleService.findIllegaStyleById(pNoticeBean.getIllegalstyleId());
					paragraph3 = paragraph3 + "         从你的违法事实、性质、情节、社会危害程度和证据看， 你的违法行为属于" + illegalStyle.getName() + "。";
				} else {
					pNoticeBean.setParagraph3("");
				}
				if("".equals(paragraph3)) {
					pNoticeBean.setParagraph3(null);
				} else{
					pNoticeBean.setParagraph3(paragraph3 + "\n");
				}
				
				//拼凑报表第四段内容
				String paragraph4 = "";
				paragraph4 = paragraph4 + "         根据" + pNotice.getSummary().getLegalBasis() + "之规定，本机关拟对你作出罚款 ";
				if(!pNoticeBean.getFines().trim().equals("") && pNoticeBean.getFines() != null) {
					paragraph4 = paragraph4 + AccountChange.digitUppercase(Double.valueOf(pNoticeBean.getFines())); //将数字转换成大写金额
				}
				// 设置其他处罚信息
				paragraph4 = paragraph4 + "、 " + pNoticeBean.getOtherpenaltyInf();
				paragraph4 = paragraph4 + " 的行政处罚。\n";
				pNoticeBean.setParagraph4(paragraph4);
				
				//拼凑报表第五段内容
				String paragraph5 = "";
				paragraph5 = paragraph5 + "         根据《中华人民共和国行政处罚》第三十一条、第三十二条的规定，";
				// “个人”
				if(pNotice.getAccreditation().getUnitName().trim().equals("") || pNotice.getAccreditation().getUnitName() == null) {
					paragraph5 = paragraph5 + "你";
				} else { // 单位
					paragraph5 = paragraph5 + "你单位";
				}
				paragraph5 = paragraph5 + "可以自收到本告知书之日起 " + pNoticeBean.getSumitDate() + " 日内到到中原区城市管理行政执法局提交庭审申请书或"
						+ "提交书面陈述、申辩材料，逾期不申请听证或陈述、申辩的视为放弃申请听证或陈述申辩权利。";
				pNoticeBean.setParagraph5(paragraph5 + "\n");
			}
		}
		/**
         * 为预览查询时，提供的方法，根据id 进行查询
         * 完善(拼凑) PNoticeBean 里的数据
         */
		public void getFillPNoforQuery(PNoticeBean pNoticeBean, PNotice pNotice) {
			if(pNotice != null) {
				// 设置第几号
				pNoticeBean.setCaseNum(pNotice.getAccreditation().getId());
				// 设置审批时间（将最后一个人的审批时间格式化）
				// 格式化时间，预览报表、打印报表使用
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
				if(pNotice.getDate() != null) {
					pNoticeBean.setDateStr(sdf.format(pNotice.getDate()));
					//设置罚告字
					SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy");
					pNoticeBean.setYearNum(sdf2.format(pNotice.getDate()));
				}
				//开始拼凑,每次取出数据时，都将其 trim，然后再添加空格
				String paragraph1 = pNotice.getParagraph1();
				String paragraph2 = pNotice.getParagraph2();
				String paragraph3 = pNotice.getParagraph3();
				String paragraph4 = pNotice.getParagraph4();
				String paragraph5 = pNotice.getParagraph5();
				if(paragraph1 != null) {
					pNoticeBean.setParagraph1(paragraph1.trim() + "\n");
				}
				if(paragraph2 != null) {
					pNoticeBean.setParagraph2("         " + paragraph2.trim() + "\n");
				}
				if(paragraph3 != null) {
					pNoticeBean.setParagraph3("         " + paragraph3.trim() + "\n");
				}
				if(paragraph4 != null) {
					pNoticeBean.setParagraph4("         " + paragraph4.trim() + "\n");
				}
				if(paragraph5 != null) {
					pNoticeBean.setParagraph5("         " + paragraph5.trim() + "\n");
				}
			}
		}
		


		@Override
		public PNotice getPNoticeById(String id) {
			
			return pNoticeDao.findPNoticeById(Long.parseLong(id));
		}



		@Override
		public PNotice findPNoticeByPNoticeId(Long pNoticeId) {
			PNotice pNotice = pNoticeDao.findPNoticeById(pNoticeId);
			return pNotice;
		}

		@Override
		public void updatePNotice(PNotice pNotice) {
			pNoticeDao.updatePNotice(pNotice);
			
		}
		
}
