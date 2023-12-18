package ca.trovo.hello;

/**
 * Represents a greeter, one who greets someone else. The host (person) acting and creating {@link Greeting}.
 */
public interface Greeter {

	/**
	 * Perform the interaction with {@code greetee}, creating a {@link Greeting}.
	 * <p>
	 * How this <b>greeter</b> <em>greets</em> the <b>greetee</b>.
	 * </p>
	 *
	 * @param greetee
	 * 			One how is greeted.
	 *
	 * @return The interaction, how this <b>greeter</b> has <em>greeted</em> the <b>greetee</b>.
	 *
	 */
	public Greeting greet(String greetee);

}
