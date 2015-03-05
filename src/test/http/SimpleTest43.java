package test.http;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import test.DateTest;

public class SimpleTest43 {
	
	public static void downloadImage(String url){
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet get = new HttpGet(url);
		try{
			httpclient.execute(get);
			CloseableHttpResponse response = httpclient.execute(get);
			InputStream is = response.getEntity().getContent();
			String localFileName = "link_tmpf_" + DateTest.getTimeAsStr("yyyyMMddHHmmss") + "_" + 0;
			String filepath = "/home/zeng_weifeng/Downloads/tmp/vtlive/" + localFileName;
			File localFile = new File(filepath);
			OutputStream os = new FileOutputStream(localFile);
			int i;
			byte[] buf = new byte[10240];
			while((i = is.read(buf)) != -1){
				os.write(buf, 0, i);
				System.out.print(buf);
			}
			is.close();
			os.close();
			response.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				httpclient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception{
		String url = "http://192.168.1.13:8888/GetImage/0112aedbe0b931bdcc299c45b1b7f2fcd057";
		String url2 = "http://192.168.1.15/livedata/20150113/13/link_tmpf_20150113043649_1472504.jpeg";
		downloadImage(url);
//		testBasic();
	}
	
	public static void test(){
		
	}
	
	public final static void testBasic() throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpget = new HttpGet("http://www.apache.org/");

            System.out.println("Executing request " + httpget.getURI());
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
                // Do not feel like reading the response body
                // Call abort on the request object
                httpget.abort();
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }
}
