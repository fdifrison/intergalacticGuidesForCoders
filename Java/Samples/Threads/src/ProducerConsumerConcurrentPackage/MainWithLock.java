package ProducerConsumerConcurrentPackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

import static ProducerConsumerConcurrentPackage.MainWithLock.EOF;

public class MainWithLock {
	public static final String EOF = "EOF";

	public static void main(String[] args) {
		List<String> buffer = new ArrayList<String>();

		ReentrantLock bufferLock = new ReentrantLock();

		MyProducer2 producer = new MyProducer2(buffer, ThreadColor.ANSI_PURPLE, bufferLock);
		MyConsumer2 myConsumer1 = new MyConsumer2(buffer, ThreadColor.ANSI_CYAN, bufferLock);
		MyConsumer2 myConsumer2 = new MyConsumer2(buffer, ThreadColor.ANSI_GREEN, bufferLock);

		new Thread(producer).start();
		new Thread(myConsumer1).start();
		new Thread(myConsumer2).start();

	}
}

class MyProducer2 implements Runnable {
	private List<String> buffer;
	private String color;
	private ReentrantLock bufferLock;

	public MyProducer2(List<String> buffer, String color, ReentrantLock bufferLock) {
		this.buffer = buffer;
		this.color = color;
		this.bufferLock = bufferLock;
	}

	@Override
	public void run() {
		Random random = new Random();
		String[] nums = { "1", "2", "3", "4", "5" };

		for (String num : nums) {
			try {
				System.out.println(color + "Adding..." + num);

				bufferLock.lock();
				buffer.add(num);
				bufferLock.unlock();

				Thread.sleep(random.nextInt(1000));
			} catch (InterruptedException e) {
				System.out.println("Producer was interrupted");
			}
		}

		System.out.println(color + "Addig EOF and exiting...");
		bufferLock.lock();
		buffer.add(EOF);
		bufferLock.unlock();

	}

}

class MyConsumer2 implements Runnable {
	private List<String> buffer;
	private String color;
	private ReentrantLock bufferLock;

	public MyConsumer2(List<String> buffer, String color, ReentrantLock bufferLock) {
		this.buffer = buffer;
		this.color = color;
		this.bufferLock = bufferLock;
	}

	@Override
	public void run() {
		while (true) {

			bufferLock.lock();
			if (buffer.isEmpty()) {
				bufferLock.unlock();
				continue;
			}

			if (buffer.get(0).equals(EOF)) { // check if EOF is the first element of the list, meaning that
												// everything
												// has been received
				System.out.println(color + "Exiting");
				bufferLock.unlock();
				break;
			} else {
				System.out.println(color + "Removed " + buffer.remove(0));
			}

			bufferLock.unlock();
		}

	}

}
