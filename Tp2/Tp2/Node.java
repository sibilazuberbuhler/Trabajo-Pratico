package anillo;

public abstract class Node{
    public abstract Object get_cargo();

    public abstract Node get_next();

    public abstract Node connect(Object cargo);

    public abstract void disconnect();
}
