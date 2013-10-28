package cn.tinder.fuego.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MyFilter implements Filter
{

	private FilterConfig filterCongig = null;
	private String encoding = null;

	public void init(FilterConfig filterConfig) throws ServletException
	{
		this.filterCongig = filterConfig;
		this.encoding = filterConfig.getInitParameter("encoding");

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		if (request.getCharacterEncoding() == null)
		{
			if (encoding != null)
			{
				request.setCharacterEncoding(encoding);
			}
		}
		chain.doFilter(request, response);
	}

	public void destroy()
	{
		// TODO Auto-generated method stub

	}

	public FilterConfig getFilterCongig()
	{
		return filterCongig;
	}

	public void setFilterCongig(FilterConfig filterCongig)
	{
		this.filterCongig = filterCongig;
	}

	public String getEncoding()
	{
		return encoding;
	}

	public void setEncoding(String encoding)
	{
		this.encoding = encoding;
	}

}
