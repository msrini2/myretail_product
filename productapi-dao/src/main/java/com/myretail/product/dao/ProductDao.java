package com.myretail.product.dao;

import java.util.Collection;

import com.myretail.product.bo.response.ProductDetailResponse;


public interface ProductDao 
{
	/**
	 * @param productId
	 * @return
	 */
	public ProductDetailResponse getProductDetail(String productId);
	
	/**
	 * @param entity
	 * @return
	 */
	public <T> T persistTransient(T entity);
	
	/**
	 * @param entities
	 */
	public <T> void persistTransientAll(Collection<T> entities);
	
	/**
	 * @param entity
	 */
	public <T> void update(T entity);
	
	
	/**
	 * @param entities
	 */
	public <T> void updateAll(Collection<T> entities);
	
	/**
	 * @param entity
	 */
	public <T> void delete(T entity);
	
	/**
	 * @param entities
	 */
	public <T> void deleteAll(Collection<T> entities);
}
