package drone;

public abstract class Command {
    public abstract void execute(Drone drone) throws DroneException;
}


