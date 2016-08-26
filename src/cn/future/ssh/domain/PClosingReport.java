package cn.future.ssh.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

 

/**处罚结案报告*/
public class PClosingReport {
	/**id*/
	private Long id;
	private String basicCase;     //基本案件及执行情况
	private Boolean hostFlag;     //主办标记
	private Boolean joinFlag;    //协办标记
	private Boolean chiefFlag;         //大队标记
	private Boolean captainFlag;     //中队标记
	private Boolean legalFlag;      //法制科标记
	private Boolean indCommFlag;   //业委会标记
	private Boolean printFlag;      //打印标记
	private Summary summary;   //行政处罚裁量标准表(多对一的关系)++++++++++++++++++++++++
	private Accreditation accreditation;    //立案审批表（一对一的对关系）+++++++++++++++++++
	private Date paymentDate;				//缴款日期
	
	 private String captainSuggest;	//中队长意见
	 private String legSuggest;	//法制科意见
	 private String bigCaptainSuggest;	//大队长意见
	 private String legGuideSuggest;	//法制科指导意见
	 private String currentSign;//当前审核人标记
	 private String ICSuggest;			//业委会意见
	
	 
	 private String captainName;	//中队长审批人员姓名
	 private String legalName;	//法制科审批人员姓名
	 private String bigCaptainName;	//大队长审批人员姓名
	 private String indCommName;    //业委会审批人员姓名
	 
	 private Date hostDate;//主办和协办审批的时间
	 private Date captainDate;//中队的审批时间
	 private Date legalDate;//法制科的审批时间
	 private Date bigCaptainDate;//大队长审批时间
	 private Date indCommDate;//部门主要领导意见
	 
	 private Boolean proofServicePCFlag;			//送达回证标记
	 private Boolean fineNoteFlag;	//罚款专用票据标记
	
	    
	 
	  private Set<Document> proofServicePC = new HashSet<Document>();;			//送达回证
	  private Set<Document> fineNote = new HashSet<Document>();//罚款专用票据
	 
	  
	  


	public Set<Document> getProofServicePC() {
		return proofServicePC;
	}

	public void setProofServicePC(Set<Document> proofServicePC) {
		this.proofServicePC = proofServicePC;
	}

	public Boolean getFineNoteFlag() {
		return fineNoteFlag;
	}

	public void setFineNoteFlag(Boolean fineNoteFlag) {
		this.fineNoteFlag = fineNoteFlag;
	}

	

	public Boolean getProofServicePCFlag() {
		return proofServicePCFlag;
	}

	public void setProofServicePCFlag(Boolean proofServicePCFlag) {
		this.proofServicePCFlag = proofServicePCFlag;
	}

	public Set<Document> getFineNote() {
		return fineNote;
	}

	public void setFineNote(Set<Document> fineNote) {
		this.fineNote = fineNote;
	}

	public Date getIndCommDate() {
		return indCommDate;
	}

	public void setIndCommDate(Date indCommDate) {
		this.indCommDate = indCommDate;
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

	public String getIndCommName() {
			return indCommName;
		}

		public void setIndCommName(String indCommName) {
			this.indCommName = indCommName;
		}

	public String getICSuggest() {
		return ICSuggest;
	}

	public void setICSuggest(String iCSuggest) {
		ICSuggest = iCSuggest;
	}

	public String getCaptainSuggest() {
		return captainSuggest;
	}

	public void setCaptainSuggest(String captainSuggest) {
		this.captainSuggest = captainSuggest;
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

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getBasicCase() {
		return basicCase;
	}

	public void setBasicCase(String basicCase) {
		this.basicCase = basicCase;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
