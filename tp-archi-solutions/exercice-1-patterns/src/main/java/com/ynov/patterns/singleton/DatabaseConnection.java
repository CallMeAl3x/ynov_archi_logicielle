package com.ynov.patterns.singleton;

/**
 * Thread-safe Singleton representing a database connection.
 * Uses double-checked locking to ensure only one instance is created.
 */
public class DatabaseConnection {

    // Volatile ensures visibility of changes across threads
    private static volatile DatabaseConnection instance;

    private final String connectionUrl;
    private boolean connected;

    /**
     * Private constructor prevents direct instantiation.
     */
    private DatabaseConnection() {
        this.connectionUrl = "jdbc:mysql://localhost:3306/librarydb";
        this.connected     = false;
        System.out.println("[Singleton] DatabaseConnection instance created.");
    }

    /**
     * Returns the single instance, creating it if necessary (double-checked locking).
     */
    public static DatabaseConnection getInstance() {
        if (instance == null) {                        // First check (no lock)
            synchronized (DatabaseConnection.class) {
                if (instance == null) {                // Second check (with lock)
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }

    /**
     * Opens the database connection.
     */
    public void connect() {
        if (!connected) {
            connected = true;
            System.out.println("[DB] Connected to: " + connectionUrl);
        } else {
            System.out.println("[DB] Already connected to: " + connectionUrl);
        }
    }

    /**
     * Executes a simulated SQL query.
     *
     * @param sql the SQL statement to run
     */
    public void executeQuery(String sql) {
        if (!connected) {
            System.out.println("[DB] ERROR: Not connected. Call connect() first.");
            return;
        }
        System.out.println("[DB] Executing query: " + sql);
        System.out.println("[DB] Query executed successfully.");
    }

    /**
     * Closes the database connection.
     */
    public void disconnect() {
        if (connected) {
            connected = false;
            System.out.println("[DB] Disconnected from: " + connectionUrl);
        } else {
            System.out.println("[DB] Already disconnected.");
        }
    }

    public String getConnectionUrl() { return connectionUrl; }
    public boolean isConnected()     { return connected; }
}
