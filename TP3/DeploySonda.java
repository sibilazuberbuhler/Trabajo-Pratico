package drone;

public class DeploySonda extends Command {
    public void execute(Drone drone) {
        drone.setSondaStatus(SondaStatus.Desplegada);
    }
}
