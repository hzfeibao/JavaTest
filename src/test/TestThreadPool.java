package test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestThreadPool {
	
	public static void main(String[] args){
		BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
		ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 6, 1, TimeUnit.DAYS, queue);
		
		for(int i = 0; i < 20; i++){
			executor.execute(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					try{
						System.out.println("run at " + System.currentTimeMillis()/1000);
//						System.out.println(executor.getActiveCount());
						Thread.sleep(60*1000);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				
			});
		}
		
		executor.shutdown();
	}
	
	public static void testThreadPool(){
//		new ThreadPoolExecutor();
		// 构造一个线程池  
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 4, 3, TimeUnit.SECONDS, 
        	new ArrayBlockingQueue<Runnable>(3), new ThreadPoolExecutor.DiscardOldestPolicy());
        
        
	}
}

class TheadPoolRunner implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}
