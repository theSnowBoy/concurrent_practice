package wang.demos;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * 【题目】构造一个出现死锁的场景。
 * 【分析及解答】
 * 1.构造满足死锁的4个必要条件。
 * 2.相互请求资源。
 * 
 * 注意：Thread1 与 Thread2对于 lock1,lock2加锁的顺序是相反的。
 */
public class DeadLock {

	//指定两个锁。
	public static Lock lock1 =  new ReentrantLock();
	public static Lock lock2 = new ReentrantLock();
	
	public static void main(String[] args) {
		Thread T1 = new Thread(new Thread1());
		Thread T2 = new Thread(new Thread2());
		T1.start();
		T2.start();
	}
	
	public static class Thread1 implements Runnable{
		@Override
		public void run() {
			try {
				lock1.lock();
				System.out.println("T1 : lock lock1");
				Thread.sleep(500);
				lock2.lock();
				System.out.println("T1 : lock lock2");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally{
				lock1.unlock();
				System.out.println("T1 : unlock lock1");
				lock2.unlock();
				System.out.println("T1 : unlock lock2");
			}
		}
		
	}
	
	public static class Thread2 implements Runnable{
		@Override
		public void run() {
			try {
				lock2.lock();
				System.out.println("T2 : lock lock2");
				Thread.sleep(500);
				lock1.lock();
				System.out.println("T2 : lock lock1");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally{
				lock2.unlock();
				System.out.println("T2 : unlock lock2");
				lock1.unlock();
				System.out.println("T2 : unlock lock1");
			}
		}
	}
}
