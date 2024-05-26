package drone;

public class Drone {
    private Direction direction;
    private Velocity velocity;
    private SondaStatus sondaStatus;
    public int x;
    public int y;

    public Drone(Direction direction, Velocity velocity, SondaStatus sondaStatus) {
        this.direction = direction;
        this.velocity = velocity;
        this.sondaStatus = sondaStatus;
    }


    public Direction getDirection() {
        return this.direction;
    }

    public Velocity getVelocity() {
        return this.velocity;
    }

    public SondaStatus getSondaStatus() {
        return this.sondaStatus;
    }

    private void move() throws DroneException {
        if (this.getSondaStatus() == SondaStatus.Desplegada) {
            throw new DroneException();
        }
        this.direction.moveForwards(this);
    }

    public void setDirection(Direction direction) throws DroneException {
        this.direction = direction;
        this.move();
    }

    public void setVelocity(Velocity velocity) throws DroneException {
        this.velocity = velocity;
        this.move();
    }

    public void setSondaStatus(SondaStatus sondaStatus) {
        this.sondaStatus = sondaStatus;
    }
}