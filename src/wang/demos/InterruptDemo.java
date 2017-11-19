package wang.demos;

/*
 * ����Ŀ��ȡ���������е��̡߳�
 * �����������
 * һ���̲߳�ͣ��ִ�����������
 * ��һ���߳�����������У���ֹ���̡߳�
 * PS�����interrupt��־λΪtrue����ô ���� wait,sleep,join�ȷ���ʱ�����̻߳��׳��쳣��
 */
public class InterruptDemo {

	public static void main(String[] args) throws InterruptedException {
		Thread T1 = new Thread(new WorkingThread());
		T1.start();
		Thread.sleep(500);
		T1.interrupt();
	}
	public static class WorkingThread implements Runnable{

		@Override
		public void run() {
			while(true){
				System.out.println("hello");
				try {
					Thread.sleep(100); //��sleep�����У����interrupt��־λ���м�⡣
				} catch (InterruptedException e) {
					e.printStackTrace();
					break;
				}
			}
		}
	}
}
