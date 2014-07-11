package cn.tinder.fuego.webservice.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.tinder.fuego.util.ValidatorUtil;
import cn.tinder.fuego.webservice.struts.bo.base.SystemUserBo;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;

 
/**
 * 
* @ClassName: AuthenticationFilter 
* @Description: TODO
* @author Tang Jun
* @date 2014-3-13 上午01:09:55 
*
 */
public class AuthenticationFilter implements Filter
{

	private static final String LOGIN_URL_FLAG = "login";
	private static final  String LOGIN_PAGE = "jsp/login.jsp";

 

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		 HttpServletRequest httpRequest = (HttpServletRequest)request;
		 
		 HttpServletResponse httpResponse = (HttpServletResponse)response;
		 HttpSession  session = httpRequest.getSession();
		 String url = httpRequest.getRequestURL().toString();
		  
		 //the url does not contains login url, we should check login or not
		
		 if(url.toLowerCase().indexOf(LOGIN_URL_FLAG)<0)
		 {
		     SystemUserBo loginUser = (SystemUserBo) session.getAttribute(RspBoNameConst.SYSTEM_USER);
			 if(null == loginUser || loginUser.getUserID().isEmpty())
			 {
				 httpResponse.sendRedirect(httpRequest.getContextPath()+"/"+LOGIN_PAGE);
			 }
		 }
		 
 
		chain.doFilter(request, httpResponse);
	}


	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy()
	{
		// TODO Auto-generated method stub
		
	}


	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig arg0) throws ServletException
	{
 		
	}
 
}
