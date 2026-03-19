package com.ynov.solid.lsp.solution;

/**
 * SOLUTION: Shape interface — only declares what ALL shapes share.
 * Rectangle and Square are independent implementations; neither extends
 * the other, so there is no substitution problem.
 */
public interface Shape {

    /**
     * Returns the area of this shape.
     */
    int area();

    /**
     * Returns a descriptive label for display.
     */
    String describe();
}
