package test.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TestLock {
	private List<Integer> arrayList = new ArrayList<Integer>();
	private Lock lock = new ReentrantLock();//
	private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	
	public void insert(Thread thread){
//		Lock lock = new ReentrantLock();//
		lock.lock();
		try{
			System.out.println(thread.getName() + " get the lock");
			for(int i = 0; i< 5; i++){
				arrayList.add(i);
				Thread.sleep(2*1000);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			lock.unlock();
			System.out.println(thread.getName() + " release the lock");
		}
	}
	
	public void insertTryLock(Thread thread){
		if (lock.tryLock()) {
			try {
				System.out.println(thread.getName() + " get the lock");
				for (int i = 0; i < 5; i++) {
					arrayList.add(i);
					Thread.sleep(1 * 1000);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
				System.out.println(thread.getName() + " release the lock");
			}
		}else{
			System.out.println(thread.getName() + " don't get the lock");
		}
	}
	
	public void insertLockInterruptibly(Thread thread) throws InterruptedException{
		lock.lockInterruptibly();//注意，如果需要正确中断等待锁的线程，必须将获取锁放在外面，然后将InterruptedException抛出
		try{
			System.out.println(thread.getName() + " get the lock");
			long times = System.currentTimeMillis();
			for(;;){
				if(System.currentTimeMillis() - times > 10*1000)
					break;
				//do something
			}
		}finally{
			System.out.println(Thread.currentThread().getName() + " finally");
			lock.unlock();
			System.out.println(Thread.currentThread().getName() + " release lock");
		}
	}
	
	public synchronized void get(Thread thread){
		long start = System.currentTimeMillis();
		while(System.currentTimeMillis() - start <= 1000){
			if((System.currentTimeMillis() - start)%10 == 0)
				System.out.println(thread.getName() + " reading...");
		}
		System.out.println(thread.getName() + " read over");
	}
	
	public void getWithReadWriteLock(Thread thread){
		rwl.readLock().lock();
		try{
			long start = System.currentTimeMillis();
			while(System.currentTimeMillis() - start <= 1000){
				if((System.currentTimeMillis() - start)%10 == 0)
					System.out.println(thread.getName() + " reading...");
			}
			System.out.println(thread.getName() + " read over");
		}finally{
			rwl.readLock().unlock();
		}
	}
	
	public static void main(String[] args){
		final TestLock test = new TestLock();
		//testLock
//		new Thread(){
//			public void run(){
//				test.insert(Thread.currentThread());
//			}
//		}.start();
//		new Thread(){
//			public void run(){
//				test.insert(Thread.currentThread());
//			}
//		}.start();
		
		//testTryLock
//		new Thread(){
//			public void run(){
//				test.insertTryLock(Thread.currentThread());
//			}
//		}.start();
//		new Thread(){
//			public void run(){
//				test.insertTryLock(Thread.currentThread());
//			}
//		}.start();
		
		//testLockInterruptibly
//		MyThreadLock thread1 = new MyThreadLock(test);
//		MyThreadLock thread2 = new MyThreadLock(test);
//		thread1.start();
//		thread2.start();
//		try{
//			Thread.sleep(2*1000);
//		}catch(InterruptedException e){
//			e.printStackTrace();
//		}
//		thread2.interrupt();
		
		//testReadWithSynchronized
//		new Thread(){
//			public void run(){
//				test.get(Thread.currentThread());
//			}
//		}.start();
//		new Thread(){
//			public void run(){
//				test.get(Thread.currentThread());
//			}
//		}.start();
		
		//testReadWithReadWriteLock
//		new Thread(){
//			public void run(){
//				test.getWithReadWriteLock(Thread.currentThread());
//			}
//		}.start();
//		new Thread(){
//			public void run(){
//				test.getWithReadWriteLock(Thread.currentThread());
//			}
//		}.start();
	}
}

class MyThreadLock extends Thread {
    private TestLock test = null;
    public MyThreadLock(TestLock test) {
        this.test = test;
    }
    @Override
    public void run() {
         
        try {
            test.insertLockInterruptibly(Thread.currentThread());
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+" interrupted");
        }
    }
}
