// BinaryNode consists left-node, right-node and binary-operator-node 
// overrides the evaluate() function 
// performs operation of operator type
public class BinaryNode extends Node {
    public Node left;
    public Token operator;
    public Node right;

    public BinaryNode(Node left, Token operator, Node right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    @Override
    public double evaluate() {
        double leftValue = left.evaluate();
        double rightValue = right.evaluate();

        switch (operator.zustand) {
            case PLUS:
                return leftValue + rightValue;
            case MINUS:
                return leftValue - rightValue;
            case MULT:
                return leftValue * rightValue;
            case DIV:
                return leftValue / rightValue;
            default:
                throw new RuntimeException("Invalid operator type.");
        }
    }

    @Override
    public String toString() {
        if(right instanceof ValueNode && left instanceof ValueNode){
            double leftvalue = left.evaluate();
            double rightvalue = right.evaluate();
            return "{" +
                "left=" + leftvalue +
                ", operator=" + operator.value +
                ", right=" + rightvalue +
                "}";
        }
        else if(right instanceof ValueNode) {
            double rightvalue = right.evaluate();
            return "{" +
                "left=" + left +
                ", operator=" + operator.value +
                ", right=" + rightvalue +
                "}";
        }
        else if(left instanceof ValueNode) {
            double leftvalue = right.evaluate();
            return "{" +
                "left=" + leftvalue +
                ", operator=" + operator.value +
                ", right=" + right +
                "}";
        }
        else {
            return "{" +
                "left=" + left +
                ", operator=" + operator.value +
                ", right=" + right +
                "}";
        }
    }
}