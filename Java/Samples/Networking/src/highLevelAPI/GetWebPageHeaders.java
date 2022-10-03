package highLevelAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

public class GetWebPageHeaders {

	public static void main(String[] args) {
		try {
			URL url = new URL("http://example.com");
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true); // needed to POST to webpage
			connection.connect();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			// ONE WAY TO READ A WEBPAGE
//			URL url = new URL("http://example.com");
//			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
//
			String line = "";
			while (line != null) {
				line = bufferedReader.readLine();
				System.out.println(line);
			}
			bufferedReader.close();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
