public class NumberNode extends Node {
    public Token value;

    public NumberNode(Token value) {
        this.value = value;
    }

    public double getnodeValue() {
        return Double.parseDouble(value.value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}