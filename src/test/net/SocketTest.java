package test.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketTest {
	
	public static void talkServer(){
		try{
			ServerSocket server = new ServerSocket(10086);
			Socket socket = server.accept();
			String line = null;
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter writer = new PrintWriter(socket.getOutputStream());
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("client: " + reader.readLine());
			line = in.readLine();
			while(!"exit".equals(line)){
				writer.println(line);
				writer.flush();
				System.out.println("one: " + line);
//				line = reader.readLine();
				System.out.println("another: " + reader.readLine());
				line = in.readLine();
			}
			writer.close();
			reader.close();
			socket.close();
			server.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		talkServer();
	}
}

class TalkClient1{
	public static void talkClient(){
		try {
			Socket socket = new Socket("127.0.0.1", 10086);
			String line = null;
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			PrintWriter writer = new PrintWriter(socket.getOutputStream());
			BufferedReader in = new BufferedReader(new InputStreamReader(
					System.in));
			System.out.println("client: " + reader.readLine());
			line = in.readLine();
			while (!"exit".equals(line)) {
				writer.println(line);
				writer.flush();
				System.out.println("one: " + line);
				System.out.println("another: " + reader.readLine());
				line = in.readLine();
			}
			writer.close();
			reader.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	public static void main(String[] args){
//		talkClient();
//	}
}
