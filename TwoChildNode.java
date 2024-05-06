// TwoChildNode consists nodeLeft-node, nodeRight-node and binary-operator-node 
// overrides the evaluate() function 
// performs operation of operator type
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
    public double evaluate() {
        double valueLeft = nodeLeft.evaluate();
        double valueRight = nodeRight.evaluate();

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
    public String toString() {
        if(nodeRight instanceof ValueNode && nodeLeft instanceof ValueNode){
            double valueLeft = nodeLeft.evaluate();
            double valueRight = nodeRight.evaluate();
            return "{" +
                "nodeLeft=" + valueLeft +
                ", operator=" + operator.value +
                ", nodeRight=" + valueRight +
                "}";
        }
        else if(nodeRight instanceof ValueNode) {
            double valueRight = nodeRight.evaluate();
            return "{" +
                "nodeLeft=" + nodeLeft +
                ", operator=" + operator.value +
                ", nodeRight=" + valueRight +
                "}";
        }
        else if(nodeLeft instanceof ValueNode) {
            double valueLeft = nodeRight.evaluate();
            return "{" +
                "nodeLeft=" + valueLeft +
                ", operator=" + operator.value +
                ", nodeRight=" + nodeRight +
                "}";
        }
        else {
            return "{" +
                "nodeLeft=" + nodeLeft +
                ", operator=" + operator.value +
                ", nodeRight=" + nodeRight +
                "}";
        }
    }
}