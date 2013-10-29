package cn.tinder.fuego.webservice.struts.bo.search;

import java.util.ArrayList;
import java.util.List;

import cn.tinder.fuego.webservice.struts.bo.assets.AssetsInfoBo;
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsPageBo;
import cn.tinder.fuego.webservice.struts.bo.base.AssetsBo;

public class AssetsStatusSearchInitPageBo {
	private List<String>	deptList;	//Department 
	
	private List<String>	typeList;	//Assets Type
	
	private List<String>	techList;	//status 
	
	private AssetsPageBo	pageBo = new AssetsPageBo();

	public AssetsStatusSearchInitPageBo(){
		pageBo.setAssetsList(new ArrayList<AssetsInfoBo>());
	}
	
	public AssetsStatusSearchInitPageBo(AssetsPageBo bo){
		pageBo=bo;
	}
	
	
	public List<String> getDeptList() {
		return deptList;
	}

	public void setDeptList(List<String> deptList) {
		this.deptList = deptList;
	}

	public List<String> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<String> typeList) {
		this.typeList = typeList;
	}

	public List<String> getTechList() {
		return techList;
	}

	public AssetsPageBo getPageBo() {
		return pageBo;
	}

	@Override
	public String toString() {
		return "AssetsStatusSearchInitPageBo [deptList=" + deptList
				+ ", typeList=" + typeList + ", techList=" + techList
				+ ", pageBo=" + pageBo + "]";
	}

	public void setPageBo(AssetsPageBo pageBo) {
		this.pageBo = pageBo;
	}

	public void setTechList(List<String> techList) {
		this.techList = techList;
	}


	
	
	
}
