package anillo;

public class NodeNull extends Node {
    public Object get_cargo() {
        throw new IllegalStateException("Vacio");
    }

    public Node get_next() {
        throw new IllegalStateException("No hay siguiente");
    }

    public Node connect(Object cargo) {
        NodeCargo newNode = new NodeCargo(cargo);
        newNode.next = newNode;
        return newNode;
    }

    public void disconnect() {
        throw new IllegalStateException("Vacio");
    }

}
