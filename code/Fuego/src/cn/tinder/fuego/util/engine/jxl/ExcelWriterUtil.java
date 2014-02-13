package cn.tinder.fuego.util.engine.jxl;

import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ExcelWriterUtil {
	/**
	 * 写一行
	 * @param row
	 * @param str
	 * @param sheet
	 */
	public static void wirteStringsInALine(int row, String[] str, WritableSheet sheet){
		Label label ;
		for(int i =0;i<str.length;i++)  {
			label = new Label(i, row, str[i]);
			try {
				sheet.addCell(label);
			} catch (RowsExceededException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
