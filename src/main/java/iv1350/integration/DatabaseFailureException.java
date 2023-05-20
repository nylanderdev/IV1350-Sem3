package iv1350.integration;

/**
 * An unchecked exception indicating a critical error when interfacing with a database
 */
public class DatabaseFailureException extends RuntimeException {
    /**
     * The recommended hard-coded id used to simulate a database failure
     */
    public static final int DATABASE_FAILURE_TRIGGER_ID = -2;

    /**
     * Creates a generic exception indicating that a database failure has occurred
     */
    public DatabaseFailureException() {
        super("A database failure occurred");
    }
}
