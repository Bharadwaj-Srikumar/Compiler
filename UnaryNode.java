// UnaryNode consists right-node and unary-operator-node
// overrides the evaluate() function
// performs operation of operator type
// (for negative Numbers)
public class UnaryNode extends Node {
    public Token operator;
    public Node right;

    public UnaryNode(Token operator, Node right) {
        this.operator = operator;
        this.right = right;
    }

    @Override
    public double evaluate() {
        double rightValue = right.evaluate();

        switch (operator.zustand) {
            case PLUS:
                return rightValue;
            case MINUS:
                return -rightValue;
            default:
                throw new RuntimeException("Invalid operator type.");
        }
    }

    @Override
    public String toString() {
        if(right instanceof ValueNode){
            double rightvalue = right.evaluate();
            return "UnaryNode{" +
                "operator=" + operator.value +
                ", right=" + rightvalue +
                "}";
                }
        else {
            return "UnaryNode{" +
                "operator=" + operator.value +
                ", right=" + right +
                "}";
                }
    }
}