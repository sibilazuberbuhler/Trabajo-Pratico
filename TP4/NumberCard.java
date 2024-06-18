package unogame;

public class NumberCard extends Card {

    public NumberCard(String color, int number) {
        super.color = color;
        this.value = number;
    }

    @Override
    public boolean canAccept(Card nextCard) {
        return nextCard.matchesColor(this.color) || nextCard.matchesNumber(this.value) || nextCard instanceof WildCard;
    }

    @Override
    public void bePlayed(UnoGame game) {
        game.placeCard(this);
        game.nextPlayer();
    }

    @Override 
    public boolean matchesNumber(int number) {
        return this.value == number;
    }
}
