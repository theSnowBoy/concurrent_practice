package wang.demos;

import java.util.LinkedList;
import java.util.List;

/*
 * ����Ŀ��ʹ��volatileЭ�������̡߳��߳�1����������֮������volatile����֪ͨ�߳�2.
 * һ���߳��������ݣ���������󣬸���ͬ��״̬��֪ͨ��һ���߳̽��д���
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
			System.out.println("�ܺ�Ϊ��" + sum);
		}
	}
}
