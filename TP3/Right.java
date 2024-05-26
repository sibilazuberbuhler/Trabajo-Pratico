package drone;

public class Right extends Command {

    public void execute(Drone drone) throws DroneException {
        drone.setDirection(drone.getDirection().getRight(drone));
    }
}
