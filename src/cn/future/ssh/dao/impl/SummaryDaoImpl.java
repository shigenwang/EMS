package cn.future.ssh.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.future.ssh.dao.SummaryDao;
import cn.future.ssh.domain.Summary;

public class SummaryDaoImpl extends HibernateDaoSupport implements SummaryDao {
    
	@Override
	public void saveSummary(Summary summary) {
		this.getHibernateTemplate().save(summary);
	}

	@Override
	public List<Summary> findAllSummary() {
		String hql = "from Summary s";
		List<Summary> summarys = this.getHibernateTemplate().find(hql);
		return summarys;
		
	}

	@Override
	public void updateSummary(Summary summary) {
		this.getHibernateTemplate().update(summary);
		
	}

	@Override
	public Summary findSummaryById(Long id) {
	   if(id==null){
		   return null;
	   }else{
		 Summary summary= (Summary) this.getHibernateTemplate().get(Summary.class, id);
		 return summary;
	   }
      
	}
	@Override
	public void deleteSummary(Summary summary) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(summary);
	}
}
