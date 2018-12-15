package sennet.java.multi_thread;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class ThreadExitDemo {
	static class MyTask extends Thread {
		private volatile boolean closed = false;

		@Override
		public void run() {
			System.out.println("I will start work");
			while (!closed && !isInterrupted()) {
			}
			System.out.println("I will be exiting.");
		}

		public void close() {
			this.closed = true;
			this.interrupt();
		}
	}

	@Test
	public void test_flagExit() throws InterruptedException {
		MyTask t = new MyTask();
		t.start();
		TimeUnit.SECONDS.sleep(5);
		System.out.println("System will be shutdown");
		t.close();
	}

	@Test
	public void test_interruptedExit() throws InterruptedException {
		Thread t = new Thread() {
			@Override
			public void run() {
				System.out.println("I will start work");
				while (true) {
					try {
						TimeUnit.MILLISECONDS.sleep(1);
					} catch (InterruptedException e) {
						break;
					}
				}
				System.out.println("I will be exiting.");
			}
		};
		t.start();
		TimeUnit.SECONDS.sleep(10);
		System.out.println("System will be shutdown.");
		t.interrupt();
	}
}
