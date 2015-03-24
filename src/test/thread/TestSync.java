package test.thread;


//refer http://blog.csdn.net/ghsau/article/details/7424694

public class TestSync {
	public static void main(String[] args) {  
        final Outputter output = new Outputter();  
//        new Thread() {  
//            public void run() {  
//                output.output("zhangsan");  
//            };  
//        }.start();        
//        new Thread() {  
//            public void run() {  
//                output.output("lisi");  
//            };  
//        }.start();
        
        final Test t = new Test();
        for(int i = 0; i < 10; i++){
        	final int index = i;
        	new Thread(){
        		public void run(){
        			if(index%2 == 0){
        				t.two();
        			}else{
        				t.one();
        			}
        		}
        	}.start();
        }
        
        final Test1 t1 = new Test1();
        for(int i = 0; i < 10; i++){
        	final int index = i;
        	new Thread(){
        		public void run(){
        			if(index%2 == 0){
        				t1.two();
        			}else{
        				t1.one();
        			}
        		}
        	}.start();
        }
        
        final Test2 t2 = new Test2();
        for(int i = 0; i < 10; i++){
        	final int index = i;
        	new Thread(){
        		public void run(){
        			if(index%2 == 0){
        				t2.two();
        			}else{
        				t2.one();
        			}
        		}
        	}.start();
        }
    }
	
	class temp{
		
	}
}


/**
 * 一个线程执行互斥代码过程如下：
        1. 获得同步锁；
        2. 清空工作内存；
        3. 从主内存拷贝对象副本到工作内存；
        4. 执行代码(计算或者输出等)；
        5. 刷新主内存数据；
        6. 释放同步锁。
 */

class Outputter {
	//将synchronized加在需要互斥的方法上,用this锁住整个方法内的代码块
	//public synchronized void output(String name){...}
	//如果用synchronized加在静态方法上，就相当于用××××.class锁住整个方法内的代码块
    public void output(String name) {  
        // TODO 为了保证对name的输出不是一个原子操作，这里逐个输出name的每个字符
    	synchronized(this){//////使用synchronized将需要互斥的代码包含起来，并上一把锁
    		//锁必须是需要互斥的多个线程间的共享对象，像下面的代码是没有意义的
    		//Object lock = new Object();  synchronized (lock) 
			for (int i = 0; i < name.length(); i++) {
				System.out.print(name.charAt(i));
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
    	}
    }  
}

class Test{  
    static int i = 0, j = 0;  
    static void one() {  
        try {
        	i++;
			Thread.sleep(100);
			j++;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }  
    static void two() {  
        System.out.println("i=" + i + " j=" + j);  
        try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }  
}

class Test1 {  
    static int i = 0, j = 0;  
    static synchronized void one() {  
        try {
        	i++;
			Thread.sleep(100);
			j++;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }  
    static synchronized void two() {  
        System.out.println("1 i=" + i + " j=" + j);  
        try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }  
}

class Test2{  
    static volatile int i = 0, j = 0;  
    static void one() {  
        i++;  
        j++;  
    }  
    static void two() {  
        System.out.println("2 i=" + i + " j=" + j);  
    }  
} 