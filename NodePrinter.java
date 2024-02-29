public class NodePrinter {
    private Node root;

    public NodePrinter(Node root) {
        this.root = root;
    }

    public void print() {
        print(root, "");
    }

    private void print(Node node, String indent) {
        if (node instanceof ValueNode) {
            ValueNode valueNode = (ValueNode) node;
            System.out.println(indent + valueNode.value.value);
        } 
        else if (node instanceof UnaryNode) {
            UnaryNode unaryNode = (UnaryNode) node;
            System.out.println(indent + unaryNode.operator.value);
            print(unaryNode.right, indent + "\t");
        } 
        else if (node instanceof BinaryNode) {
            BinaryNode binaryNode = (BinaryNode) node;
            System.out.println(indent + binaryNode.operator.value);
            print(binaryNode.left, indent + "\t");
            print(binaryNode.right, indent + "\t");
        }
    }
}