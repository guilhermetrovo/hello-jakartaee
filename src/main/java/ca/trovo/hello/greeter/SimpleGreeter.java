package ca.trovo.hello.greeter;

import java.util.List;
import java.util.Random;

import ca.trovo.hello.Greeter;


/**
 * A simple {@link Greeter} that uses a short and pre-defined set of responses.
 *
 * @see #GREETINGS
 *
 */
final class SimpleGreeter extends RandomListGreeter {

	/**
	 * The short and pre-defined set of responses.
	 * <p>
	 * It expects to have one {@link String#formatted(Object...)} parameter
	 * to replace with {@code greetee}'s value.
	 */
	private static final List<String> GREETINGS = List.of(
		"Hello %s",
		"Hi %s",
		"Hey %s",
		"*eye contact and smile at* %s",
		"How are you %s?"
	);


	/**
	 * Default constructor.
	 */
	public SimpleGreeter() {
		super(GREETINGS);
	}

	/**
	 * Uses the given randomizer for greetings.
	 *
	 * @param random
	 * 			The randomizer to use.
	 *
	 * @see Random#nextInt(int)
	 *
	 */
	public SimpleGreeter(Random random) {
		super(GREETINGS, random);
	}

}
