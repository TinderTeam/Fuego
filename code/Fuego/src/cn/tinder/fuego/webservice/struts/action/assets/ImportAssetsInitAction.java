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
import cn.tinder.fuego.webservice.struts.bo.PurchaseWarehousingPageBo;
import cn.tinder.fuego.webservice.struts.bo.base.AssetsPuchaseDeployBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;



/**
 * 
* @ClassName: PurchaseWarehousingInitAction 
* @Description: TODO
* @author Kaiqiang Zhang
* @date 2013-10-1 下午05:51:54 
*
 */
public class ImportAssetsInitAction extends Action
{
    private static final Log log = LogFactory.getLog(ImportAssetsInitAction.class);
    
     
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
    	log.info(LogKeyConst.INPUT_ACTION+"PurchaseWarehousingInitAction");
        
    	String nextPage = PageNameConst.IMPORT_ASSETS_PAGE;// "PurchaseWarehousing"->"/jsp/purchaseWarehousing.jsp"
  
    	//SessionBo
    	PurchaseWarehousingPageBo purchaseWarehousingPage = null;
    	//DisplayBo
    	List<AssetsPuchaseDeployBo> assetsPuchaseDeployBo = null;
    	/*
    	 * Search and show exist purchasePlan. 
    	 */
    	assetsPuchaseDeployBo = TransactioServiceTest.getAssetsPDBo();
    	
    	request.setAttribute(RspBoNameConst.DEPLOY_LIST, assetsPuchaseDeployBo);

        log.info(LogKeyConst.NEXT_PAGE+nextPage);
        return mapping.findForward(nextPage);	

    }
 

}
