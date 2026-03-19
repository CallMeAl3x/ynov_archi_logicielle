package com.ynov.solid.srp.solution;

/**
 * Thin orchestrator — delegates to specialised single-responsibility classes.
 * This class has ONE responsibility: coordinate the user-creation workflow.
 */
public class UserService {

    private final UserValidator    validator;
    private final UserRepository   repository;
    private final EmailService     emailService;
    private final UserReportService reportService;

    public UserService(UserValidator validator,
                       UserRepository repository,
                       EmailService emailService,
                       UserReportService reportService) {
        this.validator     = validator;
        this.repository    = repository;
        this.emailService  = emailService;
        this.reportService = reportService;
    }

    public void createUser(String name, String email) {
        User user = new User(name, email);
        if (!validator.validate(user)) {
            System.out.println("[UserService] User creation aborted due to validation errors.");
            return;
        }
        repository.save(user);
        emailService.sendWelcomeEmail(user);
        System.out.println(reportService.generateReport(user));
    }
}
