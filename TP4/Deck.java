package unogame;

import java.util.List;

public class Deck {
    List<Card> cards;

    public Deck(List<Card> cards) {
        this.cards = cards;   
    }

    public Card draw() {
        return cards.remove(cards.size() - 1);
    }
}
