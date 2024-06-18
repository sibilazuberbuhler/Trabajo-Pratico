package unogame;

import java.util.List;
import java.util.Map;

public abstract class UnoState {
    public abstract void nextPlayer();

    public abstract void reverseOrder();

    public abstract void placeCard(Card card);

    public abstract void drawCards(int n);

    public abstract String getCurrentPlayer();

    public abstract void playCard(String player, Card card);

    public abstract boolean gameEnded();

    public abstract Card topCard();

    public abstract Map<String, List<Card>> getHands();

    public abstract void addCardsToPlayer(String player, List<Card> cards);
}
