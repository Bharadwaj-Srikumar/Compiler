// import java.util.List;
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
            Token operator = getPreviousToken();
            Node nodeRight = term();
            nodeLeft = new TwoChildNode(nodeLeft, operator, nodeRight);
        }
        return nodeLeft;
    }

    private Node term() {
        Node nodeLeft = factor();
        while (match(TYPE.MULT, TYPE.DIV)) {
            Token operator = getPreviousToken();
            Node nodeRight = factor();
            nodeLeft = new TwoChildNode(nodeLeft, operator, nodeRight);
        }
        return nodeLeft;
    }

   private Node factor() {
        if (match(TYPE.PLUS, TYPE.MINUS)) {
            Token operator = getPreviousToken();
            Node nodeRight = factor();
            Node node = new OneChildNode(operator, nodeRight);
            return node;
        } 
        else if (match(TYPE.ZAHL)) {
            Token value = getPreviousToken();
            ValueNode vnode = new ValueNode(value);
            return vnode;
        } 
        else if (match(TYPE.OPEN_PAR)) {
            Node expression = this.expression();
            consume(TYPE.CLOSE_PAR, "Expect ')' after expression.");
            return expression;
        }
        else { 
            throw new RuntimeException("Expect expression, but found " + getCurrentToken().zustand + "");
        }
    }

    private boolean match(TYPE... types) {
        for (TYPE type : types) {
            if (check(type)) {
                advance();
                return true;
            }
        }
        return false;
    }

    private Token consume(TYPE type, String message) {
        if (check(type))
            return advance();
        throw new RuntimeException(message);
    }

    private boolean check(TYPE type) {
        if (isEndToken())
            return false;
        return getCurrentToken().zustand == type;
    }

    private Token advance() {
        if (!isEndToken())
            current++;
            return getPreviousToken();
    }

    private boolean isEndToken() {
        return current==tokens.size();
    }

    private Token getCurrentToken() {
        return tokens.get(current);
    }

    private Token getPreviousToken() {
        return tokens.get(current - 1);
    }
}