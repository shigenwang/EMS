package cn.future.ssh.utils;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

public class Install {
    //获取session
	@Resource
	private SessionFactory sessionFactory;
	@Transactional
	public void install(){
		Session session=sessionFactory.getCurrentSession();
		
	}
	
}
