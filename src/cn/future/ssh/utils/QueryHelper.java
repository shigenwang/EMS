package cn.future.ssh.utils;

import java.util.ArrayList;
import java.util.List;

/*
 *查询辅助类，里面都是定义一些方法
 *包括：from子句，where子句，orderby子句的拼接方法
 *可以再相应的Action中进行调用该方法，进行hql的拼接
 */
public class QueryHelper {
    /*
     * 以下三个字符串用于辅助拼接hql语句
     */
	private String fromClause;//from子句
	private String whereClause="";//where子句
	private String orderByClause="";// order by 子句
	private List<Object>parameters=new ArrayList<Object>();
	
	/*
	 * 因为from是必须的，所以我们在构造函数中赋值
	 * alias:别名
	 */
	public QueryHelper(Class clazz,String alias){
		if(clazz.getSimpleName() .contains("Personnel") ) { // 如果要查询的类为 personnel时，执行的查询语句。
			fromClause=" from "+clazz.getSimpleName()+" "+alias+" left outer join  p.roles as r";
		}
		if(clazz.getSimpleName().contains("Accreditation")) { // 如果要查询的类为 Accreditation时， 执行的查询语句。
			fromClause = " from " + clazz.getSimpleName() + " " + alias;
		}
	}
	
	/*
	 * 拼接where子句
	 */
	public QueryHelper addCondition(String condition,Object...params){
		    //进行拼接
		    
			if((whereClause.length()==0)){
				whereClause=" where "+condition;
			} 
			else{
				whereClause+=" and "+condition;
			}
			//参数
			if(params!=null||params.length!=0){
				for(Object p:params){
					parameters.add(p);
				}
			}
		 return this;
	}
	/**
	 * 拼接 order by子句
	 * @return
	 */
	public QueryHelper addOrderBy(String param, String sortParam) {
		// 开始拼接
		orderByClause = orderByClause + " order by " + param + sortParam;
		return this;
	}

	
	/*
	 * 获取生成的用于查询数据列表的Hql语句(第一种)
	 */
	public String getListQueryHql(){
		return "select distinct(p) "+fromClause+whereClause + orderByClause;
	}
	
	
	/*
	 * 获取生成查询总记录的hql语句(第一种)
	 */
	public String getCountQueryHql(){
		return "select count(distinct p.id)"+fromClause+whereClause + orderByClause;
	}

	/*
	 * 
	 */
	public List<Object> getParameters(){
		return parameters;
	}
}
