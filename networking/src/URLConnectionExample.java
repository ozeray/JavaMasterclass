import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class URLConnectionExample {

    public static void main(String[] args) {
        try {
            URL url = new URL("http://example.org");
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            connection.connect();
            BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            Map<String, List<String>> headerFields = connection.getHeaderFields();
            for (Map.Entry<String, List<String>> entry : headerFields.entrySet()) {
                String key = entry.getKey();
                List<String> value = entry.getValue();
                System.out.println(key + ":");
                value.forEach(s -> System.out.println("\t" + s));

            }
//            String line = "";
//            while (line != null) {
//                line = input.readLine();
//                System.out.println(line);
//            }
            input.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
