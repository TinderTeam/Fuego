package cn.tinder.fuego.stub.strust.form;

import cn.tinder.fuego.webservice.struts.form.purchase.PurchasePlanForm;

public class PurchasePlanFormStub {
	public static PurchasePlanForm getFullFrom(){
		PurchasePlanForm form= new PurchasePlanForm();
		form.setDate("2010-01-01");
		form.setDuty("三家村加油站");
		form.setManageName("惠阳经营管理部");
		String[] type = {"全部"};		
		form.setTypeList(type);		
		return form;		
	}
	
	public static PurchasePlanForm getRefCreateFrom(){
		PurchasePlanForm form= new PurchasePlanForm();
		form.setDate("2010-01-01");
		form.setDuty("测试加油站");
		form.setManageName("全部");
		String[] type = {"全部"};		
		form.setTypeList(type);		
		return form;		
	}
}
