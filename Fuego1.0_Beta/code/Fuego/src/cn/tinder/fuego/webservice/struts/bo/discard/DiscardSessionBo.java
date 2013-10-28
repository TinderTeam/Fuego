package cn.tinder.fuego.webservice.struts.bo.discard;

import java.util.ArrayList;
import java.util.List;

public class DiscardSessionBo {

	private List<AssetsDiscardInfoBo> list = new ArrayList<AssetsDiscardInfoBo>();

	public List<AssetsDiscardInfoBo> getList() {
		return list;
	}

	public void setList(List<AssetsDiscardInfoBo> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "DiscardSessionBo [list=" + list + "]";
	}
	
}
