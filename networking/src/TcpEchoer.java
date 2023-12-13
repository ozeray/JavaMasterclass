import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TcpEchoer extends Thread {
    private Socket socket;

    public TcpEchoer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            while (true) {
                String echoString = input.readLine();
                try {
                    Thread.sleep(2500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (echoString.equals("exit")) {
                    break;
                }
                System.out.printf("%s - client sent: %s%n", getName(), echoString);
                output.println(echoString);
            }
        } catch (IOException e) {
            System.out.println("Ooops: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException ignored) {}
        }
    }
}
