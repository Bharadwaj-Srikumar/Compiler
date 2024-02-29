import javax.swing.JOptionPane;
public class Compiler{
    public static void main(String[] args){
        String input = JOptionPane.showInputDialog("Enter the roman numeral expression");

        System.out.println("\nCompiler gestartet:\n");
        Lexer lexer = new Lexer(input);
        lexer.scan(); // Lexer Ergebnis
        
        Converter converter = new Converter(lexer.getResult());
        converter.convert(); // Converter Ergebnis

        System.out.println("Parser Tokens:");
        Parser parser = new Parser(converter.getResult());
        Node syntaxTree = parser.parse();
        System.out.println(syntaxTree+"\n"); // Parser Ergebnis

        System.out.println("Abstrakter Syntaxbaum:");
        NodePrinter nodePrinter = new NodePrinter(syntaxTree);
        nodePrinter.print(); // Syntax Tree

        System.out.println("\n"+"Zwischencode (PostFix-Notation):");
        IntermediateCodeGenerator intermediateCodeGenerator = new IntermediateCodeGenerator(syntaxTree);
        intermediateCodeGenerator.generate();
        intermediateCodeGenerator.print();
        
        double result = syntaxTree.evaluate();
        System.out.println("\n"+"Endergebnis: " + result+"\n\nCompiler beendet");


    }
}