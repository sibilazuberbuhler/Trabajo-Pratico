package drone;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DroneTests {

    @Test
    public void testEastCanBeCreated() {East east = new East();}

    @Test
    public void testWestCanBeCreated() {West west = new West();}

    @Test
    public void testNorthCanBeCreated() {North north = new North();}

    @Test
    public void testSouthCanBeCreated() {South south = new South();}

    @Test
    public void testLeftCanBeCreated() {Command c = new Left();}

    @Test
    public void testIncrementVelocityCanBeCreated() {
        Command c = new IncrementVelocity();
    }

    @Test
    public void testDeployCanBeCreated() {
        Command c = new DeploySonda();
    }

    @Test
    public void testDroneCanBeCreatedLookingNorthNotDeployed() {
        Drone drone = createDrone(new North(), new Velocity(), SondaStatus.Recuperar);
    }

    @Test
    public void testDroneCanBeCreatedLookingEastNotDeployed() {
        Drone drone = createDrone(new East(), new Velocity(), SondaStatus.Recuperar);
    }

    @Test
    public void testDroneCanBeCreatedLookingSouthDeployed() {
        Drone drone = createDrone(new South(), new Velocity(), SondaStatus.Desplegada);
    }

    @Test
    public void testDroneCanBeCreatedLookingWestDeployed() {
        Drone drone = createDrone(new West(), new Velocity(), SondaStatus.Desplegada);
    }

    @Test
    public void testRotateLeftWithVelocity0DoesntMove() throws DroneException {
        Drone drone = createDrone(new North(), new Velocity(), SondaStatus.Recuperar);

        execute(CommandParser.parse("l"), drone);
        AssertComplete(drone, 0, 0);
    }

    @Test
    public void testRotateRightWithVelocity0DoesntMove() throws DroneException {
        Drone drone = createDrone(new North(), new Velocity(), SondaStatus.Recuperar);

        execute(CommandParser.parse("r"), drone);
        AssertComplete(drone, 0, 0);
    }

    @Test
    public void testIncrementVelocityLookingNorthMovesNorth() throws DroneException {
        Drone drone = createDrone(new North(), new Velocity(), SondaStatus.Recuperar);

        execute(CommandParser.parse("i"), drone);
        AssertComplete(drone, 0, 1);
    }

    @Test
    public void testIncrementVelocityLookingWestMovesWest() throws DroneException {
        Drone drone = createDrone(new West(), new Velocity(), SondaStatus.Recuperar);

        execute(CommandParser.parse("i"), drone);
        AssertComplete(drone, -1, 0);
    }

    @Test
    public void testIncrementVelocityLookingSouthMovesSouth() throws DroneException {
        Drone drone = createDrone(new South(), new Velocity(), SondaStatus.Recuperar);

        execute(CommandParser.parse("i"), drone);
        AssertComplete(drone, 0, -1);
    }

    @Test
    public void testIncrementVelocityLookingEastMovesEast() throws DroneException {
        Drone drone = createDrone(new East(), new Velocity(), SondaStatus.Recuperar);

        execute(CommandParser.parse("i"), drone);
        AssertComplete(drone, 1, 0);
    }

    @Test
    public void testIncrementVelocityAfterRotateWestMovesWest() throws DroneException {
        Drone drone = createDrone(new North(), new Velocity(), SondaStatus.Recuperar);

        execute(CommandParser.parse("li"), drone);
        AssertComplete(drone, -1, 0);
    }

    @Test
    public void testIncrementVelocityAfterRotateSouthMovesSouth() throws DroneException {
        Drone drone = createDrone(new North(), new Velocity(), SondaStatus.Recuperar);

        execute(CommandParser.parse("lli"), drone);
        AssertComplete(drone, 0, -1);
    }

    @Test
    public void testIncrementVelocityAfterRotateEastMovesEast() throws DroneException {
        Drone drone = createDrone(new North(), new Velocity(), SondaStatus.Recuperar);

        execute(CommandParser.parse("llli"), drone);
        AssertComplete(drone, 1, 0);
    }

    @Test
    public void testIncrementVelocityAfterRotateNorthMovesEast() throws DroneException {
        Drone drone = createDrone(new North(), new Velocity(), SondaStatus.Recuperar);

        execute(CommandParser.parse("lllli"), drone);
        AssertComplete(drone, 0, 1);
    }

    @Test
    public void testDeployedProbeProhibitsMove() {
        assertThrows(DroneException.class, () -> execute(CommandParser.parse("i"), createDrone(new North(), new Velocity(), SondaStatus.Desplegada)));
    }

    @Test
    public void testIncrementVelocityTwiceLookingNorthMovesNorthTwice() throws DroneException {
        Drone drone = createDrone(new North(), new Velocity(), SondaStatus.Recuperar);

        execute(CommandParser.parse("ii"), drone);
        AssertComplete(drone, 0, 3);
    }

    @Test
    public void testIncrementVelocityTwiceLookingEastMovesEastTwice() throws DroneException {
        Drone drone = createDrone(new East(), new Velocity(), SondaStatus.Recuperar);

        execute(CommandParser.parse("ii"), drone);
        AssertComplete(drone, 3, 0);
    }

    @Test
    public void testDecrementVelocityLookingNorthMovesSouth() throws DroneException {
        Drone drone = createDrone(new North(), new Velocity(), SondaStatus.Recuperar);

        execute(CommandParser.parse("s"), drone);
        AssertComplete(drone, 0, -1);
    }

    @Test
    public void testRotateWithProbeDeployedThrowsException() {
        assertThrows(DroneException.class, () -> execute(CommandParser.parse("l"), createDrone(new North(), new Velocity(), SondaStatus.Desplegada)));
    }

    @Test
    public void testMoveWithProbeDeployedThrowsException() {
        assertThrows(DroneException.class, () -> execute(CommandParser.parse("i"), createDrone(new North(), new Velocity(), SondaStatus.Desplegada)));
    }

    @Test
    public void testReverseVelocityMovesOppositeDirection() throws DroneException {
        Drone drone = createDrone(new North(), new Velocity(), SondaStatus.Recuperar);

        execute(CommandParser.parse("s"), drone);
        AssertComplete(drone, 0, -1);
    }

    @Test
    public void testDeployedProbeThenStoreAndMove() {
        assertDoesNotThrow(() -> execute(CommandParser.parse("fi"), createDrone(new North(), new Velocity(), SondaStatus.Desplegada)));
    }

    @Test
    public void testDeployedProbeThenStoreAndRotate() {
        assertDoesNotThrow(() -> execute(CommandParser.parse("fl"), createDrone(new North(), new Velocity(), SondaStatus.Desplegada)));
    }

    @Test
    public void testRetrievedProbeThenDeployAndTryToMove() {
        assertThrows(DroneException.class, () -> execute(CommandParser.parse("di"), createDrone(new North(), new Velocity(), SondaStatus.Recuperar)));
    }

    @Test
    public void testRetrievedProbeThenDeployAndTryToRotate() {
        assertThrows(DroneException.class, () -> execute(CommandParser.parse("dl"), createDrone(new North(), new Velocity(), SondaStatus.Recuperar)));
    }

    private static void AssertComplete(Drone drone, int expectedX, int expectedY) {
        assertEquals(drone.x, expectedX);
        assertEquals(drone.y, expectedY);
    }

    private Drone createDrone(Direction direction, Velocity velocity, SondaStatus sondaStatus) {
        return new Drone(direction, velocity, sondaStatus);
    }

    private void execute(List<Command> commands, Drone drone) throws DroneException {
        for (Command command : commands) {
            command.execute(drone);
        }
    }

}
