package com.kd.example.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDao {

	@Autowired
	protected SessionFactory sessionFactory;

	public void save(Object entity) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			session.save(entity);
		} catch (Exception e) {
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		session.close();
	}

	public void delete(Object entity) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			session.delete(entity);
		} catch (Exception e) {
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		session.close();
	}
}
