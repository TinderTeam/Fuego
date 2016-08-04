package cn.tinder.fuego.webservice.struts.bo.check;

public class AssetsCheckBo {

	private String dept ;
	private String gasName ;
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getGasName() {
		return gasName;
	}
	public void setGasName(String gasName) {
		this.gasName = gasName;
	}
	@Override
	public String toString() {
		return "AssetsCheckBo [dept=" + dept + ", gasName=" + gasName + "]";
	}
	
	
}
