package wang.demos;

/*
 * 【题目】取消正在运行的线程。
 * 【分析及解答】
 * 一个线程不停的执行输出的任务。
 * 另一个线程在这个过程中，终止该线程。
 * PS：如果interrupt标志位为true，那么 调用 wait,sleep,join等方法时，该线程会抛出异常。
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
					Thread.sleep(100); //在sleep方法中，会对interrupt标志位进行检测。
				} catch (InterruptedException e) {
					e.printStackTrace();
					break;
				}
			}
		}
	}
}
