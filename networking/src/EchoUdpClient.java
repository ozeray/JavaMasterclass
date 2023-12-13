import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class EchoUdpClient {

    public static void main(String[] args) throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        try (DatagramSocket socket = new DatagramSocket()) {
            Scanner scanner = new Scanner(System.in);
            String echoString;
            do {
                System.out.println("Enter string to be echoed: ");
                echoString = scanner.nextLine();

                byte[] buffer = echoString.getBytes();
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, inetAddress, 5000);
                socket.send(datagramPacket);

                byte[] received = new byte[50];
                DatagramPacket receivedPacket = new DatagramPacket(received, received.length);
                socket.receive(receivedPacket);
                System.out.println("Received: " + new String(received, 0, receivedPacket.getLength()));
            } while (!echoString.equals("exit"));
        } catch (IOException e) {
            System.out.println("Client error " + e.getMessage());
        }
    }
}
