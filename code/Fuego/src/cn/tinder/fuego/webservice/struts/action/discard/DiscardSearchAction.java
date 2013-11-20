package cn.tinder.fuego.webservice.struts.action.discard;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.tinder.fuego.service.AssetsManageService;
import cn.tinder.fuego.service.ServiceContext;
import cn.tinder.fuego.service.TransPlanService;
import cn.tinder.fuego.service.constant.TransactionConst;
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.ParameterConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;
import cn.tinder.fuego.webservice.struts.form.AssetsFilterForm;



/**
 * 
* @ClassName: DiscardAction 
* @Description: TODO
* @author Kaiqiang Zhang
* @date 2013-10-1 下午05:14:32 
*
 */
public class DiscardSearchAction extends Action
{
    private static final Log log = LogFactory.getLog(DiscardSearchAction.class);
    private AssetsManageService assetsService = ServiceContext.getInstance().getAssetsManageService();
	
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
    	log.info(LogKeyConst.INPUT_ACTION+"DiscardSearchAction");
        
    	String nextPage = null;
    	AssetsFilterForm discardSearchForm = (AssetsFilterForm)form;	
    	String submitPara = request.getParameter(ParameterConst.SUBMIT_PARA_NAME); 
    	//查询
    	if(submitPara.equals(ParameterConst.SUBMIT_PARA_NAME))
    	{
    		request.setAttribute(RspBoNameConst.DISCARD_SEARCH_FORM, discardSearchForm);
    		nextPage = PageNameConst.DISCARD_SEARCH_INIT;
		}
    	
        log.info("[Jump]:NextPage:"+nextPage);
        return mapping.findForward(nextPage);	

    }

}
