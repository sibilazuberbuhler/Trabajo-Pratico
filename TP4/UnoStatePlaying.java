package unogame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class UnoStatePlaying extends UnoState {
    private final Deck deck;
    private int currentPlayerIndex = 0;
    private final List<Card> pit = new ArrayList<>();
    private final Map<String, List<Card>> hands = new HashMap<>();
    private int currentOrder = 1;
    private List<String> players;
    private final UnoGame unogame;

    public UnoStatePlaying(UnoGame unogame, Deck deck) {
        players = unogame.getPlayers();
        this.unogame = unogame;
        this.deck = deck;
        players.forEach(player -> hands.put(player, new ArrayList<>()));
    }

    @Override
    public void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + currentOrder + players.size()) % players.size();
    }

    @Override
    public void reverseOrder() {
        currentOrder = currentOrder * -1;
    }

    @Override 
    public void placeCard(Card card) {
        pit.add(card);
    }

    @Override
    public void drawCards(int n) {
        IntStream.range(0, n).forEach(i -> addCardToCurrentPlayer());
    }
    public void addCardToCurrentPlayer() {
        String currentPlayer = getCurrentPlayer();
        Card newCard = deck.draw();
        hands.get(currentPlayer).add(newCard);
    }

    @Override
    public String getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    @Override
    public Card topCard() {
        return pit.get(pit.size() - 1);
    }

    @Override 
    public void playCard(String player, Card card) {
        if (getCurrentPlayer() != player) {
            throw new IllegalStateException("No es el turno de ese jugador");
        }
        if (!topCard().canAccept(card)) {
            throw new IllegalStateException("La carta no puede jugarse sobre la anterior");
        }
        card.bePlayed(unogame);
        hands.get(player).remove(card);

        if (hands.get(player).isEmpty()) {
            unogame.setState(new UnoStateEnded(player));
        }
    }

    @Override
    public boolean gameEnded() {
        return false;
    }

    @Override 
    public Map<String, List<Card>> getHands() {
        return hands;
    }

    @Override 
    public void addCardsToPlayer(String player, List<Card> cards) {
        hands.put(player, cards);
    }
}
