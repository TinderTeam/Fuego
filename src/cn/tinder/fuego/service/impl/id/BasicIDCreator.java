/**   
* @Title: BasicIDCreator.java 
* @Package cn.tinder.fuego.service.impl.id 
* @Description: TODO
* @author Tang Jun   
* @date 2013-10-12 上午12:15:46 
* @version V1.0   
*/ 
package cn.tinder.fuego.service.impl.id;

import cn.tinder.fuego.service.constant.TransactionConst;

/** 
 * @ClassName: BasicIDCreator 
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-10-12 上午12:15:46 
 *  
 */

public class BasicIDCreator
{
	// Convert currentID to TransID
	public static String getPrefixID(String prefix,int length,int currentID)
	{
		String curID = "";
		int m = 0;
		curID = String.valueOf(currentID);
		m = length - curID.length();
		curID = prefix + getZeroStr(m) + curID;

		return curID;

	}
	// get some "0" and add to transID
	private static String getZeroStr(int n)
	{
		String str = "";
		for (int i = 0; i < n; i++)
		{
			str = str + "0";
		}

		return str;

	}
}
