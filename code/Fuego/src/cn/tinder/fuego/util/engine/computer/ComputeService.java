package cn.tinder.fuego.util.engine.computer;

import java.util.Date;
import java.util.List;

import cn.tinder.fuego.util.date.DateService;
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsInfoBo;
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsPageBo;

public class ComputeService {
	
	static public float cptUsedYears(String string){
		
		float v;
		Date today= new Date();
		long l=DateService.stringToDate(string).getTime()-today.getTime();
		l=l/1000;
		l=l/3600;
		l=l/24;
		v=l/30;
		v=v/12;
		return v;
	}
	
	static public float cptValue(Date startDate,int limit,float value){
		float v;
		Date today= new Date();
		long l=today.getTime()-startDate.getTime();
		l=l/1000;
		l=l/3600;
		l=l/24;
		v=l/30;
		v=v/12;
		v=v/((long)limit);		
		v=value*v;
			return value-v;		
	}

	static public float cptShowValue(Date startDate,int limit,float value){
		float v;
		Date today= new Date();
		long l=today.getTime()-startDate.getTime();
		l=l/1000;
		l=l/3600;
		l=l/24;
		v=l/30;
		v=v/12;
		v=v/((long)limit);		
		v=value*v;
		if(value-v<0){
			return 0;
		}
			return value-v;		
	}
	
	//cptValue By PageBo
	public static AssetsPageBo cptValueAsList(AssetsPageBo pageBo) {
		
		List<AssetsInfoBo> assetInfoBo = pageBo.getAssetsList();
		for(AssetsInfoBo infoBo:assetInfoBo){
			try{
				//Date
				Date pDate =DateService.stringToDate(infoBo.getAssets().getPurchaseDate());
				//limit
				int limit=infoBo.getAssets().getExpectYear();
				//
				float value=infoBo.getAssets().getOriginalValue();

				infoBo.getExtAttr().setCurrentValue(String.format("%.2f",cptValue(pDate,limit,value)));
			}catch(RuntimeException ex){
				//If Err then set value = 0 ;
				infoBo.getExtAttr().setCurrentValue("0");
			}
			
		}			
		return pageBo;
	}	
	//cptValue By PageBo
	public static AssetsPageBo cptShowValueAsList(AssetsPageBo pageBo) {
		
		List<AssetsInfoBo> assetInfoBo = pageBo.getAssetsList();
		for(AssetsInfoBo infoBo:assetInfoBo){
			try{
				//Date
				Date pDate =DateService.stringToDate(infoBo.getAssets().getPurchaseDate());
				//limit
				int limit=infoBo.getAssets().getExpectYear();
				//
				float value=infoBo.getAssets().getOriginalValue();

				infoBo.getExtAttr().setCurrentValue(String.format("%.2f",cptShowValue(pDate,limit,value)));
			}catch(RuntimeException ex){
				//If Err then set value = 0 ;
				infoBo.getExtAttr().setCurrentValue("0");
			}
			
		}			
		return pageBo;
	}	
	
	/**
	 * 计算效益金额
	 */
	public static float cptValueMoney(float orangeValue, float usedYears,int limitYears){
		
		float value;
		
		/*
		 *	先判断资产属于超期资产或未超期资产 
		 */
		
		if(usedYears>limitYears){
			/*
			 * 属于超期资产
			 */
			value=-ComputeConstent.OUT_VALUE_RATE*(orangeValue/(limitYears*12))*((limitYears-usedYears)*12);
		}else{
			/*
			 * 属于未超期资产
			 */
			value=ComputeConstent.VALUE_RATE*(orangeValue/(limitYears*12))*((usedYears-limitYears)*12);
		}
		return value;		
	}

}
