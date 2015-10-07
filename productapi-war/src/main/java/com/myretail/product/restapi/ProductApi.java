package com.myretail.product.restapi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;	
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myretail.product.bo.response.BaseResponse;
import com.myretail.product.service.ProductService;

@Service
@Path("/r/{version: v\\d+}")
public class ProductApi {


	@Autowired
	private ProductService utilityService;

	@GET
	@Path("/productdetails")
	@Produces(MediaType.APPLICATION_JSON)
	public BaseResponse productdetails(@PathParam("version") String version, @Context UriInfo uriInfo,
			@Context Request request, @Context HttpHeaders headers)
	{

		return utilityService.getProductDetails(readQueryParams(uriInfo.getQueryParameters(true)));
	}
	
	
	private Map<String, String> readQueryParams(MultivaluedMap<String, String> queryParams)
	{
		if (queryParams == null || queryParams.isEmpty()) return null;
		Map<String, String> params = new HashMap<String, String>();

		for (String key : queryParams.keySet())
		{
			List<String> values = queryParams.get(key);
			if (values != null) for (String value : values)
			{
				if (value != null)
				{
					String existingValue = params.get(key);
					if (null == existingValue)
					{
						params.put(key, value.trim());
					}
				}
			}
		}
		return params;
	}
	
	
}
