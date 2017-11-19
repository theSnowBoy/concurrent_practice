package wang.demos;

import java.util.Timer;
import java.util.TimerTask;

/*
 * 【题目】实现延迟执行任务的功能。
 * 【分析及解答】
 * 使用Timer进行相应的实现。
 * PS:需要进一步探究Timer内部的实现机制。
 */
public class TimerDemo {

	public static void main(String[] args) throws InterruptedException {
		Timer timer = new Timer(false);
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("time is up !");
			}
		}, 2000);
		System.out.println("main thread");
		
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("new task coming!");
			}
		}, 3000);
		Thread.sleep(3000);//此处时间设为1000，导致后面timer没来得及执行任务就退出了。设为> 3000,则两个任务都能执行完毕。
		timer.cancel();//需要中断timer线程，否则其会一直运行着，检测着其任务队列。
	}
}
