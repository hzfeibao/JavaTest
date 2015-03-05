package test.string;

import java.util.Random;

import org.apache.commons.lang.StringUtils;

public class StringTest {
	private static char[] letters =("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
	private static Random randGen = new Random();  
	String str = "";
	public static String getRandomString(Integer len){
		String result = null;
		
		if(len < 1)
			return result;
		char[] buf = new char[len];
		for(int i = 0; i < len; i++){
			buf[i] = letters[randGen.nextInt(letters.length-1)%letters.length];
		}
		
		result = new String(buf);
		return result;
	}
	
	public static void printChar(Character c, Integer len){
		for(int i = 0; i < len; i++){
			System.out.print((char)(c+len));
		}
	}
	
	public static void testIndex(){
		String s1 = "java.lang.Exception: can't get webdriver from pool! driver pool is full!, see in log.";
		String s2 = "driver pool is fulll";
		System.out.println(s1.indexOf(s2));
	}
	
	public static void testNull(){
		String s1 = null;
		String s2 = "";
		String s3 = "   ";
		System.out.println(StringUtils.isEmpty(s1) + "   " + StringUtils.isBlank(s1));
		System.out.println(StringUtils.isEmpty(s2) + "   " + StringUtils.isBlank(s2));
		System.out.println(StringUtils.isEmpty(s3) + "   " + StringUtils.isBlank(s3));
	}
	
	public static void main(String[] args){
//		String s = getRandomString(4);
//		System.out.println(s);
//		testIndex();
		testNull();
	}
}
