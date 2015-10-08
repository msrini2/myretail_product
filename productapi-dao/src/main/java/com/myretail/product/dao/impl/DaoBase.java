package com.myretail.product.dao.impl;

import java.util.Collection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.myretail.product.bo.response.ErrorCode;
import com.myretail.product.core.util.StrUtil;
import com.myretail.product.dao.Dao;
import com.myretail.product.exception.ProductException;


public abstract class DaoBase implements Dao {
	
	//@Autowired
	//private StrUtil strUtil;


	//private static final Logger logger = LoggerFactory.getLogger(DaoBase.class);
	/**
	 * Returns session factory for current version of DAO.<br>
	 * 
	 * @return
	 */
	abstract protected SessionFactory getSessionFactory();

	protected Session getSession() {
		SessionFactory sf = getSessionFactory();
		if (sf == null) {
			//logger.error("Session Factory is null");
			throw new ProductException(ErrorCode.UNAVAILABLE, "Session Factory is null");
		}
		return sf.openSession();
	}

	protected void closeSession(Session session) {
		if (session != null && session.isOpen()) {
			try {
				session.flush();
			} catch (Exception ex) {
				//logger.error("Exception Caught in closeSession Method:::" + strUtil.formatException(ex));
			}
			session.close();
		}
	}

	@Override
	public <T> T persistTransient(T entity) {
		if (entity == null) {
			//logger.warn("Null entity passed to persist");
			return null;
		}
		Session session = null;
		Transaction txn = null;
		try {
			session = getSession();
			txn = session.beginTransaction();
			session.persist(entity);
			session.clear();
			session.flush();
			session.refresh(entity);
			txn.commit();
		} catch (Exception e) {
			if (txn != null)
				txn.rollback();
			String error = "Error inserting entity " + entity;
			//logger.error(error, e);
			throw new ProductException(ErrorCode.UNAVAILABLE, error);
		} finally {
			closeSession(session);
		}
		return entity;
	}

	@Override
	public <T> void persistTransientAll(Collection<T> entities) {
		if (entities == null || entities.size() == 0) {
			//logger.warn("No entities passed to persist");
			return;
		}
		Session session = null;
		Transaction txn = null;
		try {
			session = getSession();
			txn = session.beginTransaction();
			for (Object entity : entities) {
				session.persist(entity);
				session.flush();
				session.refresh(entity);
			}
			txn.commit();
		} catch (Exception e) {
			if (txn != null)
				txn.rollback();
			String error = "Error inserting entities " + entities;
			//logger.error(error, e);
			throw new ProductException(ErrorCode.UNAVAILABLE, error);
		} finally {
			closeSession(session);
		}
	}

	@Override
	public <T> void update(T entity) {
		Session session = null;
		Transaction txn = null;
		try {
			session = getSession();
			txn = session.beginTransaction();
			session.update(entity);
			session.flush();
			session.refresh(entity);
			txn.commit();
		} catch (Exception e) {
			if (txn != null)
				txn.rollback();
			String error = "Error updating entity " + entity;
			//logger.error(error, e);
			throw new ProductException(ErrorCode.UNAVAILABLE, error);
		} finally {
			closeSession(session);
		}
	}

	@Override
	public <T> void updateAll(Collection<T> entities) {
		if (entities == null || entities.size() == 0) {
			//logger.warn("No entities passed to update");
			return;
		}
		Session session = null;
		Transaction txn = null;
		try {
			session = getSession();
			txn = session.beginTransaction();
			for (Object entity : entities) {
				session.update(entity);
				session.flush();
				session.refresh(entity);
			}
			txn.commit();
		} catch (Exception e) {
			if (txn != null)
				txn.rollback();
			String error = "Error updating entities " + entities;
			//logger.error(error, e);
			throw new ProductException(ErrorCode.UNAVAILABLE, error);
		} finally {
			closeSession(session);
		}
	}

	@Override
	public <T> void delete(T entity) {
		Session session = null;
		Transaction txn = null;
		try {
			session = getSession();
			txn = session.beginTransaction();
			session.delete(entity);
			session.flush();
			txn.commit();
		} catch (Exception e) {
			if (txn != null)
				txn.rollback();
			String error = "Error deleting entity " + entity;
			//logger.error(error, e);
			throw new ProductException(ErrorCode.UNAVAILABLE, error);
		} finally {
			closeSession(session);
		}
	}

	@Override
	public <T> void deleteAll(Collection<T> entities) {
		if (entities == null || entities.size() == 0) {
			//logger.warn("No entities passed to delete");
			return;
		}
		Session session = null;
		Transaction txn = null;
		try {
			session = getSession();
			txn = session.beginTransaction();
			for (Object entity : entities) {
				session.delete(entity);
				session.flush();
			}
			txn.commit();
		} catch (Exception e) {
			if (txn != null)
				txn.rollback();
			String error = "Error deleting entities " + entities;
			//logger.error(error, e);
			throw new ProductException(ErrorCode.UNAVAILABLE, error);
		} finally {
			closeSession(session);
		}
	}

}
