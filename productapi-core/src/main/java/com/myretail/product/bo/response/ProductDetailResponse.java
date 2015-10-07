package com.myretail.product.bo.response;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonInclude(Include.NON_NULL)
public class ProductDetailResponse extends BaseResponse
{
   /**
	 * 
	 */
	private static final long serialVersionUID = -8977373558323674756L;
private Long productId;
   private String sku;
   private String name;
   private String category;
   private Double price;
public Long getProductId()
{
	return productId;
}
public void setProductId(Long productId)
{
	this.productId = productId;
}
public String getSku()
{
	return sku;
}
public void setSku(String sku)
{
	this.sku = sku;
}
public String getName()
{
	return name;
}
public void setName(String name)
{
	this.name = name;
}
public String getCategory()
{
	return category;
}
public void setCategory(String category)
{
	this.category = category;
}
public Double getPrice()
{
	return price;
}
public void setPrice(Double price)
{
	this.price = price;
}
   
   
}
