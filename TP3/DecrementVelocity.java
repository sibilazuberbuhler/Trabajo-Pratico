package drone;

public class DecrementVelocity extends Command {
    public void execute(Drone drone) throws DroneException {
        drone.setVelocity(drone.getVelocity().disminuir());
    }
}
