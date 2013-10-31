/**   
* @Title: AllocationInitAction.java 
* @Package cn.tinder.fuego.webservice.struts.action.allocation 
* @Description: TODO
* @author Tang Jun   
* @date 2013-9-24 下午11:55:04 
* @version V1.0   
*/ 
package cn.tinder.fuego.webservice.struts.action.apply;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.tinder.fuego.service.GasAssetsApplyService;
import cn.tinder.fuego.service.ServiceContext;
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.bo.apply.GasAssetsApplyInitPageBo;
import cn.tinder.fuego.webservice.struts.bo.base.SystemUserBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;

/**
 * 
* @ClassName: GasAssetsApplyInitAction 
* @Description: TODO
* @author Kong Jingliang
* @date 2013-10-1 下午05:42:01 
*
 */

public class GasAssetsApplyInitAction extends Action
{
    private static final Log log = LogFactory.getLog(GasAssetsApplyInitAction.class);
    
    private GasAssetsApplyService  gasAssetsApplyService = ServiceContext.getInstance().getGasAssetsApplyService();
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
    	log.info(LogKeyConst.INPUT_ACTION+"GasAssetsApplyInitAction");
    	
    	String pageName = PageNameConst.GAS_ASSETS_APPLY_PAGE;
    	
    	//Bo
    	GasAssetsApplyInitPageBo pageBo = null;
    	SystemUserBo user = (SystemUserBo) request.getSession().getAttribute(RspBoNameConst.SYSTEM_USER);
        
    	/*
    	 * Get Pagebo from the user and the DAO
    	 */
    	pageBo = gasAssetsApplyService.getPageBoByUser(user);
    //	pageBo = GasAssetsApplyTestService.get(user);
        
    	request.setAttribute(RspBoNameConst.ASSETS_LIST,pageBo.getAssetsList());
    	request.setAttribute(RspBoNameConst.ASSETS_PAGE_DATA,pageBo.getAssetBoList());
    	request.setAttribute(RspBoNameConst.TYPE_LIST,pageBo.getTypeList());
    	request.setAttribute(RspBoNameConst.TECH_LIST,pageBo.getTechList());
    	
    	//Send Bo for Next Page useing
    	request.getSession().setAttribute(RspBoNameConst.PAGE_BO,pageBo);
        
        log.info(LogKeyConst.NEXT_PAGE+pageName);
        return mapping.findForward(pageName);	

    }

}
