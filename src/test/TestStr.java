package test;

import java.net.URI;
import java.net.URISyntaxException;

public class TestStr {
	public static void main(String[] args){
//		test();
		int time = (int) (System.currentTimeMillis() / 1000);
		System.out.println("time: " + time);
		String url = "http://www.estrenosonline.org/paul-2/";
		String url2 = "http://baidu.com/search?keyword=hello";
//		testUrl(url);
//		testUrl(url2);
//		testTS();
		testInt();
	}
	
	public static void test(){
		String st1 = "1125460.jpg";
		int i = st1.indexOf(".");
		System.out.println(st1.substring(0, i));
	}
	
	public static void testUrl(String url){
		try {
			URI u = new URI(url);
			String domain  = u.getHost();
			System.out.println("domain:" + domain);
			
			Integer f = domain.indexOf(".");
			Integer l = domain.lastIndexOf(".");
			if(f != l)
				System.out.println(domain.substring(f+1));
			else System.out.println(domain);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void testTS(){
		Integer startTime = (int) (System.currentTimeMillis() / 1000)-30*24*60*60;
		System.out.println("startTime: " + startTime + "   now: " + (int) (System.currentTimeMillis() / 1000));
	}
	
	public static void testInt(){
		int COUNT_BITS = Integer.SIZE - 3;
		int CAPACITY   = (1 << COUNT_BITS) - 1;
		System.out.println("bits: " + COUNT_BITS + "   capacity: " + CAPACITY + "   max: " + Integer.MAX_VALUE);
		System.out.println("max/4: " + Integer.MAX_VALUE/4 + "   1<<2: " + (1<<2) + "   " + (2<<2));
		System.out.println("max/8: " + Integer.MAX_VALUE/8 + "   " + (1 << (Integer.MAX_VALUE/8)));
		System.out.println("1<<29: " + (1 << 29));
		
		for(int i = 0; i < 32; i++){
			System.out.println(i + ":   " +1 + " << 2 :" + (1 << i));
		}
	}
}
