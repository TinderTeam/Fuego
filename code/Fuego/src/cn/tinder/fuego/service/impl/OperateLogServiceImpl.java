/**   
* @Title: OperateLogServiceImpl.java 
* @Package cn.tinder.fuego.service.impl 
* @Description: TODO
* @author Tang Jun   
* @date 2013-12-8 上午12:33:40 
* @version V1.0   
*/ 
package cn.tinder.fuego.service.impl;

import java.util.ArrayList;
import java.util.List;

import cn.tinder.fuego.dao.DaoContext;
import cn.tinder.fuego.dao.OperateRecordDao;
import cn.tinder.fuego.domain.po.OperateRecord;
import cn.tinder.fuego.domain.po.PhysicalAssetsStatus;
import cn.tinder.fuego.service.OperateLogService;
import cn.tinder.fuego.service.model.OperateLogModel;

/** 
 * @ClassName: OperateLogServiceImpl 
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-12-8 上午12:33:40 
 *  
 */

public class OperateLogServiceImpl implements OperateLogService
{
	OperateRecordDao operateDao = DaoContext.getInstance().getOperateRecordDao();

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.OperateLogService#writeLog(cn.tinder.fuego.service.model.OperateLogModel)
	 */
	@Override
	public void writeLog(OperateLogModel operInfo)
	{
		OperateRecord operateRecord = covertOperInfoLog(operInfo);
		
		operateDao.create(operateRecord);
 
	}


	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.OperateLogService#writeLog(java.util.List)
	 */
	@Override
	public void writeLog(List<OperateLogModel> operInfoList)
	{
		List<OperateRecord> operRecordList = new ArrayList<OperateRecord>();
		for(OperateLogModel operate : operInfoList)
		{
			operRecordList.add(covertOperInfoLog(operate));
		}
		
	}
	private OperateRecord covertOperInfoLog(OperateLogModel operInfo)
	{
		OperateRecord operateRecord = new OperateRecord();
		operateRecord.setUserName(operInfo.getUserName());
		operateRecord.setTime(operInfo.getOperTime());
		operateRecord.setOperate(operInfo.getOperName());
		operateRecord.setTransID(operInfo.getTransID());
		operateRecord.setAssets((PhysicalAssetsStatus) operInfo.getOperObj());
		return operateRecord;
	}

}
