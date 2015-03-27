package test.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestVolatile {
	
	private volatile int inc = 0;
	
	private Lock lock= new ReentrantLock();//1
	
	private AtomicInteger incAto = new AtomicInteger(0);//3
	
	public void increase(){
		inc++;
	}
	
	public void increaseWithLock(){//1
		lock.lock();
		try{
			inc++;
		}finally{
			lock.unlock();
		}
	}
	
	public synchronized void increaseWithSynchronized(){//2
		inc++;
	}
	
	public void increaseWithAtomic(){
		incAto.getAndIncrement();
	}
	
	public static void main(String[] args){
		final TestVolatile test = new TestVolatile();
		for(int i = 0; i < 10; i++){
			new Thread(){
				public void run(){
					for(int j = 0; j < 1000; j++){
						test.increase();
//						test.increaseWithAtomic();
//						test.increaseWithLock();
//						test.increaseWithSynchronized();
					}
				}
			}.start();
		}
		while(Thread.activeCount()>1)//保证前面的线程都执行完
			Thread.yield();
		System.out.println(test.inc);
//		System.out.println(test.incAto.get());
	}
	
}
