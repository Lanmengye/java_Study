package sennet.java.multi_thread.ticketWindow;

/**
 * 多窗口售票实例 Runnable接口实现方式 使用synchronized关键字实现数据同步
 * 
 * @author Lanme
 *
 */
public class TicketWindowRunnable_synchronized implements Runnable {	
	public static void main(String[] args) {
		final TicketWindowRunnable_synchronized task = new TicketWindowRunnable_synchronized();
		new Thread(task, "一号出号机").start();
		new Thread(task, "二号出号机").start();
		new Thread(task, "三号出号机").start();
		new Thread(task, "四号出号机").start();
	}
	
	private int index = 1;
	private final  int MAX = 200;
	
	@Override
	public void run() {
		while (true) {
			try {//为了测试线程切换
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			synchronized (this) {
				if (index <= MAX) {
					System.out.println(Thread.currentThread().getName() + " 的号码是： " + (index++));
				}
				else {
					break;
				}
			}
		}
	}

}
