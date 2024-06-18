package unogame;

public class ReverseCard extends NumberlessCard {
    public ReverseCard(String color) {
        super.color = color;
    }

    public boolean canAccept(Card nextCard) {
        return nextCard.matchesColor(color) || nextCard instanceof ReverseCard || nextCard instanceof WildCard;
    }

    @Override
    public void bePlayed(UnoGame game) {
        game.placeCard(this);
        game.reverseOrder();
    }
}
