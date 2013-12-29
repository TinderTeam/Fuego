package cn.tinder.fuego.util.engine.computer.statistics;

import java.util.Date;
/**
 * TASK #8 #92 事务增加能分“资产来源”及“资产类别”进行分类统计
* @ClassName: StatisticsServie 
* @Description: TODO
* @author Nan Bowen
* @date 2013-12-29 上午12:56:37 
*
 */
public interface StatisticsServie {

	/**
	 * 资产验收统计
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public String receiveAssetsStatistics(Date startDate, Date endDate);
	
	
	/**
	 * 资产处置统计
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public String discardAssetsStatistics(Date startDate, Date endDate);
	
	/**
	 * 资产调拨统计
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public String assignAssetsStatistics(Date startDate, Date endDate);
}
