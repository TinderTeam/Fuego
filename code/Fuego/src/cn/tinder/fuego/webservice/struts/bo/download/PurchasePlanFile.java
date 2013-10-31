package cn.tinder.fuego.webservice.struts.bo.download;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.service.exception.msg.ExceptionMsg;
import cn.tinder.fuego.service.impl.util.ExcelIOServiceImpl;
import cn.tinder.fuego.service.util.ExcelIOService;
import cn.tinder.fuego.util.date.DateService;
import cn.tinder.fuego.webservice.struts.bo.base.PurchasePlanBo;
import cn.tinder.fuego.webservice.struts.bo.purchaseplan.PurchasePlanSessionBo;
import cn.tinder.fuego.webservice.struts.constant.OutputFileConst;

public class PurchasePlanFile
{

	private static final Log log = LogFactory.getLog(PurchasePlanFile.class);

	private File file;
	private File modFile;
	ExcelIOService excelIOimpl = new ExcelIOServiceImpl();

	public PurchasePlanFile(PurchasePlanSessionBo purchasePlan)
	{

		log.info("进入创建流程");

		/*
		 * 获取输出文件路径
		 */
		file = new File(OutputFileConst.PURCHASE_PLAN_FILE_PATH);
		if (file.exists())
		{
			/*
			 * 删除原有文件，重新构造
			 */
			file.delete();
			file = new File(OutputFileConst.PURCHASE_PLAN_FILE_PATH);
		}

		modFile = new File(OutputFileConst.PURCHASE_PLAN_FILE_MODEL_PATH);
		if (!modFile.exists())
		{
			log.error(ExceptionMsg.MODFILE_NOT_EXIST + " FilePath=" + OutputFileConst.PURCHASE_PLAN_FILE_MODEL_PATH);
			throw new ServiceException(ExceptionMsg.MODFILE_NOT_EXIST);
		}

		// 获取JXL模版
		Workbook modWorkBook;
		try
		{
			modWorkBook = Workbook.getWorkbook(modFile);
			WritableWorkbook workbook = Workbook.createWorkbook(file, modWorkBook);

			/*
			 * 获取校验
			 */
			if (modWorkBook == null)
			{
				log.error("Mod 文件无法加载 为Excel文件");
			}
			if (workbook == null)
			{
				log.error("导出文件无法加载 为Excel文件");
			}

			// 获取一个Sheet 进行读取操作
			WritableSheet sheet = null;
			if (workbook.getNumberOfSheets() > 0)
			{
				sheet = workbook.getSheet(0); // 获取第一个sheet
			} else
			{
				sheet = workbook.createSheet(OutputFileConst.PURCHASE_PLAN_FILE_MODEL_PATH, 0);
			}

			Date date = new Date(System.currentTimeMillis());

			excelIOimpl.writeLabel(sheet, 2, 3, DateService.DateToLongString(date));
			excelIOimpl.writeLabel(sheet, 2, 12, purchasePlan.getPurchaseTransBo().getTransInfo().getCreateUser());
			/*
			 *注意，这里存储着不是HandleUser而是创建者的部门，此处没有为了这一点问题增加一个Bo的字段。因为其实中石化的这个东西你懂得
			 *谁也不真正知道要搞成什么样，我们最适当的决策是应该先完成需求。 
			 */
			excelIOimpl.writeLabel(sheet, 2,6, purchasePlan.getPurchaseTransBo().getTransInfo().getHandleUser());
			
			
			List<PurchasePlanBo> boList = purchasePlan.getPurchasePageBo().getAssetsList();
			
			
			

			int i=boList.size();
			for (PurchasePlanBo bo : boList)
			{

				
				sheet.insertRow(4);
				excelIOimpl.writeLabel(sheet, 5, 1,String.valueOf(i));
				i--;
				excelIOimpl.writeLabel(sheet, 5, 2, bo.getAssetsBo().getAssetsID());
				excelIOimpl.writeLabel(sheet, 5, 3, bo.getAssetsBo().getAssetsName());
				excelIOimpl.writeLabel(sheet, 5, 4, bo.getAssetsBo().getAssetsSRC());
				excelIOimpl.writeLabel(sheet, 5, 5, bo.getAssetsBo().getAssetsType());
				excelIOimpl.writeLabel(sheet, 5, 6, bo.getAssetsBo().getManufacture());
				excelIOimpl.writeLabel(sheet, 5, 7, bo.getAssetsBo().getSpec());
				excelIOimpl.writeLabel(sheet, 5, 8, bo.getAssetsBo().getUnit());
				excelIOimpl.writeLabel(sheet, 5, 9, String.valueOf(bo.getAssetsBo().getQuantity()));
				excelIOimpl.writeLabel(sheet, 5, 10, String.valueOf(bo.getPrice()));
				excelIOimpl.writeLabel(sheet, 5, 11, String.valueOf(bo.getMoney()));
				excelIOimpl.writeLabel(sheet, 5, 12, bo.getAssetsBo().getNote());
			}

		

			workbook.write();// 将修改保存到workbook --》一定要保存
			workbook.close();// 关闭workbook，释放内存 ---》一定要释放内存
			modWorkBook.close();

		} catch (BiffException e)
		{
			// TODO Auto-generated catch block
			log.error(ExceptionMsg.FILE_ERR, e);
			e.printStackTrace();
		} catch (IOException e)
		{
			log.error(ExceptionMsg.FILE_ERR, e);
			e.printStackTrace();
		} catch (WriteException e)
		{
			log.error(ExceptionMsg.FILE_ERR, e);
			e.printStackTrace();
		}

	}

	/**
	 * @return the file
	 */
	public File getFile()
	{
		return file;
	}

	/**
	 * @param file
	 *            the file to set
	 */
	public void setFile(File file)
	{
		this.file = file;
	}

	/**
	 * @return the modFile
	 */
	public File getModFile()
	{
		return modFile;
	}

	/**
	 * @param modFile
	 *            the modFile to set
	 */
	public void setModFile(File modFile)
	{
		this.modFile = modFile;
	}

}
