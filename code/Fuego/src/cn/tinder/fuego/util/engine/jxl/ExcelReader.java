package cn.tinder.fuego.util.engine.jxl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.service.exception.msg.ExceptionMsg;

public class ExcelReader {
	private static final Log log = LogFactory.getLog(ExcelReader.class);
	    
	private File readFile;
	private int excelRows;	//Excel行数
	private	int rows;		//有效行数
	private int excelLines;	//Excel列数
	private int lines;		//有效列数	
	private int titleRowNumber = 1;	//标题行的行号
	private Map<String,Integer> titleMap;	//标题-列数 图
	private List<List<String>> arrayData;	//数据表
	
	

	
	public List<String> getItem(int row){
		 List<String> list = arrayData.get(row);
		return list;
	}
	
	public ExcelReader(File file){
		//利用文件加载
		readFile = file;
		try{
			excelLoad();
		}catch(Exception ex){
			throw new ExcelReaderException(ex.getMessage());
		}
	}
	
	public ExcelReader(File file , int titleRow){
		//利用文件加载		
		titleRowNumber= titleRow;		
		readFile = file;
		try{
			excelLoad();
		}catch(Exception ex){
			throw new ExcelReaderException(ex.getMessage());
		}
	}

	ExcelReader(String path,int titleRow){
		//利用路径
		titleRowNumber=titleRow;
		
		readFile = new File(path);
		try{
			excelLoad();
		}catch(Exception ex){
			throw new ExcelReaderException(ex.getMessage());
		}
	}
	
	ExcelReader(String path){
		//利用路径
		readFile = new File(path);
		try{
			excelLoad();
		}catch(Exception ex){
			throw new ExcelReaderException(ex.getMessage());
		}
	}

	private void excelLoad() {
		//加载Excel文件
		
		//
		excelReadableCheckOut();
		
		// 工作表操作
		Workbook book;
			
	    try {
	    	book = Workbook.getWorkbook(readFile);
			if (book == null) {
				throw new ServiceException(ExceptionMsg.EXCEL_READ_ERR);
			}
			Sheet sheet = book.getSheet(0);
			if(sheet==null){
				throw new ServiceException(ExceptionMsg.EXCELSHEET_ISNULL);
			}	
			
			excelLines = sheet.getColumns();
			excelRows  = sheet.getRows();
			
			
			//加载标题(填入有效列数)
			titleMap=loadTitleRow(titleRowNumber,sheet);
			if(titleMap.isEmpty())
			{
				throw new ExcelReaderException(ExcelReaderExceptionMsg.TITLE_EMPTY);
			}
			log.info("标题行内容为:"+titleMap);
			
			//创建数据内容表
			arrayData = loadData(titleMap,sheet);	//数据表
			log.info("数据行内容为:"+arrayData);
			
	    }catch(ExcelReaderException excelEx){
			throw new ExcelReaderException(excelEx);
		}catch(Exception ex){
			log.error(ExcelReaderExceptionMsg.UNKNOW_ERR+readFile.getName());
			ex.printStackTrace();
			throw new ExcelReaderException(ExcelReaderExceptionMsg.EXCEL_OPEN_ERR+readFile.getName(),ex);
		}			
	}
	
	
/**
 * 获取数据内容
 * @param tiltleMap2
 * @param sheet
 * @return
 */
private List<List<String>> loadData(Map<String, Integer> titleMap,
		Sheet sheet) {
	// TODO Auto-generated method stub
	List<List<String>> dataList = new ArrayList<List<String>>();
		
	Cell cell;
	for(int j = 0 ;j<excelRows-titleRowNumber;j++){	
		int empt = 1;
		List<String> strList =new ArrayList<String>();
		for(int i=0 ;i<lines-1;i++){
			cell=sheet.getCell(i,titleRowNumber+j);
			String str = cell.getContents();
			if(!str.isEmpty()){
				empt=0;
			}
			
			strList.add(str);		
		}
		if(empt==0){
			rows=j;
			dataList.add(strList);
		}else{
			rows=j;
			return dataList;
		}
		
	}
	return dataList;
}

/*
 * 获取Excel的标题行
 */
private Map<String, Integer> loadTitleRow(int titleRow, Sheet sheet) {
	Map<String,Integer> map = new HashMap<String,Integer>();
	
	Cell cell;
	int i =0;
    while(true){
    if(i>=excelLines){
    	lines=i+1;
    	return map;
    }
	cell=sheet.getCell(i,titleRow-1);
	String titleName=cell.getContents();
	if(titleName==null||titleName.isEmpty()){
		lines=i+1;
		return map;
	}else{
		map.put(titleName,i);
	}
		i++;
	}
}



/**
 * 表格可读性校验
 */
	private void excelReadableCheckOut() {
		
		if(!readFile.exists()){
			log.error(ExcelReaderExceptionMsg.FILE_NOT_EXIST+readFile.getName());
			throw new ExcelReaderException(ExcelReaderExceptionMsg.FILE_NOT_EXIST+readFile.getName());
		}
		if (readFile.getName().indexOf(".xls") <= 0){
			log.error(ExcelReaderExceptionMsg.FILE_TYPE_ERR+readFile.getName());
            throw new ExcelReaderException(ExcelReaderExceptionMsg.FILE_TYPE_ERR+readFile.getName());
		}

       
	}

/**
 * @return the readFile
 */
public File getReadFile() {
	return readFile;
}

/**
 * @param readFile the readFile to set
 */
public void setReadFile(File readFile) {
	this.readFile = readFile;
}

/**
 * @return the excelRows
 */
public int getExcelRows() {
	return excelRows;
}

/**
 * @param excelRows the excelRows to set
 */
public void setExcelRows(int excelRows) {
	this.excelRows = excelRows;
}

/**
 * @return the rows
 */
public int getRows() {
	return rows;
}

/**
 * @param rows the rows to set
 */
public void setRows(int rows) {
	this.rows = rows;
}

/**
 * @return the excelLines
 */
public int getExcelLines() {
	return excelLines;
}

/**
 * @param excelLines the excelLines to set
 */
public void setExcelLines(int excelLines) {
	this.excelLines = excelLines;
}

/**
 * @return the lines
 */
public int getLines() {
	return lines;
}

/**
 * @param lines the lines to set
 */
public void setLines(int lines) {
	this.lines = lines;
}

/**
 * @return the titleRowNumber
 */
public int getTitleRowNumber() {
	return titleRowNumber;
}

/**
 * @param titleRowNumber the titleRowNumber to set
 */
public void setTitleRowNumber(int titleRowNumber) {
	this.titleRowNumber = titleRowNumber;
}

/**
 * @return the titleMap
 */
public Map<String, Integer> getTitleMap() {
	return titleMap;
}

/**
 * @param titleMap the titleMap to set
 */
public void setTitleMap(Map<String, Integer> titleMap) {
	this.titleMap = titleMap;
}

/**
 * @return the arrayData
 */
public List<List<String>> getArrayData() {
	return arrayData;
}

/**
 * @param arrayData the arrayData to set
 */
public void setArrayData(List<List<String>> arrayData) {
	this.arrayData = arrayData;
}

/**
 * @return the log
 */
public static Log getLog() {
	return log;
}

/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	return "ExcelReader [readFile=" + readFile + ", excelRows=" + excelRows
			+ ", rows=" + rows + ", excelLines=" + excelLines + ", lines="
			+ lines + ", titleRowNumber=" + titleRowNumber + ", titleMap="
			+ titleMap + ", arrayData=" + arrayData + "]";
}
	
	
	
}
