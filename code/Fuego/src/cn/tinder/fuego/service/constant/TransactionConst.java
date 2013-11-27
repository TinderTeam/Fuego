/**   
 * @Title: TransactionTypeConst.java 
 * @Package cn.tinder.fuego.util.constant 
 * @Description: TODO
 * @author Tang Jun   
 * @date 2013-9-28 上午01:48:33 
 * @version V1.0   
 */
package cn.tinder.fuego.service.constant;

import java.io.File;


/**
 * @ClassName: TransactionTypeConst
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-9-28 上午01:48:33
 * 
 */

public class TransactionConst
{
	public static final String ASSIGN_PLAN_TYPE = "A"; // assign plan type
	public static final String ASSIGN_PLAN_NAME = "实物资产调拨单"; // Transname
	public static final String ASSIGN_PLAN_URL = "AssignSubmitInit.do"; // Transname
	public static final String ASSIGN_MAX_STEP = "5";
	public static final String ASSIGN_APPROVAL_STEP = "4";
	
	public static final String DISCARD_PLAN_TYPE ="D"; // discard plan type
	public static final String DISCARD_PLAN_NAME = "实物资产处置单"; // Transname
	public static final String DISCARD_PLAN_URL = "DiscardSureInit.do"; // Transname
	public static final String DISCARD_MAX_STEP = "3";
	public static final String DISCARD_APPROVAL_STEP = "2";
	
	public static final String RECAPTURE_PLAN_TYPE ="R"; // discard plan type
	public static final String RECAPTURE_PLAN_NAME = "实物资产回收单"; // Transname
	public static final String RECAPTURE_PLAN_URL = "AssetsRecaptureEnsureInit.do"; // Transname
	public static final String RECAPTURE_MAX_STEP = "3";
	public static final String RECAPTURE_APPROVAL_STEP = "2";
	
	public static final String PURCHASE_PLAN_TYPE = "P"; //   plan type
	public static final String PURCHASE_PLAN_NAME = "实物资产采购单"; //   plan type
	public static final String PURCHASE_PLAN_URL = "PurchasePlanEnsureInit.do"; //   plan type
	public static final String PURCHASE_APPROVAL_STEP = "2";
	public static final String PURCHASE_MAX_STEP = "3";

	public static final String CHECK_PLAN_TYPE = "C"; //   plan type
	public static final String CHECK_PLAN_NAME = "实物资产盘点单"; //   plan name
	public static final String CHECK_PLAN_URL = "GasStationCheckStatusEnsureInit.do"; // assign plan type
	public static final String CHECK_MAX_STEP = "2";

	public static final String RECEIVE_PLAN_TYPE = "E"; //   plan type
	public static final String RECEIVE_PLAN_NAME = "实物资产验收单"; //   plan name
	public static final String RECEIVE_PLAN_URL = "ImportAssetsSubmitInit.do"; // assign plan type
	public static final String RECEIVE_MAX_STEP = "2";
     
	public static final String TRANS_LAST_STEP = "1";
	public static final String TRANS_FINISH_STEP = "0";
	
	public static final String NOTICE_PLAN_TYPE = "盘点通知";  // transaction type NOTICE

	public static final int END_STEP_FLAG = 0; // end step
	public static final int ID_LENGTH = 9; // ID length

	public static final String TRRANS_STATUS_TODO = "未提交"; // Transaction state
	public static final String TRANS_STATUS_DONE = "已完成"; // Transaction state
	public static final String TRRANS_STATUS_DOING = "未完成";
	public static final String TRRANS_STATUS_REFUSE = "审批不通过";
	
	public static final String TRRANS_STATUS_READ = "已读";
	public static final String TRRANS_STATUS_NO_READ = "未读";
	
 

}
