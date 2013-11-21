/**   
* @Title: BasicDataInitAction.java 
* @Package cn.tinder.fuego.webservice.struts.action.assets 
* @Description: TODO
* @author Zhu Liucao   
* @date 2013-10-19 下午06:37:27 
* @version V1.0   
*/ 
package cn.tinder.fuego.webservice.struts.action.pricedata;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.action.purchaseWarehousing.TransactioServiceTest;
import cn.tinder.fuego.webservice.struts.bo.base.AssetsPuchaseDeployBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;

/**
 * 
* @ClassName: PriceDataInitAction 
* @Description: TODO
* @author Nan Bowen
* @date 2013-11-22 上午12:36:01 
*
 */
public class PriceDataInitAction extends Action
{   private static final Log log = LogFactory.getLog(PriceDataInitAction.class);


public ActionForward execute(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception
{
	log.info(LogKeyConst.INPUT_ACTION+"PriceDataInitAction");
    
	String nextPage = PageNameConst.PRICE_DATA_PAGE;

    log.info(LogKeyConst.NEXT_PAGE+nextPage);
    return mapping.findForward(nextPage);	

}

}
