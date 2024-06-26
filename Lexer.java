import java.util.ArrayList;
class Lexer {

    // Initializierung der benötigten Attributen zum Entwerfen des Lexer/Scanner
    String lexerInput;
    ArrayList<String> lexerOutput = new ArrayList<String>();
    String lexerTokens = "";

    // Konstruktur zum Initialisieren des Lexers mit der Eingabe
    Lexer(String input) {
        this.lexerInput = input;
    }

  // Listenobjekt zum Zuweisen von Token und Zustand zu jedem gültigen Zeichen der Eingabe

    ArrayList<Token> matcher = new ArrayList<Token>();

    // Methode zum Entfernen der Leerzeichen in der Eingabe und zum Umwandeln der Eingabe in einer ArrayList
    ArrayList<String> trim(String array){
        ArrayList<String> trimmed_array = new ArrayList<String>();
        String leerzeichen = " ";
        String tab = "\t";
        String zeilenUmbruch = "\n";
        String zeilenUmbruch1 = "\r";
        String zeilenUmbruch2 = "\r\n";
        String Ersetzer = "";
        array = array.replace(leerzeichen, Ersetzer).replace(tab, Ersetzer).replace(zeilenUmbruch, Ersetzer).replace(zeilenUmbruch1, Ersetzer).replace(zeilenUmbruch2, Ersetzer);
        for(int i=0;i<array.length();i++){
            String value_trim = String.valueOf(array.charAt(i));
            if(!value_trim.equals(leerzeichen) || !value_trim.equals(zeilenUmbruch) || !value_trim.equals(zeilenUmbruch1) || !value_trim.equals(zeilenUmbruch2) || !value_trim.equals(tab)){
                trimmed_array.add(value_trim);
            }
        }
        return trimmed_array;
    }

    // Methode zum Ausgeben der Eingabe ohne Leerzeichen
    void printlexerOutput(ArrayList<String> lexerOutput){
        String output="";
        for(int i=0;i<lexerOutput.size();i++){
            output=output+lexerOutput.get(i);
        }
        System.out.println("Lexer Input: "+output);
        System.out.println("Lexer Input size: "+output.length());
    }

    // Hauptmethode zum Scannen der Eingabe und zum Drucken der Ausgabe 
    void scan() {
        lexerOutput = trim(lexerInput);
        printlexerOutput(lexerOutput);
        for(int index=0;index<lexerOutput.size();index++) {
            matchToken(lexerOutput.get(index));
        }
        // Anpassen der Token-Werte. Wenn Ziffer nacheinander auftachen, dann sind die Teil einer Zahl.
        for(int i=0;i<matcher.size();i++) {
            if((i<matcher.size()-1)&&(matcher.get(i).token==1&&matcher.get(i+1).token==1)){
                matcher.get(i).token=2;
                matcher.get(i).zustand= TYPE.ZAHL;
            }
            else if((i>0)&&(matcher.get(i-1).token==2&&matcher.get(i).token==1)){
                matcher.get(i).token=2;
                matcher.get(i).zustand= TYPE.ZAHL;
            }
        }        printlexerTokens();

    }

    // Methode zum Analysieren der Eingabe nach Anpassung der Tokens
    void printlexerTokens(){
        for(int j=0;j<matcher.size();j++){
            lexerTokens = lexerTokens + "Match: "+matcher.get(j).value+"\n";
            if(matcher.get(j).token==0){
                lexerTokens = lexerTokens + "Lexikalischer Fehler in Position "+(j+1)+". Ungültiges Zeichen "+matcher.get(j).value;
                System.out.println(lexerTokens);
                System.exit(0);
            }
            else if(j==0 && (j<matcher.size()-2) && matcher.get(j).token==2 && matcher.get(j+1).token==2){
                lexerTokens = lexerTokens+"Token: "+matcher.get(j).token+"\n";
            }
            else if(j==0 && (j<=matcher.size()-2)){
                lexerTokens = lexerTokens+"Token: "+matcher.get(j).token+"\n"+matcher.get(j).zustand+"\n\n";
            }
            else if(j>0 && (j<=matcher.size()-2) && matcher.get(j).token==2 && matcher.get(j+1).token==2){
                lexerTokens = lexerTokens+"Token: "+matcher.get(j-1).token+"->"+matcher.get(j).token+"\n";
            }
            else if(j>0 && (j<=matcher.size()-2) && matcher.get(j).token==matcher.get(j+1).token){
                lexerTokens = lexerTokens+"Token: "+matcher.get(j-1).token+"->"+matcher.get(j).token+"\n"+"Zustand: "+matcher.get(j).zustand+"\n\n";
            }
            else if(j>0 && (j<=matcher.size()-2) && matcher.get(j).token!=matcher.get(j+1).token){
                lexerTokens = lexerTokens+"Token: "+matcher.get(j-1).token+"->"+matcher.get(j).token+"\n"+"Zustand: "+matcher.get(j).zustand+"\n\n";
            }
            else if((j==matcher.size()-1) && matcher.get(j-1).token==2 && matcher.get(j).token==2){
                lexerTokens = lexerTokens+"Token: "+matcher.get(j-1).token+"->"+matcher.get(j).token+"\n"+"Zustand: "+matcher.get(j).zustand+"\n\n";
            }
            else if((j==matcher.size()-1) && matcher.get(j-1).token!=matcher.get(j).token){
                lexerTokens = lexerTokens+matcher.get(j-1).token+"->"+matcher.get(j).token+"\n"+"Zustand: "+matcher.get(j).zustand+"\n\n";
            }
        }
        System.out.println("Lexer Tokens:\n"+lexerTokens);
    }

    // Methode zum Zuweisen von Token und Zustand zum aktuellen Zeichen der Eingabe
    void matchToken(String value){
        TYPE zustand;
        byte token;
        switch(value){
            case "(":
                zustand = TYPE.OPEN_PAR;
                token = 3;
                break;
            case ")":
                zustand = TYPE.CLOSE_PAR;
                token = 4;
                break;
            case "I", "V", "X", "L", "C", "D", "M":
                zustand = TYPE.ZIFFER;
                token = 1;
                break;
            case "+":
                zustand = TYPE.PLUS;
                token = 7;
                break;
            case "-": 
                zustand = TYPE.MINUS;
                token = 8;
                break;
            case "*": 
                zustand = TYPE.MULT;
                token = 5;
                break;
            case "/": 
                zustand = TYPE.DIV;
                token = 6;
                break;
            default: 
                zustand = TYPE.NO_TYPE;
                token = 0;
                break;
            }

        matcher.add(new Token(token,zustand,value));       
    }

    ArrayList<String> getResult(){
        System.out.println("Lexer Output:"+lexerOutput+"\n\n");
        return lexerOutput;
    }
}