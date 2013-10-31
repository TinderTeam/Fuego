package cn.tinder.fuego.service.exception;

public class ServiceException extends RuntimeException
{
	/**
	 * 
	 */

	public ServiceException()
	{
		super();
	}

	public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
	{
		// super(message, cause, enableSuppression, writableStackTrace);
	}

	public ServiceException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public ServiceException(String message)
	{
		super(message);
	}

	public ServiceException(Throwable cause)
	{
		super(cause);
	}

	@Override
	public String toString()
	{
		String message = getLocalizedMessage();
		return (message != null) ? (message) : "";

	}
}
