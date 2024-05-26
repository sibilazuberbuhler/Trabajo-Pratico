package drone;

public abstract class Direction {

    String key;

    public abstract Direction getRight(Drone drone);

    public abstract Direction getLeft(Drone drone);

    public abstract void moveForwards(Drone drone);

    public abstract String getKey();
}