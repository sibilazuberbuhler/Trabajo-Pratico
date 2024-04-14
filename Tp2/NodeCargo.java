package anillo;

public class NodeCargo extends Node{
    Object cargo;
    NodeCargo next;

    NodeCargo(Object cargo) {
        this.cargo = cargo;
        this.next = null;
    }

    public Object get_cargo() {
        return this.cargo;
    }

    public Node get_next() {
        return this.next;
    }

    public Node connect(Object cargo) {
        NodeCargo previousNode = this.getPreviousNode();
        NodeCargo newNode = new NodeCargo(cargo);
        previousNode.next = newNode;
        newNode.next = this;
        return newNode;
    }

    public void disconnect() {
        NodeCargo previousNode = this.getPreviousNode();
        NodeCargo nextNode = this.next;
        previousNode.next = nextNode;
    }

    private NodeCargo getPrevious(NodeCargo currentNode, NodeCargo previousNode) {
        return (currentNode.next == this) ? currentNode : getPrevious((NodeCargo) currentNode.next, currentNode);
    }
    private NodeCargo getPreviousNode() {
        return getPrevious(this, this);
    }

}
