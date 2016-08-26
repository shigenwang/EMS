package cn.future.ssh.domain;

import java.util.Date;

/**处罚决定书*/
public class PDecide {
	/**id*/
	private Long id;
	private String  caseIntroduction;        //案件简介
	private String payBank;     //缴款银行
	private String bankAccount;			//银行账号
	private String adreconAddress1;     //行政复议地点1
	private String adreconAddress2;     //行政复印地点2
	private Integer adreconTimeLimit;	//行政复议时限
	private Integer actualFines;		//实际罚款
    private String legalSuggest;		//法制部门意见
	
	private Date date;       //决定书的生成时间
	private Boolean legalFlag;      //法制科标记
	private Boolean printFlag;      //打印标记
	private Accreditation accreditation;    //立案审批表（一对一的对关系）+++++++++++++++++++++++++
	private Summary summary;     //行政处罚裁量标准表(多对一的关系)+++++++++++++++++
	
	
	private String paragraph2;		
	private String paragraph3;
	
	private String paragraph4;
	private String paragraph5;
	private String paragraph6;
	private String paragraph7;
	private String paragraph8;
	
	private PDFDocument pdfDocument;//对应的文书
	
	public PDFDocument getPdfDocument() {
		return pdfDocument;
	}

	public void setPdfDocument(PDFDocument pdfDocument) {
		this.pdfDocument = pdfDocument;
	}

	public Integer getActualFines() {
		return actualFines;
	}

	public void setActualFines(Integer actualFines) {
		this.actualFines = actualFines;
	}

	public String getParagraph2() {
		return paragraph2;
	}

	public void setParagraph2(String paragraph2) {
		this.paragraph2 = paragraph2;
	}

	public String getParagraph3() {
		return paragraph3;
	}

	public void setParagraph3(String paragraph3) {
		this.paragraph3 = paragraph3;
	}

	public String getParagraph4() {
		return paragraph4;
	}

	public void setParagraph4(String paragraph4) {
		this.paragraph4 = paragraph4;
	}

	public String getParagraph5() {
		return paragraph5;
	}

	public void setParagraph5(String paragraph5) {
		this.paragraph5 = paragraph5;
	}

	public String getParagraph6() {
		return paragraph6;
	}

	public void setParagraph6(String paragraph6) {
		this.paragraph6 = paragraph6;
	}

	public String getParagraph7() {
		return paragraph7;
	}

	public void setParagraph7(String paragraph7) {
		this.paragraph7 = paragraph7;
	}

	public String getParagraph8() {
		return paragraph8;
	}

	public void setParagraph8(String paragraph8) {
		this.paragraph8 = paragraph8;
	}

	public String getCaseIntroduction() {
		return caseIntroduction;
	}

	public void setCaseIntroduction(String caseIntroduction) {
		this.caseIntroduction = caseIntroduction;
	}

	public Integer getAdreconTimeLimit() {
		return adreconTimeLimit;
	}

	public void setAdreconTimeLimit(Integer adreconTimeLimit) {
		this.adreconTimeLimit = adreconTimeLimit;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	
	public String getPayBank() {
		return payBank;
	}

	public void setPayBank(String payBank) {
		this.payBank = payBank;
	}

	public String getAdreconAddress1() {
		return adreconAddress1;
	}

	public void setAdreconAddress1(String adreconAddress1) {
		this.adreconAddress1 = adreconAddress1;
	}

	public String getAdreconAddress2() {
		return adreconAddress2;
	}

	public void setAdreconAddress2(String adreconAddress2) {
		this.adreconAddress2 = adreconAddress2;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Boolean getLegalFlag() {
		return legalFlag;
	}

	public void setLegalFlag(Boolean legalFlag) {
		this.legalFlag = legalFlag;
	}

	public Boolean getPrintFlag() {
		return printFlag;
	}

	public void setPrintFlag(Boolean printFlag) {
		this.printFlag = printFlag;
	}

	public Accreditation getAccreditation() {
		return accreditation;
	}

	public void setAccreditation(Accreditation accreditation) {
		this.accreditation = accreditation;
	}

	public Summary getSummary() {
		return summary;
	}

	public void setSummary(Summary summary) {
		this.summary = summary;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLegalSuggest() {
		return legalSuggest;
	}

	public void setLegalSuggest(String legalSuggest) {
		this.legalSuggest = legalSuggest;
	}
	
}
