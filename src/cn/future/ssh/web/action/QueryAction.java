package cn.future.ssh.web.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.future.ssh.domain.Accreditation;
import cn.future.ssh.domain.CaseSource;
import cn.future.ssh.domain.PageBean;
import cn.future.ssh.domain.Personnel;
import cn.future.ssh.domain.Squadron;
import cn.future.ssh.service.AccreditationService;
import cn.future.ssh.service.CaseSourceService;
import cn.future.ssh.service.PersonnelService;
import cn.future.ssh.service.QueryService;
import cn.future.ssh.service.SquadronService;
import cn.future.ssh.utils.JasperHelper;
import cn.future.ssh.utils.QueryHelper;
import cn.future.ssh.utils.SessionContext;
import cn.future.ssh.utils.ValueContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class QueryAction extends ActionSupport implements ModelDriven<Accreditation>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 查询条件：
	private Date fromDate; // 结案时间段：开始
	private Date toDate; // 结案时间段：结束
	private String squadronName; //中队的名称
	private String squaPerson; //承办人姓名
	private Long caseSourceId; //案情来源id
	private String legName; // 法制科领导姓名
	
	// 各种service
	private PersonnelService personnelService; // 人
	private SquadronService squadronService; // 中队
	private CaseSourceService caseSourceService; // 案情来源
	private AccreditationService accreditationService; // 立案审批表
	private QueryService queryService; 
	// 分页相关
	private int pageNum=1;  // 当前页
    private int pageSize=10;// 每页多少条记录
	
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public QueryService getQueryService() {
		return queryService;
	}
	public void setQueryService(QueryService queryService) {
		this.queryService = queryService;
	}
	public AccreditationService getAccreditationService() {
		return accreditationService;
	}
	public void setAccreditationService(AccreditationService accreditationService) {
		this.accreditationService = accreditationService;
	}
	public PersonnelService getPersonnelService() {
		return personnelService;
	}
	public void setPersonnelService(PersonnelService personnelService) {
		this.personnelService = personnelService;
	}
	public SquadronService getSquadronService() {
		return squadronService;
	}
	public void setSquadronService(SquadronService squadronService) {
		this.squadronService = squadronService;
	}
	public CaseSourceService getCaseSourceService() {
		return caseSourceService;
	}
	public void setCaseSourceService(CaseSourceService caseSourceService) {
		this.caseSourceService = caseSourceService;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		if(toDate != null && fromDate != null) {
			// 如果开始时间不在结束时间之前，将两者进行交换
			if(!fromDate.before(toDate)){
				Date tempDate = fromDate;
				fromDate = toDate;
				toDate = tempDate;
			}
			// 然后，将结束时间进行加一
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(toDate); 
			calendar.add(calendar.DATE, 1);
			//把日期往后增加一天.整数往后推,负数往前移动 
			toDate = calendar.getTime(); // 这个时间就是日期往后推一天的结果
		}
		this.toDate = toDate;
	}
	public String getSquadronName() {
		return squadronName;
	}
	public void setSquadronName(String squadronName) {
		this.squadronName = squadronName;
	}
	public String getSquaPerson() {
		return squaPerson;
	}
	public void setSquaPerson(String squaPerson) {
		this.squaPerson = squaPerson;
	}
	
	public Long getCaseSourceId() {
		return caseSourceId;
	}
	public void setCaseSourceId(Long caseSourceId) {
		this.caseSourceId = caseSourceId;
	}
	public String getLegName() {
		return legName;
	}
	public void setLegName(String legName) {
		this.legName = legName;
	}
	
	private Accreditation model = new Accreditation();
	@Override
	public Accreditation getModel() {
		return model;
	}
	public void setModel(Accreditation model) {
		this.model = model;
	}
	/**
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		// 准备数据 Squadron（中队）
    	List<Squadron> squadronList = squadronService.getAllSquadron();
    	ValueContext.putValueContext("squadronList", squadronList);
    	// 准备时间
    	if(fromDate != null && toDate != null) {
    		ValueContext.putValueContext("fromDate", fromDate);
    		ValueContext.putValueContext("toDate", toDate);
    	}
    	// 准备数据 案情来源
    	List<CaseSource> caseSourceList = caseSourceService.getAllCaseSource();
    	ValueContext.putValueContext("caseSourceList", caseSourceList);
    	// 准备数据 法制科人员姓名
    	List<Personnel> legalList = personnelService.findLegalDepartmentPersonnels();
    	ValueContext.putValueContext("legalList", legalList);
    	
    	// 根据登录角色不同，查询之前，限制的条件也不一样
//    	String squadronName = ""; //中队名字
//    	String squaPerson = "";  //承办人名字
//    	String legName = "";     //法制科领导名字
    	//获得登录人
    	String identity = (String) ActionContext.getContext().getSession().get("loaderSign");
    	//登录者是个人
    	if("个人".equals(identity)) {
    		Personnel personnel = SessionContext.get();
    		// 是中队成员
    		if(personnelService.isContainsRoles("中队队员", personnel)) {
    			squadronName = personnel.getSquadron().getName(); //所属中队
    			squaPerson = personnel.getName(); //自己名字
    		}
    		// 是中队长
    		if(personnelService.isContainsRoles("中队长", personnel)) {
    			squadronName = personnel.getSquadron().getName(); //所属中队
    		}
    		// 是法制科领导
    		if(personnelService.isContainsRoles("法制科领导", personnel)) {
    			legName = personnel.getName();
    		}
    		// 是大队长
    		if(personnelService.isContainsRoles("大队长", personnel)) {
    		}
    		// 包括业委会领导、案审委领导等都没有什么限制
    		
    	}
    	if("中队".equals(identity)) {
    		Personnel personnel = SessionContext.get();
    		squadronName = personnel.getSquadron().getName();
    	}
    	
    	// 准备数据 某中队里的成员
    	List<Personnel> squPerList = new ArrayList<Personnel>();
    	if(squadronName != null) {
    		squPerList = personnelService.findPersonBySquName(squadronName);
    	}
    	ValueContext.putValueContext("squPerList", squPerList);
    	
    	//准备用户分页的信息
    	QueryHelper queryHelper=new QueryHelper(Accreditation.class, "p");
    	// 按照编号排序
    	queryHelper.addOrderBy(" id ", " DESC ");
    	// 时间
    	if(fromDate != null && toDate != null) {
    		queryHelper.addCondition("(p.pClosingReport.indCommDate between ? and ?)", fromDate, toDate); 
    		// 查询条件使用之后，将回显时间正常
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(toDate); 
			calendar.add(calendar.DATE, -1);
			//把日期往后增加一天.整数往后推,负数往前移动 
			toDate = calendar.getTime(); // 这个时间就是日期往前推一天的结果
    	}
    	// 中队
    	if(squadronName != null && !"".equals(squadronName.trim())) {
    		queryHelper.addCondition("p.squadron.name=?", squadronName);
    	}
    	// 承办人姓名
    	if(squaPerson != null && !"".equals(squaPerson.trim())) {
    		queryHelper.addCondition("(p.sponsor.name=? or p.assistantHandle.name=?)", squaPerson, squaPerson);
    	}
    	// 案件来源
    	if(caseSourceId != null && 0 != caseSourceId) {
    		queryHelper.addCondition("p.caseSource.id=?", caseSourceId);
    	}
    	// 法制科领导姓名
    	if(legName != null && !"".equals(legName.trim())) {
    		queryHelper.addCondition("p.legalName=?", legName);
    	}
    	
    	// 得到查询条件标记的集合，用户每个人到查询界面显示的查询条件
    	List<String> conditionsList = queryService.getConditionsSet(SessionContext.get(), identity);
    	Set<String> conditionsSet = new HashSet<String>(conditionsList); // 转换成set集合，去重复
    	ValueContext.putValueContext("conditionsSet", conditionsSet);
    	// 得到pageBean 对象，并将此放置值栈中：
    	PageBean pageBean = accreditationService.getPageBean(pageNum, pageSize, queryHelper);
    	ValueContext.putValueStack(pageBean);
		return "list";
	}
	/**
	 * 将汇总查询的数据导出excel
	 */
	public void exportpdf() {
		//准备用户分页的信息
    	QueryHelper queryHelper=new QueryHelper(Accreditation.class, "p");
    	// 按照编号排序
    	queryHelper.addOrderBy(" id ", " DESC ");
    	// 时间
    	if(fromDate != null && toDate != null) {
			queryHelper.addCondition("(p.pClosingReport.indCommDate between ? and ?)", fromDate, toDate); 
    	}
    	// 中队
    	if(squadronName != null && !"".equals(squadronName.trim())) {
    		queryHelper.addCondition("p.squadron.name=?", squadronName);
    	}
    	// 承办人姓名
    	if(squaPerson != null && !"".equals(squaPerson.trim())) {
    		queryHelper.addCondition("(p.sponsor.name=? or p.assistantHandle.name=?)", squaPerson, squaPerson);
    	}
    	// 案件来源
    	if(caseSourceId != null && 0 != caseSourceId) {
    		queryHelper.addCondition("p.caseSource.id=?", caseSourceId);
    	}
    	// 法制科领导姓名
    	if(legName != null && !"".equals(legName.trim())) {
    		queryHelper.addCondition("p.legalName=?", legName);
    	}
    	// 得到pageBean
    	PageBean pageBean = accreditationService.getAllResult(queryHelper);
    	// 获取里边的 Accreditation 集合
    	List<Accreditation> accreditationList =  pageBean.getRecordList();
    	
    	// 进行excel下载
    	JasperHelper.exportmain("excel", ".jasper", accreditationList, "huizongchaxun.xls");
	}
	public String queryDetail() throws Exception {
		Accreditation accreditation = accreditationService.findAccreditationByAccreditationId(model.getId());
		ValueContext.putValueContext("accreditation", accreditation);
		return "detail";
	}

}
