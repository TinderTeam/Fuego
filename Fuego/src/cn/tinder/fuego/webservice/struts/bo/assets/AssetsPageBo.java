/**   
 * @Title: QueryAssetsPageBo.java 
 * @Package cn.tinder.fuego.webservice.struts.bo.query 
 * @Description: TODO
 * @author Tang Jun   
 * @date 2013-9-26 上午01:49:24 
 * @version V1.0   
 */
package cn.tinder.fuego.webservice.struts.bo.assets;

import java.util.ArrayList;
import java.util.List;

import cn.tinder.fuego.service.constant.AssetsConst;
import cn.tinder.fuego.webservice.struts.bo.page.PageModelBo;

/**
 * @ClassName: QueryAssetsPageBo
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-9-26 上午01:49:24
 * 
 */

public class AssetsPageBo
{
	private List<AssetsInfoBo> assetsList = new ArrayList<AssetsInfoBo>();
	
	private PageModelBo page = new PageModelBo();
	private boolean showCheckBox = false;

	private boolean showModifyBtn = false;

	private boolean showCheckState = false;
	
	private boolean showReceiveState = false;

	private boolean showNote = false;

	public List<AssetsInfoBo> getAssetsList()
	{
		return assetsList;
	}

	public void setAssetsList(List<AssetsInfoBo> assetsList)
	{
		this.assetsList = assetsList;
	}

	public boolean isShowCheckBox()
	{
		return showCheckBox;
	}

	public void setShowCheckBox(boolean showCheckBox)
	{
		this.showCheckBox = showCheckBox;
	}

	public boolean isShowModifyBtn()
	{
		return showModifyBtn;
	}

	public void setShowModifyBtn(boolean showModifyBtn)
	{
		this.showModifyBtn = showModifyBtn;
	}

	public boolean isShowCheckState()
	{
		return showCheckState;
	}

	public void setShowCheckState(boolean showCheckState)
	{
		this.showCheckState = showCheckState;
	}

	public boolean isShowNote()
	{
		return showNote;
	}

	public void setShowNote(boolean showNote)
	{
		this.showNote = showNote;
	}

	
	public PageModelBo getPage()
	{
		return page;
	}

	public void setPage(PageModelBo page)
	{
		this.page = page;
	}

	public boolean isShowReceiveState()
	{
		return showReceiveState;
	}

	public void setShowReceiveState(boolean showReceiveState)
	{
		this.showReceiveState = showReceiveState;
	}
	
	public boolean isReceiveFinished()
	{
 		for(AssetsInfoBo assetsInfo : this.getAssetsList())
		{	
			if(AssetsConst.RECEIVE_STATUS_TODO.equals(assetsInfo.getExtAttr().getReceiveState()))
			{
				return false;
			}
		}
 		return true;
	}

	@Override
	public String toString()
	{
		return "AssetsPageBo [assetsList=" + assetsList + ", page=" + page + ", showCheckBox=" + showCheckBox + ", showModifyBtn=" + showModifyBtn + ", showCheckState=" + showCheckState
				+ ", showReceiveState=" + showReceiveState + ", showNote=" + showNote + "]";
	}


}
