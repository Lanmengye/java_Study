package sennet.java.multi_thread.ticketWindow;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多窗口售票实例 
 * Runnable接口实现方式 
 * 使用lock锁实现数据同步
 * @author Lanme
 *
 */
public class TicketWindowRunnable_lock implements Runnable {

	public static void main(String[] args) {
		final TicketWindowRunnable_lock task = new TicketWindowRunnable_lock();
		new Thread(task, "一号出号机").start();
		new Thread(task, "二号出号机").start();
		new Thread(task, "三号出号机").start();
		new Thread(task, "四号出号机").start();
	}

	private int index = 1;
	private final int MAX = 200;
	private final Lock lock = new ReentrantLock(); // 锁对象

	@Override
	public void run() {

		while (true) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			lock.lock();
			try {
				if (index <= MAX) {
					System.out.println(Thread.currentThread().getName() + " 的号码是： " + (index++));
				} else {
					break;
				}
			} finally {
				lock.unlock();
			}
		}
	}
}
