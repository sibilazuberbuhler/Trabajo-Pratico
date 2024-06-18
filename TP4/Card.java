package unogame;

public abstract class Card {
    protected String color;
    protected int value;

    abstract public void bePlayed(UnoGame unogame);
    abstract public boolean canAccept(Card nextCard);

    public String getColor() {
        return this.color;
    }

    public int getNumber() {

        return this.value;
    }

    public boolean matchesColor(String color) {
        return this.color.equals(color);
    }

    public abstract boolean matchesNumber(int number);
}
