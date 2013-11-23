/**   
 * @Title: AssetExtAtrr.java 
 * @Package cn.tinder.fuego.webservice.struts.bo.assets 
 * @Description: TODO
 * @author Tang Jun   
 * @date 2013-10-2 下午07:09:47 
 * @version V1.0   
 */
package cn.tinder.fuego.webservice.struts.bo.assets;

import org.apache.struts.action.ActionForm;

import cn.tinder.fuego.service.constant.AssetsConst;

/**
 * @ClassName: AssetExtAtrr
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-10-2 下午07:09:47
 * 
 */

public class AssetsExtAttrBo extends ActionForm
{
	private String checkState = AssetsConst.CHECK_STATUS_TODO;
	private int 	checkCnt = 1;
	private String receiveState = AssetsConst.RECEIVE_STATUS_TODO;
	private String note;
	private String currentValue;
	
	/*
		 * Edit By Bowen Nan
		 * Issue #42
		 * 17:02 2013/11/23*/

	
	public int getCheckCnt()
	{
		return checkCnt;
	}

	public void setCheckCnt(int checkCnt)
	{
		this.checkCnt = checkCnt;
	}

	public String getCheckState()
	{
		return checkState;
	}

	public void setCheckState(String checkState)
	{
		this.checkState = checkState;
	}

	public String getNote()
	{
		return note;
	}

	public void setNote(String note)
	{
		this.note = note;
	}

	public String getReceiveState()
	{
		return receiveState;
	}

	public void setReceiveState(String receiveState)
	{
		this.receiveState = receiveState;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AssetsExtAttrBo [checkState=" + checkState + ", checkCnt="
				+ checkCnt + ", receiveState=" + receiveState + ", note="
				+ note + ", currentValue=" + currentValue + "]";
	}

	public void setCurrentValue(String string) {
		this.currentValue = string;
	}

	public String getCurrentValue() {
		return currentValue;
	}


}
