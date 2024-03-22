package ca.trovo.greetings.greeter;

import java.io.Serial;

import ca.trovo.greetings.Greeter;


/**
 * A exception for when a {@link Greeter} cannot be found.
 */
public class GreeterNotFoundException extends RuntimeException {

	/** The generated serial ID. */
	@Serial
	private static final long serialVersionUID = -1427855482290763799L;


	/**
	 * Constructs a new runtime exception with {@code null} as its detail message.
	 * The cause is not initialized, and may subsequently be initialized by a call to {@link #initCause}.
     */
    public GreeterNotFoundException() {
        super();
    }

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a call to {@link #initCause}.
     *
     * @param message
     * 			The detail message.
     *
     */
    public GreeterNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new runtime exception with the specified detail message and cause.
     * <p>Note that the detail message associated with {@code cause} is <i>not</i> automatically
     * incorporated in this exception's detail message.
     *
     * @param message
     * 			The detail message.
     * @param cause
     * 			The cause. A {@code null} value is permitted, and indicates that the cause is nonexistent or unknown.
     *
     */
    public GreeterNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new runtime exception with the specified cause and a detail message of {@code (cause==null ? null : cause.toString())}
     * (which typically contains the class and detail message of {@code cause}).
     * This constructor is useful for runtime exceptions that are little more than wrappers for other throwables.
     *
     * @param cause
     * 			The cause. A {@code null} value is permitted, and indicates that the cause is nonexistent or unknown.
     *
     */
    public GreeterNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new runtime exception with the specified detail message, cause, suppression enabled or disabled,
     * and writable stack trace enabled or disabled.
     *
     * @param message
     * 			The detail message.
     * @param cause
     * 			The cause. A {@code null} value is permitted, and indicates that the cause is nonexistent or unknown.
     * @param enableSuppression
     * 			Whether or not suppression is enabled or disabled
     * @param writableStackTrace
     * 			Whether or not the stack trace should be writable
     *
     *
     */
    protected GreeterNotFoundException(String message, Throwable cause,
                               boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }




    /**
     * Constructs a new exception with the specified name in the message message.
     *
     * @param name
     * 			The greeter name that was not found to include in message.
     *
     */
    public static GreeterNotFoundException forName(String name) {
    	if (name == null || name.isEmpty()) {
    		return new GreeterNotFoundException(new NullPointerException("The name is required to get a Greeter."));
    	}

    	return new GreeterNotFoundException("Greeter with name %s was not found.".formatted(name));
    }
}
