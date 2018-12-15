package senney.java.multi_thread.communication;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class EventQueue {
	static class Event {
	}

	private final int max;
	private final static int DEFAULT_MAX_EVENT = 10;
	private final LinkedList<Event> eventQueue = new LinkedList<>();

	public EventQueue() {
		this(DEFAULT_MAX_EVENT);
	}

	public EventQueue(int max) {
		this.max = max;
	}

	final Lock lock = new ReentrantLock();
	final Condition isFull = lock.newCondition();
	final Condition isEmpty = lock.newCondition();

	public void offer(Event event) {
		lock.lock();
		try {
			while(eventQueue.size() >= max) {
				printMessage(" the queue is full.");
				isFull.await();
			}
			eventQueue.addLast(event);
			isEmpty.signalAll();
			printMessage(" the new event is submitted.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public Event take() {
		lock.lock();
		Event event = null;
		try {
			while(eventQueue.isEmpty()) {
				printMessage(" the queue is empty");
				isEmpty.await();
			}
			event = eventQueue.removeFirst();
			isFull.signalAll();
			printMessage(" the event " + event + " is handled.");

		} catch (InterruptedException e) {
		} finally {
			lock.unlock();
		}
		return event;
	}

	private void printMessage(String message) {
		System.out.printf("%s: %s\n", Thread.currentThread().getName(), message);
	}
}
