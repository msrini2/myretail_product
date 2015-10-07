package com.myretail.product.model;

// Generated Oct 5, 2015 3:34:46 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ProdDetails generated by hbm2java
 */
@Entity
@Table(name = "prod_details", catalog = "offers_service_qa")
public class ProductDetails implements java.io.Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long productId;
	private String sku;
	private String name;
	private String category;
	private Date updated;
	private String jsonContent;

	public ProductDetails()
	{
	}

	public ProductDetails(String sku, String name, String category, Date updated, String jsonContent)
	{
		this.sku = sku;
		this.name = name;
		this.category = category;
		this.updated = updated;
		this.jsonContent = jsonContent;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id", unique = true, nullable = false)
	public Long getProductId()
	{
		return this.productId;
	}

	public void setProductId(Long productId)
	{
		this.productId = productId;
	}

	@Column(name = "sku", nullable = false)
	public String getSku()
	{
		return this.sku;
	}

	public void setSku(String sku)
	{
		this.sku = sku;
	}

	@Column(name = "name", nullable = false, length = 128)
	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Column(name = "category", nullable = false, length = 128)
	public String getCategory()
	{
		return this.category;
	}

	public void setCategory(String category)
	{
		this.category = category;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated", nullable = false, length = 19)
	public Date getUpdated()
	{
		return this.updated;
	}

	public void setUpdated(Date updated)
	{
		this.updated = updated;
	}

	@Column(name = "json_content", nullable = false, length = 16777215)
	public String getJsonContent()
	{
		return this.jsonContent;
	}

	public void setJsonContent(String jsonContent)
	{
		this.jsonContent = jsonContent;
	}

}