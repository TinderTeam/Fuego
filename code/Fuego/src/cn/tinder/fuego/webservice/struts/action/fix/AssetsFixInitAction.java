/**   
* @Title: BasicDataInitAction.java 
* @Package cn.tinder.fuego.webservice.struts.action.assets 
* @Description: TODO
* @author Zhu Liucao   
* @date 2013-10-19 下午06:37:27 
* @version V1.0   
*/ 
package cn.tinder.fuego.webservice.struts.action.fix;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import cn.tinder.fuego.dao.AssetsFixDao;
import cn.tinder.fuego.dao.impl.AssetsFixDaoImpl;
import cn.tinder.fuego.domain.po.AssetsFix;
import cn.tinder.fuego.util.ConfigInformation;
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;

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
	ApplicationContext ctx = new FileSystemXmlApplicationContext( ConfigInformation.getResourcePath()+"/resource/assetsFixBean.xml");
	String nextPage = PageNameConst.ASSETS_FIX;
	
	
	/**
	 * 从数据库读取所有Fix记录
	 */
	AssetsFixDao dao =  (AssetsFixDao) ctx.getBean("assetsFixDao");

	List<AssetsFix> list = dao.getAllAssetsFix();
	log.info(list);
	request.setAttribute(RspBoNameConst.ASSETS_FIX_LIST,list);

    log.info(LogKeyConst.NEXT_PAGE+nextPage);
    return mapping.findForward(nextPage);	

}

}
