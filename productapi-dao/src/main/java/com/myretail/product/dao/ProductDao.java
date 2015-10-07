package com.myretail.product.dao;

import com.myretail.product.bo.response.ProductDetailResponse;


public interface ProductDao 
{
	public ProductDetailResponse getProductDetail(String productId);
}
