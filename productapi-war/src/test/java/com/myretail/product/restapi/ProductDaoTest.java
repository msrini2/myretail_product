package com.myretail.product.restapi;

import org.junit.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import com.myretail.product.bo.response.ProductDetailResponse;
import com.myretail.product.dao.ProductDao;
import com.myretail.product.model.ProductDetails;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/spring/applicationContextTest.xml"})
@Transactional
@TransactionConfiguration(defaultRollback=true)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductDaoTest
{
    @Autowired
    private ProductDao productDao;
    
    @Autowired 
    ProductApi productApi;
    

    @Test
    public void test1DbInsert()
    {
    	ProductDetails  pd = new ProductDetails();
        pd.setName("Product");
        pd.setSku("Test");
        pd.setCategory("Test");
        pd.setJsonContent("{}");
        productDao.persistTransient(pd);
        Assert.assertNotNull(pd);
        Assert.assertNotNull(pd.getProductId());
        productDao.delete(pd);
    }
    
    
    
    @Test
    public void test2DbRead()
    {
    	ProductDetails  pd = new ProductDetails();
        pd.setName("AProduct");
        pd.setSku("Test");
        pd.setCategory("Test");
        pd.setJsonContent("{}");
        productDao.persistTransient(pd);
    	ProductDetailResponse productDetails = productDao.getProductDetail(Long.toString(pd.getProductId()));
    	Assert.assertEquals(pd.getProductId(), productDetails.getProductId());
    	Assert.assertEquals(pd.getName(), productDetails.getName());
    	Assert.assertEquals(pd.getSku(), productDetails.getSku());
    	productDao.delete(pd);
    }
    

}