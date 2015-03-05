package test.http;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import test.DateTest;

public class SimpleTest41 {
	public static void getImage(String url){
		HttpPost post = new HttpPost(url);
		HttpGet get = new HttpGet(url);
		DefaultHttpClient client = new DefaultHttpClient();
		try {
			HttpResponse response = client.execute(get);
			HttpEntity entity = response.getEntity();
//			EntityUtils.consume(entity);
			System.out.println(EntityUtils.getContentCharSet(entity) + "     " + EntityUtils.getContentMimeType(entity));
			InputStream is = entity.getContent();
			String localFileName = "link_tmpf_" + DateTest.getTimeAsStr("yyyyMMddHHmmss") + "_" + 0;
			String filepath = "/home/zeng_weifeng/Downloads/tmp/vtlive/" + localFileName;
			File localFile = new File(filepath);
			OutputStream os = new FileOutputStream(localFile);
			int i;
			byte[] buf = new byte[10240];
			while((i = is.read(buf)) != -1){
				os.write(buf,0,i);
				System.out.print(buf);
			}
			is.close();
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			client.getConnectionManager().shutdown();
		}
	}
	
	public static void testPost(){
		
	}
	
	public static void main(String[] args){
		String url = "http://192.168.1.13:8888/GetImage/0112aedbe0b931bdcc299c45b1b7f2fcd057";
		String url2 = "http://192.168.1.15/livedata/20150113/13/link_tmpf_20150113043649_1472504.jpeg";
		getImage(url);
	}
}
