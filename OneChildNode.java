public class OneChildNode extends Node {
    public Token operator;
    public Node nodeRight;

    public OneChildNode(Token operator, Node nodeRight) {
        this.operator = operator;
        this.nodeRight = nodeRight;
    }

    @Override
    public double berechnen() {
        double valueRight = nodeRight.berechnen();

        switch (operator.zustand) {
            case PLUS:
                return valueRight;
            case MINUS:
                return -valueRight;
            default:
                throw new RuntimeException("Invalid operator type.");
        }
    }

    @Override
    public String printSyntaxTree() {
        if(nodeRight instanceof NumberNode){
            double valueRight = nodeRight.berechnen();
            return "OneChildNode{" +
                "operator=" + operator.value +
                ", nodeRight=" + valueRight +
                "}";
                }
        else {
            return "OneChildNode{" +
                "operator=" + operator.value +
                ", nodeRight=" + nodeRight +
                "}";
                }
    }
}