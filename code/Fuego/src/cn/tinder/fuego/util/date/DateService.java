package cn.tinder.fuego.util.date;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.service.exception.msg.ExceptionMsg;

public class DateService {
	private static final Log log = LogFactory
	.getLog(DateService.class);

		public static Date addYear(Date date,int year){
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.YEAR,year);
			Date newDate=cal.getTime();
			return newDate;
			
		}
	

	public static String DateToString(Date date){
		String str = null;
		DateFormat d = DateFormat.getDateInstance(DateFormat.MEDIUM);
		
		if(null == date)
		{	
			return "";
		}
		try
		{
			str=d.format(date);
 		}
		catch(Exception e)
		{
			str="";
			//log.warn("the date formart is wrong."+date);
		}
		return str;
		
	}
	
	public static String DateToLongString(Date date){
		String str = null;
		DateFormat d = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM);
		str=d.format(date);
		return str;
		
	}
	
	public static Date stringToDate(String date) {
		DateFormat d = DateFormat.getDateInstance(DateFormat.MEDIUM);
		
		Date da;
		if(null==date || date.trim().isEmpty()){
			return null;
			
		}
		
		
		try {
			da = d.parse(date);
		} catch (Exception e) {
			log.error("Err: Date Str is:"+date);
			throw new ServiceException(ExceptionMsg.DATE_FOMATE);
		
		}
		return da;
		
	}


	public static Date addYear(String purchaseDate, int expectYear) {
	
		return addYear(stringToDate(purchaseDate),expectYear);
	}
	

	
	
}
