package com.ynov.solid.lsp.problem;

/**
 * PROBLEM: Rectangle base class.
 * Intended to be extended — but Square will violate LSP.
 */
public class Rectangle {

    protected int width;
    protected int height;

    public Rectangle(int width, int height) {
        this.width  = width;
        this.height = height;
    }

    public void setWidth(int width)   { this.width  = width;  }
    public void setHeight(int height) { this.height = height; }

    public int getWidth()  { return width;  }
    public int getHeight() { return height; }

    public int area() {
        return width * height;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
               "{width=" + width + ", height=" + height + ", area=" + area() + "}";
    }
}
