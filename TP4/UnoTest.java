package unogame;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


public class UnoTest {

    @Test
    void testPlayerCanThrowBlueOverRed() {
        UnoGame game = GameNumberCard(List.of("Camila", "Sibila"), "Red");
        game.addCardsToPlayer("Camila", camilaCards);
        game.playCard("Camila", camilaCards.get(4));
        assertEquals("Blue", game.topCard().getColor());
        assertEquals(2, game.topCard().getNumber());
    }

    @Test
    void testPlayerCantThrowRedOverGreen() {
        UnoGame game = GameNumberCard(List.of("Camila", "Sibila"), "Green");
        game.addCardsToPlayer("Sibila", sibilaCards);
        assertThrows(IllegalStateException.class, () -> game.playCard("Sibila", sibilaCards.get(0)));
    }

    @Test
    void TestNewPitCard() {
        UnoGame game = GameNumberCard(List.of("Camila", "Sibila"), "Red");
        assertEquals("Red", game.topCard().getColor());
        assertEquals(2, game.topCard().getNumber());
    }

    @Test
    void testCanCreateAndSetAGame() {
        Deck deck = new Deck(deckCards);
        List<String> players = List.of("Camila", "Sibila");
        UnoGame game = new UnoGame(players, deck);

        assertEquals(players.size(), game.getHands().size());
        assertEquals(players.get(0), game.getCurrentPlayer());
    }

    @Test
    void TestCantPlayWithLessThan2Players() {
        assertThrows(IllegalArgumentException.class, () -> new UnoGame(List.of("Camila"), new Deck(deckCards)));
    }

    @Test
    void TestPlayerThrowsCardAndRemovesFromHand() {
        UnoGame game = GameNumberCard(List.of("Camila", "Sibila"), "red");
        game.addCardsToPlayer("Camila", camilaCards);
        game.playCard("Camila", camilaCards.get(0));
        assertEquals(8, game.getHands().get("Camila").size());
    }

    @Test
    void TestPlayerCanThrowCardAfterOtherPlayer() {
        UnoGame game = GameNumberCard(List.of("Camila", "Sibila"), "red");
        AddCardsCamilaAndSibila(game);
        game.playCard("Camila", camilaCards.get(3));
        game.playCard("Sibila", sibilaCards.get(2));
        assertEquals(8, game.getHands().get("Camila").size());
        assertEquals(6, game.getHands().get("Sibila").size());
    }

    @Test
    void TestPlayerCannotThrowCardIncompatibleWithThePitCard() {
        assertThrows(IllegalStateException.class, () -> GameNumberCard(List.of("Camila", "Sibila"), "red").playCard("Camila", camilaCards.get(2)));
    }

    @Test
    void TestPlayerCannotThrowCardThatDoesNotHave() {
        GameNumberCard(List.of("Camila", "Sibila"), "red").addCardsToPlayer("Camila", camilaCards);
        assertThrows(IllegalStateException.class, () -> GameNumberCard(List.of("Camila", "Sibila"), "red").playCard("Camila", new NumberCard("yellow", 10)));
    }

    @Test
    void TestSkipCardSkipsPlayer2() {
        UnoGame game = GameNumberCard(List.of("Camila", "Sibila"), "red");
        game.addCardsToPlayer("Camila", camilaCards);
        game.playCard("Camila", camilaCards.get(5));
        assertEquals("Camila", game.getCurrentPlayer());
    }

    @Test
    void TestReverseCardReversesOrder() {
        UnoGame game = GameNumberCard(List.of("Camila", "Sibila"), "red");
        game.addCardsToPlayer("Camila", camilaCards);
        game.playCard("Camila", camilaCards.get(6));
        assertEquals("Camila", game.getCurrentPlayer());
    }


    @Test
    void TestPlayerCannotPlayOutOfTurn() {
        UnoGame game = GameNumberCard(List.of("Camila", "Sibila"), "red");
        AddCardsCamilaAndSibila(game);
        game.playCard("Camila", camilaCards.get(0));
        game.playCard("Sibila", sibilaCards.get(0));
        assertThrows(IllegalStateException.class, () -> game.playCard("Sibila", sibilaCards.get(2)));
    }

    @Test
    void TestDrawTwoCardSkipsPlayer2AndAdds2Cards() {
        Deck deck = new Deck(deckCards);
        List<String> players = List.of("Camila", "Sibila");
        UnoGame game = new UnoGame(players, deck);

        int cardsBefore = deck.cards.size();
        game.placeCard(new NumberCard("red", 2));

        AddCardsCamilaAndSibila(game);

        game.playCard("Camila", camilaCards.get(5));
        
        assertEquals("Camila", game.getCurrentPlayer());
        assertEquals(9, game.getHands().get("Sibila").size());
        assertEquals(cardsBefore - 2, deck.cards.size());
    }

    @Test
    void TestPlayerCanPlayDrawTwoCardOverReverseCard() {
        UnoGame game = GameNumberCard(List.of("Camila", "Sibila"), "red");
        game.addCardsToPlayer("Camila", camilaCards);
        game.playCard("Camila", camilaCards.get(6));
        game.playCard("Camila", camilaCards.get(5));
        assertEquals("Camila", game.getCurrentPlayer());
    }

    @Test
    void TestPlayerCanPlayWildCardOverNumberCard() {
        UnoGame game = GameNumberCard(List.of("Camila", "Sibila"), "red");
        AddCardsCamilaAndSibila(game);
        game.playCard("Camila", getWildCard(camilaCards, 8, "Green"));
        assertEquals("Sibila", game.getCurrentPlayer());
    }

    @Test
    void TestPlayerCanPlayDrawTwoCardOverWildCard() {
        UnoGame game = GameNumberCard(List.of("Camila", "Sibila"), "Green");
        AddCardsCamilaAndSibila(game);
        game.playCard("Camila", getWildCard(camilaCards, 8, "red"));
        game.playCard("Sibila", sibilaCards.get(4));
        assertEquals("Sibila", game.getCurrentPlayer());
    }

    @Test
    void TestPlayerCanPlayWildCardOverWildCardAndChangeColorToRed() {
        UnoGame game = GameNumberCard(List.of("Camila", "Sibila"), "red");
        AddCardsCamilaAndSibila(game);
        game.playCard("Camila", getWildCard(camilaCards, 8, "Green"));
        game.playCard("Sibila", getWildCard(sibilaCards, 1, "Blue"));
        assertEquals("Blue", game.topCard().getColor());
    }

    @Test
    void testWildCard() {
        UnoGame game = GameNumberCard(List.of("Camila", "Sibila"), "red");
        game.addCardsToPlayer("Camila", camilaCards);
        game.playCard("Camila", getWildCard(camilaCards, 8, "Green"));
        assertEquals("Green", game.topCard().getColor());
        assertEquals("Sibila", game.getCurrentPlayer());
    }


    @Test
    void testCannotPlayNonMatchingColorAfterWildCard() {
        UnoGame game = GameNumberCard(List.of("Camila", "Sibila"), "red");
        AddCardsCamilaAndSibila(game);
        game.playCard("Camila", getWildCard(camilaCards, 8, "Blue"));
        assertThrows(IllegalStateException.class, () -> game.playCard("Sibila", sibilaCards.get(0)));
    }

    @Test
    void testFourPlayersGame() {
        UnoGame game = createGame(deckCards, "Camila", "Sibila", "Juan", "Pedro");
        game.placeCard(new NumberCard("red", 8));

        AddCardsCamilaAndSibila(game);

        game.addCardsToPlayer("Juan", juanCards);
        game.addCardsToPlayer("Pedro", pedroCards);


        game.playCard("Camila", camilaCards2.get(0));
        assertEquals("Sibila", game.getCurrentPlayer());

        game.playCard("Sibila", sibilaCards.get(0));
        assertEquals("Juan", game.getCurrentPlayer());

        game.playCard("Juan", juanCards.get(0));
        assertEquals("Pedro", game.getCurrentPlayer());

        game.playCard("Pedro", pedroCards.get(0));
        assertEquals("Camila", game.getCurrentPlayer());
    }

    @Test
    void testSkipCardSkipsPlayer() {
        UnoGame game = GameNumberCard(List.of("Camila", "Sibila", "Juan"), "red");
        AddCardsCamilaAndSibila(game);
        game.playCard("Camila", camilaCards.get(7));
        assertEquals("Juan", game.getCurrentPlayer());
    }

    @Test
    void testWinner() {
        UnoGame game = GameNumberCard(List.of("Camila", "Sibila"), "red");
        game.addCardsToPlayer("Camila", camilaCards2);
        game.playCard("Camila", camilaCards2.get(0));
        assertThrows(IllegalStateException.class, () -> game.playCard("Camila", new NumberCard("red", 2)));
    }
    @Test
    void testSibilaCannotPlayAfterGameEnds() {
        UnoGame game = GameNumberCard(List.of("Camila", "Sibila"), "red");
        game.addCardsToPlayer("Camila", camilaCards2);
        game.addCardsToPlayer("Sibila", sibilaCards2);
        game.playCard("Camila", camilaCards2.get(0));
        assertThrows(IllegalStateException.class, () -> game.playCard("Sibila", sibilaCards2.get(0)));
    }


    List<Card> camilaCards = new ArrayList<Card>(Arrays.asList(
            new NumberCard("red", 2),
            new NumberCard("yellow", 2),
            new NumberCard("green", 8),
            new NumberCard("red", 3),
            new NumberCard("Blue", 2),
            new DrawTwoCard("red"),
            new ReverseCard("red"),
            new SkipCards("red"),
            new WildCard()
    ));


    List<Card> sibilaCards = new ArrayList<>(Arrays.asList(
            new NumberCard("red", 3),
            new WildCard(),
            new NumberCard("yellow", 3),
            new NumberCard("green", 5),
            new DrawTwoCard("red"),
            new NumberCard("blue", 7),
            new SkipCards("red")
    ));

    List<Card> sibilaCards2 = new ArrayList<>(Arrays.asList(
            new NumberCard("red", 3)
    ));

    List<Card> camilaCards2 = new ArrayList<>(Arrays.asList(
            new NumberCard("red", 2)
    ));


    List<Card> deckCards = new ArrayList<>(Arrays.asList(
            new NumberCard("red", 9),
            new NumberCard("green", 6),
            new NumberCard("yellow", 5),
            new NumberCard("blue", 4)
    ));

List<Card> juanCards = new ArrayList<>(Arrays.asList(
            new NumberCard("red", 2),
            new NumberCard("green", 3),
            new NumberCard("yellow", 4),
            new NumberCard("blue", 5)
    ));

    List<Card> pedroCards = new ArrayList<>(Arrays.asList(
            new NumberCard("red", 3),
            new NumberCard("green", 4),
            new NumberCard("yellow", 5),
            new NumberCard("blue", 6)
    ));

    private UnoGame createGame(List<Card> deckCards, String... playerNames) {
        Deck deck = new Deck(deckCards);
        List<String> players = Arrays.asList(playerNames);
        return new UnoGame(players, deck);
    }

    private UnoGame GameNumberCard(List<String> Camila, String Colour) {
        Deck deck = new Deck(deckCards);
        UnoGame game = new UnoGame(Camila, deck);
        game.placeCard(new NumberCard(Colour, 2));
        return game;
    }

    private void AddCardsCamilaAndSibila(UnoGame game) {
        game.addCardsToPlayer("Camila", camilaCards);
        game.addCardsToPlayer("Sibila", sibilaCards);
    }

    private WildCard getWildCard(List<Card> cards, int index, String color) {
        WildCard wcard = (WildCard) cards.get(index);
        wcard.setColor(color);
        return wcard;
    }
}