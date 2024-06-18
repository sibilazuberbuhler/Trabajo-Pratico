package unogame;

import java.util.*;

public class UnoGame {
    private final List<String> players;
    private UnoState state;

    public UnoGame(List<String> players, Deck deck) {
        if (players == null || players.size() < 2) {
            throw new IllegalArgumentException("Not enough players to play");
        }
        this.players = players;
        this.state = new UnoStatePlaying(this, deck);
    }

    public String getCurrentPlayer() { 
        return state.getCurrentPlayer();
    }

    public List<String> getPlayers() { 
        return this.players;
    }

    public void playCard(String player, Card card) { 
        state.playCard(player, card);
    }


    public Map<String, List<Card>> getHands() { 
        return state.getHands();
    }

    public void placeCard(Card card) { 
        state.placeCard(card);
    }

    public void nextPlayer() { 
        state.nextPlayer();
    }

    public void reverseOrder() { 
        state.reverseOrder();
    }

    public void drawCards(int n) {
        state.drawCards(n);
    }

    public Card topCard() {
        return state.topCard();
    }

    public void addCardsToPlayer(String player, List<Card> cards) {
        state.addCardsToPlayer(player, cards);
    }
    public void setState(UnoState state) { 
        this.state = state;
    }
}





 