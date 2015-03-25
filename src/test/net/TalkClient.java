package test.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

//test java net socket

public class TalkClient{
	public static void talkClient(){
		try {
			Socket socket = new Socket("127.0.0.1", 10086);
			String line = null;
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			BufferedReader in = new BufferedReader(new InputStreamReader(
					System.in));
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
	
	public static void main(String[] args){
		talkClient();
	}
}
