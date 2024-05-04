import java.util.ArrayList;
import java.util.List;

public class ZwischenCode {
    private Node root;
    private List<String> intermediateCode;

    public ZwischenCode(Node root) {
        this.root = root;
        this.intermediateCode = new ArrayList<>();
    }

    public List<String> generate() {
        generate(root);
        return intermediateCode;
    }

    private void generate(Node node) {
        if (node instanceof ValueNode) {
            ValueNode valueNode = (ValueNode) node;
            intermediateCode.add("PUSH" + "\t\t" + valueNode.value.value);
        } else if (node instanceof UnaryNode) {
            UnaryNode unaryNode = (UnaryNode) node;
            generate(unaryNode.right);
            intermediateCode.add("OPERATOR" + "\t" + unaryNode.operator.value);
        } else if (node instanceof BinaryNode) {
            BinaryNode binaryNode = (BinaryNode) node;
            generate(binaryNode.left);
            generate(binaryNode.right);
            intermediateCode.add("OPERATOR" + "\t" + binaryNode.operator.value);
        } 
    }

    public void print() {
        for (String line : intermediateCode) {
            System.out.println(line);
        }
    }
}