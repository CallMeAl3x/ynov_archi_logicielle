package com.ynov.solid;

import com.ynov.solid.srp.SRPDemo;
import com.ynov.solid.ocp.OCPDemo;
import com.ynov.solid.lsp.LSPDemo;
import com.ynov.solid.isp.ISPDemo;
import com.ynov.solid.dip.DIPDemo;

/**
 * Master runner that executes all 5 SOLID principle demonstrations.
 */
public class SOLIDDemo {

    public static void main(String[] args) {
        separator("SOLID PRINCIPLES - COMPLETE DEMO");

        separator("S — Single Responsibility Principle");
        SRPDemo.main(args);

        separator("O — Open/Closed Principle");
        OCPDemo.main(args);

        separator("L — Liskov Substitution Principle");
        LSPDemo.main(args);

        separator("I — Interface Segregation Principle");
        ISPDemo.main(args);

        separator("D — Dependency Inversion Principle");
        DIPDemo.main(args);

        separator("ALL 5 SOLID PRINCIPLES DEMONSTRATED");
    }

    private static void separator(String title) {
        System.out.println();
        System.out.println("============================================================");
        System.out.println("  " + title);
        System.out.println("============================================================");
    }
}
