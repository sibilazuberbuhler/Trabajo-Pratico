package unogame;

public abstract class NumberlessCard extends Card {
    @Override 
    public int getNumber() {
        throw new UnsupportedOperationException("Esta carta no tiene numero");
    }
    
    @Override
    public boolean matchesNumber(int number) {
        return false;
    }
}
