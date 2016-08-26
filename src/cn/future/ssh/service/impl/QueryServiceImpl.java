package cn.future.ssh.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import cn.future.ssh.domain.Personnel;
import cn.future.ssh.domain.Role;
import cn.future.ssh.service.QueryService;

public class QueryServiceImpl implements QueryService {

	/**
	 * squadronNameFlag; //中队的名称标
		squaPersonFlag; //承办人姓名标记 
		caseSourceIdFlag; //案情来源标记
		legNameFlag; // 法制科领导姓名标记 
	 */
	public List<String> getConditionsSet(Personnel personnel, String loaderSign) {
		List<String> conditionsList = new ArrayList<String>();
		if(loaderSign!=null&&"中队".equals(loaderSign)){
			conditionsList.add("squaPersonFlag");
			conditionsList.add("caseSourceIdFlag");
			conditionsList.add("legNameFlag");
		}else{
			for(Role role:personnel.getRoles()){
				String roleName = role.getName();
				if(roleName!=null){
					if(roleName.equals("中队队员")) {
						conditionsList.add("caseSourceIdFlag");
						conditionsList.add("legNameFlag");
					} else if(roleName.equals("中队长")){
						conditionsList.add("squaPersonFlag");
						conditionsList.add("caseSourceIdFlag");
						conditionsList.add("legNameFlag");
					} else if(roleName.equals("法制科领导")){
						conditionsList.add("squaPersonFlag");
						conditionsList.add("caseSourceIdFlag");
						conditionsList.add("squadronNameFlag");
					} else if(roleName.equals("业委会领导") || roleName.equals("大队长") || roleName.equals("案审委领导")){ // 其他领导查询条件不限制
						conditionsList.add("squaPersonFlag");
						conditionsList.add("caseSourceIdFlag");
						conditionsList.add("squadronNameFlag");
						conditionsList.add("legNameFlag");
					}
				}
			}
		}
	return conditionsList;
	}

}
