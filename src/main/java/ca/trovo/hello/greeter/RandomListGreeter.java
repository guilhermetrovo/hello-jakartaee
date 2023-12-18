package ca.trovo.hello.greeter;

import java.util.List;
import java.util.Random;

import ca.trovo.hello.Greeter;
import ca.trovo.hello.Greeting;


/**
 * A simple base class for a {@link Greeter} that uses a pre-defined set of responses.
 */
abstract class RandomListGreeter implements Greeter {


	/** A single instance of a randomizer to use internally. */
	private final Random random;

	/** The pre-defined set of responses. */
	protected final List<String> greetings;



	/**
	 * Default constructor that uses a simple {@link Random#Random()} instance.
	 *
	 * @param greetings
	 * 			The pre-defined set of responses.
	 *
	 */
	protected RandomListGreeter(List<String> greetings) {
		this(greetings, new Random());
	}

	/**
	 * Uses the given randomizer for greetings.
	 *
	 * @param greetings
	 * 			The pre-defined set of responses.
	 * @param random
	 * 			The randomizer to use.
	 *
	 * @see Random#nextInt(int)
	 *
	 */
	protected RandomListGreeter(List<String> greetings, Random random) {
		this.greetings = greetings;
		this.random = random;
	}



	/**
	 * Helper method to get a raw greeting, which needs to be formatted.
	 * @return A random raw greeting from the pre-defined list.
	 * @see #GREETINGS
	 */
	protected final String getGreeting() {
		int index = this.random.nextInt(this.greetings.size());
		String greeting = this.greetings.get(index);
		return greeting;
	}


	@Override
	public Greeting greet(String greetee) {
		String greeting = getGreeting().formatted(greetee);
		return new Greeting(this, greetee, greeting);
	}

}
