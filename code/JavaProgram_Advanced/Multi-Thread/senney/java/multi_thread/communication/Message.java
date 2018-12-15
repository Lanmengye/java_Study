package senney.java.multi_thread.communication;

public class Message {
	private String title;
	private String content;
	private boolean canProduce = true;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public synchronized void set(String title, String content) {
			if(!canProduce) {
				try {
					this.wait();
				} catch (InterruptedException e) {
				}
			}
			setTitle(title);
			setContent(content);
			canProduce = false;
			this.notify();
	}
	
	public synchronized void get()
	{
		if(canProduce) {
			try {
				this.wait();
			} catch (InterruptedException e) {
			}
		}
		System.out.printf("title: %s ---> content: %s\n",getTitle(), getContent());
		canProduce = true;
		this.notify();
	}
}
