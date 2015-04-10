package test.concurrent;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

//refer:http://ifeve.com/java7-concurrency-cookbook-4/

public class ForkJoinTest3 {
	
	public static void main(String[] args){
		ForkJoinPool pool = new ForkJoinPool();
		FolderProcessor jdk = new FolderProcessor("/home/zeng_weifeng/jdk1.6.0_37/src", "java");
		FolderProcessor app = new FolderProcessor("/home/zeng_weifeng/DevTool/SVN/appTracker", "java");
		FolderProcessor vtl = new FolderProcessor("/home/zeng_weifeng/DevTool/SVN/vtlive", "java");
		
		pool.execute(jdk);
		pool.execute(app);
		pool.execute(vtl);
		
		do {
			System.out.printf("******************************************\n");
			System.out.printf("Main: Parallelism: %d\n", pool.getParallelism());
			System.out.printf("Main: Active Threads: %d\n",
					pool.getActiveThreadCount());
			System.out.printf("Main: Task Count: %d\n",
					pool.getQueuedTaskCount());
			System.out.printf("Main: Steal Count: %d\n", pool.getStealCount());
			System.out.printf("******************************************\n");
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while ((!jdk.isDone()) || (!app.isDone()) || (!vtl.isDone()));
		pool.shutdown();
		
		List<String> results;
		results = jdk.join();
		System.out.printf("jdk: %d files found", results.size());
		results = app.join();
		System.out.printf("app: %d files found", results.size());
		results = vtl.join();
		System.out.printf("vtl: %d files found", results.size());
		
		
	}
}
