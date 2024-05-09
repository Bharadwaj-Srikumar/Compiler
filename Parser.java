import java.util.ArrayList;

public class Parser {
    private ArrayList<Token> tokens  = new ArrayList<Token>();;
    private int current = 0;

    public Parser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public Node parse() {
        Node node = expression();
        return node;
    }

    private Node expression() {
        Node nodeLeft = term();
        while (match(TYPE.PLUS, TYPE.MINUS)) {
            Token operator = tokens.get(current - 1);
            Node nodeRight = term();
            nodeLeft = new TwoChildNode(nodeLeft, operator, nodeRight);
        }
        return nodeLeft;
    }

    private Node term() {
        Node nodeLeft = factor();
        while (match(TYPE.MULT, TYPE.DIV)) {
            Token operator = tokens.get(current - 1);
            Node nodeRight = factor();
            nodeLeft = new TwoChildNode(nodeLeft, operator, nodeRight);
        }
        return nodeLeft;
    }

   private Node factor() {
        if (match(TYPE.PLUS, TYPE.MINUS)) {
            Token operator = tokens.get(current - 1);
            Node nodeRight = factor();
            Node node = new OneChildNode(operator, nodeRight);
            return node;
        } 
        else if (match(TYPE.ZAHL)) {
            Token value = tokens.get(current - 1);
            NumberNode vnode = new NumberNode(value);
            return vnode;
        } 
        else if (match(TYPE.OPEN_PAR)) {
            Node expression = this.expression();
            verarbeiten(TYPE.CLOSE_PAR, "Expect ')' after expression.");
            return expression;
        }
        else { 
            throw new RuntimeException("Expect expression, but found " + tokens.get(current).zustand + "");
        }
    }

    private boolean match(TYPE... types) {
        for (TYPE type : types) {
            if (pruefeZustand(type)) {
                gotoNextToken();
                return true;
            }
        }
        return false;
    }

    private Token verarbeiten(TYPE type, String message) {
        if (pruefeZustand(type))
            return gotoNextToken();
        throw new RuntimeException(message);
    }

    private boolean pruefeZustand(TYPE type) {
        if (current==tokens.size())
            return false;
        return tokens.get(current).zustand == type;
    }

    private Token gotoNextToken() {
        if (!(current==tokens.size()))
            current++;
            return tokens.get(current - 1);
    }


}