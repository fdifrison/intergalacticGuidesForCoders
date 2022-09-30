package counter;

public class CounterUnsync {
	
	public static void main(String[] args) {
		Countdown countdown = new Countdown();
		
		CountdownThread t1 = new CountdownThread(countdown);
		t1.setName("Thread 1");
		CountdownThread t2 = new CountdownThread(countdown);
		t2.setName("Thread 2");
		
		t1.start();
		t2.start();
		
	}

}


class Countdown {
	
	// if we use an instance variable to loop then the 2 thread will concur in the
	// same loop, so the first time that i=0 both threads (t1,t2) are stopped
	// if we define (int i = 10) in the for loop instead (we create a variable), both thread will count from 10 to 1
	private int i;
	
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
		
		for(i =10; i> 0; i--) {
			System.out.println(color + Thread.currentThread().getName() + ": i = " +i);
		}
		
		// in case of not declaring the instance variable i above
//		for(int i =10; i> 0; i--) {
//			System.out.println(color + Thread.currentThread().getName() + ": i = " +i);
//		}
		
	}
}

class CountdownThread extends Thread {
	private Countdown threCountdown;
	
	public CountdownThread(Countdown countdown) {
		threCountdown = countdown;
	}
	
	public void run() {
		threCountdown.doCountdown();
	}
}






