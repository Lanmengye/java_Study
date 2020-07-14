package senney.java.multi_thread;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.junit.Test;

/**
 * 常用线程API使用示例
 * 
 * @author Lanme
 *
 */
public class ThreadAPIDemo {
	/**
	 * sleep(): 使线程睡眠指定时间
	 */
	@Test
	public void test_sleep() {
		new Thread(() -> {
			long startTime = System.currentTimeMillis();
			try {
				Thread.sleep(2_000L);
			} catch (InterruptedException e) {
			}
			long endTime = System.currentTimeMillis();
			System.out.println(String.format("Total spend %d ms", endTime - startTime));
		}).start();

		new Thread(() -> {
			long startTime = System.currentTimeMillis();
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
			}
			long endTime = System.currentTimeMillis();
			System.out.println(String.format("Total spend %d ms", endTime - startTime));
		}).start();

		long startTime = System.currentTimeMillis();
		try {
			Thread.sleep(3_000L);
		} catch (InterruptedException e) {
		}
		long endTime = System.currentTimeMillis();
		System.out.println(String.format("Total spend %d ms", endTime - startTime));
	}

	/**
	 * yield(): 提醒线程自愿放弃资源
	 */
	@Test
	public void test_yield() {
		IntStream.range(0, 2).mapToObj(ThreadAPIDemo::create).forEach(Thread::start);
	}

	private static Thread create(int index) {
		return new Thread(() -> {
			if (index == 0)
				Thread.yield();
			System.out.println(index);
		});
	}

	/**
	 * currentThread(): 获取当前线程实例
	 */
	@Test
	public void test_currentThread() {
		Thread thread = new Thread() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread() == this);
			}
		};
		thread.start();
		String name = Thread.currentThread().getName();
		System.out.println(name);
	}

	/**
	 * setName(name): 修改线程名字
	 */
	@Test
	public void test_setName() {
		Thread t1 = new Thread();
		System.out.println(t1.getName());

		Thread t2 = new Thread("MyThread-2");
		System.out.println(t2.getName());

		Thread t3 = new Thread();
		t3.setName("MyThread-3");
		System.out.println(t3.getName());

		Thread t4 = new Thread();
		t4.start();
		t3.setName("MyThread-4");
		System.out.println(t3.getName());
	}

	/**
	 * getThreadGroup(): 获取线程所在的线程组
	 */
	@Test
	public void test_threadGroup() {
		Thread t1 = new Thread("t1");
		ThreadGroup group = new ThreadGroup("TestGroup");
		Thread t2 = new Thread(group, "t2");

		ThreadGroup mainThreadGroup = Thread.currentThread().getThreadGroup();

		System.out.println("Main thread belong group:" + mainThreadGroup.getName());
		System.out.println("t1 and main belong the same group: " + (mainThreadGroup == t1.getThreadGroup()));
		System.out.println("t2 thread group belong main group: " + (mainThreadGroup == t2.getThreadGroup()));
		System.out.println("t2 thread group belong main TestGroup: " + (group == t2.getThreadGroup()));
	}

	/**
	 * join(): 使多个线程串行化执行
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void test_join() throws InterruptedException {
		List<Thread> threads = IntStream.range(1, 3).mapToObj(ThreadAPIDemo::createThread).collect(toList());
		threads.forEach(Thread::start);
		for (Thread thread : threads) {
			thread.join();
		}

		for (int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName() + "#" + i);
			shortSleep();
		}
	}

	private static Thread createThread(int seq) {
		return new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				System.out.println(Thread.currentThread().getName() + "#" + i);
				shortSleep();
			}
		}, "MyThread-" + seq);
	}

	private static void shortSleep() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * setPriority(): 设置线程优先级
	 */
	@Test
	public void test_priority() {
		Thread t1 = new Thread(() -> {
			while (true) {
				System.out.println("t1");
			}
		});
		t1.setPriority(3);

		Thread t2 = new Thread(() -> {
			while (true) {
				System.out.println("t2");
			}
		});
		t2.setPriority(10);

		t1.start();
		t2.start();
	}

	/**
	 * interrupt(): 中断线程
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void test_interrupt() throws InterruptedException {
		Thread thread = new Thread(() -> {
			try {
				TimeUnit.MINUTES.sleep(1);
			} catch (InterruptedException e) {
				System.out.println("A InterruptedException occured.");
			}
		});
		thread.start();
		TimeUnit.MILLISECONDS.sleep(2);
		thread.interrupt();

		Thread t = new Thread();
		t.start();
		t.interrupt();
		System.out.println("线程是否被打断了：" + t.isInterrupted());
	}

	/**
	 * isInterrupted(): 检测线程是否被打断过
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void test_isInterrupted() throws InterruptedException {
		Thread thread = new Thread() {
			@Override
			public void run() {
				while (true) {
				}
			}
		};
		thread.start();
		TimeUnit.MILLISECONDS.sleep(2);
		System.out.printf("Thread is interrupted? %s\n", thread.isInterrupted());
		thread.interrupt();
		System.out.printf("Thread is interrupted? %s\n", thread.isInterrupted());

	}

	/**
	 * interrupted(): 检测线程是否被打断过，如果打断过(true)，则清除打断标记(false)
	 * @throws InterruptedException
	 */
	@Test
	public void test_Interrupted() throws InterruptedException {
		Thread thread = new Thread() {
			@Override
			public void run() {
				while (true) {
					System.out.println(Thread.interrupted());
				}
			}
		};
		thread.setDaemon(true);
		thread.start();
		thread.interrupt();
		TimeUnit.MILLISECONDS.sleep(1);
	}

	/**
	 * setDaemon(true): 将线程设置为守护线程
	 * @throws InterruptedException
	 */
	@Test
	public void test_daemonThread()throws InterruptedException{
		Thread thread = new Thread(() ->{
			while (true) {
				try {
					Thread.sleep(1);
					TimeUnit.SECONDS.sleep(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}) ;
		thread.setDaemon(true);
		thread.start();
		Thread.sleep(2_000L);
		System.out.println("Main thread finished lifecycle.");
	}
}
