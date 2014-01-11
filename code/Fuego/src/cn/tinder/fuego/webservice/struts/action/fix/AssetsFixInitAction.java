/**   
* @Title: BasicDataInitAction.java 
* @Package cn.tinder.fuego.webservice.struts.action.assets 
* @Description: TODO
* @author Zhu Liucao   
* @date 2013-10-19 下午06:37:27 
* @version V1.0   
*/ 
package cn.tinder.fuego.webservice.struts.action.fix;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;

/**
 * 
* @ClassName: AssetsFixInitAction 
* @Description: TODO
* @author Nan Bowen
* @date 2014-1-11 下午05:56:27 
*
 */
public class AssetsFixInitAction extends Action
{   private static final Log log = LogFactory.getLog(AssetsFixInitAction.class);


public ActionForward execute(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception
{
	log.info(LogKeyConst.INPUT_ACTION+"BasicDataInitAction");
    
	String nextPage = PageNameConst.BASIC_DATA_PAGE;
	
	/**
	 * 从数据库读取所有Fix记录
	 */
    log.info(LogKeyConst.NEXT_PAGE+nextPage);
    return mapping.findForward(nextPage);	

}

}
