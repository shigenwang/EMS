package cn.future.ssh.domain;

import java.util.List;
import java.util.Set;

/*
 * 主要用于封装分页用的各个属性，可以再jsp页面中进行取值即可
 */
public class PageBean {
   
	//指定或是页面参数
	private int currentPage;//当前页
	private int pageSize;//每页显示多少条
	
	//通过查询数据库得到
	private List recordList;//本页的数据列表
	private int recordCount;//总记录数
	
	//通过计算得到的
	private int pageCount;//总页数
	private int beginPageIndex;//开始页的索引
    private int endPageIndex;//结束页的索引
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
    
	/*public Set getRecordList() {
		return recordList;
	}
	public void setRecordList(Set recordList) {
		this.recordList = recordList;
	}*/
	
	public int getRecordCount() {
		return recordCount;
	}
	public List getRecordList() {
		return recordList;
	}
	public void setRecordList(List recordList) {
		this.recordList = recordList;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getBeginPageIndex() {
		return beginPageIndex;
	}
	public void setBeginPageIndex(int beginPageIndex) {
		this.beginPageIndex = beginPageIndex;
	}
	public int getEndPageIndex() {
		return endPageIndex;
	}
	public void setEndPageIndex(int endPageIndex) {
		this.endPageIndex = endPageIndex;
	}
    
    /*
     * 用于对PageBean的属性进行赋值
     * 
     */
	public PageBean(int currentPage,int pageSize,List recordList,int recordCount){
		//接收4个必要的值，另外三个值在其中进行计算即可(总页数，页面列表的开始索引和结束索引)
	    this.currentPage=currentPage;
	    this.pageSize=pageSize;
	    this.recordList=recordList;
	    this.recordCount=recordCount;
	    
	    //计算总页码
	    pageCount=(recordCount+pageSize-1)/pageSize;
	    																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																						
	    //计算beginPageIndex和endPageIndex
	    //1. 总页数不多余10页，则全部显示
	    if(pageCount<10){
	    	beginPageIndex=1;
	    	endPageIndex=pageCount;
	    }else{
	    //2. 总页数多余10页，则显示当前页附近的共10个页码
	    	beginPageIndex=currentPage-4;
	    	endPageIndex=currentPage-5;
	    	 // 2.1 当前页附近的共10个页码(前4个+当前页+后5个)
	        //2.1.2 当前页面不够4个，则显示前10个页码
	        if(beginPageIndex<1){
	        	beginPageIndex=1;
	        	endPageIndex=10;
	        }
	        //2.1.2 当前页后面不够5个，则显示后10个的页码
	        if(endPageIndex<pageCount){
	        	endPageIndex=pageCount;
	        	beginPageIndex=pageCount-10+1;
	        }
	    }
	}
}
