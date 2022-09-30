package messages;

import java.util.Random;

public class Main {

	public static void main(String[] args) {
		Message message = new Message();
		(new Thread(new Writer(message))).start();
		(new Thread(new Reader(message))).start();
	}
}

class Message {
	private String message;
	private boolean empty = true;

	public synchronized String read() {
		while (empty) {
			// loop until there is something to read
			try {
				wait();
			} catch (InterruptedException e) {

			}
		}
		empty = true;
		notifyAll();
		return message;
	}

	public synchronized void write(String message) {
		while (!empty) {
			try {
				wait();
			} catch (InterruptedException e) {

			}
		}
		empty = false;
		this.message = message;
		notifyAll();
	}

}

// PRODUCER
class Writer implements Runnable {
	private Message message;

	public Writer(Message message) {
		this.message = message;
	}

	@Override
	public void run() {
		String messages[] = { "Dove sono cavallo e cavaliere? Dov'è il corno dal suono violento?",
				"Dove sono l'elmo e lo scudiero, e la fulgida capigliatura al vento?",
				"Dov'è la mano sull'arpa, e il rosso fuoco ardente?",
				"Dov'è la primavera e la messe, ed il biondo grano crescente?",
				"Son passati come pioggia sulla montagna, come raffiche di vento in campagna;",
				"I giorni scompaiono ad ovest, dietro i colli che un mare d'ombra bagna.",
				"Chi riunirà il fumo del legno morto incandescente?",
				"Chi tornerà dal Mare e potrà mirare il tempo lungo e fuggente?" };

		Random random = new Random();

		for(int i=0; i<messages.length; i++) {
            message.write(messages[i]);

			try {
				Thread.sleep(random.nextInt(2000));
			} catch (InterruptedException e) {

			}
		}

		message.write("Said Theoden");

	}

}

class Reader implements Runnable {
	private Message message;

	public Reader(Message message) {
		this.message = message;
	}

	public void run() {
		Random random = new Random();
		for (String latestMessage = message.read(); !latestMessage.equals("Said Theoden"); latestMessage = message.read()) {
			System.out.println(latestMessage);
			try {
				Thread.sleep(random.nextInt(2000));
			} catch (InterruptedException e) {

			}
		}
	}

}
