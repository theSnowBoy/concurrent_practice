package wang.demos;

import javax.swing.plaf.SliderUI;

/*
 * ����ĿҪ��������T1��T2��T3�����̣߳���������֤T2��T1ִ�����ִ�У�T3��T2ִ�����ִ�У�
 * 
 * �����졿 join�÷�
 * t.join()�����������ô˷������߳�(calling thread)��ֱ���߳�t��ɣ����߳��ټ�����
 * ͨ��������main()���߳��ڣ��ȴ������߳�����ٽ���main()���̡߳�
 */
public class ArrangeThreeThreadOrders {
	public static void main(String[] args) throws InterruptedException {
		method1();
		method2();
	}

	// ����1
	public static void method1() throws InterruptedException {
		System.out.println("method1 :");
		Thread T1 = new Thread(new MyThread("T1", null));
		Thread T2 = new Thread(new MyThread("T2", T1));
		Thread T3 = new Thread(new MyThread("T3", T2));
		T3.start();
		T2.start();
		T1.start();
		T3.join(); //Ϊ�˱������Main�̺߳�һ������method2�С�
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
					pre.join();// ��ǰ�߳�����������ȴ�pre�߳�ִ����ϡ�
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(name + " �� is running ~");
		}
	}
}
