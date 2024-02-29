// Node class is abstract class for defining evaluate() function 
// gets implemented by its subclasses. 

// Also known as Abstract Syntax Tree (AST)
public abstract class Node {
    // abstract evaluate method to be implemented by subclasses
    public abstract double evaluate();

    public abstract String toString();
}