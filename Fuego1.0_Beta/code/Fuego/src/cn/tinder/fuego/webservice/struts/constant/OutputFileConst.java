package cn.tinder.fuego.webservice.struts.constant;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;

import org.apache.tools.ant.util.StringUtils;
import org.junit.Test;

import cn.tinder.fuego.util.ConfigInformation;
import cn.tinder.fuego.util.constant.ConfigItemNameConst;

public class OutputFileConst {
	public static final String ASSETS_STATUES_FILE_PATH =getResourcePath()+"AssetsStatues.xls";
	public static final String ASSETS_STATUES_FILE_MODEL_PATH = getResourcePath()+"Model\\AssetsStatuesModel.xls";
	public static final String PURCHASE_PLAN_MODE = getResourcePath()+"Model\\PurchasePlanModel.xls";
	public static final String PURCHASE_PLAN_FILE_PATH = getResourcePath()+"PurchasePlanFile.xls";
	public static final String PURCHASE_PLAN_FILE_MODEL_PATH = getResourcePath()+"Model\\PurchasePlanFileModel.xls";
	public static final String UPLOAD_FILE_PATH =getResourcePath()+"upload\\";
	public static final String ASSIGN_FILE_MODEL_PATH =getResourcePath()+"Model\\AssignFileModel.xls";
	public static final String ASSIGN_FILE_PATH =getResourcePath()+"AssignFile.xls";
	public static final String RECAPTUREPLAN_FILE_PATH = getResourcePath()+"RecapturePlanFile.xls";
	public static final String RECAPTUREPLAN_FILE_MODEL_PATH = getResourcePath()+"Model\\RecapturePlanFileModel.xls";
	public static final String DISCARD_PLAN_FILE_PATH = getResourcePath()+"DiscardPlanFile.xls";
	public static final String DISCARD_PLAN_FILE_MODEL_PATH = getResourcePath()+"Model\\DiscardPlanFileModel.xls";
	public static final String RECIEVE_FILE_PATH =  getResourcePath()+"RecieveFile.xls";
	public static final String RECIEVE_FILE_MODEL_PATH =getResourcePath()+"Model\\AssetsReceivingModel.xls";
	
	public static final String GOOGLE_BROWSE_PATH = getToolPath() + File.separator + ConfigInformation.getPropertyByName(ConfigItemNameConst.GOOGLE_BROWSE);


	/**
	 * 类路径获取
	 * @author Winter Lau
	 * @date 2009-12-4 下午03:29:43
	 */
	
	private static String getResourcePath() 
	{
			String path = OutputFileConst.class.getClassLoader().getResource(File.separator).getPath();
			path=path.substring(1);
			path=URLDecoder.decode(path);
			path=path.replace("/WEB-INF/classes/", "/files/");
			return path;
	}
	
	private static String getToolPath()
	{
		return getWebAppPath() + File.separator + "tools";
	}
	private static String getWebAppPath()
	{
		String path = OutputFileConst.class.getClassLoader().getResource(File.separator).getPath();
		path=URLDecoder.decode(path);
		path += ".."+File.separator + ".."; 
		
		
		return path;
 	}

	
		
}
