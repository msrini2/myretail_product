package com.myretail.product.dao;

import java.util.Collection;

public interface Dao {

	// by default methods may use this number of retry count.
	// Each method may have overloaded version which takes specific retry count.
	// Default value should be kept low: 2 or 3 max to avoid potential error to time out to client
	public static final int DEFAULT_RETRY_COUNT_ON_TIMEOUT = 3;

	/**
	 * Persists given transient object to DB. This object should not have been persisted to DB previously.<br>
	 * 
	 * @param <T>
	 * @param entity
	 * @return
	 */
	public <T> T persistTransient(T entity);

	/**
	 * Persists given list of transient objects. These objects should not have been persisted to DB previously.<br>
	 * 
	 * @param entities
	 */
	public <T> void persistTransientAll(Collection<T> entities);

	/**
	 * Updates the object in DB. This object must have been persisted to DB previously.
	 * 
	 * @param <T>
	 * @param entity
	 */
	public <T> void update(T entity);

	/**
	 * Updates objects in this list. These objects must have been saved to DB previously.
	 * 
	 * @param <T>
	 * @param entities
	 */
	public <T> void updateAll(Collection<T> entities);

	/**
	 * Deletes object from DB. This object must have been persisted to DB previously.
	 * 
	 * @param <T>
	 * @param entity
	 */
	public <T> void delete(T entity);

	/**
	 * Deletes a list of objects from DB. These objects must have been previously saved to DB.
	 * 
	 * @param <T>
	 * @param entities
	 */
	public <T> void deleteAll(Collection<T> entities);

}
