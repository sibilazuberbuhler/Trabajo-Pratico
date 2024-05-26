package drone;

public class SondaStatus {
    public static final SondaStatus Recuperar = new SondaStatus("Recuperar");
    public static final SondaStatus Desplegada = new SondaStatus("Desplegada");

    private final String name;

    private SondaStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}