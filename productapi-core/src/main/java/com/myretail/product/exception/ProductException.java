package com.myretail.product.exception;

import com.myretail.product.bo.response.ErrorCode;

public class ProductException extends RuntimeException
{

		private ErrorCode errorCode;

		public ProductException(ErrorCode errorCode)
		{
			this(errorCode, null);
		}

		public ProductException(ErrorCode errorCode, String message)
		{
			super(message);
			this.errorCode = errorCode;
		}

		public ProductException(ErrorCode errorCode, Exception exception, String message)
		{
			super(message + " [E] " + exception.getClass().getName() + ": " + exception.getMessage());
			this.errorCode = errorCode;
		}

		public ErrorCode getErrorCode()
		{
			return errorCode;
		}

		private static final long serialVersionUID = 623807079429109619L;

}
