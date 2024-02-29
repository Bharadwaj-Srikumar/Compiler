import java.util.List;
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
     * It starts by calling the term() method to get the left operand, then it looks for any plus or minus operators, 
     * and if found, it calls the term() method again to get the right operand. 
     * It then creates a new BinaryNode object with the left and right operands and the operator and 
     * sets it as the left operand for the next iteration. 
     * It returns the final Node object that represents the parsed expression. */
    private Node expression() {
        Node left = term();
        while (match(TYPE.PLUS, TYPE.MINUS)) {
            Token operator = previous();
            Node right = term();
            left = new BinaryNode(left, operator, right);
        }
        return left;
    }

    /**term(): This method is used to parse terms in the input. 
     * It works similar to the expression() method but it looks for any multiply or divide operators. */
    private Node term() {
        Node left = factor();
        while (match(TYPE.MULT, TYPE.DIV)) {
            Token operator = previous();
            Node right = factor();
            left = new BinaryNode(left, operator, right);
        }
        return left;
    }

    /**factor(): This method is used to parse factors in the input. 
     * It starts by checking if the current token is a unary operator (plus or minus), if yes, 
     * it calls the factor() method again to get the right operand and creates a new UnaryNode object with the operator 
     * and the right operand. If the current token is an integer or float, it creates a new ValueNode object with the 
     * token's value. If the current token is a left parenthesis, it calls the expression() method to get the 
     * expression inside the parenthesis and returns it. */
   private Node factor() {
        if (match(TYPE.PLUS, TYPE.MINUS)) {
            Token operator = previous();
            Node right = factor();
            Node node = new UnaryNode(operator, right);
            return node;
        } 
        else if (match(TYPE.ZAHL)) {
            Token value = previous();
            ValueNode vnode = new ValueNode(value);
            return vnode;
        } 
        else if (match(TYPE.OPEN_PAR)) {
            Node expression = this.expression();
            consume(TYPE.CLOSE_PAR, "Expect ')' after expression.");
            return expression;
        }
        else { 
            throw new RuntimeException("Expect expression, but found " + peek().zustand + "");
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
        if (isAtEnd())
            return false;
        return peek().zustand == type;
    }

    private Token advance() {
        if (!isAtEnd())
            current++;
            return previous();
    }

    private boolean isAtEnd() {
        return current==tokens.size();
    }

    private Token peek() {
        return tokens.get(current);
    }

    private Token previous() {
        return tokens.get(current - 1);
    }
}