package co.ayo.jmc.collections.cardgame;

import co.ayo.jmc.collections.Card;

import java.util.List;

public class PlayerCards {

    private final List<Card> cards;

    PlayerCards(List<Card> cards) {
        this.cards = cards;
    }

    private int playerHandScore() {
        int score = 0;
        for (Card card : cards) {
            score += card.rank();
        }
        return score;
    }

    @Override
    public String toString() {
        return "Score: %d".formatted(playerHandScore());
    }
}
