package com.myretail.product.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("dao")
public class DaoImpl extends DaoBase
{

	@Autowired
	@Qualifier("productApiSessionFactory")
	private SessionFactory sessionFactory;

	@Override
	protected SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}

}
