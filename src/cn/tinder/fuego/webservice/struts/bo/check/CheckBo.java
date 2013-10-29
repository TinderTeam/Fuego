package cn.tinder.fuego.webservice.struts.bo.check;

import java.util.List;

public class CheckBo {

	private List<String> gasStation ;
	
	private List<String> deptName ;
	
	private List<String> assetsType ;
	

		

	public List<String> getGasStation() {
		return gasStation;
	}

	public void setGasStation(List<String> gasStation) {
		this.gasStation = gasStation;
	}

	public List<String> getDeptName() {
		return deptName;
	}

	public void setDeptName(List<String> deptName) {
		this.deptName = deptName;
	}

	public List<String> getAssetsType() {
		return assetsType;
	}

	public void setAssetsType(List<String> assetsType) {
		this.assetsType = assetsType;
	}

	@Override
	public String toString() {
		return "CheckBo [gasStation=" + gasStation + ", deptName=" + deptName
				+ ", assetsType=" + assetsType + "]";
	}


	
	
}
