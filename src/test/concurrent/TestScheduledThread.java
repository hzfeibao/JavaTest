package test.concurrent;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

public class TestScheduledThread {
	
	public static void main(String[] args){
		final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
		final Runnable beeper = new Runnable(){
			int count = 0;
			public void run(){
				System.out.println(new Date() + " beep " + (++count));
			}
		};
		final ScheduledFuture beeperHandle = scheduler.scheduleAtFixedRate(beeper, 1, 2, SECONDS);
		final ScheduledFuture beeperHandle2 = scheduler.scheduleAtFixedRate(beeper, 2, 5, SECONDS);
		scheduler.schedule(new Runnable(){
			public void run(){
				beeperHandle.cancel(true);
				beeperHandle2.cancel(true);
				scheduler.shutdown();
			}
		}, 30, SECONDS);
		
	}
}
