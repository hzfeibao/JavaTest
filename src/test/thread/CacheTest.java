package test.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class CacheTest {
	public static void main(String[] args){
		ExecutorService pool = Executors.newCachedThreadPool();
		List<Thread> threadList = new ArrayList<Thread>();
		for(int i = 0; i < 20; i++){
			SimpleThread2 thread = new SimpleThread2();
			thread.setUsername("user" + i);
			thread.setMsg("msg" + i);
			threadList.add(thread);
		}
		for(int i = 0; i < 20; i++){
			pool.execute(threadList.get(i));
		}
		pool.shutdown();
	}
}

class SimpleThread2 extends Thread{
	private String username;
	private String msg;
	
	public void run(){
//		System.out.println(Thread.currentThread().getName() + " running...");
		System.out.println(Thread.currentThread().getName() + "   name: " + username + "   msg: " + msg);
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}