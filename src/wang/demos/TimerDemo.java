package wang.demos;

import java.util.Timer;
import java.util.TimerTask;

/*
 * ����Ŀ��ʵ���ӳ�ִ������Ĺ��ܡ�
 * �����������
 * ʹ��Timer������Ӧ��ʵ�֡�
 * PS:��Ҫ��һ��̽��Timer�ڲ���ʵ�ֻ��ơ�
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
		Thread.sleep(3000);//�˴�ʱ����Ϊ1000�����º���timerû���ü�ִ��������˳��ˡ���Ϊ> 3000,������������ִ����ϡ�
		timer.cancel();//��Ҫ�ж�timer�̣߳��������һֱ�����ţ��������������С�
	}
}
