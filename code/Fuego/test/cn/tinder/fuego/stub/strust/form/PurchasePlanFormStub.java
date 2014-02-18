package cn.tinder.fuego.stub.strust.form;

import cn.tinder.fuego.webservice.struts.form.purchase.PurchasePlanForm;

public class PurchasePlanFormStub {
	public static PurchasePlanForm getFullFrom(){
		
		String[] tech = {"损坏","待报废","正常"};	
		String[] type = {"全部"};
		String[] matchMod = {"资产名称"};
		PurchasePlanForm form= new PurchasePlanForm();		
		form.setDate("2098-01-01");
		form.setDuty("全部");
		form.setManageName("全部");
		form.setTechStatusList(tech);
		form.setMatchAttrList(matchMod);
		form.setTypeList(type);		
		return form;		
	}
	/**
	 * 全部匹配，按照2010-01-01的时间。
	 * @return
	 */
	public static PurchasePlanForm getRefCreateFrom(){
		String[] tech = {"损坏","待报废","正常"};	
		String[] type = {"全部"};
		String[] matchMod = {"资产名称","生产厂家","技术参数"};
		PurchasePlanForm form= new PurchasePlanForm();
		form.setDate("2010-01-01");
		form.setDuty("测试加油站");
		form.setManageName("全部");	
		form.setTypeList(type);		
		form.setTechStatusList(tech);
		form.setMatchAttrList(matchMod);		
		return form;		
	}
	/*
	 * 测试油站所有损坏的资产
	 */
	public static PurchasePlanForm getTechIsBadCreateFrom(){
		String[] tech = {"故障"};	
		String[] type = {"全部"};
		String[] matchMod = {"资产名称"};
		PurchasePlanForm form= new PurchasePlanForm();
		form.setDate("2098-01-01");
		form.setDuty("测试加油站");
		form.setManageName("全部");	
		form.setTypeList(type);		
		form.setTechStatusList(tech);
		form.setMatchAttrList(matchMod);		
		return form;		
	}
	
	/*
	 * 测试油站所有只按照名称匹配的资产
	 */
	public static PurchasePlanForm getMatchIsAllCreateFrom(){
		String[] tech = {"故障","待报废","正常"};
		String[] type = {"全部"};
		String[] matchMod = {"资产名称"};
		PurchasePlanForm form= new PurchasePlanForm();
		form.setDate("2098-01-01");
		form.setDuty("测试加油站");
		form.setManageName("全部");	
		form.setTypeList(type);		
		form.setTechStatusList(tech);
		form.setMatchAttrList(matchMod);		
		return form;		
	}
}
