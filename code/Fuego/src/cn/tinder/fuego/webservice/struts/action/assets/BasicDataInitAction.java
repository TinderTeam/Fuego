/**   
* @Title: BasicDataInitAction.java 
* @Package cn.tinder.fuego.webservice.struts.action.assets 
* @Description: TODO
* @author Zhu Liucao   
* @date 2013-10-19 下午06:37:27 
* @version V1.0   
*/ 
package cn.tinder.fuego.webservice.struts.action.assets;

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
 * @ClassName: BasicDataInitAction 
 * @Description: TODO
 * @author Zhu Liucao
 * @date 2013-10-19 下午06:37:27 
 *  
 */
public class BasicDataInitAction extends Action
{   private static final Log log = LogFactory.getLog(BasicDataInitAction.class);


public ActionForward execute(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception
{
	log.info(LogKeyConst.INPUT_ACTION+"BasicDataInitAction");
    
	String nextPage = PageNameConst.BASIC_DATA_PAGE;

	/* Modify By Nanbowen
	 * 2013-11-21
	 * 原因：发现多余代码，基础数据导入界面不需要显示任何信息故将下列代码删除
	
	
	//SessionBo
	//PurchaseWarehousingPageBo purchaseWarehousingPage = null;
	//DisplayBo
	List<AssetsPuchaseDeployBo> assetsPuchaseDeployBo = null;
	/*
	 * Search and show exist purchasePlan. 
	
	
	
	
	assetsPuchaseDeployBo = TransactioServiceTest.getAssetsPDBo();
	
	request.setAttribute(RspBoNameConst.DEPLOY_LIST, assetsPuchaseDeployBo);
	 */
	
	
	
	
	
	
    log.info(LogKeyConst.NEXT_PAGE+nextPage);
    return mapping.findForward(nextPage);	

}

}
