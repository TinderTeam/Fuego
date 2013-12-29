/**   
 * @Title: AssignPlanService.java 
 * @Package cn.tinder.fuego.service 
 * @Description: TODO
 * @author Tang Jun   
 * @date 2013-9-28 上午02:18:13 
 * @version V1.0   
 */
package cn.tinder.fuego.service;

import java.io.File;
import java.util.List;

import cn.tinder.fuego.webservice.struts.bo.assets.AssetsInfoBo;
import cn.tinder.fuego.webservice.struts.bo.base.AssetsBo;
import cn.tinder.fuego.webservice.struts.bo.trans.TransOperInfoBo;


/**
 * 
 * @ClassName: TransPlanService
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-9-28 上午02:28:45
 * 
 */

public interface TransPlanService<E>
{
	public E createPlan(String user);
	
	public E createPlan(String user,List<String> childUserList);


	public int getMaxStep(String transID);
	
	public boolean isApporalStep(int step);
   
	public void deletePlan(String transID);

	public void updatePlan(E plan);

	public void forwardNext(String transID);
	
	public void forwardNext(String transID,String transInfo);

	
	public void backward(String transID,String transInfo);
 

	public E getPlanByTransID(String transID);
	
	public void validate(E plan);
	
	public File getExportFile(E plan);
	public List<AssetsInfoBo> importByFile(File file);
	
	public int getPlanCount(List<String> transIDList);
	public float getPlanAssetsSumValue(List<String> transIDList);

}
