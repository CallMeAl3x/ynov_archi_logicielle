package com.ynov.solid.lsp.solution;

/**
 * SOLUTION: Rectangle implements Shape directly.
 * Independent from Square — no inheritance coupling.
 */
public class Rectangle implements Shape {

    private int width;
    private int height;

    public Rectangle(int width, int height) {
        this.width  = width;
        this.height = height;
    }

    public void setWidth(int width)   { this.width  = width;  }
    public void setHeight(int height) { this.height = height; }
    public int  getWidth()  { return width;  }
    public int  getHeight() { return height; }

    @Override
    public int area() { return width * height; }

    @Override
    public String describe() {
        return "Rectangle(" + width + "x" + height + ") area=" + area();
    }
}
