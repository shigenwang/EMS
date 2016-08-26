package cn.future.ssh.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/** 处罚审批表 */
public class PTable {
	/** id */
	private Long id;
	private String basicCase; // 基本案情
	private String illegalGrade; // 违法行为的等次
	private String legalBasis; // 法律依据

	private Summary summary; // 行政处罚裁量标准表(多对一的关系)+++++++++++++++++++++++++
	private String client; // 当事人
	private Accreditation accreditation; // 立案审批表（一对一的对关系）++++++++++++++++++++++
	private String factAndSuggestion; // 事实和处理意见
	private String caseLevel; // 案件性质

	private String captainSuggest; // 中队长意见
	private String legSuggest; // 法制科意见
	private String bigCaptainSuggest; // 大队长意见

	private String ICSuggest; // 业委会意见
	private String CRCSuggest; // 案审委意见
	private String legGuideSuggest; // 法制科指导意见
	
	private Boolean legalFlag; // 法制科标记
	
	private Boolean depLeaderFlag;  //部门领导标记
	private String depLeaderName;  //部门领导姓名
	private Date depLeaderDate;  //部门领导审批日期
	private String depLeaderSuggest;	//部门领导建议
	private Boolean indCommFlag; // 业委会标记
	private Boolean caseRevCommFlag; // 案审委标记
	private Boolean hostFlag; // 主办标记
	private Boolean joinFlag; // 协办标记
	private Boolean captainFlag; // 中队标记
	private Boolean chiefFlag; // 大队标记
	private Boolean printFlag; // 打印标记
	private String captainName;	//中队长姓名
	private String legalName;	//法制科姓名
	private String bigCaptainName;	//大队长姓名
	private String indCommNames;//业委会所有审批人员的姓名
	private String caseRevCommNames; // 案审委所有审批人员的姓名
	
    private Date hostDate;//主办和协办审批的时间
    private Date captainDate;//中队的审批时间
    private Date legalDate;//法制科的审批时间
    private Date bigCaptainDate;//大队长审批
    private String ICOrCRCDecide;//业委会或案审委集体决定
   
    private Boolean proofServicePTFlag;			//送达回证标记
    private Boolean finalReportFlag;	//案件调查终结报告标记
    private Boolean recordSheetFlag;	//调查终结报告笔录纸标记
  
    
    private Set<Document> proofServicePT = new HashSet<Document>();			//送达回证
	private Set<Document> finalReport = new HashSet<Document>();//案件调查终结报告
    private Set<Document> recordSheet = new HashSet<Document>();	//调查终结报告笔录纸

	public Set<Document> getProofServicePT() {
		return proofServicePT;
	}

	public void setProofServicePT(Set<Document> proofServicePT) {
		this.proofServicePT = proofServicePT;
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

	
	public Boolean getProofServicePTFlag() {
		return proofServicePTFlag;
	}

	public void setProofServicePTFlag(Boolean proofServicePTFlag) {
		this.proofServicePTFlag = proofServicePTFlag;
	}

	
	public Set<Document> getFinalReport() {
		return finalReport;
	}

	public void setFinalReport(Set<Document> finalReport) {
		this.finalReport = finalReport;
	}

	public Set<Document> getRecordSheet() {
		return recordSheet;
	}

	public void setRecordSheet(Set<Document> recordSheet) {
		this.recordSheet = recordSheet;
	}

	public Boolean getDepLeaderFlag() {
		return depLeaderFlag;
	}

	public void setDepLeaderFlag(Boolean depLeaderFlag) {
		this.depLeaderFlag = depLeaderFlag;
	}

	public String getDepLeaderName() {
		return depLeaderName;
	}

	public void setDepLeaderName(String depLeaderName) {
		this.depLeaderName = depLeaderName;
	}

	

	public Date getDepLeaderDate() {
		return depLeaderDate;
	}

	public void setDepLeaderDate(Date depLeaderDate) {
		this.depLeaderDate = depLeaderDate;
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

	public Boolean getCaptainFlag() {
		return captainFlag;
	}

	public void setCaptainFlag(Boolean captainFlag) {
		this.captainFlag = captainFlag;
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

	public String getCurrentSign() {
		return currentSign;
	}

	public void setCurrentSign(String currentSign) {
		this.currentSign = currentSign;
	}

	private String currentSign;// 当前审核人标记

	public String getCaseLevel() {
		return caseLevel;
	}

	public void setCaseLevel(String caseLevel) {
		this.caseLevel = caseLevel;
	}

	public String getFactAndSuggestion() {
		return factAndSuggestion;
	}

	public void setFactAndSuggestion(String factAndSuggestion) {
		this.factAndSuggestion = factAndSuggestion;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBasicCase() {
		return basicCase;
	}

	public void setBasicCase(String basicCase) {
		this.basicCase = basicCase;
	}

	public String getLegalBasis() {
		return legalBasis;
	}

	public void setLegalBasis(String legalBasis) {
		this.legalBasis = legalBasis;
	}

	public String getIllegalGrade() {
		return illegalGrade;
	}

	public void setIllegalGrade(String illegalGrade) {
		this.illegalGrade = illegalGrade;
	}

	public Boolean getLegalFlag() {
		return legalFlag;
	}

	public void setLegalFlag(Boolean legalFlag) {
		this.legalFlag = legalFlag;
	}

	public Boolean getIndCommFlag() {
		return indCommFlag;
	}

	public void setIndCommFlag(Boolean indCommFlag) {
		this.indCommFlag = indCommFlag;
	}

	public Boolean getCaseRevCommFlag() {
		return caseRevCommFlag;
	}

	public void setCaseRevCommFlag(Boolean caseRevCommFlag) {
		this.caseRevCommFlag = caseRevCommFlag;
	}

	public Boolean getHostFlag() {
		return hostFlag;
	}

	public void setHostFlag(Boolean hostFlag) {
		this.hostFlag = hostFlag;
	}

	public Boolean getJoinFlag() {
		return joinFlag;
	}

	public void setJoinFlag(Boolean joinFlag) {
		this.joinFlag = joinFlag;
	}

	public Boolean getChiefFlag() {
		return chiefFlag;
	}

	public void setChiefFlag(Boolean chiefFlag) {
		this.chiefFlag = chiefFlag;
	}

	public Boolean getPrintFlag() {
		return printFlag;
	}

	public void setPrintFlag(Boolean printFlag) {
		this.printFlag = printFlag;
	}

	public Summary getSummary() {
		return summary;
	}

	public void setSummary(Summary summary) {
		this.summary = summary;
	}

	public Accreditation getAccreditation() {
		return accreditation;
	}

	public void setAccreditation(Accreditation accreditation) {
		this.accreditation = accreditation;
	}
}
