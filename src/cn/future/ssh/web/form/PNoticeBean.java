package cn.future.ssh.web.form;

import java.io.File;
import java.util.Date;

import cn.future.ssh.domain.IllegalStyle;

/**
 * 
 * 整理好的行政处罚事先告知书
 */
public class PNoticeBean {
	
	private String taskId;

	private String outCome;
	private Long pNoticeId;
	
	//需要映射页面信息的属性
	private String illegalContent;   //违法行为内容
	private String fines;     //罚款金额
	private int sumitDate;   //时限(默认3日)
	private IllegalStyle illegalStyles;     //违法行为类型表 
	private String busLicense;//营业执照或法人登记账号
	private Long illegalstyleId;
	private String otherpenaltyInf;
    private String legalSuggest;//法制部门意见
	// 打印报表使用（给报表凑数据）
	private String paragraph1; // 第一段内容
	private String paragraph2; // 第二段内容
	private String paragraph3; // 第三段内容
	private String paragraph4; // 第四段内容
	private String paragraph5; // 第五段内容
	private Date date; // 最后一人审批时间
	private Long caseNum; // 文件号
	
	private String yearNum; // 罚告字
	private String dateStr; // 最后一个人审批时间（格式化后的时间）
	
	private File pdfDocument;		//对应的文书
	private String pdfDocumentFileName;
	private String pdfDocumentContentType;
	private String pdfDocumentName;
	
	private String reallyName;		//上传文件的文件名
	
	
	public String getReallyName() {
		return reallyName;
	}

	public void setReallyName(String reallyName) {
		this.reallyName = reallyName;
	}

	public String getPdfDocumentName() {
		return pdfDocumentName;
	}

	public void setPdfDocumentName(String pdfDocumentName) {
		this.pdfDocumentName = pdfDocumentName;
	}

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

	public Long getPNoticeId() {
		return pNoticeId;
	}

	public void setPNoticeId(Long pNoticeId) {
		this.pNoticeId = pNoticeId;
	}

	public String getBusLicense() {
		return busLicense;
	}

	public String getIllegalContent() {
		return illegalContent;
	}

	public String getFines() {
		return fines;
	}

	public void setFines(String fines) {
		this.fines = fines;
	}

	public int getSumitDate() {
		return sumitDate;
	}

	public void setSumitDate(int sumitDate) {
		this.sumitDate = sumitDate;
	}

	public IllegalStyle getIllegalStyles() {
		return illegalStyles;
	}

	public void setIllegalStyles(IllegalStyle illegalStyles) {
		this.illegalStyles = illegalStyles;
	}

	public String getParagraph5() {
		return paragraph5;
	}

	public void setParagraph5(String paragraph5) {
		this.paragraph5 = paragraph5;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getCaseNum() {
		return caseNum;
	}

	public void setCaseNum(Long caseNum) {
		this.caseNum = caseNum;
	}

	public String getOtherpenaltyInf() {
		return otherpenaltyInf;
	}

	public void setOtherpenaltyInf(String otherpenaltyInf) {
		this.otherpenaltyInf = otherpenaltyInf;
	}

	
	public String getLegalSuggest() {
		return legalSuggest;
	}

	public void setLegalSuggest(String legalSuggest) {
		this.legalSuggest = legalSuggest;
	}

	public void setBusLicense(String busLicense) {
		this.busLicense = busLicense;
	}

	public void setIllegalContent(String illegalContent) {
		this.illegalContent = illegalContent;
	}

	public long getIllegalstyleId() {
		return illegalstyleId;
	}

	public void setIllegalstyleId(long illegalstyleId) {
		this.illegalstyleId = illegalstyleId;
	}

	public void setIllegalstyleId(Long illegalstyleId) {
		this.illegalstyleId = illegalstyleId;
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
	
}
