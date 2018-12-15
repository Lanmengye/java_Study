package sennet.java.multi_thread.ticketWindow;

/**
 * 通过实现Runnable接口并将其作为参数传递给Thread构造函数实现多窗口售票实例（未考虑数据同步问题）
 * 
 * @author Lanme
 *
 */
public class TicketWindow_Runnable implements Runnable {

	public static void main(String[] args) {
		final TicketWindow_Runnable task = new TicketWindow_Runnable();
		new Thread(task, "一号出号机").start();
		new Thread(task, "二号出号机").start();
		new Thread(task, "三号出号机").start();
		new Thread(task, "四号出号机").start();

	}

	private int index = 1;
	private final static int MAX = 50;

	@Override
	public void run() {
		while (index <= MAX) {
			System.out.println(Thread.currentThread().getName() + " 的号码是： " + (index++));
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
