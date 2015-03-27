package test.thread;

public class TestThreadLocal {
	private ThreadLocal<Long> longLocal = new ThreadLocal<Long>();
	private ThreadLocal<String> stringLocal = new ThreadLocal<String>();
	
	public void set() {
		longLocal.set(Thread.currentThread().getId());
		stringLocal.set(Thread.currentThread().getName());
	}

	public long getLong() {
		return longLocal.get();
	}

	public String getString() {
		return stringLocal.get();
	}
	
	public static void main(String[] args) throws InterruptedException{
		final TestThreadLocal test = new TestThreadLocal();
		test.set();
		System.out.println("long: " + test.getLong() + "   string: " + test.getString());
		
		Thread thread1 = new Thread(){
			public void run(){
				test.set();
				System.out.println("long: " + test.getLong() + "   string: " + test.getString());
			}
		};
		
		thread1.start();
		thread1.join();
		
		test.set();
		System.out.println("long: " + test.getLong() + "   string: " + test.getString());
	}
}
