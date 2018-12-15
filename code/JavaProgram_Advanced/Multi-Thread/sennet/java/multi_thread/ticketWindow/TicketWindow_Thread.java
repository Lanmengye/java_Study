package sennet.java.multi_thread.ticketWindow;

/**
 * 通过继承Thread类，重写run方法实现多窗口售票实例（未考虑数据同步问题）
 * 
 * @author Lanme
 *
 */
public class TicketWindow_Thread extends Thread {

	public static void main(String[] args) {
		new TicketWindow_Thread("一号出号机").start();
		new TicketWindow_Thread("二号出号机").start();
		new TicketWindow_Thread("三号出号机").start();
		new TicketWindow_Thread("四号出号机").start();
	}

	private final String name;
	private static final int MAX = 50;
	private static int index = 1;

	public TicketWindow_Thread(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		while (index <= MAX) {
			System.out.println("柜台：" + name + "当前号码是：" + (index++));
		}
	}

}
