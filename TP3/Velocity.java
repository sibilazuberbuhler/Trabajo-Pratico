package drone;

public class Velocity {
    private int valor;

    Velocity(int baseVelocity) {
        this.valor = baseVelocity;
    }

    Velocity() {
        this.valor = 0;
    }

    public Velocity incrementar() {
        return new Velocity(this.valor + 1);
    }

    public Velocity disminuir() {
        return new Velocity(this.valor - 1);
    }

    public int getValor() {
        return valor;
    }
}


