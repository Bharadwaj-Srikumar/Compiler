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
    public String toString() {
        if(nodeRight instanceof NumberNode && nodeLeft instanceof NumberNode){
            double valueLeft = ((NumberNode)nodeLeft).getnodeValue();
            double valueRight = ((NumberNode)nodeRight).getnodeValue();
            return "{" +
                "leftChild=" + valueLeft +
                ", operator=" + operator.value +
                ", rightChild=" + valueRight +
                "}";
        }
        else if(nodeRight instanceof NumberNode) {
            double valueRight = ((NumberNode)nodeRight).getnodeValue();
            return "{" +
                "leftChild=" + nodeLeft +
                ", operator=" + operator.value +
                ", rightChild=" + valueRight +
                "}";
        }
        else if(nodeLeft instanceof NumberNode) {
            double valueLeft = ((NumberNode)nodeRight).getnodeValue();
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