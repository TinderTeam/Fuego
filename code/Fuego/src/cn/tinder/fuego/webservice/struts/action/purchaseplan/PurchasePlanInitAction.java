package cn.tinder.fuego.webservice.struts.action.purchaseplan;



import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.tinder.fuego.service.LoadService;
import cn.tinder.fuego.service.ServiceContext;
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.util.date.DateService;
import cn.tinder.fuego.webservice.struts.bo.PurchasePlanRefSelectBoTest;
import cn.tinder.fuego.webservice.struts.bo.base.MenuTreeBo;
import cn.tinder.fuego.webservice.struts.bo.base.SystemUserBo;
import cn.tinder.fuego.webservice.struts.bo.purchaseplan.PurchasePlanPageBo;
import cn.tinder.fuego.webservice.struts.bo.purchaseplan.PurchasePlanSessionBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;

/**
 * 
* @ClassName: PurchasePlanInitAction 
* @Description: TODO
* @author Nan Bowen
* @date 2013-9-24 下午07:31:18 
*
 */
public class PurchasePlanInitAction extends Action
{
    private static final Log log = LogFactory.getLog(PurchasePlanInitAction.class);
    //Service
    private LoadService  loadService = ServiceContext.getInstance().getLoadService();
    
    @Override

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
    	log.info(LogKeyConst.INPUT_ACTION+"PurchasePlanInitAction");
    	//Page
    	String pageName = PageNameConst.PURCHASE_PLAN_PAGE;
    	//RequestIn
    	
    	SystemUserBo user = (SystemUserBo) request.getSession().getAttribute(RspBoNameConst.SYSTEM_USER);
	    	//EmptyTest
	    	if(null == user){
	    		log.error("the user is null");    		
	    		pageName = PageNameConst.LOGIN_PAGE;
	    		return mapping.findForward(pageName);	
	    	}
	    	
	    	
    	//Out for show
    	//Department Name for show
    	String departName = user.getDeptName();
    	List<String> typeList = loadService.loadAssetsTypeList();
    	String today = DateService.DateToString(new Date(System.currentTimeMillis()));    	
    	
    	log.info("BO Show: dptName="+departName+";typeList="+typeList+";today="+today+";");
    	
    	request.getSession().setAttribute(RspBoNameConst.PURCHASE_PLAN_DATA, new PurchasePlanSessionBo());
		
    	//request
        request.setAttribute(RspBoNameConst.DEPT_NAME,departName);
        request.setAttribute(RspBoNameConst.TYPE_LIST,typeList);
        request.setAttribute(RspBoNameConst.TODAY,today);
        

        log.info(LogKeyConst.NEXT_PAGE+pageName);
        return mapping.findForward(pageName);	

    }
 

}
