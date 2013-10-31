package cn.tinder.fuego.webservice.struts.bo.check;

import java.util.List;

public class ResultBo {

	private String number  ;
	
	private String gasName ;
	
	private String deptName ;
	
	private String assetsName ;
	
	private String assetsType ;
		
	private String dept ;
	
	private String accountQunty ;
	
	private String actQunty ;
	
	private String diff ;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getGasName() {
		return gasName;
	}

	public void setGasName(String gasName) {
		this.gasName = gasName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getAssetsName() {
		return assetsName;
	}

	public void setAssetsName(String assetsName) {
		this.assetsName = assetsName;
	}

	public String getAssetsType() {
		return assetsType;
	}

	public void setAssetsType(String assetsType) {
		this.assetsType = assetsType;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getAccountQunty() {
		return accountQunty;
	}

	public void setAccountQunty(String accountQunty) {
		this.accountQunty = accountQunty;
	}

	public String getActQunty() {
		return actQunty;
	}

	public void setActQunty(String actQunty) {
		this.actQunty = actQunty;
	}

	public String getDiff() {
		return diff;
	}

	public void setDiff(String diff) {
		this.diff = diff;
	}

	@Override
	public String toString() {
		return "ResultBo [number=" + number + ", gasName=" + gasName
				+ ", deptName=" + deptName + ", assetsName=" + assetsName
				+ ", assetsType=" + assetsType + ", dept=" + dept
				+ ", accountQunty=" + accountQunty + ", actQunty=" + actQunty
				+ ", diff=" + diff + "]";
	}
		



	
}
