package drone;

public class Left extends Command {

    public void execute(Drone drone) throws DroneException {
        drone.setDirection(drone.getDirection().getLeft(drone));
    }
}
