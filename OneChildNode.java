// OneChildNode consists nodeRight-node and unary-operator-node
// overrides the evaluate() function
// performs operation of operator type
// (for negative Numbers)
public class OneChildNode extends Node {
    public Token operator;
    public Node nodeRight;

    public OneChildNode(Token operator, Node nodeRight) {
        this.operator = operator;
        this.nodeRight = nodeRight;
    }

    @Override
    public double evaluate() {
        double valueRight = nodeRight.evaluate();

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
    public String toString() {
        if(nodeRight instanceof ValueNode){
            double valueRight = nodeRight.evaluate();
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