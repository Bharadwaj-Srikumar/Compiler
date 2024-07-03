public class OneChildNode extends Node {
    public Token operator;
    public Node nodeRight;

    public OneChildNode(Token operator, Node nodeRight) {
        this.operator = operator;
        this.nodeRight = nodeRight;
    }


    @Override
    public String toString() {
        if(nodeRight instanceof NumberNode){
            double valueRight = ((NumberNode)nodeRight).getnodeValue();
            return "OneChildNode{" +
                "operator=" + operator.value +
                ", rightChild=" + valueRight +
                "}";
                }
        else {
            return "OneChildNode{" +
                "operator=" + operator.value +
                ", rightChild=" + nodeRight +
                "}";
                }
    }
}