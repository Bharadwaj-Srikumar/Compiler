public class NumberNode extends Node {
    public Token value;

    public NumberNode(Token value) {
        this.value = value;
    }

    @Override
    public double berechnen() {
        return Double.parseDouble(value.value);
    }

    @Override
    public String printSyntaxTree() {
        return value.toString();
    }
}