package cn.future.ssh.web.form;

import java.io.File;
import java.util.Date;
import java.util.List;

import cn.future.ssh.domain.CaseSource;
import cn.future.ssh.domain.Document;
import cn.future.ssh.domain.PClosingReport;
import cn.future.ssh.domain.PDecide;
import cn.future.ssh.domain.PNotice;
import cn.future.ssh.domain.PTable;
/**
 * 
 * 整理好的立案审批表
 */
public class AccreditationBean {
	private Long accreditationId;
	private Long sponsorId;
	private Long assistantHandleId;
	private String taskId;
	private String outCome;
	private Long summaryId;			//裁量阶次id
	
	private Long caseSourceId;		//案情来源id
	
	private String unitName;   //单位名称
	private String leRepresentative;     //单位法定代表人或主要负责人
    private String unitTel;        //电话
    private String unitAddress;        //住址
   
    private Boolean hostFlag;     //主办标记
    private Boolean joinFlag;    //协办标记
    private Boolean chiefFlag;       //大队标记
    private Boolean captainFlag;     //中队标记
    private Boolean printFlag;      //打印标记
    private Boolean legalFlag;      //法制科标记
    private String captainName;	//中队长姓名
    private String legName;	//法制科意见姓名
    private String bigCaptainName;	//大队长姓名
    private String personnelName;      //个人姓名
    private String sex;      //个人性别
    private Integer userAge;       //年龄
    private String userAddress;      //住址
    private String idNumber;      //身份证号
    private String userTel;     //个人电话
   
 //   private String suggestion;	//指导意见
   
    private String currentSign;//当前审核人标记
    private String sponsorName;//主办人姓名
    private String assistantHandleName;//协办人姓名
    private String caseSourceName;//案件来源名称
	/*private Personnel sponsor;  // 主办
	private Personnel assistantHandle; // 协办
*/	private CaseSource caseSource;      //案情来源表
	
    private Date hostDate;//主办和协办审批的时间
    private Date captainDate;//中队的审批时间
    private Date bigCaptainDate;//大队长的审批时间
    
    private String hostDateStr; // 格式化的时间
    private String captainDateStr; 
    private String bigCaptainDateStr;
    private String legalDateStr;
    private String yearNum; // 案立字
    
    private String baseCase;//基本案情
    private Date legalDate;	//法制科意见
    private String captainSuggest;	//中队长意见
    private String legSuggest;	//法制科意见
    private String bigCaptainSuggest;	//大队长意见
    private String legGuideSuggest;	//法制科指导意见
    
    
    private File idCard;			//当事人身份证			
    private String idCardContentType;			
    private String idCardFileName;			
    
    private File businessLicense;			//营业执照		
    private String businessLicenseContentType;			
    private String businessLicenseFileName;	
    			
    
	private List<File> enforceCard;				//执法人文书
	private List<String> enforceCardFileName;	
	private List<String> enforceCardContentType;
    
	private List<File> orderChangeNotice;				//责令改正通知书文书
	private List<String> orderChangeNoticeFileName;	
	private List<String> orderChangeNoticeContentType;
    
	private List<File> recordInquest;				//现场勘验笔录文书
	private List<String> recordInquestFileName;	
	private List<String> recordInquestContentType;
	
	private List<File> sitePhotos;				//现场照片文书
	private List<String> sitePhotosFileName;	
	private List<String> sitePhotosContentType;
	
	private List<File> recordInv;				//调查询问笔录文书
	private List<String> recordInvFileName;	
	private List<String> recordInvContentType;
	
	private List<File> recordPaper;				//笔录纸文书
	private List<String> recordPaperFileName;	
	private List<String> recordPaperContentType;
	

	private Boolean idCardFlag;			//当事人身份证上传标记
	private Boolean enforceCardFlag;	//执法证标记
	private Boolean orderChangeNoticeFlag;	//责令改正通知书标记
	private Boolean recordInquestFlag;		//现场勘验笔录标记
	private Boolean sitePhotosFlag;			//现场照片标记
	private Boolean recordInvFlag;			//调查询问笔录标记
	private Boolean recordPaperFlag;		//笔录纸标记
	private Boolean businessLicenseFlag;	//营业执照标记
	/**处罚事先告知书*/
	private PNotice pNotice;
	/**处罚审批表*/
	private PTable pTable;
	/**处罚决定书*/
	private PDecide pDecide;
	/**处罚结案报告*/
	private PClosingReport pClosingReport;
	
	private String documentName;
	
	
	
	public Boolean getBusinessLicenseFlag() {
		return businessLicenseFlag;
	}
	public void setBusinessLicenseFlag(Boolean businessLicenseFlag) {
		this.businessLicenseFlag = businessLicenseFlag;
	}
	public File getBusinessLicense() {
		return businessLicense;
	}
	public void setBusinessLicense(File businessLicense) {
		this.businessLicense = businessLicense;
	}
	public String getBusinessLicenseContentType() {
		return businessLicenseContentType;
	}
	public void setBusinessLicenseContentType(String businessLicenseContentType) {
		this.businessLicenseContentType = businessLicenseContentType;
	}
	public String getBusinessLicenseFileName() {
		return businessLicenseFileName;
	}
	public void setBusinessLicenseFileName(String businessLicenseFileName) {
		this.businessLicenseFileName = businessLicenseFileName;
	}
	public PNotice getPNotice() {
		return pNotice;
	}
	public void setPNotice(PNotice pNotice) {
		this.pNotice = pNotice;
	}
	public PTable getPTable() {
		return pTable;
	}
	public void setPTable(PTable pTable) {
		this.pTable = pTable;
	}
	public PDecide getPDecide() {
		return pDecide;
	}
	public void setPDecide(PDecide pDecide) {
		this.pDecide = pDecide;
	}
	public PClosingReport getPClosingReport() {
		return pClosingReport;
	}
	public void setPClosingReport(PClosingReport pClosingReport) {
		this.pClosingReport = pClosingReport;
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
	public String getBigCaptainDateStr() {
		return bigCaptainDateStr;
	}
	public void setBigCaptainDateStr(String bigCaptainDateStr) {
		this.bigCaptainDateStr = bigCaptainDateStr;
	}
	public String getLegalDateStr() {
		return legalDateStr;
	}
	public void setLegalDateStr(String legalDateStr) {
		this.legalDateStr = legalDateStr;
	}
	public String getYearNum() {
		return yearNum;
	}
	public void setYearNum(String yearNum) {
		this.yearNum = yearNum;
	}
	public Boolean getIdCardFlag() {
		return idCardFlag;
	}
	public void setIdCardFlag(Boolean idCardFlag) {
		this.idCardFlag = idCardFlag;
	}
	public Boolean getEnforceCardFlag() {
		return enforceCardFlag;
	}
	public void setEnforceCardFlag(Boolean enforceCardFlag) {
		this.enforceCardFlag = enforceCardFlag;
	}
	public Boolean getOrderChangeNoticeFlag() {
		return orderChangeNoticeFlag;
	}
	public void setOrderChangeNoticeFlag(Boolean orderChangeNoticeFlag) {
		this.orderChangeNoticeFlag = orderChangeNoticeFlag;
	}
	public Boolean getRecordInquestFlag() {
		return recordInquestFlag;
	}
	public void setRecordInquestFlag(Boolean recordInquestFlag) {
		this.recordInquestFlag = recordInquestFlag;
	}
	public Boolean getSitePhotosFlag() {
		return sitePhotosFlag;
	}
	public void setSitePhotosFlag(Boolean sitePhotosFlag) {
		this.sitePhotosFlag = sitePhotosFlag;
	}
	public Boolean getRecordInvFlag() {
		return recordInvFlag;
	}
	public void setRecordInvFlag(Boolean recordInvFlag) {
		this.recordInvFlag = recordInvFlag;
	}
	public Boolean getRecordPaperFlag() {
		return recordPaperFlag;
	}
	public void setRecordPaperFlag(Boolean recordPaperFlag) {
		this.recordPaperFlag = recordPaperFlag;
	}
	public String getDocumentName() {
		return documentName;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	public String getIdCardContentType() {
		return idCardContentType;
	}
	public void setIdCardContentType(String idCardContentType) {
		this.idCardContentType = idCardContentType;
	}
	public String getIdCardFileName() {
		return idCardFileName;
	}
	public void setIdCardFileName(String idCardFileName) {
		this.idCardFileName = idCardFileName;
	}
	public File getIdCard() {
		return idCard;
	}
	public void setIdCard(File idCard) {
		this.idCard = idCard;
	}
	public List<File> getEnforceCard() {
		return enforceCard;
	}
	public void setEnforceCard(List<File> enforceCard) {
		this.enforceCard = enforceCard;
	}
	public List<String> getEnforceCardFileName() {
		return enforceCardFileName;
	}
	public void setEnforceCardFileName(List<String> enforceCardFileName) {
		this.enforceCardFileName = enforceCardFileName;
	}
	public List<String> getEnforceCardContentType() {
		return enforceCardContentType;
	}
	public void setEnforceCardContentType(List<String> enforceCardContentType) {
		this.enforceCardContentType = enforceCardContentType;
	}
	public List<File> getOrderChangeNotice() {
		return orderChangeNotice;
	}
	public void setOrderChangeNotice(List<File> orderChangeNotice) {
		this.orderChangeNotice = orderChangeNotice;
	}
	public List<String> getOrderChangeNoticeFileName() {
		return orderChangeNoticeFileName;
	}
	public void setOrderChangeNoticeFileName(List<String> orderChangeNoticeFileName) {
		this.orderChangeNoticeFileName = orderChangeNoticeFileName;
	}
	public List<String> getOrderChangeNoticeContentType() {
		return orderChangeNoticeContentType;
	}
	public void setOrderChangeNoticeContentType(
			List<String> orderChangeNoticeContentType) {
		this.orderChangeNoticeContentType = orderChangeNoticeContentType;
	}
	public List<File> getRecordInquest() {
		return recordInquest;
	}
	public void setRecordInquest(List<File> recordInquest) {
		this.recordInquest = recordInquest;
	}
	public List<String> getRecordInquestFileName() {
		return recordInquestFileName;
	}
	public void setRecordInquestFileName(List<String> recordInquestFileName) {
		this.recordInquestFileName = recordInquestFileName;
	}
	public List<String> getRecordInquestContentType() {
		return recordInquestContentType;
	}
	public void setRecordInquestContentType(List<String> recordInquestContentType) {
		this.recordInquestContentType = recordInquestContentType;
	}
	public List<File> getSitePhotos() {
		return sitePhotos;
	}
	public void setSitePhotos(List<File> sitePhotos) {
		this.sitePhotos = sitePhotos;
	}
	public List<String> getSitePhotosFileName() {
		return sitePhotosFileName;
	}
	public void setSitePhotosFileName(List<String> sitePhotosFileName) {
		this.sitePhotosFileName = sitePhotosFileName;
	}
	public List<String> getSitePhotosContentType() {
		return sitePhotosContentType;
	}
	public void setSitePhotosContentType(List<String> sitePhotosContentType) {
		this.sitePhotosContentType = sitePhotosContentType;
	}
	public List<File> getRecordInv() {
		return recordInv;
	}
	public void setRecordInv(List<File> recordInv) {
		this.recordInv = recordInv;
	}
	public List<String> getRecordInvFileName() {
		return recordInvFileName;
	}
	public void setRecordInvFileName(List<String> recordInvFileName) {
		this.recordInvFileName = recordInvFileName;
	}
	public List<String> getRecordInvContentType() {
		return recordInvContentType;
	}
	public void setRecordInvContentType(List<String> recordInvContentType) {
		this.recordInvContentType = recordInvContentType;
	}
	public List<File> getRecordPaper() {
		return recordPaper;
	}
	public void setRecordPaper(List<File> recordPaper) {
		this.recordPaper = recordPaper;
	}
	public List<String> getRecordPaperFileName() {
		return recordPaperFileName;
	}
	public void setRecordPaperFileName(List<String> recordPaperFileName) {
		this.recordPaperFileName = recordPaperFileName;
	}
	public List<String> getRecordPaperContentType() {
		return recordPaperContentType;
	}
	public void setRecordPaperContentType(List<String> recordPaperContentType) {
		this.recordPaperContentType = recordPaperContentType;
	}
	public Date getLegalDate() {
		return legalDate;
	}
	public void setLegalDate(Date legalDate) {
		this.legalDate = legalDate;
	}
	public String getCaptainName() {
		return captainName;
	}
	public void setCaptainName(String captainName) {
		this.captainName = captainName;
	}
	public String getLegName() {
		return legName;
	}
	public void setLegName(String legName) {
		this.legName = legName;
	}
	public String getBigCaptainName() {
		return bigCaptainName;
	}
	public void setBigCaptainName(String bigCaptainName) {
		this.bigCaptainName = bigCaptainName;
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
	public Boolean getCaptainFlag() {
		return captainFlag;
	}
	public void setCaptainFlag(Boolean captainFlag) {
		this.captainFlag = captainFlag;
	}
	public Boolean getPrintFlag() {
		return printFlag;
	}
	public void setPrintFlag(Boolean printFlag) {
		this.printFlag = printFlag;
	}
	public Boolean getLegalFlag() {
		return legalFlag;
	}
	public void setLegalFlag(Boolean legalFlag) {
		this.legalFlag = legalFlag;
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
	public String getCaseSourceName() {
		return caseSourceName;
	}
	public void setCaseSourceName(String caseSourceName) {
		this.caseSourceName = caseSourceName;
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
	
	
	public String getBaseCase() {
		return baseCase;
	}
	public void setBaseCase(String baseCase) {
		this.baseCase = baseCase;
	}
	
	
	
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getLeRepresentative() {
		return leRepresentative;
	}
	public void setLeRepresentative(String leRepresentative) {
		this.leRepresentative = leRepresentative;
	}
	public String getUnitTel() {
		return unitTel;
	}
	public void setUnitTel(String unitTel) {
		this.unitTel = unitTel;
	}
	public String getUnitAddress() {
		return unitAddress;
	}
	public void setUnitAddress(String unitAddress) {
		this.unitAddress = unitAddress;
	}
	
	public String getPersonnelName() {
		return personnelName;
	}
	public void setPersonnelName(String personnelName) {
		this.personnelName = personnelName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getUserTel() {
		return userTel;
	}
	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}
	
	
	public CaseSource getCaseSource() {
		return caseSource;
	}
	public void setCaseSource(CaseSource caseSource) {
		this.caseSource = caseSource;
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

	
	
	
	public Date getBigCaptainDate() {
		return bigCaptainDate;
	}
	public void setBigCaptainDate(Date bigCaptainDate) {
		this.bigCaptainDate = bigCaptainDate;
	}
	public Long getSummaryId() {
		return summaryId;
	}
	public Long getCaseSourceId() {
		return caseSourceId;
	}
	public String getOutCome() {
		return outCome;
	}
	public void setOutCome(String outCome) {
		this.outCome = outCome;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
	
	public Long getSponsorId() {
		return sponsorId;
	}
	public void setSponsorId(Long sponsorId) {
		this.sponsorId = sponsorId;
	}
	public Long getAssistantHandleId() {
		return assistantHandleId;
	}
	public void setAssistantHandleId(Long assistantHandleId) {
		this.assistantHandleId = assistantHandleId;
	}
	public Integer getUserAge() {
		return userAge;
	}
	public void setUserAge(Integer userAge) {
		this.userAge = userAge;
	}
	public void setSummaryId(Long summaryId) {
		this.summaryId = summaryId;
	}
	public void setCaseSourceId(Long caseSourceId) {
		this.caseSourceId = caseSourceId;
	}
	public Long getAccreditationId() {
		return accreditationId;
	}
	public void setAccreditationId(Long accreditationId) {
		this.accreditationId = accreditationId;
	}
	
	
}
