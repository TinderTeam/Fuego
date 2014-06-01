package cn.tinder.fuego.webservice.struts.action.search;



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
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsPageBo;
import cn.tinder.fuego.webservice.struts.bo.base.SystemUserBo;
import cn.tinder.fuego.webservice.struts.bo.download.AssetsStatuesFile;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;
import cn.tinder.fuego.webservice.struts.form.AssetsFilterForm;




/**
 * 
* @ClassName: AssetsStatusSearchAction 
* @Description: TODO
* @author Nan Bowen
* @date 2013-10-2 下午01:06:40 
*
 */
public class SerachDownloadAjaxAction extends Action
{            
    private static final Log log = LogFactory.getLog(SerachDownloadAjaxAction.class);
    private AssetsManageService  assetsManageService = ServiceContext.getInstance().getAssetsManageService();
     @Override
     public ActionForward execute(ActionMapping mapping, ActionForm form,
             HttpServletRequest request, HttpServletResponse response)
             throws Exception
     {
        log.info("DownliadAjax Aciton");
        AssetsFilterForm  searchForm = new AssetsFilterForm();
        
        searchForm.setAssetsID(request.getParameter("assetsID"));
        searchForm.setAssetsName(request.getParameter("assetsName"));
        searchForm.setDuty(request.getParameter("duty"));
        searchForm.setManageName(request.getParameter("manageName"));
        searchForm.setLocation(request.getParameter("location"));
        searchForm.setTechState(request.getParameter("techSeate"));
        searchForm.setStartPurchaseDate(request.getParameter("startPurchaseDate"));
        searchForm.setEndPurchaseDate(request.getParameter("endPurchaseDate"));
        searchForm.setStartDueDate(request.getParameter("startDueDate"));     
        searchForm.setEndDueDate(request.getParameter("endDueDate"));
        searchForm.setAssetsType(request.getParameter("assetsType"));
 
        SystemUserBo user = (SystemUserBo) request.getSession().getAttribute(RspBoNameConst.SYSTEM_USER);  

        AssetsPageBo  assetsPageBo= assetsManageService.getAssetsByFilter(user.getUserID(),searchForm,true);
		
		AssetsStatuesFile downfile= new AssetsStatuesFile(assetsPageBo);

		request.getSession().setAttribute(RspBoNameConst.DOWN_LOAD_FILE,downfile.getFile().getAbsolutePath()); 
       
        return null;
     }
	

}
