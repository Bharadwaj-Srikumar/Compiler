public class TwoChildNode extends Node {
    public Node nodeLeft;
    public Token operator;
    public Node nodeRight;

    public TwoChildNode(Node nodeLeft, Token operator, Node nodeRight) {
        this.nodeLeft = nodeLeft;
        this.operator = operator;
        this.nodeRight = nodeRight;
    }

    @Override
    public double berechnen() {
        double valueLeft = nodeLeft.berechnen();
        double valueRight = nodeRight.berechnen();

        switch (operator.zustand) {
            case PLUS:
                return valueLeft + valueRight;
            case MINUS:
                return valueLeft - valueRight;
            case MULT:
                return valueLeft * valueRight;
            case DIV:
                return valueLeft / valueRight;
            default:
                throw new RuntimeException("Invalid operator type.");
        }
    }

    @Override
    public String printSyntaxTree() {
        if(nodeRight instanceof NumberNode && nodeLeft instanceof NumberNode){
            double valueLeft = nodeLeft.berechnen();
            double valueRight = nodeRight.berechnen();
            return "{" +
                "leftChild=" + valueLeft +
                ", operator=" + operator.value +
                ", rightChild=" + valueRight +
                "}";
        }
        else if(nodeRight instanceof NumberNode) {
            double valueRight = nodeRight.berechnen();
            return "{" +
                "leftChild=" + nodeLeft +
                ", operator=" + operator.value +
                ", rightChild=" + valueRight +
                "}";
        }
        else if(nodeLeft instanceof NumberNode) {
            double valueLeft = nodeRight.berechnen();
            return "{" +
                "leftChild=" + valueLeft +
                ", operator=" + operator.value +
                ", rightChild=" + nodeRight +
                "}";
        }
        else {
            return "{" +
                "leftChild=" + nodeLeft +
                ", operator=" + operator.value +
                ", rightChild=" + nodeRight +
                "}";
        }
    }
}