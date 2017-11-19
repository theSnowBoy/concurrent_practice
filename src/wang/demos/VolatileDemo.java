package wang.demos;

import java.util.LinkedList;
import java.util.List;

/*
 * 【题目】使用volatile协调两个线程。线程1处理完数据之后，利用volatile变量通知线程2.
 * 一个线程输入数据，输入结束后，更改同步状态，通知下一个线程进行处理。
 */
public class VolatileDemo {
	private static List<Integer> nums = new LinkedList<>();
	private volatile static boolean isPrepared = false;
	
	public static void main(String[] args) {
		Thread producer = new Thread(new R1());
		Thread consumer =  new Thread(new SumR());
		consumer.start();
		producer.start();
	}

	public static class R1 implements Runnable{

		@Override
		public void run() {
			for(int i = 2;i <=10;i++){
				nums.add(i);
			}
			isPrepared = true;
		}
	}
	
	public static class SumR implements Runnable{

		@Override
		public void run() {
			while(!isPrepared){
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			int sum = 0;
			for(int num : nums){
				sum += num;
			}
			System.out.println("总和为：" + sum);
		}
	}
}
