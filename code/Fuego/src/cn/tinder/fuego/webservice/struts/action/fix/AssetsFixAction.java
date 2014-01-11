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
import cn.tinder.fuego.webservice.struts.constant.OutputFileConst;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;

/** 
 * 
* @ClassName: AssetsFixAction 
* @Description: TODO
* @author Nan Bowen
* @date 2014-1-11 下午08:53:58 
*
 */
public class AssetsFixAction extends Action
{   private static final Log log = LogFactory.getLog(AssetsFixAction.class);


public ActionForward execute(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception
{
	log.info(LogKeyConst.INPUT_ACTION+"BasicDataInitAction");
    
	String nextPage = PageNameConst.BASIC_DATA_PAGE;

	
	
	request.setAttribute(RspBoNameConst.DOWN_LOAD_FILE, OutputFileConst.DISCARD_FILE_MODEL_PATH);
	nextPage = PageNameConst.DOWNLOAD_ACTION;
	
	
	
    log.info(LogKeyConst.NEXT_PAGE+nextPage);
    return mapping.findForward(nextPage);	

}

}
