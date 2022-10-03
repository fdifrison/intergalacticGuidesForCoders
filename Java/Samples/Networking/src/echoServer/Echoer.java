package echoServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Echoer extends Thread {
	private Socket socket;

	public Echoer(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
			while (true) {
				String echoString = bufferedReader.readLine();
				System.out.println("Client is saying: " + echoString);
				if (echoString.equals("exit")) {
					break;
				}
				try {
					Thread.sleep(15000); // make the client wait for server response
					// the reason is to not congest the server
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				printWriter.println(echoString);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
