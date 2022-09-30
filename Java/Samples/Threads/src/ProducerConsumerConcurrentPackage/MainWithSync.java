package ProducerConsumerConcurrentPackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static ProducerConsumerConcurrentPackage.MainWithSync.EOF;

public class MainWithSync {
	public static final String EOF = "EOF";

	public static void main(String[] args) {
		List<String> buffer = new ArrayList<String>();
		MyProducer producer = new MyProducer(buffer, ThreadColor.ANSI_PURPLE);
		MyConsumer myConsumer1 = new MyConsumer(buffer, ThreadColor.ANSI_CYAN);
		MyConsumer myConsumer2 = new MyConsumer(buffer, ThreadColor.ANSI_GREEN);

		new Thread(producer).start();
		new Thread(myConsumer1).start();
		new Thread(myConsumer2).start();

	}
}

class MyProducer implements Runnable {
	private List<String> buffer;
	private String color;

	public MyProducer(List<String> buffer, String color) {
		this.buffer = buffer;
		this.color = color;
	}

	@Override
	public void run() {
		Random random = new Random();
		String[] nums = { "1", "2", "3", "4", "5" };

		for (String num : nums) {
			try {
				System.out.println(color + "Adding..." + num);
				synchronized (buffer) {
					buffer.add(num);
				}

				Thread.sleep(random.nextInt(1000));
			} catch (InterruptedException e) {
				System.out.println("Producer was interrupted");
			}
		}

		System.out.println(color + "Addig EOF and exiting...");
		synchronized (buffer) {
			buffer.add(EOF);
		}
	}

}

class MyConsumer implements Runnable {
	private List<String> buffer;
	private String color;

	public MyConsumer(List<String> buffer, String color) {
		this.buffer = buffer;
		this.color = color;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (buffer) {

				if (buffer.isEmpty()) {
					continue;
				}

				if (buffer.get(0).equals(EOF)) { // check if EOF is the first element of the list, meaning that
													// everything
													// has been received
					System.out.println(color + "Exiting");
					break;
				} else {
					System.out.println(color + "Removed " + buffer.remove(0));
				}

			}
		}

	}

}
