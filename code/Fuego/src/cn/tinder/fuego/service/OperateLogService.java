/**   
* @Title: OperateLogService.java 
* @Package cn.tinder.fuego.service 
* @Description: TODO
* @author Tang Jun   
* @date 2013-12-8 上午12:27:50 
* @version V1.0   
*/ 
package cn.tinder.fuego.service;

import java.util.List;

import cn.tinder.fuego.service.model.OperateLogModel;
import cn.tinder.fuego.webservice.struts.bo.log.AssetsOperateLogBo;
import cn.tinder.fuego.webservice.struts.form.log.OperateLogFilterForm;

/** 
 * @ClassName: OperateLogService 
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-12-8 上午12:27:50 
 *  
 */

public interface OperateLogService
{
	public void writeLog(OperateLogModel operInfo);
	public void writeLog(List<OperateLogModel> operInfoList);
	
	public List<AssetsOperateLogBo> getAssetsOperateLog(OperateLogFilterForm filter);
	
	public List<String> getAllOperateName();


}
