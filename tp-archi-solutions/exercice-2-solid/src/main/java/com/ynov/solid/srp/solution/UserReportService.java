package com.ynov.solid.srp.solution;

/**
 * Single responsibility: generate user reports.
 */
public class UserReportService {

    public String generateReport(User user) {
        System.out.println("[UserReportService] Generating report...");
        return "=== User Report ===\n" +
               "Name   : " + user.getName()  + "\n" +
               "Email  : " + user.getEmail() + "\n" +
               "Status : Active\n";
    }
}
