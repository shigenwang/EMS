package cn.future.ssh.dao.impl;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.future.ssh.dao.PersonnelDao;
import cn.future.ssh.domain.PageBean;
import cn.future.ssh.domain.Personnel;
import cn.future.ssh.domain.Squadron;
import cn.future.ssh.utils.QueryHelper;


public class PersonnelDaoImpl extends HibernateDaoSupport implements PersonnelDao {
    
	@Override
	public Personnel findPersonnelByAccount(String account) {
		String hql = "from Personnel p where p.account = ? and p.flag='T'";
		List<Personnel> personnels = this.getHibernateTemplate().find(hql,account);
		Personnel personnel = null;
		if(personnels!=null&&personnels.size()>0){
			personnel = personnels.get(0);
		}
		return personnel;
	}

	@Override
	public List<Personnel> getPersonnelBySquadron(Squadron squadron) {
		if(squadron!=null){
			String hql = "from Personnel p where p.squadron = ? and p.flag='T'";
			List<Personnel> personnels = this.getHibernateTemplate().find(hql, squadron);
			return personnels;
		}else{
			String hql="from Personnel p";
			return this.getHibernateTemplate().find(hql);
		}
	
		
	}
	/**
	 * 根据中队队员查找中队长
	 */
	@Override
	public Personnel findCaptainByMember(Personnel member) {
		String hql = "select p from Personnel p inner join p.roles as r where  r.name = ? and p.squadron = ? and p.flag='T'";
		
		List<Personnel> personnels = this.getHibernateTemplate().find(hql, "中队长",member.getSquadron());
		Personnel personnel = null;
		
		if(personnels!=null&&personnels.size()>0){
			personnel = personnels.get(0);
		}
		return personnel;
	}
	/**
	 * 查找大队长(也用于判断是否有大队长，中队长，业务领导，部门主要领导)
	 */
	@Override
	public Personnel findPersonByRole(String roleName) {
		String hql = "select p from Personnel p inner join p.roles as r where r.name = ? and p.flag='T'";
		List<Personnel> personnels = this.getHibernateTemplate().find(hql, roleName);
		Personnel personnel = null;
		
		if(personnels!=null&&personnels.size()>0){
			personnel = personnels.get(0);
		}
		return personnel;
		
	}
	/**
	 * 查找法制科所有人员
	 */
	@Override
	public List<Personnel> findLegalDepartmentPersonnels() {
		String hql = "select p from Personnel p inner join p.roles as r where r.name = ? and p.flag='T'";
		return this.getHibernateTemplate().find(hql,"法制科领导");
		
	}
	/**
	 * 查找业委会所有人员
	 */
	@Override
	public List<Personnel> findIndustryCommitteePersonnels() {
		
		String hql = "select p from Personnel p inner join p.roles as r where r.name = ? and p.flag='T'";
		return this.getHibernateTemplate().find(hql,"业委会领导");
	}
	/**
	 * 查找案审委所有人员
	 */
	@Override
	public List<Personnel> findCaseReviewComPersonnels() {
		
		String hql = "select p from Personnel p inner join p.roles as r where r.name = ? and p.flag='T'";
		return this.getHibernateTemplate().find(hql,"案审委领导");
	}

	@Override
	public void savePersonnel(Personnel personnel) {
        this.getHibernateTemplate().save(personnel);	
	}
	
	
	@Override
	public Personnel getPersonnelById(Long id) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(Personnel.class, id);
	}

	@Override
	public void updatePersonnel(Personnel personnel) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(personnel);
	}
	
	public void deletePersonnel(Personnel personnel) {
	  this.getHibernateTemplate().update(personnel);;	
	}

	
	
	/**
	 * 根据account、password查找
	 */
	public Personnel findByAccountAndPassword(String account, String password) {

		
		String md5Password=DigestUtils.md5Hex(password);


		String hql = "select p from Personnel p where p.account= ? and p.password= ? and p.flag='T'";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setString(0, account);
		query.setString(1, md5Password);
		List<Personnel> personnelList = query.list();
		if(personnelList.size() != 0) {
			return personnelList.get(0);
		} else {
			return null;
		}
	}
	
	/*
	 * 查询的方法
	 */
	@Override
	public PageBean getPageBean(int pageNum, int pageSize,
			QueryHelper queryHelper) {
		//参数列表
		List<Object>parameters=queryHelper.getParameters();
		
		//查询本页的数据列表
		Query listQuery=this.getSessionFactory().getCurrentSession().createQuery(queryHelper.getListQueryHql());
		if(parameters!=null){
			 for(int i=0;i<parameters.size();i++){
				 listQuery.setParameter(i, parameters.get(i));
			 }
		}
		listQuery.setFirstResult((pageNum-1)*pageSize);
		listQuery.setMaxResults(pageSize);
		
		List<Personnel> list=listQuery.list();//执行查询
		
		
		//查询总数量
		Query countQuery=this.getSessionFactory().getCurrentSession().createQuery(queryHelper.getCountQueryHql());
		if(parameters!=null){
			 for(int i=0;i<parameters.size();i++){
				 countQuery.setParameter(i, parameters.get(i));
			 }
		}
		Long count=(Long) countQuery.uniqueResult();
		return new PageBean(pageNum,pageSize,list,count.intValue());
	}

	@Override
	public List<Personnel> findPersonBySquName(String squName) {
		String hql = "select p from Personnel p where p.squadron.name=? and p.flag='T'";
		return this.getHibernateTemplate().find(hql, squName);
	}
	
	@Override
	public List<Personnel> getAllPersonnel() {
		String hql="from Personnel p where p.flag='T'";
		return this.getHibernateTemplate().find(hql);
	}
	
	@Override
	public Personnel findPersonByRoleAndSquadron(String name, String squadronName) {
		String hql="select distinct(p) from Personnel p left outer join fetch p.roles as r where r.name=? and p.squadron.name=? and p.flag='T'";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter(0, name);
		query.setParameter(1, squadronName);
		List<Personnel> personnelList = query.list();
		if(personnelList.size() != 0) {
			return new Personnel();
		} else {
			return null;
		}
	}
}
