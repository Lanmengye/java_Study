package senney.java.multi_thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {
	public static void main(String[] args) {
		ExecutorService es = Executors.newFixedThreadPool(5);
		ThreadPoolRunnable task = new ThreadPoolRunnable();
		for (int i = 0; i < 10; i++) {
			es.submit(task);
		}
		es.shutdown();
	}

	public static class ThreadPoolRunnable implements Runnable {
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + "线程提交任务……");
		}
	}
}
