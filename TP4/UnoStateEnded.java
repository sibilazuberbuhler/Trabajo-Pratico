package unogame;

import java.util.List;
import java.util.Map;

public class UnoStateEnded extends UnoState{
    private final String winnerPlayer;

    public UnoStateEnded(String winner) {
        this.winnerPlayer = winner;
    }

    private void juegoTerminado() {
        throw new IllegalStateException("El juego ha terminado.");
    }

    @Override
    public void nextPlayer() {
        juegoTerminado();
    }

    @Override
    public void reverseOrder() {
        juegoTerminado();
    }

    @Override 
    public void placeCard(Card card) {
        juegoTerminado();
    }

    @Override
    public void drawCards(int n) {
        juegoTerminado();
    }

    @Override
    public Card topCard() {
        throw new IllegalStateException("El juego ha terminado.");
    }

    @Override
    public String getCurrentPlayer() {
        return winnerPlayer;
    }

    @Override 
    public void playCard(String player, Card card) {
        juegoTerminado();
    }

    @Override
    public boolean gameEnded() {
        return true;
    }

    @Override 
    public Map<String, List<Card>> getHands() {
        throw new IllegalStateException("El juego ha terminado.");
    }

    @Override 
    public void addCardsToPlayer(String player, List<Card> cards) {
        juegoTerminado();
    }
}

