package co.ayo.jmc.io.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Main {

    public static void main(String[] args) {
        try (FileOutputStream file = new FileOutputStream("basics/files/sample.dat")) {
            FileChannel binChannel = file.getChannel();
            byte[] outputBytes = "Hello World".getBytes();
            ByteBuffer byteBuffer = ByteBuffer.wrap(outputBytes);
            int numBytes = binChannel.write(byteBuffer);
            System.out.println("Num of bytes written: " + numBytes);
            System.out.println(new String(outputBytes));

            ByteBuffer intBuffer = ByteBuffer.allocate(Integer.BYTES);
            intBuffer.putInt(245);
            intBuffer.flip();
            numBytes = binChannel.write(intBuffer);
            System.out.println("Written: " + numBytes);

            intBuffer.flip(); // Buffer size = 4!
            intBuffer.putInt(-9876);
            intBuffer.flip();
            numBytes = binChannel.write(intBuffer);
            System.out.println("Written: " + numBytes);

            RandomAccessFile raf = new RandomAccessFile("basics/files/sample.dat", "rwd");
            // Using standard IO:
//            byte[] b = new byte[outputBytes.length];
//            raf.read(b);
//            System.out.println(new String(b));
//            int a1 = raf.readInt();
//            int a2 = raf.readInt();
//            System.out.println(a1);
//            System.out.println(a2);

            // Using NIO:
            FileChannel fileChannel = raf.getChannel();
//            outputBytes[0] = 'a';
//            outputBytes[1] = 'b';
            byteBuffer.flip();
            long numBytesRead = fileChannel.read(byteBuffer);
            if (byteBuffer.hasArray()) {
                System.out.println("byte array = " + new String(byteBuffer.array()));
            }
//            System.out.println("outputBytes = " + new String(outputBytes));

            // Relative read:
//            intBuffer.flip();
//            numBytesRead = fileChannel.read(intBuffer);
//            intBuffer.flip();
//            System.out.println(intBuffer.getInt());
//            intBuffer.flip();
//            numBytesRead = fileChannel.read(intBuffer);
//            intBuffer.flip();
//            System.out.println(intBuffer.getInt());

            // Absolute read:
            intBuffer.flip();
            numBytesRead = fileChannel.read(intBuffer);
            System.out.println(intBuffer.getInt(0));
            intBuffer.flip();
            numBytesRead = fileChannel.read(intBuffer);
            System.out.println(intBuffer.getInt(0));

            binChannel.close();
            fileChannel.close();
            raf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
