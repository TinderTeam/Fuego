package cn.tinder.fuego.webservice.struts.action.systemdatainput;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.tinder.fuego.service.ServiceContext;
import cn.tinder.fuego.service.sys.SystemParaService;
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.bo.SystemDataInputPageBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;



/**
 * 
* @ClassName: SystemDataImputInitAction 
* @Description: TODO
* @author Kong Jingliang
* @date 2013-10-1 下午07:11:08 
*
 */
public class SystemDataInputInitAction extends Action
{
    private static final Log log = LogFactory.getLog(SystemDataInputInitAction.class);
    
    private SystemParaService systemParaService = ServiceContext.getInstance().getSystemParaService();
     
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
    	log.debug(LogKeyConst.INPUT_ACTION+"SystemDataInputInitAction");
        
    	String nextPage = PageNameConst.SYSTEM_DATA_INPUT_PAGE;
  
    	SystemDataInputPageBo systemDataInputPage = new  SystemDataInputPageBo();
    	systemDataInputPage=null;

        log.debug(LogKeyConst.NEXT_PAGE+nextPage);
        return mapping.findForward(nextPage);	

    }
 

}
