package counter;

public class CounterSync {
	
	public static void main(String[] args) {
		CountdownSync countdown = new CountdownSync();
		
		CountdownThreadSync t1 = new CountdownThreadSync(countdown);
		t1.setName("Thread 1");
		CountdownThreadSync t2 = new CountdownThreadSync(countdown);
		t2.setName("Thread 2");
		
		t1.start();
		t2.start();
		
	}

}

// makes sense to synchronize with a synchronized block since the only critical operation in the method  is the for loop
class CountdownSync {
	
	public void doCountdown() {
		String color;
		
		switch (Thread.currentThread().getName()) {
		case "Thread 1": {
			color = ThreadColor.ANSI_CYAN;
			break;
		}
		case "Thread 2": {
			color = ThreadColor.ANSI_PURPLE;
			break;
		}
		default:
			color = ThreadColor.ANSI_GREEN;
		}
		
		
		synchronized (this) { // we synchronize over the countdown object that is sheared between the threads
			for(int i =10; i> 0; i--) {
				System.out.println(color + Thread.currentThread().getName() + ": i = " +i);
			}
		}
		
		
	
	}
}



//class CountdownSync {
//	
//	public synchronized void doCountdown() {
//		String color;
//		
//		switch (Thread.currentThread().getName()) {
//		case "Thread 1": {
//			color = ThreadColor.ANSI_CYAN;
//			break;
//		}
//		case "Thread 2": {
//			color = ThreadColor.ANSI_PURPLE;
//			break;
//		}
//		default:
//			color = ThreadColor.ANSI_GREEN;
//		}
//		
//		for(int i =10; i> 0; i--) {
//			System.out.println(color + Thread.currentThread().getName() + ": i = " +i);
//		}
//		
//	
//	}
//}

class CountdownThreadSync extends Thread {
	private CountdownSync threCountdown;
	
	public CountdownThreadSync(CountdownSync countdown) {
		threCountdown = countdown;
	}
	
	public void run() {
		threCountdown.doCountdown();
	}
}






