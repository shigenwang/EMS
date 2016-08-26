package cn.future.ssh.dao.impl;

import java.util.List;



import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.future.ssh.dao.SquadronDao;
import cn.future.ssh.domain.Squadron;

public class SquadronDaoImpl extends HibernateDaoSupport implements SquadronDao {

	@Override
	public void saveSquadron(Squadron squadron) {
		this.getSessionFactory().getCurrentSession().save(squadron);
		
	}


	@Override
	public Squadron getSquadronById(Long id) {
		String hql="from Squadron s where id="+id;
		
	    //Squadron squadron=(Squadron) this.getHibernateTemplate().find(hql);//这个会报错，因为find返回的是list
		//Squadron squadron= (Squadron) (this.getHibernateTemplate().find(hql).get(0));//这个就可以
		Squadron squadron=(Squadron) this.getHibernateTemplate().get(Squadron.class, id);//也可以
		
		return squadron;
	}


	@Override
	public List<Squadron> findAllSquadron() {
        String hql="from Squadron s ";
		return this.getHibernateTemplate().find(hql);
	}



	/*@Override
	public void deleteSquadronById(Squadron squadron) {
		
		this.getHibernateTemplate().delete(squadron);
		
	}*/


	@Override
	public Squadron getById(Long id) {
		if(id==null){
			return null;
		}else
			return this.getHibernateTemplate().get(Squadron.class, id);

	}

	@Override
	public Squadron findByAccountAndPassword(String account, String password) {

		String md5Password=DigestUtils.md5Hex(password);

		//String mdsPassword = password;
		Session session = this.getSessionFactory().getCurrentSession();
		String hql = "select s from Squadron s where s.account = ? and s.password = ?";
		Query query = session.createQuery(hql);
		query.setString(0, account);
		query.setString(1, md5Password);
		List<Squadron> squadronList = query.list();
		if(squadronList.size() != 0) {
			return squadronList.get(0);
		} else {
			return null;
		}
	}


	@Override
	public void updateSquadron(Squadron squadron) {
		Session session = this.getSessionFactory().getCurrentSession();
		session.merge(squadron);
		//this.getHibernateTemplate().update(squadron);
		
	}
}
