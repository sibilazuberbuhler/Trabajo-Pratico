package unogame;

public class WildCard extends NumberlessCard {
    public boolean canAccept(Card nextCard) {
        if (super.color == null) {
            throw new IllegalStateException("La carta de cambio de color debe tener un color antes de jugar otra");
        }
        return nextCard.matchesColor(color) || nextCard instanceof WildCard;
    }

    @Override
    public void bePlayed(UnoGame game) {
        if (super.color == null) {
            throw new IllegalStateException("La carta de cambio de color debe tener un color antes de ser jugada");
        }
        game.placeCard(this);
        game.nextPlayer();
    }

    public void setColor(String chosenColor) {
        if (this.color != null) {
            throw new IllegalStateException("La carta no debe tener un color hasta que el jugador elige uno");
        }
        this.color = chosenColor;
    }
}

