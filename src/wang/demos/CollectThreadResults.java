package wang.demos;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

/*
 * ����Ŀ�� �����һ�γ��򲢷���ִ�У������ջ��ܽ����
 * �����߳���һ�����������з������֣���������������̴߳ӹ���������ȡ��������͡�
 * 
 * �����������
 * ���β��� �����߳� ������ܸ��̵߳ļ������������̸߳���������໥������
 * ���ԣ����߳�Ӧ�ü��������̵߳�ִ��״̬��
 * 
 * ����ʹ�õ�java �������ߣ�CyclicBarrier ��CountDownLatch
 */
public class CollectThreadResults {
	private static List<Integer> results = new LinkedList<>(); //�����洢�̵߳�ִ�н����
	private static final CountDownLatch latch = new CountDownLatch(2); // �����������߳��ʵ�ʱ��ʼ���̡� 

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
		System.out.println("���Ϊ �� " + sum);//���ӦΪ10.����ȷ��
	}
	public  static class MyThread implements Runnable{
		private int start;
		private int count;
		//��start ��ʼ������count�����֡�
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
