package anillo;

public class Ring {
    private Node current;

    public Ring() {
        this.current = new NodeNull();
    }

    public Ring next() {
        Ring nextRing = new Ring();
        nextRing.current = this.current.get_next();
        return nextRing;
    }

    public Object current() {
        return this.current.get_cargo();
    }

    public Ring add(Object cargo) {
        this.current = this.current.connect(cargo);
        return this;
    }

    public Ring remove() {
        if (this.current.get_next() == this.current) {
            this.current = new NodeNull();
            return this;
        } else {
            Ring nextRing = new Ring();
            nextRing.current = this.current.get_next();
            this.current.disconnect();
            return nextRing;
        }
    }
}

