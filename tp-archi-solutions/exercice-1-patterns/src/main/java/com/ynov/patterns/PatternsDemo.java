package com.ynov.patterns;

import com.ynov.patterns.builder.BuilderDemo;
import com.ynov.patterns.factory.FactoryDemo;
import com.ynov.patterns.prototype.PrototypeDemo;
import com.ynov.patterns.singleton.SingletonDemo;

/**
 * Master runner that executes all four design-pattern demonstrations
 * with clear section separators.
 */
public class PatternsDemo {

    public static void main(String[] args) {
        separator("DESIGN PATTERNS - COMPLETE DEMO");

        separator("1. BUILDER PATTERN");
        BuilderDemo.main(args);

        separator("2. FACTORY PATTERN");
        FactoryDemo.main(args);

        separator("3. SINGLETON PATTERN");
        SingletonDemo.main(args);

        separator("4. PROTOTYPE PATTERN");
        PrototypeDemo.main(args);

        separator("ALL PATTERNS DEMONSTRATED SUCCESSFULLY");
    }

    private static void separator(String title) {
        System.out.println();
        System.out.println("============================================================");
        System.out.println("  " + title);
        System.out.println("============================================================");
    }
}
