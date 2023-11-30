package co.ayo.jmc.linkedlists;

import java.util.*;

public class AlbumSongChallenge {

    private static final ArrayList<Album> albums = new ArrayList<>();

    public static void main(String[] args) {
        Album album = new Album("Hasret", "Orhan Gencebay");
        album.addSong("Hasret", 4.35);
        album.addSong("Sev Beni", 4.44);
        album.addSong("Bırakma", 5.3);
        album.addSong("Ayşem", 4.33);
        album.addSong("Akşam Güneşi", 5.25);
        albums.add(album);

        album = new Album("Kuzu Kuzu", "Tarkan");
        album.addSong("Kuzu Kuzu", 3.56);
        album.addSong("Yalnızlık", 4.2);
        album.addSong("Dudu Dudu", 4.55);
        albums.add(album);

        LinkedList<Song> playlist = new LinkedList<>();
        albums.get(0).addToPlayList("Hasret", playlist);
        albums.get(0).addToPlayList("Ayşem", playlist);
        albums.get(0).addToPlayList(5, playlist);
        albums.get(0).addToPlayList(14, playlist);
        albums.get(1).addToPlayList(0, playlist);
        albums.get(1).addToPlayList("Dudu Dudu", playlist);
        albums.get(1).addToPlayList("Bici Bici", playlist);

        play(playlist);
    }

    private static void play(LinkedList<Song> playList) {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        boolean forward = true;
        ListIterator<Song> listIterator = playList.listIterator();
        if (playList.size() == 0) {
            System.out.println("No songs in playlist");
            return;
        } else {
            System.out.println("Now playing " + listIterator.next());
            printMenu();
        }

        while (!quit) {
            int action = scanner.nextInt();

            switch (action) {
                case 0:
                    System.out.println("Playlist complete.");
                    quit = true;
                    break;
                case 1:
                    if (!forward) {
                        if (listIterator.hasNext()) {
                            listIterator.next();
                        }
                        forward = true;
                    }
                    if (listIterator.hasNext()) {
                        System.out.println("Now playing " + listIterator.next());
                    } else {
                        System.out.println("We have reached the end of the playlist");
                        forward = false;
                    }
                    break;
                case 2:
                    if (forward) {
                        if (listIterator.hasPrevious()) {
                            listIterator.previous();
                        }
                        forward = false;
                    }
                    if (listIterator.hasPrevious()) {
                        System.out.println("Now playing " + listIterator.previous());
                    } else {
                        System.out.println("We are at the start of the playlist");
                        forward = true;
                    }
                    break;
                case 3:
                    if (forward) {
                        if (listIterator.hasPrevious()) {
                            System.out.println("Now replaying " + listIterator.previous());
                            forward = false;
                        } else {
                            System.out.println("We are at the start of the playlist");
                        }
                    } else {
                        if (listIterator.hasNext()) {
                            System.out.println("Now replaying " + listIterator.next());
                            forward = true;
                        } else {
                            System.out.println("We have reached the end of the playlist");
                        }
                    }
                    break;
                case 4:
                    printList(playList);
                    printMenu();
                    break;
                case 5:
                    printMenu();
                    break;
                case 6:
                    if (playList.size() > 0) {
                        listIterator.remove();
                        if (listIterator.hasNext()) {
                            System.out.println("Now playing " + listIterator.next());
                        } else if (listIterator.hasPrevious()) {
                            System.out.println("Now playing " + listIterator.previous());
                        } else {
                            System.out.println("No songs in playlist");
                            quit = true;
                        }
                    }
                    break;
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("Available actions:\npress");
        System.out.println("0 - to quit\n" +
                           "1 - to play next song\n" +
                           "2 - to play previous song\n" +
                           "3 - to replay the current song\n" +
                           "4 - to list songs in the playlist\n" +
                           "5 - to print available actions\n" +
                           "6 - delete current song from playlist");
    }

    private static void printList(LinkedList<Song> playList) {
        Iterator<Song> iterator = playList.iterator();
        System.out.println("===================================");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("===================================");
    }
}
