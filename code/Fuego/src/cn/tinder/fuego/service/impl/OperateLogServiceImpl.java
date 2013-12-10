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
import cn.tinder.fuego.service.constant.OperateLogConst;
import cn.tinder.fuego.service.model.OperateLogModel;
import cn.tinder.fuego.service.model.convert.ConvertAssetsModel;
import cn.tinder.fuego.webservice.struts.bo.log.AssetsOperateLogBo;
import cn.tinder.fuego.webservice.struts.bo.page.PageModelBo;
import cn.tinder.fuego.webservice.struts.form.log.OperateLogFilterForm;

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
		operateDao.create(operRecordList);
		
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


	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.OperateLogService#getAssetsOperateLog(cn.tinder.fuego.webservice.struts.form.log.OperateLogFilterForm)
	 */
	@Override
	public List<AssetsOperateLogBo> getAssetsOperateLog(OperateLogFilterForm filter)
	{
		OperateRecord assetsFilter = new OperateRecord();
		assetsFilter.setAssets(new PhysicalAssetsStatus());
 		OperateRecord assetsFilterDate = new OperateRecord();
		assetsFilterDate.setAssets(new PhysicalAssetsStatus());
		if((null != filter.getAssetsID()) && (filter.getAssetsID().trim().isEmpty()))
		{
			assetsFilter.getAssets().setAssetsID(null);
		}
		else
		{
			assetsFilter.getAssets().setAssetsID(filter.getAssetsID());	
		}
	 
		if((null != filter.getAssetsName()) && (filter.getAssetsName().trim().isEmpty()))
		{	
			assetsFilter.getAssets().setAssetsName(null);
		}else{
			assetsFilter.getAssets().setAssetsName(filter.getAssetsName());
		}
 
 
 
	    List<AssetsOperateLogBo> operateLogList  = new ArrayList<AssetsOperateLogBo>();

		int count = operateDao.getAssetsOperateLogByFilterCount(assetsFilter, assetsFilterDate);
		PageModelBo<AssetsOperateLogBo> page = new PageModelBo<AssetsOperateLogBo>();
		page.setCount(count);
		page.setCurrentPage(filter.getPageNum());
		List<OperateRecord> recordList;
 
		recordList = operateDao.getAssetsOperateLogByFilter(assetsFilter,assetsFilterDate,page.getStartNum(),page.getPageSize());				
		 
		 for(OperateRecord record : recordList)
		 {
			 AssetsOperateLogBo operateLog = new AssetsOperateLogBo();
			 operateLog.setUserName(record.getUserName());
			 operateLog.setOperName(record.getOperate());
			 operateLog.setOperTime(record.getTime());
			 operateLog.setTransID(record.getTransID());
			 operateLog.setAssets(ConvertAssetsModel.convertAssets(record.getAssets()));
			 operateLogList.add(operateLog);
		 }
  
		return operateLogList;
		
	}


	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.OperateLogService#getAllOperateName()
	 */
	@Override
	public List<String> getAllOperateName()
	{
		// TODO Auto-generated method stub
		List<String> operateNameList = new ArrayList<String>();
		operateNameList.add(OperateLogConst.ASSETS_ADD_OPERATE);
		operateNameList.add(OperateLogConst.ASSETS_DELETE_OPERATE);
		operateNameList.add(OperateLogConst.ASSETS_UPDATE_OPERATE);

		return operateNameList;
	}

}
