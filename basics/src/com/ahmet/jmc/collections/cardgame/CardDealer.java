package com.ahmet.jmc.collections.cardgame;

import com.ahmet.jmc.collections.Card;

import java.util.Collections;
import java.util.List;

public class CardDealer {

    public static void main(String[] args) {
        dealCardsAndDisplayPlayerHandScores(4);
    }

    static void dealCardsAndDisplayPlayerHandScores(int numberOfPlayers) {
        List<Card> deck = Card.getStandardDeck();
        Collections.shuffle(deck);
        int numberOfCardsPerPlayer = deck.size() / numberOfPlayers;
        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.println("\nPlayer #" + (i + 1));
            List<Card> playerCards = deck.subList(i * numberOfCardsPerPlayer, (i + 1) * numberOfCardsPerPlayer);
            Card.printDeck(playerCards, null, 4);
            System.out.println(new PlayerCards(playerCards));
        }
    }
}
