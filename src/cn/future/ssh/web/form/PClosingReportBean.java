package cn.future.ssh.web.form;

import java.io.File;
import java.util.Date;
import java.util.List;

import cn.future.ssh.domain.Accreditation;



public class PClosingReportBean {

	private String taskId;
	private Long pClosingReportId;
	private String outCome;
	private String caseLevel;
	private Date paymentDate;				//缴款日期
	private String paymentDateSign;         //缴款日期标记
	// 各个角色审批意见
	private String captainSuggest;	//中队长意见
	private String legSuggest;	//法制科意见
	private String bigCaptainSuggest;	//大队长意见（主管领导）
	private String legGuideSuggest;	//法制科指导意见
	private String currentSign;//当前审核人标记
	private String ICSuggest;			//业委会意见
	
	private String basicCase;     //基本案件及执行情况
	private Accreditation accreditation;    //立案审批表
	// 各个角色姓名
	private String sponsorName; // 主办姓名
	private String assistantHandleName; //协办姓名
	private String captainName;	//中队长审批人员姓名
	private String legalName;	//法制科审批人员姓名
	private String bigCaptainName;	//大队长审批人员姓名
	private String indCommName;    //业委会审批人员姓名
	

	// 各角色审批时间（格式化后的时间）
	private String hostDateStr;//主办和协办审批的时间
	private String captainDateStr;//中队的审批时间
	private String legalDateStr;//法制科的审批时间
	private String bigCaptainDateStr;//大队长审批时间
	private String indCommDateStr;//业委会审批时间
	
	
	private List<File> proofServicePC;				//送达回证
	private List<String> proofServicePCFileName;	
	private List<String> proofServicePCContentType;
	
	private List<File> fineNote;				//罚款专用票据
	private List<String> fineNoteFileName;	
	private List<String> fineNoteContentType;
	
	
	private Boolean proofServicePCFlag;			//送达回证标记
    private Boolean fineNoteFlag;	//案件调查终结报告标记
    
    
	private String documentName;


	public String getTaskId() {
		return taskId;
	}


	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}


	public Long getPClosingReportId() {
		return pClosingReportId;
	}


	public void setPClosingReportId(Long pClosingReportId) {
		this.pClosingReportId = pClosingReportId;
	}


	public String getOutCome() {
		return outCome;
	}


	public void setOutCome(String outCome) {
		this.outCome = outCome;
	}


	public String getCaseLevel() {
		return caseLevel;
	}


	public void setCaseLevel(String caseLevel) {
		this.caseLevel = caseLevel;
	}


	public Date getPaymentDate() {
		return paymentDate;
	}


	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}


	public String getPaymentDateSign() {
		return paymentDateSign;
	}


	public void setPaymentDateSign(String paymentDateSign) {
		this.paymentDateSign = paymentDateSign;
	}


	public String getCaptainSuggest() {
		return captainSuggest;
	}


	public void setCaptainSuggest(String captainSuggest) {
		this.captainSuggest = captainSuggest;
	}


	public String getLegSuggest() {
		return legSuggest;
	}


	public void setLegSuggest(String legSuggest) {
		this.legSuggest = legSuggest;
	}


	public String getBigCaptainSuggest() {
		return bigCaptainSuggest;
	}


	public void setBigCaptainSuggest(String bigCaptainSuggest) {
		this.bigCaptainSuggest = bigCaptainSuggest;
	}


	public String getLegGuideSuggest() {
		return legGuideSuggest;
	}


	public void setLegGuideSuggest(String legGuideSuggest) {
		this.legGuideSuggest = legGuideSuggest;
	}


	public String getCurrentSign() {
		return currentSign;
	}


	public void setCurrentSign(String currentSign) {
		this.currentSign = currentSign;
	}


	public String getICSuggest() {
		return ICSuggest;
	}


	public void setICSuggest(String iCSuggest) {
		ICSuggest = iCSuggest;
	}


	public String getBasicCase() {
		return basicCase;
	}


	public void setBasicCase(String basicCase) {
		this.basicCase = basicCase;
	}


	public Accreditation getAccreditation() {
		return accreditation;
	}


	public void setAccreditation(Accreditation accreditation) {
		this.accreditation = accreditation;
	}


	public String getSponsorName() {
		return sponsorName;
	}


	public void setSponsorName(String sponsorName) {
		this.sponsorName = sponsorName;
	}


	public String getAssistantHandleName() {
		return assistantHandleName;
	}


	public void setAssistantHandleName(String assistantHandleName) {
		this.assistantHandleName = assistantHandleName;
	}


	public String getCaptainName() {
		return captainName;
	}


	public void setCaptainName(String captainName) {
		this.captainName = captainName;
	}


	public String getLegalName() {
		return legalName;
	}


	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}


	public String getBigCaptainName() {
		return bigCaptainName;
	}


	public void setBigCaptainName(String bigCaptainName) {
		this.bigCaptainName = bigCaptainName;
	}


	public String getIndCommName() {
		return indCommName;
	}


	public void setIndCommName(String indCommName) {
		this.indCommName = indCommName;
	}


	public String getHostDateStr() {
		return hostDateStr;
	}


	public void setHostDateStr(String hostDateStr) {
		this.hostDateStr = hostDateStr;
	}


	public String getCaptainDateStr() {
		return captainDateStr;
	}


	public void setCaptainDateStr(String captainDateStr) {
		this.captainDateStr = captainDateStr;
	}


	public String getLegalDateStr() {
		return legalDateStr;
	}


	public void setLegalDateStr(String legalDateStr) {
		this.legalDateStr = legalDateStr;
	}


	public String getBigCaptainDateStr() {
		return bigCaptainDateStr;
	}


	public void setBigCaptainDateStr(String bigCaptainDateStr) {
		this.bigCaptainDateStr = bigCaptainDateStr;
	}


	public String getIndCommDateStr() {
		return indCommDateStr;
	}


	public void setIndCommDateStr(String indCommDateStr) {
		this.indCommDateStr = indCommDateStr;
	}


	public List<File> getProofServicePC() {
		return proofServicePC;
	}


	public void setProofServicePC(List<File> proofServicePC) {
		this.proofServicePC = proofServicePC;
	}


	public List<String> getProofServicePCFileName() {
		return proofServicePCFileName;
	}


	public void setProofServicePCFileName(List<String> proofServicePCFileName) {
		this.proofServicePCFileName = proofServicePCFileName;
	}


	public List<String> getProofServicePCContentType() {
		return proofServicePCContentType;
	}


	public void setProofServicePCContentType(List<String> proofServicePCContentType) {
		this.proofServicePCContentType = proofServicePCContentType;
	}


	public List<File> getFineNote() {
		return fineNote;
	}


	public void setFineNote(List<File> fineNote) {
		this.fineNote = fineNote;
	}


	public List<String> getFineNoteFileName() {
		return fineNoteFileName;
	}


	public void setFineNoteFileName(List<String> fineNoteFileName) {
		this.fineNoteFileName = fineNoteFileName;
	}


	public List<String> getFineNoteContentType() {
		return fineNoteContentType;
	}


	public void setFineNoteContentType(List<String> fineNoteContentType) {
		this.fineNoteContentType = fineNoteContentType;
	}


	public Boolean getProofServicePCFlag() {
		return proofServicePCFlag;
	}


	public void setProofServicePCFlag(Boolean proofServicePCFlag) {
		this.proofServicePCFlag = proofServicePCFlag;
	}


	public Boolean getFineNoteFlag() {
		return fineNoteFlag;
	}


	public void setFineNoteFlag(Boolean fineNoteFlag) {
		this.fineNoteFlag = fineNoteFlag;
	}


	public String getDocumentName() {
		return documentName;
	}


	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
    
	
}
