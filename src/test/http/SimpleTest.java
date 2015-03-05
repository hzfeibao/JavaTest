package test.http;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import test.DateTest;

//3.1
public class SimpleTest {
	public static void getImage(String url) throws Exception{
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(url);
		
		client.executeMethod(method); 
		InputStream is = method.getResponseBodyAsStream();
		String localFileName = "link_tmpf_" + DateTest.getTimeAsStr("yyyyMMddhhmmss") + "_" + 0;
		String filepath = "/home/zeng_weifeng/Downloads/tmp/vtlive/" + localFileName;
		File localFile = new File(filepath);
		OutputStream os = new FileOutputStream(localFile);
		int i;
		byte[] buf = new byte[10240];
		while((i = is.read(buf)) != -1)
			os.write(buf);
		is.close();
		os.close();
		method.releaseConnection();
	}
	
	public static void downloadImage(String url) throws Exception{
		HttpClient client = new HttpClient();
		GetMethod get = new GetMethod(url);
		client.executeMethod(get);
		String localFileName = "link_tmpf_" + DateTest.getTimeAsStr("yyyyMMddHHmmss") + "_" + 1;
		String filepath = "/home/zeng_weifeng/Downloads/tmp/vtlive/" + localFileName;
		File localFile = new File(filepath);
		OutputStream os = new FileOutputStream(localFile);
		os.write(get.getResponseBody());
		os.close();
		get.releaseConnection();
	}
	
	public static void main(String[] args) throws Exception{
//		String url = "http://192.168.1.13:8888/GetImage/010847f4cf3aa362189bc31966d231c8344c";
//		downloadImage(url);
		getImage("http://tv.sohu.com/sports/cbalive/");
	}
}
