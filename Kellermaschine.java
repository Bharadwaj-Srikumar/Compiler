import java.util.Stack;

public class Kellermaschine {
    private String [] postfix ;

    public Kellermaschine(String [] postfix){
        this.postfix=postfix;
    }
    public double berechnen() {
        Stack<Double> stack = new Stack<>();
        for (String token : this.postfix) {
            switch (token) {
                case "+":
                    double rightPlus = stack.pop();
                    double leftPlus = stack.pop();
                    stack.push(leftPlus + rightPlus);
                    break;
                case "-":
                    double rightMinus = stack.pop();
                    double leftMinus = stack.pop();
                    stack.push(leftMinus - rightMinus);
                    break;
                case "*":
                    double rightMult = stack.pop();
                    double leftMult = stack.pop();
                    stack.push(leftMult * rightMult);
                    break;
                case "/":
                    double rightDiv = stack.pop();
                    double leftDiv = stack.pop();
                    stack.push(leftDiv / rightDiv);
                    break;
                default:
                    // Assuming the token is a number
                    stack.push(Double.parseDouble(token));
                    break;
            }
        }

        return stack.pop();
    }
}
