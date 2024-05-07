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
        } else if (node instanceof OneChildNode) {
            OneChildNode OneChildNode = (OneChildNode) node;
            generate(OneChildNode.nodeRight);
            intermediateCode.add("OPERATOR" + "\t" + OneChildNode.operator.value);
        } else if (node instanceof TwoChildNode) {
            TwoChildNode TwoChildNode = (TwoChildNode) node;
            generate(TwoChildNode.nodeLeft);
            generate(TwoChildNode.nodeRight);
            intermediateCode.add("OPERATOR" + "\t" + TwoChildNode.operator.value);
        } 
    }

    public void print() {
        for (String line : intermediateCode) {
            System.out.println(line);
        }
    }
}