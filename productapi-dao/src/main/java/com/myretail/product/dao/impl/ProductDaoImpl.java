package com.myretail.product.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myretail.product.bo.response.ErrorCode;
import com.myretail.product.core.util.StrUtil;
import com.myretail.product.bo.response.ProductDetailResponse;
import com.myretail.product.dao.ProductDao;
import com.myretail.product.dao.util.DBConstants;
import com.myretail.product.exception.ProductException;
import com.myretail.product.model.ProductDetails;
import com.myretail.product.model.ProductPrice;

@Component("productDao")
public class ProductDaoImpl extends DaoImpl implements ProductDao
{

	//@Autowired
	//private StrUtil strUtil;
	
	//private static final Logger logger = LoggerFactory.getLogger(ProductDaoImpl.class);
	
	public ProductDetailResponse getProductDetail(String productId)
	{
		Session session = getSession();
		ProductDetailResponse response = new ProductDetailResponse();
		try
		{
			Criteria criteria = session.createCriteria(ProductDetails.class);
			criteria.add(Restrictions.eq(DBConstants.FIELD_PRODUCT_ID, Long.parseLong(productId)));
			@SuppressWarnings("unchecked")
			List<ProductDetails> productDetails = criteria.list();
			if(productDetails.isEmpty())
			{
				throw new ProductException(ErrorCode.NOPRODUCT, DBConstants.PRODUCT_NOT_FOUND+productId);
			}

				ProductDetails pd = productDetails.get(0);
				response.setCategory(pd.getCategory());
				response.setName(pd.getName());
				response.setPrice(getPrice(productId));
				response.setSku(pd.getSku());
				response.setProductId(pd.getProductId());
		}
		catch (Exception e)
		{
			//logger.error(DBConstants.DB_EXCEPTION_PREFIX+strUtil.formatException(e));
		}
		
		finally {
			closeSession(session);
		}
		return response;
	}
	
	
	private Double getPrice(String productId)
	{

		Session session = getSession();
		Double price = null;
		try
		{
			Criteria criteria = session.createCriteria(ProductPrice.class);
			criteria.add(Restrictions.eq("productId", Long.parseLong(productId)));
			@SuppressWarnings("unchecked")
			List<ProductPrice> productPrices = criteria.list();
			if(!productPrices.isEmpty())
			{ 
				ProductPrice pp = productPrices.get(0);
				price = pp.getPrice();
			}
		}
		catch (Exception e)
		{
			//logger.error("Exception while retrieving product details"+strUtil.formatException(e));
		}
		
		finally {
			closeSession(session);
		}
		return price;
	
	}
	
}
