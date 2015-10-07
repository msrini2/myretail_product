package com.myretail.product.web.exception;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;
import javax.ws.rs.core.Response.StatusType;

import com.myretail.product.bo.response.BaseResponse;
import com.myretail.product.bo.response.ErrorCode;

public class ProductWebException extends WebApplicationException
{

	public ProductWebException(ErrorCode errorCode)
	{
		this(getResponseStatus(errorCode), errorCode);
	}

	/**
	 * This is preferred constructor as it accepts our own error code and message for convenience.<br>
	 * 
	 * @param errorCode
	 * @param message
	 */
	public ProductWebException(ErrorCode errorCode, String message)
	{
		this(getResponseStatus(errorCode), errorCode, message);
	}

	public ProductWebException(StatusType status, ErrorCode errorCode)
	{
		this(status, errorCode, null);
	}

	public ProductWebException(StatusType status, ErrorCode errorCode, String message)
	{
		super(Response.status(status).entity(new BaseResponse(errorCode, message)).type("application/json").build());
	}

	private static StatusType getResponseStatus(ErrorCode errorCode)
	{
		StatusType status = errorCodeStatusMap.get(errorCode);
		if (status == null)
		{
/*			getLogger()
					.error("ErrorCode is not handled by service :  " + errorCode
							+ ". Will return 500 (INTERNAL_SERVER_ERROR)");
			status = Response.Status.INTERNAL_SERVER_ERROR;*/
		}
		return status;
	}

	private static class CustomStatusType implements StatusType
	{

		private int statusCode;
		private Family statusFamily;
		private String reasonPhrase;

		public CustomStatusType(int statusCode, Family statusFamily, String reasonPhrase)
		{
			this.statusCode = statusCode;
			this.statusFamily = statusFamily;
			this.reasonPhrase = reasonPhrase;
		}

		@Override
		public int getStatusCode()
		{
			return statusCode;
		}

		@Override
		public Family getFamily()
		{
			return statusFamily;
		}

		@Override
		public String getReasonPhrase()
		{
			return reasonPhrase;
		}

	}

	private static final CustomStatusType STATUS_TYPE_TIMEOUT = new CustomStatusType(504, Family.SERVER_ERROR,
			"Timeout");

	private static final Map<ErrorCode, StatusType> errorCodeStatusMap = new HashMap<ErrorCode, StatusType>();
	static
	{
		// All known ErrorCode values must be mapped to known status,
		// however we are not mapping the ErrorCode.UNDEFINED in order to see the error in the log
		errorCodeStatusMap.put(ErrorCode.UNAVAILABLE, Response.Status.SERVICE_UNAVAILABLE);
		errorCodeStatusMap.put(ErrorCode.TOOFEWPARAMS, Response.Status.BAD_REQUEST);
		errorCodeStatusMap.put(ErrorCode.SECURITY, Response.Status.FORBIDDEN);
		errorCodeStatusMap.put(ErrorCode.NOTFOUND, Response.Status.BAD_REQUEST);
		errorCodeStatusMap.put(ErrorCode.UNDEFINED, Response.Status.OK);
		errorCodeStatusMap.put(ErrorCode.INVALID_PARAM, Response.Status.OK);
		errorCodeStatusMap.put(ErrorCode.SAVE_RESTRICTED, Response.Status.OK);
		errorCodeStatusMap.put(ErrorCode.TIMEOUT, STATUS_TYPE_TIMEOUT); // new status.
	}


	private static final long serialVersionUID = -6362602403494691796L;

}
