import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class EchoUdpServer {
    public static void main(String[] args) {
        try (DatagramSocket datagramSocket = new DatagramSocket(5000)) {
            while (true) {
                byte[] data = new byte[50];
                DatagramPacket datagramPacket = new DatagramPacket(data, data.length);
                datagramSocket.receive(datagramPacket);
//                System.out.println("Data received: " + new String(data));
                String message = new String(data, 0, datagramPacket.getLength());
                System.out.println("Data received: " + message);

                InetAddress clientAddress = datagramPacket.getAddress();
                int clientPort = datagramPacket.getPort();
                message = "echo:" + message;
                byte[] messageBuffer = message.getBytes();
                DatagramPacket packet = new DatagramPacket(messageBuffer, messageBuffer.length, clientAddress, clientPort);
                datagramSocket.send(packet);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
