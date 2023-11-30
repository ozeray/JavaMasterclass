package com.ahmet.jmc.collections;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<Card> deck = Card.getStandardDeck();
        Card.printDeck(deck);

        Card[] cardArray = new Card[13];
        Card aceOfHearts = Card.getFaceCard(Card.Suit.HEART, 'A');
        Arrays.fill(cardArray, aceOfHearts);
        Card.printDeck(Arrays.asList(cardArray), "Aces of Hearts", 1);

        List<Card> cards = new ArrayList<>(52); // Initial capacity given, but the list is still empty.
        Collections.fill(cards, aceOfHearts);
        // Will not fill the list with 52 elements! Because cards list is empty.
        System.out.println(cards);
        System.out.println("cards.size() = " + cards.size());

        List<Card> acesOfHearts = Collections.nCopies(13, aceOfHearts); // Returns an immutable list of 13 elements
        Card.printDeck(acesOfHearts, "Aces of Hearts", 1);

        Card kingOfClubs = Card.getFaceCard(Card.Suit.CLUB, 'K');
        List<Card> kingsOfClubs = Collections.nCopies(13, kingOfClubs);
        Card.printDeck(kingsOfClubs, "Kings of Clubs", 1);

        Collections.addAll(cards, cardArray); // As opposed to fill above, addAll method here adds elements.
        Collections.addAll(cards, cardArray);
        Card.printDeck(cards, "Card Collection with Aces Added", 2);

        Collections.copy(cards, kingsOfClubs); // Will throw "Source does not fit in dest" if cards size is not at least 13!
        // If more than 13, the first 13 elements will be replaced, remaining elements will not be affected.
        Card.printDeck(cards, "Card Collection with Kings Copied", 2);

        cards = List.copyOf(kingsOfClubs); // Returns an unmodifiable list!
        // Collections.copy(cards, acesOfHearts); // Calling this now will throw exception, since cards is
        // unmodifiable!
        Card.printDeck(cards, "List Copy Of Kings", 1);
        // Note: copyOf method throws NullPointerException if given coll is null or if it contains any nulls!

        Collections.shuffle(deck);
        Card.printDeck(deck, "Shuffled Deck", 4);

        Collections.reverse(deck);
        Card.printDeck(deck, "Reversed Deck", 4);

        var sortingAlgorithm = Comparator.comparing(Card::rank)
                                         .thenComparing(Card::suit);
        deck.sort(sortingAlgorithm);
//        Collections.sort(deck, sortingAlgorithm); Legacy way of sorting lists
        Card.printDeck(deck, "Sorted Deck", 13);

        Collections.reverse(deck);
        Card.printDeck(deck, "Reversed Sorted Deck", 13);

        Card.printDeck(deck.subList(4, 8), "Kings in Deck", 1);
        List<Card> tens = deck.subList(16, 20);
        Card.printDeck(tens, "Tens in Deck", 1);

        int tensSublistIndex = Collections.indexOfSubList(deck, tens);
        System.out.println("Sublist index for tens: " + tensSublistIndex);
        System.out.println("Deck contains tens? " + deck.containsAll(tens));

        boolean disjoint = Collections.disjoint(deck, tens);
        System.out.println("Tens and deck are disjoint: " + disjoint);
        boolean disjoint2 = Collections.disjoint(kingsOfClubs, tens);
        System.out.println("Kings of Club and Tens are disjoint: " + disjoint2);

        deck.sort(sortingAlgorithm);
        Card tenOfHearts = Card.getNumericCard(Card.Suit.HEART, 10);
        int foundIndex = Collections.binarySearch(deck, tenOfHearts, sortingAlgorithm);
        System.out.println("binarySearch: foundIndex = " + foundIndex);
        System.out.println("indexOf: foundIndex = " + deck.indexOf(tenOfHearts));
        System.out.println("Found = " + deck.get(foundIndex));

//        Card tenOfClubs = Card.getNumericCard(Card.Suit.CLUB, 10);
//        Collections.replaceAll(deck, tenOfClubs, tenOfHearts);
//        Card.printDeck(deck.subList(32, 36), "Tens row", 1);

//        System.out.println("# of Ten of Hearts in the deck: " + Collections.frequency(deck, tenOfHearts));

        System.out.println("Min: " + Collections.min(deck, sortingAlgorithm));
        System.out.println("Max: " + Collections.max(deck, sortingAlgorithm));

        var sortBySuit = Comparator.comparing(Card::suit)
                                   .thenComparing(Card::rank);
        deck.sort(sortBySuit);
        Card.printDeck(deck, "Deck Sorted By Suit, Rank", 4);

        List<Card> clubs = new ArrayList<>(deck.subList(0, 13));
        Collections.rotate(clubs, 2);
        System.out.println("UnRotated: " + deck.subList(0, 13));
        System.out.println("Rotated 2: " + clubs);

        clubs = new ArrayList<>(deck.subList(0, 13));
        Collections.rotate(clubs, -2);
        System.out.println("UnRotated: " + deck.subList(0, 13));
        System.out.println("Rotated -2: " + clubs);

    }

//    Stackoverflow:
//    An unmodifiable collection is often a wrapper around a modifiable collection which other code may still have access to. So while you can't make any changes to it if you only have a reference to the unmodifiable collection, you can't rely on the contents not changing.
//    An immutable collection guarantees that nothing can change the collection anymore. If it wraps a modifiable collection, it makes sure that no other code has access to that modifiable collection. Note that although no code can change which objects the collection contains references to, the objects themselves may still be mutable - creating an immutable collection of StringBuilder doesn't somehow "freeze" those objects.
}
