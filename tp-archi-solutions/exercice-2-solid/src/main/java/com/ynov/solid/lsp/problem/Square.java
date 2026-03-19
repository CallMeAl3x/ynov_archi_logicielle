package com.ynov.solid.lsp.problem;

/**
 * PROBLEM: Square extends Rectangle — classic LSP violation.
 *
 * A square must keep width == height, so it overrides both setters.
 * Code written for Rectangle now behaves unexpectedly when given a Square.
 */
public class Square extends Rectangle {

    public Square(int side) {
        super(side, side);
    }

    /**
     * Overrides to force both dimensions to stay equal — violates LSP:
     * a client using Rectangle API cannot predict this side-effect.
     */
    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        super.setHeight(width);   // side-effect not expected by Rectangle clients
    }

    @Override
    public void setHeight(int height) {
        super.setWidth(height);   // side-effect not expected by Rectangle clients
        super.setHeight(height);
    }
}
