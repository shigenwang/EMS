package cn.future.ssh.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**立案审批表*/
public class Accreditation implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**id*/
	private Long id;
	private String unitName;   //单位名称
	private String leRepresentative;     //单位法定代表人或主要负责人
    private String unitTel;        //电话
    private String unitAddress;        //住址
    private String baseCase; 			//基本案情
    
    
    
    private String captainSuggest;	//中队长意见
    private String legSuggest;	//法制科意见
    private String bigCaptainSuggest;	//大队长意见
    private String legGuideSuggest;	//法制科指导意见
    private String currentSign;//当前审核人标记
    
    private String captainName;	//中队长姓名
    private String legalName;	//法制科姓名
    private String bigCaptainName;	//大队长姓名
    
    private Date bigCaptainDate;//大队长审批
    private Date hostDate;//主办和协办审批的时间
    private Date captainDate;//中队的审批时间
    private Date legalDate;//法制科的审批时间
    
    private String personnelName;      //个人姓名
    private String sex;      //个人性别
    private Integer userAge;       //年龄
    private String userAddress;      //住址
    private String idNumber;      //身份证号
    private String userTel;     //个人电话
    
    
    private Boolean hostFlag;     //主办标记
    private Boolean joinFlag;    //协办标记
    private Boolean chiefFlag;       //大队标记
    private Boolean captainFlag;     //中队标记
    private Boolean printFlag;      //打印标记
    private Boolean legalFlag;      //法制科标记
    
    
    
    private Boolean idCardFlag;			//当事人身份证上传标记
    private Boolean enforceCardFlag;	//执法证标记
    private Boolean orderChangeNoticeFlag;	//责令改正通知书标记
    private Boolean recordInquestFlag;		//现场勘验笔录标记
    private Boolean sitePhotosFlag;			//现场照片标记
    private Boolean recordInvFlag;			//调查询问笔录标记
    private Boolean recordPaperFlag;		//笔录纸标记
    private Boolean businessLicenseFlag;		//笔录纸标记
    
    private Document idCard;			//当事人身份证文书
	private Set<Document> enforceCard = new HashSet<Document>();//执法人文书
    private Set<Document> orderChangeNotice = new HashSet<Document>();	//责令改正通知书文书
    private Set<Document> recordInquest = new HashSet<Document>();		//现场勘验笔录文书
    private Set<Document> sitePhotos = new HashSet<Document>();			//现场照片文书
    private Set<Document> recordInv = new HashSet<Document>();			//调查询问笔录文书
    private Set<Document> recordPaper = new HashSet<Document>();		//笔录纸文书
    private Document businessLicense;			//营业执照
    
    private CaseSource caseSource;      //案情来源表（多对一的关系）
    private Summary summary;//行政处罚裁量标准表(一对一的关系)
   
    private Squadron squadron ;	//中队表   多对一的关系
    

	/**处罚事先告知书*/
	private PNotice pNotice;
	/**处罚审批表*/
	private PTable pTable;
	/**处罚决定书*/
	private PDecide pDecide;
	/**处罚结案报告*/
	private PClosingReport pClosingReport;
	/**主办*/
	private Personnel sponsor;
	/**协办*/
	private Personnel assistantHandle;
    
    

    public Boolean getBusinessLicenseFlag() {
		return businessLicenseFlag;
	}
	public void setBusinessLicenseFlag(Boolean businessLicenseFlag) {
		this.businessLicenseFlag = businessLicenseFlag;
	}
	public Document getBusinessLicense() {
		return businessLicense;
	}
	public void setBusinessLicense(Document businessLicense) {
		this.businessLicense = businessLicense;
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
	public Document getIdCard() {
		return idCard;
	}
	public void setIdCard(Document idCard) {
		this.idCard = idCard;
	}

	public Set<Document> getEnforceCard() {
		return enforceCard;
	}
	public void setEnforceCard(Set<Document> enforceCard) {
		this.enforceCard = enforceCard;
	}
	public Set<Document> getOrderChangeNotice() {
		return orderChangeNotice;
	}
	public void setOrderChangeNotice(Set<Document> orderChangeNotice) {
		this.orderChangeNotice = orderChangeNotice;
	}
	public Set<Document> getRecordInquest() {
		return recordInquest;
	}
	public void setRecordInquest(Set<Document> recordInquest) {
		this.recordInquest = recordInquest;
	}
	public Set<Document> getSitePhotos() {
		return sitePhotos;
	}
	public void setSitePhotos(Set<Document> sitePhotos) {
		this.sitePhotos = sitePhotos;
	}
	public Set<Document> getRecordInv() {
		return recordInv;
	}
	public void setRecordInv(Set<Document> recordInv) {
		this.recordInv = recordInv;
	}
	public Set<Document> getRecordPaper() {
		return recordPaper;
	}
	public void setRecordPaper(Set<Document> recordPaper) {
		this.recordPaper = recordPaper;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	
	public Date getBigCaptainDate() {
		return bigCaptainDate;
	}
	public void setBigCaptainDate(Date bigCaptainDate) {
		this.bigCaptainDate = bigCaptainDate;
	}
	public PNotice getpNotice() {
		return pNotice;
	}
	public void setpNotice(PNotice pNotice) {
		this.pNotice = pNotice;
	}
	
	public PTable getpTable() {
		return pTable;
	}
	public void setpTable(PTable pTable) {
		this.pTable = pTable;
	}
	public PDecide getpDecide() {
		return pDecide;
	}
	public void setpDecide(PDecide pDecide) {
		this.pDecide = pDecide;
	}
	public PClosingReport getpClosingReport() {
		return pClosingReport;
	}
	public void setpClosingReport(PClosingReport pClosingReport) {
		this.pClosingReport = pClosingReport;
	}
	public Personnel getSponsor() {
		return sponsor;
	}
	public void setSponsor(Personnel sponsor) {
		this.sponsor = sponsor;
	}
	public Personnel getAssistantHandle() {
		return assistantHandle;
	}
	public void setAssistantHandle(Personnel assistantHandle) {
		this.assistantHandle = assistantHandle;
	}
	
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
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
	public String getLeRepresentative() {
		return leRepresentative;
	}
	public void setLeRepresentative(String leRepresentative) {
		this.leRepresentative = leRepresentative;
	}

	public Squadron getSquadron() {
		return squadron;
	}
	public void setSquadron(Squadron squadron) {
		this.squadron = squadron;
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

	public Integer getUserAge() {
		return userAge;
	}
	public void setUserAge(Integer userAge) {
		this.userAge = userAge;
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
	public CaseSource getCaseSource() {
		return caseSource;
	}
	public void setCaseSource(CaseSource caseSource) {
		this.caseSource = caseSource;
	}
	public Summary getSummary() {
		return summary;
	}
	public void setSummary(Summary summary) {
		this.summary = summary;
	}

	public String getBaseCase() {
		return baseCase;
	}
	public void setBaseCase(String baseCase) {
		this.baseCase = baseCase;
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
	
}
