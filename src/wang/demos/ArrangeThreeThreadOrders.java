package wang.demos;

import javax.swing.plaf.SliderUI;

/*
 * 【题目要求】现在有T1、T2、T3三个线程，你怎样保证T2在T1执行完后执行，T3在T2执行完后执行？
 * 
 * 【考察】 join用法
 * t.join()方法阻塞调用此方法的线程(calling thread)，直到线程t完成，此线程再继续；
 * 通常用于在main()主线程内，等待其它线程完成再结束main()主线程。
 */
public class ArrangeThreeThreadOrders {
	public static void main(String[] args) throws InterruptedException {
		method1();
		method2();
	}

	// 方法1
	public static void method1() throws InterruptedException {
		System.out.println("method1 :");
		Thread T1 = new Thread(new MyThread("T1", null));
		Thread T2 = new Thread(new MyThread("T2", T1));
		Thread T3 = new Thread(new MyThread("T3", T2));
		T3.start();
		T2.start();
		T1.start();
		T3.join(); //为了避免进入Main线程后一个方法method2中。
	}
	
	public static void method2() throws InterruptedException{
		System.out.println("method 2:");
		Thread T1 = new Thread(new MyThread("T1", null));
		Thread T2 = new Thread(new MyThread("T2", null));
		Thread T3 = new Thread(new MyThread("T3", null));
		T1.start();
		T1.join();
		T2.start();
		T2.join();
		T3.start();
		T3.join();
	}

	public static class MyThread implements Runnable {
		private String name;
		private Thread pre;

		public MyThread(String name, Thread pre) {
			this.name = name;
			this.pre = pre;
		}

		@Override
		public void run() {
			if (pre != null) {
				try {
					pre.join();// 当前线程阻塞在这里，等待pre线程执行完毕。
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(name + " ： is running ~");
		}
	}
}
