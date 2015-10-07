package com.myretail.product.bo.response;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonInclude(Include.NON_NULL)
public class BaseResponse implements Serializable
{

	private boolean success;
	private ErrorCode errorCode;
	private String message;

	public BaseResponse()
	{
		setSuccess(true);
	}

	public BaseResponse(boolean success)
	{
		this.success = success;
	}

	public BaseResponse(ErrorCode errorCode)
	{
		this.success = false;
		this.errorCode = errorCode;
	}

	public BaseResponse(ErrorCode errorCode, String message)
	{
		this.success = false;
		this.errorCode = errorCode;
		this.message = message;
	}

	public BaseResponse(String message)
	{
		this.success = true;
		this.message = message;
	}

	public void setSuccess(boolean success)
	{
		this.success = success;
	}

	public boolean isSuccess()
	{
		return success;
	}

	public void setErrorCode(ErrorCode errorCode)
	{
		this.errorCode = errorCode;
	}

	public ErrorCode getErrorCode()
	{
		return errorCode;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public String getMessage()
	{
		return message;
	}

	private static final long serialVersionUID = 3856643567381887995L;

}
