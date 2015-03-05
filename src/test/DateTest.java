package test;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.easytodo.keel.util.DateUtils;

public class DateTest {
	public static Integer dayNum(String startDate, String endDate){
		Integer num = 0;
		
		Date start = DateUtils.string2Date(startDate, "yyyy-MM-dd");
		Date end = DateUtils.string2Date(endDate, "yyyy-MM-dd");
		
		System.out.println("start: " + startDate + "   end:" + endDate);
		System.out.println("st: " + start.getTime() + "   end: " + end.getTime());
		long s = end.getTime() - start.getTime();
		int d = (int) (s/(24*60*60*1000));
		System.out.println("s: " + s + "   d:" + d);
		return num;
	}
	
	public static String getTimeAsStr(String format){
		return getTimeAsStr(new Date(), format);
	}
	
	public static String getTimeAsStr(Date date, String format){
		String result = null;
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		result = formatter.format(date);
		return result;
	}
	
//	public static void main(String[] args){
//		dayNum("2014-12-23", "2015-01-01");
//	}
}
