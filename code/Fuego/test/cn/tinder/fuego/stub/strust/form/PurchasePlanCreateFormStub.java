package cn.tinder.fuego.stub.strust.form;

import cn.tinder.fuego.webservice.struts.form.purchase.PurchasePlanCreateForm;

	public class PurchasePlanCreateFormStub {
		public static PurchasePlanCreateForm getFullFrom(){
			PurchasePlanCreateForm form= new PurchasePlanCreateForm();
			
			form.setAssetsCreateBo(null);
			
			return form;		
		}
		
		public static PurchasePlanCreateForm getNullFrom(){
			PurchasePlanCreateForm form= new PurchasePlanCreateForm();
			return form;		
		}
}
