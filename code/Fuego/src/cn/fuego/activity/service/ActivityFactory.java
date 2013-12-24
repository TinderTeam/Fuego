package cn.fuego.activity.service;

import cn.fuego.activity.domain.po.BasicActivityRecord;
/**
 * 
* @ClassName: ActivityService 
* @Description: TODO
* @author Nan Bowen
* @date 2013-12-14 下午03:50:31 
*
 */
public interface ActivityFactory {
	/**
	 * 通过配置文件路径创建事务
	 * @param srcPath
	 * @return
	 */
	public BasicActivityRecord createBusinessActivity(String srcPath);
	
}
