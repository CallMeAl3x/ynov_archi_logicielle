package com.ynov.solid.lsp.problem;

/**
 * Shows the LSP bug: code written for Rectangle fails silently when a Square
 * is substituted.
 */
public class LSPProblemDemo {

    /**
     * This method assumes it receives a Rectangle where width and height
     * can be set independently.
     */
    static void resizeAndPrint(Rectangle r) {
        r.setWidth(5);
        r.setHeight(10);
        System.out.println("Expected area = 50, actual area = " + r.area() + "  -> " + r);
    }

    public static void main(String[] args) {
        System.out.println("--- LSP Problem Demo ---");

        Rectangle rect = new Rectangle(3, 4);
        System.out.print("Rectangle: ");
        resizeAndPrint(rect);   // area = 50 -> CORRECT

        Rectangle square = new Square(5);
        System.out.print("Square   : ");
        resizeAndPrint(square); // area = 100 -> WRONG! (both sides became 10)
    }
}
