package senney.java.multi_thread.communication;

public class MessageClient {
	public static void main(String[] args) {
		Message msg = new Message();

		Thread producer = new Thread() {

			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					if ((i & 1) == 0) {
						msg.set("aaa", "111111");
					} else {
						msg.set("bbb", "222222");
					}
				}
			}
		};

		Thread consumer = new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					msg.get();
				}
			}
		};

		producer.start();
		consumer.start();
	}
}
