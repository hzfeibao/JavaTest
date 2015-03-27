package test.thread;

import java.util.PriorityQueue;
import java.util.concurrent.ArrayBlockingQueue;

public class TestBlockingQueue {
	private int queueSize = 10;
	private PriorityQueue<Integer> queue = new PriorityQueue<Integer>(queueSize);
	private ArrayBlockingQueue<Integer> queueBq = new ArrayBlockingQueue<Integer>(10);
	
	public static void main(String[] args){
		TestBlockingQueue test = new TestBlockingQueue();
		
		//test with nonblocking queue
//		Producer producer = test.new Producer();
//		Consumer consumer = test.new Consumer();
//		producer.start();
//		consumer.start();
		
		//test with blocking queue
		ProducerBq producerBq = test.new ProducerBq();
		ConsumerBq consumerBq = test.new ConsumerBq();
		producerBq.start();
		consumerBq.start();
	}
	
	class Consumer extends Thread{
		
		public void run(){
			consume();
		}
		
		private void consume(){
			while(true){
				synchronized(queue){
					while(queue.size() == 0){
						System.out.println("queue is empty, please wait");
						try {
							queue.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							queue.notify();
						}
					}
					queue.poll();
					queue.notify();
					System.out.println("consume 1, " + queue.size() + " left");
				}
			}
		}
	}
	
	class Producer extends Thread{
		public void run(){
			produce();
		}
		
		private void produce(){
			while(true){
				synchronized(queue){
					while(queue.size() == queueSize){
						System.out.println("queue is full, please wait");
						try {
							queue.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							queue.notify();
						}
					}
					queue.offer(1);
					queue.notify();
					System.out.println("produce 1, " + queue.size() + " left");
				}
			}
		}
	}
	
	class ConsumerBq extends Thread{
		
		public void run(){
			consume();
		}
		
		private void consume(){
			while(true){
				try {
					Integer i = queueBq.take();
					System.out.println("consume 1, " + queueBq.size() + " left");//???
					System.out.println("consume 1:" + i + ", " + queueBq.size() + " left");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	class ProducerBq extends Thread{
		public void run(){
			produce();
		}
		
		private void produce(){
			while(true){
				try {
					queueBq.put(1);
					System.out.println("produce 1, " + queueBq.size() + " left");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
