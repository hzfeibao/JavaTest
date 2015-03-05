package test.date;

import java.util.Date;

public class DateTest {
	public static void testTimestamp(){
		Date date = new Date();
		System.out.println(date.getTime());
		Date d = new Date();
		System.out.println(d);
		long t = 1423584578000L;
		System.out.println(new Date(t));
	}
	
	public static void main(String[] args){
		testTimestamp();
	}
}
