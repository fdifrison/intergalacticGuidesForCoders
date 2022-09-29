package testThread;

public class AnotherThread extends Thread {

	@Override
	public void run() {
		System.out.println(ThreadColor.ANSI_CYAN + "Hello from " + currentThread().getName());
		
		try {
			System.out.println("I'm tired let me sleep a bit pls...");
			Thread.sleep(3000);
			System.out.println(ThreadColor.ANSI_CYAN +"I have slept 3 seconds.. now I'm fresh");
		} catch (InterruptedException e) {
			System.out.println(ThreadColor.ANSI_CYAN + "Another thread woke me up");
			return; //terminate the thread instance if exception is catch
		}
		
		
		
//		System.out.println(ThreadColor.ANSI_BLACK + "3 Seconds passed, I'm awake");
		
	}

	
}
