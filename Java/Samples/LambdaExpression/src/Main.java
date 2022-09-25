public class Main {
    
	public static void main(String[] args) {
//		new Thread(new CodeToRun()).start();
		
		// with anonymous class
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				System.out.println("Printing from runable");
//				
//			}
//		}).start();
		
		// Using Lambda Expression
		
		new Thread(()-> System.out.println("Printing from runnable")).start();
	}

	
}

class CodeToRun implements Runnable {

	@Override
	public void run() {
		System.out.println("Printing from runable");
		
	}
	
}