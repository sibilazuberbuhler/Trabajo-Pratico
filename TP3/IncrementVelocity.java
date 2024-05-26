package drone;

public class IncrementVelocity extends Command {
    public void execute(Drone drone) throws DroneException {
        drone.setVelocity(drone.getVelocity().incrementar());
    }
}
