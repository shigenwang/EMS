package cn.future.ssh.web.form;


import java.io.File;
import java.util.List;

import java.util.Date;



public class PTableBean {
	
	private String taskId;
	private Long  pTableId;
	private String outCome;
	private String caseLevel;			//案件性质
	private String basicCase;         //基本案情
	private String illegalGrade;      //违法行为的等次
	private String legalBasis;			//法律依据
	private String client;		//当事人
	
	private String summaryName;  // 案由
	// 各种姓名
	private String squadronName; // 承办中队
	private String sponsorName;  // 主办
	private String assistantHandleName; // 协办
	private String captainName;	//中队长姓名
	private String legalName;	//法制科姓名
	private String bigCaptainName;	//大队长姓名
	private String depLeaderName;  //部门领导姓名
	private String indCommNames;//业委会所有审批人员的姓名
	private String caseRevCommNames; // 案审委所有审批人员的姓名
	
	// 各种审核时间
	private Date hostDate;//主办和协办审批的时间
    private Date captainDate;//中队的审批时间
    private Date legalDate;//法制科的审批时间
    private Date bigCaptainDate;//大队长审批时间
    private Date depLeaderDate;  //部门领导审批日期
    
    // 各种格式化后的时间（供报表打印使用）
    private String hostDateStr;//  格式化后的主办和协办审批的时间
    private String captainDateStr;// 格式化后的中队的审批时间
    private String legalDateStr;// 格式化后的法制科的审批时间
    private String bigCaptainDateStr;// 格式化后的大队长审批时间
    private String depLeaderDateStr;  //格式化后的部门领导审批日期
    
    // 各种审批意见
    private String hostSuggest; // 主办和协办意见
 	private String captainSuggest;	//中队长意见
 	private String legSuggest;	//法制科意见
 	private String bigCaptainSuggest;	//大队长意见（主管领导）
 	private String depLeaderSuggest;	//部门领导建议
 	private String ICSuggest;			//业委会意见
 	private String CRCSuggest;			//案审委意见
 	private String factAndSuggestion;	//事实和处理意见 
 	private String ICOrCRCDecide;//业委会或案审委集体决定
 	private String legGuideSuggest;	//法制科指导意见
 	
	private List<File> proofServicePT;				//送达回证
	private List<String> proofServicePTFileName;	
	private List<String> proofServicePTContentType;
	
	private List<File> finalReport;				//案件调查终结报告
	private List<String> finalReportFileName;	
	private List<String> finalReportContentType;
	
	private List<File> recordSheet;				//调查终结报告笔录纸
	private List<String> recordSheetFileName;	
	private List<String> recordSheetContentType;
	
	private Boolean proofServicePTFlag;			//送达回证标记
    private Boolean finalReportFlag;	//案件调查终结报告标记
    private Boolean recordSheetFlag;	//调查终结报告笔录纸标记
	public String getHostSuggest() {
		return hostSuggest;
	}



	public void setHostSuggest(String hostSuggest) {
		this.hostSuggest = hostSuggest;
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



	public String getDepLeaderName() {
		return depLeaderName;
	}



	public void setDepLeaderName(String depLeaderName) {
		this.depLeaderName = depLeaderName;
	}



	public String getIndCommNames() {
		return indCommNames;
	}



	public void setIndCommNames(String indCommNames) {
		this.indCommNames = indCommNames;
	}



	public String getCaseRevCommNames() {
		return caseRevCommNames;
	}



	public void setCaseRevCommNames(String caseRevCommNames) {
		this.caseRevCommNames = caseRevCommNames;
	}



	public Date getHostDate() {
		return hostDate;
	}



	public void setHostDate(Date hostDate) {
		this.hostDate = hostDate;
	}



	public Date getCaptainDate() {
		return captainDate;
	}



	public void setCaptainDate(Date captainDate) {
		this.captainDate = captainDate;
	}



	public Date getLegalDate() {
		return legalDate;
	}



	public void setLegalDate(Date legalDate) {
		this.legalDate = legalDate;
	}



	public Date getBigCaptainDate() {
		return bigCaptainDate;
	}



	public void setBigCaptainDate(Date bigCaptainDate) {
		this.bigCaptainDate = bigCaptainDate;
	}



	public Date getDepLeaderDate() {
		return depLeaderDate;
	}



	public void setDepLeaderDate(Date depLeaderDate) {
		this.depLeaderDate = depLeaderDate;
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



	public String getDepLeaderDateStr() {
		return depLeaderDateStr;
	}



	public void setDepLeaderDateStr(String depLeaderDateStr) {
		this.depLeaderDateStr = depLeaderDateStr;
	}



	

    
    private String department;
	
	private String currentSign;//当前审核人标记
	
	private String documentName;
	


	public String getDocumentName() {
		return documentName;
	}



	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}



	public List<File> getProofServicePT() {
		return proofServicePT;
	}



	public void setProofServicePT(List<File> proofServicePT) {
		this.proofServicePT = proofServicePT;
	}



	public Boolean getProofServicePTFlag() {
		return proofServicePTFlag;
	}



	public void setProofServicePTFlag(Boolean proofServicePTFlag) {
		this.proofServicePTFlag = proofServicePTFlag;
	}



	


	public List<String> getProofServicePTFileName() {
		return proofServicePTFileName;
	}



	public void setProofServicePTFileName(List<String> proofServicePTFileName) {
		this.proofServicePTFileName = proofServicePTFileName;
	}



	public List<String> getProofServicePTContentType() {
		return proofServicePTContentType;
	}



	public void setProofServicePTContentType(List<String> proofServicePTContentType) {
		this.proofServicePTContentType = proofServicePTContentType;
	}



	public List<File> getFinalReport() {
		return finalReport;
	}



	public void setFinalReport(List<File> finalReport) {
		this.finalReport = finalReport;
	}



	public List<String> getFinalReportFileName() {
		return finalReportFileName;
	}



	public void setFinalReportFileName(List<String> finalReportFileName) {
		this.finalReportFileName = finalReportFileName;
	}



	public List<String> getFinalReportContentType() {
		return finalReportContentType;
	}



	public void setFinalReportContentType(List<String> finalReportContentType) {
		this.finalReportContentType = finalReportContentType;
	}





	public List<File> getRecordSheet() {
		return recordSheet;
	}



	public void setRecordSheet(List<File> recordSheet) {
		this.recordSheet = recordSheet;
	}






	public List<String> getRecordSheetFileName() {
		return recordSheetFileName;
	}



	public void setRecordSheetFileName(List<String> recordSheetFileName) {
		this.recordSheetFileName = recordSheetFileName;
	}



	public List<String> getRecordSheetContentType() {
		return recordSheetContentType;
	}



	public void setRecordSheetContentType(List<String> recordSheetContentType) {
		this.recordSheetContentType = recordSheetContentType;
	}







	public Boolean getFinalReportFlag() {
		return finalReportFlag;
	}



	public void setFinalReportFlag(Boolean finalReportFlag) {
		this.finalReportFlag = finalReportFlag;
	}



	public Boolean getRecordSheetFlag() {
		return recordSheetFlag;
	}



	public void setRecordSheetFlag(Boolean recordSheetFlag) {
		this.recordSheetFlag = recordSheetFlag;
	}



	public String getDepLeaderSuggest() {
		return depLeaderSuggest;
	}



	public void setDepLeaderSuggest(String depLeaderSuggest) {
		this.depLeaderSuggest = depLeaderSuggest;
	}



	public String getICOrCRCDecide() {
		return ICOrCRCDecide;
	}



	public void setICOrCRCDecide(String iCOrCRCDecide) {
		ICOrCRCDecide = iCOrCRCDecide;
	}



	public String getSummaryName() {
		return summaryName;
	}



	public void setSummaryName(String summaryName) {
		this.summaryName = summaryName;
	}



	public String getSquadronName() {
		return squadronName;
	}



	public void setSquadronName(String squadronName) {
		this.squadronName = squadronName;
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



	
	public String getCurrentSign() {
		return currentSign;
	}



	public void setCurrentSign(String currentSign) {
		this.currentSign = currentSign;
	}



	public Long getpTableId() {
		return pTableId;
	}



	public void setpTableId(Long pTableId) {
		this.pTableId = pTableId;
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



	public String getICSuggest() {
		return ICSuggest;
	}



	public void setICSuggest(String iCSuggest) {
		ICSuggest = iCSuggest;
	}



	public String getCRCSuggest() {
		return CRCSuggest;
	}



	public void setCRCSuggest(String cRCSuggest) {
		CRCSuggest = cRCSuggest;
	}



	public String getLegGuideSuggest() {
		return legGuideSuggest;
	}



	public void setLegGuideSuggest(String legGuideSuggest) {
		this.legGuideSuggest = legGuideSuggest;
	}



	public String getDepartment() {
		return department;
	}



	public void setDepartment(String department) {
		this.department = department;
	}



	public String getClient() {
		return client;
	}

	

	public String getFactAndSuggestion() {
		return factAndSuggestion;
	}



	public void setFactAndSuggestion(String factAndSuggestion) {
		this.factAndSuggestion = factAndSuggestion;
	}



	public void setClient(String client) {
		this.client = client;
	}

	public String getCaseLevel() {
		return caseLevel;
	}

	public void setCaseLevel(String caseLevel) {
		this.caseLevel = caseLevel;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
	
	
	

	public String getBasicCase() {
		return basicCase;
	}

	public void setBasicCase(String basicCase) {
		this.basicCase = basicCase;
	}

	public String getIllegalGrade() {
		return illegalGrade;
	}

	public void setIllegalGrade(String illegalGrade) {
		this.illegalGrade = illegalGrade;
	}

	public String getLegalBasis() {
		return legalBasis;
	}

	public void setLegalBasis(String legalBasis) {
		this.legalBasis = legalBasis;
	}

	public void setOutCome(String outCome) {
		this.outCome = outCome;
	}

	public String getOutCome() {
		return outCome;
	}

	public String getTaskId() {
		return taskId;
	}

	public Long getPTableId() {
		return pTableId;
	}

	public void setPTableId(Long pTableId) {
		this.pTableId = pTableId;
	}


	

	
	
}
