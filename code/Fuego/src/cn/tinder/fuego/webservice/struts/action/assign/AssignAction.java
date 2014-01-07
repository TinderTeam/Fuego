/**   
 * @Title: AllocationAssertAction.java 
 * @Package cn.tinder.fuego.webservice.struts.action.allocation 
 * @Description: TODO
 * @author Tang Jun   
 * @date 2013-9-24 下午11:53:07 
 * @version V1.0   
 */
package cn.tinder.fuego.webservice.struts.action.assign;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.tinder.fuego.dao.SystemUserDao;
import cn.tinder.fuego.dao.impl.SystemUserDaoImpl;
import cn.tinder.fuego.domain.po.AssignPlan;
import cn.tinder.fuego.service.ServiceContext;
import cn.tinder.fuego.service.TransPlanService;
import cn.tinder.fuego.service.constant.TransactionConst;
import cn.tinder.fuego.service.impl.plan.AssignPlanServiceImpl;
import cn.tinder.fuego.service.impl.util.ExcelIOServiceImpl;
import cn.tinder.fuego.service.model.AssignSumModel;
import cn.tinder.fuego.service.util.ExcelIOService;
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.util.engine.jxl.ExcelReader;
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsInfoBo;
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsPageBo;
import cn.tinder.fuego.webservice.struts.bo.assign.AssignPlanBo;
import cn.tinder.fuego.webservice.struts.bo.base.AssetsBo;
import cn.tinder.fuego.webservice.struts.bo.base.SystemUserBo;
import cn.tinder.fuego.webservice.struts.constant.OutputFileConst;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.ParameterConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;
import cn.tinder.fuego.webservice.struts.form.AssignDeptForm;

/**
 * @ClassName: AllocationAssertAction
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-9-24 下午11:53:07
 * 
 */

public class AssignAction extends Action
{
	private static final Log log = LogFactory.getLog(AssignAction.class);
	ExcelIOService excelIOService = new ExcelIOServiceImpl();
	private TransPlanService planService = ServiceContext.getInstance().getAssignPlanService();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		log.info(LogKeyConst.INPUT_ACTION);
		String pageName = PageNameConst.ASSIGN_CREATE_INIT_ACTION;
		String submitPara = request.getParameter(ParameterConst.SUBMIT_PARA_NAME);
		AssignDeptForm deptForm = (AssignDeptForm) form;
		// get department information list
		SystemUserBo user = (SystemUserBo) request.getSession().getAttribute(RspBoNameConst.SYSTEM_USER);
		
		if(ParameterConst.SUBMIT_PARA_NAME.equals(submitPara)){
			if (null == user || null == deptForm)
			{
				log.error("the user is " + user);
				log.error("the dept form is " + deptForm);
				pageName = PageNameConst.LOGIN_PAGE;
			} else
			{
				AssignPlanBo planInfo = (AssignPlanBo) planService.createPlan(user.getUserID());

				planInfo.getTransInfo().setInDept(deptForm.getInDept());
				planInfo.getTransInfo().setOutDept(deptForm.getOutDept());

				request.getSession().setAttribute(RspBoNameConst.ASSIGN_PLAN_DATA, planInfo);
			}
		}else if(ParameterConst.UPLOAD_PARA_NAME.equals(submitPara)){
			log.info("Upload theFiles");
			ExcelReader excelReader = new ExcelReader(excelIOService.uploadFile(deptForm.getUploadFile()),2);
			
			Map<AssignSumModel, List<AssetsInfoBo>> map = excelIOService.loadDataToPlanList(excelReader);
		
			for(AssignSumModel model :	map.keySet()){
				log.info(model);
				AssignPlanBo assignPlan =(AssignPlanBo) planService.createPlan(user.getUserID());
				assignPlan.getTransInfo().setInDept(model.getAssetsInGas());
				assignPlan.getTransInfo().setOutDept(model.getAssetsOutGas());
				assignPlan.getAssetsPage().setAssetsList(map.get(model));	
				assignPlan.getTransInfo().getTransInfo().setExecuteName(model.getNote());
				assignPlan.getTransInfo().getTransInfo().setHandleUser(model.getDept());
				planService.updatePlan(assignPlan);
				planService.forwardNext(assignPlan.getTransInfo().getTransInfo().getTransID());
			}
			
			log.info(excelReader.toString());
			pageName = PageNameConst.SYSTEM_SUCCESS_PAGE;
		}else if(ParameterConst.DOWNLOAD_PARA_NAME.equals(submitPara)){
			log.info("DownLoadFiles");
			request.setAttribute(RspBoNameConst.DOWN_LOAD_FILE, OutputFileConst.ASSIGN_MODEL_PATH);
			pageName = PageNameConst.DOWNLOAD_ACTION;
		}


		log.info(LogKeyConst.NEXT_PAGE + pageName);
		return mapping.findForward(pageName);

	}
}
