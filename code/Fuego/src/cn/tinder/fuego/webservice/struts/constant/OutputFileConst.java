package cn.tinder.fuego.webservice.struts.constant;

import java.io.File;

import cn.tinder.fuego.util.ConfigInformation;
import cn.tinder.fuego.util.constant.ConfigItemNameConst;

public class OutputFileConst {
	public static final String ASSETS_STATUES_FILE_PATH =ConfigInformation.getResourcePath()+"AssetsStatues.xls";
	public static final String ASSETS_STATUES_FILE_MODEL_PATH = ConfigInformation.getResourcePath()+"Model\\AssetsStatuesModel.xls";
	public static final String PURCHASE_PLAN_MODE = ConfigInformation.getResourcePath()+"Model\\PurchasePlanModel.xls";
	public static final String PURCHASE_PLAN_FILE_PATH = ConfigInformation.getResourcePath()+"PurchasePlanFile.xls";
	public static final String PURCHASE_PLAN_FILE_MODEL_PATH = ConfigInformation.getResourcePath()+"Model\\PurchasePlanFileModel.xls";
	public static final String UPLOAD_FILE_PATH =ConfigInformation.getResourcePath()+"upload\\";
	public static final String ASSIGN_FILE_MODEL_PATH =ConfigInformation.getResourcePath()+"Model\\AssignFileModel.xls";
	public static final String ASSIGN_FILE_PATH =ConfigInformation.getResourcePath()+"AssignFile.xls";
	public static final String RECAPTUREPLAN_FILE_PATH = ConfigInformation.getResourcePath()+"RecapturePlanFile.xls";
	public static final String RECAPTUREPLAN_FILE_MODEL_PATH = ConfigInformation.getResourcePath()+"Model\\RecapturePlanFileModel.xls";
	public static final String DISCARD_PLAN_FILE_PATH = ConfigInformation.getResourcePath()+"DiscardPlanFile.xls";
	public static final String DISCARD_PLAN_FILE_MODEL_PATH = ConfigInformation.getResourcePath()+"Model\\DiscardPlanFileModel.xls";
	public static final String RECIEVE_FILE_PATH =  ConfigInformation.getResourcePath()+"RecieveFile.xls";
	public static final String RECIEVE_FILE_MODEL_PATH =ConfigInformation.getResourcePath()+"Model\\AssetsReceivingModel.xls";
	
	public static final String GOOGLE_BROWSE_PATH = ConfigInformation.getToolPath() + File.separator + ConfigInformation.getPropertyByName(ConfigItemNameConst.GOOGLE_BROWSE);
	public static final String CHECK_FILE_PATH = ConfigInformation.getResourcePath()+"CheckFile.xls";
	public static final String CHECK_FILE_MODEL_PATH = ConfigInformation.getResourcePath()+"Model\\GasStationCheckModel.xls";
	public static final String PRICEFILE_MODEL =ConfigInformation.getResourcePath()+"Model\\PriceModel.xls";
	public static final String PRICEFILE = ConfigInformation.getResourcePath()+"\\PriceFile.xls";
	public static final String QUOTAFILE_MODEL = ConfigInformation.getResourcePath()+"Model\\QuotaModel.xls";
	public static final String QUOTAFILE = ConfigInformation.getResourcePath()+"\\QuotaFile.xls";
	public static final String DISCARD_FILE_MODEL_PATH =ConfigInformation.getResourcePath()+"Model\\DiscardModel.xls";
	public static final String ASSETS_STATUES_DELETE_MODEL_PATH =ConfigInformation.getResourcePath()+"Model\\DeleteAssetsModel.xls";
	public static final String ASSIGN_MODEL_PATH = ConfigInformation.getResourcePath()+"Model\\AssignModel.xls";
	public static final String ASSETS_FIX_FILE = ConfigInformation.getResourcePath()+"AssetsFixFile.xls";
	public static final String ASSETS_FIX_FILE_DELETE_MODEL = ConfigInformation.getResourcePath()+ "Model\\AssetsFixDeleteFile.xls";
	public static final String ASSETS_FIX_FILE_MODEL = ConfigInformation.getResourcePath()+ "Model\\AssetsFixModeFile.xls";



	
	
	
		
}
