package test;

import java.net.MalformedURLException;
import java.net.URL;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;

public class TestJsonRpc {

	public static void testJsonRpcClient() throws Throwable{
		String url = "http://127.0.0.1:8080/mdm/test";
		
		JsonRpcHttpClient client = new JsonRpcHttpClient(new URL(url));
		User user = new User();
		user.setUsername("hello");
		user.setPassword("world");
//		User user = client.invoke("getUser", new Object[]{"hello", "world"}, User.class);
//		client.invoke
		user = client.invoke("getUser", user, User.class);

		System.out.println("userId: " + user.getId() + "   username: " + user.getUsername() + " password: " + user.getPassword());
	}
	
	
	public static void main(String[] args) throws Throwable{
		testJsonRpcClient();
	}
}
