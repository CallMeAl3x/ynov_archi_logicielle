package com.ynov.solid.lsp;

import com.ynov.solid.lsp.problem.LSPProblemDemo;
import com.ynov.solid.lsp.solution.Rectangle;
import com.ynov.solid.lsp.solution.Shape;
import com.ynov.solid.lsp.solution.Square;

/**
 * Full LSP demo: shows the problem and the solution side by side.
 */
public class LSPDemo {

    public static void main(String[] args) {
        System.out.println("=== LSP Demo ===\n");

        System.out.println("--- PROBLEM: Square extends Rectangle ---");
        LSPProblemDemo.main(args);

        System.out.println();
        System.out.println("--- SOLUTION: independent Shape implementations ---");

        Shape[] shapes = {
            new Rectangle(5, 10),
            new Square(7)
        };

        for (Shape s : shapes) {
            System.out.println(s.describe());
        }

        // Polymorphic usage: any Shape can be substituted safely
        System.out.println("\nTotal area of all shapes: " +
            java.util.Arrays.stream(shapes).mapToInt(Shape::area).sum());

        // Verify Rectangle can be resized independently
        Rectangle r = new Rectangle(3, 4);
        r.setWidth(5);
        r.setHeight(10);
        System.out.println("Rectangle after resize: " + r.describe()); // area=50 CORRECT

        System.out.println("\n=== LSP Demo: DONE ===");
    }
}
