package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestMatch {
	public static void main(String[] args){
		test1();
		test2();
	}
	
	public static void test2(){
		//String st1 = "(.*fengyunzhibo.com*.)";
		String st1 = "(.*)";
		String st2 = "http://www.fengyunzhibo.com/tv/236_1375092311246.htm";
		Matcher m = Pattern.compile(st1).matcher(st2);
		if(m.find()){
			System.out.print("test2:"  + m.group(1));
		}
	}
	
	public static void test1() {  
        String regex = "\\w(\\d\\d)(\\w+)";  
        String candidate = "x99SuperJava";  
          
        Pattern p = Pattern.compile(regex);  
        Matcher matcher = p.matcher(candidate);  
        if(matcher.find()){  
            int gc = matcher.groupCount();  
            for(int i = 0; i <= gc; i++)  
                System.out.println("group " + i + " :" + matcher.group(i));  
        }  
    } 
}
