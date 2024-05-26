package drone;

public class West extends Direction {

    public West() {
        this.key = "W";
    }

    public Direction getRight(Drone drone) {
        return new North();
    }

    public Direction getLeft(Drone drone) {
        return new South();
    }

    public void moveForwards(Drone drone) {
        drone.x -= drone.getVelocity().getValor();
    }
    public String getKey() {
        return this.key;
    }

}