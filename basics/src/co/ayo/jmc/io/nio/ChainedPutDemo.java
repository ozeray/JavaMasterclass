package co.ayo.jmc.io.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChainedPutDemo {
    public static void main(String[] args) {
        try(FileOutputStream output = new FileOutputStream("basics/files/sample.dat")) {
            FileChannel fileChannel = output.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(100);
            byte[] outputBytes = "Hello World".getBytes();
            byte[] outputBytes2 = "Nice to meet you".getBytes();
            buffer.put(outputBytes)
                  .putInt(245)
                  .putInt(-9876)
                  .put(outputBytes2);
            buffer.flip();
            fileChannel.write(buffer);

            RandomAccessFile raf = new RandomAccessFile("basics/files/sample.dat", "rwd");
            FileChannel readChannel = raf.getChannel();
            ByteBuffer readBuffer = ByteBuffer.allocate(100);
            readChannel.read(readBuffer);
            readBuffer.flip();
            byte[] firstStringArr = new byte[outputBytes.length];
            readBuffer.get(firstStringArr);
            System.out.println("First: " + new String(firstStringArr));
            System.out.println(readBuffer.getInt());
            System.out.println(readBuffer.getInt());
            byte[] secondStringArr = new byte[outputBytes2.length];
            readBuffer.get(secondStringArr);
            System.out.println("Second: " + new String(secondStringArr));

            fileChannel.close();
            raf.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
