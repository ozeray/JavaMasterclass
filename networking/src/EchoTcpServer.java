import java.io.IOException;
import java.net.ServerSocket;

public class EchoTcpServer {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            while (true) { // Start new thread when a new connection is established.
                new TcpEchoer(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            System.out.println("Server exception " + e.getMessage());
        }
    }
}
