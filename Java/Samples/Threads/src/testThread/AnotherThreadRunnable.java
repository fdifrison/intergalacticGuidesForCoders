package testThread;


public class AnotherThreadRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println(ThreadColor.ANSI_RED + "Hello from " + this.getClass());
    }

}
