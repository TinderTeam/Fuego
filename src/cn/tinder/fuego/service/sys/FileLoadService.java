package cn.tinder.fuego.service.sys;

import java.io.File;

import cn.tinder.fuego.webservice.struts.bo.purchaseplan.PurchasePlanCreatePageBo;

public interface FileLoadService {
	/**
	 * 
	 * @param file
	 * @return
	 */
	public PurchasePlanCreatePageBo uploadPurchasePlan(File file); 
	
	/**
	 * 
	 * @param fileType
	 * @return
	 */
	public String download(String fileType);
	
	
}
