package cn.tinder.fuego.service.util;

import java.io.File;
import java.util.List;
import java.util.Map;

import jxl.write.WritableSheet;

import org.apache.struts.upload.FormFile;

import cn.tinder.fuego.service.model.AssignSumModel;
import cn.tinder.fuego.util.engine.jxl.ExcelReader;
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsInfoBo;

public interface ExcelIOService {
	public void writeLabel(WritableSheet sheet, int x, int y, String str);
	
	public void simpleWriteLabel(WritableSheet sheet, int x, int y, String str);

	public File uploadFile(FormFile file);

	
	public Map<AssignSumModel, List<AssetsInfoBo>> loadDataToPlanList(ExcelReader er);

}
