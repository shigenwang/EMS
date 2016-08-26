package cn.future.ssh.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.future.ssh.dao.AccreditationDao;
import cn.future.ssh.domain.Accreditation;
import cn.future.ssh.domain.PageBean;
import cn.future.ssh.utils.QueryHelper;

public class AccreditationDaoImpl extends HibernateDaoSupport implements AccreditationDao{

	@Override
	public void saveAccreditation(Accreditation accreditation) {
		this.getHibernateTemplate().save(accreditation);
		
	}

	@Override
	public void updateAccreditation(Accreditation accreditation) {
		this.getHibernateTemplate().update(accreditation);
		
	}

	@Override
	public Accreditation findAccreditationByAccreditationId(Long accreditationId) {
		
		Accreditation accrediatation = this.getHibernateTemplate().get(Accreditation.class,accreditationId);
		return accrediatation;
		
	}
	/**
	 * 汇总查询，返回pageBean
	 */
	public PageBean getPageBean(int pageNum, int pageSize,
			QueryHelper queryHelper) {
		
		// 参数列表：
		List<Object> parameters = queryHelper.getParameters();
		// 查询本页的数据列表：
		
		Query listQuery = this.getSessionFactory().getCurrentSession().createQuery(queryHelper.getListQueryHql());
		if(parameters != null) {
			for(int i = 0; i < parameters.size(); i++) {
				listQuery.setParameter(i, parameters.get(i));
			}
		}
		listQuery.setFirstResult((pageNum - 1) * pageSize);
		listQuery.setMaxResults(pageSize);
		// 执行查询操作
		List<Accreditation> list = listQuery.list();
		
		// 查询总数量
		Query countQuery = this.getSessionFactory().getCurrentSession().createQuery(queryHelper.getCountQueryHql());
		if(parameters != null) {
			for(int i = 0; i < parameters.size(); i++) {
				countQuery.setParameter(i, parameters.get(i));
			}
		}
		Long count = (Long) countQuery.uniqueResult();
		return new PageBean(pageNum, pageSize, list, count.intValue());
	}
	/**
	 * 汇总查询，返回根据查询条件的所有结果，为导出excel做准备
	 */
	@Override
	public PageBean getAllResult(QueryHelper queryHelper) {
		// 参数列表：
		List<Object> parameters = queryHelper.getParameters();
		// 查询本页的数据列表：
		
		Query listQuery = this.getSessionFactory().getCurrentSession().createQuery(queryHelper.getListQueryHql());
		if(parameters != null) {
			for(int i = 0; i < parameters.size(); i++) {
				listQuery.setParameter(i, parameters.get(i));
			}
		}
		// 执行查询操作
		List<Accreditation> list = listQuery.list();
		
		// 查询总数量
		Query countQuery = this.getSessionFactory().getCurrentSession().createQuery(queryHelper.getCountQueryHql());
		if(parameters != null) {
			for(int i = 0; i < parameters.size(); i++) {
				countQuery.setParameter(i, parameters.get(i));
			}
		}
		Long count = (Long) countQuery.uniqueResult();
		return new PageBean(0, 0, list, count.intValue());
	}

	
	
	
	
}
