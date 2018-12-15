package sennet.java.multi_thread;

import java.util.concurrent.TimeUnit;

/**
 * 多线程编程的一个简单例子：边看新闻边听音乐
 * @author Lanme
 *
 */
public class MultiThreadExample {

	public static void main(String[] args) {
		new Thread(MultiThreadExample::enjoyMusic).start();
		browseNews();
	}

	private static void browseNews() {
		while (true) {
			System.out.println("Uh-huh, the good news. ");
			sleep(1);
		}
	}

	private static void enjoyMusic() {
		while (true) {
			System.out.println("Uh-huh, the nice music. ");
			sleep(1);
		}
	}

	private static void sleep(int seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
