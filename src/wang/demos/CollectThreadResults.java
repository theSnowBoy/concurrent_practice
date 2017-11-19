package wang.demos;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

/*
 * 【题目】 如何让一段程序并发的执行，并最终汇总结果？
 * 两个线程往一个公共集合中放入数字，放入结束后，则主线程从公共集合中取出数字求和。
 * 
 * 【分析及解答】
 * 分治策略 ：主线程 负责汇总各线程的计算结果，其他线程负责计算且相互独立。
 * 所以，主线程应该监视其他线程的执行状态。
 * 
 * 可以使用的java 并发工具：CyclicBarrier 和CountDownLatch
 */
public class CollectThreadResults {
	private static List<Integer> results = new LinkedList<>(); //用来存储线程的执行结果。
	private static final CountDownLatch latch = new CountDownLatch(2); // 用来控制主线程适当时候开始流程。 

	public static void main(String[] args) throws InterruptedException {
		Thread T1 = new Thread(new MyThread(1, 2));
		Thread T2 = new Thread(new MyThread(3,2));
		T1.start();
		T2.start();
		Thread.sleep(600);
		latch.await();
		int sum = 0;
		for(int result : results){
			sum += result;
		}
		System.out.println("结果为 ： " + sum);//结果应为10.（正确）
	}
	public  static class MyThread implements Runnable{
		private int start;
		private int count;
		//从start 开始，放入count个数字。
		public MyThread(int start,int count){
			this.start = start;
			this.count = count;
		}
		@Override
		public void run() {
			for(int i = 0; i < count;i++){
				synchronized (results) {
					results.add(start +i);
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
			latch.countDown();
		}
	}
}
