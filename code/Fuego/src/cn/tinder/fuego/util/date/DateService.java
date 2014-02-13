package cn.tinder.fuego.util.date;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
			log.warn("the date formart is wrong."+date);
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
	
	/**
	 * get the first date of current month
	 * @return
	 */
	public static String getCurMonthFirstDate()
	{
		Calendar   cal_1=Calendar.getInstance();//获取当前日期 
        cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
	    Date date = cal_1.getTime();
	    return DateToString(date);
	}
	/**
	 * get the last date of current month
	 * @return
	 */
	public static String getCurMonthLastDate()
	{
		Calendar ca = Calendar.getInstance();    
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));  
	    Date date = ca.getTime();

        return DateToString(date);
	}
	
	public static Date getCurrentDate()
	{
		 
        return new Date(System.currentTimeMillis());
	}
	
	public static Timestamp getCurrentDateTime()
	{
		 
        return new Timestamp(System.currentTimeMillis());
	}
	public static String getCurrentDateTimeStr()
	{
		Date time = new Timestamp(System.currentTimeMillis());
		
        return time.toString();
	}
	
	public static int countDayNum(Date startDate,Date endDate)
	{
		if(null == startDate || null == endDate  )
		{
			return 0;
		}
		long milSec = startDate.getTime()-endDate.getTime();
		int dayNum = (int) (milSec/1000/3600/24);
		return dayNum;
       
	}
	
	
}
