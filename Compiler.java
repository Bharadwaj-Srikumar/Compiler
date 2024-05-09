import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
public class Compiler{
    public static void main(String[] args) throws IOException{
        String path = "./Eingabebeispiele/Eingabe1.txt";
        Path Datei = Path.of(path);
        String input = Files.readString(Datei);
        System.out.println("\nCompiler gestartet:\n");
        String delimiter = ";";
        String[] split_input = input.split(delimiter);

        for(int i=0;i<split_input.length;i++){
            String input_i = split_input[i];
            int ausdruck_nummer = i+1;
            System.out.println("Evaluation des "+ ausdruck_nummer +" Ausdrucks: " + input_i + "\n");

            // Lexer Ergebnis
            Lexer lexer = new Lexer(input_i);
            lexer.scan();
            
            // Converter Ergebnis
            Converter converter = new Converter(lexer.getResult());
            converter.convert();

            // Parser Ergebnis
            System.out.println("Parser Tokens:");
            Parser parser = new Parser(converter.getResult());
            Node syntaxTree = parser.parse();
            System.out.println(syntaxTree+"\n");

            // Syntax Tree
            System.out.println("Abstrakter Syntaxbaum:");
            NodePrinter nodePrinter = new NodePrinter(syntaxTree);
            nodePrinter.print();

            // Zwischencode
            System.out.println("\n"+"Zwischencode (PostFix-Notation):");
            ZwischenCode intermediateCodeGenerator = new ZwischenCode(syntaxTree);
            intermediateCodeGenerator.generate();
            intermediateCodeGenerator.print();

            // Endergebnis            
            double result = syntaxTree.berechnen();
            System.out.println("\n"+"Endergebnis: " + result+"\n");
        }
        System.out.println("\nCompiler beendet");
    }
}