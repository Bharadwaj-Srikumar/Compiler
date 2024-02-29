// ValueNode consists value-node
// overrides the evaluate() function
// returns value of value-node

public class ValueNode extends Node {
    public Token value;

    public ValueNode(Token value) {
        this.value = value;
    }

    @Override
    public double evaluate() {
        return Double.parseDouble(value.value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}