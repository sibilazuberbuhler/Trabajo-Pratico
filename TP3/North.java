package drone;

public class North extends Direction {

    public North() {
        this.key = "N";
    }

    public Direction getRight(Drone drone) {
        return new East();
    }

    public Direction getLeft(Drone drone) {
        return new West();
    }

    public void moveForwards(Drone drone) {
        drone.y += drone.getVelocity().getValor();
    }

    public String getKey() {
        return this.key;
    }

}