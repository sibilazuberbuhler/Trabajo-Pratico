package unogame;

public class SkipCards extends NumberlessCard {
    public SkipCards(String color) {
        super.color = color;
    }

    public boolean canAccept(Card nextCard) {
        return nextCard.matchesColor(color) || nextCard instanceof SkipCards || nextCard instanceof WildCard;
    }

    @Override
    public void bePlayed(UnoGame game) {
        game.placeCard(this);
        game.nextPlayer();
        game.nextPlayer();
    }
}




