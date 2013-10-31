package cn.tinder.fuego.service.util;

import java.io.File;

import org.apache.struts.upload.FormFile;

import jxl.write.WritableSheet;

public interface ExcelIOService {
	public void writeLabel(WritableSheet sheet, int x, int y, String str);
	
	public void simpleWriteLabel(WritableSheet sheet, int x, int y, String str);

	public File uploadFile(FormFile file);


}
