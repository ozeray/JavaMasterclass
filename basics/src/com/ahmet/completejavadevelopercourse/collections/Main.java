package com.ahmet.completejavadevelopercourse.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

    }

//    Stackoverflow:
//    An unmodifiable collection is often a wrapper around a modifiable collection which other code may still have access to. So while you can't make any changes to it if you only have a reference to the unmodifiable collection, you can't rely on the contents not changing.
//    An immutable collection guarantees that nothing can change the collection any more. If it wraps a modifiable collection, it makes sure that no other code has access to that modifiable collection. Note that although no code can change which objects the collection contains references to, the objects themselves may still be mutable - creating an immutable collection of StringBuilder doesn't somehow "freeze" those objects.
}
