package cn.tinder.fuego.util.engine.jxl.exception;

public class ExcelWriterException extends RuntimeException
{
	/**
	 * 
	 */

	public ExcelWriterException()
	{
		super();
	}

	public ExcelWriterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
	{
		// super(message, cause, enableSuppression, writableStackTrace);
	}

	public ExcelWriterException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public ExcelWriterException(String message)
	{
		super(message);
	}

	public ExcelWriterException(Throwable cause)
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
