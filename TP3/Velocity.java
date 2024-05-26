//package Drone;
//
//public class Velocity {
//    public static final Velocity MIN = new Velocity("MIN");
//    public static final Velocity LOW = new Velocity("LOW");
//    public static final Velocity MEDIUM = new Velocity("MEDIUM");
//    public static final Velocity HIGH = new Velocity("HIGH");
//    public static final Velocity MAX = new Velocity("MAX");
//
//    private final String name;
//
//    private Velocity(String name) {
//        this.name = name;
//    }
//
//    public String getName() {
//        return this.name;
//    }
//}
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


