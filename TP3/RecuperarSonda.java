package drone;

public class RecuperarSonda extends Command {
    public void execute(Drone drone) {
        drone.setSondaStatus(SondaStatus.Recuperar);
    }
}
