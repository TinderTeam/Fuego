package cn.tinder.fuego.webservice.struts.form.purchase;

import java.util.Arrays;

import cn.tinder.fuego.webservice.struts.form.AssetsFilterForm;

public class PurchaseAssetsSelectForm extends AssetsFilterForm{
	
	String [] assetsIDList ;
	
	
	
	public String[] getAssetsIDList() {
		return assetsIDList;
	}
	
	@Override
	public String toString() {
		return "PurchaseAssetsSelectForm [assetsIDList="
				+ Arrays.toString(assetsIDList) + "]"+super.toString();
	}
	public void setAssetsIDList(String[] assetsIDList) {
		this.assetsIDList = assetsIDList;
	}
	
	
	

	
	
	
}
