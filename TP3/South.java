package drone;

public class South extends Direction {

    public South() {
        this.key = "S";
    }

    public Direction getRight(Drone drone) {
        return new West();
    }

    public Direction getLeft(Drone drone) {
        return new East();
    }

    public void moveForwards(Drone drone) {
        drone.y -= drone.getVelocity().getValor();
    }

    public String getKey() {
        return this.key;
    }

}
