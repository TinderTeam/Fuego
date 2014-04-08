package cn.tinder.fuego.util.engine.jxl;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.tinder.fuego.util.date.DateService;
import cn.tinder.fuego.util.engine.jxl.exception.ExcelWriterException;
import cn.tinder.fuego.util.engine.jxl.exception.ExcelWriterExceptionMsg;

/**
 * 
* @ClassName: ExcelWriter 
* @Description: This class is to write a MC-Excel file by using JXL. We can easily output a simple-object with only one step.
* Step : Building a ExcelWriter with such parameters in follow: file or path for output, an array of string for each lines' title.
* @Description: (Chinese) ExcelWriter类文件是一个利用JXL写Excel文件的简单模块。只需要进行一步操作就可以将简单标准的对象进行Excel的输出。
* 操作步骤：构建一个ExcelWriter对象并通过传入如下参数的方式： 一个文件对象或者目标文件的路径，一个字符串数组用以存放需要导出的各个参数的标题。
* @author Nan Bowen
* @date 2014-2-4 p.m. 10:13:47 
* @see ExcelTitleModel
*
*/

public class ExcelWriter {
	/*
	 * Basic Component
	 */
	private static final Log log = LogFactory.getLog(ExcelWriter.class);
    
	
	/*
	 *	Private members (core) 核心私有成员 
	 */
	
	private File writeFile;	//File for output 写出的文件
	
	/**
	 * @return the writeFile
	 */
	public File getWriteFile()
	{
		return writeFile;
	}


	/**
	 * @param writeFile the writeFile to set
	 */
	public void setWriteFile(File writeFile)
	{
		this.writeFile = writeFile;
	}


	private Map<ExcelTitleModel,String> lineTitleMap=new HashMap<ExcelTitleModel,String>();	// Map for filter the outputPara and the title 列名-属性对应表
	
	private List<ExcelTitleModel>	filedList=new ArrayList<ExcelTitleModel>(); //List for restore the Field order 属性-顺序对用表
	
	private List<?> obList;	//output Object List. it will be init at the beginning. //输出对象列泛型
	
	private String[] lineTitleName=null;	//Field title 输出字段表头名称
	
	private WritableSheet sheet;	// output sheet. 输出的Excel的Sheet结构
	
	/*
	 *  expand member 拓展用成员
	 */
	
	private String excelTitle;	//	main title 标题行
	private int lines;			// output count 写出列数
	
	

	/**
	 * Construction Function 构造方法
	 * 传入对象列表、标题列表、输出路径的方式
	 * @param list
	 * @param titleName
	 * @param path
	 */
	public ExcelWriter(List<?> list,String[] titleName,String path){
		
		check(list,titleName,path);
		/*
		 * init the member 
		 */
		writeFile= new File(path);
		lineTitleName=titleName;
		/*
		 * init the output bo
		 */
		init(list.get(0).getClass(),0,null);
		obList=list;

		
		/*
		 * 写Excel文件
		 */
		 try {
	
			WritableWorkbook book = Workbook.createWorkbook(writeFile);
			sheet = book.createSheet("sheet", 0);
			
			/*
			 * 1.写入标题信息
			 */
			writeTitle();
			/*
			 * 2.在指定行写入数据（暂时默认为2 ，可拓展）
			 */
			writeExcle(2);
			
			
			
			
		
			book.write();
			book.close();
			
		} catch (IOException e) {			
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * Check the Construction Function Para 
	 * @param list
	 * @param titleName
	 * @param path
	 */
	private void check(List<?> list, String[] titleName, String path) {
		/* 
		 * Condition:
		 * 1. list empty test and size test;
		 * 2. titleName test
		 * 3. path test
		 */
		
		if(null==list||list.isEmpty()){
			log.error(ExcelWriterExceptionMsg.OBJECT_LIST_NULL);
			throw new ExcelWriterException(ExcelWriterExceptionMsg.OBJECT_LIST_NULL);
		}
		
		if(null==titleName||titleName.length==0){
			log.error(ExcelWriterExceptionMsg.FIELD_TITLE_LIST_NULL);
			throw new ExcelWriterException(ExcelWriterExceptionMsg.FIELD_TITLE_LIST_NULL);
		}
		
		if(null==path||path.isEmpty()||!path.endsWith(".xls")){
			log.error(ExcelWriterExceptionMsg.OUTPUT_FILE_FORM_ERR);
			throw new ExcelWriterException(ExcelWriterExceptionMsg.OUTPUT_FILE_FORM_ERR);
		}
		
		
	}



	private void writeTitle() {
		//在指定文件写入标题
		String [] str = getTitleString();
		ExcelWriterUtil.wirteStringsInALine(1,str,sheet);
	}
	
/**
 * 获取按照顺序的输出列标题
 * @return
 */
	private String[] getTitleString() {
		String[] titleList=new String[filedList.size()];
		int i=0;
		for(ExcelTitleModel model :filedList){
			titleList[i]=lineTitleMap.get(model);
			i++;
		}
		return titleList;
	}

	private void writeExcle(int rowCount) {
		/*
		 * 将OBlist的内容按照格式解析出来。
		 */
		
		int row=rowCount;
		
		for(Object ob:obList){
			String[] obList=new String[filedList.size()];
			/*
			 * 对每一个OB进行解析处理
			 */
			//按照列表对所有的队列进行
			int i=0;
			for(ExcelTitleModel model:filedList){
				obList[i]=getStringByOb(model,ob);
				i++;
			}
			//写入一行数据
			ExcelWriterUtil.wirteStringsInALine(row,obList,sheet);
			row++;
		}
	}

	private String getStringByOb(ExcelTitleModel model, Object ob) {
		// 按照对象的名称解析对象（可能含有子结构）

		if(model.getName().contains(".")){
			//包含子结构，如"a.b"的模式，所以先要将两者分离出来。
			log.info(model.getName());
			String father = model.getName().split("\\.")[0];
			String son = model.getName().split("\\.")[1];
			ExcelTitleModel newModel= new ExcelTitleModel();
			newModel.setName(son);
			newModel.setType(model.getType());
		
			return getStringByOb(newModel,getter(ob,father));
		}else{
			//不包含子结构,即简单结构，则按照不同的数据类型进行区分
			Object paraOb= getter(ob,model.getName());
			if(paraOb==null){
				log.error(model.getName());
				return null;
			}
			if(paraOb.getClass().equals(Integer.class)){
				int i = (Integer)paraOb;
				return String.valueOf(i);
			}else if(paraOb.getClass().equals(String.class)){
				return (String)paraOb;
			}else if(paraOb.getClass().equals(Float.class)){
				return String.valueOf(paraOb);
			}else if(paraOb.getClass().equals(Date.class)){
				return DateService.DateToString((Date)paraOb);
			}else{
				return null;
			}
		}

	}

	private String strFirstUpper(String father) {
		String first = father.substring(0, 1).toUpperCase();
		String rest = father.substring(1, father.length());
		String newStr = new StringBuffer(first).append(rest).toString();
		return newStr;
	}

	private int init(Class<?> cla,int count,String paraName){
		log.info("处理对象："+cla.getSimpleName());
		Field[] f=cla.getDeclaredFields();
		int i=count;//从第i列开始（递归用）
		for (Field field : f) {//获取所有的属性
			//判断是否为最简单属性（可直接显示的）
			log.info("-子对象："+field.getName()+"类型："+field.getType());
			if(field.getType().toString().equals("int")||field.getType().toString().equals("float")||field.getType().equals(Date.class)||field.getType().equals(String.class)){
				//为简单属性
				log.info("为简单对象");
				if( lineTitleName.length-1<i||lineTitleName[i]==null||lineTitleName[i].isEmpty()){
			    	//如果参数中表头名称为空，则认为将不会在改Excel中显示对应列,列数计数i和数列内容均不变					
					log.info("无对应名称");
				}else{
			    	//对应出表头名称和简单对象
			    	ExcelTitleModel model = new ExcelTitleModel();
			    	if(paraName!=null){
			    		model.setName(paraName+"."+field.getName());
			    	}else{
			    		model.setName(field.getName());
			    	}
			    	
			    	model.setType(field.getType());
			    	log.info("对应名称为"+ lineTitleName[i]+",模式名称为:"+model.getName());
			    	lineTitleMap.put(model, lineTitleName[i]);
			    	//顺序列表
				    filedList.add(model);	
			    }
			i++;
			}else{
				//为一个复杂类，解析该复杂类
				log.info("-复杂对象："+field.getName()+"类型："+field.getType());
				i=init(field.getType(),i,field.getName());
			}
		
		}
		return i;	
	
	}

	
	public Object  getter(Object obj, String att) {
        try {
            Method method = obj.getClass().getMethod("get" +strFirstUpper( att));
            return method.invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
		return null;
    }
}
