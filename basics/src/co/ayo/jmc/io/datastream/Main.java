package co.ayo.jmc.io.datastream;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

class Player implements Serializable {
    @Serial
    private static final long serialVersionUID = 15L;
    private static final int version = 3;

    private transient long accountId;
    private String name;
    private long topScore;
    private List<String> collectedWeapons = new ArrayList<>();

    public Player(String name, long id, int topScore, List<String> collectedWeapons) {
        this.name = name;
        this.accountId = id;
        this.topScore = topScore;
        this.collectedWeapons = collectedWeapons;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                "id='" + accountId + '\'' +
                ", topScore=" + topScore +
                ", collectedWeapons=" + collectedWeapons +
                '}';
    }

    @Serial
    @SuppressWarnings("unchecked")
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
//        stream.defaultReadObject();
        System.out.println("Deserializing");
        name = stream.readUTF();
        int serializedVersion = stream.readInt();
        topScore = (serializedVersion == 3) ? stream.readLong() : stream.readInt();
//        topScore = stream.readInt();
        collectedWeapons = (List<String>) stream.readObject();
    }

    @Serial
    private void writeObject(ObjectOutputStream stream) throws IOException {
//        stream.defaultWriteObject();
        System.out.println("Serializing");
        stream.writeUTF(name);
        stream.writeInt(version);
        stream.writeLong(topScore);
        stream.writeObject(collectedWeapons);
    }
}

public class Main {

    public static void main(String[] args) {
//        Path dataFile = Path.of("basics/files/data.dat");
//        writeData(dataFile);
//        readData(dataFile);

        Player tim = new Player("Tim", 2L, 100_000_010, List.of("knife", "machete", "pistol"));
        System.out.println(tim);

        Path timFile = Path.of("basics/files/tim.dat");
//        writeObject(timFile, tim);
        Player player = readObject(timFile);
        System.out.println(player);
    }

    private static void writeData(Path dataFile) {
        try (DataOutputStream dataStream =
                     new DataOutputStream(new FileOutputStream(dataFile.toFile()))) {
            // If writing large amount of data wrap inside BufferedOutputStream:
            // new DataOutputStream(new BufferedOutputStream(new FileOutputStream(dataFile.toFile()))) {
            int myInt = 17;
            long myLong = 100_000_000_000_000L;
            boolean myBoolean = true;
            char myChar = 'Z';
            float myFloat = 77.7f;
            double myDouble = 98.6;
            String myString = "Hello World";

            long position = 0;
            dataStream.writeInt(myInt);
            System.out.println("writeInt writes " + (dataStream.size() - position));
            position = dataStream.size();

            dataStream.writeLong(myLong);
            System.out.println("writeLong writes " + (dataStream.size() - position));
            position = dataStream.size();

            dataStream.writeBoolean(myBoolean);
            System.out.println("writeBoolean writes " + (dataStream.size() - position));
            position = dataStream.size();

            dataStream.writeChar(myChar);
            System.out.println("writeChar writes " + (dataStream.size() - position));
            position = dataStream.size();

            dataStream.writeFloat(myFloat);
            System.out.println("writeFloat writes " + (dataStream.size() - position));
            position = dataStream.size();

            dataStream.writeDouble(myDouble);
            System.out.println("writeDouble writes " + (dataStream.size() - position));
            position = dataStream.size();

            dataStream.writeUTF(myString);
            System.out.println("writeUTF writes " + (dataStream.size() - position));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readData(Path dataFile) {
        try (DataInputStream dataStream = new DataInputStream(Files.newInputStream(dataFile))) {
            System.out.println("myInt = " + dataStream.readInt());
            System.out.println("myLong = " + dataStream.readLong());
            System.out.println("myBool = " + dataStream.readBoolean());
            System.out.println("myChar = " + dataStream.readChar());
            System.out.println("myFloat = " + dataStream.readFloat());
            System.out.println("myDouble = " + dataStream.readDouble());
            System.out.println("myString = " + dataStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeObject(Path dataFile, Player player) {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(dataFile))) {
            System.out.println("Writing object");
            oos.writeObject(player);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Player readObject(Path dataFile) {
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(dataFile))) {
            System.out.println("Reading object");
            return (Player) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
