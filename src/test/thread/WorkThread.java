package test.thread;

public class WorkThread implements Runnable{

	Object lock = new Object();
	Runnable runner = null;
	ThreadPool pool = null;
	
	public WorkThread(ThreadPool pool)
	{
		this.pool = pool;
	}

	public void start(Runnable r)
	{
		runner = r;
		synchronized (lock)
		{
			lock.notify();
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			if(runner != null){
				runner.run();
				runner = null;
			}if (pool.putWorkThread(this))
			{
				System.out.println(Thread.currentThread().getName() + " 被回收!");
				synchronized (lock)
				{
					try
					{
						lock.wait();
					}
					catch (InterruptedException ie)
					{
						System.out.println("停止线程时出现异常");
					}
				}
			}else{
				System.out.println(Thread.currentThread().getName() + " 被丢弃!");
				break;
			}
		}
	}
	
}
