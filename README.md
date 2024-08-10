# Compilerdesign
Der Compiler zum Umwandeln römischer mathematischen Ausdrücke in arabischen Ausdrücken und Berechnen des Endergebnisses als eine Dezimalzahl, was Teil der Prüfungsleistung des Fachs Compilerbau an der Hochschule Bochum ist.

Der Compiler nimmt mathematische Ausdrücke in Form römische Zahlen und Operatoren (+,-,*,/,%), konvertiert die römischen Zahlen in arabischen Zahlen nach den offiziellen Umwandlungsregeln, scannt und parst die Eingabe (prüft die Eingabe auf syntaktische sowie semantische korrektheit), erzeugt den abstrakten Syntaxbaum nach definierter Sprachgrammatik, erzeugt den ZwischenCode in der PostFix-Notation (in Form Operand, Operand, Operator) und berechnet ebenfalls im Endeffekt das Ergebnis des Ausdrücks nach den mathematischen Berechnungsregeln.

Der Compiler hat 5 Kernbestandteile nämlich Lexer, Converter, Parser, ZwischenCodeErzeuger und der Kellermaschine.

1. Lexer: Der Lexer scannt die Eingabe (in Form römische Zahlen und Operatoren), entfernt Leerzeichen in der Eingabe, prüft nach lexikalischer Korrektheit und weist allen Eingabezeichen jeweiligen Tokens hinzu.

2. Converter: Der Converter wandelt die Eingabezeichen (insbesondere der römischen Zahlen in der Eingabe) zu der Form arabischen Zahlen nach den offiziellen Regeln zum Umwandeln römischer in arabischer Zahlen, prüft nach semantischer Korrektheit und weist wieder die neue Array Tokens hinzu.

3. Parser: Der Parser prüft die Ausgabe vom Converter auf syntaktische Korrektheit basierend auf der definierten Sprachgrammatik und erzeugt einen abstrakten Syntaxbaum (AST) mithilfe der Node-Klassen (NumberNode, OneChildNode, TwoChildNode).

Der Parser verarbeitet eine Liste von Tokens (ArrayList<Token>) und versucht, eine AST-Struktur daraus zu erstellen. Das Ziel ist es, eine Baumstruktur zu generieren, die die logische Struktur des Ausdrucks im Eingabetoken darstellt.

Die Wurzel des Baumes wird durch das letzte Operator-Token repräsentiert.
Die linken und rechten Terme sind die linke und rechte Seite des Baumes. Dafür dienen die Methoden expression(), term(), factor() welche Definition als Kommentar in dem Code selber zu finden ist.
Die NodePrinter-Klasse ermöglicht die Visualisierung des abstrakten Syntaxbaums (AST), indem sie den Baum in einer konsolengerechten Formatierung ausgibt.

4.Zwischencode erzeugen, auch bekannt als Intermediate Code Generation, ist ein wichtiger Schritt im Kompilierungsprozess. In dieser Phase wird der abstrakte Syntaxbaum (AST) in einen Zwischencode umgewandelt. Dieser Zwischencode ist oft eine einfache, aber explizite Darstellung des Programms.
Der Zwischencode nutzt die Postfix-Notation, um den AST in lineare Ausdrücke ohne Klammern umzuwandeln. Dabei werden die Node-Klassen (NumberNode, OneChildNode, TwoChildNode) verwendet.
Die Ausgabe dieser linearen Ausdrücke folgt so:
NumberNode: Operand
OneChildNode: Operand Operator
TwoChildNode: Operand Operand Operator

Methode erzeugen:

Wenn der übergebene Knoten ein NumberNode ist wird der Wert dieser Zahl dem Zwischencode als PUSH-Befehl hinzugefügt.
Wenn der Knoten ein OneChildNode ist wird zuerst der Zwischencode für das rechte Kind generiert.
Dann wird der Operator dieses Knotens dem Zwischencode als OPERATOR-Befehl hinzugefügt.
Fall TwoChildNode:
Wenn der Knoten ein TwoChildNode ist, wird zuerst der Zwischencode für das linke Kind generiert.
Dann wird der Zwischencode für das rechte Kind generiert.
Schließlich wird der Operator dieses Knotens dem Zwischencode als OPERATOR-Befehl hinzugefügt.

5. Kellermaschine: In diesem Compiler wurde die Kellermaschine über die Node-Klasse hinaus realisiert. Die Methode berechnen() wurde von NumberNode, OneChildNode sowie TwoChildNode vererbt und wird zum Berechnen des mathematischen Ergebnis in dem Parser genutzt.

Sonstige Teile des Compilers
1. Type und Token: Die Klassen Type und Token werden überall in dem FrontEnd des Compilers nämlich Lexer, Converter und Parser verwendet, um den Eingabezeichen Tokens zuzuweisen.

2. NodePrinter: Nimmt die Eingabe vom Parser (in Form linker Teil, Operator, rechter Teil) und erzeugt den abstrakten Syntaxbaum nach definierter Sprachgrammatik (über die Methoden expression(), term(), und factor() des Parsers).

3. Node: Node ist eine abstrakte Klasse, die die Methoden berechnen() und toString() beinhält, die von anderen Klassen, die Node vererben, überschrieben bzw. overridet.

4. NumberNode:
   toString(): gibt einfach den String-Wert des Eingabezeichens zurück
   berechnen(): gibt den umgewandelten Double()-Wert des Eingabezeichens zurück

5. OneChildNode:
   toString(): Stellt die Eingabezeichen in Form: "OneChildNode{operator= operatorValue, nodeRight= valueRight}" auf Basis des bevorstehenden Operators dar.
   berechnen(): gibt entweder den positiven- oder den negativen-Wert des Eingabezeichens auf Basis des bevorstehenden Operators zurück.

6. TwoChildNode:
   toString(): Stellt die Eingabezeichen in Form: "{nodeLeft= valueLeft, operator= operatorValue, nodeRight= valueRight}" auf Basis des bevorstehenden Operators dar.
   berechnen(): Wendet die jeweilige mathemtische Operation auf den linken und den rechten Eingabeteil des Parser an und gibt das berechnete Erdergebnis zurück.
