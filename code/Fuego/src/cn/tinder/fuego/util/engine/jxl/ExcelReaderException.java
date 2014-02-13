package cn.tinder.fuego.util.engine.jxl;

public class ExcelReaderException extends RuntimeException
{
	/**
	 * 
	 */

	public ExcelReaderException()
	{
		super();
	}

	public ExcelReaderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
	{
		// super(message, cause, enableSuppression, writableStackTrace);
	}

	public ExcelReaderException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public ExcelReaderException(String message)
	{
		super(message);
	}

	public ExcelReaderException(Throwable cause)
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
