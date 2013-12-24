package cn.fuego.activity.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import cn.fuego.activity.domain.po.BasicActivityRecord;
import cn.fuego.activity.service.ActivityFactory;
import cn.fuego.activity.util.constant.ActivityConstant;

/**
 * 
* @ClassName: AcvivityFactoryImpl 
* @Description: TODO
* @author Nan Bowen
* @date 2013-12-14 下午03:57:09 
*
 */
public class AcvivityFactoryImpl implements ActivityFactory{
	Log log = LogFactory.getLog(ActivityFactory.class);
	
	
	@Override
	public BasicActivityRecord createBusinessActivity(String actvtTypeName) {
		//加载配置文件目录
		ApplicationContext appContext = new FileSystemXmlApplicationContext(ActivityConstant.ACTVT_CFG_PATH);
		//加载业务活动
		BasicActivityRecord actvt= (BasicActivityRecord) appContext.getBean(actvtTypeName);
		
		return actvt;
	}

}
