package com.myretail.product.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myretail.product.bo.response.ErrorCode;
import com.myretail.product.bo.response.ProductDetailResponse;
import com.myretail.product.dao.ProductDao;
import com.myretail.product.exception.ProductException;
import com.myretail.product.util.ProductConstants;


@Service
public class ProductService {
	
	
	@Autowired
	private ProductDao productDao;

	public ProductDetailResponse getProductDetails(Map<String, String> params)
	{
		
		if(params == null || params.isEmpty())
		{
			throw new ProductException(ErrorCode.TOOFEWPARAMS, ProductConstants.MSG_MISSING_REQUIRED_PARAMS);
		}
			
		if(!params.containsKey(ProductConstants.PARAM_PRODUCT_ID))
		{
			throw new ProductException(ErrorCode.INVALID_PARAM, ProductConstants.MSG_MISSING_REQUIRED_PARAMS);
		}
		
		ProductDetailResponse productDetail = null;
		try
		{
			productDetail = productDao.getProductDetail(params.get(ProductConstants.PARAM_PRODUCT_ID));
			
		}
		catch (Exception e)
		{
			productDetail.setSuccess(false);
			productDetail.setMessage(ProductConstants.GENERIC_ERROR_MSG);
		}
		
		return productDetail;
	}

}
