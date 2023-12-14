import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpPostExample {

    public static void main(String[] args) {
        try {
            URL url = new URL("http://somewebpage.com/process.php");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type",  "application/x-www-form-urlencoded");

            String params = "parameter1=25&parameter2=hello";
            connection.setRequestProperty("Content-Length", Integer.toString(params.getBytes().length));

            connection.setUseCaches(false);
            connection.setDoOutput(true);
            connection.setDoInput(true);

            DataOutputStream output = new DataOutputStream(connection.getOutputStream());
            output.writeBytes(params);
            output.flush();
            output.close();

            InputStream input = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line = "";
            while (line != null) {
                line = reader.readLine();
                System.out.println(line);
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
