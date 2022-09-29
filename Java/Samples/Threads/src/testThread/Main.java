package testThread;

public class Main {

	public static void main(String[] args) {
		System.out.println(ThreadColor.ANSI_GREEN + "Hello from main thread");
		
		AnotherThreadRunnable newThread = new AnotherThreadRunnable();
		new Thread (newThread).start(); 
		
		Thread anotherThread = new AnotherThread();
		anotherThread.setName("AnotherTread");
		anotherThread.start();
		
		Thread runnableThread =new Thread(new AnotherThreadRunnable() {

			@Override
			public void run() {
				System.out.println(ThreadColor.ANSI_PURPLE + "Thread purle is coming!");
				try {
					System.out.println(ThreadColor.ANSI_PURPLE +"Oh come on AnotherTread is sleeping.. now I have to wait.. f***!");
					anotherThread.join(2000);
					System.out.println(ThreadColor.ANSI_PURPLE + "AnotherTread terminated or timed out, I can run again ");
				} catch (InterruptedException e) {
					System.out.println(ThreadColor.ANSI_PURPLE + "I coundn't wait, I was interrupted!");
				}
			}
			
		});
		
		runnableThread.start();
		
		
//		anotherThread.interrupt();
		
		System.out.println(ThreadColor.ANSI_BLUE + "Hello from end of main again");
		

		
	}
}
