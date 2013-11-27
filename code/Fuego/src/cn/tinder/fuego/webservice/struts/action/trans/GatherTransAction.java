package cn.tinder.fuego.webservice.struts.action.trans;

import java.util.ArrayList;
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
import cn.tinder.fuego.service.TransPlanService;
import cn.tinder.fuego.service.TransactionService;
import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.bo.base.SystemUserBo;
import cn.tinder.fuego.webservice.struts.bo.trans.TransSumInfoBo;
import cn.tinder.fuego.webservice.struts.bo.trans.TransactionBaseInfoBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.ParameterConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;
import cn.tinder.fuego.webservice.struts.form.TransFilterForm;

/**
 * @author Nan Bowen
 * @function:LoginAction
 */
public class GatherTransAction extends Action
{
	private static final Log log = LogFactory.getLog(GatherTransAction.class);

	private LoadService loadService = ServiceContext.getInstance().getLoadService();
	private TransactionService transactionService =ServiceContext.getInstance().getTransactionService();
 

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
        log.info(LogKeyConst.INPUT_ACTION);
        
    	String nextPage = null;
    	try
    	{
    		nextPage = handle(form,request);
		} 
    	catch(ServiceException e)
    	{
    		log.warn("opration failed",e);
    		request.setAttribute(RspBoNameConst.OPERATE_EXCEPION, e.getMessage());
			nextPage = PageNameConst.ERROR_PAGE; 
    	}
    	catch (Exception e)
		{
			log.error("system error",e);
			nextPage = PageNameConst.SYSTEM_ERROR_PAGE; 
		}

        log.info(LogKeyConst.NEXT_PAGE+nextPage);
        return mapping.findForward(nextPage);	

	}

	private String handle(ActionForm form,HttpServletRequest request)
	{
 
		TransFilterForm filter = (TransFilterForm)form;
		String nextPage = PageNameConst.GATHER_TRANS_PAGE;

		SystemUserBo user = (SystemUserBo) request.getSession().getAttribute(RspBoNameConst.SYSTEM_USER);
		String submitPara = request.getParameter(ParameterConst.SUBMIT_PARA_NAME);
 		if (ParameterConst.CONFIRM_PARA_NAME.equals(submitPara))
		{
 			// Init Notice
 			List<TransactionBaseInfoBo> transList = transactionService.getTransListByUser(user.getUserID(), filter);;
 
 			List<String> transIDList = new ArrayList<String>();
 			for(TransactionBaseInfoBo trans : transList)
 			{
 	 			transIDList.add(trans.getTransID());
 			}
 			TransPlanService transPlanService = ServiceContext.getInstance().getPlanServiceByType(transactionService.getTransTypeByTransName(filter.getTransName()));
 			
 			TransSumInfoBo sumInfo = new TransSumInfoBo();
 			if(!transIDList.isEmpty())
 			{
 	 			sumInfo.setTransNum(transIDList.size());
 	 			sumInfo.setAssetsNum(transPlanService.getPlanCount(transIDList));
 	 			sumInfo.setAssetsValue(transPlanService.getPlanAssetsSumValue(transIDList));
 			}

  			request.setAttribute(RspBoNameConst.TRANS_INFO_LIST, transList);
  			request.setAttribute(RspBoNameConst.TRANS_SUM_INFO, sumInfo);

		}
 		else if(ParameterConst.CANCEL_PARA_NAME.equals(submitPara))
 		{
 			nextPage = PageNameConst.INDEX_INIT_ACTION;
 		}
  
		return nextPage;
	}
	 

}
