package highLevelAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class GetWebPageHTML {

	public static void main(String[] args) {
		try {
			URL url = new URL("http://example.com");
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			connection.connect();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			Map<String, List<String>> headersMap = connection.getHeaderFields();
			for (Map.Entry<String, List<String>> entry : headersMap.entrySet()) {
				String key = entry.getKey();
				List<String> value = entry.getValue();
				System.out.println("Value for key: " + key);
				value.stream().forEach(System.out::println);
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
