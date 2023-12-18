package ca.trovo.hello.greeter;

import java.util.List;
import java.util.Random;

import ca.trovo.hello.Greeter;


/**
 * A simple {@link Greeter} that uses a short and pre-defined set of slang responses.
 *
 * @see #GREETINGS
 *
 */
final class SlangGreeter extends RandomListGreeter {

	/**
	 * The short and pre-defined set of responses.
	 * <p>
	 * It expects to have one {@link String#formatted(Object...)} parameter
	 * to replace with {@code greetee}'s value.
	 */
	private static final List<String> GREETINGS = List.of(
		"Yo %s!",
		"Yo %s, ma'man!",
		"Hail %s",
		"'Sup %s",
		"Whatsuuuuup bro %s",
		"%s boss in tha haussss!"
	);


	/**
	 * Default constructor.
	 */
	public SlangGreeter() {
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
	public SlangGreeter(Random random) {
		super(GREETINGS, random);
	}

}
