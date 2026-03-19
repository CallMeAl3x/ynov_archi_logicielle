package com.ynov.solid.lsp.solution;

/**
 * SOLUTION: Square implements Shape independently.
 * No inheritance from Rectangle means no LSP violation.
 */
public class Square implements Shape {

    private int side;

    public Square(int side) {
        this.side = side;
    }

    public void setSide(int side) { this.side = side; }
    public int  getSide()         { return side;      }

    @Override
    public int area() { return side * side; }

    @Override
    public String describe() {
        return "Square(" + side + "x" + side + ") area=" + area();
    }
}
