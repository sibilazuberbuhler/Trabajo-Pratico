package unogame;

public class DrawTwoCard extends NumberlessCard {
    public DrawTwoCard(String color) {
        super.color = color;

    }
    @Override
    public boolean canAccept(Card nextCard) {
        return nextCard.matchesColor(color) || nextCard instanceof DrawTwoCard || nextCard instanceof WildCard;
    }
    @Override
    public void bePlayed(UnoGame game) {
        game.placeCard(this);
        game.nextPlayer();
        game.drawCards(2);
        game.nextPlayer();
    }

    @Override 
    public int getNumber() {
        throw new UnsupportedOperationException("Esta carta no tiene numero");
    }

}


