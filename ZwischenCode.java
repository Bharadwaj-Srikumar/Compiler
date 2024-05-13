import java.util.ArrayList;
import java.util.List;

public class ZwischenCode {
    // Deklaration von Attributen von Type Node und eine Liste von Typ String.
    private Node parentNode;
    private List<String> zwischenCode;

    // Konstruktur zum Initialisieren des Baumes und Liste.
    public ZwischenCode(Node root) {
        this.parentNode = root;
        this.zwischenCode = new ArrayList<>();
    }
    // Die Methode gibt eine Liste zwischenCode von type String zurück.
    public List<String> generate() {
        erzeugen(parentNode);
        return zwischenCode;
    }
    //
    private void erzeugen(Node node) {
        if (node instanceof NumberNode) {
            NumberNode numberNode = (NumberNode) node;
            zwischenCode.add("PUSH" + "\t\t" + numberNode.value.value);
        } else if (node instanceof OneChildNode) {
            OneChildNode OneChildNode = (OneChildNode) node;
            erzeugen(OneChildNode.nodeRight);
            zwischenCode.add("OPERATOR" + "\t" + OneChildNode.operator.value);
        } else if (node instanceof TwoChildNode) {
            TwoChildNode TwoChildNode = (TwoChildNode) node;
            erzeugen(TwoChildNode.nodeLeft);
            erzeugen(TwoChildNode.nodeRight);
            zwischenCode.add("OPERATOR" + "\t" + TwoChildNode.operator.value);
        } 
    }

    public void print() {
        for (String line : zwischenCode) {
            System.out.println(line);
        }
    }
}