package cn.future.ssh.domain;

import java.util.Date;
import java.util.Set;

/**处罚事先告知书*/
public class PNotice {
	/**id*/
	private Long id;
	private String illegalContent;   //违法行为内容
	private String fines;     //罚款金额
	private int sumitDate;   //时限(默认3日)
	private Date date;       // 告知书的生成时间
	private String busLicense;//营业执照或法人登记账号
	private Boolean legalFlag;      //法制科标记
	private Boolean printFlag;      //打印标记
    private String legalSuggest;	//法制部门意见
	private Accreditation accreditation;    //立案审批表（一对一的对关系）+++++++++++++++++++
	private IllegalStyle illegalStyles;     //违法行为类型表  多对一的关系++++++++++++++++
	private Summary summary;//行政处罚裁量标准表(多对一的关系)++++++++++++++++++++++++++++
	private String otherpenaltyInf;			//其他处罚信息
	//private String legalName;	//法制科审核人员姓名
	private String paragraph1; // 第一段内容
	private String paragraph2; // 第二段内容
	private String paragraph3; // 第三段内容
	private String paragraph4; // 第四段内容
	private String paragraph5; // 第五段内容
	
	private PDFDocument pdfDocument;//对应的文书
	
	
	
	public PDFDocument getPdfDocument() {
		return pdfDocument;
	}

	public void setPdfDocument(PDFDocument pdfDocument) {
		this.pdfDocument = pdfDocument;
	}

	/*	public String getLegalName() {
		return legalName;
	}

	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}
*/
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

	public String getLegalSuggest() {
		return legalSuggest;
	}

	public void setLegalSuggest(String legalSuggest) {
		this.legalSuggest = legalSuggest;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
    
	
	public String getOtherpenaltyInf() {
		return otherpenaltyInf;
	}

	public void setOtherpenaltyInf(String otherpenaltyInf) {
		this.otherpenaltyInf = otherpenaltyInf;
	}

	public String getBusLicense() {
		return busLicense;
	}

	public void setBusLicense(String busLicense) {
		this.busLicense = busLicense;
	}

	public String getIllegalContent() {
		return illegalContent;
	}

	public void setIllegalContent(String illegalContent) {
		this.illegalContent = illegalContent;
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

    
	public IllegalStyle getIllegalStyles() {
		return illegalStyles;
	}

	public void setIllegalStyles(IllegalStyle illegalStyles) {
		this.illegalStyles = illegalStyles;
	}

	public Summary getSummary() {
		return summary;
	}

	public void setSummary(Summary summary) {
		this.summary = summary;
	}
	
}
