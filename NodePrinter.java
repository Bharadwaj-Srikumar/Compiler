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
        else if (node instanceof OneChildNode) {
            OneChildNode OneChildNode = (OneChildNode) node;
            System.out.println(indent + OneChildNode.operator.value);
            print(OneChildNode.nodeRight, indent + "\t");
        } 
        else if (node instanceof TwoChildNode) {
            TwoChildNode TwoChildNode = (TwoChildNode) node;
            System.out.println(indent + TwoChildNode.operator.value);
            print(TwoChildNode.nodeLeft, indent + "\t");
            print(TwoChildNode.nodeRight, indent + "\t");
        }
    }
}