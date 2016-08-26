package cn.future.ssh.domain;

/*
 * 行政处罚裁量标准表
 */
public class Summary {
	private Long id;
	private String name;      //案由的名称
	private String penaltyType;      //处罚种类
	private String vioRegulations;     //违反条例
	private String legalBasis;     //法律依据
	private String cutOrder;       //裁量阶次
	private int timeLimit;       //办理时限（默认为30日）
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPenaltyType() {
		return penaltyType;
	}
	public void setPenaltyType(String penaltyType) {
		this.penaltyType = penaltyType;
	}
	public String getVioRegulations() {
		return vioRegulations;
	}
	public void setVioRegulations(String vioRegulations) {
		this.vioRegulations = vioRegulations;
	}
	public String getLegalBasis() {
		return legalBasis;
	}
	public void setLegalBasis(String legalBasis) {
		this.legalBasis = legalBasis;
	}
	public String getCutOrder() {
		return cutOrder;
	}
	public void setCutOrder(String cutOrder) {
		this.cutOrder = cutOrder;
	}
	public int getTimeLimit() {
		return timeLimit;
	}
	public void setTimeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
	}
	
}
