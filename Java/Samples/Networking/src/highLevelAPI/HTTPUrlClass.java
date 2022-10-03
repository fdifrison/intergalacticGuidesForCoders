package highLevelAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class HTTPUrlClass {
	public static void main(String[] args) {

		try {
			URL url = new URL("http://example.com");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", "Chrome");
			connection.setReadTimeout(30000);

			int responseCode = connection.getResponseCode(); // implicitly call connection.connect()
			System.out.println("Response code: " + responseCode);

			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				line = bufferedReader.readLine();
				System.out.println(line);
			}

			bufferedReader.close();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
