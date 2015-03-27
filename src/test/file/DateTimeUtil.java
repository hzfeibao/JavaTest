package test.file;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateTimeUtil {
	
	public static String getDateStr(){
		return getDateStr(new Date(), "yyyy-MM-dd");
	}
	
	public static String getDateStr(String format){
		return getDateStr(new Date(), format);
	}
	
	public static String getDateStr(Date date, String format){
		SimpleDateFormat dateFormate = new SimpleDateFormat(format);
		return dateFormate.format(date);
	}
	
	public static String getDateStr(String format, String timeZone){
		Date date = new Date();
		SimpleDateFormat dateFormate = new SimpleDateFormat(format);
		TimeZone timezone=TimeZone.getTimeZone(timeZone);//utc时区
		dateFormate.setTimeZone(timezone);//设置为utc时区
		return dateFormate.format(date);
	}
	
	public static String getDateTimeStr(String format){
		return getDateStr(new Date(), format);
	}
	
	public static String getDateTimeStr(){
		return getDateStr(new Date(), "yyyy-MM-dd HH:mm:ss");
	}
	
	public static String getDateTimeStr(String format, String timeZone){
		Date date = new Date();
		SimpleDateFormat dateFormate = new SimpleDateFormat(format);
		TimeZone timezone=TimeZone.getTimeZone(timeZone);
		dateFormate.setTimeZone(timezone);
		return dateFormate.format(date);
	}
}
