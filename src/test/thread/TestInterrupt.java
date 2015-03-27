package test.thread;

public class TestInterrupt {
	
	public static void main(String[] args){
		TestInterrupt test = new TestInterrupt();
		MyThread thread = test.new MyThread();
		thread.start();
		try{
			Thread.currentThread().sleep(2*1000);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		thread.interrupt();
	}
	
	class MyThread extends Thread {
		@Override
		public void run() {
			try {
				System.out.println("进入睡眠状态");
				Thread.currentThread().sleep(10000);
				System.out.println("睡眠完毕");
			} catch (InterruptedException e) {
				System.out.println("得到中断异常");
			}
			System.out.println("run方法执行完毕");
		}
	}
}
