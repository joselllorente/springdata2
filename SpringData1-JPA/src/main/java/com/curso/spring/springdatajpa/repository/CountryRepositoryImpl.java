package com.curso.spring.springdatajpa.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.curso.spring.springdatajpa.model.Country;


public class CountryRepositoryImpl implements CountryRepositoryCustom
{
	@PersistenceContext
	private EntityManager entityManager;	
	
	@Override
	@Transactional
	public void clearEntityCache() 
	{
		SessionFactory sessionFactory = entityManager.unwrap(Session.class).getSessionFactory();
		sessionFactory.getCache().evictEntityRegion(Country.class);
	}	

}