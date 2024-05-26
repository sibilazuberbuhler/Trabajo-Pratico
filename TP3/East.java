package drone;

public class East extends Direction {
    public Direction getRight(Drone drone) {
        return new South();
    }

    public Direction getLeft(Drone drone) {
        return new North();
    }

    public void moveForwards(Drone drone) {
        drone.x += drone.getVelocity().getValor();
    }

    public String getKey() {
        return this.key;
    }

}