package cn.future.ssh.web.form;

import java.io.File;
import java.util.Date;
import java.util.List;

public class PDecideBean {
	
	private String taskId;
	private Long pDecideId;
	private String outCome;
	private String caseLevel;
	private Integer actualFines;
	private Long accreditationId;// 字号
	
	private String caseIntroduction;        //案件简介
	private String payBank;     //交款银行
	private String bankAccount;			//银行账号
	private String adreconAddress1;     //行政复议地点1
	private String adreconAddress2;     //行政复印地点2
	private Integer adreconTimeLimit;	//行政复议时限天数
    private String legalSuggest;//法制部门意见
    
	private String paragraph1;
	private String paragraph2;		
	private String paragraph3;
	
	private String paragraph4;
	private String paragraph5;
	private String paragraph6;
	private String paragraph7;
	private String paragraph8;
	
	private String yearNum; // 罚决字
	private Date date;       //时间
	private String dateStr; // 格式化时间
	
	private File pdfDocument;		//对应的文书
	private String pdfDocumentFileName;
	private String pdfDocumentContentType;
	private String pdfDocumentName;
	
	private String reallyName;		//上传文件的文件名

	
	
	public File getPdfDocument() {
		return pdfDocument;
	}
	public void setPdfDocument(File pdfDocument) {
		this.pdfDocument = pdfDocument;
	}
	public String getPdfDocumentFileName() {
		return pdfDocumentFileName;
	}
	public void setPdfDocumentFileName(String pdfDocumentFileName) {
		this.pdfDocumentFileName = pdfDocumentFileName;
	}
	public String getPdfDocumentContentType() {
		return pdfDocumentContentType;
	}
	public void setPdfDocumentContentType(String pdfDocumentContentType) {
		this.pdfDocumentContentType = pdfDocumentContentType;
	}
	public String getPdfDocumentName() {
		return pdfDocumentName;
	}
	public void setPdfDocumentName(String pdfDocumentName) {
		this.pdfDocumentName = pdfDocumentName;
	}
	public String getReallyName() {
		return reallyName;
	}
	public void setReallyName(String reallyName) {
		this.reallyName = reallyName;
	}
	public Long getAccreditationId() {
		return accreditationId;
	}
	public void setAccreditationId(Long accreditationId) {
		this.accreditationId = accreditationId;
	}
	public String getYearNum() {
		return yearNum;
	}
	public void setYearNum(String yearNum) {
		this.yearNum = yearNum;
	}
	public String getDateStr() {
		return dateStr;
	}
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getLegalSuggest() {
		return legalSuggest;
	}
	public void setLegalSuggest(String legalSuggest) {
		this.legalSuggest = legalSuggest;
	}
	public Integer getActualFines() {
		return actualFines;
	}
	public void setActualFines(Integer actualFines) {
		this.actualFines = actualFines;
	}
	
	
	public String getParagraph1() {
		return paragraph1;
	}
	public void setParagraph1(String paragraph1) {
		this.paragraph1 = paragraph1;
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
	public Integer getAdreconTimeLimit() {
		return adreconTimeLimit;
	}
	public void setAdreconTimeLimit(Integer adreconTimeLimit) {
		this.adreconTimeLimit = adreconTimeLimit;
	}
	public String getCaseIntroduction() {
		return caseIntroduction;
	}
	public void setCaseIntroduction(String caseIntroduction) {
		this.caseIntroduction = caseIntroduction;
	}
	
	public Long getPDecideId() {
		return pDecideId;
	}
	public void setPDecideId(Long pDecideId) {
		this.pDecideId = pDecideId;
	}
	public String getPayBank() {
		return payBank;
	}
	public void setPayBank(String payBank) {
		this.payBank = payBank;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
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
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
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
	
	
}
