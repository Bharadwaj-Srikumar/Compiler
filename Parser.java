// import java.util.List;
import java.util.ArrayList;

public class Parser {
    private ArrayList<Token> tokens  = new ArrayList<Token>();;
    private int current = 0;

    public Parser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    /**parse(): This is the entry point of the parser. 
     * It calls the expression() method, which starts the parsing process. 
     * It returns a Node object, which is the result of parsing the input tokens. */
    public Node parse() {
        Node node = expression();
        return node;
    }

    /**expression(): This method is used to parse expressions in the input. 
     * It starts by calling the term() method to get the nodeLeft operand, then it looks for any plus or minus operators, 
     * and if found, it calls the term() method again to get the nodeRight operand. 
     * It then creates a new TwoChildNode object with the nodeLeft and nodeRight operands and the operator and 
     * sets it as the nodeLeft operand for the next iteration. 
     * It returns the final Node object that represents the parsed expression. */
    private Node expression() {
        Node nodeLeft = term();
        while (match(TYPE.PLUS, TYPE.MINUS)) {
            Token operator = getPreviousToken();
            Node nodeRight = term();
            nodeLeft = new TwoChildNode(nodeLeft, operator, nodeRight);
        }
        return nodeLeft;
    }

    /**term(): This method is used to parse terms in the input. 
     * It works similar to the expression() method but it looks for any multiply or divide operators. */
    private Node term() {
        Node nodeLeft = factor();
        while (match(TYPE.MULT, TYPE.DIV)) {
            Token operator = getPreviousToken();
            Node nodeRight = factor();
            nodeLeft = new TwoChildNode(nodeLeft, operator, nodeRight);
        }
        return nodeLeft;
    }

    /**factor(): This method is used to parse factors in the input. 
     * It starts by checking if the current token is a unary operator (plus or minus), if yes, 
     * it calls the factor() method again to get the nodeRight operand and creates a new OneChildNode object with the operator 
     * and the nodeRight operand. If the current token is an integer or float, it creates a new ValueNode object with the 
     * token's value. If the current token is a nodeLeft parenthesis, it calls the expression() method to get the 
     * expression inside the parenthesis and returns it. */
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